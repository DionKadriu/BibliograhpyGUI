package com.stir.cscu9t4assignment2021;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;
/**
 * @author Dion Kadriu
 */
public class MyFrameAnimation extends JFrame {
    MyPanelAnimation myPanelAnimation;
        MyFrameAnimation(){
            super("Bibliography Application");//setting the name of the application
            myPanelAnimation= new MyPanelAnimation();//setting the panel to the jFrame
            myPanelAnimation.setForeground(Color.red);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setting it to be able to close on close
            this.add(myPanelAnimation);
            this.pack();//Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
            this.setLocationRelativeTo(null);//setting it to open in the middle of the window
            this.setVisible(true);//making it visible


        }
}
