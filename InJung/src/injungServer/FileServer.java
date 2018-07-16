package injungServer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * FileServer Class File Receiving Server
 * 
 * @author Ji
 *
 */
public class FileServer extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = -8924679077451955255L;

	// Frame member
	private Container root;

	private JPanel topPane = new JPanel();
	private JPanel centerPane = new JPanel();
	private JPanel bottomPane = new JPanel();

	private JLabel lblIp = new JLabel("IP");
	private JLabel lblPort = new JLabel("Port");
	private JTextField txtIp = new JTextField();
	private JTextField textPort = new JTextField();

	private JScrollPane scrollPane = new JScrollPane();
	private JTextArea txtConsole = new JTextArea();

	private JButton btnStart = new JButton("Start Server");
	private JButton btnStop = new JButton("Stop Server");

	// Socket member
	private String host = "localhost"; // default value : 127.0.0.1(localhost)
	private int port = 9998; // default port : 9998

	private Socket sock;
	private ServerSocket serv;

	// File member
	private File dir = new File("./server_photo"); // default photo storage folder
	private File file;

	private FileServer() {
		initFrame();
	}

	private void initFrame() {
		// setup Frame
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("InJung File Server");

		root = getContentPane();

		// setup topPane
		topPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		txtIp.setColumns(10);
		txtIp.setText(host);
		textPort.setColumns(5);
		textPort.setText(((Integer) port).toString());

		// setup centerPane
		centerPane.setLayout(new GridLayout(0, 1, 5, 5));
		scrollPane.setViewportView(txtConsole);

		// setup bottomPane
		bottomPane.setLayout(new FlowLayout());

		// set buttons' action
		btnStart.addActionListener(this);
		btnStop.addActionListener(this);

		// add components
		topPane.add(lblIp);
		topPane.add(txtIp);
		topPane.add(lblPort);
		topPane.add(textPort);

		centerPane.add(scrollPane);

		bottomPane.add(btnStart);
		bottomPane.add(btnStop);

		root.add(topPane, BorderLayout.NORTH);
		root.add(centerPane, BorderLayout.CENTER);
		root.add(bottomPane, BorderLayout.SOUTH);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnStart)) {
			startServer();
		} else if (e.getSource().equals(btnStop)) {
			// [Test Needed] Below is Jihoon's test. I am not sure it works or not!
			try {
				if (sock != null)
					sock.close();
				if (serv != null)
					serv.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}

	private void startServer() {

		try {
			txtConsole.append("***Starting Server***");
			serv = new ServerSocket(port);

			while (true) {
				txtConsole.append("Waiting for Client...");
				sock = serv.accept();
				txtConsole.append("Client(" + sock.getInetAddress().getHostAddress() + ") is connected.\n");

//				FileReceiver receiver = new FileReceiver(sock, bis, dis);
//				SwingUtilities.invokeLater(receiver);

			} // end of while
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				txtConsole.append("Closing Service");
				if (sock != null)
					sock.close();
				if (serv != null)
					serv.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end of try-catch for serv

	} // end of startServer()

//	public class FileReceiver extends Thread { 
//		
//		private Socket sock; 
//		private BufferedInputStream bis;
//		private DataInputStream dis;
//		
//		public FileReceiver(Socket sock) {
//			this.sock = sock;
//		}
//		
//		@Override
//		public void run() {
//			try {
//				bis = new BufferedInputStream(sock.getInputStream());
//				dis = new DataInputStream(bis);
//				
//				txtConsole.append("File Receiving.../n");
//				
//				String fileName = dis.readUTF();
//				file = new File(dir, fileName);
//				Files.copy(dis, file.toPath());
//				
//				txtConsole.append(file.getName() + " has been transfered to Server/n");
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					if(dis !=null) dis.close();
//					if(bis !=null) bis.close();
//					if(sock!=null) sock.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			
//			}
//		} // end of run() method 
//		
//		
//	}

	public static void main(String[] args) {
		new FileServer();

	}

	@Override
	public void run() {

	}

}
