package com.felix.tmall.util;

import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JOptionPane;

public class PortUtil {

	public static boolean testPort(int port) {
		try {
			ServerSocket ss = new ServerSocket(port);
			ss.close();
			return false;
		} catch (java.net.BindException e) {
			return true;
		} catch (IOException e) {
			return true;
		}
	}


	public static void checkPort(int port, String server, boolean shutdown) {
		if(!testPort(port)) {
			if(shutdown) {
				String message =String.format("hasn't find %s started in port: %d %n",server,port);
				JOptionPane.showMessageDialog(null, message);
				System.exit(1);
			}
			else {
				String message =String.format("\"hasn't find %s started in port: %d %n\"continue or not?",server,port);
			    if(JOptionPane.OK_OPTION != 	JOptionPane.showConfirmDialog(null, message)) 
					System.exit(1);
			    
				
			}
		}
	}

}
