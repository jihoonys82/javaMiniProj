package injung;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;

/**
 * File Receiver 
 * DataIutputStream - receive and File which is requested. 
 * Referred form https://stackoverflow.com/questions/15649972/how-do-i-send-file-name-with-file-using-sockets-in-java
 * @since 2018-07-15
 * @author Jihoon Jeong
 *
 */
public class FileReceiver extends Thread {
	
	private File file;
	private Socket sock; 
	
	private DataOutputStream dos;
	private BufferedOutputStream bos;
	
	private DataInputStream dis;
	private BufferedInputStream bis;
	
	private String host = "localhost";
	private int port = 9998;
	
	/**
	 * File Receiver Constructor
	 * @param file : Source file
	 */
	public FileReceiver(File file) {
		this.file = file;
	}
	
	@Override
	public void run() {
		try {
			sock = new Socket(host,port);
			bos = new BufferedOutputStream(sock.getOutputStream());
			dos = new DataOutputStream(bos);
			
			dos.writeUTF("Send");			// notice file sending to server
			System.out.println(file.getName());
			dos.writeUTF(file.getName());	// send file name
			Files.copy(file.toPath(), dos); // copy file to server
			
			dos.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(dos!=null) dos.close();
				if(bos!=null) bos.close();
				if(sock!=null) sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
}
