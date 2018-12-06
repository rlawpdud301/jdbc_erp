package jdbc_erp.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

public class MyDataSource {

	private static final MyDataSource instance = new MyDataSource();

	public static MyDataSource getInstance() {
		return instance;
	}

	private DataSource dataSource;

	   public DataSource getDataSource() {
	      return dataSource;
	   }

	   public void close() {
	      try {
	         DataSources.destroy(dataSource);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }

	   }
	
	   private MyDataSource() {
		      Properties prop = loadProperties();
		      /*
		       * for(Entry<Object, Object> e: prop.entrySet()) {
		       * System.out.printf("%s -> %s%n", e.getKey(), e.getValue()); }
		       */

		      try {
		         DataSource ds_unpooled = DataSources.unpooledDataSource(prop.getProperty("url"), prop);
		         Map<String, Object> overries = new HashMap<>();
		         overries.put("maxStatements", "200");
		         overries.put("maxPoolSize", new Integer(50));

		         dataSource = DataSources.pooledDataSource(ds_unpooled, overries);
		      } catch (Exception e) {
		         e.printStackTrace();
		      }

		   }

		   private Properties loadProperties() {
		      Properties properties = new Properties();
		      try (InputStream is = ClassLoader.getSystemResourceAsStream("db.properties")) {
		         properties.load(is);
		      } catch (IOException e) {
		         e.printStackTrace();
		      }
		      return properties;
		   }
	
	
	
	
	
}

