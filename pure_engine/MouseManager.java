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
    
    /**
     * Constructor method for mouse manager
     */
    public MouseManager() {
        izquierdo = false;
        derecho = false;
    }
    
    /**
     * gets x value for mouse position
     * @return an <code>int</code> corresponding to the x position
     */
    public int getX() {
        return x;
    }

    /**
     * gets y value for mouse position
     * @return an <code>int</code> corresponding to the y position
     */
    public int getY() {
        return y;
    }
  
    /**
     * gets the perimeter for a 10*10 rectangle around the mouse
     * @return the <code>rectangle</code> surrounding the mouse
     */
    public Rectangle getPerimeter() {
        return new Rectangle(x, y, 10, 10);
    }
    
    /**
     * gets x y values for mouse position
     * @return the <code>Point</code> corresponding to the position
     */
    public Point getPoint() {
        return new Point(x,y);
    }
    
    /**
     * sets the left click value
     * @param izq a <code>boolean</code> that says if clicked or not
     */
    public void setIzquierdo(boolean izq) {
        this.izquierdo = izq;
    }
    
    /**
     * checks if the left click is pressed
     * @return a <code>boolean</code> that says if it's pressed
     */
    public boolean isIzquierdo() {
        return izquierdo;
    }
    /**
     * checks if the right click is pressed
     * @return a <code>boolean</code> that says if it's pressed
     */
    public boolean isDerecho() {
        return derecho;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    /**
     * checks if the a button is pressed
     * @param e a <code>MouseEvent</code> with the action
     */
    @Override
    public void mousePressed(MouseEvent e) { 
        if (e.getButton() == MouseEvent.BUTTON1) {
           izquierdo = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
           derecho = true;
        }
    }

    /**
     * checks if the a button is released
     * @param e a <code>MouseEvent</code> with the action
     */
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

    /**
     * checks if the mouse is moved
     * @param e a <code>MouseEvent</code> with the action
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public boolean mouseClicked(int CLICK) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

