/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week_01_prc;

/**
 *
 * @author chamodpankaja
 */
public class Sender {
    
    public Message createMessage(String msg){
    
        Message message = new Message(msg);
        System.out.println("[SENDER] - Message was Created");
        return message;
    }
    
    
    public void sendMsg(Message msg ,  Reciever rec){
    
        System.out.println("[SENDER] -  Message send to reciever");
        rec.recieveMessage(msg);
    }
}
