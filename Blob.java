/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slimecraft;
import java.awt.Color;

/**
 *
 * @author CSchafer
 */
public class Blob extends Slime {
    private static final Color COLOR = Color.GREEN;
    private static final int SPEED = 5;
        
    public Blob(int x, int y) {
        super(SPEED, x, y, COLOR);
    }  
    
    public void fight(Glob glob) {
        if (super.collide(glob)) {
            if (super.getStrength() >= glob.getStrength()) {
                this.Win();
                glob.die();
            } else {
                this.die();
                glob.Win();
            }
        }
    }
    
}
