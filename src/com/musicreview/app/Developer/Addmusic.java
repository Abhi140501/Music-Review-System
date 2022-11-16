package com.musicreview.app.Developer;

import com.musicreview.app.Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Statement;

public class Addmusic extends Frame{
    private TextField songname;
    private TextField singer;
    private TextField genre;
    private Button addButton;
    private Button musiclist;
    private Label songnamelabel;
    private Label singerlabel;
    private Label genrelabel;

    public Addmusic(String username){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setLayout(null);
        songname=new TextField();
        songname.setBounds(175,50,150,25);
        singer=new TextField();
        singer.setBounds(175,75,150,25);
        genre=new TextField();
        genre.setBounds(175,100,150,25);
        addButton=new Button("Add");
        addButton.setBounds(100,170,75,25);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=songname.getText();
                String singername=singer.getText();
                String gen=genre.getText();
                try {
                    Connector con= new Connector();
                    Statement statement=con.connection.createStatement();
                    String sql="insert into songs (songname,singer,gender,username) values ('"+name+"','"+singername+"','"+gen+"','"+username+"')";
                    statement.execute(sql);
                    JOptionPane.showMessageDialog(null,"Success");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        musiclist=new Button("List");
        musiclist.setBounds(200,170,75,25);
        musiclist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    new Music(username);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        songnamelabel=new Label("Song name");
        songnamelabel.setBounds(100,50,150,25);
        singerlabel=new Label("Singer name");
        singerlabel.setBounds(100,75,150,25);
        genrelabel=new Label("Genre");
        genrelabel.setBounds(100,100,150,25);
        add(songname);
        add(singer);
        add(genre);
        add(addButton);
        add(musiclist);
        add(songnamelabel);
        add(singerlabel);
        add(genrelabel);
        setVisible(true);
        setSize(500,500);


    }

}
