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

public class TCPServer extends AbstractReceiver implements Runnable {

	private static TCPServer singleton;

	private static NIControler NiCon;
	
	private ServerSocket server = null;

	private TCPServer() {
		this.setPortEcoute(6789);
		try {
			server = new ServerSocket(this.getPortEcoute());
			System.out.println("TCPSERVER - Waiting for connection ...");
		} catch (IOException e) {
			System.out.println("TCPSERVER ERROR DURING CREATION");
		}	
	}

	public static TCPServer getInstanceTCPServer() {
		if (singleton == null) {
			singleton = new TCPServer();
		}
		return singleton;
	}

	public void setNiCon(NIControler NiCont) {
		TCPServer.NiCon = NIControler.getInstance();
	}
	
	public ServerSocket getServerSocket(){
		return server;
	}

	/*
	 * Redéfinition de la méthode d'écoute des paquets UDP sur le réseau
	 */
	public void listen() {
		
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
						String chemin = "/home/mamadou/Documents/Val/4IR/COO-POO/"
								+ fileMessage.getNamefile();
						FileOutputStream fileOut = new FileOutputStream(chemin);
						int tailleLue;
						while ((tailleLue = inFile.read(bFile, 0,
								(int) fileMessage.getSize())) != -1) {
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

	public void run() {
		listen();
	}

}
