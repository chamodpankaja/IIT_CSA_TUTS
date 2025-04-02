/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week_01_prc;

/**
 *
 * @author chamodpankaja
 */
public class Reciever {
    
    public void recieveMessage(Message msg){
    
        System.out.println("[RECIEVER] - Message Recieved");
        System.out.println("Recieved MEssage - " + msg.getContent() );
    
    }
    
}