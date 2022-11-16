package com.musicreview.app.login;

import com.musicreview.app.Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Statement;

public class Signup extends Frame {
    private Label username;
    private Label password;
    private Button login;
    private Button signup;
    private TextField user;
    private JPasswordField pass;
    private Label error;
    public  Signup(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setLayout(null);
        username = new Label("Username:");
        password = new Label("Password:" );
        login = new Button("Login");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login();
            }
        });
        signup = new Button("Signup");
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usr=user.getText();
                String pswd= String.valueOf(pass.getPassword());

                try {
                    Connector con = new Connector();
                    Statement statement=con.connection.createStatement();
                    String sql="insert into user values('"+usr+"','"+pswd+"')";
                    statement.execute(sql);
                    int input= JOptionPane.showOptionDialog(null,"signup success","SUCCESS",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
                    if (input==JOptionPane.OK_OPTION){
                        setVisible(false);
                        new Login();
                    }

                } catch (SQLException ex) {
                    error.setText(ex.getMessage());
                    if(ex.getErrorCode()==0){
                        error.setText("Cant connect to database");
                    }
                    else if(ex.getErrorCode()==1062){
                        error.setText(("user name already exist"));
                    }
                    else{
                        error.setText(String.valueOf(ex.getErrorCode()));
                    }
                    ex.printStackTrace();
                }


            }
        });
        user = new TextField(null);
        pass = new JPasswordField(null);
        error= new Label(null);
        username.setBounds(50,100,100,50);
        password.setBounds(50,150,100,50);
        user.setBounds(150,110,150,25);
        pass.setBounds(150,165,150,25);
        signup.setBounds(170,220,100,25);
        login.setBounds(170,270,100,25);
        error.setBounds(170,190,100,25);
        error.setForeground(Color.red);
        add(username);
        add(user);
        add(password);
        add(pass);
        add(error);
        add(login);
        add(signup);
        setTitle("SIGNUP");
        setSize(500,500);
        setVisible(true);

    }


}
