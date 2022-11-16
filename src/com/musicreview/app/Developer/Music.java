package com.musicreview.app.Developer;

import com.musicreview.app.Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Music extends Frame {
    private List list;
    private Button delete;
    private Button addB;
    private void populatemusic(List list,String username) throws SQLException{
        Connector con=new Connector();
        Statement statement= con.connection.createStatement();
        String sql="select songname from songs where username='"+username+"'";
        ResultSet rs=statement.executeQuery(sql);
        while(rs.next()){
            list.add(rs.getString(1));
        }

    }

    public Music(String username) throws SQLException {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setLayout(null);
        list=new List(0,false);
        populatemusic(list,username);
        list.setBounds(50,50,400,300);
        delete = new Button("Delete");
        delete.setBounds(200,375,75,50);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String select=list.getSelectedItem();
                Connector con= null;
                try {
                    con = new Connector();
                    Statement statement=con.connection.createStatement();
                    String sql="delete from songs where songname='"+select+"'";
                    statement.execute(sql);
                    setVisible(false);
                    new Music(username);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        addB = new Button("Add");
        addB.setBounds(300,375,75,50);
        add(list);

        add(delete);
        add(addB);
        addB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new Addmusic(username);
            }
        });
        setSize(500,500);
        setVisible(true);


    }

}
