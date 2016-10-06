package application.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import application.beans.TicketBean;
import application.beans.TicketBean.Estado;
import application.managers.ManagerPersistencia;

/*public class PersistenciaTickets {
	
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/agenciadb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	

 	
	public boolean alta_ticket_BD(TicketBean ticketBean) throws Exception{
		
		boolean resultado = false;
		
		long nroTicket = ticketBean.getNroTicket();
		String matricula = ticketBean.getMatricula();
		
		Date fechaVenta = ticketBean.getFechaHoraVenta();
		Date fechaInicioServicio = ticketBean.getFechaInicioServicio();
		java.sql.Date sqlFechaVenta = new java.sql.Date(fechaVenta.getTime());
		java.sql.Date sqlFechaInicioServicio = new java.sql.Date(fechaInicioServicio.getTime());
		System.out.println("Fecha Venta: "+ fechaVenta.toString());
		long importe = ticketBean.getImporte();
		long idTerminal = ticketBean.getIdTerminal();
		System.out.println("IdTerminal: " + idTerminal);
		Estado estado = ticketBean.getEstado();
		
		
		try {
			
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = con.createStatement();
			
			String Q = "INSERT INTO tickets (nroTicket,matricula,fechaHoraVenta, fechaInicio, importe, idTerminal,estado) values("+
					"'"+ nroTicket +"',"+
					"'"+ matricula +"',"+
					"'"+ sqlFechaVenta+"',"+
					"'"+ sqlFechaInicioServicio+"',"+
					"'"+ importe+   "',"+
//					"'"+ idAgencia+ "',"+
					"'"+ idTerminal+ "',"+
					"'"+ estado+    "')";
			System.out.println("Guardando Ticket: " +Q);
			int i = stmt.executeUpdate(Q);
			
			stmt.close();
			con.close();
			// Verificar Ingreso a DB OK
			resultado = true;
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

public class PersistenciaTickets {
	
	private ManagerPersistencia managerPersistencia = ManagerPersistencia.getPersistencia();
	private Connection connection = managerPersistencia.conexion_agenciadb();
	
	private PreparedStatement pstmt;
	 	
	public boolean alta_ticket_BD(TicketBean ticketBean) throws Exception{
		
		boolean resultado = false;
		
		long nroTicket = ticketBean.getNroTicket();
		String matricula = ticketBean.getMatricula();
		
		Date fechaHoraVenta = ticketBean.getFechaHoraVenta();
		Object sqlFechaHoraVenta = new java.sql.Timestamp(fechaHoraVenta.getTime());
		
		long importe = ticketBean.getImporte();
		long idTerminal = ticketBean.getIdTerminal();
		Estado estado = ticketBean.getEstado();
		
		String query = "INSERT INTO tickets " + 
		               "(nroTicket,matricula,fechaHoraVenta,importe,idTerminal,estado) " +
				       "values(?,?,?,?,?,?)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setLong(1,nroTicket);
			pstmt.setString(2, matricula);
			pstmt.setObject(3, sqlFechaHoraVenta);
			pstmt.setLong(4, importe);
			pstmt.setLong(5, idTerminal);
			pstmt.setString(6, estado.name());
			
			if(pstmt.executeUpdate() > 0){
				resultado = true;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw (new Exception());
		}
		
		finally{
			
			try{
				if(pstmt != null){	
					pstmt.close();
				}
				if(connection != null){ 
					connection.close();
				}
				
			} catch (SQLException e) {
				System.out.println("Error al liberar los recursos en alta de ticket");
				e.printStackTrace();
				throw (new Exception());
			}
		}
		
		return resultado;
		
	}
}

