package com.github.p4535992.database.datasource.sql;

/**
 * Created by 4535992 on 01/02/2016.
 */
public class SQLEnum {

    public enum DBType {MYSQL,H2,ORACLE,HSQLDB,SQL,DB2,HSQL,MARIADB,CUBRID,DERBY,FIREBIRD,POSTGRES,SQLITE}

    public enum DBDriver{MYSQL(0),MYSQL_GJT(1),H2(2),ORACLE(3),HSQLDB(4),CUBRID(5),DERBY(6),
        FIREBIRD(7),MARIADB(8),POSTGRES(10),SQLITE(11);

        private final Integer value;
        DBDriver(Integer value) {
            this.value = value;
        }

        public String getDriver(){
            return toString();
        }

        @Override
        public String toString() {
            String driver;
            switch (this) {
                case MYSQL: driver = "com.mysql.jdbc.Driver"; break;
                case MYSQL_GJT: driver = "org.gjt.mm.mysql.Driver"; break;
                case ORACLE: driver = "oracle.jdbc.driver.OracleDriver"; break;
                case H2: driver = "org.h2.Driver"; break;
                case HSQLDB: driver = "org.hsqldb.jdbcDriver"; break;
                case CUBRID: driver = "cubrid.jdbc.driver.CUBRIDDriver"; break;
                case DERBY: driver = "org.apache.derby.jdbc.ClientDriver"; break;
                case FIREBIRD: driver = "org.firebirdsql.jdbc.FBDriver"; break;
                case MARIADB: driver = "org.mariadb.jdbc.Driver"; break;
                case POSTGRES: driver = "org.postgresql.Driver"; break;
                case SQLITE: driver = "org.sqlite.JDBC"; break;
                default: driver = "java.sql.Driver"; break;
            }
            return driver;
        }
    }

    public enum DBConnector{MYSQL(0),H2(1),ORACLE(2),HSQLDB(3);

        private final Integer value;
        DBConnector(Integer value) {
            this.value = value;
        }

        public String getConnector(){
            return toString();
        }

        @Override
        public String toString() {
            String driver ="";
            switch (this) {
                case MYSQL: driver = "jdbc:mysql://"; break;
                case HSQLDB: driver = "jdbc:hsqldb:hsql://"; break;
                case H2: driver = "jdbc:h2:tcp://"; break;
                case ORACLE: driver = "jdbc:oracle:thin:@"; break;
            }
            return driver;
        }
    }
}
