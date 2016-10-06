package application.managers;

import application.beans.UsuarioBean;
import application.persistencia.PersistenciaUsuarios;

public class ManagerUsuarios {
	
	private PersistenciaUsuarios persistenciaUsuarios = new PersistenciaUsuarios();
	
	public boolean validarUsuario(UsuarioBean usuarioBean){
		
		boolean resultado = false;
		
		try {
			if(persistenciaUsuarios.validar_usuario_BD(usuarioBean)){
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return resultado;
	}
	

}
