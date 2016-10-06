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

import webserviceimm.DatatypeVenta;
import webserviceimm.WebserviceTicketsIMM;
import webserviceimm.WebserviceTicketsIMMService;
import application.beans.TicketBean;
import application.beans.UsuarioBean;
import application.beans.TicketBean.Estado;


public class ClientTask implements Runnable {
	private UsuarioBean usuarioBean;
	private ManagerUsuarios managerUsuarios;
	private DatatypeVenta datatypeVenta;
	private TicketBean  ticketBean;
	private ManagerTickets  managerTickets;
	private Integer idTerminal_int;
	
    private String dateFormat = "dd-MM-yyyy HH:mm";
   
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    private GregorianCalendar gregorianCalendar = new GregorianCalendar();
    
    private long idAgencia = 1;
	private long importe = 0;
    private long idTransaccion = 0;
	
    private final Socket clientSocket;

   public ClientTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Terminal conectada...");

        // Do whatever required to process the client's request
        try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String code_401 = "401";// Authentication is required and has failed or has not yet been provided
			boolean AA_OK = false; // Usuario Autenticado y Autorizado
			String causa_fallo_AA = "AAR"; // Autenticado y Autrorizado Requerido
			while (!AA_OK){
				out.print(code_401 + " " + causa_fallo_AA + "\r\n");
				out.flush();
				String nombre = in.readLine();
				String password = in.readLine();
				String idTerminal = in.readLine();
				
	            usuarioBean = new UsuarioBean();
	            usuarioBean.setNombre(nombre);
	            usuarioBean.setPassword(password);
	            usuarioBean.setIdTerminal(Integer.parseInt(idTerminal));
	            System.out.println("Id de Terminal: "+ idTerminal);
	            idTerminal_int = Integer.parseInt(idTerminal); 
	            managerUsuarios = new ManagerUsuarios();
				
				if (managerUsuarios.validarUsuario(usuarioBean)){
					// Autenticado OK
					// Falta diferenciar problema Autenticacion de Autorizacion
					AA_OK=true;
				}else{
					causa_fallo_AA="UPI"; // Usuario Password Incorrecto
				}
			}
			// AA OK
			System.out.println("Terminal Autenticada y Autorizada...");
			String code_200 = "200";
			out.print(code_200 + "\r\n");
			out.flush();
	        
			// Se recibe accion
			String orden = "0 0 0 0 0";
			String[] componentes = orden.split(" ");
			String matricula;
			String minutos;
			String fecha_inicio;
			String hora_inicio;
			String code_500 = "500";
		
			while(!componentes[0].equals("3")){
				orden = in.readLine();
				System.out.println("Recibida orden: " + orden);
				componentes = orden.split(" ");
				switch (componentes[0]){
				case "1":
					
					matricula = componentes[1];
					minutos = componentes[2];
					fecha_inicio= componentes[3];
					hora_inicio=componentes[4];
					
					System.out.println("Venta de Ticket:");
					System.out.println("Matricula: " + matricula);
					System.out.println("Minutos: " + minutos);
					System.out.println("Fecha Inicio: " + fecha_inicio);
					System.out.println("Hora Inicio: " + hora_inicio);
						
					if (ventaTicket(matricula, minutos, fecha_inicio, hora_inicio)){
						// Venta Exitosa
//						out.print(code_200 + "\r\n");
//						out.flush();
						String str_fechaHoraVenta = simpleDateFormat.format(ticketBean.getFechaHoraVenta()).replace(" ", "_");
						out.print(code_200 + " " + 
								  ticketBean.getNroTicket() + " " + 
								  ticketBean.getImporte() + " " +
								  minutos  + " " +
								  matricula  + " " +
								  fecha_inicio+"_"+hora_inicio + " " +
								  ticketBean.getIdTerminal() + " " +
								  str_fechaHoraVenta + " " +
								  "\r\n");
						out.flush();
						System.out.println("Venta Exitosa Informada a Terminal");
					}else{
						// Venta No Exitosa
						out.print(code_500+ "\r\n");
						out.flush();
						System.out.println("Venta No Exitosa Informada a Terminal");
					}
					break;
				case "2":
					System.out.println("Ingreso de cancelacion:");
					System.out.println("Matricula: " + componentes[1]);
					System.out.println("Minutos: " + componentes[2]);
					break;
				case "3":
					break;
				}
			}
			System.out.println("Terminal desconectada...");
	        out.close();
	        in.close();
	        
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	private boolean ventaTicket(String matricula, String minutos, String fecha_inicio, String hora_inicio){
		boolean venta_OK = false;
		try{
			                       
            String str_fechaInicioServicio = fecha_inicio + " " + hora_inicio;
            /*FIN str_fechaInicioServicio*/
            datatypeVenta = new DatatypeVenta();
            System.out.println("Fecha Inicio Servicio: "+ str_fechaInicioServicio);
            
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
	        datatypeVenta.setSecretoAgencia("secreto");
	           
	        /*INI Llamada a ws Imm*/
	        DatatypeVenta datatypeVentaResponse = AltaVentaIMM(datatypeVenta);
	        // Hay que contemplar caso de respuesta con ERROR
	        /*FIN Llamada a ws Imm*/
	            
	        /*INI Armo el ticket*/
	        this.idTransaccion = datatypeVentaResponse.getNroTicket();
	        this.importe = datatypeVentaResponse.getImporte();
	            
	        ticketBean = new TicketBean();
	        ticketBean.setNroTicket(this.idTransaccion);
	        ticketBean.setEstado(Estado.ACTIVO);
	        ticketBean.setFechaHoraVenta(fechaVenta.toGregorianCalendar().getTime());
	        ticketBean.setFechaInicioServicio(fechaInicioServicio.toGregorianCalendar().getTime());
	        ticketBean.setIdTerminal(idTerminal_int);
	        System.out.println("Id de Terminal en el ticket: " + idTerminal_int);
	        ticketBean.setMatricula(matricula);
	        ticketBean.setImporte(this.importe);
	        /*FIN Armo el ticket*/
	            
	        /*INI Alta de ticket en BD*/
	        managerTickets = new ManagerTickets();
	        venta_OK=managerTickets.altaTicket(ticketBean);
	        /*FIN Alta de ticket en BD*/
	    }
        catch (ParseException e) {e.printStackTrace();}
        catch (DatatypeConfigurationException e) {e.printStackTrace();}
        return venta_OK;
	}
		
		
	private DatatypeVenta AltaVentaIMM(DatatypeVenta datatypeVenta){
		WebserviceTicketsIMMService wsTicketImmService = new WebserviceTicketsIMMService();
		WebserviceTicketsIMM wsTicketImm = wsTicketImmService.getWebserviceTicketsIMMPort();
		DatatypeVenta datatypeVentaResponse = wsTicketImm.venta(datatypeVenta);
		
		return datatypeVentaResponse;
	}
}
