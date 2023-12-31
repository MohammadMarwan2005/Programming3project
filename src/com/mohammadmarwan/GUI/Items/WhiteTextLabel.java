package com.mohammadmarwan.GUI.Items;

import javax.swing.*;
import java.awt.*;

public class WhiteTextLabel extends JLabel {
    public WhiteTextLabel(String text) {
        super(text);
        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
    }
}
