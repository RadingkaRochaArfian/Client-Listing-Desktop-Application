package database;

import java.io.*;
import java.sql.*;

public class DataOperator {
  static final String DB_URL = "jdbc:h2:./backup/clientdb";
  static final String DB_USER = "sa";
  static final String DB_PASS = "";

  public static void init() {
    try {
      File backupDir = new File("backup");
      if (!backupDir.exists()) {
        backupDir.mkdirs();
      }
      try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
          Statement stmt = conn.createStatement()) {
        String sql = "CREATE TABLE IF NOT EXISTS clients (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(255)," +
            "email VARCHAR(255), " +
            "phone VARCHAR(50), " +
            "address VARCHAR(500))";
        stmt.execute(sql);
      }
    } catch (Exception e) {
    }
  }

  public static void syncFromCSV(String csvPath) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
      try (Statement stmt = conn.createStatement()) {
        stmt.execute("DELETE FROM clients");
      }
      File file = new File(csvPath);
      if (!file.exists()) {
        return;
      }
      String insertSQL = "INSERT INTO clients (name,email,phone,address) VALUES (?, ?, ?, ?)";
      try (PreparedStatement pstmt = conn.prepareStatement(insertSQL);
          BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
        String line;
        while ((line = br.readLine()) != null) {
          String[] data = line.split(",");
          if (data.length == 4) {
            pstmt.setString(1, data[0]);
            pstmt.setString(2, data[1]);
            pstmt.setString(3, data[2]);
            pstmt.setString(4, data[3]);
            pstmt.addBatch();
          }

        }
        pstmt.executeBatch();
      }
    } catch (Exception e) {
    }
  }
}
