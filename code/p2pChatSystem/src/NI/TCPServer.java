package NI;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Signals.FileMessage;

/**
 * @author Valérie Daras et Alexandre Demeyer
 */

public class TCPServer extends AbstractReceiver implements Runnable {

	/************************************************* 
	 * 				ATTRIBUTS and FIELDS 
	 ************************************************/
	
	/**
	 * Singleton
	 */
	private static TCPServer singleton;

	/**
	 * Pointeur d'objet NIControler
	 */
	private static NIControler NiCon;
	
	/**
	 * Pointeur d'objet ServerSocket
	 */
	private ServerSocket server = null;

	/************************************************* 
	 * 				CONSTRUCTOR 
	 ************************************************/
	
	/**
	 * Constructeur par defaut qui demarre un serveur TCP
	 */
	private TCPServer() {
		this.setPortEcoute(6789);
		try {
			server = new ServerSocket(this.getPortEcoute());
			System.out.println("TCPSERVER - Waiting for connection ...");
		} catch (IOException e) {
			System.out.println("TCPSERVER ERROR DURING CREATION");
		}	
	}
	
	/************************************************* 
	 * 				GETTERS and SETTERS
	 ************************************************/

	/**
	 * Setter de NiCon
	 * @param NiCont : l'objet NIControler
	 */
	public void setNiCon(NIControler NiCont) {
		TCPServer.NiCon = NIControler.getInstance();
	}
	
	/**
	 * Getter de serveur
	 * @return l'objet ServeurSocket
	 */
	public ServerSocket getServerSocket(){
		return server;
	}
	
	/************************************************* 
	 * 					METHODS 
	 ************************************************/
	
	/**
	 * Creer l'instance si elle n'est pas cree ou la recupere
	 * @return l'instance TCPServer
	 */
	public static TCPServer getInstanceTCPServer() {
		if (singleton == null) {
			singleton = new TCPServer();
		}
		return singleton;
	}

	/**
	 * Redefinition de la methode d'ecoute sur le reseau pour la classe TCPServer
	 */
	protected void listen() {
		
			while (this.isListening()) {
				try {
					Socket receiveSocket = server.accept();
					System.out.println("TCPSERVER - Recepting connection");

					// Reception du FileMessage
					InputStream inMessage = receiveSocket.getInputStream();
					byte[] bMessage = new byte[5000];
					inMessage.read(bMessage);
					ByteArrayInputStream byteIn = new ByteArrayInputStream(bMessage);
					ObjectInputStream objectIn = new ObjectInputStream(byteIn);
					FileMessage fileMessage = (FileMessage) objectIn.readObject();

					String myUsername = NiCon.getNetInfo().getLocalUser().getNickname()+ "@" + NiCon.getNetInfo().getLocalIPAddress();

					if (!(fileMessage.getNickname().equals(myUsername))) {
						InputStream inFile = receiveSocket.getInputStream();
						byte[] bFile = new byte[(int) fileMessage.getSize()];
						String current = System.getProperty("user.dir") ;
						System.out.println("CURRENT: "+current) ;
						String chemin = current+"/"+fileMessage.getNamefile() ;
						FileOutputStream fileOut = new FileOutputStream(chemin);
						int tailleLue;
						while ((tailleLue = inFile.read(bFile, 0,(int) fileMessage.getSize())) != -1) {
							fileOut.write(bFile, 0, tailleLue);
						}
						fileOut.close();

						ArrayList<String> list = ((FileMessage) fileMessage).getDest();
						if (list.remove(myUsername)) {
							System.out.println("TCPSERVER - FileMessage - Remove myUsername success : "+ myUsername);
						} else {
							System.out.println("TCPSERVER - FileMessage - [ERROR] This message was not for us ; myNickname was : "+ myUsername);
						}

						// On ajoute l'utilisateur qui nous a envoye le message
						list.add(fileMessage.getNickname());
						System.out.println("TCPSERVER - FileMessage - Source :"+ list.get(list.size() - 1));

						// On envoie les parametres au NIControler
						String name = fileMessage.getNickname();
						System.out.println("TCPSERVER : Received File from "+ name);
						NiCon.receivedFileMessage(fileMessage.getNamefile(),list);
						receiveSocket.close() ;
						
					}
				} catch (IOException e) {
					System.out.println("TCPSERVER IS CLOSED");
					
				} catch (ClassNotFoundException e) {
					System.out.println("TCPSERVER ERROR : CLASS NOT FOUND EXCEPTION");

				}
			}
	}

	/**
	 * Redefinition de la methode pour indiquer que le thread doit se mettre dans une socket d'ecoute
	 */
	public void run() {
		listen();
	}

}
