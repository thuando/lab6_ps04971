/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import com.sun.glass.events.KeyEvent;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.user;
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
public class qlnv extends javax.swing.JPanel {

    /**
     * Creates new form qlnvPanel
     */
    String url = "jdbc:sqlserver://localhost:1433;databaseName=Database-mẫu";
    String user = "sa", pass = "1";
    File file = null;
    Vector nv = null;
    int r = 0, id = 0;
    String c = null,manv=null,role= null;//Chuỗi cắt chữ
    boolean ktcrole = true;

    public qlnv() throws ParseException {
        initComponents();
        loaddata();
        loadlencompanel(r);
        //code chạy 1 dòng trên table
        tba.getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tba.getColumnModel().getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bntluu.setEnabled(false);
        txtma.setEnabled(false);
    }

    public qlnv(String manv,String role) throws ParseException {
       initComponents();
        loaddata();
        loadlencompanel(r);
        //code chạy 1 dòng trên table
        tba.getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tba.getColumnModel().getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bntluu.setEnabled(false);
        txtma.setEnabled(false);
        this.manv=manv;
        this.role=role;
    }

    public void loaddata() {
        DefaultTableModel model = (DefaultTableModel) tba.getModel();
        model.setRowCount(0);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from Nhan_Vien";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nv = new Vector();
                nv.add(rs.getString("MaNV"));
                nv.add(rs.getString("TenNV"));
                nv.add(rs.getString("NgaySinh"));
                boolean gt = rs.getBoolean("GioiTinh");
                if (gt) {
                    nv.add("Nam");
                } else {
                    nv.add("Nữ");
                }
                nv.add(rs.getString("SoDT"));
                nv.add(rs.getString("DiaChi"));
                nv.add(rs.getString("Email"));
                nv.add(rs.getString("hinhanh"));
                nv.add(rs.getString("Roles"));
                nv.add(rs.getString("MatKhau"));
                nv.add(rs.getString("username"));
                nv.add(rs.getString("TinhTrang"));
                model.addRow(nv);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean kt() {
        boolean ktlich = true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            String date = sdf.format(txt_lich.getDate());
            ktlich = true;
        } catch (Exception e) {
            ktlich = false;
        }
        boolean status = validate.validateEmail(txtemail.getText());
        if (txtma.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "chưa nhập mã nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
            txtma.requestFocus();
            return false;
        } else if (txt_ten.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "chưa nhập tên nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
            txt_ten.requestFocus();
            return false;
        } else if (!ktlich) {
            JOptionPane.showMessageDialog(this, "chưa nhập ngày sinh", "Error", JOptionPane.ERROR_MESSAGE);
            txt_lich.requestFocus();
            return false;
        } else if (txtsodt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "chưa nhập sđt", "Error", JOptionPane.ERROR_MESSAGE);
            txtsodt.requestFocus();
            return false;
        } else if (txtsodt.getText().length() != 10 && txtsodt.getText().length() != 11) {
//            System.out.println(txtsodt.getText().length());
//            System.out.println(txtsodt.getText().length() != 10);
//            System.out.println(txtsodt.getText().length() != 11);
//            System.out.println(txtsodt.getText().length() != 10 && txtsodt.getText().length() != 11);
            JOptionPane.showMessageDialog(this, "dây Không phải là số điện thoại", "Error", JOptionPane.ERROR_MESSAGE);
            txtsodt.requestFocus();
            return false;
        } else if (txtsodt.getText().length() == 10 && !txtsodt.getText().substring(0, 2).equals("09")) {

//            System.out.println(txtsodt.getText().length() == 10 && !txtsodt.getText().substring(0, 2).equals("09"));
            JOptionPane.showMessageDialog(this, "nhập sai đâu số, có 10 số", "Error", JOptionPane.ERROR_MESSAGE);
            txtsodt.requestFocus();
            return false;

        } else if (txtsodt.getText().length() == 11 && !txtsodt.getText().substring(0, 2).equals("01")) {
//            System.out.println(txtsodt.getText().length());
//            System.out.println(txtsodt.getText().length() != 10);
//            System.out.println(txtsodt.getText().length() != 11);
//            System.out.println(txtsodt.getText().length() != 10 || txtsodt.getText().length() != 11);
            JOptionPane.showMessageDialog(this, "nhập sai đầu số, có 11 sô", "Error", JOptionPane.ERROR_MESSAGE);
            txtsodt.requestFocus();
            return false;
        } else if (txtemail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "chưa nhập email nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
            txtemail.requestFocus();
            return false;
        } else if (status) {
            txtemail.setText(txtemail.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Not Valid Email", "Error", JOptionPane.ERROR_MESSAGE);
            txtemail.requestFocus();
            return false;
        }
        System.out.println(txtemail.getText());
        return true;
    }

    public void loadlencompanel(int r) throws ParseException {
        String chucvu = "";
        if (r < 0) {
            return;
        }
        txtma.setText(tba.getValueAt(r, 0).toString());
        txt_ten.setText(tba.getValueAt(r, 1).toString());
        String dateValue = tba.getValueAt(r, 2).toString(); // What ever column
//        System.out.println(dateValue);
        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
//        System.out.println(date);
        txt_lich.setDate(date);
        String gt = tba.getValueAt(r, 3).toString();
        if (gt.equals("Nam")) {
            rdo1.setSelected(true);
            rdo2.setSelected(false);
        } else {
            rdo2.setSelected(true);
            rdo1.setSelected(false);
        }
        txtsodt.setText(tba.getValueAt(r, 4).toString());
        txtdiachi.setText(tba.getValueAt(r, 5).toString());
        txtemail.setText(tba.getValueAt(r, 6).toString());
        if (tba.getValueAt(r, 8).toString().equals("0")) {
            chucvu = "Boss";
        }else if (tba.getValueAt(r, 8).toString().equals("1")) {
            chucvu = "Admin";
        } else {
            chucvu = "NhanVien";
        }
        cbrole.setSelectedItem(chucvu);
        if (!(tba.getValueAt(r, 7) + "").equals("")) {
            try {
//            File lay = new File(getClass().getResource("img\\" + tba.getValueAt(r, 7).toString()).toURI());
                File lay = new File(getClass().getResource("/img/").toURI().getPath() + tba.getValueAt(r, 7).toString() + ".jpg");
                BufferedImage img = ImageIO.read(lay);
                ImageIcon icon = new ImageIcon(img.getScaledInstance(128, 128,
                        img.SCALE_SMOOTH));
                labanh.setIcon(icon);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            labanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Businessman_104px.png")));
        }
        lab_mk.setText(tba.getValueAt(r, 9).toString());
        lab_username.setText(tba.getValueAt(r, 10).toString());
        if (tba.getValueAt(r, 10).toString().equals("True")) {
            jLabel12.setText("Online");
        } else {
            jLabel12.setText("Offline");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtsearch = new javax.swing.JTextField();
        bntsearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tba = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        rdo1 = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        txtsodt = new javax.swing.JTextField();
        txtma = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        bntthem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        rdo2 = new javax.swing.JRadioButton();
        txt_lich = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_ten = new javax.swing.JTextField();
        txtdiachi = new javax.swing.JTextField();
        lab_mk1 = new javax.swing.JLabel();
        lab_mk2 = new javax.swing.JLabel();
        lab_mk3 = new javax.swing.JLabel();
        lab_mk4 = new javax.swing.JLabel();
        lab_mk5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labanh = new javax.swing.JLabel();
        bnt_lay = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        lab_username = new javax.swing.JLabel();
        lab_mk = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbrole = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        bntsua = new javax.swing.JButton();
        bntluu = new javax.swing.JButton();
        bntxoa = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        bntsearch.setBackground(new java.awt.Color(255, 255, 255));
        bntsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search_25px.png"))); // NOI18N
        bntsearch.setText("SEARCH");
        bntsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntsearchActionPerformed(evt);
            }
        });

        tba.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MaNhanvien", "Họ và tên", "Ngày sinh", "Giới tính", "SDT", "Địa Chỉ", "email", "hinh", "role", "matkhau", "UserName", "Tinhtrang"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tba.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tbaMouseDragged(evt);
            }
        });
        tba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tba);
        if (tba.getColumnModel().getColumnCount() > 0) {
            tba.getColumnModel().getColumn(0).setMinWidth(0);
            tba.getColumnModel().getColumn(0).setMaxWidth(0);
            tba.getColumnModel().getColumn(7).setMinWidth(0);
            tba.getColumnModel().getColumn(7).setMaxWidth(0);
            tba.getColumnModel().getColumn(8).setMinWidth(0);
            tba.getColumnModel().getColumn(8).setMaxWidth(0);
            tba.getColumnModel().getColumn(9).setMinWidth(0);
            tba.getColumnModel().getColumn(9).setMaxWidth(0);
            tba.getColumnModel().getColumn(11).setMinWidth(0);
            tba.getColumnModel().getColumn(11).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtsearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(51, 51, 51)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        rdo1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdo1);
        rdo1.setText("NAM");
        rdo1.setIconTextGap(3);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("ĐỊA CHỈ");

        txtsodt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtsodt.setText("0987654321");
        txtsodt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsodtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsodtKeyTyped(evt);
            }
        });

        txtma.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtemail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtemail.setText("1@gmail.com");
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtemailKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("SỐ ĐT ");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("GIỚI TÍNH");

        bntthem.setBackground(new java.awt.Color(255, 255, 255));
        bntthem.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        bntthem.setText("Tạo Mới");
        bntthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntthemActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("MaNV");

        rdo2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdo2);
        rdo2.setText("NỮ");
        rdo2.setIconTextGap(3);

        txt_lich.setDateFormatString("dd/MM/yyyy");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("HỌ VÀ TÊN ");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("EMAIL ");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("NGÀY SINH");

        txt_ten.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_ten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txt_tenMouseReleased(evt);
            }
        });
        txt_ten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenActionPerformed(evt);
            }
        });
        txt_ten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tenKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tenKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_tenKeyTyped(evt);
            }
        });

        txtdiachi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lab_mk1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lab_mk1.setForeground(new java.awt.Color(255, 0, 0));
        lab_mk1.setText("*");

        lab_mk2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lab_mk2.setForeground(new java.awt.Color(255, 0, 0));
        lab_mk2.setText("*");

        lab_mk3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lab_mk3.setForeground(new java.awt.Color(255, 0, 0));
        lab_mk3.setText("*");

        lab_mk4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lab_mk4.setForeground(new java.awt.Color(255, 0, 0));
        lab_mk4.setText("*");

        lab_mk5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lab_mk5.setForeground(new java.awt.Color(255, 0, 0));
        lab_mk5.setText("*");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(bntthem))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(5, 5, 5))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(21, 21, 21)))
                            .addComponent(jLabel14)
                            .addComponent(jLabel1))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(rdo1)
                                .addGap(17, 17, 17)
                                .addComponent(rdo2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lab_mk1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lab_mk2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txt_lich, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 115, Short.MAX_VALUE))
                                    .addComponent(txt_ten, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtma)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lab_mk3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lab_mk4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lab_mk5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdiachi)
                                    .addComponent(txtemail)
                                    .addComponent(txtsodt))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(lab_mk1))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_lich, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdo1)
                                .addComponent(jLabel14))
                            .addComponent(rdo2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsodt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(lab_mk5))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(lab_mk4)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lab_mk2))
                        .addGap(64, 64, 64)
                        .addComponent(lab_mk3)))
                .addGap(18, 18, 18)
                .addComponent(bntthem)
                .addGap(43, 43, 43))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        labanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Businessman_104px.png"))); // NOI18N
        labanh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        bnt_lay.setText("image");
        bnt_lay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_layActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("UserName");

        lab_username.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lab_username.setText(".....................");

        lab_mk.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lab_mk.setText(".....................");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText("CHỨC VỤ: ");

        cbrole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boss", "Admin", "NhanVien" }));
        cbrole.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbroleItemStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("TRẠNG THÁI: ");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText(".....................");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("Reset Mật Khẩu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bntsua.setBackground(new java.awt.Color(255, 255, 255));
        bntsua.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        bntsua.setText("SỬA");
        bntsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntsuaActionPerformed(evt);
            }
        });

        bntluu.setBackground(new java.awt.Color(255, 255, 255));
        bntluu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        bntluu.setText("LƯU");
        bntluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntluuActionPerformed(evt);
            }
        });

        bntxoa.setBackground(new java.awt.Color(255, 255, 255));
        bntxoa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        bntxoa.setText("XÓA");
        bntxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntxoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(bntsua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntluu)
                .addGap(18, 18, 18)
                .addComponent(bntxoa)
                .addGap(82, 82, 82))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntluu)
                    .addComponent(bntxoa)
                    .addComponent(bntsua))
                .addGap(24, 24, 24))
        );

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Mật Khẩu");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(labanh)
                                .addGap(18, 18, 18)
                                .addComponent(bnt_lay))
                            .addComponent(jLabel18))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lab_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addComponent(lab_mk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbrole, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 109, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labanh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnt_lay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lab_username))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lab_mk))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbrole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bntthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntthemActionPerformed
        // TODO add your handling code here:
        ktcrole = false;
        labanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Businessman_104px.png"))); // NOI18N
        txtma.setEnabled(true);
        txt_ten.setEnabled(true);
        txt_lich.setEnabled(true);
        txtdiachi.setEnabled(true);
        txtemail.setEnabled(true);
        txtsodt.setEnabled(true);
        cbrole.setEnabled(true);
        rdo1.setEnabled(true);
        rdo2.setEnabled(true);
        txtma.setText("");
        txt_ten.setText("");
        Date a = null;
        txt_lich.setDate(a);
        txtemail.setText("");
        txtsodt.setText("");
        txtdiachi.setText("");
        cbrole.setSelectedIndex(0);
        bntluu.setEnabled(true);
        bntsua.setEnabled(false);
        bntxoa.setEnabled(false);
        tba.setVisible(false);
        //code tự động tăng mã số nhân viên
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
//           
            String sql1 = "  Select top 1 MaNV from Nhan_Vien order by MaNV DESC ";
            Statement st1 = con.createStatement();
            ResultSet rs = st1.executeQuery(sql1);
            while (rs.next()) {
                id = rs.getInt(1)+1;
            }
            txtma.setText(id + "");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_bntthemActionPerformed

    private void bntsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntsuaActionPerformed
        // TODO add your handling code here:
        if (r == 0 && r == 1) {
            JOptionPane.showMessageDialog(this, "Đây là Ban Quản Lý Dự Án , Bạn không được sửa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            String sql = " update Nhan_Vien set TenNV = ?,NgaySinh = ?,Email = ?,SoDT = ?,Gioitinh = ?,DiaChi = ?,hinhanh  = ?  where  MaNV = ? ";
            System.out.println(sql);
            Connection con = null;
            PreparedStatement ps = null;
            String a;
            try {
                con = DriverManager.getConnection(url, user, pass);
                ps = con.prepareStatement(sql);

                ps.setString(1, txt_ten.getText());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(txt_lich.getDate());
                System.out.println(date);
                ps.setString(2, date);
                System.out.println(date);
                ps.setString(3, txtemail.getText());
                ps.setString(4, txtsodt.getText());
                if (rdo1.isSelected()) {
                    a = "1";
                } else {
                    a = "0";
                }
                ps.setString(5, a);
                ps.setString(6, txtdiachi.getText());
                if (file == null) {
                    ps.setString(7, tba.getValueAt(r, 7).toString());
                } else {
                    ps.setString(7, file.getName().replace(".jpg", ""));
                }
                ps.setString(8, txtma.getText());
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
        }
    }//GEN-LAST:event_bntsuaActionPerformed

    private void bntluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntluuActionPerformed
        // TODO add your handling code here:
        ktcrole = true;
        bntsua.setVisible(true);
        bntxoa.setVisible(true);
        tba.setVisible(true);
        if (kt()) {
           
             String sql = "INSERT INTO Nhan_Vien (TenNV,NgaySinh,Email,Roles,SoDT,Gioitinh,DiaChi,MatKhau,username,hinhanh,TinhTrang) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            System.out.println(sql);
            Connection con = null;
            PreparedStatement ps = null;
            String a;
            try {
                con = DriverManager.getConnection(url, user, pass);
                ps = con.prepareStatement(sql);
                ps.setString(1, txt_ten.getText());//ten nhan vien
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(txt_lich.getDate());
                System.out.println(date + "123");
                ps.setString(2, date);//ngay sinh
                System.out.println(date);
                ps.setString(3, txtemail.getText());//email
                if(cbrole.getSelectedItem().equals("Admin")){//roles
                    ps.setString(4, "1");//roles
                }else{
                    ps.setString(4, "2");//roles
                }
                
                ps.setString(5, txtsodt.getText());
                if (rdo1.isSelected()) {
                    a = "1";
                } else {
                    a = "0";
                }
                ps.setString(6, a);
                ps.setString(7, txtdiachi.getText());
                ps.setString(8, lab_mk.getText());
                ps.setString(9, lab_username.getText());
                if (file != null) {
                    ps.setString(10, file.getName().replace(".jpg", ""));
                } else {
                    ps.setString(10, "");
                }
                 ps.setString(11, "0");
                ps.execute();
                JOptionPane.showMessageDialog(this, "Insert Thanh Cong!");
                loaddata();
                loadlencompanel(r);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Insert That Bai!");
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

    }//GEN-LAST:event_bntluuActionPerformed

    private void bntxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntxoaActionPerformed
        // TODO add your handling code here:
        if (r == 0 || r == 1) {
            JOptionPane.showMessageDialog(this, "Đây là Ban Quản Lý Dự Án, Không được xóa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            String sql = null;
            boolean ktrole = true;
            System.out.println(manv+"123123");
            if(role.equals("1")&&manv.equals("2")){
             sql = " delete from Nhan_Vien where  MaNV = ? ";
              }
             if(role.equals("1")&&!manv.equals("2")){
             sql = " delete from Nhan_Vien where  MaNV = ? and Roles = ? ";
             ktrole = false;
              }
            
            Connection con = null;
            PreparedStatement ps = null;
            try {
                con = DriverManager.getConnection(url, user, pass);
                ps = con.prepareStatement(sql);
                ps.setString(1, txtma.getText());
                if(cbrole.getSelectedItem().equals("NhanVien")&&ktrole==false){
                     ps.setString(2, cbrole.getSelectedItem().toString());
                }
                ps.execute();
                JOptionPane.showMessageDialog(this, "Delete Thanh Cong!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Bạn Không Được Xóa Admin Khác, chỉ xóa nhân viên");
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
        }
    }//GEN-LAST:event_bntxoaActionPerformed

    private void bnt_layActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_layActionPerformed
        // TODO add your handling code here:
        JFileChooser j = new JFileChooser();
        File outputfile = null;
        FileNameExtensionFilter loc = new FileNameExtensionFilter("Images", "jpg");
        j.setFileFilter(loc);
        if (j.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = j.getSelectedFile();
            BufferedImage img;
            try {
                img = ImageIO.read(file);
//                  Hàm Lấy tên và thay thế ".jpg"
//                String layten = file.getName().replace(".jpg","");
//                System.out.println(layten);

//                File outputfile = new File(getClass().getResource("img\\").getPath() + file.getName());
                outputfile = new File(getClass().getResource("/img/").toURI().getPath() + file.getName());
//                System.out.println(outputfile);
                ImageIO.write(img, "jpg", outputfile);
                ImageIcon icon = new ImageIcon(img.getScaledInstance(128, 128,
                        img.SCALE_SMOOTH));
                labanh.setIcon(icon);
                JOptionPane.showMessageDialog(this, "Up ảnh thành công");

            } catch (IOException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(this, "up ảnh thất bại", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (URISyntaxException ex) {
                Logger.getLogger(qlnv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bnt_layActionPerformed

    private void tbaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbaMouseClicked
        // TODO add your handling code here:
        ktcrole=false;
        tba.enable(true);
        r = tba.getSelectedRow();
        try {
            loadlencompanel(r);
        } catch (ParseException ex) {
            Logger.getLogger(qlnv.class.getName()).log(Level.SEVERE, null, ex);
        }
        bntluu.setEnabled(false);
        txtma.setEnabled(false);
        bntsua.setEnabled(true);
        bntxoa.setEnabled(true);
        ktcrole=true;
    }//GEN-LAST:event_tbaMouseClicked

    private void tbaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbaMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tbaMousePressed

    private void tbaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbaMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tbaMouseReleased

    private void tbaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbaMouseDragged
        // TODO add your handling code here:

    }//GEN-LAST:event_tbaMouseDragged

    private void txtemailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtemailKeyPressed

    private void txtsodtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsodtKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtsodtKeyPressed

    private void txtsodtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsodtKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtsodtKeyTyped

    private void bntsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntsearchActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tba.getModel();
        model.setRowCount(0);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from Nhan_Vien where MaNV like '%" + txtsearch.getText() + "%'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nv = new Vector();
                nv.add(rs.getString("MaNV"));
                nv.add(rs.getString("TenNV"));
                nv.add(rs.getString("NgaySinh"));

                boolean gt = rs.getBoolean("GioiTinh");
                if (gt) {
                    nv.add("Nam");
                } else {
                    nv.add("Nữ");
                }
                nv.add(rs.getString("SoDT"));
                nv.add(rs.getString("DiaChi"));
                nv.add(rs.getString("Email"));
                nv.add(rs.getString("hinhanh"));
                nv.add(rs.getString("Roles"));
                nv.add(rs.getString("MatKhau"));
                nv.add(rs.getString("username"));
                model.addRow(nv);

            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Search That Bai!");
        }
    }//GEN-LAST:event_bntsearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String sql = " update Nhan_Vien set MatKhau = '123' where MaNV like '%" + txtma.getText() + "%' ";

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(this, "Sửa Thành Công!");
            loaddata();
            loadlencompanel(r);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_tenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tenKeyPressed
        // TODO add your handling code here:


    }//GEN-LAST:event_txt_tenKeyPressed

    private void txt_tenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tenKeyReleased
        // TODO add your handling code here:
        int ktchuoi = 0;
        String d = removeAccent(txt_ten.getText());
        System.out.println(d + "123");

        String cat[] = d.split("[ ]");
        System.out.println(cat.length);
        String cchuoi = "";
        if (cat.length > 1) {

            for (int i = 0; i < cat.length - 1; i++) {
                cchuoi = cat[cat.length - 1];
                System.out.println(c + "đây là chuỗi đã cắt");
            }
        } else {
            cchuoi = d;
        }

//        System.out.println((cat.length - 1));
//        System.out.println(cat.length);
        lab_username.setText(cchuoi.toLowerCase() + txtma.getText().toLowerCase());
    }//GEN-LAST:event_txt_tenKeyReleased

    private void txt_tenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenActionPerformed

    private void cbroleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbroleItemStateChanged
        // TODO add your handling code here:â
        if(ktcrole){
        if(cbrole.getSelectedItem().equals("Boss")){
              cbrole.setSelectedIndex(2);
             JOptionPane.showMessageDialog(this, "Đây là Boss Bạn k có quyền chỉnh", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }//GEN-LAST:event_cbroleItemStateChanged

    private void txt_tenMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tenMouseReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_tenMouseReleased

    private void txt_tenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tenKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if ((Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_tenKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnt_lay;
    private javax.swing.JButton bntluu;
    private javax.swing.JButton bntsearch;
    private javax.swing.JButton bntsua;
    private javax.swing.JButton bntthem;
    private javax.swing.JButton bntxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbrole;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lab_mk;
    private javax.swing.JLabel lab_mk1;
    private javax.swing.JLabel lab_mk2;
    private javax.swing.JLabel lab_mk3;
    private javax.swing.JLabel lab_mk4;
    private javax.swing.JLabel lab_mk5;
    private javax.swing.JLabel lab_username;
    private javax.swing.JLabel labanh;
    private javax.swing.JRadioButton rdo1;
    private javax.swing.JRadioButton rdo2;
    private javax.swing.JTable tba;
    private com.toedter.calendar.JDateChooser txt_lich;
    private javax.swing.JTextField txt_ten;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsodt;
    // End of variables declaration//GEN-END:variables
 private static char[] SPECIAL_CHARACTERS = {' ', '!', '"', '#', '$', '%',
        '*', '+', ',', ':', '<', '=', '>', '?', '@', '[', '\\', ']', '^',
        '`', '|', '~', 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò',
        'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê',
        'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ',
        'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ',
        'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ',
        'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế',
        'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị',
        'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ', 'ỗ', 'Ộ',
        'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ',
        'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự',};

    private static char[] REPLACEMENTS = {' ', '\0', '\0', '\0', '\0', '\0',
        '\0', '_', '\0', '_', '\0', '\0', '\0', '\0', '\0', '\0', '_',
        '\0', '\0', '\0', '\0', '\0', 'A', 'A', 'A', 'A', 'E', 'E', 'E',
        'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a',
        'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
        'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a',
        'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
        'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e',
        'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I',
        'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
        'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
        'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
        'U', 'u',};

    public static String toUrlFriendly(String s) {
        int maxLength = Math.min(s.length(), 236);
        char[] buffer = new char[maxLength];
        int n = 0;
        for (int i = 0; i < maxLength; i++) {
            char ch = s.charAt(i);
            buffer[n] = removeAccent(ch);
            // skip not printable characters
            if (buffer[n] > 31) {
                n++;
            }
        }
        // skip trailing slashes
        while (n > 0 && buffer[n - 1] == '/') {
            n--;
        }
        return String.valueOf(buffer, 0, n);
    }

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SPECIAL_CHARACTERS, ch);
        if (index >= 0) {
            ch = REPLACEMENTS[index];
        }
        return ch;
    }

    public static String removeAccent(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();

    }
}
