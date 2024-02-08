package factories;

import javax.swing.*;
import java.awt.*;

public class WindowFactory {
    public static JFrame getDummyWindow(){
        return getDummyWindow(true);
    }
    public static JFrame getDummyWindow(boolean visibility){
        JFrame frame = new JFrame();
        frame.setTitle("dummy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
//        frame.pack(); //set window to the min size possible, fitting the components inside within
        frame.setVisible(visibility);
        frame.setResizable(false);
        return frame;
    }

    public static JFrame getWindow(String title, Dimension size){
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size);
        frame.setVisible(true);
        return frame;
    }

    public static JFrame getWindow(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

}
