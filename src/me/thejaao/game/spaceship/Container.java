package me.thejaao.game.spaceship;

import me.thejaao.game.spaceship.models.Phase;

import javax.swing.*;

public class Container extends JFrame {

    public Container() {
        add(new Phase());
        setTitle("Space Ship - 2D Game");
        ImageIcon icon = new ImageIcon("res//icon-spaceship.png");
        this.setIconImage(icon.getImage());
        setSize(1024, 728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Container();
    }
}
