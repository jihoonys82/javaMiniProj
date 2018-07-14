package injungServer;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;

/**
 * File Receiver Thread Class 
 * @author Jihoon Jeong
 * @since 2018-07-11
 */
public class FileReceiver extends Thread { 
	
//	private Socket sock; 
	private BufferedInputStream bis;
	private DataInputStream dis;
	
	private File dir = new File("./server_photo"); // default photo storage folder
	private File file; 
	
	public FileReceiver(BufferedInputStream bis, DataInputStream dis) {
//		this.sock = sock;
		this.bis = bis;
		this.dis = dis;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("File Receiving...");
			
			String fileName = dis.readUTF();
			file = new File(dir, fileName);
			Long bytes = Files.copy(dis, file.toPath());
			
			System.out.println(file.getName() +" (Size:"+ bytes + "bytes) has been transfered to Server/n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
//			try {
//				if(dis!=null) dis.close();
//				if(bis!=null) bis.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	} // end of run() method 
	
	
}