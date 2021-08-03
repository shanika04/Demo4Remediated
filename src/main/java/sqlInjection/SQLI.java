package sqlInjection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

public class SQLI {
  public void sqlTest1(HttpServletRequest request) {
    try {
      String ip = request.getParameter("ip");
      String uuid = UUID.randomUUID().toString();
      String sql = "INSERT INTO banned_ip(id, ip) VALUE('" + uuid + "'," + "?" + ")";
      PreparedStatement statement = getJDBCConnection().prepareStatement(sql);
      statement.setString(1, ip);

      statement.execute();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  public void sqlTest2(int x, String d, String y) {
    try {
      int u = x + 1;
      System.out.println(d + "blabla");
      String id = getid(y);
      String sql = "INSERT INTO banned_ip(id, ip) VALUE(" + "?" + "," + "?" + ")";
      PreparedStatement statement = getJDBCConnection().prepareStatement(sql);
      statement.setString(1, UUID.randomUUID().toString());

      statement.setString(2, id);
      statement.execute();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    System.out.print("sdfdsf");
  }

  public String getid(String x) {
    String id = x;
    return id;
  }

  Connection getJDBCConnection() {
    return null;
  }
}
