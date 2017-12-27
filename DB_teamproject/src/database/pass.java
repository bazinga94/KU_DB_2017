
package database;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;


public class pass extends JFrame implements ActionListener{

    //Initializing Components
    JLabel lb5;
    JTextField tf5;
    JButton btn;

    //Creating Constructor for initializing JFrame components
    public pass() {
        setLayout(null);

        //Providing Title
        lb5 = new JLabel("Enter the password:");
        lb5.setBounds(20, 20, 100, 20);
        tf5 = new JTextField(20);
        tf5.setBounds(130, 20, 200, 20);

        btn = new JButton("Enter");
        btn.setBounds(180, 50, 100, 20);
        btn.addActionListener(this);

//        btn1 = new JButton("Back");
//        btn1.setBounds(370,230,100,20);


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);

        add(lb5);
        add(tf5);
        add(btn);
    }
    public void actionPerformed(ActionEvent e){
            String str = tf5.getText();
            if(str.equals("1234")){
                new database.Member_List();
            }
    }
    public static void main(String args[]){
        new pass();
    }
}