package application.managers;

import application.beans.TicketBean;
import application.persistencia.PersistenciaTickets;

public class ManagerTickets {
	
	private PersistenciaTickets persistenciaTickets = new PersistenciaTickets();
	
	public boolean altaTicket(TicketBean ticketBean){
		boolean resultado = false;
		
		try {
			if(persistenciaTickets.alta_ticket_BD(ticketBean)){
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public String getEstadoTicket(long nroTicket){
		
		String estado = "";
		
		try{
		
			estado = persistenciaTickets.get_estado_ticket_BD(nroTicket);
		
		}catch(Exception e){
			
			e.printStackTrace();
		
		}
		
		return estado;
	}
	
	public void cancelarTicket(long nroTicket){
		try{
			persistenciaTickets.cancelar_ticket_BD(nroTicket);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
