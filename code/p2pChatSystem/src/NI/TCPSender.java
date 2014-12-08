package NI;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import Controler.User;
import Signals.AbstractMessage;

public class TCPSender extends AbstractSender {

	/*
	 * Méthode permettant d'envoyer un message en broadcast
	 */
	public void sendBroadcast(AbstractMessage message){}
	
	/*
	 * Méthode permettant d'envoyer un message à une liste d'utilisateurs
	 */
	public void send(AbstractMessage message, ArrayList<String> listOfUsers, ArrayList<InetAddress> ipAddressesList){
		
		Socket client;
		
		for (int i = 0; i < ipAddressesList.size(); i++){
		
			try {
				client = new Socket(InetAddress.getByName(ipAddressesList.get(i).toString()),6789);
				System.out.println("CONNECTED !");
				OutputStream out = client.getOutputStream();
				OutputStreamWriter outWriter = new OutputStreamWriter(out);
				BufferedWriter outBuffer = new BufferedWriter(outWriter);
				
				// PARTIE A MODIFIER
				int j = 0;
				String text = "Test d'envoi";
				do{
					outBuffer.write(text);
					outBuffer.flush();
					i++;
				}while (j!=5);
				
				
				System.out.println("Client closed "+System.currentTimeMillis());
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
