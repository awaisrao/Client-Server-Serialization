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
public class ServerSide extends Thread{

    /**
     * @param args the command line arguments
     */
     private ServerSocket serverSocket;
   
   public ServerSide(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000);
   }

   public void run()
   {
      while(true)
      {
         try
         {
           
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
            
                ObjectInputStream in =
                new ObjectInputStream(server.getInputStream());
                 DataClass sd = (DataClass)in.readObject();
                    
           
            
                //sd = (DataClass)in.readObject();
            //System.out.println((String)in.readObject());
            
            
            
            DataOutputStream out =
                 new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to "
              + server.getLocalSocketAddress() + "\nGoodbye!");
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
    public static void main(String[] args) {
        // TODO code application logic here
        int port = 12000;
      try
      {
         Thread t = new ServerSide(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
    }
    
}
