/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.week_02;

/**
 *
 * @author chamodpankaja
 */
public class UserProcessor implements Runnable {
   
    private UserValidator validator;
    private UserDataStore dataStore;
    private Object lock;
    private String name;
    private int age;

    public UserProcessor(UserValidator validator, UserDataStore dataStore, Object lock, String name, int age) {
        this.validator = validator;
        this.dataStore = dataStore;
        this.lock = lock;
        this.name = name;
        this.age = age;
    }
    
    
    @Override
    public void run(){
    
        synchronized (lock) {
            if(validator.isValidAge(age)){
               dataStore.addUser(new User(name,age));
               
            }else{
            
                System.out.println("\nInvalid age for : " + name+"\n");
            }
        }
    
    }
    
}
