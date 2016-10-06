package application.managers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ManagerVentasAgencia extends HttpServlet{

	/*public static void main(String[] args) {
		
		System.out.println("Iniciando...");
        
		new ManagerVentasAgencia().startServer();
	}*/
	
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		
		System.out.println("Iniciando...");
		new ManagerVentasAgencia().startServer();
	}
	
	private void startServer() {
        
		final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);
        
		Runnable serverTask = new Runnable() {
            
			public void run() {
				ServerSocket serverSocket = null;
				Socket clientSocket = null;
                try {
                    
                	System.out.println("Por abrir socket en puerto 16000");
                	serverSocket = new ServerSocket(16000);
                	System.out.println("Socket en puerto 16000 creado");
                    
                	System.out.println("Waiting for clients to connect...");
                    
                	while (true) {
                        
                		clientSocket = serverSocket.accept();
                        
                		clientProcessingPool.submit(new ClientTask(clientSocket));
                    
                	}
                
                } catch (IOException e) {
                	try {
                		clientSocket.close();
						serverSocket.close();
						System.out.println("Sockets Cerrados");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
					}
                    System.err.println("Unable to process client request");
                    e.printStackTrace();
                }
            }
        };
        
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    
	};
	
}
