package ui;

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class MainMenuBackground extends JComponent {
  int width;
  int height;

  public MainMenuBackground(int width, int height) {
    this.width = width;
    this.height = height;
  }

  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    RenderingHints rh = new RenderingHints(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHints(rh);
    setBackground(g2d);
    setIcon(g2d);
    setAppName(g2d);

  }

  private void setBackground(Graphics2D g2d) {
    g2d.setColor(new Color(240, 248, 255));
    g2d.fill(
        new Rectangle2D.Double(0, 0, width, height));
  }

  private void setAppName(Graphics2D g2d) {

    g2d.setFont(new Font("Arial", Font.BOLD, 45));
    g2d.setColor(Color.BLACK);
    g2d.drawString("CLIENT LISTER", 285, 185);
  }

  private void setIcon(Graphics2D g2d) {
    AffineTransform reset = g2d.getTransform();
    int xPoint = 200;
    int yPoint = 120;
    int width = 75;
    int height = 75;
    Rectangle2D.Double r1 = new Rectangle2D.Double(xPoint, yPoint, width, height);
    g2d.setColor(Color.BLUE);
    g2d.rotate(Math.toRadians(15), xPoint, yPoint);
    g2d.fill(r1);
    g2d.setColor(Color.GREEN);
    g2d.rotate(Math.toRadians(20), xPoint, yPoint);
    g2d.fill(r1);
    g2d.setColor(Color.YELLOW);
    g2d.rotate(Math.toRadians(25), xPoint, yPoint);
    g2d.fill(r1);
    g2d.setTransform(reset);
  }
}
