package server;

import java.net.*;
import java.io.*;

public class CCMultiServerThread extends Thread {
	private Socket socket = null;
	String message;

	public CCMultiServerThread(Socket socket) {
		super("KKMultiServerThread");
		this.socket = socket;
	}

	public void run() {



		try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				OutputStream os = socket.getOutputStream();

		)

		{
			String inputLine, s;
			CCProtocol ccp = new CCProtocol();

			s = "Hello";
			out.println(s);

			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				System.out.print("Enter Chat Message: ");
				s = br.readLine();
				ccp.processInput(s);
				message = ccp.processInput(s);
				out.println(message);
				if (s.equals("Bye"))
					break;
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
