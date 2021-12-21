package classend;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.sql.*;
import java.util.Arrays;
import  javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class seldetail extends JFrame{
    private JPanel mianban;
    private JButton bt_selgo,bt_return,bt_edit,bt_sub;
    private JLabel choice,write;
    private JTextField fwrite ;
    private JComboBox cmbsel;
    private JTable table;
    private JScrollPane jsp;
    private Vector rowDate,columns;
    seldetail()
    {
        super("查询学生");
        this.setSize(800, 600);
        this.setLocation(600, 200);
        mianban=new JPanel(null);

        choice=new JLabel("请选择:");
        choice.setFont(new Font("微软雅黑",Font.BOLD,20));
        choice.setBounds(50,20,100,60);

        cmbsel = new JComboBox(new String[] { "学号", "姓名", "性别","生日","年龄","专业","宿舍" });
        cmbsel.setBounds(150,30,100,40);
        write=new JLabel("关键词:");

        write.setFont(new Font("微软雅黑",Font.BOLD,20));
        write.setBounds(300,20,100,60);

        fwrite=new JTextField(20);
        fwrite.setBounds(400,35,175,35);

        bt_selgo=new JButton("查询");
        bt_selgo.setBounds(600,30,90,45);

        bt_edit=new JButton("修改信息");
        bt_edit.setBounds(125,100,90,45);

        bt_sub=new JButton("删除该学生");
        bt_sub.setBounds(300,100,100,45);

         bt_return=new JButton("返回");
         bt_return.setBounds(500,100,100,45);

         bt_selgo.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 int pick=cmbsel.getSelectedIndex();
                 String msg=fwrite.getText();
                 showmessage(pick,msg);
             }
         });
         bt_return.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 seldetail.super.dispose();
                 new sel();
             }
         });

        mianban.add(choice);
        mianban.add(cmbsel);
        mianban.add(write);
        mianban.add(fwrite);
        mianban.add(bt_selgo);
        mianban.add(bt_edit);
        mianban.add(bt_sub);
        mianban.add(bt_return);
        this.add(mianban);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void showmessage(int pick,String msg)
    {
              String ml=null;
            switch (pick)
            {
                case 0:ml="select * from students where num=? ";break;
                case 1:ml="select * from students where name=? ";break;
                case 2:ml="select * from students where gender=? ";break;
                case 3:ml="select * from students where birthday=? ";break;
                case 4:ml="select * from students where age=? ";break;
                case 5:ml="select * from students where major=? ";break;
                case 6:ml="select * from students where locate=? ";break;
            }
        String url="jdbc:mysql://localhost:3306/classmanage?useUnicode=true&characterEncoding=utf8&useSSL=false";
        String basename="root";
        String pwd="123456";

        try
        {
            //列名
            columns=new Vector();
            String[] tbname={"学号","姓名","性别","生日","年龄","专业","宿舍"};
            columns.add("学号");columns.add("姓名");columns.add("性别");columns.add("生日");columns.add("年龄");columns.add("专业");columns.add("宿舍");

            //
            rowDate=new Vector();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,basename,pwd);
            Statement sta=con.createStatement();

            com.mysql.jdbc.PreparedStatement pst= (com.mysql.jdbc.PreparedStatement) con.prepareStatement(ml);
            pst.setString(1,msg);
            ResultSet resset =  pst.executeQuery();
            while (resset.next())
            {
                Vector da=new Vector();
                da.add(resset.getString("num"));
                da.add(resset.getString("name"));
                da.add(resset.getString("gender"));
                da.add(resset.getString("birthday"));
                da.add(resset.getString("age"));
                da.add(resset.getString("major"));
                da.add(resset.getString("locate"));
                rowDate.add(da);
                table=new JTable(rowDate,columns);
                table.setFont(new Font("微软雅黑",Font.PLAIN,13));
                jsp=new JScrollPane(table);
                jsp.setBounds(100,155,600,485);
                mianban.add(jsp);

                table.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount()==1){
                            Point p = e.getPoint();
                            int row=table.rowAtPoint(p);
                            String snum=table.getValueAt(row,0).toString();
                            String sname=table.getValueAt(row,1).toString();
                            String ssex=table.getValueAt(row,2).toString();
                            String sbir=table.getValueAt(row,3).toString();
                            String sage=table.getValueAt(row,4).toString();
                            String smajor=table.getValueAt(row,5).toString();
                            String slocate=table.getValueAt(row,6).toString();
                            bt_edit.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    stuedit stu= new stuedit();
                                    stu.fnum.setText(snum);
                                    stu.fname.setText(sname);
                                    stu.fbirthday.setText(sbir);
                                    stu.fage.setText(sage);
                                    stu.fmajor.setText(smajor);
                                    stu.flocate.setText(slocate);
                                }
                            });
                            bt_sub.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String url="jdbc:mysql://localhost:3306/classmanage?useUnicode=true&characterEncoding=utf8&useSSL=false";
                                    String basename="root";
                                    String pwd="123456";
                                    try {
                                        Class.forName("com.mysql.jdbc.Driver");
                                        Connection con = DriverManager.getConnection(url, basename, pwd);
                                        Statement sta = con.createStatement();
                                        String ml="delete from students where num=? and name=? ";
                                        com.mysql.jdbc.PreparedStatement ps= (com.mysql.jdbc.PreparedStatement) con.prepareStatement(ml);
                                        ps.setString(1,snum);
                                        ps.setString(2,sname);
                                        int res=ps.executeUpdate();
                                        if (res>0){
                                            JOptionPane.showMessageDialog(null,"该生信息已删除");
                                            seldetail.super.dispose();
                                            new seldetail();
                                        }
                                    }
                                    catch (Exception error){
                                        System.out.println(error.getMessage());
                                        error.printStackTrace();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

            }

        }catch (Exception error)
        {
            System.out.println(error.getMessage());
            error.printStackTrace();
        }

    }

    public static void main(String[] args) {
       seldetail se=new seldetail();
    }
}
