package com.kdu.cryptotrading.gui;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

/**
 * GUI class for logging the ongoing transactions in the market.
 */
public class LogGUI {
    public static JFrame jFrame = new JFrame("Crypto Log");
    public static JTextArea jTextArea = new JTextArea();

    /**
     * Initializes the GUI.
     */
    public static void initialize() {
        JScrollPane scroll = new JScrollPane(jTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1900, 400);
        jTextArea.setFont(jTextArea.getFont().deriveFont(20f));
        DefaultCaret caret = (DefaultCaret) jTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jFrame.setVisible(true);
        jFrame.add(scroll);
    }
}
