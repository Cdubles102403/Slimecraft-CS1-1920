/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slimecraft;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author CSchafer
 */
public class Food extends Sprite {

    private static final Color COLOR = Color.PINK;
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private static final int SPEED = 0;

    public Food (int x, int y) {
        super(SPEED, x, y, WIDTH, HEIGHT, COLOR);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.fillOval(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

}