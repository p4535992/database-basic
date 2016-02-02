package com.github.p4535992.database.datasource.database;

import com.github.p4535992.database.datasource.DataSourceFactory;
import com.github.p4535992.database.datasource.database.AbstractDatabase;
import com.github.p4535992.database.datasource.sql.SQLEnum;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.util.mysql.MySQLDatabase;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class MySqlDatabase extends AbstractDatabase<MySqlDatabase> {

    public MySqlDatabase(Connection connection){
        super.dataSource = DataSourceFactory.createDataSource(connection);
        super.connection = connection;
        setNewJdbcTemplate();
        setNewDSLContext(super.connection,SQLDialect.MYSQL);
        jooqUtilities.setDslContext(dslContext);
        jooqUtilities.setSqlDialect(sqlDialect);
    }

    public MySqlDatabase(DataSource dataSource){
        this.dataSource = dataSource;
        try {
            super.connection = dataSource.getConnection();
        } catch (SQLException e) {
            super.connection = null;
        }
        setNewJdbcTemplate();
        setNewDSLContext(connection, SQLDialect.MYSQL);
        jooqUtilities.setDslContext(dslContext);
        jooqUtilities.setSqlDialect(sqlDialect);
    }

    public MySqlDatabase(String host, String port, String user, String pass, String database) {
        //super.setDriverManager(SQLEnum.DBDriver.MYSQL, SQLEnum.DBConnector.MYSQL,host,port,user,pass,database);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public String getDatabaseName() {
        return SQLEnum.DBType.HSQLDB.name();
    }

    @Override
    public String getDriverClassName() {
        return SQLEnum.DBDriver.MYSQL.toString();
    }

    @Override
    public MySQLDatabase getJOOQDatabase(Connection conn){
        MySQLDatabase mySQLDatabase = new MySQLDatabase();
        mySQLDatabase.setConnection(conn);
        mySQLDatabase.create();
        return mySQLDatabase;
    }

    @Override
    public DSLContext getDSLContext() {
        return dslContext;
    }

    @Override
    public SQLDialect getSQLDialect() {
        return SQLDialect.MYSQL;
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
