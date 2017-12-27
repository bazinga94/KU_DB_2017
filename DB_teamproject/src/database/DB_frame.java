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
        super("영화 예매 관리 프로그램");
        //v=getMemberList();
        //MemberDAO
        DB_Mysql mydb = new DB_Mysql();
        
        System.out.println(thea_name);  //thea_name을 못 받아옴
        
        v = mydb.getMemberList();
        //System.out.println("v="+v);
        cols = getColumn();
       
        
       
        model = new DefaultTableModel(v, cols);
       
        jTable = new JTable(model);
        pane = new JScrollPane(jTable);
        add(pane);
       
        pbtn = new JPanel();
        btnInsert = new JButton("예매하기");
        pbtn.add(btnInsert);
        add(pbtn,BorderLayout.NORTH);
       
       
        jTable.addMouseListener(this); //리스너 등록
        btnInsert.addActionListener(this); //회원가입버튼 리스너 등록
       
        setSize(600,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end 생성자
   
   
    //JTable의 컬럼
    public Vector getColumn(){
        Vector col = new Vector();
        col.add("영화 이름");
        col.add("상영 시작");
        col.add("상영 종료");
        col.add("상영관");
        col.add("가격");
        col.add("screen_id");
       
       
        return col;
    }//getColumn
    
   
    //Jtable 내용 갱신 메서드
    public void jTableRefresh(){
       
    	DB_Mysql mydb = new DB_Mysql();
        DefaultTableModel model= new DefaultTableModel(mydb.getMemberList(), getColumn());
        jTable.setModel(model);    
       
    }
   
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // mouseClicked 만 사용
        int r = jTable.getSelectedRow();
        String id = (String) jTable.getValueAt(r, 0);
        //System.out.println("id="+id);
       // MemberProc mem = new MemberProc(id,this); //아이디를 인자로 수정창 생성
               
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
        //예매하기 버튼을 클릭하면
    	info.amount = info.amount + 1000; //티켓넘버의 차이를 두기위한 amount
    	int row = jTable.getSelectedRow(); //테이블에서 선택한 행의 번호를 불러오는 변수

    	Connection conn = null;                                        // null로 초기화 한다.
    	PreparedStatement pstmt = null;

    	try{

    	String url = "jdbc:mysql://localhost/database_term2";        // 사용하려는 데이터베이스명을 포함한 URL 기술
    	String id = "root";                                                    // 사용자 계정
    	String pw = "ynwaljh940!";                                                // 사용자 계정의 패스워드

    	Class.forName("com.mysql.jdbc.Driver");                       // 데이터베이스와 연동하기 위해 DriverManager에 등록한다.
    	conn=DriverManager.getConnection(url,id,pw);              // DriverManager 객체로부터 Connection 객체를 얻어온다.

    	String sql = "insert into booking (customer_id, screen_id,ticket_no) values ('"+info.cus_id+"',?,?)";        // sql 쿼리
    	pstmt = conn.prepareStatement(sql);                          // prepareStatement에서 해당 sql을 미리 컴파일한다.

    	pstmt.setString(1,(String)jTable.getValueAt(row,5));    //선택한 테이블 행의 값을 불러와 query 문 안에 전달한다. 
    	
    	pstmt.setString(2,ticket_no);    // 예매한 날짜(screen_date를 바탕으로 ticket_no를 설정한다.
    	  	                                  	
    	pstmt.executeUpdate();
    	
    	// 쿼리를 실행한다.




    	}catch(Exception e1){                                                    // 예외가 발생하면 예외 상황을 처리한다.

    	e1.printStackTrace();

    	System.out.println("member 테이블에 새로운 레코드 추가에 실패했습니다.");
    	JOptionPane.showMessageDialog(this, "예매 실패 했습니다.");

    	}finally{                                                            // 쿼리가 성공 또는 실패에 상관없이 사용한 자원을 해제 한다. (순서중요)
    		JOptionPane.showMessageDialog(this, "감사합니다.");
    	if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}            // PreparedStatement 객체 해제

    	if(conn != null) try{conn.close();}catch(SQLException sqle){}            // Connection 해제

    	}

         dispose();
    
        
    }
}
