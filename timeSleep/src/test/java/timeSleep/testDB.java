package timeSleep;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class testDB {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testConnection() {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test", "test")) {
			log.info(con);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
