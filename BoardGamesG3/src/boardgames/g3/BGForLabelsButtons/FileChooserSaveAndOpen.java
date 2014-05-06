package boardgames.g3.BGForLabelsButtons;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserSaveAndOpen extends JPanel
                             implements ActionListener {
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
  
    

    public FileChooserSaveAndOpen() {
        super(new BorderLayout());

        log = new JTextArea();
        log.setEditable(false);
        log.setFocusable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        fc = new JFileChooser();

        openButton = new JButton("Open a File..");
        openButton.addActionListener(this);


        saveButton = new JButton("Save a File..");
        saveButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
    
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    
    public void actionPerformed(ActionEvent e) {
     FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
     
        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(FileChooserSaveAndOpen.this);
            fc.setFileFilter(filter);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                
                
            } 
            

        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(FileChooserSaveAndOpen.this);
            fc.setFileFilter(filter);
            if (returnVal == JFileChooser.APPROVE_OPTION) {          
               try {
                 File file = fc.getSelectedFile(); 
                 PrintWriter os = new PrintWriter(file);
                 
                 os.print("Hej");
                 os.close();
                
                } catch (FileNotFoundException e1) {
                 e1.printStackTrace();
                }
            } 
        }
    }

  }

