
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.nio.file.*;

public class Main extends JFrame implements ActionListener{

  Functions functions = new Functions();
  JTextArea logArea = new JTextArea();
  DefaultCaret caret = (DefaultCaret) logArea.getCaret();
  JScrollPane scrollPane = new JScrollPane(logArea);
  Font plain = new Font("Monospace", Font.PLAIN, 11);
  JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
      JMenuItem newPkg = new JMenuItem("New Package");
      JMenuItem openPkg = new JMenuItem("Clone existing package");
    JMenu pkgMenu = new JMenu("Package");
      JMenuItem build = new JMenuItem("Build");
      JMenuItem install = new JMenuItem("Install packages");
      JMenuItem ncPKGBUILD = new JMenuItem("Run namcap on PKGBUILD");
      JMenuItem ncbin = new JMenuItem("Run namcap on binaries");
      JMenuItem upload = new JMenuItem("Upload package to AUR");
    JMenu editMenu = new JMenu("Edit");
      JMenuItem newInstallFile = new JMenuItem("Create .install file");
      JMenuItem openInstallFile = new JMenuItem("Modify .install file");
      JMenuItem newPKGBUILD = new JMenuItem("Create new PKGBUILD");
      JMenuItem openPKGBUILD = new JMenuItem("Modify PKGBUILD");
      JMenuItem settings = new JMenuItem("Change settings");
      JMenuItem clearLogs = new JMenuItem("Clear logs");

  public Main(){
    setTitle("ArchIDE");
    setVisible(false);
    setSize(600,301);
    add(mainMenuBar);
    setJMenuBar(mainMenuBar);
    setExtendedState(this.getExtendedState());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //logs
    add(logArea);
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    logArea.setLineWrap(true);
    logArea.setSize(600,300);
    logArea.setFont(plain);
    logArea.setForeground(Color.GREEN);
    logArea.setBackground(Color.BLACK);
    logArea.setEditable(false);
    logArea.setVisible(true);
    logArea.append("ArchIDE 0.2.1~ --> Logs (~/.archide/archide.log)");
    logArea.append("\n");

    scrollPane.setViewportView(logArea);
    add(scrollPane);
    scrollPane.setBounds(10,60,780,500);
    scrollPane.setVisible(true);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(100, 100));

    fileMenu.add(newPkg); newPkg.addActionListener(this);
    fileMenu.add(openPkg); openPkg.addActionListener(this);

    pkgMenu.add(build);
    pkgMenu.add(install);
    pkgMenu.add(ncPKGBUILD);
    pkgMenu.add(ncbin);
    pkgMenu.add(upload);

    editMenu.add(openPKGBUILD); openPKGBUILD.addActionListener(this);
    editMenu.add(openInstallFile);
    editMenu.add(newInstallFile);
    editMenu.add(settings); settings.addActionListener(this);
    editMenu.add(clearLogs);

    mainMenuBar.add(fileMenu);
    mainMenuBar.add(editMenu);
    mainMenuBar.add(pkgMenu);

    setLocationRelativeTo(null);
    setVisible(true);

  }



  public void actionPerformed(ActionEvent e){
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String thistime = dateFormat.format(date);

    //Create new package directory
    if (e.getSource() == newPkg){
      newPKG gui = new newPKG();
      gui.newPKGGUI();
      logArea.append("\n" + thistime + ": [initialized creation of new package]");
      String log = "[initialized creation of new package]";
      functions.logWriter(log);
    }

    if (e.getSource() == settings){
      try
      {
        String editor = Files.readAllLines(Paths.get("archide.conf")).get(8);
        functions.openConf(editor);
        String log = "\n" + thistime + ": [changing configurations archide.conf]";
        logArea.append(log);
        functions.logWriter(log);
      } catch (IOException ioe) { System.out.println(ioe); }

    }

    if (e.getSource() == openPkg){
      clonePKG gui = new clonePKG();
      gui.clonePKGGUI();
      logArea.append("\n" + thistime + ": [ready to clone new package]");
      String log = "[ready to clone new package]";
      functions.logWriter(log);
    }

}

  public static void main(String[] args){
     new Main();
   }
}
