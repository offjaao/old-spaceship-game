package me.thejaao.game.spaceship.models;

import javax.swing.*;
import java.awt.*;

public class Shot {

    private Image image;
    private int x, y;
    private int width, height;
    private boolean visible;

    private static final int widhtPanel = 1000;

    public Shot(int x, int y) {
        this.x = x;
        this.y = y;
        visible = true;
    }

    public void load() {
        ImageIcon imageIcon = new ImageIcon("res\\shoot.png");
        image = imageIcon.getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void update() {
        int velocity = 2;
        this.x += velocity;
        if (this.x > widhtPanel) {
            visible = false;
        }
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

}
