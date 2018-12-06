package jdbc_erp;


import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import jdbc_erp.jdbc.ConnectionProvider;
import jdbc_erp.jdbc.LogUtil;



public class ConnectionProviderTest {

   @BeforeClass
   public static void setUpBeforeClass() throws Exception {
      System.out.println();
      LogUtil.prnLog("Start ConnectionProviderTest");
   }

   @AfterClass
   public static void tearDownAfterClass() throws Exception {
      System.out.println();
      LogUtil.prnLog("End ConnectionProviderTest");
   }

   @Before
   public void setUp() throws Exception {
      System.out.println();
   }

   @Test
   public void testConnection() {
      try (Connection connection = ConnectionProvider.getConnection()) {
         LogUtil.prnLog(connection.toString());
      } catch (SQLException e) {

         e.printStackTrace();
      }
   }

}