package classend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class edit extends JFrame
{
    private JPanel mianban;
    private JLabel edl;
    private JLabel id;
    private JLabel pwd;
    private JTextField fid;
    private JPasswordField fpwd;
    private JButton submit;
    private JButton res;
    private JButton ReturnPage;
      edit()
      {

          super("更改管理员信息");
          this.setSize(800, 600);
          this.setLocation(600, 200);
          mianban=new JPanel(null);

          edl=new JLabel("管理员信息修改");
          edl.setFont(new Font("微软雅黑",Font.BOLD,20));
          edl.setBounds(300, 30, 180, 25);

          id =new JLabel("管理员名称:");
          id.setFont(new Font("微软雅黑",Font.BOLD,20));
          id.setBounds(100, 100, 200, 100);

          fid=new JTextField(20);
          fid.setBounds(225,135,300,30);

          pwd =new JLabel("管理员密码:");
          pwd.setFont(new Font("微软雅黑",Font.BOLD,20));
          pwd.setBounds(100, 200, 200, 200);

          fpwd=new JPasswordField(20);
          fpwd.setBounds(225,285,300,30);

          submit=new JButton("更新信息");
          submit.setBounds(125,400,90,50);
          res=new JButton("重置信息");
          res.setBounds(300,400,90,50);
          ReturnPage=new JButton("返回上一页");
          ReturnPage.setBounds(450,400,150,50);
          mianban.add(edl);
          mianban.add(id);
          mianban.add(fid);
          mianban.add(pwd);
          mianban.add(fpwd);
          mianban.add(submit);
          mianban.add(res);
          mianban.add(ReturnPage);

          submit.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                 String mid=fid.getText();
                 String mpwd=new String(fpwd.getPassword());
                  String url="jdbc:mysql://localhost:3306/classmanage?useUnicode=true&characterEncoding=utf8&useSSL=false";
                  String basename="root";
                  String pwd="123456";
                  try
                  {
                      Class.forName("com.mysql.jdbc.Driver");
                      Connection con= DriverManager.getConnection(url,basename,pwd);
                      Statement sta=con.createStatement();
                      String ml="update manage set id=?,password=? where account='2040706105' ";
                      PreparedStatement ps=con.prepareStatement(ml) ;
                      ps.setString(1,mid);
                      ps.setString(2,mpwd);
                      int res=ps.executeUpdate();
                      if (res>0)
                      {
                          JOptionPane.showMessageDialog(null,"管理员信息修改成功!");
                          edit.super.dispose();
                          new Manage();
                      }
                      else {
                          JOptionPane.showMessageDialog(null,"管理员信息修改失败!");
                          fid.setText("");
                          fpwd.setText("");
                          JOptionPane.showMessageDialog(null,"请重新修改!");
                      }
                  }catch (Exception error){
                      System.out.println(error.getMessage());
                      error.printStackTrace();
                  }
              }
          });

          res.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  Object obj=e.getSource();
                  if (obj==(JButton) obj){
                      fid.setText("");
                      fpwd.setText("");
                      JOptionPane.showMessageDialog(null,"重置信息成功!");
                  }
              }
          });
          ReturnPage.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                   edit.super.dispose();
                   new Manage();
              }
          });
          this.add(mianban);
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.setVisible(true);
      }

    public static void main(String[] args) {
        edit ed=new edit();
    }
}
