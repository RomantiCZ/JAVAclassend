package classend;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.sql.*;
import java.util.Arrays;
import  javax.swing.*;
import javax.swing.border.Border;

public class ClassManage extends JFrame
{
    private JPanel mianban;
    private JLabel head;
    private JLabel acc;
    private JLabel pwd;
    private JLabel biaoti;
    private JTextField facc;
    private JPasswordField fpwd;
    private JButton sub;
    private JButton res;
    ClassManage ()
    {
      super("班级管理系统");
      this.setSize(800, 600);
      this.setLocation(600, 200);
      mianban=new JPanel(null);

      head=new JLabel("管理员登录");
      head.setFont(new Font("微软雅黑",Font.BOLD,20));
      head.setBounds(300, 30, 120, 25);

      acc =new JLabel("管理员账号:");
      acc.setFont(new Font("微软雅黑",Font.BOLD,20));
      acc.setBounds(100, 100, 200, 100);

      facc=new JTextField(20);
      facc.setBounds(225,135,300,30);

      pwd =new JLabel("密码:");
      pwd.setFont(new Font("微软雅黑",Font.BOLD,20));
      pwd.setBounds(157, 200, 200, 200);

      fpwd=new JPasswordField(20);
      fpwd.setBounds(225,285,300,30);

      sub=new JButton("登录");
      sub.setBounds(250,350,90,50);

      res=new JButton("重置信息");
      res.setBounds(380,350,90,50);
      mianban.add(head);
      mianban.add(acc);
      mianban.add(facc);
      mianban.add(pwd);
      mianban.add(fpwd);
      mianban.add(sub);
      mianban.add(res);
      this.add(mianban);

      //上传添加事件
      sub.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e)
          {
              String conacc=facc.getText();
              String conpwd = new String(fpwd.getPassword());
              //从文本框获取账号和密码
              //进行数据库连接
              String url="jdbc:mysql://localhost:3306/classmanage?useUnicode=true&characterEncoding=utf8&useSSL=false";
              String basename="root";
              String pwd="123456";
              try
              {
                  Class.forName("com.mysql.jdbc.Driver");
                  Connection con= DriverManager.getConnection(url,basename,pwd);
                  Statement sta=con.createStatement();

                  String ml="select * from manage where account=? and password=? ";
                  PreparedStatement ps=con.prepareStatement(ml) ;
                  ps.setString(1,conacc);
                  ps.setString(2,conpwd);
                  ResultSet res=ps.executeQuery();
                 if (!res.next())
                 {
                   JOptionPane.showMessageDialog(null,"该管理员不存在");
                   facc.setText("");
                   fpwd.setText("");
                 }
                 else
                 {
                    ClassManage.super.dispose();
                    new Manage();
                 }
              }catch (Exception error){
                  System.out.println(error.getMessage());
                  error.printStackTrace();
              }

          }
      });
       //重置添加事件
        res.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj=e.getSource();
                if (obj==(JButton) obj)
                {
                    JOptionPane.showMessageDialog(null,"重置信息成功");
                    facc.setText("");
                    fpwd.setText("");
                }
            }
        });
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);

    }

    public static void main(String[] args)
    {
      ClassManage peopleman=  new ClassManage();
    }
}

