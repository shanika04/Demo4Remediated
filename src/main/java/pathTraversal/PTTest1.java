package pathTraversal;

import io.whitesource.cure.FileUtils;
import java.io.File;
import javax.servlet.http.HttpServletRequest;

// Base Directory: /users/ws/profiles/
// Unsafe filename: ../../../etc/passwd
// Canonical unsafe-file: /users/ws/profiles/../../../etc/passwd ----->>>  /etc/passwd ---->>>>
// Vulnerability!

public class PTTest1 {
  private final String BASE_DIRECTORY = "/users/ws/profiles/";

  public void test(HttpServletRequest request) {
    try {
      String fileName = request.getParameter("fileName");
      String sanitizedPath = FileUtils.normalize(fileName);
      if (sanitizedPath == null) {
        // TODO: Handle exception
        throw new RuntimeException("Possible PathTraversal attack detected");
      }
      File myFile = new File(BASE_DIRECTORY + sanitizedPath);
      deleteFile(myFile);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void deleteFile(File f) {
    f.delete();
  }
}
