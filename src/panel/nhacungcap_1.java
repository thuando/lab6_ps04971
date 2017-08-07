package panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Duy
 */
public class nhacungcap_1 extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    String url = "jdbc:sqlserver://localhost:1433;databaseName=Database-mẫu";
    String user = "sa", pass = "1";
    Vector nv = null;
    int r = 0, id = 0;
    String c = null;
    qlhhPanel ncc;
    GridBagLayout layout = new GridBagLayout();

    public nhacungcap_1() {
        initComponents();
        tab.getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tab.getColumnModel().getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loaddata();
        loadlencompanel(r);
    }

    public void loaddata() {
        DefaultTableModel model = (DefaultTableModel) tab.getModel();
        model.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from Nha_Cung_Cap";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nv = new Vector();
                nv.add(rs.getString(1));
                nv.add(rs.getString(2));
                nv.add(rs.getString(3));
                nv.add(rs.getString(4));
                nv.add(rs.getString(5));
                model.addRow(nv);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

//    public boolean kt() {
//        boolean status = validate.validateEmail(txtemail.getText());
//        if (txtma.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "chưa nhập mã nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
//            txtma.requestFocus();
//            return false;
//        } else if (txt_ten.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "chưa nhập tên nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
//            txt_ten.requestFocus();
//            return false;
//        } else if (txtsodt.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "chưa nhập sđt", "Error", JOptionPane.ERROR_MESSAGE);
//            txtsodt.requestFocus();
//            return false;
//        } else if (txtsodt.getText().length() != 10 && txtsodt.getText().length() != 11) {
//            System.out.println(txtsodt.getText().length());
//            System.out.println(txtsodt.getText().length() != 10);
//            System.out.println(txtsodt.getText().length() != 11);
//            System.out.println(txtsodt.getText().length() != 10 && txtsodt.getText().length() != 11);
//              JOptionPane.showMessageDialog(this, "dây Không phải là số điện thoại", "Error", JOptionPane.ERROR_MESSAGE);
//            txtsodt.requestFocus();
//            return false;
//        } else if (txtsodt.getText().length() == 10&&!txtsodt.getText().substring(0, 2).equals("09")) {
//          
//            System.out.println(txtsodt.getText().length() == 10&&!txtsodt.getText().substring(0, 2).equals("09"));
//                  JOptionPane.showMessageDialog(this, "nhập sai đâu số, có 10 số", "Error", JOptionPane.ERROR_MESSAGE);
//            txtsodt.requestFocus();
//                return false;
//
//        } else if (txtsodt.getText().length() == 11&&!txtsodt.getText().substring(0, 2).equals("01")) {
//            System.out.println(txtsodt.getText().length());
//            System.out.println(txtsodt.getText().length() != 10);
//            System.out.println(txtsodt.getText().length() != 11);
//            System.out.println(txtsodt.getText().length() != 10 || txtsodt.getText().length() != 11);
//              JOptionPane.showMessageDialog(this, "nhập sai đầu số, có 11 sô", "Error", JOptionPane.ERROR_MESSAGE);
//            txtsodt.requestFocus();
//            return false;
//        } else if (txtemail.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "chưa nhập email nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
//            txtemail.requestFocus();
//            return false;
//        } else if (status) {
//            txtemail.setText(txtemail.getText());
//        } else {
//            JOptionPane.showMessageDialog(this, "Not Valid Email", "Error", JOptionPane.ERROR_MESSAGE);
//            txtemail.requestFocus();
//            return false;
//        }
//        System.out.println(txtemail.getText());
//        return true;
//    }
    public void loadlencompanel(int r) {
        if (r < 0) {
            return;
        }
        txt_mcc.setText(tab.getValueAt(r, 0).toString());
        txt_ncc.setText(tab.getValueAt(r, 1).toString());
        txt_sdt.setText(tab.getValueAt(r, 2).toString());
        txt_dc.setText(tab.getValueAt(r, 3).toString());
        txt_fax.setText(tab.getValueAt(r, 4).toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mGradientPanelBeanInfo1 = new id.web.martinusadyh.panel.MGradientPanelBeanInfo();
        mAbstractPanel1 = new id.web.martinusadyh.panel.MAbstractPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_mcc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_ncc = new javax.swing.JTextField();
        txt_dc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_sdt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_fax = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab = new javax.swing.JTable();
        bntdelete = new javax.swing.JButton();
        bntsua = new javax.swing.JButton();
        bntnew = new javax.swing.JButton();
        bntdelete1 = new javax.swing.JButton();

        javax.swing.GroupLayout mAbstractPanel1Layout = new javax.swing.GroupLayout(mAbstractPanel1);
        mAbstractPanel1.setLayout(mAbstractPanel1Layout);
        mAbstractPanel1Layout.setHorizontalGroup(
            mAbstractPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        mAbstractPanel1Layout.setVerticalGroup(
            mAbstractPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mã nhà cung cấp:");

        txt_mcc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tên nhà cung cấp:");

        txt_ncc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_dc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Địa chỉ:");

        txt_sdt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Số điện thoại: ");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Fax: ");

        txt_fax.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã NCC", "Tên NCC", "Địa chỉ", "SDT", "FAX"
            }
        ));
        tab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tab);

        bntdelete.setBackground(new java.awt.Color(255, 255, 255));
        bntdelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntdelete.setText("Xóa");
        bntdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntdeleteActionPerformed(evt);
            }
        });

        bntsua.setBackground(new java.awt.Color(255, 255, 255));
        bntsua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntsua.setText("Lưu");
        bntsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntsuaActionPerformed(evt);
            }
        });

        bntnew.setBackground(new java.awt.Color(255, 255, 255));
        bntnew.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntnew.setText("New");
        bntnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntnewActionPerformed(evt);
            }
        });

        bntdelete1.setBackground(new java.awt.Color(255, 255, 255));
        bntdelete1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bntdelete1.setText("Kết Thúc");
        bntdelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntdelete1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel1))
                                            .addGap(43, 43, 43))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(128, 128, 128)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_fax, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addComponent(txt_ncc)
                                        .addComponent(txt_mcc)
                                        .addComponent(txt_dc)
                                        .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bntsua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bntnew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bntdelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bntdelete1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(27, 27, 27))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_mcc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_dc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_fax, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(29, 29, 29))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(bntnew, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bntsua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bntdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bntdelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bntnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntnewActionPerformed
        // TODO add your handling code here:
        txt_mcc.setText("");
        txt_dc.setText("");
        txt_fax.setText("");
        txt_ncc.setText("");
        txt_sdt.setText("");
        int id = 0;
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "insert into Nha_Cung_Cap (TenNCC,DiaChi,SoDT,Fax) \n"
                    + "values('null','0',0,'0')";
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            String sql1 = "  Select top 1 MaNCC from Nha_Cung_Cap order by MaNCC DESC ";
            Statement st1 = con.createStatement();
            ResultSet rs = st1.executeQuery(sql1);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            txt_mcc.setText(id + "");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_bntnewActionPerformed

    private void bntsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntsuaActionPerformed
        // TODO add your handling code here:
        String sql = " update Nha_Cung_Cap set TenNCC =? , DiaChi=? , SoDT=? , Fax=?   where MaNCC = '" + txt_mcc.getText() + "'";
        System.out.println(sql);
        Connection con = null;
        PreparedStatement ps = null;
        String a;
        try {
            con = DriverManager.getConnection(url, user, pass);
            ps = con.prepareStatement(sql);
            ps.setString(1, txt_ncc.getText());
            ps.setString(2, txt_dc.getText());
            ps.setString(3, txt_sdt.getText());
            ps.setString(4, txt_fax.getText());
            ps.execute();
            JOptionPane.showMessageDialog(this, "Update Thanh Cong!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Update That Bai!");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se2) {

            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        loaddata();
    }//GEN-LAST:event_bntsuaActionPerformed

    private void bntdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntdeleteActionPerformed
        // TODO add your handling code here:
        String sql = "delete from Nha_Cung_Cap where  MaNCC = ? ";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
            ps = con.prepareStatement(sql);
            ps.setString(1, txt_mcc.getText());
            ps.execute();
            JOptionPane.showMessageDialog(this, "Delete Thanh Cong!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Delete That Bai!");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException se2) {

            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        loaddata();
    }//GEN-LAST:event_bntdeleteActionPerformed

    private void tabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabMouseClicked
        // TODO add your handling code here:
        r = tab.getSelectedRow();
        loadlencompanel(r);
    }//GEN-LAST:event_tabMouseClicked

    private void bntdelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntdelete1ActionPerformed
        // TODO add your handling code here:
        ncc = new qlhhPanel();
        setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        add(ncc, c);
        jPanel1.setVisible(false);
        ncc.setVisible(true);


    }//GEN-LAST:event_bntdelete1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntdelete;
    private javax.swing.JButton bntdelete1;
    private javax.swing.JButton bntnew;
    private javax.swing.JButton bntsua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private id.web.martinusadyh.panel.MAbstractPanel mAbstractPanel1;
    private id.web.martinusadyh.panel.MGradientPanelBeanInfo mGradientPanelBeanInfo1;
    private javax.swing.JTable tab;
    private javax.swing.JTextField txt_dc;
    private javax.swing.JTextField txt_fax;
    private javax.swing.JTextField txt_mcc;
    private javax.swing.JTextField txt_ncc;
    private javax.swing.JTextField txt_sdt;
    // End of variables declaration//GEN-END:variables
}
