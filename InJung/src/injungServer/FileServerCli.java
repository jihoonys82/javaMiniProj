package injungServer;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * FileServer Command line Version
 * 
 * @author Jihoon Jeong
 * @since 2018-07-11
 */
public class FileServerCli {

	// Socket member
//	private String host = "localhost"; 	// default value : 127.0.0.1(localhost)
	private final int port = 9998; // default port : 9998

	private Socket sock;
	private ServerSocket serv;

	private BufferedInputStream bis;
	private DataInputStream dis;

	private File dir = new File("./server_photo");

	private FileServerCli() {
		try {
			System.out.println("***Starting Server***");
			serv = new ServerSocket(port);

			while (true) {
				System.out.println("Waiting for Client...");
				sock = serv.accept();

				if (bis == null)
					bis = new BufferedInputStream(sock.getInputStream());
				if (dis == null)
					dis = new DataInputStream(bis);

				System.out.println("Client(" + sock.getInetAddress().getHostAddress() + ") is connected.");

				// check it is request for sending or receiving file.
				// when receive "Send" : FileReceiver call
				// when receive "Request" : FileSender call
				System.out.println(1);
				String r = dis.readUTF();
				System.out.println(11);
//				if(dis.readUTF().equals("Send")) {
				if (r.equals("Send")) {
					System.out.println(2);
					System.out.println("File Receivng...");
					FileReceiver receiver = new FileReceiver(dis);
					receiver.start();
//				} else if(dis.readUTF().equals("Request")) {
				} else if (r.equals("Request")) {
					System.out.println(3);
					String requestedFile = dis.readUTF(); // receive file name for request.
					System.out.println("File (" + requestedFile + ") Sending...");
					if ((new File(dir, requestedFile).exists())) {
						System.out.println(31);
						FileSender sender = new FileSender(sock, requestedFile);
						sender.start();
					} else {
						System.out.println(32);
						System.out.println(requestedFile + " is not exists in server!");
					}
				}
				System.out.println(4);
				System.out.println("dddd : " + r);
//				System.out.println(dis.readUTF());
			} // end of while
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("Closing Service....");
				if (dis != null)
					dis.close();
				if (bis != null)
					bis.close();
				if (sock != null)
					sock.close();
				if (serv != null)
					serv.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end of try-catch
	}

	public static void main(String[] args) {
		new FileServerCli();
	}

}
