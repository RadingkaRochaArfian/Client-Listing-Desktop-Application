package ui;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class ClientListerBackground extends JComponent {
  private int width;
  private int height;

  public ClientListerBackground(int width, int height) {
    this.width = width;
    this.height = height;
  }

  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    setBackground(g2d);
    setLeftPart(g2d);
    setRightPart(g2d);
  }

  private void setBackground(Graphics2D g2d) {
    g2d.setColor(new Color(240, 248, 255));
    g2d.fill(
        new Rectangle2D.Double(0, 0, width, height));
  }

  private void setLeftPart(Graphics2D g2d) {
    g2d.setColor(new Color(0, 0, 139));
    g2d.fill(
        new Rectangle2D.Double(width / 3 - 10, 0, 10, height));
    g2d.setColor(new Color(0, 128, 128));
    g2d.fill(
        new Rectangle2D.Double(0, 0, width / 3 - 10, height));
    g2d.setColor(Color.WHITE);
    g2d.fill(
        new Rectangle2D.Double(60, 65, 130, 3));
  }

  private void setRightPart(Graphics2D g2d) {
    g2d.setColor(new Color(200, 168, 0));
    g2d.fill(
        new Rectangle2D.Double(width / 3, 0, width, height));
    g2d.setColor(new Color(104, 151, 187));
    g2d.fill(
        new Rectangle2D.Double(280, 5, 495, 555));
    g2d.setColor(Color.WHITE);
    g2d.fill(
        new Rectangle(445, 60, 163, 3));
  }

}
