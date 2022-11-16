package com.musicreview.app.User;

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

public class Rating extends Frame {
    private List list;
    private Button addrate;
    private TextField addrating;
    private Button display;
    private void populatemusic(List list, String username) throws SQLException {
        Connector con = new Connector();
        Statement statement = con.connection.createStatement();
        String sql = "select songname from songs";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(rs.getString(1));
        }
    }

    public Rating(String username) throws SQLException {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setLayout(null);
        addrating=new TextField();
        addrating.setBounds(50,400,150,25);
        addrate=new Button("Rate");
        addrate.setBounds(230,400,100,25);
        addrate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String select=list.getSelectedItem();
                String addr=addrating.getText();
                Connector con = null;
                try {
                    con = new Connector();
                    Statement statement=con.connection.createStatement();
                    String sql="select songid from songs where songname='"+select+"'";
                    ResultSet rs= statement.executeQuery(sql);
                    rs.next();
                    int id=rs.getInt(1);
                    sql="insert into rating (rating,songid) values ('"+addr+"',"+id+")";
                    statement.execute(sql);
                    JOptionPane.showMessageDialog(null,"Success");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        display=new Button("Display");
        display.setBounds(350,400,100,25);
        display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    new Display(username);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        list=new List(0,false);
        populatemusic(list,username);
        list.setBounds(50,50,400,300);
        add(addrating);
        add(addrate);
        add(list);
        add(display);
        setSize(500,500);
        setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        new Rating("Abhiancreed");
    }
}
