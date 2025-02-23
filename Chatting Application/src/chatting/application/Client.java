package chatting.application;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;//for color class
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Client implements ActionListener{
    
    JTextField text;
    static JPanel textarea;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    
    Client(){ //to creat the window
        f.setLayout(null); //null because I want to creat my own layout
        
        
        JPanel up=new JPanel();
        up.setBackground(new Color(128, 0, 128)); //purple
        up.setBounds(0,0,450,70);
        up.setLayout(null);
        f.add(up);
        
        //for the back button
        ImageIcon back=new ImageIcon(ClassLoader.getSystemResource("icons/back.png")); //image location
        Image b1 = back.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);  //image frame
        ImageIcon b2 = new ImageIcon(b1);
        JLabel Back = new JLabel(b2);
        Back.setBounds(5,20,25,25);
        up.add(Back);
        
        
        Back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae)
            {
                System.exit(0);
            }
        });
        
        //for dp of client
        ImageIcon serverpp=new ImageIcon(ClassLoader.getSystemResource("icons/clientpp.jpeg")); //image location
        Image spp1 = serverpp.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);  //image frame
        ImageIcon spp2 = new ImageIcon(spp1);
        JLabel ServerPp = new JLabel(spp2);
        ServerPp.setBounds(40,10,50,50);
        up.add(ServerPp);
        
        ServerPp.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae)
            {
                JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon( 
                        serverpp.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH))), "Enlarged Image",JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        //for video icon
        ImageIcon video=new ImageIcon(ClassLoader.getSystemResource("icons/video.png")); //image location
        Image v1 = video.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);  //image frame
        ImageIcon v2 = new ImageIcon(v1);
        JLabel Video = new JLabel(v2);
        Video.setBounds(300,20,30,30);
        up.add(Video);
        
        Video.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent ae) {
                JOptionPane.showMessageDialog(null, "Service not available yet", "Sorry!! :(", JOptionPane.INFORMATION_MESSAGE); }
        });
        
        //for phone call icon
        ImageIcon call=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png")); //image location
        Image c1 = call.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);  //image frame
        ImageIcon c2 = new ImageIcon(c1);
        JLabel Call = new JLabel(c2);
        Call.setBounds(360,20,30,30);
        up.add(Call);
        
        Call.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent ae) {
                JOptionPane.showMessageDialog(null, "Service not available yet", "Sorry!! :(", JOptionPane.INFORMATION_MESSAGE); }
        });
        
        //for video call icon
        ImageIcon dots=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png")); //image location
        Image d1 = dots.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);  //image frame
        ImageIcon d2 = new ImageIcon(d1);
        JLabel Dots = new JLabel(d2);
        Dots.setBounds(410,20,10,25);
        up.add(Dots);
        
        //for block pop up
        JPopupMenu popupMenu = new JPopupMenu(); 
        JMenuItem blockItem = new JMenuItem("Block"); 
        blockItem.setFont(new Font("TIMES NEW ROMAN", Font.ITALIC|Font.BOLD, 14));
        popupMenu.add(blockItem); 
        Dots.addMouseListener(new MouseAdapter() { 
            @Override public void mouseClicked(MouseEvent e) { 
                popupMenu.show(Dots, e.getX(), e.getY()); } 
        });
        
        
        //for client name
        JLabel name = new JLabel("Sayveer");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("TIMES NEW ROMAN", Font.BOLD,18));
        up.add(name);
        
        JLabel active = new JLabel("Active Now");
        active.setBounds(110,35,100,18);
        active.setForeground(Color.GREEN);
        active.setFont(new Font("TIMES NEW ROMAN", Font.ITALIC,14));
        up.add(active);
        
        textarea = new JPanel();
        textarea.setBounds(5, 75, 440, 570);
        f.add(textarea);
        
        text=new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN,16));
        f.add(text);
        
        JButton send = new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(128,0,128));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        f.add(send);
        
        
        
        f.setSize(450, 700); //size of window
        f.setLocation(800,50); //position of window
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true); //to make the window visible
    }
    
    
    public void actionPerformed(ActionEvent ae){
        try {
            String out=text.getText();
        
        
        JPanel p2 = formatLabel(out);
        
        textarea.setLayout(new BorderLayout());
        
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        
        
        textarea.add(vertical, BorderLayout.PAGE_START);
        
        
        dout.writeUTF(out);
        
        text.setText("");
        
        f.repaint();
        f.invalidate();
        f.validate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static JPanel formatLabel(String out){
        JPanel panel =new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN,16));
        output.setBackground(new Color(218, 112, 214));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        
        return panel;
    }
    
    public static void main(String[] args)
    {
        new Client();
        
        try {
            Socket s = new Socket("127.0.0.1", 1930);
            DataInputStream din =  new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
            while(true){
                    textarea.setLayout(new BorderLayout());
                    String msg = din.readUTF();
                    JPanel panel = formatLabel(msg);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    
                    vertical.add(Box.createVerticalStrut(15));
                    textarea.add(vertical,BorderLayout.PAGE_START);
                    
                    f.validate();
                }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
