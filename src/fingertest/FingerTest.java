/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingertest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Khouiled
 */
public class FingerTest {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    static String st="";
    static String st2="";
           static int lx=10;
           static int ly=38;
           static int errs=0;
           static int inx=0;
           static long speed=0;
           static int words=0;
       static LinkedList<Integer> wrs1=new LinkedList<>();
       static LinkedList<Integer> wrs2=new LinkedList<>();
           static  long time=0;
    public static void main(String[] args) throws FileNotFoundException, Exception {
        System.out.println(System.nanoTime());
        Thread.sleep(1000);
                
        System.out.println(System.nanoTime());
        String dpath=System.getProperty("user.dir")+"\\words.txt";
        FileReader fw=new  FileReader(dpath);
    //    System.out.println((int)'a'+"  "+(int)'z'+"  "+(int)'A'+"   "+(int)'Z');
        LinkedList<String> ss=new LinkedList<>();
        String s="";
        
        for(;fw.ready();){
            char c=(char) fw.read();
            if((c>97&&c<122)||(c>65&&c<90)){
            s+=c;
            }else{
            if(!s.equals("")){
                ss.add(s);
                
                s="";
            }
            }
            
        }
        
        JFrame f =new JFrame("Finger Test");
        f.setDefaultCloseOperation(3);
        f.setUndecorated(true);
        f.setBounds(10, 10, 1100, 400);
        Random r=new Random();
        f.setFont(new Font("Monospaced", 0, 12));
        
                
            for(int i=0;i<20;i++){
            if(i>10){
              st2+=ss.get(r.nextInt(ss.size())).toLowerCase()+" ";
              words++;
            }else{
                st+=ss.get(r.nextInt(ss.size())).toLowerCase()+" ";
            words++;
            }
            }
        JPanel p=new JPanel(){
            @Override
            public Font getFont() {
                return       new Font("Monospaced", 1, 18);
        }
            
            @Override
            public void paint(Graphics g) {
        
            g.setColor(Color.red);
                
                int x=10;
                int y=50;
                wrs1.forEach((intt) -> {
                    g.fillRect(10+intt*11, 38, 11, 12);
                });
                wrs2.forEach((intt) -> {
                    g.fillRect(10+intt*11, 53, 11, 12);
                });
                      g.setColor(new Color(0, 200, 0, 200));
            g.fillRect(lx, ly, 11, 12);
           
            g.setColor(Color.black);
                
                g.drawString(st, x, y);
                g.drawString(st2, x, y+15);
            g.setColor(Color.red);
            g.drawString("Errors "+errs+"  Speed "+speed, 5, 15);
            int yy=80;
            
                  
            }
            
        };
        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
boolean er=false;

int oinx=0;
            @Override
            public void keyPressed(KeyEvent e) {
                
            if((e.getKeyChar()+"".toLowerCase()).equals((st+""+st2).charAt(inx)+"")){
                 if(time==0){
                     time=System.nanoTime();
                 }
                inx++;
                lx+=11;
            }else{
                errs++;
                if(er){
                    wrs2.add(inx-oinx);
                }else{
                    wrs1.add(inx);                }
            }
            if(inx==st.length()){
                lx=10;
                ly=53;
                oinx=inx;
                er=true;
            }
            if(inx==(st+st2).length()){
                double tmp=(double)21/(double)((double)((double)System.nanoTime()-(double)time)/(double)1000000000)*(double)60;
                speed=(long) tmp;
                time=0;
                
                oinx=0;
                er=false;
                wrs1.clear();
                wrs2.clear();
                lx=10;
                ly=38;
                inx=0;
                st="";
                st2="";
                for(int i=0;i<20;i++){
            if(i>10){
              st2+=ss.get(r.nextInt(ss.size())).toLowerCase()+" ";
            words++;
            }else{
                st+=ss.get(r.nextInt(ss.size())).toLowerCase()+" ";
            words++;
            }
            }
                errs=0;
            }
            
            f.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        f.add(p);
        f.setVisible(true);
    }
    
}
