/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_mock.dao;

import com.mycompany.csa_mock.Model.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chamodpankaja
 */
public class TeacherDAO {
    
    private static List<Teacher> teachers =  new ArrayList<>();
     
    static{
        teachers.add(new Teacher(1,"Mr nuwan"));
        teachers.add(new Teacher(2,"Mr Kamal"));
    }
    
    
    public List<Teacher> getallTeachers(){
    
        return teachers;
    }
    
    
     public Teacher getTeacherByID(int id){
    
        for(Teacher teacher : teachers){
        
            if(teacher.getId() == id){
            
                return teacher;
            }
        }
        return null;
    }
    
    public void addTeacher(Teacher teacher){
    
       int newUserId = getNextUserId();
       teacher.setId(newUserId);
       teachers.add(teacher);
    }
    
    
    public void updateTeacher(Teacher updatedTeacher) {
        
        for(int i =0 ; i<teachers.size();i++){
        
            Teacher currentTeacher = teachers.get(i);
            if(currentTeacher.getId() == updatedTeacher.getId()){
            
                teachers.set(i,updatedTeacher);
                System.out.println("Teacher updated " + updatedTeacher );
            } 
        
        }
        
    
    }


    public void deleteTeacher(int id){
    
        teachers.removeIf(teacher -> teacher.getId() ==id);
    }
    
    public int getNextUserId(){
    
       int maxUserId = Integer.MIN_VALUE;
       for(Teacher teacher : teachers){
           int userId = teacher.getId();
           if(userId > maxUserId){
               maxUserId = userId;
                       
           }
       }
       return maxUserId +1;
    }
}
