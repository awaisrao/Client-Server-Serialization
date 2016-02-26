/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientsocket;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author zulfiqar.bscs13seecs
 */
public class ClientSocket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
           String serverName = "localhost";
      int port = 12000;
      try
      {
         
         Scanner reader = new Scanner(System.in);  // Reading from System.in
         Scanner reader1 = new Scanner(System.in);  // Reading from System.in
         System.out.println("Connecting to " + serverName +
		 " on port " + port);
         Socket client = new Socket(serverName, port);
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);
         System.out.println("Just connected to " 
		 + client.getRemoteSocketAddress());
         System.out.println("1)To store new data"); 
         System.out.println("2)To access data");
         int option;
         while(true){
            System.out.print("option:");
            option = reader.nextInt();
            String ad = reader.nextLine();
            if(option == 1){
                      DataClass c = new DataClass();
                      System.out.print("Enter your name:");
                      String n = reader.nextLine(); // Scans the next token of the input as an int.
                      c.name = n;
                      System.out.print("Enter notes:");
                      String n1 = reader1.nextLine();
                      c.notes = n1;
                      ObjectOutputStream out1 = new ObjectOutputStream(outToServer);
                      out1.writeObject(c);
                
            }
            else if(option == 2){
                   System.out.println("Enter your name: ");
                   String n2 = reader.nextLine(); // Scans the next token of the input as an int.
                   out.writeUTF(n2);
               
            }
         }
         
         
         
         
         
         InputStream inFromServer = client.getInputStream();
         DataInputStream in =
                        new DataInputStream(inFromServer);
         System.out.println("Server says " + in.readUTF());
         client.close();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
    }
    
}
