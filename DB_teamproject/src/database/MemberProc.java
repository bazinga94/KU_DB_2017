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
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

public class MemberProc extends JFrame implements ActionListener {


    JPanel p;
    JTextField tfId, tfName, tfAge;
    JTextField tfTel1, tfTel2, tfTel3; //
    JButton btnInsert, btnCancel, btnUpdate,btnDelete; 

    GridBagLayout gb;
    GridBagConstraints gbc;
    Member_List mList ;

    public MemberProc(){ 

        createUI(); // 
        btnUpdate.setEnabled(false);
        btnUpdate.setVisible(false);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(false);


    }//

    public MemberProc(Member_List mList){ //
    	
        createUI(); // 
        btnUpdate.setEnabled(false);
        btnUpdate.setVisible(false);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(false);
        this.mList = mList;

    }//
    public MemberProc(String id,Member_List mList){ // 
        createUI();
        btnInsert.setEnabled(false);
        btnInsert.setVisible(false);
        this.mList = mList;


        System.out.println("id="+id);

        MemberDAO dao = new MemberDAO();
        MemberDTO vMem = dao.getMemberDTO(id);
        viewData(vMem);


    }//


    //MemberDTO 
    private void viewData(MemberDTO vMem){

        String id = vMem.getId();
        String name = vMem.getName();
        String tel = vMem.getTel();
        String age = vMem.getAge();

        //
        tfId.setText(id);
        tfId.setEditable(false); //
        tfName.setText(name);
        String[] tels = tel.split("-");
        tfTel1.setText(tels[0]);
        tfTel2.setText(tels[1]);
        tfTel3.setText(tels[2]);
        tfAge.setText(age);

    }//viewData



    private void createUI(){
        this.setTitle("회원정보");
        gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;


        //
        JLabel bId = new JLabel("아이디 : ");
        tfId = new JTextField(20);
        
        gbAdd(bId, 0, 0, 1, 1);
        gbAdd(tfId, 1, 0, 3, 1);

        //
        JLabel bName = new JLabel("이름 :");
        tfName = new JTextField(20);
        gbAdd(bName,0,2,1,1);
        gbAdd(tfName,1,2,3,1);

        //
        JLabel bTel = new JLabel("전화 번호 :");
        JPanel pTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfTel1 = new JTextField(6);
        tfTel2 = new JTextField(6);
        tfTel3 = new JTextField(6);
        pTel.add(tfTel1);
        pTel.add(new JLabel(" - "));
        pTel.add(tfTel2);
        pTel.add(new JLabel(" - "));
        pTel.add(tfTel3);
        gbAdd(bTel, 0, 3, 1,1);
        gbAdd(pTel, 1, 3, 3,1);

        
        JLabel bAddr = new JLabel("나이: ");
        tfAge = new JTextField(6);
        gbAdd(bAddr, 0,4,1,1);
        gbAdd(tfAge, 1, 4, 3,1);

        
        JPanel pButton = new JPanel();
        btnInsert = new JButton("가입");
        btnUpdate = new JButton("수정");
        btnDelete = new JButton("탈퇴");
        btnCancel = new JButton("취소");
        pButton.add(btnInsert);
        pButton.add(btnUpdate);
        pButton.add(btnDelete);
        pButton.add(btnCancel);
        gbAdd(pButton, 0, 10, 4, 1);

        
        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnCancel.addActionListener(this);
        btnDelete.addActionListener(this);

        setSize(350,200);
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE); //System.exit(0) 
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //dispose(); 


    }//createUI

    
    private void gbAdd(JComponent c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gb.setConstraints(c, gbc);
        gbc.insets = new Insets(2, 2, 2, 2);
        add(c, gbc);
    }//gbAdd

    public static void main(String[] args) {

        new MemberProc();
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnInsert){
            insertMember();
            System.out.println("insertMember() 호출 종료");
        }else if(ae.getSource() == btnCancel){
            this.dispose(); //
            
        }else if(ae.getSource() == btnUpdate){
            UpdateMember();
        }else if(ae.getSource() == btnDelete){

            int x = JOptionPane.showConfirmDialog(this,"정말 삭제 하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION);

            if (x == JOptionPane.OK_OPTION){
                deleteMember();
            }else{
                JOptionPane.showMessageDialog(this, "삭제를 취소 하였습니다.");
            }
        }

        
        mList.jTableRefresh();

    }//actionPerformed


    private void deleteMember() {
        String id = tfId.getText();

        //System.out.println(mList);
        MemberDAO dao = new MemberDAO();
        boolean ok = dao.deleteMember(id);

        if(ok){
            JOptionPane.showMessageDialog(this, "삭제 완료");
            dispose();

        }else{
            JOptionPane.showMessageDialog(this, "삭제 실패");

        }

    }//deleteMember

    private void UpdateMember() {

        
        MemberDTO dto = getViewData();
        
        MemberDAO dao = new MemberDAO();
        boolean ok = dao.updateMember(dto);

        if(ok){
            JOptionPane.showMessageDialog(this, "수정 되었습니다.");
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "수정 실패! 값을 확인 하세요.");
        }
    }

    private void insertMember(){

      
        MemberDTO dto = getViewData();
        MemberDAO dao = new MemberDAO();
        boolean ok = dao.insertMember(dto);

        if(ok){

            JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
            dispose();

        }else{

            JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
        }



    }//insertMember

    public MemberDTO getViewData(){

        
        MemberDTO dto = new MemberDTO();
        String id = tfId.getText();
        String name = tfName.getText();
        String tel1 = tfTel1.getText();
        String tel2 = tfTel2.getText();
        String tel3 = tfTel3.getText();
        String tel = tel1+"-"+tel2+"-"+tel3;
        String age = tfAge.getText();

        
        dto.setId(id);
        dto.setName(name);
        dto.setTel(tel);
        dto.setAge(age);

        return dto;
    }

}//end