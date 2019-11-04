/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creador;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio
 */
public class Write {
    private BufferedWriter bw;
    
    public void crear(String name,List<String[]> variables,String entities){
        
        
        try {
        
            String ruta = entities+name+".java";
            
            File file = new File(ruta);
            // Si el archivo no existe es creado
                if (!file.exists()) {
                    file.createNewFile();
                    FileWriter fw = new FileWriter(file,true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("package Entities;\n");
                    bw.write("\n");
                    bw.write("import java.io.Serializable;\n");
                    bw.write("/**\n" +
                            " *\n" +
                            " * @author Julio\n" +
                            " */\n");
                    
                    bw.write("public class "+name+" implements Serializable{\n");
                    
                    
                    for (String[] variable : variables) {
                        bw.write("  private " + variable[1]+" ");
                        bw.write(variable[0]+";\n");
                    }
                    bw.write("\n");
                    
                    bw.write("  public "+name+" (");
                    int indice = 0;
                    for (String[] variable : variables) {
                        if (indice == variables.size()-1) {
                            bw.write(variable[1]+" ");
                            bw.write(variable[0]+" ");
                        }else{
                            bw.write(variable[1]+" ");
                            bw.write(variable[0]+", ");
                        }
                        indice++;
                    }
                    bw.write("){\n");
                    bw.write("\n");
                    for (String[] variable : variables) {
                        bw.write("  this."+variable[0]+" = "+variable[0]+";\n");
                    }
                    bw.write("  }\n");
                    bw.write("\n");
                    
                    for (String[] variable : variables) {
                        char[] caracteres = variable[0].toCharArray();
                        caracteres[0] = Character.toUpperCase(caracteres[0]);
                        if (variable[1].contains("boolean")) {
                            bw.write("  public "+variable[1]+" is"+String.copyValueOf(caracteres)+"(){\n" );
                            bw.write("      return this."+variable[0]+";\n");
                            bw.write("  }\n");
                            bw.write("\n");
                        }else{
                            bw.write("  public "+variable[1]+" get"+String.copyValueOf(caracteres)+"(){\n" );
                            bw.write("      return this."+variable[0]+";\n");
                            bw.write("  }\n");
                            bw.write("\n");
                        }
                        
                        bw.write("  public void set"+String.copyValueOf(caracteres)+"("+variable[1]+" "+variable[0]+"){\n" );
                        bw.write("      this."+variable[0]+" = "+variable[0]+";\n");
                        bw.write("  }\n");
                        bw.write("\n");
                        
                    }
                    bw.write("}\n");
                    bw.close();
                }
//                FileWriter fw = new FileWriter(file,true);
//                BufferedWriter bw = new BufferedWriter(fw);
//                bw.write("package Entities;");
//                bw.write("import java.io.Serializable;");
               // bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void interfaces(String name,String dao){
         
        
        try {
        String ruta = dao+name+"DAO.java";
         
           File file = new File(ruta);
            // Si el archivo no existe es creado
                if (!file.exists()) {
             
                    file.createNewFile();
                    FileWriter fw = new FileWriter(file,true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    
                    bw.write("package DAO;\n");
                    bw.write("\n");
                    bw.write("import Entities."+name+";\n\n");
                    bw.write("/**\n" +
                            " *\n" +
                            " * @author Julio\n" +
                            " */\n");
                    
                    bw.write("public interface "+name+"DAO extends DAO<"+name+",Integer> {\n\n\n");
                    bw.write("}\n");
                    
                    bw.close();
                }

             } catch (Exception ex) {
                 Logger.getLogger(Write.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    public void managerSql(String dao,String mysql){
        File file = new File(dao);
        if (file.exists()) {
          
            
            try {
                String ruta = mysql+"Manager.java";
                System.out.println(ruta);
                File manager = new File(ruta);
                manager.createNewFile();
                FileWriter fw = new FileWriter(manager,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("package MySql;\n\n");
               
               String[] list = file.list();
               
                for (String string : list) {
                    string = string.replace(".java", "");
                    bw.write("import DAO."+string+";\n");
                }
                bw.write("import java.sql.Connection;\n\n");
                
                bw.write("public class Manager  implements DAOManager{\n");
                
                for (String string : list) {
                    
                    string = string.replace(".java", "");
                    
                    bw.write("  private "+string+" "+string.toLowerCase()+";\n");
                    
                }
                bw.write("  private Connection connection;\n");
                bw.write("    public Manager() {\n" +
"        this.connection = conection.Conexion.getInstancia();\n" +
"    }\n");
                for (String string : list) {
                  string =string.replace(".java", "");
                  String temp = string.replace("DAO", "D");
                    bw.write("  @Override");
                    bw.write("  public "+string +" get"+string+"(){\n");
                    bw.write("      if( "+string.toLowerCase()+" == null ){\n");
                    
                    bw.write("          "+string.toLowerCase() +" = new  "+temp+"(connection);\n}\n");
                    bw.write("      return "+string.toLowerCase()+";\n}\n");
                   
                }
                
            bw.write("}");
                
                
                bw.close();
            } catch (Exception e) {
            }
       }
    }
    public   void crearDAOManager(String dao){
       
        File daoManager =  new File(dao);
        if (daoManager.exists()) {
            try{
           String ruta = dao+"DAOManager.java";
         
           File file = new File(ruta);
           
            // Si el archivo no existe es creado
            file.createNewFile();
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("package DAO;\n\n");
            
            bw.write("\n\n\n\n");
            String[] list = daoManager.list();
            bw.write("public interface DAOManager{\n");
            for (String string : list) {
            
               
                   string = string.replace(".java", "");
                   bw.write("   "+string+" "+"get"+string+"();");
                   
                    bw.write("\n");
                 //  bw.write("import proyectodos.Entities."+name+";\n\n");
                    
                  
                    
                    
                
            }
              bw.write("}\n");
            bw.close();
            
        } catch (Exception ex) {
            Logger.getLogger(Write.class.getName()).log(Level.SEVERE, null, ex);
        }
            
                
            
                    
       }
   }
    public void DAO(String dao) throws IOException{
        
        File daoManager =  new File(dao);
        if (daoManager.exists()) {
            try{
           String ruta = dao+"DAO.java";
         
           File file = new File(ruta);
           
            // Si el archivo no existe es creado
            file.createNewFile();
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("/*\n" +
" * To change this license header, choose License Headers in Project Properties.\n" +
" * To change this template file, choose Tools | Templates\n" +
" * and open the template in the editor.\n" +
" */\n" +
"package DAO;\n" +
"\n" +
"\n" +
"import java.util.List;\n" +
"\n" +
"/**\n" +
" *\n" +
" * @author Julio\n" +
" */\n" +
"public interface DAO<T,K> {\n" +
"    \n" +
"    public void insert(T object) ;\n" +
"    public void modify(T object);\n" +
"    public void delete(T object);\n" +
"    public T obtener(K id);\n" +
"    public List<T> obtenerTodo();\n" +
"    public K lastInsertId();\n" +
"}");
            
            bw.close();
            
        } catch (Exception ex) {
            Logger.getLogger(Write.class.getName()).log(Level.SEVERE, null, ex);
        }
            
                
            
                    
       }
        
    }
    public void crearMysql(String name,List<String[]> variables, String mysql,String dao){
         File daoManager =  new File(dao);
        if (daoManager.exists()) {
            try{
           String ruta = mysql +name+"D.java";
         
           File file = new File(ruta);
           /*


           */
            // Si el archivo no existe es creado
            file.createNewFile();
            FileWriter fw = new FileWriter(file,true);
            bw = new BufferedWriter(fw);
            bw.write("package MySql;\n");
            bw.write("import DAO."+ name+"DAO;\n");
            bw.write("import Entities."+name+";\n");
            bw.write("import java.sql.Connection;\n");
            bw.write("import java.sql.PreparedStatement;\n");
            bw.write("import java.sql.ResultSet;\n");
            bw.write("import java.sql.SQLException;\n");
            bw.write("import java.util.ArrayList;\n");
            bw.write("import java.util.List;\n");
            bw.write("import java.util.logging.Level;\n");
            bw.write("import java.util.logging.Logger;\n");
            
            bw.write("\n\n\n\n");
           
            bw.write("public class "+name+"D implements  "+name+"DAO {\n\n");
            
            
            bw.write("    private Connection connection;\n");
            bw.write("    private final String INSERT  = \"INSERT INTO " +name+" (");
            //==========================================insert========================================================
                int var = 0;
                for (String[] variable : variables) {
                    if (var==0) {
                        
                    }else
                    bw.write(variable[0]+",");
                    
                    var++;
                }
                
                bw.write(") VALUES (");
                for (int i = 1; i < variables.size(); i++) {
                    
                    if (i<variables.size()-1) {
                    bw.write("?,");    
                    }else{
                        bw.write("?)\";\n");
                    }
                    
                }
                
               //==========================================fin insert ==========================================
               //========================================== Update ==========================================
               bw.write("    private final String UPDATE  = \"UPDATE "+name+" ");
               
               int i=0;
                for (String[] variable : variables) {
                    if (i==0) {
                        
                    }else{
                       
                        
                    if (i<variables.size()-1) {
                        bw.write("set "+variable[0]+" = ?, ");
                    }else{
                         bw.write("set "+variable[0]+" = ? ");
                    }
                    }
                    i++;
                }
                bw.write("WHERE "+variables.get(0)[0]+" = ? \";\n");
               //========================================== fin Update ==========================================
               //========================================== Delete ==========================================
               
               
               //private final String DELETE  = "
                bw.write("    private final String DELETE  = \"DELETE "+name+" WHERE "+variables.get(0)[0]+" = ? \";\n");
               
               //========================================== fin delete ==========================================
               bw.write("    private final String GETALL  = \"SELECT * FROM  "+name+"  \";\n");
               bw.write("    private final String GETONE  = GETALL + \"WHERE "+variables.get(0)[0]+" = ?\";\n\n");
               /*
               public CategoriaD(Connection connection) {
        this.connection = connection;
    }
               */
               
                  //========================================== Constructor ==========================================
                  bw.write("    public "+name+"D(Connection connection) {\n" +
                            "        this.connection = connection;\n" +
                        "    }\n");
                  //========================================== metodo insertar ==========================================
                    insert(name, variables, bw);
                    //========================================== fin metodo insertar ==========================================
                  //========================================== metodo Modify ==========================================
                    modify(name, variables, bw);
                    obtenerTodo(name, variables);
                    obtener(name);
                    delete(name, variables);
                    convertir(name, variables);
                    //========================================== fin metodo modfy ==========================================
              bw.write("}\n");
            bw.close();
            
        } catch (Exception ex) {
            Logger.getLogger(Write.class.getName()).log(Level.SEVERE, null, ex);
        }                
       }
    }
    public void insert(String name,List<String[]> variables,BufferedWriter bw) throws IOException{
            
                    bw.write("  @Override\n");
                    bw.write("  public void insert("+name+" object) {\n" +
                            "       PreparedStatement stat = null;;\n" );
                    bw.write("      try {\n" +
                             "          stat = connection.prepareStatement(INSERT);\n");
                    /*
                    
                    */
                    int index = 0;
                        for (String[] variable : variables) {
                            char[] typeVar = variable[1].toCharArray();
                            typeVar[0] = Character.toUpperCase(typeVar[0]);
                            
                            char[] var = variable[0].toCharArray();
                            var[0] = Character.toUpperCase(var[0]);
                                if (index==0) {
                                }else{
                            
                                    if (variable[1].contains("Date")) {
                                        bw.write("          stat.setDate("+index+",object.get"+String.copyValueOf(var)+"());\n");
                                    }else if(variable[1].contains("boolean")){
                                        bw.write("          stat.set"+String.copyValueOf(typeVar)+"("+index+",object.is"+String.copyValueOf(var)+"());\n");
                                    }else{
                                        bw.write("          stat.set"+String.copyValueOf(typeVar)+"("+index+",object.get"+String.copyValueOf(var)+"());\n");
                                    }
                                }
                                index++;
                               
                        }
                        bw.write("          if (stat.executeUpdate() == 0) {\n" +
                                 "              System.out.println(\"crear popover "+name+"\");\n" +
                                 "                \n" +
                                 "          }\n");
                        
                    
                    bw.write("      } catch (Exception e) {\n" +
                            "           e.printStackTrace();\n" +
                            "      }\n");
                    bw.write("  }\n");
        }
    
    public void modify(String name,List<String[]> variables,BufferedWriter bw) throws IOException{
    

        System.out.println("");
          bw.write("      @Override\n");
                    bw.write("      public void modify("+name+" object) {\n" +
                            "           PreparedStatement stat = null;;\n" );
                    bw.write("          try {\n" +
                             "              stat = connection.prepareStatement(UPDATE);\n");
                    /*
                    
                    */
                    int index = 0;
                        for (String[] variable : variables) {
                            char[] typeVar = variable[1].toCharArray();
                            typeVar[0] = Character.toUpperCase(typeVar[0]);
                            
                            char[] var = variable[0].toCharArray();
                            var[0] = Character.toUpperCase(var[0]);
                                if (index==0) {
                                }else{
                            
                                    if (variable[1].contains("Date")) {
                                        bw.write("              stat.setDate("+index+",object.get"+String.copyValueOf(var)+"());\n");
                                    }else if(variable[1].contains("boolean")){
                                        bw.write("              stat.set"+String.copyValueOf(typeVar)+"("+index+",object.is"+String.copyValueOf(var)+"());\n");
                                    }else{
                                        bw.write("              stat.set"+String.copyValueOf(typeVar)+"("+index+",object.get"+String.copyValueOf(var)+"());\n");
                                    }
                                }
                                index++;
                        }
                         char[] typeVar = variables.get(0)[1].toCharArray();
                            typeVar[0] = Character.toUpperCase(typeVar[0]);
                            char[] var = variables.get(0)[0].toCharArray();
                            var[0] = Character.toUpperCase(var[0]);
                        bw.write("              stat.set"+String.copyValueOf(typeVar)+"("+(index)+", object.get"+String.copyValueOf(var)+"());\n");
                        
                        bw.write("              if (stat.executeUpdate() == 0) {\n" +
                                 "                    System.out.println(\"crear popover "+name+"\");\n" +
                                 "                \n" +
                                 "              }\n");
                        
                    
                    bw.write("       } catch (Exception e) {\n" +
                            "            e.printStackTrace();\n" +
                            "        }\n");
                    bw.write("      }\n");
    }
    
    public void delete(String name,List<String[]> variables) throws IOException{
        
         char[] typeVar = variables.get(0)[1].toCharArray();
        typeVar[0] = Character.toUpperCase(typeVar[0]);
        char[] var = variables.get(0)[0].toCharArray();
        var[0] = Character.toUpperCase(var[0]);
        bw.write("@Override\n" +
"    public void delete("+name+" object) {\n" +
"      PreparedStatement stat = null;\n" +
"        try {\n" +
"            stat = connection.prepareStatement(DELETE);\n" +
"            stat.set"+String.copyValueOf(typeVar)+"(1, object.get"+String.copyValueOf(var)+"());\n" +
"            if (stat.executeUpdate()== 0 ) {\n" +
"                \n" +
"            }\n" +
"        } catch (SQLException ex) {\n" +
"            Logger.getLogger("+name+"D.class.getName()).log(Level.SEVERE, null, ex);\n" +
"        }\n" +
"    }\n");
    }
    
    public void obtenerTodo(String name, List<String[]> variables) throws IOException{
        bw.write(" @Override\n" +
"    public List<"+name+"> obtenerTodo() {\n" +
"        PreparedStatement stat = null;\n" +
"        ResultSet rs = null;\n" +
"        List<"+name+"> lst = new ArrayList<>();\n" +
"        try {\n" +
"            stat = connection.prepareStatement(GETALL);\n" +
"            rs = stat.executeQuery();\n" +
"           while (rs.next()) {\n" +
"                lst.add(convertir(rs));\n" +
"            }\n" +
"        return lst;\n" +
"        } catch (Exception e) {\n" +
"            e.printStackTrace();\n" +
"        }\n" +
"        \n" +
"     return null;\n" +
"    }\n");
    }
    public void obtener(String name) throws IOException{
        bw.write("@Override\n" +
"    public "+name+" obtener(Integer id) {\n" +
"        PreparedStatement stat = null;\n" +
"        ResultSet rs = null;\n" +
"       \n" +
"        try {\n" +
"            stat = connection.prepareStatement(GETONE);\n" +
"            stat.setInt(1,id);\n" +
"            rs = stat.executeQuery();\n" +
"           while (rs.next()) {\n" +
"                return (convertir(rs));\n" +
"            }\n" +
"           \n" +
"        } catch (Exception e) {\n" +
"            e.printStackTrace();\n" +
"        }\n" +
"           return null;\n" +
"    }\n");
    }
    public void convertir(String name, List<String[]> variables) throws IOException{
        char[] var = name.toCharArray();
        var[0] = Character.toLowerCase(var[0]);
        
        bw.write("public "+name+" convertir(ResultSet rs){\n" +
"        \n" +
"        try {\n" +
"            "+name+" "+String.copyValueOf(var)+" = new "+name+"(");
        
//        + "Integer.parseInt(rs.getString(\"idCategoria\")), rs.getString(\"categoria\"), Boolean.parseBoolean(rs.getString(\"estado\")));\n" +
//"            \n" +
    int i = 0;
        for (String[] variable : variables) {
              char[] typeVar = variable[1].toCharArray();
            typeVar[0] = Character.toUpperCase(typeVar[0]);
           
            if (i== variables.size()-1) {
            
                if (variable[1].contains("Date")) {
                    bw.write("rs.getDate(\""+variable[0]+"\"));\n\n");
                }else{
                    bw.write("rs.get"+String.copyValueOf(typeVar)+"(\""+variable[0]+"\"));\n\n");   
                    
                }
            
            }else{
              
                if (variable[1].contains("Date")) {
                    bw.write("rs.getDate(\""+variable[0]+"\"),");
                }else{
                    bw.write("rs.get"+String.copyValueOf(typeVar)+"(\""+variable[0]+"\"),");
                }  
            }
                
   
            
            i++;
        }

bw.write("            return "+String.copyValueOf(var)+";\n" +
"        } catch (SQLException ex) {\n" +
"            Logger.getLogger("+name+"D.class.getName()).log(Level.SEVERE, null, ex);\n" +
"        }\n" +
"        return null;\n" +
"    }\n");
bw.write(" @Override\n" +
"    public Integer lastInsertId() {\n" +
"        String ultimo = \"SELECT last_insert_id()\";\n" +
"        PreparedStatement stat = null;\n" +
"        ResultSet rs = null;\n" +
"        \n" +
"        try {\n" +
"            stat = connection.prepareStatement(ultimo);\n" +
"            rs = stat.executeQuery();\n" +
"            if (rs.next()) {\n" +
"                return rs.getInt(1);    \n" +
"            }\n" +
"            \n" +
"        } catch (SQLException ex) {\n" +
"            Logger.getLogger("+name+"D.class.getName()).log(Level.SEVERE, null, ex);\n" +
"        }\n" +
"        return 0;\n" +
"    }\n");
    }
        
    }

  

