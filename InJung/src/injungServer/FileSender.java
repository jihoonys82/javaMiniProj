package injungServer;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;

/**
 * File Sender (ServerSide) DataOutputStream - send String(filename) and File
 * Referred form
 * https://stackoverflow.com/questions/15649972/how-do-i-send-file-name-with-file-using-sockets-in-java
 * 
 * @since 2018-07-04
 * @author Jihoon Jeong
 *
 */
public class FileSender extends Thread {

	private File file;
	private File dir = new File("./server_photo");

	private Socket sock;

	private DataOutputStream dos;
	private BufferedOutputStream bos;

//	private String host = "localhost";
//	private int port = 9998;

	/**
	 * File Sender Constructor
	 * 
	 * @param file : Source file
	 */
	public FileSender(Socket sock, String filename) {
		this.file = new File(dir, filename);
		this.sock = sock;
	}

	@Override
	public void run() {
		try {
//			sock = new Socket(host,port);
			bos = new BufferedOutputStream(sock.getOutputStream());
			dos = new DataOutputStream(bos);

//			dos.writeUTF(file.getName()); // send file name
			Files.copy(file.toPath(), dos); // copy file to server

//			dos.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (dos != null)
					dos.close();
				if (bos != null)
					bos.close();
				if (sock != null)
					sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
