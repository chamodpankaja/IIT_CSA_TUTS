/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_mock.resource;

import com.mycompany.csa_mock.dao.ModuleDAO;
import com.mycompany.csa_mock.Model.Module;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jdk.internal.vm.annotation.Contended;

/**
 *
 * @author chamodpankaja
 */
@Path("/modules")
public class ModuleResource {
    
    private ModuleDAO moduleDAO = new ModuleDAO();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Module> getAllModules(){
        
       return moduleDAO.getAllModules();
    }
    
    @GET
    @Path("/{moduleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Module getModuleById(@PathParam("moduleId") int moduleId){
    
       return moduleDAO.getModuleById(moduleId);
    
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addModule(Module module){
    
        moduleDAO.addModule(module);
    }
    
    
    @PUT
    @Path("/{moduleId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateModule(@PathParam("moduleId") int moduleId, Module updatedModule){
    
        Module existingModule =  moduleDAO.getModuleById(moduleId);
        if(existingModule != null){
        
            updatedModule.setId(moduleId);
            moduleDAO.updateModule(updatedModule);
        }
    
    }

    @DELETE
    @Path("/{moduleId}")
    public void deleteModule(@PathParam("moduleId") int moduleId){
    
        moduleDAO.deleteModule(moduleId);
    }
    
    @GET
    @Path("/teachers/{teacherId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Module> getModulesByTeacher(@PathParam("teacherId") int teacherId){
    
        List<Module> modulesByTeacher =new ArrayList<>();
        List <Module> allModules = moduleDAO.getAllModules();
        
        for(Module module : allModules){
        
            if(module.getTeacher().getId() == teacherId){
            
                modulesByTeacher.add(module);
            }
        }
        return modulesByTeacher;
     }
    
    
}
