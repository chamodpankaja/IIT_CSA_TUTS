/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_mock.resource;

import com.mycompany.csa_mock.Model.Student;
import com.mycompany.csa_mock.Model.Module;
import com.mycompany.csa_mock.dao.ModuleDAO;
import com.mycompany.csa_mock.dao.StudentDAO;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author chamodpankaja
 */

@Path("/students")
public class StudentResource {
    
    private StudentDAO studentDAO = new StudentDAO();
    private ModuleDAO moduleDAO  = new ModuleDAO();
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public List<Student> getAllStudents(){
        
        return studentDAO.getAllStudents();
    }
    
    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentById(@PathParam("studentId") int studentId){
    
        return studentDAO.getStudentByID(studentId);
    
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addStudent(Student student ){
    
        studentDAO.addStudent(student);
    
    }
    
    @PUT
    @Path("/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateStudent(@PathParam("studentId") int studentId, Student updatedStudent){
    
        Student existingStudent = studentDAO.getStudentByID(studentId);
        
        if(existingStudent != null){
        
            updatedStudent.setId(studentId);
            studentDAO.updateStudent(updatedStudent);
        }

    }
    
    @DELETE
    @Path("/{studentId}")
    public void deleteStudent(@PathParam("studentId")int studentId){
        studentDAO.deleteStudent(studentId);
    }
    
        
    @GET
    @Path("/{studentId}/modules")
    @Produces(MediaType.APPLICATION_JSON)
    public String getModuleForStudent(@PathParam("studentId") int studentId){
    
        Student student = studentDAO.getStudentByID(studentId);
        if(student != null){
            
            int moduleId = 1;
            Module selectedModule = moduleDAO.getModuleById(moduleId);
            
            if(selectedModule != null){
                return "{\"module\": \"" + selectedModule.getName() + "\", \"teacher\": \"" + selectedModule.getTeacher() + "\"}";
            }    
        }
        return "{\"error\" : \"student or module not found.\"}";
    }
}
