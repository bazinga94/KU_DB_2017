package database;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;



public class DB_frame extends JFrame implements MouseListener,ActionListener{
	   
    Vector v;  
    Vector cols;
    DefaultTableModel model;
    JTable jTable;
    JScrollPane pane;
    JPanel pbtn;
    JButton btnInsert;
       
    thea th = new thea();
    
    String cus_id = info.cus_id;
    String screen_date = info.screen_date;
    String thea_name = info.thea_name;
    //String ticket_no = info.ticket_no;
    
    //int amount = info.amount;
    int t = Integer.parseInt(screen_date)+info.amount;
    String ticket_no = Integer.toString(t);

    //String cus_id;
    //String screen_date;
    //String thea_name;
   
    public DB_frame(){
        super("��ȭ ���� ���� ���α׷�");
        //v=getMemberList();
        //MemberDAO
        DB_Mysql mydb = new DB_Mysql();
        
        System.out.println(thea_name);  //thea_name�� �� �޾ƿ�
        
        v = mydb.getMemberList();
        //System.out.println("v="+v);
        cols = getColumn();
       
        
       
        model = new DefaultTableModel(v, cols);
       
        jTable = new JTable(model);
        pane = new JScrollPane(jTable);
        add(pane);
       
        pbtn = new JPanel();
        btnInsert = new JButton("�����ϱ�");
        pbtn.add(btnInsert);
        add(pbtn,BorderLayout.NORTH);
       
       
        jTable.addMouseListener(this); //������ ���
        btnInsert.addActionListener(this); //ȸ�����Թ�ư ������ ���
       
        setSize(600,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end ������
   
   
    //JTable�� �÷�
    public Vector getColumn(){
        Vector col = new Vector();
        col.add("��ȭ �̸�");
        col.add("�� ����");
        col.add("�� ����");
        col.add("�󿵰�");
        col.add("����");
        col.add("screen_id");
       
       
        return col;
    }//getColumn
    
   
    //Jtable ���� ���� �޼���
    public void jTableRefresh(){
       
    	DB_Mysql mydb = new DB_Mysql();
        DefaultTableModel model= new DefaultTableModel(mydb.getMemberList(), getColumn());
        jTable.setModel(model);    
       
    }
   
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // mouseClicked �� ���
        int r = jTable.getSelectedRow();
        String id = (String) jTable.getValueAt(r, 0);
        //System.out.println("id="+id);
       // MemberProc mem = new MemberProc(id,this); //���̵� ���ڷ� ����â ����
               
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //�����ϱ� ��ư�� Ŭ���ϸ�
    	info.amount = info.amount + 1000; //Ƽ�ϳѹ��� ���̸� �α����� amount
    	int row = jTable.getSelectedRow(); //���̺��� ������ ���� ��ȣ�� �ҷ����� ����

    	Connection conn = null;                                        // null�� �ʱ�ȭ �Ѵ�.
    	PreparedStatement pstmt = null;

    	try{

    	String url = "jdbc:mysql://localhost/database_term2";        // ����Ϸ��� �����ͺ��̽����� ������ URL ���
    	String id = "root";                                                    // ����� ����
    	String pw = "ynwaljh940!";                                                // ����� ������ �н�����

    	Class.forName("com.mysql.jdbc.Driver");                       // �����ͺ��̽��� �����ϱ� ���� DriverManager�� ����Ѵ�.
    	conn=DriverManager.getConnection(url,id,pw);              // DriverManager ��ü�κ��� Connection ��ü�� ���´�.

    	String sql = "insert into booking (customer_id, screen_id,ticket_no) values ('"+info.cus_id+"',?,?)";        // sql ����
    	pstmt = conn.prepareStatement(sql);                          // prepareStatement���� �ش� sql�� �̸� �������Ѵ�.

    	pstmt.setString(1,(String)jTable.getValueAt(row,5));    //������ ���̺� ���� ���� �ҷ��� query �� �ȿ� �����Ѵ�. 
    	
    	pstmt.setString(2,ticket_no);    // ������ ��¥(screen_date�� �������� ticket_no�� �����Ѵ�.
    	  	                                  	
    	pstmt.executeUpdate();
    	
    	// ������ �����Ѵ�.




    	}catch(Exception e1){                                                    // ���ܰ� �߻��ϸ� ���� ��Ȳ�� ó���Ѵ�.

    	e1.printStackTrace();

    	System.out.println("member ���̺� ���ο� ���ڵ� �߰��� �����߽��ϴ�.");
    	JOptionPane.showMessageDialog(this, "���� ���� �߽��ϴ�.");

    	}finally{                                                            // ������ ���� �Ǵ� ���п� ������� ����� �ڿ��� ���� �Ѵ�. (�����߿�)
    		JOptionPane.showMessageDialog(this, "�����մϴ�.");
    	if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}            // PreparedStatement ��ü ����

    	if(conn != null) try{conn.close();}catch(SQLException sqle){}            // Connection ����

    	}

         dispose();
    
        
    }
}
