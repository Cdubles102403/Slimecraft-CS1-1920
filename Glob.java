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
public class Glob extends Slime {
    private static final int SPEED = 3;
    private static final Color COLOR = Color.BLUE;
    public Glob(int x, int y) {
        super(SPEED, x, y, COLOR);
    }  
}
