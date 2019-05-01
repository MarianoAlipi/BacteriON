/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pure_engine;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Diego
 */
public class MouseManager  implements MouseListener, MouseMotionListener {
    private boolean izquierdo;          // to check if left has been pushed
    private boolean derecho;            // to check if right has been pushed
    private int x;                      // to get x position of the mouse
    private int y;                      // to get y position of the mouse
    
    public MouseManager() {
        izquierdo = false;
        derecho = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Point getPoint() {
        return new Point(x,y);
    }

    public boolean isIzquierdo() {
        if(izquierdo){
            izquierdo = false;
            return true;
        }
        return izquierdo;
    }

    public boolean isDerecho() {
        if(derecho){
            derecho = false;
            return true;
        }
        return derecho;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            izquierdo = true;
            x = e.getX();
            y = e.getY();            
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            derecho = true;
            x = e.getX();
            y = e.getY();            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

