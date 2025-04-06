/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.lab.based.practical.mock.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;



/*
 * ----------------
 * Please import libraries
 * ----------------
*/




/*
 * ----------------
 * Please define a class named 'ClientServer' that will contain both the server and client logic.
 * This demonstrates a simple client-server interaction within a single class.
 * ----------------
*/
public class ClientServer{
    /*
     * ----------------
     * Please create a logger and call it as logger
     * ----------------
    */

    private static final Logger logger = Logger.getLogger(ClientServer.class.getName());


    /*
     * ----------------
     * Please define a constant for the port number the server will listen on.
     * Using a constant makes it easy to change the port in one place.
     * ----------------
    */

        private static final int portNumber = 12345;

    /*
     * ----------------
     * Please define the main method, the entry point of the application.
     * This method creates and starts the server and client threads.
     * ----------------
    */
    
        public static void main(String[] args) {
        
    


        /*
         * ----------------
         * Please create a new thread for the server and start it.
         * The ServerSocket logic is encapsulated within the Server class.
         * ----------------
        */
      
            Thread serverThread = new Thread(new Server());
            serverThread.start();

        /*
         * ----------------
         * Please create a new thread for the client and start it.
         * The client logic is encapsulated within the Client class.
         * ----------------
        */
            Thread cliThread = new Thread(new Client());
            cliThread.start();
        }

    /*
     * ----------------
     * Please define a nested class named 'Server' that implements the Runnable interface.
     * This class encapsulates the server's functionality and allows it to run in a separate thread.
     * ----------------
    */
        static class Server implements Runnable{

        /*
         * ----------------
         * Please override the run method from the Runnable interface.
         * This method contains the main logic for the server.
         * ----------------
        */
            @Override
            public void run(){

            /*
             * ----------------
             * Please create a ServerSocket to listen for incoming client connections.
             * Use try-with-resources to ensure the ServerSocket is closed properly.
             * Inside the try block please please use informational logger to log "Server started on port 12345"
             * Handle potential IOExceptions during ServerSocket creation.
             * ----------------
            */
                try(ServerSocket serverSocket = new ServerSocket(portNumber)){
                
                    logger.log(Level.INFO,"Server is listening on port : "+portNumber);
                    
                    
                

                /*
                 * ----------------
                 * Please loop indefinitely, listening for and accepting client connections.
                 * ----------------
                */
                    while (true) {                        
                        
                    

                    /*
                     * ----------------
                     * Please accept an incoming client connection uaing serverSocket object and store it inside an object of the Socket class called clientSocket. 
                     * Please print "Client connected" plus the clientSocket object
                     * ----------------
                    */
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("Client has connected: " + clientSocket);



                    /*
                     * ----------------
                     * Please create a new ClientHandler thread to handle communication with the newly connected client.
                     * Pass the client's socket to the ClientHandler. Start the handler thread.
                     * ----------------
                    */
                    
                        Thread clientHandleThread = new Thread(new ClientHandler(clientSocket));
                        clientHandleThread.start();
                    
                    }
                }catch(IOException e){
                
                    e.printStackTrace();
                }
                    
            }   
        }

        /*
         * ----------------
         * Please define a nested class named 'ClientHandler' that implements the Runnable interface.
         * This class handles the interaction with a single connected client.
         * ----------------
        */
          static class ClientHandler implements Runnable{

              private Socket clientSocket;

            /*
             * ----------------
             * Please define a constructor for the ClientHandler class.
             * This constructor takes the client's socket as a parameter.
             * ----------------
            */
           
              public ClientHandler(Socket clientSocket) {
                  this.clientSocket = clientSocket;
              }


            /*
             * ----------------
             * Please override the run method of the Runnable interface.
             * This method contains the logic for communicating with the client.
             * ----------------
            */
                @Override
                public void run(){



                /*
                 * ----------------
                 * Use try-with-resources to ensure resources are closed automatically.
                 * Handle potential IOExceptions.
                 * ----------------
                */
                
                    try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true)){
                    
                    
                            
                            

                        /*
                         * ----------------
                         * Please create a BufferedReader to read text data from the client socket's input stream.
                         * BufferedReader buffers the input for efficient reading of characters, lines, and arrays.
                         * Wrap the getInputStream() (which provides raw byte stream) with an InputStreamReader
                         * to convert the byte stream into a character stream.
                         * ----------------
                        */
                        


                        /*
                         * ----------------
                         * Please create a PrintWriter to send formatted text data to the client socket's output stream.
                         * PrintWriter provides convenient methods for writing various data types (strings, numbers, etc.) as text.
                         * The 'true' argument enables auto-flushing, ensuring that data is sent immediately after each print/println call,
                         * preventing buffering-related delays.  Wrap getOutputStream() (which provides raw byte stream).
                         * ----------------
                        */
                       


                    /*
                     * ----------------
                     * Please read messages from the client in a loop and store it inside a string varibale called inputLine.
                     * Continue reading until the client sends "exit" or the connection is closed.
                     * print the inputLine plus a message like "Server received: "
                     * ----------------
                    */
                    
                        String inputLine;
                        
                        while((inputLine = in.readLine())!= null){
                        
                            System.out.println("Message Recieved : "+ inputLine);
                            
                            
                            if("exit".equalsIgnoreCase(inputLine)){
                            
                                out.println("Bye");
                                break;
                            }else{
                            
                                out.println("Echo: " + inputLine);
                            }
                        
                        }
                    


                        /*
                         * --------------
                         * Please check if the client has sent the "exit" command.
                         * --------------
                        */
                        


                            /*
                             * --------------
                             * If "exit" is received, send a "Bye" message to the client and break out of the loop.
                             * The println() method of PrintWriter writes a string followed by a newline character to the output stream.
                             * Then break the operation
                             * otherwise
                             * --------------
                            */
                            



                            /*
                             * --------------
                             * send the inputLine plus "Echo: "
                             *  
                             * --------------
                            */
                           



                
                /*
                 * --------------
                 * create a try block and close the clientSocket and catch io exception                
                 * --------------
                */

                }catch(IOException e){
                
                        System.out.println("Exception happened : " + e.getMessage());
                }finally{
                    
                    try{
                            
                        clientSocket.close();
                    }catch(IOException e){
                        
                        e.printStackTrace();
                    }
                
                }
                    
                
                
            }
        }


    /*
     * ----------------
     * Please define a nested class named 'Client' that implements the Runnable interface.
     * This class encapsulates the client's functionality and allows it to run in a separate thread.
     * ----------------
    */
    
        static class Client implements Runnable{

        /*
         * ----------------
         * Please override the run method from the Runnable interface.
         * This method contains the main logic for the client.
         * ----------------
        */
        
             @Override 
             public void run(){


            /*
             * ----------------
             * Use try-with-resources to ensure resources are closed automatically.
             * ----------------
            */
          

                    /*
                     * --------------------
                     * Please create a Socket object, establishing a connection to the server at the specified host 
                       ("localhost") and port (PORT).
                     * This initiates the connection to the server's ServerSocket.
                     * --------------------
                    */
                    
                    
                   try(
                           
                        Socket socket = new Socket("localhost",portNumber);
                        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedReader stdIn   = new BufferedReader(new InputStreamReader(System.in))
                           
                           
                    ){
                   

                    /*
                     * ----------------
                     * Please create a PrintWriter to send formatted text data to the server through the socket's output stream.
                     * PrintWriter simplifies sending text-based data.
                     * 'true' for auto-flush: ensures data is sent immediately without buffering delays.
                     * Wrap socket.getOutputStream() to provide character-based output.
                     * ----------------
                    */
                    


                    /*
                     * ----------------
                     * Please Create a BufferedReader to read text data from the server's responses through the socket's input stream.
                     * BufferedReader provides efficient reading of characters, lines, and arrays by buffering the input.
                     * Wrap socket.getInputStream() with InputStreamReader to convert bytes to characters.
                     * ----------------
                    */
                    


                    /*
                     * -------------------
                     * Please create a BufferedReader to read text input from the console (System.in).
                     * This allows the client to read user input.
                     * Wrap System.in (which is an InputStream) with an InputStreamReader to handle character encoding.
                     * -------------------
                    */
                    

                        System.out.println("client has connected to the server");
    

                /*
                 * ----------------
                 * Please read input from the console  in a loop.
                 * ----------------
                */
                
                    String userInput;
                    
                    
                    /*
                     * ----------------
                     * Please send the user's input to the server using the PrintWriter.
                     * ----------------
                    */
                       System.out.println("enter msg to send: ");
                    while((userInput = stdIn.readLine()) != null){
                    
                        out.println(userInput);
                        
                        String recievedMessage = in.readLine();
                        
                        if(recievedMessage == null){
                        
                            System.out.println("Server closed the connection.");
                            break;
                        }
                        System.out.println("Server Response: " + recievedMessage);
                        
                        if("exit".equalsIgnoreCase(userInput)){
                        
                            break;
                        }
                        
                    
                    }



                    /*
                     * ----------------
                     * Please read the server's response using the BufferedReader and store it inside a string variable called receivedMessage .
                     * check if the receivededMessage is null
                     * Then print "Server closed the connection."
                     * exit the loop
                     * ----------------
                    */
                    


                    /*
                     * ---------------
                     * Print Server Response 
                     * ---------------
                    */
                    

                    /*
                     * ----------------
                     * Please check if the user typed "exit". If so, exit the loop.
                     * ----------------
                    */
                    
                   }catch(IOException e){
                       System.out.println("Exception happened : "+ e.getMessage());
                   }
             }    
                    
        
             
        }
     
                
}