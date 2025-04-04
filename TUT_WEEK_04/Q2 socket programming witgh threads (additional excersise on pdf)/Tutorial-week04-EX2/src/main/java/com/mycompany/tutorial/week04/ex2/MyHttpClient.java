/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorial.week04.ex2;

import com.sun.net.httpserver.Headers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chamodpankaja
 */
public class MyHttpClient {
    
    public static void main(String[] args) {
        
        try{
            URL url = new URL("http://localhost:8080/hello");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            
            System.out.println("DEFAULT REQUEST HEADERS");
            Map<String,List<String>>  defaultHeaders = con.getRequestProperties();
            printHeaders(defaultHeaders);
            
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: "+ responseCode);
            
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in  = new BufferedReader(new InputStreamReader(con.getInputStream()));
                
                StringBuilder response = new StringBuilder();
                String line ; 
                
                while ((line=in.readLine())!=null) {
  
                   response.append(line).append("\n");
                    
                }
                in.close();
                System.out.println("Response:\n" + response.toString());
                
            }else{
            
                System.out.println("GET request not worked");
            }
        
        }catch(IOException e){
            
            System.out.println("Error during connection : "+ e.getMessage());
        }
    }
    
    
    
    private static void printHeaders(Map<String,List<String>> headers){
        
        headers.forEach((key,value)->{
        
            if(key != null){
            
                
                   System.out.println(key + ": "+String.join(", ",value));
               
            }
        });
    
    }
}
