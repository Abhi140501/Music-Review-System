package com.musicreview.app.login;
import com.musicreview.app.Connector;
import com.musicreview.app.Developer.Music;
import com.musicreview.app.User.Rating;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login extends Frame{
    private Label username;
    private Label password;
    private Button login;
    private Button signup;
    private TextField user;
    private JPasswordField pass;
    private Label error;
    public  Login(){
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
        error = new Label();
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usr = user.getText();
                String pswd = String.valueOf(pass.getPassword());
                try {
                    Connector con = new Connector();
                    Statement statement=con.connection.createStatement();
                    String sql="select * from developer where username='"+usr+"' and password='"+pswd+"'";
                    ResultSet rs=statement.executeQuery(sql);
                    if(rs.next()){
                        setVisible(false);
                        new Music(rs.getString(1));
                    }
                    else{
                        sql="select * from user where username='"+usr+"' and password='"+pswd+"'";
                        rs=statement.executeQuery(sql);
                        if (rs.next()) {
                            setVisible(false);
                            new Rating(rs.getString(1));

                        }
                        else{
                            error.setText("check username/password");
                        }
                    }

                    }

                 catch (SQLException ex) {
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
        signup = new Button("Signup");
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Signup();
            }
        });
        user = new TextField(null);
        pass = new JPasswordField(null);
        username.setBounds(50,100,100,50);
        password.setBounds(50,150,100,50);
        user.setBounds(150,110,150,25);
        pass.setBounds(150,165,150,25);
        login.setBounds(170,220,100,25);
        signup.setBounds(170,270,100,25);
        error.setBounds(170,190,100,25);
        error.setForeground(Color.red);
        add(username);
        add(user);
        add(password);
        add(pass);
        add(login);
        add(signup);
        add(error);
        setTitle("LOGIN");
        setSize(500,500);
        setVisible(true);

    }
}
