package database;



import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
	 
	import javax.swing.table.DefaultTableModel;
	 
	//DB 처리
	public class DB_Mysql {
	 

		 //String cus_id;
		 //String screen_date = info.screen_date;
		 //String thea_name = info.thea_name;
		
		 
		
	     String userid = "root";
	     String password = "ynwaljh940!";
	    
	     private static final String DRIVER
	        = "org.gjt.mm.mysql.Driver";
	     private static final String URL
	        = "jdbc:mysql://localhost/database_term2";
	
	    private static final String USER = "root"; //DB ID
	    private static final String PASS = "ynwaljh940!"; //DB 패스워드
	    DB_frame frame;
	    public Connection getConn(){
	        Connection con = null;
	       
	        try {
	            Class.forName(DRIVER); //1. 드라이버 로딩
	            con = DriverManager.getConnection(URL,USER,PASS); //2. 드라이버 연결
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	       
	        return con;
	    }
	
	//DB 연결 메소드
	
	 public Vector getMemberList(){
	       
	        Vector data = new Vector();  //Jtable에 값을 쉽게 넣는 방법 1. 2차원배열   2. Vector 에 vector추가
	       
	        
	        Connection con = null;       //연결
	        PreparedStatement ps = null; //명령
	        ResultSet rs = null;         //결과
	        System.out.println(info.thea_name);
	        try{
	           
	            con = getConn();
	            String sql = "select movie_name, screen_start, screen_end, hall_no, price, screen_id from movie natural join screen natural join hall natural join customer where theater_name = '"
	            +info.thea_name+"' and screen_date = '"+info.screen_date+"' and age_restriction <= customer_age and customer_id = '"+info.cus_id+"'";
	            		//"select movie_name, screen_start, screen_end, hall_no, price, screen_id from movie natural join screen natural join hall where theater_name = "+info.thea_name;
	            		
	            		
	            ps = con.prepareStatement(sql);
	            rs = ps.executeQuery(sql);
	            
	           //System.out.println(info.cus_id);
	           
	            while(rs.next()){
	                String movie_name = rs.getString("movie_name");
	                String screen_start = rs.getString("screen_start");
	                String screen_end = rs.getString("screen_end");
	                String hall_no = rs.getString("hall_no");
	                String price = rs.getString("price");
	                String screen_id = rs.getString("screen_id");
	               
	                Vector row = new Vector();
	                row.add(movie_name);
	                row.add(screen_start);
	                row.add(screen_end);
	                row.add(hall_no);
	                row.add(price);
	                row.add(screen_id);
	               
	               
	                data.add(row);           
	            }//while
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return data;
	    }//getMemberList()
	
	
	
	
	
}
	
	