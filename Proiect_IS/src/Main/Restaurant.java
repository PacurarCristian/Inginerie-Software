package Main;
import java.sql.SQLException;

import UI.LoginUI;

public class Restaurant {

	public static void main(String[] args) throws SQLException {
		DBConnect DBConnect = new DBConnect();
		DBConnect.connect();
		
		Sistem sistem = new Sistem(true);
		
		LoginUI LoginUI = new LoginUI(DBConnect, sistem);
		LoginUI.afisare();
	}

}
