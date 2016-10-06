package application.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import application.beans.TicketBean;
import application.beans.TicketBean.Estado;
import application.beans.UsuarioBean;
import application.managers.ManagerUsuarios;
import webserviceimm.DatatypeVenta;
import webserviceimm.WebserviceTicketsIMM;
import webserviceimm.WebserviceTicketsIMMService;

public class ManagerSockets implements Runnable{
	
	private final Socket  clientSocket;
	private DatatypeVenta datatypeVenta;
	
	private UsuarioBean usuarioBean;
	private TicketBean  ticketBean;  
	
	private ManagerUsuarios managerUsuarios;
	private ManagerTickets  managerTickets;
	
	private long idAgencia = 1;
	private long importe = 0;
    private long idTransaccion = 0;
	
    private String dateFormat = "yyyy-MM-dd'T'HH:mm";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    private GregorianCalendar gregorianCalendar = new GregorianCalendar();
    
    public ManagerSockets(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
	
	public void run(){
		
		PrintWriter out = null;
        BufferedReader in = null;

		try{
			//TODO Cerrar aplicación
			while(true){
				try{
					out = new PrintWriter(clientSocket.getOutputStream(), true);
		            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
		            boolean valido = login(in, out);
					
		            if(valido){
						boolean seguir = true;
						while(seguir){
							seguir = menu(in, out);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				finally
				{
					try{
						if(out != null){
							out.close();
						}
						if(in != null){
							in.close();
						}
					}catch(IOException e){
						e.printStackTrace();
					}
				}
    		}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			try{
				if(clientSocket != null){
					clientSocket.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	private boolean login(BufferedReader in, PrintWriter out){
		try {
            String welcome = "Bienvenido!";
            out.print(welcome + "\r\n");

            String prompt_nombre = "Ingrese Nombre de Usuario: ";
            out.print(prompt_nombre);
            out.flush();
            String nombre = in.readLine();

            String prompt_password = "Ingrese Password: ";
            out.print(prompt_password);
            out.flush();
            String password = in.readLine();
            
            String prompt_idTerminal = "Ingrese Id de Terminal: ";
            out.print(prompt_idTerminal);
            out.flush();
            String idTerminal = in.readLine();
                        
            usuarioBean = new UsuarioBean();
            
            usuarioBean.setNombre(nombre);
            usuarioBean.setPassword(password);
            usuarioBean.setIdTerminal(Integer.parseInt(idTerminal));
            
            managerUsuarios = new ManagerUsuarios();
            
            if(managerUsuarios.validarUsuario(usuarioBean)){
            	return true;
            }else{
            	//TODO Mostrar error y redibujar
            }
            
		}catch (IOException e1) {
        	e1.printStackTrace();
        }

		return false;

	}
	
	private boolean menu(BufferedReader in, PrintWriter out){
		boolean seguir = false;
		try
		{
			String menu = "Menu";
			out.print(menu + "\r\n\r\n");
			
			String opcion1 = "1) Venta de ticket";
			String opcion2 = "2) Cancelar ticket";
			String opcion3 = "3) Salir";
			String seleccion = "Ingrese el numero de la operacion a realizar: ";
			
			out.print(opcion1 + "\r\n" + opcion2 + "\r\n" + opcion3 + "\r\n\r\n" + seleccion);
			out.flush();
			
	        String opcion = in.readLine();
	    	
	    	switch(opcion){
	        	case "1":
	        		seguir = ventaTicket(out,in);
	        		break;
	        	
	        	case "2":
	        		break;
	        	
	        	case "3":
	                seguir = false;
	        	
	        	default:
	        		break;
	    	}
		
        }catch (IOException e){
			e.printStackTrace();
		}
		return seguir;
	}
	
	private boolean ventaTicket(PrintWriter out, BufferedReader in){
		boolean seguir = false;
		try{
			String titulo = "Venta de ticket";
			out.print(titulo + "\r\n" + "\r\n");
			
			String prompt_matricula = "Matricula del Vehiculo: ";
            out.print(prompt_matricula);
            out.flush();
            String matricula = in.readLine();
            
            String prompt_minutos = "Cantidad de Minutos: ";
            out.print(prompt_minutos);
            out.flush();
            String minutos = in.readLine();
			
            /*INI str_fechaInicioServicio*/
            String prompt_fecha_inicio = "Fecha de inicio de servicio (dd/mm/aaaa): ";
            out.print(prompt_fecha_inicio);
            out.flush();
            String fecha_inicio = in.readLine();
            
            String prompt_hora_inicio = "Hora de inicio del servicio (HH:mm): ";
            out.print(prompt_hora_inicio);
            out.flush();
            String hora_inicio = in.readLine();
                        
            String str_fechaInicioServicio = fecha_inicio + " " + hora_inicio;
            /*FIN str_fechaInicioServicio*/
        
            datatypeVenta = new DatatypeVenta();
            
            try {
				gregorianCalendar.setTime(new SimpleDateFormat(dateFormat).parse(str_fechaInicioServicio));
			    XMLGregorianCalendar fechaInicioServicio = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
	            datatypeVenta.setFechaInicioServicio(fechaInicioServicio);
	            
	            Date date_fechaVenta = new Date();
	         
	            String str_fechaVenta = simpleDateFormat.format(date_fechaVenta);
	            gregorianCalendar.setTime(simpleDateFormat.parse(str_fechaVenta));
	            XMLGregorianCalendar fechaVenta = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
				datatypeVenta.setFechaVenta(fechaVenta);
            
	            datatypeVenta.setIdAgencia(this.idAgencia);
	            datatypeVenta.setNroTicket(this.idTransaccion);
	            datatypeVenta.setImporte(this.importe);
	            datatypeVenta.setMatriculaAuto(matricula);
	            datatypeVenta.setMinutos(Integer.parseInt(minutos));
	            
	            /*INI Llamada a ws Imm*/
	            DatatypeVenta datatypeVentaResponse = AltaVentaIMM(datatypeVenta);
	            /*FIN Llamada a ws Imm*/
	            
	            /*INI Armo el ticket*/
	            this.idTransaccion = datatypeVentaResponse.getNroTicket();
	            this.importe = datatypeVentaResponse.getImporte();
	            
	            ticketBean = new TicketBean();
	            ticketBean.setNroTicket(this.idTransaccion);
	            ticketBean.setEstado(Estado.ACTIVO);
	            ticketBean.setFechaHoraVenta(fechaVenta.toGregorianCalendar().getTime());
	            ticketBean.setIdTerminal(usuarioBean.getIdTerminal());
	            ticketBean.setMatricula(matricula);
	            ticketBean.setImporte(this.importe);
	            
	            /*FIN Armo el ticket*/
	            
	            /*INI Alta de ticket en BD*/
	            managerTickets = new ManagerTickets();
	            
	            if(managerTickets.altaTicket(ticketBean)){
	            	/*Si el alta en bd es ok respondo a la terminal nroTicket e importe*/
	            	String prompt_aviso = "Datos de la venta: \r\n\r\n";
	            	String prompt_numero_ticket = "Numero de Ticket: " + ticketBean.getNroTicket() + "\r\n";
	                String prompt_importe = "Importe final: $ " + ticketBean.getImporte() + "\r\n";
	            	String prompt_fecha_hora_inicio = "Fecha/Hora de inicio del servicio: " + str_fechaInicioServicio + "\r\n";
	            	String prompt_minutos2 = "Minutos: " + minutos +	"\r\n\r\n";
	            	String prompt_salir = "Presione 1 para salir...";
	            	
	            	String prompt_salida = prompt_aviso + 
	            				           prompt_numero_ticket + 
	            				           prompt_importe + 
	            				           prompt_fecha_hora_inicio +
	            				           prompt_minutos2 +
	            				           prompt_salir;
	                
	            	out.print(prompt_salida);
	                out.flush();
	                
	                String seleccion = in.readLine();
	                
	                switch(seleccion){
		        		case "1":
		        			seguir = true;
		        			break;
		        		default:
		        			break;
	                }
	                
	            }else{
	            	/*Si falla el alta en la bd respondo a la terminal error*/
	            	//TODO Respuesta de error a la terminal 
	            }
	            /*FIN Alta de ticket en BD*/
	            
	        } catch (ParseException e) {
				e.printStackTrace();
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
            
            
        }catch (IOException e){
		   	e.printStackTrace();
		}

		return seguir;
		
	}
	
	private DatatypeVenta AltaVentaIMM(DatatypeVenta datatypeVenta){
		WebserviceTicketsIMMService wsTicketImmService = new WebserviceTicketsIMMService();
		WebserviceTicketsIMM wsTicketImm = wsTicketImmService.getWebserviceTicketsIMMPort();
		DatatypeVenta datatypeVentaResponse = wsTicketImm.venta(datatypeVenta);
		
		return datatypeVentaResponse;
	}

}
