package internet;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Client_Thread extends Thread {
	Socket connectionSocket;
	int ID;
	String s;
	String sub1;
	String sub2;
	String capitalizedSentence;
	DataOutputStream outToClient;
	BufferedReader inFromClient;
	ArrayList<Integer> avg1 = new ArrayList<Integer>();
	ArrayList<Integer> avg2 = new ArrayList<Integer>();
	int finalav;

	// lazm n3ml constructor 3shan ya5od socket bta3 client da
	public static int average(ArrayList<Integer> x) {
		int result = 0;

		for (int i = 0; i < x.size(); i++) {
			result += x.get(i);
		}
		return result / x.size();
	}

	public Client_Thread(Socket ClientSocket, int ID) {
		connectionSocket = ClientSocket;
		ID = this.ID;
	}

	public void run() {
		
		try {
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		while (true) {
			try {
				inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

			try {
			s = inFromClient.readLine();
				if (s != null) {
					System.out.println(s);
					sub1=s.substring(s.length()-3, s.length());
					sub2=s.substring(0, 13);
					
					if (sub2.equals("I am Sensor 1")) {
						double num = Double.parseDouble(sub1);
						int num2 = (int) num;
						avg1.add(num2);
						finalav = average(avg1);
						outToClient.writeBytes(finalav + "");
					}

					if (s.equals("I am Sensor 2")) {
						double num = Double.parseDouble(inFromClient.readLine());
						int num2 = (int) num;
						avg2.add(num2);
						finalav = average(avg2);
						outToClient.writeBytes(finalav + "");
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}



