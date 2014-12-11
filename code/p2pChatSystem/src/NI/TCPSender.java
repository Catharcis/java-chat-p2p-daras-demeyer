package NI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import Signals.AbstractMessage;
import Signals.FileMessage;

public class TCPSender extends AbstractSender {

	private static TCPSender singleton;
	
	
	
	private TCPSender() {
		this.setPortEnvoi(5003) ;
		this.setPortEcoute(6789) ;
	}
	
	
	public static TCPSender getInstanceTCPSender(){
		if (singleton == null){
			singleton = new TCPSender();
		}
		return singleton;
	}
	
	/*
	 * Méthode permettant d'envoyer un message à une liste d'utilisateurs
	 */
	public void sendFile(AbstractMessage message, ArrayList<String> listOfUsers, ArrayList<InetAddress> ipAddressesList, File file) {
		
		Socket client;
		
		for (int i = 0; i < ipAddressesList.size(); i++){
		
			try {
				client = new Socket(InetAddress.getByName(ipAddressesList.get(i).getHostAddress()),this.getPortEcoute());
				System.out.println("TCP SENDER - Socket connected !");
				// envoi du FileMessage
				OutputStream outMessage = client.getOutputStream() ;
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream() ;
				ObjectOutputStream objectOut = new ObjectOutputStream (byteOut);		
				objectOut.writeObject(message) ;
				outMessage.write(byteOut.toByteArray()) ;
				System.out.println("TCP SENDER - FileMessage Send") ;
				
				
				// On envoie ensuite le fichier
				FileInputStream filein = new FileInputStream (file) ;
				OutputStream outFile = client.getOutputStream();
				byte[] buf = new byte[(int)((FileMessage)message).getFileSize()] ;
				filein.read(buf) ;
				outFile.write(buf) ;
				filein.close() ;
				System.out.println("TCP SENDER - File Sent") ;
				
				System.out.println("TCP SENDER - Client closed ");
				client.close();
			} catch (IOException e) {
				System.out.println("TCP SENDER - IOEXCEPTION ERROR DURING SENDING") ;
			}
		}
	}
	
}
