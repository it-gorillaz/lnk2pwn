package com.itgorillaz.lnk2pwn.view;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.alee.extended.label.WebLinkLabel;
import com.alee.extended.label.WebMultiLineLabel;
import com.alee.laf.rootpane.WebDialog;
import com.itgorillaz.lnk2pwn.view.utils.IconFactory;

import net.miginfocom.swing.MigLayout;

@Component
public class AboutDialog extends WebDialog {
    
    private static final long serialVersionUID = 2844121433961138163L;
    
    @PostConstruct
    private void initComponents() {
        this.setModal(true);
        this.setTitle("About");
        this.setSize(400, 250);
        this.setLayout(new MigLayout("", "[grow]", "[]50[]"));
                
        WebMultiLineLabel label = new WebMultiLineLabel (getBanner(), WebMultiLineLabel.CENTER);		
        this.add(label, "wrap");
        
        WebLinkLabel github = new WebLinkLabel("https://github.com/it-gorillaz/lnk2pwn", WebLinkLabel.CENTER);
        github.setLink("https://github.com/it-gorillaz/lnk2pwn");
        github.setIcon(IconFactory.getIcon("github.png"));
        this.add(github, "grow");
    }
    
    private String getBanner() {
        return 
              ",-*\n" +
              "(_).lnk\n" +
         "-----------------\n" +           
             "<lnk2pwn>\n" +
    "Malicious Shortcut(.lnk) Generator\n" +
                           "[v1.0.0]";		
    }
    
}
