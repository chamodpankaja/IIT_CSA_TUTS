/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorial.week03.socket2;

//import necessary libraries

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class SimpleChatServer {
    
    public static void main(String[] args) {
            
        try{
            //Create a new instance of the Server Socket and pass port number
            
            ServerSocket serverSocket = new ServerSocket(12345);
            
            //print out a message to say Server is running 
            System.out.println("[SERVER] - server is running " );

            // Wait for a client to connect and accept the client request
            Socket socket = serverSocket.accept();

            //print out a message to say client connected and get the IP ddress
            System.out.println("[SERVER] - client is connected with IP Address: " + socket.getInetAddress());

            // Input stream to receive messages from the client
                InputStream inputStream =socket.getInputStream();
            // Output stream to send messages to the client
                OutputStream outputStream = socket.getOutputStream();
            
            //Create a buffer array with type byte, the size must be 1024
            
                byte [] buffer = new byte[1024];
            // Read messages from the client and print them
            
            int totalBytes;
            
            while((totalBytes = inputStream.read(buffer))!= -1){
            
                String clientMsg = new String(buffer,0,totalBytes);
                 System.out.println("[SERVER]  - client said : " + clientMsg);
                 outputStream.write("Recieved Message".getBytes());
            }
            
            

            // Close the sockets
            socket.close();
            serverSocket.close();
            
        //catch IO exception
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
