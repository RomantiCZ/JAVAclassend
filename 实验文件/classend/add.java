package classend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class add extends JFrame
{
    private JPanel mianban;
    private JLabel addstudent;
    private JLabel num,name,birthday,sex,age,major,locate;
    private JTextField fnum,fname,fbirthday,fage,fmajor,flocate;
    private JRadioButton male,female;
    private  ButtonGroup bgr;
    private JButton submit;
    private JButton res;
    private JButton ReturnPage;

    add(){
        super("添加新的学生信息");
        this.setSize(800, 600);
        this.setLocation(600, 200);
        mianban=new JPanel(null);

        addstudent=new JLabel("添加一个新的同学信息");
        addstudent.setFont(new Font("微软雅黑",Font.BOLD,30));
        addstudent.setBounds(220, 15, 350, 100);

        num=new JLabel("学号:");
        num.setFont(new Font("微软雅黑",Font.BOLD,20));
        num.setBounds(125,100,50,50);


        fnum=new JTextField();
        fnum.setBounds(200,110,300,30);

        name=new JLabel("姓名:");
        name.setFont(new Font("微软雅黑",Font.BOLD,20));
        name.setBounds(125,150,50,50);

        fname=new JTextField();
        fname.setBounds(200,160,250,30);

        sex=new JLabel("性别:");
        sex.setFont(new Font("微软雅黑",Font.BOLD,20));
        sex.setBounds(125,200,50,50);

        male=new JRadioButton("男",true);
        male.setFont(new Font("微软雅黑",Font.BOLD,10));
        male.setBounds(200,200,50,50);
        female=new JRadioButton("女",false);
        female.setBounds(250,200,50,50);
        female.setFont(new Font("微软雅黑",Font.BOLD,10));
        bgr=new ButtonGroup();
        bgr.add(male);
        bgr.add(female);

        birthday=new JLabel("生日:");
        birthday.setFont(new Font("微软雅黑",Font.BOLD,20));
        birthday.setBounds(125,250,50,50);

        fbirthday=new JTextField();
        fbirthday.setBounds(200,260,150,30);


        age=new JLabel("年龄:");
        age.setFont(new Font("微软雅黑",Font.BOLD,20));
        age.setBounds(125,300,50,50);
        fage=new JTextField();
        fage.setBounds(200,310,50,30);

        major=new JLabel("专业:");
        major.setFont(new Font("微软雅黑",Font.BOLD,20));
        major.setBounds(125,350,50,50);
        fmajor=new JTextField();
        fmajor.setBounds(200,360,250,30);

        locate=new JLabel("宿舍:");
        locate.setFont(new Font("微软雅黑",Font.BOLD,20));
        locate.setBounds(125,400,50,50);
        flocate=new JTextField();
        flocate.setBounds(200,410,280,30);

        submit=new JButton("上传信息");
        submit.setBounds(100,470,125,50);

        res=new JButton("重置学生信息");
        res.setBounds(275,470,125,50);

        ReturnPage=new JButton("返回");
        ReturnPage.setBounds(450,470,125,50);

        mianban.add(addstudent);
        mianban.add(num);
        mianban.add(fnum);
        mianban.add(name);
        mianban.add(fname);
        mianban.add(sex);
        mianban.add(male);
        mianban.add(female);
        mianban.add(birthday);
        mianban.add(fbirthday);
        mianban.add(age);
        mianban.add(fage);
        mianban.add(major);
        mianban.add(fmajor);
        mianban.add(locate);
        mianban.add(flocate);
        mianban.add(submit);
        mianban.add(res);
        mianban.add(ReturnPage);
        this.add(mianban);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String snum=fnum.getText();
                String sname=fname.getText();
                String sbir=fbirthday.getText();
                String ssex;
                if (male.isSelected()){
                    ssex="男";
                }
                else ssex="女";
                String sage=fage.getText();
                String smajor=fmajor.getText();
                String slocate=flocate.getText();
                String url="jdbc:mysql://localhost:3306/classmanage?useUnicode=true&characterEncoding=utf8&useSSL=false";
                String basename="root";
                String pwd="123456";
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con= DriverManager.getConnection(url,basename,pwd);
                    Statement sta=con.createStatement();
                    String ml="insert into students(num,name,gender,birthday,age,major,locate) values(?,?,?,?,?,?,?)";
                    PreparedStatement ps=con.prepareStatement(ml) ;
                    ps.setString(1,snum);
                    ps.setString(2,sname);
                    ps.setString(3,ssex);
                    ps.setString(4,sbir);
                    ps.setString(5,sage);
                    ps.setString(6,smajor);
                    ps.setString(7,slocate);
                    int res=ps.executeUpdate();
                  if (res>0){
                      JOptionPane.showMessageDialog(null,"学生信息已保存");
                      fnum.setText("");
                      fname.setText("");
                      fbirthday.setText("");
                      fage.setText("");
                      fmajor.setText("");
                      flocate.setText("");
                      add.super.dispose();
                     new Manage();
                  }else
                  {
                      fnum.setText("");
                      fname.setText("");
                      fbirthday.setText("");
                      fage.setText("");
                      fmajor.setText("");
                      flocate.setText("");
                      JOptionPane.showMessageDialog(null,"请重新输入学生的信息!");
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
                fnum.setText("");
                fname.setText("");
                fbirthday.setText("");
                fage.setText("");
                fmajor.setText("");
                flocate.setText("");
                JOptionPane.showMessageDialog(null,"重置信息成功!");
            }
        });

        ReturnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add.super.dispose();
                new Manage();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        add addpeople=new add();
    }
}
