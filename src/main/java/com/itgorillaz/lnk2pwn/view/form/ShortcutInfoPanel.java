package com.itgorillaz.lnk2pwn.view.form;

import javax.annotation.PostConstruct;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebTextField;
import com.itgorillaz.lnk2pwn.model.Shortcut;

import net.miginfocom.swing.MigLayout;

@Component
public class ShortcutInfoPanel extends WebPanel implements DocumentListener, ChangeListener {

    private static final long serialVersionUID = 4663408720514745622L;
    
    private final String DEFAULT_TARGET_PATH = "C:\\Windows\\System32\\cmd.exe";
    private final String DEFAULT_WORKING_DIR = "C:\\Windows\\System32";
    private final String DEFAULT_ARGUMENTS = "/c notepad.exe";
    private final String DEFAULT_FAKE_EXTENSION = ".txt";
    private final String DEFAULT_ICON_DLL = "C:\\Windows\\System32\\notepad.exe";
    private final String[] WINDOW_STYLES = {"MINIMIZED", "MAXIMIZED", "NORMAL"};
    
    private WebTextField targetPathField = new WebTextField();
    private WebTextField workingDirField = new WebTextField();
    private WebTextField argumentsField = new WebTextField();	
    private WebTextField shortcutFileNameField = new WebTextField();
    private WebTextField fakeExtensionField = new WebTextField();
    private WebTextField iconPathField = new WebTextField();
    private WebTextField iconIndexField = new WebTextField();
    private WebComboBox windowStyleComboBox = new WebComboBox(WINDOW_STYLES);
    
    @Autowired
    private Shortcut shortcut;
        
    @PostConstruct
    private void initComponents() {		
        this.setLayout(new MigLayout("", "[grow]", ""));
        
        targetPathField.getDocument().addDocumentListener(this);
        workingDirField.getDocument().addDocumentListener(this);
        argumentsField.getDocument().addDocumentListener(this);
        shortcutFileNameField.getDocument().addDocumentListener(this);
        fakeExtensionField.getDocument().addDocumentListener(this);
        iconPathField.getDocument().addDocumentListener(this);
        iconIndexField.getDocument().addDocumentListener(this);
        
        targetPathField.setText(DEFAULT_TARGET_PATH);
        workingDirField.setText(DEFAULT_WORKING_DIR);
        argumentsField.setText(DEFAULT_ARGUMENTS);
        fakeExtensionField.setText(DEFAULT_FAKE_EXTENSION);
        iconPathField.setText(DEFAULT_ICON_DLL);
                        
        WebPanel targetAndWorkingDirPanel = createTargetPathAndWorkingDirPanel();
        WebPanel argumentsPanel = createArgumentsPanel();		
        WebPanel shortcutDetailsPanel = createShortcutDetailsPanel();
        
        this.add(targetAndWorkingDirPanel, "grow,wrap");
        this.add(argumentsPanel, "grow,wrap");		
        this.add(shortcutDetailsPanel, "grow,wrap");
    }
    
    private WebPanel createTargetPathAndWorkingDirPanel() {
        MigLayout layout = new MigLayout("", "[grow][grow]", "");
        WebPanel panel = new WebPanel();
        panel.setLayout(layout);
        panel.add(new WebLabel("Target Path"));
        panel.add(new WebLabel("Working Dir"), "wrap");
        panel.add(targetPathField, "grow");			
        panel.add(workingDirField, "grow");
        return panel;
    }
    
    private WebPanel createArgumentsPanel() {
        MigLayout layout = new MigLayout("", "[grow]", "");
        WebPanel panel = new WebPanel();
        panel.setLayout(layout);
        panel.add(new WebLabel("Arguments"), "wrap");
        panel.add(argumentsField, "grow");
        return panel;
    }
    
    private WebPanel createShortcutDetailsPanel() {
        MigLayout layout = new MigLayout("", "[grow][grow][grow][][grow]", "");
        WebPanel panel = new WebPanel();
        panel.setLayout(layout);
        panel.add(new WebLabel("File Name"));
        panel.add(new WebLabel("Fake Extension"));
        panel.add(new WebLabel("Icon Location"));
        panel.add(new WebLabel("Icon Index"));
        panel.add(new WebLabel("Window Style"), "wrap");						
        panel.add(shortcutFileNameField, "grow");		
        panel.add(fakeExtensionField, "grow");				
        panel.add(iconPathField, "grow");
        panel.add(iconIndexField, "grow");
        panel.add(windowStyleComboBox, "grow");
        
        return panel;
    }
    
    @Override
    public void changedUpdate(DocumentEvent arg0) {
        updateModel();
    }

    @Override
    public void insertUpdate(DocumentEvent arg0) {
        updateModel();
    }

    @Override
    public void removeUpdate(DocumentEvent arg0) {
        updateModel();
    }
    
    @Override
    public void stateChanged(ChangeEvent arg0) {
        updateModel();		
    }
    
    private void updateModel() {
        Integer iconIndex = null;
        
        try {
            iconIndex = Integer.parseInt(iconIndexField.getText());
        } catch(NumberFormatException e) {
            // do nothing
        }
        
        shortcut.setTargetPath(targetPathField.getText());		
        shortcut.setWorkingDir(workingDirField.getText());
        shortcut.setArguments(argumentsField.getText());		
        shortcut.setFileName(shortcutFileNameField.getText());
        shortcut.setFakeExtension(fakeExtensionField.getText());
        shortcut.setIconLocation(iconPathField.getText());
        shortcut.setIconIndex(iconIndex);
        shortcut.setWindowStyle(String.valueOf(windowStyleComboBox.getSelectedItem()));		
    }
    
}
