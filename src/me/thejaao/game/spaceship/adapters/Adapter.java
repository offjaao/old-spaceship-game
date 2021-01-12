package me.thejaao.game.spaceship.adapters;

import me.thejaao.game.spaceship.models.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Adapter extends KeyAdapter {

    private Player player;

    public Adapter() {
        player = new Player();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
}
