
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Functions{

  public void logWriter(String log){
    try(
    FileWriter fw = new FileWriter("archide.log", true);
    BufferedWriter bw = new BufferedWriter(fw);
    PrintWriter printlog = new PrintWriter(bw);
    ){
      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date date = new Date();
      String thistime = dateFormat.format(date);
      printlog.append("\n" + thistime + ": " + log);} catch (IOException ioe) { ioe.printStackTrace(); }
  }

  public void openPKG(String pkgbase, String editor) throws IOException{
    try
    {
      Runtime.getRuntime().exec(editor + " " + pkgbase + "/PKGBUILD");
    }catch (IOException error){
    System.out.println(error);
  }

  }

  public void openConf(String editor) throws IOException{
    try
    {
      Runtime.getRuntime().exec(editor + " archide.conf");
    } catch (IOException ioe) { System.out.println(ioe); }
  }

  public void genPKGBUILD(String id, String pkgbase, String pkgname, String pkgver, String license, String arch){
    new File(pkgbase).mkdirs();
    String path = "./" + pkgbase + "/PKGBUILD";
    try(FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)){
          out.append("# Maintainer: " + id);
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

  public void clonePKG(String pkgbase){
    try{
      String cloneURL = "git clone ssh+git://aur@aur.archlinux.org/" + pkgbase + ".git";
      Runtime.getRuntime().exec(cloneURL);
      String log =" => cloning [" + pkgbase + "]";
      logWriter(log);
    } catch (IOException ioe) { ioe.printStackTrace(); }
  }
}
