/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;

import java.awt.Graphics;

/**
 *
 * @author Diego
 */
public class EstresBarra extends Item{
    
    public EstresBarra(Game game, int x, int y, int width, int height, int speed){
        super(game, x, y, width, height, speed);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.barraBackground, getX(), getY(), Constants.BARRA_WIDTH, Constants.BARRA_HEIGHT, null);
        if(width>=game.getWidth()*0.01*Constants.ESTRES_ALTO){
            g.drawImage(Assets.barraMortal, getX(), getY(), getWidth(), getHeight(), null);
        } else if(width>=game.getWidth()*0.009*Constants.ESTRES_BAJO){
            g.drawImage(Assets.barraEstresado, getX(), getY(), getWidth(), getHeight(), null);
        }else {
            g.drawImage(Assets.barraRelajado, getX(), getY(), getWidth(), getHeight(), null);
        }
    }
}
