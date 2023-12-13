/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ql_karaokennice;

import Form.LoginForm;

/**
 *
 * @author PC BAO THONG
 */
public class QL_KaraokeNNice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new splashscreen.SplashScreen(null, true).setVisible(true);
        new LoginForm().setVisible(true);
    }
}
