package ql_karaokennice;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.Color;

public class Load extends JFrame {

    private JPanel contentPane;
    public static JLabel lblWait;
    public static JLabel lblPercent;
    public static JProgressBar progressBar;

    public Load() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 304);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("NNICE");
        lblNewLabel.setFont(new Font("Cambria", Font.PLAIN, 25));
        lblNewLabel.setBounds(252, 34, 102, 40);
        contentPane.add(lblNewLabel);

        lblWait = new JLabel("Đang khởi động...");
        lblWait.setFont(new Font("Cambria", Font.PLAIN, 20));
        lblWait.setBounds(66, 116, 218, 31);
        contentPane.add(lblWait);

        lblPercent = new JLabel("0%");
        lblPercent.setFont(new Font("Cambria", Font.PLAIN, 20));
        lblPercent.setBounds(271, 186, 56, 24);
        contentPane.add(lblPercent);

        progressBar = new JProgressBar();
        progressBar.setForeground(new Color(0, 0, 0));
        progressBar.setBounds(66, 154, 468, 28);
        contentPane.add(progressBar);
    }
}
