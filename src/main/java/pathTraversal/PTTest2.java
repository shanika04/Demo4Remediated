package pathTraversal;

import io.whitesource.cure.FileUtils;
import java.io.File;
import javax.servlet.http.HttpServletRequest;

public class PTTest2 {
  private final String BASE_DIRECTORY = "/users/ws/profiles/";

  public void test(HttpServletRequest request) {
    String fileName = request.getParameter("fileName");
    sink(fileName);
  }

  private void sink(String filename) {
    String sanitizedPath = FileUtils.normalize(filename);
    if (sanitizedPath == null) {
      // TODO: Handle exception
      throw new RuntimeException("Possible PathTraversal attack detected");
    }
    File myFile = new File(BASE_DIRECTORY, sanitizedPath);
    myFile.delete();
  }
}
