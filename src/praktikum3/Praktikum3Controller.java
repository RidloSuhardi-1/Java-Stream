/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktikum3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author RIDLO_SHUHARDI
 */
public class Praktikum3Controller {
    private praktikum3 view;
    private List<Integer> list = new ArrayList<>();

    public Praktikum3Controller(praktikum3 view) {
        this.view = view;
        // Baca file
        this.view.getBtnBaca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proses();
            }
         });
        // Simpan file
        this.view.getBtnSimpan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
    }
    
     private void proses() {
         JFileChooser loadFile = view.getLoadFile();
         StyledDocument doc = view.getTxtPane().getStyledDocument();
         if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
             BufferedReader reader = null;
             try {
                 reader = new BufferedReader(new FileReader(loadFile.getSelectedFile()));
                 String data = null;
                 doc.insertString(0, "", null);
                 while ((data = reader.readLine()) != null) {
                     doc.insertString(doc.getLength(), data, null);
                 }
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Praktikum3Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } catch (IOException | BadLocationException ex) {
                 Logger.getLogger(Praktikum3Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } finally {
                 if (reader != null) {
                     try {
                         reader.close();
                     } catch (IOException ex) {
                         Logger.getLogger(Praktikum3Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                     }
                 }
             }
         }
     }
    
    private void save() {
         JFileChooser loadFile = view.getLoadFile();
         if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
             BufferedWriter writer = null;
             try {
                 String contents = view.getTxtPane().getText();
                 if (contents != null && !contents.isEmpty()) {
                     writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
                     writer.write(contents);
                 }

             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Praktikum3Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(Praktikum3Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } finally {
                 if (writer != null) {
                     try {
                         writer.flush();
                         writer.close();
                         view.getTxtPane().setText("");
                     } catch (IOException ex) {
                         Logger.getLogger(Praktikum3Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                     }
                 }
             }
         }
     }
}
