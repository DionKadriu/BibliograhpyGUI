package com.stir.cscu9t4assignment2021;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Dion Kadriu
 * class for the animation in the beginning of the application
 */

public class MyPanelAnimation extends JPanel implements ActionListener {
    private int elapsedTime = 0;
    private int timerDelay = 50;
    private int max = 20000;
    int angle;

    final int PANEL_WIDHT = 500;
    final int PANEL_HEIGHT = 500;
    Image movingImage;
    Image backgroundImage;
    Timer timer;
    int xMovement = 2;
    int yMovement = 1;
    int x = 0;
    int y = 0;

    MyPanelAnimation() {

        setName("Bibligraphy creator");
        this.setPreferredSize(new Dimension(PANEL_WIDHT, PANEL_HEIGHT));//setting the panel width and height
        this.setBackground(Color.blue);
        movingImage = new ImageIcon("src/Icons/Animation.png").getImage();
        backgroundImage= new ImageIcon("src/Icons/img_1.png").getImage();
        timer = new Timer(2, this);
        timer.start();//start the movement

    }

    public void paint(Graphics g) {
        super.paint(g);
               Graphics g2D = (Graphics2D) g;

        g2D.drawImage(backgroundImage,0,0,null);
        ((Graphics2D) g2D).rotate(Math.toRadians(angle),x,y);//make the figure rotate to the boundaries of x changing and y
        g2D.drawImage(movingImage, x, y, null);//boundaries where the image is going to move

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angle+=1.5;
        repaint();
        if (x > PANEL_WIDHT - movingImage.getWidth(null) || x < 0) {
            xMovement = xMovement * -1; //changing the value of the position of the figures
        }
        x = x + xMovement;//changing the value of the position of the figures
        repaint();
        if (y >= PANEL_HEIGHT - movingImage.getHeight(null) || y < 0) {
            yMovement = yMovement * -1;//changing the y position of the figure

        }
        y = y + yMovement;
        repaint();
        elapsedTime += timerDelay; // you could use getDelay here but it
        // is in milliseconds.
        if (elapsedTime >= max) {
            Timer s = (Timer) e.getSource();
            s.stop(); //stop the movement
        }
    }

}

