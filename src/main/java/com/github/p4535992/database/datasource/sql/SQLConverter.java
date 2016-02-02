package com.github.p4535992.database.datasource.sql;

import com.github.p4535992.util.string.StringUtilities;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.jooq.SQLDialect;
import org.jooq.tools.jdbc.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import static com.github.p4535992.database.datasource.sql.SQLEnum.DBDriver.*;
import static org.jooq.SQLDialect.*;
import static org.jooq.SQLDialect.DEFAULT;

/**
 * Created by 4535992 on 02/02/2016.
 * @author 4535992.
 */
public class SQLConverter {

    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(com.github.p4535992.util.database.sql.SQLConverter.class);

    /**
     * Method for get a mapt with all SQL java types.
     * href: http://www.java2s.com/Code/Java/Database-SQL-JDBC/convertingajavasqlTypesintegervalueintoaprintablename.htm.
     * @param jdbcType code int of the type sql.
     * @return map of SQL Types with name
     */
    public static Map<Integer,String> convertIntToJdbcTypeName(int jdbcType) {
        Map<Integer,String> map = new HashMap<>();
        // Get all field in java.sql.Types
        Field[] fields = java.sql.Types.class.getFields();
        for (Field field : fields) {
            try {
                String name = field.getName();
                Integer value = (Integer) field.get(null);
                map.put(value, name);
            } catch (IllegalAccessException e) {
                logger.warn(e.getMessage(),e);
            }
        }
        return map;
    }

    /**
     * method to convert a String to a SQL Type.
     * @param value the String value to convert.
     * @return the SQL Type of the value.
     */
    public static int convertStringToSQLTypes(String value){
        if(value == null) return Types.NULL;
        if(StringUtilities.isFloat(value)) return Types.FLOAT;
        if(StringUtilities.isDouble(value)) return Types.DOUBLE;
        if(StringUtilities.isDecimal(value)) return Types.DECIMAL;
        if(StringUtilities.isInt(value)) return Types.INTEGER;
        if(StringUtilities.isURL(value)) return Types.VARCHAR;
        if(StringUtilities.isNumeric(value)) return Types.NUMERIC;
        else  return Types.VARCHAR;
    }

    /**
     * Method for convert a SQLTypes to a java class.
     * @param type identificator for the SQL java types.
     * @return the corespondetn java class.
     */
    public static Class<?> convertSQLTypes2JavaClass(int type) {
        switch (type) {
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
                return  String.class;
            case Types.NUMERIC:
            case Types.DECIMAL:
                return  java.math.BigDecimal.class;
            case Types.BIT:
                return  Boolean.class;
            case Types.TINYINT:
                return  Byte.class;
            case Types.SMALLINT:
                return  Short.class;
            case Types.INTEGER:
                return  Integer.class;
            case Types.BIGINT:
                return  Long.class;
            case Types.REAL:
            case Types.FLOAT:
                return  Float.class;
            case Types.DOUBLE:
                return  Double.class;
            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
                return  Byte[].class;
            case Types.DATE:
                return  java.sql.Date.class;
            case Types.TIME:
                return  java.sql.Time.class;
            case Types.TIMESTAMP:
                return  java.sql.Timestamp.class;
            case Types.NULL:
                return  Object.class.getSuperclass();
            default:
                return Object.class;
        }
    }

    /**
     * Method for convert a java class to a SQLTypes.
     * @param aClass the correspondent java class.
     * @return the identificator for the SQL java types.
     */
    public static int convertClass2SQLTypes(Class<?> aClass) {
        if(aClass.getName().equals(String.class.getName()))return  Types.VARCHAR;
        else if(aClass.getName().equals(java.math.BigDecimal.class.getName()))return  Types.NUMERIC;
        else if(aClass.getName().equals(Boolean.class.getName()))return  Types.BIT;
        else if(aClass.getName().equals(int.class.getName()))return  Types.INTEGER;
        else if(aClass.getName().equals(Byte.class.getName()))return  Types.TINYINT;
        else if(aClass.getName().equals(Short.class.getName()))return  Types.SMALLINT;
        else if(aClass.getName().equals(Integer.class.getName()))return  Types.INTEGER;
        else if(aClass.getName().equals(Long.class.getName())) return  Types.BIGINT;
        else if(aClass.getName().equals(Float.class.getName()))return  Types.REAL;
        else if(aClass.getName().equals(Double.class.getName()))return  Types.DOUBLE;
        else if(aClass.getName().equals(Byte[].class.getName()))return  Types.VARBINARY;
        else if(aClass.getName().equals(java.sql.Date.class.getName())) return  Types.DATE;
        else if(aClass.getName().equals(java.sql.Time.class.getName()))return  Types.TIME;
        else if(aClass.getName().equals(java.sql.Timestamp.class.getName()))return  Types.TIMESTAMP;
        else if(aClass.getName().equals(java.net.URL.class.getName()))return  Types.VARCHAR;
        return Types.NULL;
    }

    /**
     * Method for convert a SQLTypes to a Stirng name of the types.
     * @param type SQL types.
     * @return the string name of the SQL types.
     */
    public static String convertSQLTypes2String(int type) {
        switch (type) {
            case Types.BIT: return "BIT";
            case Types.TINYINT: return "TINYINT";
            case Types.SMALLINT: return "SMALLINT";
            case Types.INTEGER: return "INTEGER";
            case Types.BIGINT: return "BIGINT";
            case Types.FLOAT: return "FLOAT";
            case Types.REAL:return "REAL";
            case Types.DOUBLE:return "DOUBLE";
            case Types.NUMERIC:return "NUMERIC";
            case Types.DECIMAL:return "DECIMAL";
            case Types.CHAR:return "CHAR";
            case Types.VARCHAR:return "VARCHAR";
            case Types.LONGVARCHAR:return "LONGVARCHAR";
            case Types.DATE:return "DATE";
            case Types.TIME: return "TIME";
            case Types.TIMESTAMP:return "TIMESTAMP";
            case Types.BINARY:return "BINARY";
            case Types.VARBINARY:return "VARBINARY";
            case Types.LONGVARBINARY:return "LONGVARBINARY";
            case Types.NULL:return "NULL";
            case Types.OTHER:return "OTHER";
            case Types.JAVA_OBJECT:return "JAVA_OBJECT";
            case Types.DISTINCT:return "DISTINCT";
            case Types.STRUCT:return "STRUCT";
            case Types.ARRAY:return "ARRAY";
            case Types.BLOB:return "BLOB";
            case Types.CLOB:return "CLOB";
            case Types.REF:return "REF";
            case Types.DATALINK:return "DATALINK";
            case Types.BOOLEAN:return "BOOLEAN";
            case Types.ROWID:return "ROWID";
            case Types.NCHAR:return "NCHAR";
            case Types.NVARCHAR:return "NVARCHAR";
            case Types.LONGNVARCHAR:return "LONGNVARCHAR";
            case Types.NCLOB:return "NCLOB";
            case Types.SQLXML:return "SQLXML";
            default: return "NULL";
        }
    }

    /**
     * Method to convert a DialectDatabase of JOOQ to a String name for a correct cast.
     * @param dialectDb the String to cast to a correct format.
     * @return the String with correct format.
     */
    public static String convertDialectDatabaseToTypeNameId(String dialectDb){
        if(dialectDb.toLowerCase().contains("mysql"))return "mysql";
        if(dialectDb.toLowerCase().contains("cubrid"))return "cubrid";
        if(dialectDb.toLowerCase().contains("derby"))return "derby";
        if(dialectDb.toLowerCase().contains("firebird"))return "firebird";
        if(dialectDb.toLowerCase().contains("h2"))return "h2";
        if(dialectDb.toLowerCase().contains("hsqldb"))return "hsqldb";
        if(dialectDb.toLowerCase().contains("hsql"))return "hsqldb";
        if(dialectDb.toLowerCase().contains("mariadb"))return "mariadb";
        if(dialectDb.toLowerCase().contains("postgres"))return "postgres";
        if(dialectDb.toLowerCase().contains("postgresql"))return "postgres";
        if(dialectDb.toLowerCase().contains("postgres93"))return "postgres93";
        if(dialectDb.toLowerCase().contains("postgres94"))return "postgres94";
        if(dialectDb.toLowerCase().contains("sqlite"))return "sqlite";
        logger.warn("There is not database type for the specific database dialect used:"+dialectDb);
        return "?";
    }

    public static XSDDatatype convertSQLTypesToXDDTypes(int type){
        switch (type) {
            case Types.BIT: return XSDDatatype.XSDbyte;
            case Types.TINYINT: return XSDDatatype.XSDint;
            case Types.SMALLINT: return XSDDatatype.XSDint;
            case Types.INTEGER: return XSDDatatype.XSDinteger;
            case Types.BIGINT: return XSDDatatype.XSDint;
            case Types.FLOAT: return XSDDatatype.XSDfloat;
            //case Types.REAL:return ;
            case Types.DOUBLE:return XSDDatatype.XSDdouble;
            case Types.NUMERIC:return XSDDatatype.XSDinteger;
            case Types.DECIMAL:return XSDDatatype.XSDdecimal;
            case Types.CHAR:return XSDDatatype.XSDstring;
            case Types.VARCHAR:return  XSDDatatype.XSDstring;
            case Types.LONGVARCHAR:return  XSDDatatype.XSDstring;
            case Types.DATE:return  XSDDatatype.XSDdate;
            case Types.TIME: return  XSDDatatype.XSDtime;
            case Types.TIMESTAMP:return  XSDDatatype.XSDdateTime;
            case Types.BINARY:return  XSDDatatype.XSDbase64Binary;
            case Types.VARBINARY:return XSDDatatype.XSDbase64Binary;
            case Types.LONGVARBINARY:return XSDDatatype.XSDbase64Binary;
            case Types.NULL:return XSDDatatype.XSDstring;
            //case Types.OTHER:return "";
            //case Types.JAVA_OBJECT:return "JAVA_OBJECT";
            //case Types.DISTINCT:return "DISTINCT";
            //case Types.STRUCT:return "STRUCT";
            //case Types.ARRAY:return "ARRAY";
            //case Types.BLOB:return "BLOB";
            //case Types.CLOB:return "CLOB";
            //case Types.REF:return "REF";
            //case Types.DATALINK:return "DATALINK";
            case Types.BOOLEAN: return XSDDatatype.XSDboolean;
            //case Types.ROWID:return "ROWID";
            case Types.NCHAR:return XSDDatatype.XSDstring;
            case Types.NVARCHAR:return XSDDatatype.XSDstring;
            case Types.LONGNVARCHAR:return XSDDatatype.XSDstring;
            //case Types.NCLOB:return "NCLOB";
            //case Types.SQLXML:return "SQLXML";
            default: return XSDDatatype.XSDstring;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    public static SQLEnum.DBDriver convertClassDriverToDBDriver(Class<?> driverClass){
        return convertClassDriverToDBDriver(driverClass.getCanonicalName());
    }


    public static SQLEnum.DBDriver convertURLToDbDriver(String url) {
        return convertClassDriverToDBDriver(convertURLToCanonicalDriverClassName(url));
    }

    /**
     * "Guess" the JDBC driver from a connection URL.
     */
    public static String convertURLToCanonicalDriverClassName(String url){
        //JDBCUtils.driver(url)
        switch (convertURLToSqlDialect(url).family()) {
            case CUBRID:return "cubrid.jdbc.driver.CUBRIDDriver";
            case DERBY: return "org.apache.derby.jdbc.ClientDriver";
            case FIREBIRD:return "org.firebirdsql.jdbc.FBDriver";
            case H2:return "org.h2.Driver";
            case HSQLDB:return "org.hsqldb.jdbcDriver";
            case MARIADB:return "org.mariadb.jdbc.Driver";
            case MYSQL:return "com.mysql.jdbc.Driver";
            case POSTGRES:return "org.postgresql.Driver";
            case SQLITE:return "org.sqlite.JDBC";
        }
        logger.error("Can't convert the URL:"+url+" to a DriverClassNAme");
        return "java.sql.Driver";
    }

    /**
     * "Guess" the {@link SQLDialect} from a connection URL.
     */
    public static SQLDialect convertURLToSqlDialect(String url) {
       // return JDBCUtils.dialect(url);
        if (url == null) {
            return SQLDialect.DEFAULT;
        }
        // The below list might not be accurate or complete. Feel free to
        // contribute fixes related to new / different JDBC driver configurations
        else if (url.startsWith("jdbc:cubrid:")) {
            return SQLDialect.CUBRID;
        }
        else if (url.startsWith("jdbc:derby:")) {
            return SQLDialect.DERBY;
        }
        else if (url.startsWith("jdbc:firebirdsql:")) {
            return SQLDialect.FIREBIRD;
        }
        else if (url.startsWith("jdbc:h2:")) {
            return SQLDialect.H2;
        }
        else if (url.startsWith("jdbc:hsqldb:")) {
            return SQLDialect.HSQLDB;
        }
        else if (url.startsWith("jdbc:mariadb:")) {
            return SQLDialect.MARIADB;
        }
        else if (url.startsWith("jdbc:mysql:")
                || url.startsWith("jdbc:google:")) {
            return SQLDialect.MYSQL;
        }
        else if (url.startsWith("jdbc:postgresql:")) {
            return SQLDialect.POSTGRES;
        }
        else if (url.startsWith("jdbc:sqlite:")
                || url.startsWith("jdbc:sqldroid:")) {
            return SQLDialect.SQLITE;
        }else {
            logger.error("Can't convert the URL:" + url + " to a SqlDialect");
            return SQLDialect.DEFAULT;
        }
    }

    /**
     * "Guess" the {@link SQLDialect} from a connection URL.
     */
    public static SQLDialect convertConnectionToSqlDialect(Connection connection) {
       // return JDBCUtils.dialect(conn);
        SQLDialect result = SQLDialect.DEFAULT;
        if (connection != null) {
            try {
                DatabaseMetaData m = connection.getMetaData();
                String url = m.getURL();
                result = convertURLToSqlDialect(url);
            }
            catch (SQLException ignore) {}
        }
        if (result == SQLDialect.DEFAULT) {
            // If the dialect cannot be guessed from the URL, take some other
            // measures, e.g. by querying DatabaseMetaData.getDatabaseProductName()
        }
        logger.error("Can't convert the Connection to a SQLDialect");
        return result;
    }

    public static SQLEnum.DBType convertSqlDialectToDbType(SQLDialect sqlDialect){
        switch(sqlDialect){
            case CUBRID: return SQLEnum.DBType.CUBRID;
            case DERBY: return SQLEnum.DBType.DERBY;
            case FIREBIRD: return SQLEnum.DBType.FIREBIRD;
            case H2: return SQLEnum.DBType.H2;
            case HSQLDB: return SQLEnum.DBType.HSQLDB;
            case MARIADB: return SQLEnum.DBType.MARIADB;
            case MYSQL: return SQLEnum.DBType.MYSQL;
            case POSTGRES: return SQLEnum.DBType.POSTGRES;
            case POSTGRES_9_3: return SQLEnum.DBType.POSTGRES;
            case POSTGRES_9_4: return SQLEnum.DBType.POSTGRES;
            case POSTGRES_9_5: return SQLEnum.DBType.POSTGRES;
            case SQLITE: return SQLEnum.DBType.SQLITE;
            default:{
                logger.error("Can't convert the SqlDialect:"+sqlDialect.name()+" to a DBType");
                return null;
            }
        }
    }


    public static SQLEnum.DBDriver convertClassDriverToDBDriver(String canonicalNameDriverClass){
        if(canonicalNameDriverClass.contains(SQLEnum.DBDriver.MYSQL.getDriver())) return SQLEnum.DBDriver.MYSQL;
        else if(canonicalNameDriverClass.contains(SQLEnum.DBDriver.CUBRID.getDriver())) return SQLEnum.DBDriver.CUBRID;
        else if(canonicalNameDriverClass.contains(SQLEnum.DBDriver.DERBY.getDriver())) return SQLEnum.DBDriver.DERBY;
        else if(canonicalNameDriverClass.contains(SQLEnum.DBDriver.FIREBIRD.getDriver())) return SQLEnum.DBDriver.FIREBIRD;
        else if(canonicalNameDriverClass.contains(SQLEnum.DBDriver.H2.getDriver())) return SQLEnum.DBDriver.H2;
        else if(canonicalNameDriverClass.contains(SQLEnum.DBDriver.HSQLDB.getDriver())) return SQLEnum.DBDriver.HSQLDB;
        else if(canonicalNameDriverClass.contains(SQLEnum.DBDriver.MARIADB.getDriver())) return SQLEnum.DBDriver.MARIADB;
        else if(canonicalNameDriverClass.contains(SQLEnum.DBDriver.POSTGRES.getDriver())) return SQLEnum.DBDriver.POSTGRES;
        else if(canonicalNameDriverClass.contains(SQLEnum.DBDriver.SQLITE.getDriver())) return SQLEnum.DBDriver.SQLITE;
        else {
            logger.error("Can't convert the canonicalNameDriverClass:"+canonicalNameDriverClass+" to a DBType");
            return null;
        }
    }

    public static SQLEnum.DBConnector convertClassDriverToDBConnector(Class<?> driverClass){
        return convertDBDriverToDbConnector(convertClassDriverToDBDriver(driverClass));
    }

    public static SQLEnum.DBConnector convertDBDriverToDbConnector(SQLEnum.DBDriver dbDriver){
        switch(dbDriver){
            case MYSQL: return SQLEnum.DBConnector.MYSQL;
            default: {
                logger.error("Can't convert the DBDriver:"+dbDriver.name()+" to a DBConnector");
                return null;
            }
        }
    }



}
