package Deserialization;

import io.whitesource.cure.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

public class Test1 {
  public void test(HttpServletRequest request) throws IOException, ClassNotFoundException {
    String fileName = request.getParameter("filePath");
    File file = new File(fileName);
    if (FileUtils.isFileOutsideDir(file.toString(), "/myAwesomeApp")) {
      // TODO: Handle exception
      throw new RuntimeException("Possible PathTraversal attack detected");
    }
    FileInputStream fileInputStream = new FileInputStream(file);
    ObjectInputStream in = new ObjectInputStream(fileInputStream);
    Student student = (Student) in.readObject();
    MathStudent mathStudent = (MathStudent) student;
    in.close();
  }
}

class Student {
  private int departmentID;

  public Student(int value) {
    this.departmentID = value;
  }
}

class MathStudent extends Student {
  private GradeSheet grades = null;

  public MathStudent(int value) {
    super(value);
    this.grades = new GradeSheet(value);
  }
}

class GradeSheet {
  private int avgGrade;
  private Set<String> grades = null;

  public GradeSheet(int value) {
    this.avgGrade = value;
  }
}
