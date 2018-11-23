package com.itgorillaz.lnk2pwn.view.form;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alee.extended.painter.TitledBorderPainter;
import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import com.itgorillaz.lnk2pwn.view.action.GenerateShortcutAction;
import com.itgorillaz.lnk2pwn.view.utils.IconFactory;

import net.miginfocom.swing.MigLayout;

@Component
public class FormPanel extends WebPanel {
    
    private static final long serialVersionUID = 4905134163745537325L;
    
    private WebButton generateButton = new WebButton("Generate Shortcut", IconFactory.getIcon("skull.png"));
        
    @Autowired
    private ShortcutInfoPanel shortcutInfoPanel;
    
    @Autowired
    private UACBypassPanel uacBypassPanel;
            
    @Autowired
    private GenerateShortcutAction generateShortcutAction;
    
    @PostConstruct	
    private void initComponents() {
        this.setLayout(new MigLayout("", "[grow]", "[][grow][]"));
                        
        generateButton.addActionListener(generateShortcutAction);		
        shortcutInfoPanel.setPainter(new TitledBorderPainter<>("Shortcut"));				
        uacBypassPanel.setPainter(new TitledBorderPainter<>("UAC Bypass"));
        
        WebPanel generatePanel = createGenerateButtonPanel();
        
        this.add(shortcutInfoPanel, "grow,wrap");
        this.add(uacBypassPanel, "w 100%,h 100%,wrap");		
        this.add(generatePanel, "grow");		
    }
    
    private WebPanel createGenerateButtonPanel() {
        MigLayout layout = new MigLayout("", "[grow]", "");
        WebPanel panel = new WebPanel();
        panel.setLayout(layout);
        panel.add(generateButton, "east");
                
        return panel;
    }	

}
