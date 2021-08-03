package pathTraversal;

import io.whitesource.cure.FileUtils;
import java.io.File;
import javax.servlet.http.HttpServletRequest;

public class PTTest3 {
  private final String BASE_DIRECTORY = "/users/ws/profiles/";

  public void test(HttpServletRequest request, String customDirName) {
    try {
      String fileName = request.getParameter("fileName");
      String dirPath = getValue(BASE_DIRECTORY) + customDirName + "subsubdir/";
      String sanitizedPath = FileUtils.normalize(fileName);
      if (sanitizedPath == null) {
        // TODO: Handle exception
        throw new RuntimeException("Possible PathTraversal attack detected");
      }
      File myFile = new File(dirPath + sanitizedPath);
      myFile.delete();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public String getValue(String str) {
    return str + "userdir/";
  }
}
