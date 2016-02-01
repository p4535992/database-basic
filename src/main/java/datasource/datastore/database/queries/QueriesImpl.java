package datasource.datastore.database.queries;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class QueriesImpl implements Query{

	public QueriesImpl() {}

	@Override
	public  Map<String, String> loadQueriesFromPropertiesFile(File queriesProperties) {
		Properties properties = new Properties();
		Map<String, String> queries = new TreeMap<>();
		try {
			properties.load(new FileInputStream(queriesProperties));
		} catch (IOException e) {
			throw new IllegalArgumentException("Error loading query properties from: " + queriesProperties, e);
		}
		for (Object key : properties.keySet()) {
			String property = String.valueOf(key).trim();
			String queryName = null;
			String query = null;
			String propertyName;
			int dotIndex = property.indexOf('.');
			if (dotIndex != -1) {
				propertyName = property.substring(0, dotIndex);
			} else {
				throw new IllegalArgumentException("Invalid property in " + queriesProperties + ": " + property);
			}
			if (property.endsWith(".name")) {
				queryName = properties.getProperty(property);
				query = properties.getProperty(propertyName + ".query");
				if (query == null) {
					throw new IllegalArgumentException("No query defined for name " + queryName);
				}
			} else if (property.endsWith(".query")) {
				query = properties.getProperty(property);
				queryName = properties.getProperty(propertyName + ".name");
				if (queryName == null) {
					throw new IllegalArgumentException("No name defined for query " + query);
				}
			}
			if (queryName == null || query == null) {
				throw new IllegalArgumentException("Invalid property in " + queriesProperties + ": " + property);
			}
			queries.put(queryName, query);
		}
		return queries;
	}
}
