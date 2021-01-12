package me.thejaao.game.spaceship.models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private int x, y;
    private int dx, dy;
    private Image image;
    private int height, width;
    private List<Shot> shots;
    private boolean visible;

    public Player() {
        this.x = 50;
        this.y = 264;
        this.visible = true;
        shots = new ArrayList<>();
    }

    public void load() {
        ImageIcon imageIcon = new ImageIcon("res\\spaceship.png");
        image = imageIcon.getImage();
        height = image.getHeight(null);
        width = image.getWidth(null);
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void simpleShot(){
        this.shots.add(new Shot(x + width, (int) (y + (height/2.5))));
    }

    public Rectangle getBound(){
        return new Rectangle(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_A:
                simpleShot();
                break;
            case KeyEvent.VK_UP:
                dy = -3;
                break;
            case KeyEvent.VK_DOWN:
                dy = 3;
                break;
            case KeyEvent.VK_LEFT:
                dx = -3;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 3;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                dy = -0;
                break;
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;
            case KeyEvent.VK_LEFT:
                dx = -0;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 0;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Shot> getShots() {
        return shots;
    }

}
