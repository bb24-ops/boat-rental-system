/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Administrator;
import forme.GlavnaForma;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author boris
 */
public class GlavnaFormaController {

    private final GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;

        gf.pack();
        gf.setLocationRelativeTo(null);
        gf.setResizable(false);
        gf.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        gf.setIconImage(icon);

        otvoriFormu();
    }

    public void otvoriFormu() {
        Administrator ulogovani = kordinator.Kordinator.getInstance().getUlogovani();
        String imeUVokativu = uVokativu(ulogovani.getIme(), ulogovani.getPrezime());
        gf.setVisible(true);
        gf.getjLabelUlogovani2().setText(imeUVokativu + "!");
    }

    public static String uVokativu(String ime, String prezime) {
        String imeVokativ = ime;
        String prezimeVokativ = prezime;

        if (ime.endsWith("r")) {
            imeVokativ += "e";
        } else if (ime.endsWith("a")) {
            imeVokativ = ime.substring(0, ime.length() - 1) + "o";
        }
        if (prezime.endsWith("c")) {
            prezimeVokativ += "u";
        } else {
            prezimeVokativ += "u";
        }
        return imeVokativ + " " + prezimeVokativ;
    }

    public void zabraniPrisup() {
        gf.getjMenu5().setEnabled(false);
    }

}
