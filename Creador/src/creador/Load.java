/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creador;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public class Load {
    public void loadFile(String sql,String pathEntities,String pathMysql,String dao){
            
            try {
            Scanner input = new Scanner(new File(sql));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.contains("CREATE TABLE")) {
                    line = line.replace("`", "");
                    line = line.replace("(", "");
                    line = line.replace(".", "-");
                    line = line.replace(" ", "");
                    String[] name = line.split("-");
                    List<String[]> variables = new ArrayList<>();
                    boolean nextVar = true;
                    while (nextVar) {
                        input.hasNextLine();
                        String variable = input.nextLine();
                        
                        if (variable.contains("PRIMARY KEY")) {
                            nextVar = false;
                           Write w=new Write();
                           w.crear(name[1],variables,pathEntities);
                           w.interfaces(name[1],dao);
                           w.crearMysql(name[1], variables,pathMysql,dao);
                        }else{
                            identificar(variables,variable);
                        }
                    }
                  
                  //  System.out.println(name[1]);
                    
                    
                }
                
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    Write w = new Write();
    w.crearDAOManager(dao);
    w.managerSql(dao,pathMysql);
        try {
            w.DAO(dao);
        } catch (IOException ex) {
            Logger.getLogger(Creador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(null,  "Los Beans se han creado Correctamente", "Informacion",JOptionPane.INFORMATION_MESSAGE);
    }
    private  void identificar(List variable, String linea){
       linea = linea.replace("`", "");
           linea = linea.replace(" ", "-");
           String[] line = linea.split("-");
           
       if (linea.contains("INT")) {
           
           if(linea.contains("TINYINT")){
           
                variable.add(new String[]{line[2],"boolean"});
           
            }else{
                variable.add(new String[]{line[2],"int"});
           }
       }else if(linea.contains("VARCHAR")){
           variable.add(new String[]{line[2],"String"});
           
       }else if(linea.contains("TINYINT")){
           
           variable.add(new String[]{line[2],"boolean"});
           
       }else if(linea.contains("DOUBLE")){
           variable.add(new String[]{line[2],"double"});
           
       }else if(linea.contains("DATE")){
           variable.add(new String[]{line[2],"java.sql.Date"});
           
       }
           
//       }else if(linea.contains("VARCHAR")){
//           
//       }else if(linea.contains("VARCHAR")){
//           
//       }else if(linea.contains("VARCHAR")){
//           
//       }else if(linea.contains("VARCHAR")){
//           
//       }else if(linea.contains("VARCHAR")){
//           
//       }else if(linea.contains("VARCHAR")){
//           
//       }else if(linea.contains("VARCHAR")){
//           
//       }else if(linea.contains("VARCHAR")){
//           
//       }
//       
   }     
   
}
