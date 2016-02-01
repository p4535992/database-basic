package datasource.datastore.database.queries;

import java.io.File;
import java.util.Map;

/**
 * Created by 4535992 on 01/02/2016.
 */
public interface Query {
    Map<String, String> loadQueriesFromPropertiesFile(File queriesProperties);
}
