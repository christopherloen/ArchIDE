
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.nio.file.*;

public class clonePKG implements ActionListener{

  SpringLayout layout = new SpringLayout();
  JFrame box = new JFrame();
  JLabel pkg_name = new JLabel("Clone this:");
  JLabel clone = new JLabel();
  JTextField pkg_name_text = new JTextField();
  JButton proceed = new JButton("Proceed");
  JButton cancel = new JButton("Cancel");
  Functions functions = new Functions();

 public void clonePKGGUI(){
  layout.putConstraint(SpringLayout.NORTH, pkg_name, 15, SpringLayout.WEST, box);
  layout.putConstraint(SpringLayout.WEST, pkg_name, 10, SpringLayout.NORTH, box);
  layout.putConstraint(SpringLayout.NORTH, proceed, 65, SpringLayout.WEST, box);
  layout.putConstraint(SpringLayout.WEST, proceed, 190, SpringLayout.NORTH, box);
  layout.putConstraint(SpringLayout.NORTH, cancel, 65, SpringLayout.WEST, box);
  layout.putConstraint(SpringLayout.WEST, cancel, 95, SpringLayout.NORTH, box);
  layout.putConstraint(SpringLayout.NORTH, pkg_name_text, 13, SpringLayout.WEST, box);
  layout.putConstraint(SpringLayout.WEST, pkg_name_text, 90, SpringLayout.NORTH, box);
  layout.putConstraint(SpringLayout.NORTH, clone, 45, SpringLayout.WEST, box);
  layout.putConstraint(SpringLayout.WEST, clone, 10, SpringLayout.NORTH, box);
  //box
  box.setIconImage(Toolkit.getDefaultToolkit().getImage("upload.png"));
  box.setTitle("ArchIDE: clone");
  box.setSize(300,130);
  box.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  box.setLocationRelativeTo(null);
  box.setLayout(layout);
  pkg_name_text.setPreferredSize(new Dimension(190,18)); box.add(pkg_name_text);
  box.add(pkg_name);
  box.add(clone);
  box.add(proceed); proceed.addActionListener(this);
  box.add(cancel); cancel.addActionListener(this);
  box.setVisible(true);
}

public void actionPerformed(ActionEvent e){

  if (e.getSource() == proceed){
    String pkgbase = pkg_name_text.getText(), log = "[" + pkgbase + "]" + " : Cloned";

    try
    {
     functions.clonePKG(pkgbase);
     Main mainFrame = new Main();  } catch (RuntimeException run) {  throw new RuntimeException(run);

    }

     clone.setText(pkgbase + " has been cloned");

  }

  if (e.getSource() == cancel)
  {
    box.dispose(); new Main();
  }
}
}
