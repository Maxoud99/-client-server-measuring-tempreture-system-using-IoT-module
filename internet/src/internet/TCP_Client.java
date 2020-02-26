package internet;
import java.io.*; 
import java.net.*; 
class TCP_Client { 

	public static void main(String argv[]) throws Exception 
	{ 
		String sentence; 
		String modifiedSentence; 

		BufferedReader inFromUser = 
				new BufferedReader(new InputStreamReader(System.in)); 

		Socket clientSocket = new Socket("localhost", 1234); 

		DataOutputStream outToServer = 
				new DataOutputStream(clientSocket.getOutputStream()); 
		BufferedReader inFromServer = 
				new BufferedReader(new
						InputStreamReader(clientSocket.getInputStream())); 

		while(true){
			System.out.println("Please, Enter your message.");
			sentence = inFromUser.readLine(); 

			outToServer.writeBytes(sentence + '\n'); 

			modifiedSentence = inFromServer.readLine(); 

			System.out.println("FROM SERVER: " + modifiedSentence); 
			System.out.println("================================");
			
			if(sentence.equalsIgnoreCase("Close Socket"))
			{
				clientSocket.close(); 
				System.out.println("ClientSocket is closed @Client");
				break;
			}
		}
	} 
} 



