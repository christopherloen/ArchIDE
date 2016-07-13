import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Functions{
  String editor = "gedit";
  public void openPKG() throws IOException{
    try
    {
      Runtime.getRuntime().exec(editor + " PKGBUILD");
    }catch (IOException error){
    System.out.println(error);
  }

  }
}
