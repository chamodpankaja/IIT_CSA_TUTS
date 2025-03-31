/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_mock.dao;

import java.util.ArrayList;
import java.util.List;
import com.mycompany.csa_mock.Model.Module;
import com.mycompany.csa_mock.Model.Teacher;
/**
 *
 * @author chamodpankaja
 */
public class ModuleDAO {
    
    private static List<Module> modules = new ArrayList<>();
    
    static{
          TeacherDAO teacherDAO = new TeacherDAO();
          List<Teacher> teachers = teacherDAO.getallTeachers();
          
          modules.add(new Module(1,"Science",teachers.get(0)));
          modules.add(new Module(2,"Maths",teachers.get(1)));
          
    }
    
    public List<Module> getAllModules(){
    
        return modules;
    }
    
    public Module getModuleById(int id){
    
        for(Module module: modules){
            
            if(module.getId()== id){
                return module;
            }
        }
        return null;
    
    
    }
    
    public void addModule(Module module){
    
        modules.add(module);
    }
    
    
    public void updateModule(Module updatedModule){
    
        for(int i=0; i<modules.size(); i++){
        
            Module currentModule = modules.get(i);
            
            if(currentModule.getId() == updatedModule.getId()){
                modules.set(i, updatedModule);
                System.out.println("Module updated: "+ updatedModule);
            }
        
        }
    }
    
    public void deleteModule(int id){
    
        modules.removeIf(module -> module.getId() == id);
    
    }
    
    
}
