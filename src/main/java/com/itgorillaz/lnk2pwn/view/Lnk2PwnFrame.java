package com.itgorillaz.lnk2pwn.view;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alee.laf.rootpane.WebFrame;
import com.itgorillaz.lnk2pwn.view.form.FormPanel;
import com.itgorillaz.lnk2pwn.view.utils.IconFactory;

import net.miginfocom.swing.MigLayout;

@Component
public class Lnk2PwnFrame extends WebFrame {
    
    private static final long serialVersionUID = 8887385829173862121L;
    
    private final MigLayout LAYOUT = new MigLayout("", 
            "[grow]", 
            "[]");
    
    @Autowired
    private MenuBar menubar;
    
    @Autowired
    private FormPanel formPanel;
    
    @PostConstruct
    private void initComponents() {
        this.setTitle("lnk2pwn");
        this.setName("lnk2pwn");
        this.setIconImage(IconFactory.getIcon("bomb.png").getImage());
        this.setLayout(LAYOUT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(menubar);
        this.add(formPanel, "grow");
    }
    
}
