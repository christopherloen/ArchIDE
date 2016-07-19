import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.nio.file.*;

public class uploadPKG implements ActionListener{
  Functions functions = new Functions();
  SpringLayout layout = new SpringLayout();
  JFrame box = new JFrame();
  JLabel commit = new JLabel("Commit message:");
  JTextArea dirfiles = new JTextArea();
  JTextField commit_text = new JTextField();
  JTextField delete_text = new JTextField();
  JScrollPane scrollPane = new JScrollPane(dirfiles);
  DefaultCaret caret = (DefaultCaret) dirfiles.getCaret();
  JButton upload_button = new JButton("Upload");
  JButton cancel = new JButton("Cancel");
  JButton delete = new JButton("Delete files");
  JButton done = new JButton("Done");
  JLabel delete_lbl = new JLabel("Delete:");

  public void uploadPKGGUI(String directory){
    dirfiles.append("Files:\n\n");
    File file = new File(directory + "/");
    String[] names = file.list();

    for(String name : names)
    {
      dirfiles.append(name + "\n");
    }
    //layout
    layout.putConstraint(SpringLayout.NORTH, dirfiles, 5, SpringLayout.WEST, box);
      layout.putConstraint(SpringLayout.WEST, dirfiles, 5, SpringLayout.NORTH, box);
        layout.putConstraint(SpringLayout.NORTH, commit, 220, SpringLayout.WEST, box);
          layout.putConstraint(SpringLayout.WEST, commit, 5, SpringLayout.NORTH, box);
            layout.putConstraint(SpringLayout.NORTH, commit_text, 218, SpringLayout.WEST, box);
              layout.putConstraint(SpringLayout.WEST, commit_text, 140, SpringLayout.NORTH, box);
                layout.putConstraint(SpringLayout.NORTH, cancel, 250, SpringLayout.WEST, box);
                  layout.putConstraint(SpringLayout.WEST, cancel, 10, SpringLayout.NORTH, box);
                    layout.putConstraint(SpringLayout.NORTH, delete, 250, SpringLayout.WEST, box);
                      layout.putConstraint(SpringLayout.WEST, delete, 160, SpringLayout.NORTH, box);
                        layout.putConstraint(SpringLayout.NORTH, upload_button, 250, SpringLayout.WEST, box);
                          layout.putConstraint(SpringLayout.WEST, upload_button, 300, SpringLayout.NORTH, box);
                            layout.putConstraint(SpringLayout.NORTH, done, 288, SpringLayout.WEST, box);
                              layout.putConstraint(SpringLayout.WEST, done, 315, SpringLayout.NORTH, box);
                                layout.putConstraint(SpringLayout.NORTH, delete_lbl, 293, SpringLayout.WEST, box);
                                  layout.putConstraint(SpringLayout.WEST, delete_lbl, 5, SpringLayout.NORTH, box);
                                    layout.putConstraint(SpringLayout.NORTH, delete_text, 292, SpringLayout.WEST, box);
                                      layout.putConstraint(SpringLayout.WEST, delete_text, 70, SpringLayout.NORTH, box);

    //box
    box.setIconImage(Toolkit.getDefaultToolkit().getImage("upload.png"));
    box.setTitle("ArchIDE: Upload");
    box.setSize(400,315);
    box.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    box.setLocationRelativeTo(null);
    box.setLayout(layout);

    dirfiles.setPreferredSize(new Dimension(390,200));
    dirfiles.setEditable(false);
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    dirfiles.setLineWrap(true);
    dirfiles.setForeground(Color.YELLOW);
    dirfiles.setBackground(Color.BLACK);
    scrollPane.setViewportView(dirfiles);
    scrollPane.setBounds(10,60,780,500);
    scrollPane.setVisible(true);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    box.add(dirfiles); box.add(scrollPane); box.add(delete_lbl); cancel.addActionListener(this);
      box.add(cancel); box.add(delete); box.add(done); delete.addActionListener(this);
        box.add(commit); box.add(commit_text); commit_text.setPreferredSize(new Dimension(220,20));
          box.add(delete_text); delete_text.setPreferredSize(new Dimension(220, 20));
            done.setVisible(false); delete_text.setVisible(false); delete_lbl.setVisible(false);
              box.add(upload_button); upload_button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String message = commit_text.getText();
                        functions.uploadPKG(directory, message);
                     }
                  });
                                      done.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        functions.deleteFiles(directory, delete_text);
                        functions.reloadFiles(directory, dirfiles);
                     }
                  });
                  box.setVisible(true);
                }

  public void actionPerformed(ActionEvent e){
    if (e.getSource() == delete){
      delete_text.setVisible(true); delete_lbl.setVisible(true); done.setVisible(true);
      box.setSize(400, 370);
    }
    if (e.getSource() == cancel){
      box.dispose();
    }

  }
}
