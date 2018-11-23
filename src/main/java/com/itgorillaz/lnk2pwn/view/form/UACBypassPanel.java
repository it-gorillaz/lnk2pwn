package com.itgorillaz.lnk2pwn.view.form;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.annotation.PostConstruct;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;
import com.alee.laf.text.WebTextField;
import com.itgorillaz.lnk2pwn.model.Shortcut;
import com.itgorillaz.lnk2pwn.view.utils.ColorUtils;

import net.miginfocom.swing.MigLayout;

@Component
public class UACBypassPanel extends WebPanel implements KeyListener, DocumentListener {

    private static final long serialVersionUID = 8564799804491915041L;

    private final String DEFAULT_UAC_BYPASS_FILE_NAME = "uac_bypass.vbs";	
    private final Color COMMAND_BG_COLOR = ColorUtils.hex2Rgb("#1e1e1e");
    
    private WebTextField uacFileNameField = new WebTextField();
    private WebTextArea uacCommandTextArea = new WebTextArea();
    private WebTextArea uacOuputTextArea = new WebTextArea();
    
    @Autowired
    @Qualifier("VBSSourceCode")
    private String vbsSourceCode;
    
    @Autowired
    private Shortcut shortcut;
    
    @PostConstruct
    private void initComponents() {
        MigLayout layout = new MigLayout("", "[grow]", "");
        this.setLayout(layout);
        this.add(createUACFileNamePanel(), "grow,wrap");
        this.add(createUACCommandPanel(), "grow,wrap");
        
        uacFileNameField.getDocument().addDocumentListener(this);
        uacOuputTextArea.getDocument().addDocumentListener(this);
        uacCommandTextArea.addKeyListener(this);
        
        uacFileNameField.setText(DEFAULT_UAC_BYPASS_FILE_NAME);			
        uacOuputTextArea.setText(vbsSourceCode);					
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        changeOutput(uacCommandTextArea.getText());
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        
    }
    
    @Override
    public void keyTyped(KeyEvent event) {
        
    }
    
    private void changeOutput(String command) {
        command = command.replace(System.lineSeparator(), "");
        String output = String.format(vbsSourceCode, command);
        uacOuputTextArea.setText(output);
    }
    
    private WebPanel createUACFileNamePanel() {
        MigLayout layout = new MigLayout("", "[grow]", "");
        WebPanel panel = new WebPanel();
        panel.setLayout(layout);
        panel.add(new WebLabel("File Name(.vbs)"), "wrap");
        panel.add(uacFileNameField, "grow");
        return panel;
    }
    
    private WebPanel createUACCommandPanel() {
        MigLayout layout = new MigLayout("", "[grow][grow]", "[][grow]");
        WebPanel panel = new WebPanel();
        panel.setLayout(layout);
        panel.add(new WebLabel("Command"));
        panel.add(new WebLabel("Output"), "wrap");
                        
        uacCommandTextArea.setLineWrap(true);
        uacCommandTextArea.setBackground(COMMAND_BG_COLOR);
        uacCommandTextArea.setForeground(Color.WHITE);
        
        uacOuputTextArea.setLineWrap(true);
        uacOuputTextArea.setBackground(COMMAND_BG_COLOR);
        uacOuputTextArea.setForeground(Color.WHITE);
        
        WebScrollPane uacCommandAreaScroll = new WebScrollPane(uacCommandTextArea);		
        WebScrollPane uacOuputAreaScroll = new WebScrollPane(uacOuputTextArea);
        
        panel.add(uacCommandAreaScroll, "w 100%, h 100%, hmin 250");
        panel.add(uacOuputAreaScroll, "w 100%, h 100%, hmin 250");
        
        return panel;
    }
    
    @Override
    public void changedUpdate(DocumentEvent e) {
        updateModel();			
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateModel();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateModel();
    }
    
    private void updateModel() {
        shortcut.setCommand(uacCommandTextArea.getText().trim());
        shortcut.setCommandOuput(uacOuputTextArea.getText().trim());
        shortcut.setVbsFileName(uacFileNameField.getText().trim());
    }
}
