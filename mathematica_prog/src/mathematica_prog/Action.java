package mathematica_prog;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author amine gasa
 */
public class Action {
    String solveSeconDegreeEqu(int a, int b ,int c){
        String s="";
        if (a == 0) {
            s = "the result is :" + -c / b;

        }
        else{
            float delta= (float) (Math.pow(b, 2)-4*a*c);s="delta is :"+delta;
            if(delta>0){
                s=s+" the result is X1="+(-b-Math.sqrt(delta))/2*a+" X2="
                        + (-b+Math.sqrt(delta))/2*a;
            } else if(delta==0){
                 s=s+" the result is X1=X2="+-b/2*a;
            }else{
                 s=s+" no solution";
            }
   }
    
    
    
    return s;}
    
    String commandLine(String cmd){
        String s="";
      try{  Process p=Runtime.getRuntime().exec(cmd);
      String s1;
        BufferedReader input=new BufferedReader(new InputStreamReader(p.getInputStream()));
      while((s1=input.readLine())!=null){
          s=s+s1+"\n";
      }
      }
      catch (Exception e){
          
      }
        
    return s;}
    
    String getGlobal_ip(){
        BufferedReader input=null;
        String s="";
        String url="http://ipv4bot.whatismyipaddress.com";
        try {
            input = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            s=input.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
 }
    void takeScreenShot(){
        Rectangle rect=new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        try {
            BufferedImage capture =new Robot().createScreenCapture(rect);
            try {
                ImageIO.write(capture, "png", new File("ScreenShot.png"));
                
            } catch (IOException ex) {
                Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (AWTException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    void delete_file(){
    File file=new File("screenShot.png");file.delete();
}
}
