package application.beans;

public class UsuarioBean {
	
	private String Nombre;
	private String Password;
	private long idTerminal;
	
	public UsuarioBean(){}
	
	public String getNombre() {
		return Nombre;
	}
	
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		Password = password;
	}
	
	public long getIdTerminal() {
		return idTerminal;
	}
	
	public void setIdTerminal(long idTerminal) {
		this.idTerminal = idTerminal;
	}

}
