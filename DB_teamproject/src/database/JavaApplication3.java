package database;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author woochan
 */


class info { 
    static String cus_id;
    static String cus_age;
	static String screen_date ; // 카드의 무늬 - 인스턴스 변수 
    static String thea_name; // 카드의 숫자 - 인스턴스 변수 
    static String ticket_no;
    static int amount = 100;
} 

class JPanel00 extends JPanel{
    JButton jbut1,jbut2;
    JLabel lb;
    JavaApplication3 win;

    public JPanel00(JavaApplication3 win){
        this.win = win;
        setLayout(null);


        lb = new JLabel("Movie booking management program");
        lb.setBounds(20,20,400,30);
        lb.setForeground(Color.blue);
        lb.setFont(new Font("Serif", Font.BOLD, 20));
        setVisible(true);

        jbut1 = new JButton("1.Customer Infromation Tab");
        jbut1.setBounds(20,70,200,30);


        jbut2 = new JButton("2.Go to booking           ");
        jbut2.setBounds(20,120,200,30);

        setVisible(true);
        setSize(500, 500);

        add(lb);
        add(jbut1);
        add(jbut2);

        jbut1.addActionListener(new MyActionListener1());
        jbut2.addActionListener(new MyActionListener2());
    }
    class MyActionListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            win.change("panel01");
            }
        }
    class MyActionListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == jbut2) {
                new database.NewJFrame();
            }
        }
    }
}
    

class JPanel01 extends JPanel{
    JButton jbut1,jbut2,jbut3;
    JLabel lb;
    JavaApplication3 win;

    public JPanel01(JavaApplication3 win){
        this.win = win;
        setLayout(null);


        lb = new JLabel("Customer information tab, Please select menu");
        lb.setBounds(20,20,400,30);
        lb.setForeground(Color.blue);
        lb.setFont(new Font("Serif", Font.BOLD, 20));
        setVisible(true);

        jbut1 = new JButton("1.Administrator      ");
        jbut1.setBounds(20,70,200,30);


        jbut2 = new JButton("2.Booking information");
        jbut2.setBounds(20,120,200,30);


        jbut3 = new JButton("3.Sign up            ");
        jbut3.setBounds(20,170,200,30);

        setVisible(true);
        setSize(500, 500);

        add(lb);
        add(jbut1);
        add(jbut2);
        add(jbut3);

        jbut1.addActionListener(new MyActionListener3());
        jbut2.addActionListener(new MyActionListener4());
        jbut3.addActionListener(new MyActionListener5());
    }
    class MyActionListener3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == jbut1) {
                new database.pass();
            }
        }
    }
    class MyActionListener4 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            win.change("panel02");
        }
    }
    class MyActionListener5 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == jbut3){
                new database.MemberProc();
            }
        }
    }
}


class JPanel02 extends JPanel {

    //Initializing Components
    JLabel lb, lb1, lb2, lb3, lb4, lb5,lb6;
    JTextField tf1, tf2, tf3, tf4, tf5,tf6;
    JButton btn,btn1;
    private JavaApplication3 win;

    //Creating Constructor for initializing JFrame components
    public JPanel02(JavaApplication3 win) {
        setLayout(null);
        this.win = win;

        //Providing Title
        lb5 = new JLabel("Enter yout ID:");
        lb5.setBounds(20, 20, 100, 20);
        tf5 = new JTextField(20);
        tf5.setBounds(130, 20, 200, 20);

        lb6 = new JLabel("Enter yout ticket_no:");
        lb6.setBounds(20, 50, 100, 20);
        tf6 = new JTextField(20);
        tf6.setBounds(130, 50, 200, 20);

        btn = new JButton("Search");
        btn.setBounds(180, 80, 100, 20);
        btn.addActionListener(new MyActionListener());

        btn1 = new JButton("Back");
        btn1.setBounds(370,430,100,20);

        lb = new JLabel("Here is your booking information");
        lb.setBounds(30, 110, 450, 30);
        lb.setForeground(Color.blue);
        lb.setFont(new Font("Serif", Font.BOLD, 20));
        setVisible(true);
        setSize(500, 500);

        lb1 = new JLabel("Theater:");
        lb1.setBounds(20, 150, 100, 20);
        tf1 = new JTextField(50);
        tf1.setBounds(130, 150, 200, 20);
        lb2 = new JLabel("Date:");
        lb2.setBounds(20, 180, 100, 20);
        tf2 = new JTextField(100);
        tf2.setBounds(130, 180, 200, 20);
        lb3 = new JLabel("Start time:");
        lb3.setBounds(20, 210, 100, 20);
        tf3 = new JTextField(50);
        tf3.setBounds(130, 210, 200, 20);
        lb4 = new JLabel("Movie Name:");
        lb4.setBounds(20, 240, 100, 20);
        tf4 = new JTextField(50);
        tf4.setBounds(130, 240, 200, 20);


        //Add components to the JFrame
        add(lb5);
        add(tf5);
        add(lb6);
        add(tf6);
        add(btn);
        add(btn1);

        add(lb);
        add(lb1);
        add(lb2);
        add(lb3);
        add(lb4);

        add(tf1);
        add(tf2);
        add(tf3);
        add(tf4);
        btn1.addActionListener(new MyActionListener1());

        //Set TextField Editable False
        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(false);
        tf4.setEditable(false);
    }

    class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try {
                String str = tf5.getText();
                String str1 = tf6.getText();
                String dburl = "jdbc:mysql://localhost/database_term2";

                Connection con = DriverManager.getConnection(dburl, "root", "ynwaljh940!");
                PreparedStatement st = con.prepareStatement("select * from booking where customer_id=? && ticket_no=?;");
                st.setString(1,str);
                st.setString(2,str1);
                String scid = null;
                String tino = null;
                String moid = null;

                //Excuting Query
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    String cuid = rs.getString(1); // cu_id
                    scid = rs.getString(2); // sc_id
                    tino = rs.getString(3); // t_no
                } else {
                    JOptionPane.showMessageDialog(null, "Name not Found");
                }

                PreparedStatement st1 = con.prepareStatement("select * from screen where screen_id=?");
                st1.setString(1,scid );

                ResultSet rs1 = st1.executeQuery();
                if (rs1.next()){
                    String tname = rs1.getString(4); // 숫자는 극장 이름 나오는 열로
                    moid = rs1.getString(5); // 무비 아이디 나오는 열로
                    String stime = rs1.getString(3); // date 타임 나오는 열로
                    tino = rs1.getString(1); // start 타임 나오는 열로

                    tf3.setText(tino);
                    tf1.setText(tname);
                    tf2.setText(stime);
                    PreparedStatement st2 = con.prepareStatement("select * from movie where movie_id=?");
                    st2.setString(1,moid );

                    ResultSet rs2 = st2.executeQuery();
                    if (rs2.next()){
                        String mname = rs2.getString(2);
                        tf4.setText(mname);
                    }
                }
                //Create Exception Handler
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    class MyActionListener1 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            win.change("panel01");
        }
    }
}
    


class JavaApplication3 extends JFrame{
	public JPanel00 jpanel00 = null;
    public JPanel01 jpanel01 = null;
    public JPanel02 jpanel02 = null;
    //public JPanel03 jpanel03 = null;
    //public JPanel04 jpanel04 = null;

    public void change(String panelName){

        if(panelName.equals("panel01")){
            getContentPane().removeAll();
            getContentPane().add(jpanel01);
            revalidate();
            repaint();
        }else if(panelName.equals("panel02")){
            getContentPane().removeAll();
            getContentPane().add(jpanel02);
            revalidate();
            repaint();
        }/*else if(panelName.equals("panel03")){
            getContentPane().removeAll();
            getContentPane().add(jpanel03);
            revalidate();
            repaint();
        }else{
            getContentPane().removeAll();
            getContentPane().add(jpanel04);
            revalidate();
            repaint();
        }*/
    }

    public static void main(String[] args){
        JavaApplication3 win = new JavaApplication3();

        win.setTitle("Customer Information");
        win.jpanel00 = new JPanel00(win);
        win.jpanel01 = new JPanel01(win);
        win.jpanel02 = new JPanel02(win);
        //win.jpanel03 = new JPanel03(win);
        //win.jpanel04 = new JPanel04(win);

        win.add(win.jpanel00);
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        win.setSize(500,500);
        win.setVisible(true);

    }
}



