package internet;
import java.net.*;
import java.util.LinkedList; 

class TCP_Server{ 
	static LinkedList clients = new LinkedList ();
	
	public static void main(String argv[]) throws Exception 
	{ 
		System.out.println("Server is ON......");
		int ID = 1;
		
		ServerSocket welcomeSocket = new ServerSocket(1234); 

		while(true) { 
			System.out.println("Waiting for a new connection......");
			
			Socket connectionSocket = welcomeSocket.accept(); 
			
			System.out.println("Server is now connected to the Client "+ ID);
			
			//here we need to make a thread
			Client_Thread client_thread = new Client_Thread(connectionSocket,ID);
			clients.add(client_thread);
			ID++;
			client_thread.start();
			System.out.println("Client is added.");
			System.out.println("==================================================");
		} 
	} 
} 




