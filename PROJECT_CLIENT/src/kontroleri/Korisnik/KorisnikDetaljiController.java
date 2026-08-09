/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.Korisnik;

import domen.Korisnik;
import domen.Luka;
import forme.Korisnik.KorisnikDetalji;
import forme.Korisnik.PrikazKorisnikaForma;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import kordinator.Kordinator;

/**
 *
 * @author boris
 */
public class KorisnikDetaljiController {

    private final KorisnikDetalji kd;

    public KorisnikDetaljiController(KorisnikDetalji kd) {
        this.kd = kd;

        kd.pack();
        kd.setLocationRelativeTo(null);
        kd.setResizable(false);
        kd.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        kd.setIconImage(icon);

        
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        kd.setVisible(true);
    }

    private void pripremiFormu() {
        
        kd.getjTextFieldIDKorisnika().setEnabled(false);
        kd.getjTextFieldIme().setEnabled(false);
        kd.getjTextFieldPrezime().setEnabled(false);
        kd.getjTextFieldBrTelefona().setEnabled(false);
        kd.getjComboBox1().setEnabled(false);
        kd.getjTextFieldIDLuke().setEnabled(false);
        kd.getjTextFieldNaziv().setEnabled(false);
        kd.getjTextFieldBrojMesta().setEnabled(false);
        
        
        
        Korisnik k = (Korisnik) Kordinator.getInstance().vratiParam("korisnik");
        kd.getjTextFieldIDKorisnika().setText(k.getIdKorisnik() + "");
        kd.getjTextFieldIme().setText(k.getIme());
        kd.getjTextFieldPrezime().setText(k.getPrezime());
        kd.getjTextFieldBrTelefona().setText(k.getBrojTelefona());
        System.out.println(k.getIme() + " " + k.getPrezime() + " " + k.getLuka().getNaziv());
        
        

        Luka l = Kordinator.getInstance().getLukaKoriniska();
        kd.getjComboBox1().addItem(l);
        kd.getjTextFieldIDLuke().setText(l.getIdLuka() + "");
        kd.getjTextFieldNaziv().setText(l.getNaziv());
        kd.getjTextFieldBrojMesta().setText(l.getBrMesta() + "");
        
        

    }

    private void addActionListener() {
        kd.OtkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otkazi(e);
            }

            private void otkazi(ActionEvent e) {
                kd.dispose();
            }
        });
    }

    private void popuniComboBox() {
        List<Luka> luke = komunikacija.Komunikacija.getInstanca().ucitajLuke();

        kd.getjComboBox1().removeAllItems();
        for (Luka l : luke) {
            kd.getjComboBox1().addItem(l);
        }
    }
}
