import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class Main extends JFrame implements ActionListener{

  JTextArea logArea = new JTextArea();
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
      JMenuItem settings = new JMenuItem("Change default editor");
      JMenuItem identity = new JMenuItem("Change your identity");
      JMenuItem clearLogs = new JMenuItem("Clear logs");
  public Main(){
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600,301);
    add(mainMenuBar);
    setJMenuBar(mainMenuBar);
    logArea.setLineWrap(true);

    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add(logArea);
    add(scrollPane);
    setExtendedState(this.getExtendedState());
    scrollPane.setPreferredSize(new Dimension(100, 100));
    logArea.setSize(600,300);
    logArea.setFont(plain);
    logArea.setForeground(Color.YELLOW);
    logArea.setBackground(Color.DARK_GRAY);
    logArea.setEditable(false);
    logArea.setVisible(true);
    logArea.append("ArchIDE Logs (~/.archide/archide.log)");
    logArea.append("\n");

    fileMenu.add(newPkg); newPkg.addActionListener(this);
    fileMenu.add(openPkg); openPkg.addActionListener(this);

    pkgMenu.add(build);
    pkgMenu.add(install);
    pkgMenu.add(ncPKGBUILD);
    pkgMenu.add(ncbin);
    pkgMenu.add(upload);

    editMenu.add(openPKGBUILD);
    openPKGBUILD.addActionListener(this);
    editMenu.add(newPKGBUILD);
    editMenu.add(openInstallFile);
    editMenu.add(newInstallFile);
    editMenu.add(settings);
    editMenu.add(identity);
    editMenu.add(clearLogs);

    mainMenuBar.add(fileMenu);
    mainMenuBar.add(editMenu);
    mainMenuBar.add(pkgMenu);
  }



  public void actionPerformed(ActionEvent e){
    Functions functions = new Functions();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String thistime = dateFormat.format(date);
    //Modify PKGBUILD
    if (e.getSource() == openPKGBUILD){
      try(FileWriter fw = new FileWriter("archide.log", true);
          BufferedWriter bw = new BufferedWriter(fw);
          PrintWriter out = new PrintWriter(bw)){
            functions.openPKG();
            logArea.append("\n");
            logArea.append(thistime + ": opened PKGBUILD for //insert project name");
            out.append("\n" + thistime + ": opened PKGBUILD for //insert project name");
          } catch (IOException exc) { throw new RuntimeException(exc); }

    }
    //Create new package directory
    if (e.getSource() == newPkg){
      newPKG gui = new newPKG();
      gui.newPKGGUI();

    }

    if (e.getSource() == openPkg){
      clonePKG gui = new clonePKG();
      gui.clonePKGGUI();
    }

}

  public static void main(String[] args){
     new Main();
   }
}
