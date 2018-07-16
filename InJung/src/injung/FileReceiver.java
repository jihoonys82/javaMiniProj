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

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * File Receiver (for Client) DataIutputStream - receive and File which is
 * requested. Referred form
 * https://stackoverflow.com/questions/15649972/how-do-i-send-file-name-with-file-using-sockets-in-java
 * 
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

	private JLabel lbl;

	/**
	 * File Receiver Constructor
	 * 
	 * @param file : Source file
	 */
	public FileReceiver(File file, JLabel lbl) {
		this.file = file;
		this.lbl = lbl;
	}

	@Override
	public synchronized void run() {
		try {
			sock = new Socket(host, port);
			bos = new BufferedOutputStream(sock.getOutputStream());
			dos = new DataOutputStream(bos);

			bis = new BufferedInputStream(sock.getInputStream());
			dis = new DataInputStream(bis);

			dos.writeUTF("Request"); // notice file request to server
			dos.flush();
			dos.writeUTF(file.getName()); // request file
			dos.flush();
			System.out.println("Request to server : " + file.getName());
			Long bytes = Files.copy(dis, file.toPath());

			System.out.println(file.getName() + " (Size:" + bytes + "bytes) has been transfered from Server/n");
			dos.flush();

			lbl.setIcon(new ImageIcon(file.toString()));
			lbl.validate();
			lbl.repaint();

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
