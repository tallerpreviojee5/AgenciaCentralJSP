package application.beans;

import java.util.Date;

public class TicketBean {
	
	private long nroTicket;
	private String matricula;
	private Date fechaHoraVenta;
	private Date fechaInicioServicio;
	private long importe;
	private long idTerminal;
	
	public long getIdTerminal() {
		return idTerminal;
	}

	public void setIdTerminal(long idTerminal) {
		this.idTerminal = idTerminal;
	}

	public Date getFechaInicioServicio() {
		return  fechaInicioServicio;
	}

	public void setFechaInicioServicio(Date fechaInicioServicio) {
		this.fechaInicioServicio = fechaInicioServicio;
	}

	public enum Estado {ACTIVO,CANCELADO,FINALIZADO};
	private Estado estado;
	
	public TicketBean(){}
	
	public long getNroTicket() {
		return nroTicket;
	}

	public void setNroTicket(long nroTicket) {
		this.nroTicket = nroTicket;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getFechaHoraVenta() {
		return fechaHoraVenta;
	}

	public void setFechaHoraVenta(Date fechaVenta) {
		this.fechaHoraVenta = fechaVenta;
	}

	public long getImporte() {
		return importe;
	}

	public void setImporte(long importe) {
		this.importe = importe;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
