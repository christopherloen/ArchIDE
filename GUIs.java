import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

class newPKG implements ActionListener{
  SpringLayout layout = new SpringLayout();
  JFrame box = new JFrame();
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
    layout.putConstraint(SpringLayout.NORTH, proceed, 38, SpringLayout.WEST, pkg_arch_text);
    layout.putConstraint(SpringLayout.WEST, proceed, 315, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, cancel, 38, SpringLayout.WEST, pkg_arch_text);
    layout.putConstraint(SpringLayout.WEST, cancel, 215, SpringLayout.NORTH, box);
    //frame
    box.setSize(500,225);
    box.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    box.setVisible(true);
    box.setLayout(layout);
    box.add(pkg_base); pkg_base_text.setPreferredSize(new Dimension(300,18)); box.add(pkg_base_text);
    box.add(pkg_name); pkg_name_text.setPreferredSize(new Dimension(300,18)); box.add(pkg_name_text);
    box.add(pkg_ver); pkg_ver_text.setPreferredSize(new Dimension(300,18)); box.add(pkg_ver_text);
    box.add(pkg_license); pkg_license_text.setPreferredSize(new Dimension(300,18)); box.add(pkg_license_text);
    box.add(pkg_arch); pkg_arch_text.setPreferredSize(new Dimension(300,18)); box.add(pkg_arch_text);
    proceed.setVisible(true);
    box.add(proceed); proceed.addActionListener(this);
    box.add(cancel);

  }
  public void actionPerformed(ActionEvent e){
    if (e.getSource() == proceed){
      String pkgbase = pkg_base_text.getText();
      String pkgname = pkg_name_text.getText();
      String pkgver = pkg_ver_text.getText();
      String license = pkg_license_text.getText();
      String arch = pkg_arch_text.getText();
      Functions functions = new Functions();
      functions.genPKGBUILD(pkgbase, pkgname, pkgver, license, arch);
    }
  }
}

  class clonePKG{
    SpringLayout layout = new SpringLayout();
    JFrame box = new JFrame();
    JLabel pkg_name = new JLabel("Clone this:");
    JTextField pkg_name_text = new JTextField();
    JButton proceed = new JButton("Proceed");
    JButton cancel = new JButton("Cancel");

   public void clonePKGGUI(){
    layout.putConstraint(SpringLayout.NORTH, pkg_name, 15, SpringLayout.WEST, box);
    layout.putConstraint(SpringLayout.WEST, pkg_name, 10, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, proceed, 70, SpringLayout.WEST, box);
    layout.putConstraint(SpringLayout.WEST, proceed, 190, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, cancel, 70, SpringLayout.WEST, box);
    layout.putConstraint(SpringLayout.WEST, cancel, 95, SpringLayout.NORTH, box);
    layout.putConstraint(SpringLayout.NORTH, pkg_name_text, 13, SpringLayout.WEST, box);
    layout.putConstraint(SpringLayout.WEST, pkg_name_text, 90, SpringLayout.NORTH, box);
    //box
    box.setSize(300,130);
    box.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    box.setVisible(true);
    box.setLayout(layout);
    pkg_name_text.setPreferredSize(new Dimension(150,18)); box.add(pkg_name_text);
    box.add(pkg_name);
    box.add(proceed);
    box.add(cancel);


  }
}
