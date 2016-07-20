package com.loen.archide;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.nio.file.*;


public class Functions
{

  public void loadPackages(JMenu build, JMenu install, JMenu ncPKGBUILD, JMenu ncbin, JMenu upload, JMenu openPKGBUILD, JMenu newInstallFile, JMenu openInstallFile, JMenu remove_folder, JTextArea logArea){
    File file = new File(".");
    String[] names = file.list();
    logArea.append("\n\nFound these directories:");
    for(String name : names)
    {
      if (new File("./" + name).isDirectory())
      {
      JMenuItem menubuild = new JMenuItem(name);
      build.add(menubuild); menubuild.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            build(name, logArea);
         }
      });
      JMenuItem installmenu = new JMenuItem(name);
      install.add(installmenu); installmenu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent f) {
           install(name, logArea);
         }
      });
      JMenuItem ncPKGBUILDmenu = new JMenuItem(name);
      ncPKGBUILD.add(ncPKGBUILDmenu); ncPKGBUILDmenu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent f) {
            ncPKGBUILD(name, logArea);
         }
      });
      JMenuItem ncbinmenu = new JMenuItem(name);
      ncbin.add(ncbinmenu); ncbinmenu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent f) {
            namcapBIN(name, logArea);
         }
      });
      JMenuItem uploadmenu = new JMenuItem(name);
      upload.add(uploadmenu); uploadmenu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent f) {
            uploadPKG gui = new uploadPKG();
            gui.uploadPKGGUI(name);
         }
      });
      JMenuItem openPKGBUILDmenu = new JMenuItem(name);
      openPKGBUILD.add(openPKGBUILDmenu); openPKGBUILDmenu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent f) {
          try{
            String editor = Files.readAllLines(Paths.get("archide.conf")).get(7);
            openPKG(name, editor); } catch (IOException ioe) { System.out.println(ioe); logArea.append("\n" + ioe); }
         }
      });
      JMenuItem newInstallFilemenu = new JMenuItem(name);
      newInstallFile.add(newInstallFilemenu); newInstallFilemenu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent f) {
          try{
          String editor = Files.readAllLines(Paths.get("archide.conf")).get(7);
            newInstall(name, editor, logArea);
         } catch (IOException ioe) { System.out.println(ioe); }
      }});
      JMenuItem openInstallFilemenu = new JMenuItem(name);
      openInstallFile.add(openInstallFilemenu); openInstallFilemenu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent f) {
            try
            {
              String editor = Files.readAllLines(Paths.get("archide.conf")).get(7);
                modifyInstall(editor, name, logArea);
            } catch (IOException ioe) { System.out.println(ioe); }
         }
      });
      JMenuItem remove_folder_menu = new JMenuItem(name);
      remove_folder.add(remove_folder_menu); remove_folder_menu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent f) {
              deleteFolders(name, logArea);

              loadPackages(build, install, ncPKGBUILD, ncbin, upload, openPKGBUILD, newInstallFile, openInstallFile, remove_folder, logArea);
         }
      });
      logArea.append("\n --> [" + name + "]");
      }
    }
  }

  public void build(String directory, JTextArea logarea){
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String thistime = dateFormat.format(date);
    try
    {
    ProcessBuilder pb = new ProcessBuilder("xterm", "-bg", "black", "-fg", "yellow", "-hold", "-e", "makepkg");
    pb.directory(new File(directory));
    final Process process = pb.start();
    } catch (IOException ioe) { System.out.println(ioe); }
    logWriter("building => [" + directory + "]");
    logarea.append("\n" + thistime + ": building " + "[" + directory + "]");
  }

  public void install(String directory, JTextArea logarea){
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String thistime = dateFormat.format(date);
    try
    {
    ProcessBuilder pb = new ProcessBuilder("xterm", "-bg", "black", "-fg", "yellow", "-hold", "-e", "makepkg", "-i");
    pb.directory(new File(directory));
    final Process process = pb.start();
    } catch (IOException ioe) { System.out.println(ioe); }
    logWriter("installing => [" + directory + "]");
    logarea.append("\n" + thistime + ": installing " + "[" + directory + "]");
  }

  public void ncPKGBUILD(String directory, JTextArea logarea){
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String thistime = dateFormat.format(date);
    try
    {
    ProcessBuilder pb = new ProcessBuilder("namcap", "PKGBUILD");
    pb.directory(new File(directory));
    final Process process = pb.start();
    InputStream is = process.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    String line;
    while ((line = br.readLine()) != null) {
    logWriter("running namcap on PKGBUILD => [" + directory + "]");
    logarea.append("\n" + thistime + ": running namcap on PKGBUILD => " + "[" + directory + "]");
    logarea.append("\n" + thistime + ": " + line);
    logWriter(line); }
    if (line == null){
      logarea.append("\n" + thistime + ": [" + directory + "]" + " => Done");
      logWriter("[" + directory + "]" + " => Done");
    }
    } catch (IOException ioe) { System.out.println(ioe); }
  }

  public void clearLogs(){
    try{
      PrintWriter log = new PrintWriter("archide.log");
      log.print("");
    } catch (IOException ioe) { System.out.println(ioe); }
  }

  public void newInstall(String directory, String editor, JTextArea logarea){
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String thistime = dateFormat.format(date);
    String path = directory + "/" + directory + ".install";
    try
    {
      Runtime.getRuntime().exec(editor + " " + path);
      logarea.append("\n" + thistime + ": creating .install file for [" + directory + "]");
      logWriter("creating .install file for [" + directory + "]");
    } catch (IOException ioe) { System.out.println(ioe); }
  }

  public void modifyInstall(String editor, String directory, JTextArea logarea){
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String thistime = dateFormat.format(date);
    String path = directory + "/" + directory + ".install";
    try
    {
      Runtime.getRuntime().exec(editor + " " + path);
      logarea.append("\n" + thistime + ": modifying .install file for [" + directory + "]");
      logWriter("modifying .install file for [" + directory + "]");
    } catch (IOException ioe) { System.out.println(ioe); }
  }

  public void readLogs(JTextArea logarea){
    try{
      FileReader fr = new FileReader("archide.log");
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
       logarea.append("\n" + line);
     }
    } catch (IOException ioe) { System.out.println(ioe); }
    }

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

  public void clonePKG(String pkgbase){
    try{
      String cloneURL = "git clone ssh+git://aur@aur.archlinux.org/" + pkgbase + ".git";
      Runtime.getRuntime().exec(cloneURL);
      String log =" => cloning [" + pkgbase + "]";
      logWriter(log);
    } catch (IOException ioe) { ioe.printStackTrace(); }
  }

  public void genPKGBUILD(String id, String pkgbase, String pkgname, String pkgver, String license, String arch){
    clonePKG(pkgbase);
    String path = "./" + pkgbase + "/PKGBUILD";
    try(FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)){

          out.append("# Maintainer: " + id);

          out.append("\npkgbase=" + pkgbase + "\n");
          out.append("pkgname=" + pkgname + "\n");
          out.append("pkgver=" + pkgver + "\n");
          out.append("pkgrel=1\n");
          out.append("epoch=\n");
          out.append("pkgdesc=''\n");
          out.append("arch=(" + arch + ")\n");
          out.append("url=''\n");
          out.append("license=('" + license + "')\n");
          out.append("groups=()");
          out.append("depends=('')\n");
          out.append("makedepends=('')\n");
          out.append("checkdepends=('')\n");
          out.append("optdepends=('')\n");
          out.append("provides=('')\n");
          out.append("conflicts=('')\n");
          out.append("replaces=('')\n");
          out.append("backup=('')\n");
          out.append("options=('')\n");
          out.append("install=\n");
          out.append("changelog=\n");
          out.append("source=('$pkgname-$pkgver.tar.gz'\n");
          out.append("        '$pkgname-$pkgver.patch')\n");
          out.append("noextract=('')\n");
          out.append("md5sums=('')\n");
          out.append("validpgpkeys=('')\n");
          out.append("\nprepare(){\n");
          out.append("\n}\n");
          out.append("\nbuild(){\n");
          out.append("\n}\n");
          out.append("\npackage(){\n");
          out.append("\n}");
        } catch (IOException exc) { throw new RuntimeException(exc); }
  }

  public void namcapBIN(String directory, JTextArea logarea){
    try
    {
    ProcessBuilder pb = new ProcessBuilder("namcap", "*.pkg.tar.xz");
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String thistime = dateFormat.format(date);
    pb.directory(new File(directory));
    final Process process = pb.start();
    InputStream is = process.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    String line;
    while ((line = br.readLine()) != null) {
      logarea.append("\n" + thistime + ": " + line);
      logWriter(line);
   }
   if (line == null){
      logarea.append("\n" + thistime + ": [" + directory + "]" + " => Done");
      logWriter(" [" + directory + "]" + " => Done");
   }
  } catch (IOException ioe) { System.out.println(ioe); }
  }

  public void uploadPKG(String directory, String message){
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String thistime = dateFormat.format(date);
    try
    {
    ProcessBuilder git_push = new ProcessBuilder("xterm", "-bg", "black", "-fg", "yellow", "-hold", "-e", "jeeves", "-u", message);
    git_push.directory(new File(directory));
    final Process gitpush = git_push.start();
    logWriter("Uploading => " + "[" + directory + "]"); } catch (IOException ioe) { System.out.println(ioe); }
}
  public void reloadFiles(String directory, JTextArea logarea){
    logarea.setText(null);
    logarea.append("Files:\n");
    File file = new File(directory + "/");
    String[] names = file.list();

    for(String name : names)
    {
      logarea.append("\n" + name);
    }
  }

  public void deleteFiles(String directory, JTextField textfield){
    try{
    String files = textfield.getText();
    String[] filenames = files.split(" ");
    for ( String file : filenames){
    ProcessBuilder rmFiles = new ProcessBuilder("rm", "-rf", file);
    rmFiles.directory(new File(directory));
    final Process rm = rmFiles.start(); } } catch (IOException ioe) { System.out.println(ioe); }
  }

  public void deleteFolders(String directory, JTextArea logarea){
    try
    {
    ProcessBuilder rmrf = new ProcessBuilder("rm", "-rv", directory);
    final Process rm = rmrf.start();
    InputStream is = rm.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    String line;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String thistime = dateFormat.format(date);
    logWriter("removing [" + directory + "] directory");
    while ((line = br.readLine()) != null) {
      logarea.append("\n" + thistime + ": " + line);
      logWriter(line);
    }

  } catch (IOException ioe) { System.out.println(ioe); }
  }
}
