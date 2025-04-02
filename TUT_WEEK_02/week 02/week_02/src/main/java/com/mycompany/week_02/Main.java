/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author chamodpankaja
 */
public class Main {
      
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        UserValidator validator = new UserValidator();
        UserDataStore dataStore = new UserDataStore();
        List<Thread> threadList = new ArrayList<>();
        Object lock = new Object();
        boolean addmoreUsers = true;
        
        
        while(addmoreUsers){
        
        
            for (int i = 0; i < 3; i++) {
                
                System.out.println("enter user's name : ");
                String name =  scanner.nextLine();
                
                
                System.out.println("enter user's age : ");
                
                while(!scanner.hasNextInt()){
                    System.out.println("please enter valid age");
                    scanner.next();
                
                }
                int age = scanner.nextInt();
                
                
                UserProcessor processor = new UserProcessor(validator,dataStore,lock,name,age);
                Thread thread = new Thread(processor);
                threadList.add(thread);
                
                scanner.nextLine();
            }
            
            
            System.out.println("Do you want to add more users : ");
            String response = scanner.nextLine().trim().toLowerCase();
            addmoreUsers = response.equals("yes");
            
            
    
        
        }
        
        for(Thread t : threadList){
            
                t.start();
                
                try{
                    t.join();
                }catch(InterruptedException e ){
                    System.out.println("Thread Interrupted "+ e.getMessage());
                }
            
        }
        
        System.out.println("======== ALL USERS =========");
        
        for(User user : dataStore.getAllUsers()){
        
            System.out.println(user);
        }
        scanner.close();
        
        
        
    }
}
