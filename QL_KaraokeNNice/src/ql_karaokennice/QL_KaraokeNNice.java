/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ql_karaokennice;

import gui.LoginForm;

/**
 *
 * @author PC BAO THONG
 */
public class QL_KaraokeNNice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Load ld = new Load();
//        LoginForm lgf = new LoginForm();
//        ld.setVisible(true);
//        for (int i = 1; i <= 100; i++) {
//            try {
//                Thread.sleep(40);
//                ld.lblPercent.setText(i + "%");
//                ld.progressBar.setValue(i);
//                if (i >= 40 && i < 90) {
//                    ld.lblWait.setText("Kết nối đến Database...");
//                } else if (i >= 90) {
//                    ld.lblWait.setText("Vui lòng chờ...");
//                }
//                if (i == 100) {
//                    ld.setVisible(false);
//                    lgf.setVisible(true);
//                }
//            } catch (InterruptedException ex) {
//
//            }
//        }
new LoginForm().setVisible(true);
    }
}
