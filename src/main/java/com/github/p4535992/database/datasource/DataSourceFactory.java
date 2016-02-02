package com.github.p4535992.database.datasource;

import com.jolbox.bonecp.BoneCPDataSource;
import com.github.p4535992.database.datasource.sql.SQLConverter;
import com.github.p4535992.database.datasource.sql.SQLEnum;
import com.github.p4535992.database.datasource.context.DatabaseContextFactory;
import com.github.p4535992.database.datasource.context.LocalContext;
import com.github.p4535992.database.datasource.context.LocalContextFactory;
import com.github.p4535992.database.datasource.sql.SQLUtilities;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.NamingManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by 4535992 on 01/02/2016.
 */
public class DataSourceFactory {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(DataSourceFactory.class);

    private static DataSourceFactory ourInstance =
            new DataSourceFactory();

    public static DataSourceFactory getInstance() {
        return ourInstance;
    }

    private DataSourceFactory() {}

    private static LocalContext ctx;
    private static Map<LocalContext,List<DataSource>> mapOfLocalContext = new HashMap<>();

    public static Map<LocalContext, List<DataSource>> getMapOfLocalContext() {
        return mapOfLocalContext;
    }

    public static DataSource createDataSource(
            String dataSourceName,String jdbcUrl,String driverDbClassName,
            String username,String password) {
        return createDataSourceBase(dataSourceName,jdbcUrl,driverDbClassName,username,password);
    }

    public static DataSource createDataSource(
            String dataSourceName,String jdbcUrl,SQLEnum.DBDriver dbDriver,
            String username,String password) {
        return createDataSourceBase(dataSourceName,jdbcUrl,dbDriver.toString(),username,password);
    }

    public static DataSource createDataSource(String dataSourceName,Connection conn) {
        return createDataSourceBase(dataSourceName,conn);
    }

    public static DataSource createDataSource(Connection conn) {
        try {
            return createDataSourceBase(conn.getMetaData().getDatabaseProductVersion(),conn);
        } catch (SQLException e) {
            logger.warn(e.getMessage(),e);
            return createDataSourceBase(conn.toString(),conn);
        }
    }

    public static DataSource createDataSourceWithSpring(String url,String username,String password,boolean suppressClose){
        DataSource dataSource = new SingleConnectionDataSource(url,username,password,suppressClose);
        addElementTOMap(dataSource);
        return dataSource;
    }

    public static DataSource createDataSourceWithSpring(Class<?> driverClass,String url,String username,String password){
        DriverManagerDataSource driverManag = new DriverManagerDataSource();
        // driverManag.setDriverClassName(driver.getDriver());//"com.mysql.jdbc.Driver"
        driverManag.setDriverClassName(driverClass.getCanonicalName());
        SQLEnum.DBConnector dialectDB = SQLConverter.convertClassDriverToDBConnector(driverClass);
        driverManag.setUrl(url); //"jdbc:mysql://localhost:3306/jdbctest"
        driverManag.setUsername(username);
        driverManag.setPassword(password);
        DataSource dataSource = driverManag;
        addElementTOMap(dataSource);
        return dataSource;

    }

    public static DataSource createDataSourceWithBoneCP(SQLEnum.DBDriver dbDriver, String url, String username, String password){
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(dbDriver.getDriver());
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        addElementTOMap(dataSource);
        return dataSource;
    }

    public static DataSource getDataSource(String dataSourceName) {
        return createDataSourceBase(dataSourceName,null,null,null,null);
    }

    /**
     * https://www.safaribooksonline.com/library/view/java-enterprise-best/0596003846/ch04.html
     * http://penguindreams.org/blog/running-beans-that-use-application-server-datasources-locally/
     * http://www.java2s.com/Code/Java/Database-SQL-JDBC/MiniConnectionPoolManager.htm
     */
    private static DataSource createDataSourceBase(
            String dataSourceName,String jdbcUrl,String driverDbClassName,
            String username,String password) {
        logger.info("Attempting to connect to the DataSource '" + dataSourceName+"'...");
        DataSource dataSource = null;
        try {
            logger.info("...initializing the naming context...");
            if(jdbcUrl == null) {
                //Use DatabaseContext (all context set inner)
                try {
                    NamingManager.setInitialContextFactoryBuilder(new DatabaseContextFactory());
                }catch(java.lang.IllegalStateException e){
                    logger.warn("InitialContextFactoryBuilder already set:"+e.getMessage());
                    /*
                    DatabaseContextFactory factory = new DatabaseContextFactory();
                    Properties env = new Properties();
                    env.put(Context.INITIAL_CONTEXT_FACTORY, driverDbClassName);
                    env.put(Context.PROVIDER_URL, url);
                    factory.createInitialContextFactory(env);
                    */
                    //return null;
                }
            }else {
                //Use LocalContext (all context set outer)
               /* LocalContext ctx = LocalContextFactory.createLocalContext("com.mysql.jdbc.Driver");
                ctx.addDataSource("jdbc/js1","jdbc:mysql://dbserver1/dboneA", "username", "xxxpass");*/
                ctx = LocalContextFactory.createLocalContext(driverDbClassName);
                ctx.addDataSource(dataSourceName, jdbcUrl, username, password);
                addElementTOMap(ctx,dataSourceName);
                //callDataSource(dataSourceName);
            }
            logger.info("...establishing a context...");
        } catch (NamingException e) {
            logger.error(e.getMessage(),e);
        }

        try {
            dataSource = (DataSource) new InitialContext().lookup(dataSourceName);
            //noinspection unused
            Connection conn = dataSource.getConnection();
            logger.info("...establishing a connection to the datasource '"+dataSourceName+"', " +
                    "connect to the database '"+dataSource.getConnection().getCatalog()+"'");
        }catch (NamingException|SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return dataSource;
    }

    private static DataSource createDataSourceBase(String dataSourceName,Connection conn){
        try {
            DatabaseMetaData meta = conn.getMetaData();
            String password = SQLUtilities.getPasswordFromUrl(meta.getURL());
            if (password == null || password.isEmpty()) password = "";
            return createDataSourceBase(dataSourceName,
                    meta.getURL(), meta.getDriverName(), meta.getUserName(), password);
        }catch(SQLException e){
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    private static void addElementTOMap(LocalContext ctx,String dataSourceName) throws NamingException {
        List<DataSource> list = new ArrayList<>();
        try {
           list = mapOfLocalContext.get(ctx);
        }catch(NullPointerException ignored){}

        DataSource dataSource = (DataSource) ctx.lookup(dataSourceName);
        if(list!= null && !list.isEmpty()) {
            list.add(dataSource);
            mapOfLocalContext.put(ctx,list);
        }else{
            mapOfLocalContext.put(ctx, Collections.singletonList(dataSource));
        }
    }

    private static void addElementTOMap(DataSource dataSource){
        try {
            ctx = LocalContextFactory.createLocalContext(dataSource.getConnection().getMetaData().getDriverName());
            addElementTOMap(ctx,dataSource.getConnection().getMetaData().getDatabaseProductVersion());
        } catch (NamingException | SQLException e) {
            logger.warn("Can't add the DataSource generated with Spring to the local Map of dataSource:"+e.getMessage());
        }
    }
}
