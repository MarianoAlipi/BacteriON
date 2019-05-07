/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pure_engine;
import java.awt.Point;
import java.awt.Rectangle;
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
  
    public Rectangle getPerimeter() {
        return new Rectangle(x, y, 10, 10);
    }
  
    public Point getPoint() {
        return new Point(x,y);
    }

    public void setIzquierdo(boolean izq) {
        this.izquierdo = izq;
    }
    
    public boolean isIzquierdo() {
        return izquierdo;
        
    }

    public boolean isDerecho() {
        return derecho;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        /*if (e.getButton() == MouseEvent.BUTTON1) {
            izquierdo = true;         
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            derecho = true;        
        }*/
    }

    @Override
    public void mousePressed(MouseEvent e) { 
        if (e.getButton() == MouseEvent.BUTTON1) {
           izquierdo = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
           derecho = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            izquierdo = false;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            derecho = false;
        }
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
        x = e.getX();
        y = e.getY();
    }

    public boolean mouseClicked(int CLICK) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

