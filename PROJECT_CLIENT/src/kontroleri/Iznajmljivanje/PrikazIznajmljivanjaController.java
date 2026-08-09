/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.Iznajmljivanje;

import com.sun.java.accessibility.util.AWTEventMonitor;
import domen.Iznajmljivanje;
import domen.Korisnik;
import domen.Skiper;
import domen.StavkaIzn;
import forme.FormaMod;
import forme.Iznajmljivanje.PrikazIznajmljivanjaForma;
import forme.model.ModelTabeleIznajmljivanje;
import forme.model.ModelTabeleSkiper;
import forme.model.ModelTabeleStavkaIzn;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;

/**
 *
 * @author boris
 */
public class PrikazIznajmljivanjaController {

    private final PrikazIznajmljivanjaForma pif;

    public PrikazIznajmljivanjaController(PrikazIznajmljivanjaForma prf) {
        this.pif = prf;

        pif.pack();
        pif.setLocationRelativeTo(null);
        pif.setResizable(false);
        pif.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        pif.setIconImage(icon);

        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pif.setVisible(true);
        //klijentski zahtev da ucitamo sve moguce skipere i da kreiramo model tabele!
    }

    public void pripremiFormu() {
        List<Iznajmljivanje> iznajmljivnja = Komunikacija.getInstanca().ucitajIznajmljivanja();
        ModelTabeleIznajmljivanje mti = new ModelTabeleIznajmljivanje(iznajmljivnja);
        pif.getjTableIznajmljivanja().setModel(mti);
    }

    private void addActionListener() {

        pif.azurirajIznajmljivanjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pif.getjTableIznajmljivanja().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pif, "Sistem ne moze da nadje iznajmljivanje.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getjTableIznajmljivanja().getModel();
                    Iznajmljivanje i = mti.getLista().get(red);

                    List<StavkaIzn> stavke = komunikacija.Komunikacija.getInstanca().ucitajIznajmljivanjeS(i.getIdIznajmljivanje());
                    i.setStavke(stavke);
                    Kordinator.getInstance().dodajParam("iznajmljivanje_za_izmenu", i);
                    Kordinator.getInstance().otvoriIzmeniIznajmljivanjeFormu();

                }
            }
        });

        pif.pretraziIznAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cenaText = pif.getjTextFieldIznosIzn().getText().trim();
                String imeKorisnika = pif.getjTextFieldIme().getText().trim();

                Iznajmljivanje iznajmljivanje = new Iznajmljivanje();

                try {
                    if (!cenaText.isEmpty()) {
                        double maxCena = Double.parseDouble(cenaText);
                        iznajmljivanje.setUkupanIznos(maxCena);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(pif, "Unesite validnu cenu (npr. 1500)", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Korisnik k = new Korisnik();

                if (!imeKorisnika.isEmpty()) {
                    k.setIme(imeKorisnika);
                    iznajmljivanje.setKorisnik(k);
                } else {
                    k.setIme("/");
                    iznajmljivanje.setKorisnik(k);
                }

                if (imeKorisnika.isEmpty() && cenaText.isEmpty()) {
                    JOptionPane.showMessageDialog(pif, "Sistem ne može da nađe iznajmljivanja po zadatim kriterijumima.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<Iznajmljivanje> iznajmljivanjaPoKrit = new ArrayList<>();
                //Slanje zahteva ka serveru
                try {
                    iznajmljivanjaPoKrit = komunikacija.Komunikacija.getInstanca().pretraziIznajmljivanja(iznajmljivanje);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(PrikazIznajmljivanjaController.class.getName()).log(Level.SEVERE, null, ex);
                }

                ModelTabeleIznajmljivanje mti = (ModelTabeleIznajmljivanje) pif.getjTableIznajmljivanja().getModel();
                if (iznajmljivanjaPoKrit != null && !iznajmljivanjaPoKrit.isEmpty()) {
                    JOptionPane.showMessageDialog(pif, "Sistem je našao iznajmljivanja po zadatim kriterijumima.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    mti.setLista(iznajmljivanjaPoKrit);
                } else {
                    JOptionPane.showMessageDialog(pif, "Sistem ne može da nađe iznajmljivanja po zadatim kriterijumima.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    if (iznajmljivanjaPoKrit.isEmpty()) {
                        mti.setLista(new ArrayList<>());
                        mti.fireTableDataChanged();
                    }
                }
            }
        });

        pif.prikaziSveAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
                //pif.getjButtonPrikaziSve().setEnabled(false);
                pif.getjButtonPretraziIzn().setEnabled(true);
                pif.getjTextFieldIznosIzn().setText("");
                pif.getjTextFieldIme().setText("");
            }
        });
    }

}
