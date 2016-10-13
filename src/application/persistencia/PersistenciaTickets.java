package application.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import application.beans.TicketBean;
import application.beans.TicketBean.Estado;
import application.managers.ManagerPersistencia;

public class PersistenciaTickets {
	
	private ManagerPersistencia managerPersistencia = ManagerPersistencia.getPersistencia();
	private Connection connection = null;
	
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	 	
	public boolean alta_ticket_BD(TicketBean ticketBean) throws Exception{
		
		connection = managerPersistencia.conexion_agenciadb();
		
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
	
	public String get_estado_ticket_BD(long nroTicket){
		
		String estado = "";
		String query = "SELECT estado FROM tickets " + 
					   "WHERE nroTicket = ?";
		try{
			connection = managerPersistencia.conexion_agenciadb();
			
			pstmt = connection.prepareStatement(query);
			
			pstmt.setLong(1,nroTicket);
						
			rs = pstmt.executeQuery();
						
			if(rs.next()) {
				estado = rs.getString("estado");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			try{
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if (connection != null) 
					connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
		return estado;
	}
	
	public void cancelar_ticket_BD(long nroTicket){
		
		try{
			
			connection = managerPersistencia.conexion_agenciadb();
			
			String query = "UPDATE tickets " +
				   	   "SET estado = 'CANCELADO' " +
				       "WHERE nroTicket = ?";
	
			pstmt = connection.prepareStatement(query);
			
			pstmt.setLong(1, nroTicket);
		
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			try{
				if(rs != null)
					rs.close();
				if(connection != null) 
					connection.close();
				if(pstmt != null)
					pstmt.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

