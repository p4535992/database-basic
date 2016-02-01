package datasource.datastore.database.database;

import datasource.DataSourceFactory;
import datasource.datastore.database.AbstractDatabase;
import datasource.datastore.database.Enumerator;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.util.hsqldb.HSQLDBDatabase;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 */
public class HsqlDatabase extends AbstractDatabase<HsqlDatabase> {

	HsqlDatabase(Connection connection){
        super.dataSource = DataSourceFactory.createDataSource(connection);
        super.connection = connection;
        setNewJdbcTemplate();
        setNewDSLContext(super.connection,SQLDialect.HSQLDB);
        jooqUtilities.setDslContext(dslContext);
        jooqUtilities.setSqlDialect(sqlDialect);
	}

    HsqlDatabase(DataSource dataSource){
        super.dataSource = dataSource;
        try {
            super.connection = dataSource.getConnection();
        } catch (SQLException e) {
            super.connection = null;
        }
        setNewJdbcTemplate();
        setNewDSLContext(connection,SQLDialect.HSQLDB);
        jooqUtilities.setDslContext(dslContext);
        jooqUtilities.setSqlDialect(sqlDialect);
    }

    HsqlDatabase(String host, String port, String user, String pass, String database) {
        super.setDriverManager(Enumerator.DBDriver.HSQLDB, Enumerator.DBConnector.HSQLDB,host,port,user,pass,database);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public String getDatabaseName() {
        return Enumerator.DBType.HSQLDB.name();
    }

    @Override
    public String getDriverClassName() {
        return Enumerator.DBDriver.HSQLDB.toString();
    }

    @Override
    public HSQLDBDatabase getJOOQDatabase(Connection conn) {
        HSQLDBDatabase hsqldbDatabase = new HSQLDBDatabase();
        hsqldbDatabase.setConnection(super.connection);
        hsqldbDatabase.create();
        return hsqldbDatabase;
    }

    @Override
    public DSLContext getDSLContext() {
        return dslContext;
    }

    @Override
    public SQLDialect getSQLDialect() {
        return SQLDialect.HSQLDB;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return super.jdbcTemplate;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }






}
