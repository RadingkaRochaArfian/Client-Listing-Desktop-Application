package ui;

import database.DataOperator;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import system.AppOperator;
import java.awt.event.*;

public class ClientLister {
  int width;
  int height;
  AppOperator op;
  JFrame frame;
  JTextField tfName, tfEmail, tfPhone, tfAddress;
  DefaultTableModel clientTable;
  JTable table;

  public ClientLister(AppOperator operator, int width, int height) {
    op = operator;
    this.width = width;
    this.height = height;
    execClientLister();
  }

  private void execClientLister() {
    frame = new JFrame("Client Lister");
    frame.setSize(width, height);
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    DataOperator.init();
    addLeftSideButtons();
    addRightSideButtons();
    ClientListerBackground bg = new ClientListerBackground(width, height);
    bg.setBounds(0, 0, width, height);
    frame.add(bg);
    loadFromFile();
    DataOperator.syncFromCSV("bin/ClientList.csv");
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent e) {
        saveToFile();
        DataOperator.syncFromCSV("bin/ClientList.csv");
        System.exit(0);
      }
    });
  }

  private void saveToFile() {
    try {
      java.io.File dir = new java.io.File("bin");
      if (!dir.exists()) {
        dir.mkdirs();
      }
      java.io.FileWriter writer = new java.io.FileWriter("bin/ClientList.csv");
      for (int i = 0; i < clientTable.getRowCount(); i++) {
        writer.write(clientTable.getValueAt(i, 0) + ",");
        writer.write(clientTable.getValueAt(i, 1) + ",");
        writer.write(clientTable.getValueAt(i, 2) + ",");
        writer.write(clientTable.getValueAt(i, 3) + "\n");
      }
      writer.close();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(frame,
          "Error while saving data: " + e.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE

      );
    }

  }

  private void loadFromFile() {
    try {
      java.io.File file = new java.io.File("bin/ClientList.csv");
      if (!file.exists()) {
        return;
      }
      java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
      String line;
      while ((line = reader.readLine()) != null) {
        StringTokenizer st = new StringTokenizer(line, ",");
        if (st.countTokens() == 4) {
          String name = st.nextToken();
          String email = st.nextToken();
          String phone = st.nextToken();
          String address = st.nextToken();
          clientTable.addRow(new Object[] { name, email, phone, address });
        }
      }
      reader.close();
    } catch (Exception e) {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          JOptionPane.showMessageDialog(frame,
              "Error to load data: " + e.getMessage(),
              "Load Error",
              JOptionPane.ERROR_MESSAGE);
        }
      });
    }
  }

  private void addRightSideButtons() {
    JLabel rightPartTitle = new JLabel("List of Clients");
    rightPartTitle.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
    rightPartTitle.setForeground(Color.WHITE);
    rightPartTitle.setBounds(445, 30, 200, 30);
    frame.add(rightPartTitle);

    String[] column = { "Name", "Email", "Phone", "Address" };
    clientTable = new DefaultTableModel(column, 0);
    table = new JTable(clientTable);
    JScrollPane sp = new JScrollPane(table);
    sp.setBounds(300, 100, 455, 410);
    frame.add(sp);

    JButton bDelete = new JButton("Delete (Selected)");
    bDelete.setBounds(605, 520, 150, 30);
    bDelete.setBackground(new Color(255, 102, 102));
    bDelete.setForeground(Color.BLACK);
    bDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSelectedRow();
      }
    });
    frame.add(bDelete);
  }

  private void deleteSelectedRow() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
      int confirm = JOptionPane.showConfirmDialog(frame,
          "Delete this data row?",
          "Confirm to delete",
          JOptionPane.YES_NO_OPTION

      );
      if (confirm == JOptionPane.YES_OPTION) {
        clientTable.removeRow(selectedRow);
        saveToFile();
        DataOperator.syncFromCSV("bin/ClientList.csv");
        JOptionPane.showMessageDialog(frame, "Data deleted successfully!");
      }
    } else {
      JOptionPane.showMessageDialog(
          frame,
          "Select a data row first!",
          "Info",
          JOptionPane.INFORMATION_MESSAGE);
    }

  }

  private void addLeftSideButtons() {
    JLabel leftPartTitle = new JLabel("New Client");
    leftPartTitle.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
    leftPartTitle.setForeground(Color.WHITE);
    leftPartTitle.setBounds(60, 30, 200, 30);
    frame.add(leftPartTitle);

    JLabel titleName = new JLabel("Name");
    titleName.setFont(new Font("Arial", Font.PLAIN, 18));
    titleName.setForeground(Color.WHITE);
    titleName.setBounds(25, 100, 80, 25);
    frame.add(titleName);

    tfName = new JTextField();
    tfName.setBounds(25, 135, 185, 25);
    frame.add(tfName);

    JLabel titleEmail = new JLabel("Email");
    titleEmail.setFont(new Font("Arial", Font.PLAIN, 18));
    titleEmail.setForeground(Color.WHITE);
    titleEmail.setBounds(25, 195, 80, 25);
    frame.add(titleEmail);

    tfEmail = new JTextField();
    tfEmail.setBounds(25, 230, 185, 25);
    frame.add(tfEmail);

    JLabel titlePhone = new JLabel("Phone");
    titlePhone.setFont(new Font("Arial", Font.PLAIN, 18));
    titlePhone.setForeground(Color.WHITE);
    titlePhone.setBounds(25, 290, 80, 25);
    frame.add(titlePhone);

    tfPhone = new JTextField();
    tfPhone.setBounds(25, 325, 185, 25);
    frame.add(tfPhone);

    JLabel titleAddress = new JLabel("Address");
    titleAddress.setFont(new Font("Arial", Font.PLAIN, 18));
    titleAddress.setForeground(Color.WHITE);
    titleAddress.setBounds(25, 385, 80, 25);
    frame.add(titleAddress);

    tfAddress = new JTextField();
    tfAddress.setBounds(25, 420, 185, 25);
    frame.add(tfAddress);

    JButton bAdd = new JButton("Add");
    bAdd.setBounds(25, 480, 80, 30);
    bAdd.setBackground(new Color(180, 238, 180));
    bAdd.setForeground(Color.BLACK);
    bAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addClient();
      }
    });
    frame.add(bAdd);

    JButton bClear = new JButton("Clear");
    bClear.setBounds(130, 480, 80, 30);
    bClear.setBackground(Color.WHITE);
    bClear.setForeground(Color.BLACK);
    bClear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        clearForm();
      }
    });
    frame.add(bClear);
  }

  private void addClient() {
    String name = tfName.getText().trim();
    String email = tfEmail.getText().trim();
    String phone = tfPhone.getText().trim();
    String address = tfAddress.getText().trim();

    if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
      JOptionPane.showMessageDialog(frame, "Every Field must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (!email.contains("@") || !email.contains(".")) {
      JOptionPane.showMessageDialog(frame, "Email must be valid!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (!phone.matches("\\d+") || phone.length() < 10) {
      JOptionPane.showMessageDialog(frame, "Phone number must contain digits (0-9) only!", "Error",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
    clientTable.addRow(
        new Object[] {
            name, email, phone, address
        });
    saveToFile();
    DataOperator.syncFromCSV("bin/ClientList.csv");
    clearForm();
  }

  private void clearForm() {
    tfName.setText("");
    tfEmail.setText("");
    tfPhone.setText("");
    tfAddress.setText("");
  }

  public void show() {
    frame.setVisible(true);
  }

  public void hide() {
    frame.setVisible(false);
  }
}
