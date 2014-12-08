package NI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends AbstractReceiver {

	
	public TCPServer(){
		this.setPortEcoute(6789);
	}
	
	
	/*
	 * Redéfinition de la méthode d'écoute des paquets UDP sur le réseau
	 */
	public void listen(){
		
		ServerSocket server;
		
		while (this.isListening()){
		try {
			server = new ServerSocket(this.getPortEcoute());
			System.out.println("Waiting for connection ...");
			Socket listenSocket = server.accept();		
			System.out.println("Recepting connection");
			// On crée un lien étroit entre un InputStream et un BufferedReader
			InputStream in = listenSocket.getInputStream();
			InputStreamReader inReader = new InputStreamReader(in);
			BufferedReader inBuffer = new BufferedReader(inReader);
			
			// PARTIE A MODIFIER
			String text,result ="";
			while ((text = inBuffer.readLine()) != null)
			{
				result += text;
			}
			System.out.println("Result : " + result);
			
			
			
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}
	
}
