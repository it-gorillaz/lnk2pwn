package com.itgorillaz.lnk2pwn.view.core;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

@Component
public class DefaultWindowController implements WindowController, AWTEventListener {

    private Window window;
    private JFrame root;
        
    @Override
    public void show(JFrame frame, BoundsPolicy policy) {
        Toolkit.getDefaultToolkit().addAWTEventListener(this, AWTEvent.WINDOW_EVENT_MASK);
        this.root = frame;
        this.initBounds(frame, policy);
        frame.setVisible(true);
    }

    @Override
    public Window getActiveWindow() {
        return window;
    }

    @Override
    public JFrame getRootFrame() {
        return root;
    }
    
    @Override
    public void eventDispatched(AWTEvent event) {
        if (event instanceof WindowEvent) {
            WindowEvent windowEvent = (WindowEvent) event;
            
            switch(windowEvent.getID())
            {
                case WindowEvent.WINDOW_ACTIVATED:
                    window = windowEvent.getWindow();
                    break;
                    
                case WindowEvent.WINDOW_DEACTIVATED:
                    window = null;
                    break;
                    
                default:
                    break;
            }
            
        }
    }
        
    private void initBounds(JFrame frame, BoundsPolicy policy) {
        if (!EventQueue.isDispatchThread()) {
            throw new IllegalStateException("WindowController.show() should be called "
                    + "from the Event Dispatch Thread.");
        }
        
        switch(policy) {
            
            case CENTER_ONLY:
                frame.setLocationRelativeTo(null);
                break;
            
            case MAXIMIZE_BOTH:
                frame.setState(JFrame.MAXIMIZED_BOTH);
                break;
                
            case PACK_ONLY:
                frame.pack();
                break;
                
            case PACK_AND_CENTER:
                frame.pack();
                frame.setLocationRelativeTo(null);
                break;
            
            case MAXIMIZE:
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension dimension = toolkit.getScreenSize();
                Insets insets = toolkit.getScreenInsets(frame.getGraphicsConfiguration());
                
                int width = dimension.width - (insets.left + insets.top);
                int height = dimension.height - (insets.top + insets.bottom);
                int x = insets.left;
                int y = insets.right;
                
                frame.pack();
                frame.setSize(width, height);
                frame.setLocation(x, y);
                
                break;
            
            case RESTORE_LAST_STATE:
                break;
            
            default:
                break;			
        }
        
    }
}
