/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javaapplication1.user;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author USER
 */
public class tt extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    //Thiết lập đường dẫn kết nối database
    String url = "jdbc:sqlserver://localhost:1433;databaseName=Database-mẫu";
    String user = "sa", pass = "1";
    //Biến toàn cuchj user1
    String user1 = "";

    public tt() {
        initComponents();
        //chạy mặt định nếu k có dữ liệu truyền
//        String chucvu = "";
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection con = DriverManager.getConnection(url, user, pass);
//            String sql = " select * from Nhan_Vien where username = 'admin' ";
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                lbht.setText(rs.getString("TenNV"));
//
//                lbns.setText(rs.getString("NgaySinh"));
//                boolean gt = rs.getBoolean("GioiTinh");
//                if (gt) {
//                    rdo1.setSelected(true);
//                    rd2.setSelected(false);
//                } else {
//                    rd2.setSelected(true);
//                    rdo1.setSelected(false);
//                }
//                lbcmnd.setText(rs.getString("MatKhau"));
//                lbasdt.setText(rs.getString("SoDT"));
//                if (rs.getString("Roles").equals("0")) {
//                    chucvu = "Boss";
//                } else if (rs.getString("Roles").equals("1")) {
//                    chucvu = "Admin";
//                } else {
//                    chucvu = "NhanVien";
//                }
//                labrole.setText(chucvu);
//                lbaemail.setText(rs.getString("Emial"));
//                labdc.setText(rs.getString("DiaChi"));
//                File lay = new File(getClass().getResource("/img/").toURI().getPath() + rs.getString("hinhanh") + ".jpg");
////            System.out.println(lay);
//                BufferedImage img = ImageIO.read(lay);
//                ImageIcon icon = new ImageIcon(img.getScaledInstance(128, 128,
//                        img.SCALE_SMOOTH));
//                lbaanh.setIcon(icon);
//            }
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }

    //Khi dữ liệu đã được truyền từ jframe user
    public tt(String user1, String trangthai) {
        initComponents();
        this.user1 = user1;
        String chucvu = "";
        try {
            System.out.println(user1);
            //Kết nối dữ liệu database
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from Nhan_Vien where username = '" + user1 + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lbht.setText(rs.getString("TenNV"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(rs.getString("NgaySinh"));
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                lbns.setText(dateFormat1.format(date));
                boolean gt = rs.getBoolean("GioiTinh");
                //Xét Giới Tính
                if (gt) {
                    rdo1.setSelected(true);
                    rd2.setSelected(false);
                } else {
                    rd2.setSelected(true);
                    rdo1.setSelected(false);
                }
                lbcmnd.setText(rs.getString("MatKhau"));
                lbasdt.setText(rs.getString("SoDT"));
                //Xét Quyền
                if (rs.getString("Roles").equals("0")) {
                    chucvu = "Boss";
                } else if (rs.getString("Roles").equals("1")) {
                    chucvu = "Admin";
                } else {
                    chucvu = "NhanVien";
                }
                labrole.setText(chucvu);
                labdc.setText(rs.getString("DiaChi"));
                lbaemail.setText(rs.getString("Email"));
                //Xét hình ảnh------------------
                //          Nếu tên hình ảnh khác NULL, Thì sẽ được lấy đường dẫn trực tiếp ra        
                if (!rs.getString("hinhanh").equals("null")) {
                    //Đường dẫn file lấy theo thư mục \build\classes\img
                    File lay = new File(getClass().getResource("/img/").toURI().getPath() + rs.getString("hinhanh") + ".jpg");
//              System.out.println(lay);
//              đọc đường dẫn file đã được chọn
                    BufferedImage img = ImageIO.read(lay);
                    ImageIcon icon = new ImageIcon(img.getScaledInstance(128, 128,
                            img.SCALE_SMOOTH));
//              dán ảnh lên lbaanh
                    lbaanh.setIcon(icon);
                } else {
                    //Ngược lại nếu tên hình ảnh null thì sẽ được lấy mặc định
                    lbaanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Businessman_104px.png")));
                }
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        // Không cho chọn giới tính, chỉ được xem
        rdo1.setEnabled(false);
        rd2.setEnabled(false);
        // Điều chỉnh trạng thái của username
        if (trangthai.equals("true")) {
            jLabel35.setText("online");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        rd2 = new javax.swing.JRadioButton();
        rdo1 = new javax.swing.JRadioButton();
        lbcmnd = new javax.swing.JLabel();
        lbht = new javax.swing.JLabel();
        lbns = new javax.swing.JLabel();
        lbasdt = new javax.swing.JLabel();
        labdc = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lbaemail = new javax.swing.JLabel();
        lbaanh = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        labrole = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(711, 803));

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(741, 640));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel18.setText("HỌ VÀ TÊN: ");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel20.setText("NGÀY SINH:");

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel22.setText("GIỚI TÍNH: ");

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel23.setText("CMND:");

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel25.setText("SỐ ĐIỆN THOẠI: ");

        rd2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rd2);
        rd2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rd2.setText("NỮ");
        rd2.setIconTextGap(3);

        rdo1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdo1);
        rdo1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdo1.setText("NAM");
        rdo1.setIconTextGap(3);
        rdo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo1ActionPerformed(evt);
            }
        });

        lbcmnd.setText(".................");

        lbht.setText(".................");

        lbns.setText(".................");

        lbasdt.setText(".................");

        labdc.setText(".................");

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel29.setText("ĐỊA CHỈ : ");

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel30.setText("EMAIL: ");

        lbaemail.setText(".................");

        lbaanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Businessman_104px.png"))); // NOI18N
        lbaanh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel32.setText("CHỨC VỤ: ");

        labrole.setText(".................");

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel34.setText("TRẠNG THÁI: ");

        jLabel35.setText(".................");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Đổi mật khẩu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbht, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbns, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdo1)
                                .addGap(41, 41, 41)
                                .addComponent(rd2)))
                        .addGap(108, 108, 108)
                        .addComponent(lbaanh))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(lbcmnd, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labrole, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lbasdt, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(labdc, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lbaemail, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbht, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(lbns, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdo1)
                            .addComponent(rd2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lbaanh, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lbcmnd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labrole, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbasdt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labdc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbaemail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //Đây là nơi đổi mật khẩu, sẽ được truyền username để selete
        matkhau a = new matkhau(user1);
        a.setVisible(true);
        // code dùng để tắt JFrame user chứ jpanel hiện tịa là tt(Thoát hoàn toàn Jframe user)
        ((user) tt.this.getTopLevelAncestor()).dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rdo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labdc;
    private javax.swing.JLabel labrole;
    private javax.swing.JLabel lbaanh;
    private javax.swing.JLabel lbaemail;
    private javax.swing.JLabel lbasdt;
    private javax.swing.JLabel lbcmnd;
    private javax.swing.JLabel lbht;
    private javax.swing.JLabel lbns;
    private javax.swing.JRadioButton rd2;
    private javax.swing.JRadioButton rdo1;
    // End of variables declaration//GEN-END:variables
}
