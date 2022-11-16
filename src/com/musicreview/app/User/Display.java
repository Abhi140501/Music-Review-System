package com.musicreview.app.User;

import com.musicreview.app.Connector;
import com.musicreview.app.login.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Display extends Frame {
    private List list;
    private Button logout;
    private void populatemusic(List list, String username) throws SQLException {
        ArrayList<Integer> names = new ArrayList<Integer>(); // works fine
        Connector con = new Connector();
        Statement statement = con.connection.createStatement();
        String sql = "select songname,songid from songs";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            names.add(rs.getInt(2));
        }
        for(int i = 0; i < names.size(); i++) {
            sql = "SELECT songname from songs WHERE songid="+names.get(i);
            rs = statement.executeQuery(sql);
            rs.next();
            String name = rs.getString(1);
            sql = "SELECT rating from rating WHERE songid="+names.get(i);
            rs = statement.executeQuery(sql);
            if(rs.next()) {
                int rating = rs.getInt(1);
                list.add(name+" : "+rating);
            } else {
                list.add(name+" : Not Rated");
            }
        }
    }
    public Display(String username) throws SQLException{
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setLayout(null);
        logout=new Button("LOG OFF");
        logout.setBounds(230,400,100,25);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login();
            }
        });
        list=new List(0,false);
        populatemusic(list,username);
        list.setBounds(50,50,400,300);
        add(list);
        add(logout);
        setSize(500,500);
        setVisible(true);
    }
}
