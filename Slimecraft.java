/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slimecraft;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author CSchafer
 */
public class Slimecraft extends JFrame  {
    private static int x = 800;
    private static int y = 600;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        JFrame j = new Slimecraft();
        World c = new World();
        j.add(c);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(x, y);
        j.setVisible(true);
        j.setLocationRelativeTo(null);
        j.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
             //   c.keyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
             //   c.keyReleased(e);
            }
        });

    }
    
}
