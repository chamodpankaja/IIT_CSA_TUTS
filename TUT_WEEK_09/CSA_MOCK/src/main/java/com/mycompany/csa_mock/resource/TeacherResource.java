/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_mock.resource;

import com.mycompany.csa_mock.Model.Teacher;
import com.mycompany.csa_mock.dao.TeacherDAO;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author chamodpankaja
 */
@Path("/teachers")
public class TeacherResource {
    
    private TeacherDAO teacherDAO = new TeacherDAO();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Teacher> getAllTeachers(){
    
        return teacherDAO.getallTeachers();
    }
    
    
    @GET
    @Path("/{tacherId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Teacher getTeacherById(@PathParam("teacherId") int teacherId){
    
        return teacherDAO.getTeacherByID(teacherId);
    
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTeacher (Teacher teacher){
    
        teacherDAO.addTeacher(teacher);
    }
    
    @PUT
    @Path("/{teacherId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTeacher(@PathParam("tacherId") int teacherId ,Teacher updatedTeacher){
    
        Teacher existingTeacher = teacherDAO.getTeacherByID(teacherId);
        if(existingTeacher != null){
        
            updatedTeacher.setId(teacherId);
            teacherDAO.updateTeacher(updatedTeacher);
        }
    }
    
    @DELETE
    @Path("/{teacherId}")
    public void deleteTeacher(@PathParam("teacherId") int teacherId){
        teacherDAO.deleteTeacher(teacherId);
    }
    
    
    
}
