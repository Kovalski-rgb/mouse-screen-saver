package factories;

import utils.Bound;

import javax.swing.*;
import java.awt.*;

public class TextFactory {
    public static JTextField getDummyButton(){
        JTextField button = new JTextField();
        button.setText("dummy");
        var rectangle = new Rectangle(100, 100);
        button.setBounds(rectangle);
//        button.setBounds(50,100,95,30);
        button.setVisible(true);
        button.setEnabled(true);
        return button;
    }

    public static JTextField getButton(String text, Bound bound){
        JTextField button = new JTextField();
        button.setText(text);
        button.setBounds(bound.x, bound.y, bound.width, bound.height);
        button.setVisible(true);
        button.setEnabled(true);
        return button;
    }
}
