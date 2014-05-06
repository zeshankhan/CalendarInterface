/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package learn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author liza
 */
public class CalendarGui extends javax.swing.JFrame {

    /**
     * Creates new form CalendarGui
     */
    public static int memo_status=0;
    public static int current_month=3;
    public static int current_year=2014;
    public static int current_date=1;
    public static String current_time="00:00";
    public String [] months={"JAN","FEB","MAR","APR","MAY","JUNE","JULY","AUG","SEP","OCT","NOV","DEC"};
    public CalendarGui() {
        Date date=new Date();
        current_date=date.getDate();
        current_month=date.getMonth();
        current_year=date.getYear()+1900;
        initComponents1();
        CurrentDate();
        
    }
    private void initComponents1() {

        jPanel1 = new javax.swing.JPanel();
        date_txt = new javax.swing.JLabel();
        txt_time = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(510, 681));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(null);

        date_txt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        date_txt.setText("jLabel2");
        jPanel1.add(date_txt);
        date_txt.setBounds(10, 0, 100, 40);

        txt_time.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_time.setText("jLabel2");
        jPanel1.add(txt_time);
        txt_time.setBounds(330, 0, 80, 40);

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 30, 410, 50);

        

        jPanel2.setLayout(null);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Date:", "Time:", "Title:", "Post:", "==============" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(0, 0, 410, 110);

        jTabbedPane1.addTab("Post", jPanel2);

        jPanel1.add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 390, 410, 140);
        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(510, 170, 2, 2);
        
        initialize_calendar();
        
        getContentPane().add(jPanel1);
        jPanel1.setBounds(50, 60, 410, 540);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 0, 660);
        
        date_memo=new memo();

        pack();
    }// </editor-fold>   

    public void initialize_calendar()
    {
        month=new JLabel();
        year=new JLabel();
        pre_month=new JButton();
        next_month=new JButton();
        jPanel1.add(month);
        jPanel1.add(year);
        jPanel1.add(pre_month);
        jPanel1.add(next_month);
        days=new JButton[7];
        dates=new JButton[42];
        int x=0,y=70,w=58,h=45;
        pre_month.setBounds(x,y-30, w, h-10);
        next_month.setBounds(x+(6*w)+4,y-30, w, h-10);
        month.setBounds(x+(3*w)-10,y-30, w, h-10);
        year.setBounds(x+(4*w)-10,y-30, w, h-10);
        for(int i=0;i<7;i++)
        {
            days[i]=new JButton();
            jPanel1.add(days[i]);
            days[i].setBounds(x+(w*i),y, w+5, h+5);
        }
        days[0].setText("SUN");
        days[1].setText("MON");
        days[2].setText("TUE");
        days[3].setText("WED");
        days[4].setText("THU");
        days[5].setText("FRI");
        days[6].setText("SAT");
        pre_month.setText("<<");
        next_month.setText(">>");
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<7;j++)
            {
                dates[(i*7)+j]=new JButton();
                dates[(i*7)+j].setText(""+((i*7)+j+1));
                jPanel1.add(dates[(i*7)+j]);
                dates[(i*7)+j].setBounds(x+(w*(j)),y+(h*(i+1)), w+5, h+5);
            }
        }
        update_date();
        set_actions();
    }
    public void set_actions()
    {
        next_month.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    next_monthActionPerformed(evt);
                }
            });
        pre_month.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    pre_monthActionPerformed(evt);
                }
            });
        for(int i=0;i<42;i++)
        {
        dates[i].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    dateActionPerformed(evt);
                }
            });
        }
    }
    private void dateActionPerformed(java.awt.event.ActionEvent evt) {
        JButton b=(JButton) evt.getSource();
        current_date=Integer.parseInt(b.getText());
        update_date();
       }
    private void next_monthActionPerformed(java.awt.event.ActionEvent evt) {
        current_month++;
        update_date();
       }
    private void pre_monthActionPerformed(java.awt.event.ActionEvent evt) {
        current_month--;
        update_date();
       }
    public void update_date()
    {
        while(current_month>11)
        {
            current_month-=12;
            current_year++;
        }
        while(current_month<0)
        {
            current_month+=12;
            current_year--;
        }
        int count=28;
        if((current_month==0)||(current_month==2)||(current_month==4)||(current_month==6)||(current_month==7)||(current_month==9)||(current_month==11))
        {
            count=31;
        }
        else if((current_month==3)||(current_month==5)||(current_month==8)||(current_month==10))
        {
            count=30;
        }
        else if(((current_year%4==0&&current_year%100!=0)||(current_year%400==0))&&(current_month==1))
        {
            count=29;
        }
        Date d=new Date(current_year,current_month,0);
        int start_day=d.getDay();
        for(int i=0;i<42;i++)
        {
            dates[i].setVisible(true);
        }
        for(int i=0;i<start_day;i++)
        {
            dates[i].setVisible(false);
        }
        for(int i=start_day+count;i<42;i++)
        {
            dates[i].setVisible(false);
        }
        for(int i=start_day,j=1;i<start_day+count;i++,j++)
        {
            dates[i].setText(""+j);
        }
        year.setText(""+current_year);
        month.setText(months[current_month]);
        if(memo_status<1)
        {
            date_memo=new memo();
            memo_status++;
        }
        else
            date_memo.setVisible(true);
        date_memo.getDateField().setText(current_date+"/"+(current_month+1)+"/"+current_year);
        try {
            update_events();
            //jTextField2.setText(""+current_date+"/"+(current_month+1)+"/"+current_year);
        } catch (IOException ex) {
            Logger.getLogger(CalendarGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void CurrentDate(){


        Thread clock= new Thread(){
           public void run(){
            for(;;){

        Calendar cal = new GregorianCalendar();
        int month =cal.get(Calendar.MONTH);
        int year =cal.get(Calendar.YEAR);
        int day =cal.get(Calendar.DAY_OF_MONTH);
        date_txt.setText("Date: "+(month+1)+"/"+day+"/"+year);


        int second =cal.get(Calendar.SECOND);
        int minute =cal.get(Calendar.MINUTE);
        int hour =cal.get(Calendar.HOUR);
        txt_time.setText("Time: "+hour+":"+(minute)+":"+second);
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CalendarGui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            }
            };
        clock.start();
            }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        date_txt = new javax.swing.JLabel();
        txt_time = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(null);

        date_txt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        date_txt.setText("jLabel2");
        jPanel1.add(date_txt);
        date_txt.setBounds(10, 0, 100, 40);

        txt_time.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_time.setText("jLabel2");
        jPanel1.add(txt_time);
        txt_time.setBounds(330, 0, 80, 40);

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 30, 410, 50);

        jPanel2.setLayout(null);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Date:", "Time:", "Title:", "Memo:", "==============" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(0, 0, 410, 120);

        jTabbedPane1.addTab("Memos", jPanel2);

        jPanel1.add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 390, 410, 140);
        jTabbedPane1.getAccessibleContext().setAccessibleName("Notes");

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(510, 170, 2, 2);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(50, 60, 410, 540);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 0, 660);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CalendarGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalendarGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalendarGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalendarGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalendarGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel txt_time;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JButton[] dates;
    private javax.swing.JButton[] days;
    private javax.swing.JLabel year;
    private javax.swing.JLabel month;
    private javax.swing.JButton next_month;
    private javax.swing.JButton pre_month;
    public static memo date_memo;

    private void update_events() throws IOException {
        File folder = new File("files/");
        final List data=new ArrayList();
        File [] files=folder.listFiles();
        for (int ind=files.length-1;ind>=0;ind--) {
            File fileEntry=files[ind];
            if (!fileEntry.isDirectory()) {
                String path=""+fileEntry;
                File file=new File(path);
                FileReader fw = null;
                try {
                    fw = new FileReader(file.getAbsoluteFile());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CalendarGui.class.getName()).log(Level.SEVERE, null, ex);
                }
                BufferedReader bw=new BufferedReader(fw);
                String temp_data=bw.readLine();
                try{
                    while(temp_data!=null)
                    {
                        data.add(temp_data);
                        temp_data=bw.readLine();
                    }
                }
                catch(Exception ex){}
                data.add("\n----------------\n\n");
                bw.close();
                fw.close();
            }
        }
        jList1.setModel(new javax.swing.AbstractListModel() {
                    public int getSize() { return data.size(); }
                    public Object getElementAt(int i) { return data.get(i).toString(); }
                });
    }
}