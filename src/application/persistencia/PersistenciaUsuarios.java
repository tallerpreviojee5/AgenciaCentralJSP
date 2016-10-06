package application.persistencia;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.beans.UsuarioBean;
import application.managers.ManagerPersistencia;


/*public class PersistenciaUsuarios {
	
		
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/usuarios_terminales";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	private ResultSet rs;
 	
	public boolean validar_usuario_BD(UsuarioBean usuarioBean) throws Exception{
		
		boolean resultado = false;
		
		String nombre   = usuarioBean.getNombre();
		String password = usuarioBean.getPassword();
		long idTerminal  = usuarioBean.getIdTerminal();
		
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = con.createStatement();
					
			String Q = "SELECT * FROM usuarios WHERE nombre='" + nombre +
					"' AND password='" + password +
					"' AND idTerminal='" + idTerminal + "'";
			
			System.out.println("Ejecutando consulta a DB: " + Q);
			rs = stmt.executeQuery(Q);
			rs.next();
			String nombre_DB = rs.getString("nombre");
			String password_DB = rs.getString("password");
			System.out.println("Nombre en Base: "+nombre_DB);
			System.out.println("Password en Base: "+password_DB);
			
			long idTerminal_DB = rs.getLong("idTerminal");
			
			if (nombre.equals(nombre_DB)&&password.equals(password_DB)){
				// Autenticado OK
				if(idTerminal==idTerminal_DB){
					// Autenticado y Autorizado
					resultado = true;
				}else{
					// Falla de Autorizacion
					resultado = false;
				}
				
			}else{
				// Falla de Autenticacion
				resultado = false;
			}
			stmt.close();
			con.close();
		}
		 catch(SQLException e) {
			 System.out.println("SQLException: "+e.getMessage());
			 }
		catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: "+e.getMessage());
			}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
			}
		return resultado;
	}

}*/

public class PersistenciaUsuarios {
	
	private ManagerPersistencia managerPersistencia = ManagerPersistencia.getPersistencia();
	private Connection connection = managerPersistencia.conexion_usuarios_terminales();
	
	private PreparedStatement pstmt;
	private ResultSet rs;
 	
	public boolean validar_usuario_BD(UsuarioBean usuarioBean) throws Exception{
		
		boolean resultado = false;
		
		String nombre   = usuarioBean.getNombre();
		String password = usuarioBean.getPassword();
		long idTerminal  = usuarioBean.getIdTerminal();
		
		try {
			
			String query = " SELECT * FROM usuarios " +
						   " WHERE nombre = ? " +
						   " AND password = ? " +
						   " AND idTerminal = ?";
			
			pstmt = connection.prepareStatement(query);
				
			pstmt.setString(1,nombre);
			pstmt.setString(2,password);
			pstmt.setLong(3,idTerminal);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				resultado = true; 
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw (new Exception());
		}
		
		finally {
			try {
				
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(connection != null){
					connection.close();
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
				throw (new Exception());
			}
		}
		
		return resultado;
	}

}
