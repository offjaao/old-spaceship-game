package me.thejaao.game.spaceship.models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Phase extends JPanel implements ActionListener {

    private Image background;
    private Player player;
    private Timer timer;
    private List<Enemy> enemies;
    private boolean ingame;

    public Phase() {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon imageIcon = new ImageIcon("res\\background.jpg");
        background = imageIcon.getImage();

        player = new Player();
        player.load();

        addKeyListener(new Adapter());
        timer = new Timer(5, this);
        timer.start();
        initializeEnemies();
        ingame = true;
    }

    public void initializeEnemies() {
        int[] coordinates = new int[40];
        enemies = new ArrayList<>();

        for (int i = 0; i < coordinates.length; i++) {
            int x = (int) (Math.random() * 8000 + 1024);
            int y = (int) (Math.random() * 650 + 30);
            enemies.add(new Enemy(x, y));
        }
    }

    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        if (ingame) {
            graphics2D.drawImage(background, 0, 0, null);
            graphics2D.drawImage(player.getImage(), player.getX(), player.getY(), this);
            List<Shot> shots = player.getShots();
            for (Shot shot : shots) {
                shot.load();
                graphics2D.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
            }

            for (Enemy en : enemies) {
                en.load();
                graphics2D.drawImage(en.getImage(), en.getX(), en.getY(), this);
            }
        } else {
            ImageIcon endGame = new ImageIcon("res\\endgame.png");
            graphics2D.drawImage(endGame.getImage(), 350, 250, null);
        }

        graphics.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();

        List<Shot> shots = player.getShots();
        for (int i = 0; i < shots.size(); i++) {
            Shot shot = shots.get(i);
            if (shot.isVisible()) {
                shot.update();
            } else {
                shots.remove(i);
            }
        }
        for (int o = 0; o < enemies.size(); o++) {
            Enemy en = enemies.get(o);
            if (en.isVisible()) {
                en.update();
            } else {
                enemies.remove(o);
            }
        }
        checkColision();
        repaint();
    }

    public void checkColision() {
        Rectangle spaceshipR = player.getBound();
        Rectangle enemyR;
        Rectangle shotR;
        for (Enemy tempEnemy : enemies) {
            enemyR = tempEnemy.getBound();
            if (spaceshipR.intersects(enemyR)) {
                player.setVisible(false);
                tempEnemy.setVisible(false);
                ingame = false;
            }
        }
        List<Shot> shots = player.getShots();
        for (Shot tempShots : shots) {
            shotR = tempShots.getBound();
            for (Enemy tempEnemy : enemies) {
                enemyR = tempEnemy.getBound();
                if (shotR.intersects(enemyR)) {
                    tempEnemy.setVisible(false);
                    tempShots.setVisible(false);
                }
            }
        }
    }

    private class Adapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}
