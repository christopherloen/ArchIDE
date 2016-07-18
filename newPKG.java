
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.nio.file.*;

public class newPKG implements ActionListener{
  SpringLayout layout = new SpringLayout();
  JFrame box = new JFrame();
  JLabel pkginfo = new JLabel();
  JLabel pkg_base = new JLabel("Base:");
  JLabel pkg_name = new JLabel("Name:");
  JLabel pkg_ver = new JLabel("Version:");
  JLabel pkg_license = new JLabel("License:");
  JLabel pkg_arch = new JLabel("Architecture:");
  JTextField pkg_base_text = new JTextField();
  JTextField pkg_name_text = new JTextField();
  JTextField pkg_ver_text = new JTextField();
  JTextField pkg_license_text = new JTextField();
  JTextField pkg_arch_text = new JTextField();
  JButton proceed = new JButton("Proceed");
  JButton cancel = new JButton("Cancel");

  public void newPKGGUI(){
    //layout
    layout.putConstraint(SpringLayout.NORTH, pkg_base, 15, SpringLayout.WEST, box);
    layout.putConstraint(SpringLayout.WEST, pkg_base, 25, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, pkg_name, 30, SpringLayout.NORTH, pkg_base);
    layout.putConstraint(SpringLayout.WEST, pkg_name, 25, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, pkg_ver, 30, SpringLayout.NORTH, pkg_name);
    layout.putConstraint(SpringLayout.WEST, pkg_ver, 25, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, pkg_license, 30, SpringLayout.NORTH, pkg_ver);
    layout.putConstraint(SpringLayout.WEST, pkg_license, 25, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, pkg_arch, 30, SpringLayout.NORTH, pkg_license);
    layout.putConstraint(SpringLayout.WEST, pkg_arch, 25, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, pkg_base_text, 15, SpringLayout.WEST, box);
    layout.putConstraint(SpringLayout.WEST, pkg_base_text, 125, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, pkg_name_text, 45, SpringLayout.WEST, box);
    layout.putConstraint(SpringLayout.WEST, pkg_name_text, 125, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, pkg_ver_text, 75, SpringLayout.WEST, box);
    layout.putConstraint(SpringLayout.WEST, pkg_ver_text, 125, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, pkg_license_text, 105, SpringLayout.WEST, box);
    layout.putConstraint(SpringLayout.WEST, pkg_license_text, 125, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, pkg_arch_text, 135, SpringLayout.WEST, box);
    layout.putConstraint(SpringLayout.WEST, pkg_arch_text, 125, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, proceed, 40, SpringLayout.WEST, pkg_arch_text);
    layout.putConstraint(SpringLayout.WEST, proceed, 390, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, cancel, 40, SpringLayout.WEST, pkg_arch_text);
    layout.putConstraint(SpringLayout.WEST, cancel, 290, SpringLayout.NORTH, box);
    //frame
    box.setIconImage(Toolkit.getDefaultToolkit().getImage("upload.png"));
    box.setTitle("Create new package");
    box.setSize(500,235);
    box.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    box.setLocationRelativeTo(null);
    box.setLayout(layout);
    box.add(pkg_base); pkg_base_text.setPreferredSize(new Dimension(300,20)); box.add(pkg_base_text);
    box.add(pkg_name); pkg_name_text.setPreferredSize(new Dimension(300,20)); box.add(pkg_name_text);
    box.add(pkg_ver); pkg_ver_text.setPreferredSize(new Dimension(300,20)); box.add(pkg_ver_text);
    box.add(pkg_license); pkg_license_text.setPreferredSize(new Dimension(300,20)); box.add(pkg_license_text);
    box.add(pkg_arch); pkg_arch_text.setPreferredSize(new Dimension(300,20)); box.add(pkg_arch_text);
    box.add(proceed); proceed.addActionListener(this);
    box.add(cancel); cancel.addActionListener(this);
    box.setVisible(true);
  }
  public void actionPerformed(ActionEvent e){
    Functions functions = new Functions();
    if (e.getSource() == proceed){
      String pkgbase = pkg_base_text.getText();
      String pkgname = pkg_name_text.getText();
      String pkgver = pkg_ver_text.getText();
      String license = pkg_license_text.getText();
      String arch = pkg_arch_text.getText();
      String log = "Generation of: => [" + pkgbase + "]";
      try(FileReader fr = new FileReader("archide.conf"); BufferedReader br = new BufferedReader(fr);){
        String editor = Files.readAllLines(Paths.get("archide.conf")).get(7);
        String id = Files.readAllLines(Paths.get("archide.conf")).get(4);
        functions.genPKGBUILD(id, pkgbase, pkgname, pkgver, license, arch);
        functions.openPKG(pkgbase, editor); } catch (IOException ioe) {  ioe.printStackTrace(); }
        functions.logWriter(log);
        new Main();
    }
    if (e.getSource() == cancel){
      box.dispose(); new Main();
    }
  }
}
