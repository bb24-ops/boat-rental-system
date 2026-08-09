/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import forme.ServerskaForma;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author boris
 */
public class Main {

    public static void main(String[] args) {
        ServerskaForma sf = new ServerskaForma();

        sf.pack();
        sf.setLocationRelativeTo(null);
        sf.setVisible(true);
        sf.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage(ServerskaForma.class.getResource("/slike/sidro.png"));
        sf.setIconImage(icon);

        sf.setVisible(true);
    }
}
