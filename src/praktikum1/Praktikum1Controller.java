/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktikum1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author RIDLO_SHUHARDI
 */
public class Praktikum1Controller {
    private praktikum1 view;

    public Praktikum1Controller(praktikum1 view) {
        this.view = view;
        this.view.getBtnBaca().addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 proses();
             }
         });
    }
    
    private void proses() {
        JFileChooser loadFile = view.getLoadFile();
        StyledDocument doc = view.getTxtPane().getStyledDocument();
        
        if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(loadFile.getSelectedFile());
                int read = inputStream.read();
                doc.insertString(0, "", null);
                while (read != -1) {
                     doc.insertString(doc.getLength(), "" + read, null);
                     System.out.println("" + read);
                     read = inputStream.read();
                 }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Praktikum1Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IOException | BadLocationException ex) {
                 Logger.getLogger(Praktikum1Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } finally {
                 if (inputStream != null) {
                     try {
                         inputStream.close();
                     } catch (IOException ex) {
                         Logger.getLogger(Praktikum1Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                     }
                 }
             }
        }
    }
}
