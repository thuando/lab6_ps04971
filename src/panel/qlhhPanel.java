/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class qlhhPanel extends javax.swing.JPanel {

    /**
     * Creates new form qlhhPanel
     */
    String url = "jdbc:sqlserver://localhost:1433;databaseName=Database-mẫu";
    String user = "sa", pass = "1";
    File file = null;
    Vector nv = null;
    int r = 0;
    nhacungcap_1 ncc;
    phieunhap pn;
    GridBagLayout layout = new GridBagLayout();

    public qlhhPanel() {
        initComponents();
        loaddata();
        loadlencompanel(r);
    }

    public qlhhPanel(String user) {
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
            String sql = " SELECT  MaHang,TenHang,SoLuong,DonGia,TinhTrang,Mat_Hang.MaNCC,TenNCC from Mat_Hang inner join Nha_Cung_Cap on Mat_Hang.MaNCC = Nha_Cung_Cap.MaNCC ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nv = new Vector();
                nv.add(rs.getString(1));
                nv.add(rs.getString(2));
                nv.add(rs.getString(3));
                nv.add(rs.getString(4));
                nv.add(rs.getString(5));
                nv.add(rs.getString(6));
                nv.add(rs.getString(7));
                model.addRow(nv);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select TenNCC from Nha_Cung_Cap";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            cbocc.removeAllItems();
            while (rs.next()) {
                cbocc.addItem(rs.getString(1));

            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadlencompanel(int r) {
        if (r < 0) {
            return;
        }
        txt_ma.setText(tab.getValueAt(r, 0).toString());
        txt_ten.setText(tab.getValueAt(r, 1).toString());
        lbsl.setText(tab.getValueAt(r, 2).toString());
        lbgia.setText(tab.getValueAt(r, 3).toString());
        if (tab.getValueAt(r, 4).equals(1)) {
            lbtt.setText("còn hàng");
        } else {
            lbtt.setText("hết hàng");
        }
        cbocc.setSelectedItem(tab.getValueAt(r, 6).toString());

//        System.out.println(tab.getValueAt(r, 8) + "");
//        if (!(tab.getValueAt(r, 8) + "").equals("null")) {
//            try {
////            File lay = new File(getClass().getResource("img\\" + tba.getValueAt(r, 7).toString()).toURI());
//                File lay = new File(getClass().getResource("/img/").toURI().getPath() + tab.getValueAt(r, 8).toString());
//                System.out.println(lay);
//                BufferedImage img = ImageIO.read(lay);
//                ImageIcon icon = new ImageIcon(img.getScaledInstance(128, 128,
//                        img.SCALE_SMOOTH));
//                layanh.setIcon(icon);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        } else {
//            layanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Shopping Bag_96px.png"))); // NOI18N
//        }
    }

    public boolean kt() {
        if (txt_ten.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "chưa nhập tên hàng", "Error", JOptionPane.ERROR_MESSAGE);
            txt_ten.requestFocus();
            return false;
        }
        return true;
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
        txt_search = new javax.swing.JButton();
        bnt_search = new javax.swing.JTextField();
        txt_ten = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        cbocc = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        bnt_sua = new javax.swing.JButton();
        bnt_luu = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbsl = new javax.swing.JLabel();
        lbtt = new javax.swing.JLabel();
        lbgia = new javax.swing.JLabel();
        txt_ma = new javax.swing.JLabel();
        bnt_luu1 = new javax.swing.JButton();
        bnt_luu2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 153, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txt_search.setBackground(new java.awt.Color(255, 255, 255));
        txt_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search_25px.png"))); // NOI18N
        txt_search.setText("SEARCH");
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });

        txt_ten.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_ten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_tenMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Tên Hàng");

        jLabel32.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel32.setText("NHÀ CUNG CẤP:");

        cbocc.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbocc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbocc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboccActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel31.setText("SỐ LƯỢNG:");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel27.setText("GIÁ:");

        bnt_sua.setBackground(new java.awt.Color(255, 255, 255));
        bnt_sua.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        bnt_sua.setText("Tạo Mới");
        bnt_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_suaActionPerformed(evt);
            }
        });

        bnt_luu.setBackground(new java.awt.Color(255, 255, 255));
        bnt_luu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        bnt_luu.setText("Save");
        bnt_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_luuActionPerformed(evt);
            }
        });

        tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hàng", "Tên Hàng", "Số Lương", "Đơn Giá", "Tình Trạng", "MaNCC", "TNCC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab);
        if (tab.getColumnModel().getColumnCount() > 0) {
            tab.getColumnModel().getColumn(0).setMinWidth(0);
            tab.getColumnModel().getColumn(0).setMaxWidth(0);
            tab.getColumnModel().getColumn(4).setMinWidth(0);
            tab.getColumnModel().getColumn(4).setMaxWidth(0);
            tab.getColumnModel().getColumn(5).setMinWidth(0);
            tab.getColumnModel().getColumn(5).setMaxWidth(0);
            tab.getColumnModel().getColumn(6).setMinWidth(0);
            tab.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Mã Hàng");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tinh Trạng");

        lbsl.setText(".................");

        lbtt.setText(".................");

        lbgia.setText(".................");

        txt_ma.setText(".................");

        bnt_luu1.setBackground(new java.awt.Color(255, 255, 255));
        bnt_luu1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        bnt_luu1.setText("Hàng Về");
        bnt_luu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_luu1ActionPerformed(evt);
            }
        });

        bnt_luu2.setBackground(new java.awt.Color(255, 255, 255));
        bnt_luu2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        bnt_luu2.setText("Nhà Cung Cấp");
        bnt_luu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_luu2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(bnt_sua)
                                        .addComponent(jLabel32))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(cbocc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(78, 78, 78))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(30, 30, 30)
                                            .addComponent(bnt_luu)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel31)
                                            .addGap(26, 26, 26)
                                            .addComponent(lbsl, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel27)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbgia, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(10, 10, 10))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbtt, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(bnt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txt_search))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(404, 404, 404)
                            .addComponent(bnt_luu2)
                            .addGap(29, 29, 29)
                            .addComponent(bnt_luu1)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bnt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel31)
                                .addComponent(lbsl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(4, 4, 4)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(5, 5, 5)
                            .addComponent(jLabel27)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bnt_sua)
                                .addComponent(bnt_luu)
                                .addComponent(bnt_luu2)
                                .addComponent(bnt_luu1)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(16, 16, 16)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel32)
                                        .addComponent(cbocc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbgia, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lbtt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(86, 86, 86)))
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

    private void tabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabMouseClicked
        // TODO add your handling code here:
        r = tab.getSelectedRow();
        loadlencompanel(r);
        bnt_luu.setEnabled(true);
    }//GEN-LAST:event_tabMouseClicked

    private void bnt_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_luuActionPerformed
        // TODO add your handling code here:
        String mancc = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select MaNCC from Nha_Cung_Cap where TenNCC like '" + cbocc.getSelectedItem() + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            cbocc.removeAllItems();
            while (rs.next()) {
                mancc = rs.getString(1);

            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (kt()) {
            String sql = " update Mat_Hang set TenHang = ?,MaNCC=?   where MaHang = ? ";
            System.out.println(sql);
            Connection con = null;
            PreparedStatement ps = null;
            String a;
            try {
                con = DriverManager.getConnection(url, user, pass);
                ps = con.prepareStatement(sql);
                ps.setString(1, txt_ten.getText());
//                ps.setString(2, file.getName());
                ps.setString(2, mancc);
                ps.setString(3, txt_ma.getText());
                ps.execute();
                JOptionPane.showMessageDialog(this, "Update Thanh Cong!");
                loaddata();
                bnt_sua.setVisible(true);
                bnt_search.setVisible(true);
                bnt_luu.setVisible(false);
                tab.setVisible(true);
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

        }

    }//GEN-LAST:event_bnt_luuActionPerformed

    private void bnt_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_suaActionPerformed
        // TODO add your handling code here:
//        layanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Shopping Bag_96px.png"))); // NOI18N
        bnt_luu.setEnabled(true);
        bnt_search.setVisible(false);
        bnt_sua.setVisible(false);
        tab.setVisible(false);
        int id = 0;
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = " INSERT INTO Mat_Hang(TenHang,SoLuong,DonGia,TinhTrang,MaNCC) values('null','0','0','0','0') ";
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            String sql1 = "  Select top 1 MaHang from Mat_Hang order by MaHang DESC ";
            Statement st1 = con.createStatement();
            ResultSet rs = st1.executeQuery(sql1);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        txt_ma.setText(id + "");
        loaddata();
    }//GEN-LAST:event_bnt_suaActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tab.getModel();
        model.setRowCount(0);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = " SELECT  MaHang,TenHang,SoLuong,DonGia,TinhTrang,Mat_Hang.MaNCC,TenNCC from Mat_Hang inner join Nha_Cung_Cap on Mat_Hang.MaNCC = Nha_Cung_Cap.MaNCC"
                    + " where MaHang like '%" + txt_search.getText() + "%'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nv = new Vector();
                nv.add(rs.getString(1));
                nv.add(rs.getString(2));
                nv.add(rs.getString(3));
                nv.add(rs.getString(4));
                nv.add(rs.getString(5));
                nv.add(rs.getString(6));
                nv.add(rs.getString(7));
                model.addRow(nv);

            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Search That Bai!");
        }
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_tenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tenMouseClicked
        // TODO add your handling code here:
        txt_ten.setText("");
    }//GEN-LAST:event_txt_tenMouseClicked

    private void cboccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboccActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboccActionPerformed

    private void bnt_luu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_luu1ActionPerformed
        // TODO add your handling code here:
        pn = new phieunhap();
        setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        add(pn, c);
        jPanel1.setVisible(false);
        pn.setVisible(true);
    }//GEN-LAST:event_bnt_luu1ActionPerformed

    private void bnt_luu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_luu2ActionPerformed
        // TODO add your handling code here:
        ncc = new nhacungcap_1();
        setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        add(ncc, c);
        jPanel1.setVisible(false);
        ncc.setVisible(true);
    }//GEN-LAST:event_bnt_luu2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnt_luu;
    private javax.swing.JButton bnt_luu1;
    private javax.swing.JButton bnt_luu2;
    private javax.swing.JTextField bnt_search;
    private javax.swing.JButton bnt_sua;
    private javax.swing.JComboBox<String> cbocc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbgia;
    private javax.swing.JLabel lbsl;
    private javax.swing.JLabel lbtt;
    private javax.swing.JTable tab;
    private javax.swing.JLabel txt_ma;
    private javax.swing.JButton txt_search;
    private javax.swing.JTextField txt_ten;
    // End of variables declaration//GEN-END:variables
}
