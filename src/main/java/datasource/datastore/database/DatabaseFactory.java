package datasource.datastore.database;

import datasource.datastore.database.database.HsqlDatabase;
import datasource.datastore.database.database.MySqlDatabase ;
import java.util.*;

/**

 */
public class DatabaseFactory {

	private final Map<String, Class<? extends AbstractDatabase>> databases;
	private final Properties properties = new Properties();

	private static final DatabaseFactory instance = new DatabaseFactory();

	private DatabaseFactory() {
		databases = new TreeMap<>();
		registerDatabase(MySqlDatabase.class);
		registerDatabase(HsqlDatabase.class);
	}

	/**
	 * Returns the {@code DatabaseFactory} instance
	 * @return the {@code DatabaseFactory} instance
	 */
	public static DatabaseFactory getInstance() {
		return instance;
	}

	/**
	 * Creates a new instance of a given implementation of {@link AbstractDatabase}.
	 *
	 * @param databaseClass a class that extends {@link AbstractDatabase}.
	 * @return the {@link AbstractDatabase} instance.
	 */
	private static AbstractDatabase newInstance(Class<? extends AbstractDatabase> databaseClass) {
		try {
			return databaseClass.newInstance();
		} catch (Exception e) {
			throw new IllegalStateException("Cannot create instance of database: " + databaseClass);
		}
	}

	/**
	 * Adds a new {@link AbstractDatabase} implementation to the factory.
	 * @param databaseClass a class that extends {@link AbstractDatabase}.
	 */
	public void registerDatabase(Class<? extends AbstractDatabase> databaseClass) {
		databases.put(newInstance(databaseClass).getDatabaseName().toLowerCase(), databaseClass);
	}

	/**
	 * Returns the names of the available databases you can connect to using this factory class.
	 * @return a set of database names available for use.
	 */
	public Set<String> getAvailableDatabases() {
		return Collections.unmodifiableSet(databases.keySet());
	}


}
