package application.managers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*public class ManagerPersistencia {

	//Singleton Manager_Persistencia
	private static ManagerPersistencia managerPersistencia;
	
	private ManagerPersistencia(){}
	
	public static ManagerPersistencia getPersistencia(){
		 
		if(managerPersistencia == null){
		 	 
			managerPersistencia = new ManagerPersistencia();
		 }
		
		 return managerPersistencia;
	}
	
	public Connection establecer_Conexion(){
		
		Connection connection = null;
		
		try {
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup("java:/MySqlDS");
			connection = ds.getConnection();
		}catch (SQLException e) {
			System.out.println("Error al obtener la conexion: " + e.getMessage());
		} catch (NamingException e1) {
			System.out.println("Error de : NamingException" + e1.getMessage());
		}
		
		return connection;
		
	}
}*/

public class ManagerPersistencia {

	//Singleton Manager_Persistencia
	private static ManagerPersistencia managerPersistencia;
	
	private ManagerPersistencia(){}
	
	public static ManagerPersistencia getPersistencia(){
		 
		if(managerPersistencia == null){
		 	 
			managerPersistencia = new ManagerPersistencia();
		 }
		
		 return managerPersistencia;
	}
	
	public Connection conexion_agenciadb(){
		
		Connection connection = null;
		
		try {
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup("java:/agenciadbDS");
			connection = ds.getConnection();
		}catch (SQLException e) {
			System.out.println("Error al obtener la conexion: " + e.getMessage());
		} catch (NamingException e1) {
			System.out.println("Error de : NamingException" + e1.getMessage());
		}
		
		return connection;
		
	}
	
	public Connection conexion_usuarios_terminales(){
		
		Connection connection = null;
		
		try {
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup("java:/usuarios_terminalesDS");
			connection = ds.getConnection();
		}catch (SQLException e) {
			System.out.println("Error al obtener la conexion: " + e.getMessage());
		} catch (NamingException e1) {
			System.out.println("Error de : NamingException" + e1.getMessage());
		}
		
		return connection;
		
	}
}
