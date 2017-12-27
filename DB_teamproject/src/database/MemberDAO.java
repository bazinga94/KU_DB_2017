/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author asus
 */
//CRUD : Create;insert , Read;Select, Update, delete

import java.sql.*;
import java.util.Vector;
import java.util.function.Predicate;

import javax.swing.table.DefaultTableModel;

public class MemberDAO {

    private static final String DRIVER
            = "org.gjt.mm.mysql.Driver";
    private static final String URL
            = "jdbc:mysql://localhost:3306/database_term2";

    private static final String USER = "root"; //DB ID
    private static final String PASS = "ynwaljh940!"; //DB
    Member_List mList;

    public MemberDAO() {

    }

    public MemberDAO(Member_List mList){
        this.mList = mList;
        System.out.println("DAO=>"+mList);
    }

    /**DB�뿰寃� 硫붿냼�뱶*/
    public Connection getConn(){
        Connection con = null;

        try {
            Class.forName(DRIVER); 
            con = DriverManager.getConnection(URL,USER,PASS); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }


    public MemberDTO getMemberDTO(String id){

        MemberDTO dto = new MemberDTO();

        Connection con = null;       //
        PreparedStatement ps = null; //
        ResultSet rs = null;         //

        try {

            con = getConn();
            String sql = "select * from customer where customer_id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();

            if(rs.next()){
                dto.setId(rs.getString("customer_id"));
                dto.setName(rs.getString("customer_name"));
                dto.setTel(rs.getString("phone_number"));
                dto.setAge(rs.getString("customer_age"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }

    
    public Vector getMemberList(){

        Vector data = new Vector();  //


        Connection con = null;       //
        PreparedStatement ps = null; //
        ResultSet rs = null;         //

        try{

            con = getConn();
            String sql = "select * from customer order by customer_name asc";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                String id = rs.getString("customer_id");
                String name = rs.getString("customer_name");
                String tel = rs.getString("phone_number");
                String age = rs.getString("customer_age");

                Vector row = new Vector();
                row.add(id);
                row.add(name);
                row.add(tel);
                row.add(age);

                data.add(row);
            }//while
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }//getMemberList()



    
    public boolean insertMember(MemberDTO dto){

        boolean ok = false;

        Connection con = null;       //
        PreparedStatement ps = null; //
        try{

            con = getConn();
            String sql = "INSERT INTO customer(customer_id, customer_name, phone_number, customer_age)"
                    + "VALUES(?,?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getId());
            ps.setString(2, dto.getName());
            ps.setString(3, dto.getTel());
            ps.setString(4, dto.getAge());

            int r = ps.executeUpdate(); //


            if(r>0){
                System.out.println("가입 성공");
                ok=true;
            }else{
                System.out.println("가입 실패");
            }



        }catch(Exception e){
            e.printStackTrace();
        }

        return ok;
    }//insertMmeber


    public boolean updateMember(MemberDTO vMem){
        System.out.println("dto="+vMem.toString());
        boolean ok = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{

            con = getConn();
            String sql = "update customer set customer_name=?, phone_number=?, customer_age=?" + "where customer_id=?";

            ps = con.prepareStatement(sql);

            ps.setString(1, vMem.getName());
            ps.setString(2, vMem.getTel());
            ps.setString(3, vMem.getAge());
            ps.setString(4, vMem.getId());

            int r = ps.executeUpdate(); 

            if(r>0) ok = true; 
        }catch(Exception e){
            e.printStackTrace();
        }

        return ok;
    }

   
    public boolean deleteMember(String id){

        boolean ok =false ;
        Connection con =null;
        PreparedStatement ps =null;
        PreparedStatement ps1 = null;

        try {
            con = getConn();
            //String sql = "delete from booking where customer_id=?";
            String sql1 = "delete from customer where customer_id=?";

            
            
            
            ps1=con.prepareStatement(sql1);
            //ps = con.prepareStatement(sql);
            
            
            ps1.setString(1,id);
            //ps.setString(1, id);
            //ps.executeUpdate();
            int r = ps1.executeUpdate(); // 
            //ps1.executeUpdate();
            if (r>0) ok=true; //

        } catch (Exception e) {
            System.out.println(e + "-> 오류발생");
        }
        return ok;
    }


    public void userSelectAll(DefaultTableModel model) {


        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConn();
            String sql = "select * from customer order by customer_name asc";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();


            for (int i = 0; i < model.getRowCount();) {
                model.removeRow(0);
            }

            while (rs.next()) {
                Object data[] = { rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)};

                model.addRow(data);
            }

        } catch (SQLException e) {
            System.out.println(e + "=> userSelectAll fail");
        } finally{

            if(rs!=null)
                try {
                    rs.close();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
            if(ps!=null)
                try {
                    ps.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            if(con!=null)
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }
}