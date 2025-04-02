/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week_01_prc;

/**
 *
 * @author chamodpankaja
 */
public class Main {
    
    public static void main(String[] args) {
        
        Sender send = new Sender();
        Reciever reciever = new Reciever();
        Message msg = send.createMessage("Hello world from Thumpane");
        send.sendMsg(msg, reciever);
        
    }
}
