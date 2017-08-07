/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class NewClass {
    
      
    File dd = new File(getClass().getResource("hoadon.jrxml").getPath());
    public static void main(String[] args) {
        String chuoien = "đỗ thành nam";
     String cat[] = chuoien.split("[ ]");
        System.out.println(cat.length);
//         String a = "ThuanDCnhanvien0009";
//            System.out.println(a.lastIndexOf("nhanvien"));
//            String ms = a.substring(a.lastIndexOf("nh"), a.length());
//            String b = "ThuanDCnhanvien0009";
//            String bms = b.replace("nhanvien","admin");
//            System.out.println(bms+"ms nè");
//             System.out.println(""); 
//
//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//Date date = new Date();
//System.out.println(dateFormat.format(date));
//        System.out.println(dateFormat.format(date).replace("-", ""));
//            System.out.println(b.length()-12+"fuck nam+=");
//            String ms1 =b.substring(0, b.length()-12);
//            System.out.println(ms);
//            System.out.println(ms1+"admin");
    }
}
