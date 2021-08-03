package xss;

import io.whitesource.cure.Encoder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XSS1 {
  HttpServletRequest request;
  HttpServletResponse response;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    this.request = request;
    this.response = response;
    String name = request.getParameter("name");
    unsafe(name);
  }

  private void unsafe(String name) {
    try {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.write("<div> <style>color:" + Encoder.forCssStringXss(name) + "</style>");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
