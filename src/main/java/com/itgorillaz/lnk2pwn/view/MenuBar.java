package com.itgorillaz.lnk2pwn.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.separator.WebSeparator;
import com.itgorillaz.lnk2pwn.view.action.ExitAction;
import com.itgorillaz.lnk2pwn.view.action.ShowAboutDialogAction;
import com.itgorillaz.lnk2pwn.view.utils.IconFactory;

@Component
public class MenuBar extends WebMenuBar {

    private static final long serialVersionUID = -288171329249268844L;

    @Autowired
    private ExitAction exitAction;
    
    @Autowired
    private ShowAboutDialogAction showAboutDialogAction;
    
    @PostConstruct	
    private void initComponents() {
        WebMenu menu = new WebMenu("File");
        menu.setIcon(IconFactory.getIcon("file.png"));
        
        WebMenuItem about = new WebMenuItem("About");
        about.setIcon(IconFactory.getIcon("about.png"));
        about.addActionListener(showAboutDialogAction);
        
        WebMenuItem exit = new WebMenuItem("Exit");
        exit.setIcon(IconFactory.getIcon("exit.png"));
        exit.addActionListener(exitAction);
        
        menu.add(about);
        menu.add(new WebSeparator());
        menu.add(exit);		
        
        this.add(menu);
    }
    
}
