package osInjection;

import io.whitesource.cure.Encoder;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

public class DataMembersFlowTest {

  public void test(HttpServletRequest request) throws IOException, InterruptedException {
    String command = request.getParameter("command");
    Inner inner = new Inner();
    inner.setSuperInner(command);
    doStuff("mkdir", inner);
  }

  private void doStuff(String command, Inner inner) throws IOException, InterruptedException {
    /* POTENTIAL FLAW: command injection */
    Process process =
        Runtime.getRuntime().exec(command + Encoder.forOsCommand(inner.getSuperInner()));
    process.waitFor();
  }

  public static class Inner {
    private String superInner;

    public Inner() {}

    public void setSuperInner(String superInner) {
      this.superInner = superInner;
    }

    public String getSuperInner() {
      return superInner;
    }
  }
}
