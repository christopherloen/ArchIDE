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

  public void genPKGBUILD(String pkgbase, String pkgname, String pkgver, String license, String arch){
    new File(pkgbase).mkdirs();
    
    String path = "./" + pkgbase + "/PKGBUILD";
    try(FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)){

          out.append("# Maintainer: Your name <your@email.com>\n");
          out.append("\npkgbase=" + pkgbase + "\n");
          out.append("pkgname=" + pkgname + "\n");
          out.append("pkgver=" + pkgver + "\n");
          out.append("epoch=\n");
          out.append("pkgrel=1\n");
          out.append("depends=\n");
          out.append("pkgdesc=''\n");
          out.append("arch=(" + arch + ")\n");
          out.append("url=''\n");
          out.append("license=('" + license + "')\n");
          out.append("depends=('')\n");
          out.append("source=('')\n");
          out.append("md5sums=('')\n");
          out.append("\nprepare(){\n");
          out.append("\n}\n");
          out.append("\nbuild(){\n");
          out.append("\n}");
          out.append("\npackage(){\n");
          out.append("\n}");
        } catch (IOException exc) { throw new RuntimeException(exc); }
  }
}
