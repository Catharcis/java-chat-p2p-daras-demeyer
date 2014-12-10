package NI;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Signals.AbstractMessage;
import Signals.typeContenu;

public class TCPServer extends AbstractReceiver implements Runnable {

	private static TCPServer singleton;
	
	private static NIControler NiCon ;
	
	private TCPServer(){
		this.setPortEcoute(6789);
	}
	
	
	public static TCPServer getInstanceTCPServer(){
		if (singleton == null){
			singleton = new TCPServer();
		}
		return singleton;
	}
	
	public void setNiCon (NIControler NiCont) {
		TCPServer.NiCon = NIControler.getInstance() ;
	}
	
	/*
	 * Redéfinition de la méthode d'écoute des paquets UDP sur le réseau
	 */
	public void listen(){
		
		ServerSocket server = null ;
		
		try {
			server = new ServerSocket(this.getPortEcoute());
			System.out.println("Waiting for connection ...");
			
			//while (this.isListening()) {
			try {
				Socket receiveSocket = server.accept();
				System.out.println("Recepting connection");
				
				// Reception du FileMessage
				InputStream inMessage = receiveSocket.getInputStream() ;
				byte[] bMessage = null ;
				inMessage.read(bMessage) ;
				ByteArrayInputStream byteIn = new ByteArrayInputStream(bMessage) ;
				ObjectInputStream objectIn = new ObjectInputStream (byteIn);
				AbstractMessage fileMessage = (AbstractMessage) objectIn.readObject() ;
				
				String myUsername = NiCon.getNetInfo().getLocalUser().getNickname()+"@"+NiCon.getNetInfo().getLocalIPAddress();
				
				if (!(fileMessage.getNickname().equals(myUsername))){
					if (fileMessage.getTypeContenu() == typeContenu.FILEMESSAGE) {
						InputStream inFile = receiveSocket.getInputStream() ;
						byte[] bFile = null ;
						inFile.read(bFile) ;
						ByteArrayInputStream byteFileIn = new ByteArrayInputStream(bFile) ;
						ObjectInputStream objectFileIn = new ObjectInputStream (byteFileIn);						
						File file = (File)objectFileIn.readObject() ;
						
						
						ArrayList<String> list = fileMessage.getDest();
						if (list.remove(myUsername)){
							System.out.println("UDPReceiver - TextMessage - Remove myUsername success : "+myUsername);

						}
						else{
							System.out.println("UDPReceiver - TextMessage - [ERROR] This message was not for us");
						}
						
						// On ajoute l'utilisateur qui nous a envoye le message
						list.add(textMessage.getNickname());
						System.out.println("UDPReceiver - TextMessage - Source :"+list.get(0));
						
						// On envoie les parametres au NIControler
						
						
						String name = fileMessage.getNickname();
						System.out.println("UDPReceiver : Received File from " +name) ;
						NiCon.receivedFileMessage(file, packet.getAddress()) ;
					}
				}
					
				
				
				server.close();
			} catch (IOException e) {
				System.out.println("TCPSERVER ERROR : IOEXCEPTION DURING RECEIVING") ;
			}
		//	}
			catch (ClassNotFoundException e) {
				System.out.println("TCPSERVER ERROR : CLASS NOT FOUND EXCEPTION") ;
			}
			
		} catch (IOException e1) {
			System.out.println("TCPSERVER ERROR : IOEXCEPTION DURING SERVERSOCKET CREATION") ;
		}
		

		
	}
	
	public void run(){
		listen();
	}
	
}
