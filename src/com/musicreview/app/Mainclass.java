package com.musicreview.app;
import com.musicreview.app.login.Login;
import com.musicreview.app.login.Signup;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Mainclass extends Frame {
    private Button login;
    private Button signup;

     public Mainclass() {
         addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent e) {
                 System.exit(0);
             }
         });




         setLayout(null);
         login = new Button("login");

         login.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 setVisible(false);
                 new Login();

             }
         });

         login.setBounds(100,250,100,50);
         signup = new Button("signup");
         signup.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 setVisible(false);
                 new Signup();
             }
         });
         signup.setBounds(300,250,100,50);
         setTitle("MUSIC REVIEW");
         add(login);
         add(signup);
         setSize(500,500);
         setVisible(true);

     }

    public static void main(String[] args) {
        new Mainclass();
    }

}