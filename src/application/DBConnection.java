package application;

import java.util.List;

import db.interfaces.DBManager;
import db.interfaces.PacientManager;
import db.sqlite.SQLiteManager;
import pojos.Pacient;

public class DBConnection {

	private static DBManager dbManager;
	private static PacientManager pacientManager;

	public void addPacient (Pacient pacient){

		dbManager = new SQLiteManager();
		dbManager.connect();
		//dbManager.createTables();
		pacientManager = dbManager.getPacientManager();
		pacientManager.add(pacient);
		dbManager.disconnect();

	}

	public List<Pacient> searchPacientByName (String name){

		dbManager = new SQLiteManager();
		dbManager.connect();
		pacientManager = dbManager.getPacientManager();
		return pacientManager.searchByName(name) ;

	}

}
