package factories;

import utils.Bound;

import javax.swing.*;
import java.awt.*;

public class ButtonFactory {

    static final int DEFAULT_SIZE = 100;

    public static JButton getDummyButton(){
        JButton button = new JButton();
        button.setText("dummy");
        var rectangle = new Rectangle(DEFAULT_SIZE, DEFAULT_SIZE);
        button.setBounds(rectangle);
        button.setVisible(true);
        button.setEnabled(true);
        return button;
    }

    public static JButton getButton(String text, Rectangle rect){
        JButton button = new JButton();
        button.setText(text);
        button.setBounds(rect.x, rect.y, rect.width, rect.height);
        button.setVisible(true);
        button.setEnabled(true);
        return button;
    }

}
