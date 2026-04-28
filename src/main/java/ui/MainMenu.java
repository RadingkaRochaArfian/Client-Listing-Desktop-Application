package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import system.AppOperator;

public class MainMenu extends JPanel {

  AppOperator op;
  int width;
  int height;
  JFrame frame;

  public MainMenu(AppOperator operator, int width, int height) {
    op = operator;
    this.width = width;
    this.height = height;
    setLayout(null);
    setOpaque(true);
  }

  public void execMainMenu() {
    frame = new JFrame("Main Menu");
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);
    setButton(frame);
    frame.setVisible(true);
    MainMenuBackground bg = new MainMenuBackground(width, height);
    bg.setBounds(0, 0, width, height);
    frame.add(bg);
  }

  private void setButton(JFrame frame) {
    JButton bMasuk = new JButton("Masuk");
    bMasuk.setPreferredSize(new Dimension(200, 50));
    bMasuk.setFont(new Font("Arial", Font.PLAIN, 20));
    bMasuk.setBackground(new Color(52, 152, 219));
    bMasuk.setForeground(Color.WHITE);
    bMasuk.setBounds(width / 2 - 100, 300, 200, 45);
    bMasuk.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Timer buffer = new Timer(100, new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            frame.dispose();
          }
        });
        buffer.setRepeats(false);
        buffer.start();
        op.runClientLister();
      }
    });
    frame.add(bMasuk);
  }
}
