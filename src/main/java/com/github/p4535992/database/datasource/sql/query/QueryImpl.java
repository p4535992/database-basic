package com.github.p4535992.database.datasource.sql.query;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.util.AbstractDatabase;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Created by 4535992 on 02/02/2016.
 */
public class QueryImpl<T> extends AbstractQuery<T>{


    @Override
    public String getDatabaseName() {
        return null;
    }

    @Override
    public String getDriverClassName() {
        return null;
    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return null;
    }

    @Override
    public DataSource getDataSource() {
        return null;
    }

    @Override
    public AbstractDatabase getJOOQDatabase(Connection conn) {
        return null;
    }

    @Override
    public DSLContext getDSLContext() {
        return null;
    }

    @Override
    public SQLDialect getSQLDialect() {
        return null;
    }
}
