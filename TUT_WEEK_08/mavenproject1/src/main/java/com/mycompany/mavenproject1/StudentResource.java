/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author chamodpankaja
 */
@Path("/students")
public class StudentResource {

    private static final ConcurrentHashMap<String, Student> studentStore = new ConcurrentHashMap<>();
    
    
    static{
        addInitialStudents();
    }
    private static void addInitialStudents(){
    
        Student student1 = new Student(UUID.randomUUID().toString(),"Alice","smith");
        Student student2 = new Student(UUID.randomUUID().toString(),"chamod","pankaja");
        Student student3 = new Student(UUID.randomUUID().toString(),"kamal","perera");
        
        studentStore.put(student1.getId(),student1);
        studentStore.put(student2.getId(),student2);
        studentStore.put(student3.getId(),student3);
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public List<Student> getAllStudents(){
    
        return new ArrayList<>(studentStore.values());
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getStudentById(@PathParam("id") String studentId){
    
        Student student = studentStore.get(studentId);
        
        if(student == null){
        
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
        
            return Response.status(Response.Status.OK).build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student){
        
        if(student.getFirstName() != null && student.getLastName() != null){
        
            String id = UUID.randomUUID().toString();
            
            student.setId(id);
            studentStore.put(id, student);
            
            return Response.status(Response.Status.CREATED).entity(student).build();
        }else{
        
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
    
    }
    
    
}
