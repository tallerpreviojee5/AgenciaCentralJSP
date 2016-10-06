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

}
