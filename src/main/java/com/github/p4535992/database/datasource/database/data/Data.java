package com.github.p4535992.database.datasource.database.data;

import java.util.*;

import com.github.p4535992.database.datasource.database.DatabaseBasic;
import org.springframework.jdbc.support.rowset.*;

public class Data {
	protected final DatabaseBasic database;
	private final String selectScript;

	private final List<Object[]> data;
	private String[] columnNames;

	public Data(DatabaseBasic database, String selectScript) {
		this.database = database;
		this.selectScript = selectScript;

		this.data = new ArrayList<Object[]>();
		this.columnNames = new String[0];
	}

	public void reloadData() {
		data.clear();

		SqlRowSet queryResult = database.getJdbcTemplate().queryForRowSet(selectScript);
		SqlRowSetMetaData metaData = queryResult.getMetaData();
		columnNames = metaData.getColumnNames();

		for (int i = 0; i < columnNames.length; i++) {
			String label = metaData.getColumnLabel(i + 1);
			if (label != null) {
				columnNames[i] = label;
			}
		}

		while (queryResult.next()) {
			Object[] row = new Object[columnNames.length];
			for (int i = 0; i < row.length; i++) {
				row[i] = queryResult.getObject(i + 1);
			}
			data.add(row);
		}

	}

	public List<Object[]> getRows() {
		return data;
	}

	public String[] getColumnNames() {
		return columnNames;
	}
}
