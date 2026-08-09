/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.Korisnik;

import domen.Iznajmljivanje;
import domen.Korisnik;
import forme.Korisnik.PrikazKorisnikaForma;
import forme.model.ModelTabeleIznajmljivanje;
import forme.model.ModelTabeleKorisnici;
import forme.model.ModelTabeleSkiper;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kontroleri.Iznajmljivanje.PrikazIznajmljivanjaController;
import kordinator.Kordinator;

/**
 *
 * @author boris
 */
public class PrikazKorisnikaController {

    private final PrikazKorisnikaForma pkf;

    public PrikazKorisnikaController(PrikazKorisnikaForma pkf) {
        this.pkf = pkf;

        pkf.pack();
        pkf.setLocationRelativeTo(null);
        pkf.setResizable(false);
        pkf.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        pkf.setIconImage(icon);

        //ovde kreces sa dugmetom za brisanje!
        addActionListener();

    }

    public void otvoriFormu() {
        pripremiFormu();
        pkf.setVisible(true);
        //klijentski zahtev da ucitamo sve moguce skipere i da kreiramo model tabele!
    }

    public void pripremiFormu() {
        List<Korisnik> korisnici = Komunikacija.getInstanca().ucitajKorisnike();
        ModelTabeleKorisnici mtk = new ModelTabeleKorisnici(korisnici);
        pkf.getjTableKorisnici().setModel(mtk);
    }

    private void addActionListener() {
        pkf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = pkf.getjTextFieldIme().getText().trim();
                String prezime = pkf.getjTextFieldPrezime().getText().trim();
                
                Korisnik k = new Korisnik();
                if (!ime.isEmpty()) {
                    k.setIme(ime);
                } else {
                    k.setIme("/");
                }
                
                if (!prezime.isEmpty()) {
                    k.setPrezime(prezime);
                } else {
                    k.setPrezime("/");
                }

                if (ime.isEmpty() && prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe korisnika po zadatim kriterijumima.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<Korisnik> korisniciPoKrit = new ArrayList<>();
                try {
                    korisniciPoKrit = komunikacija.Komunikacija.getInstanca().pretraziKorisnika(k);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(PrikazIznajmljivanjaController.class.getName()).log(Level.SEVERE, null, ex);
                }

                ModelTabeleKorisnici mtk = (ModelTabeleKorisnici) pkf.getjTableKorisnici().getModel();
                if (korisniciPoKrit != null && !korisniciPoKrit.isEmpty()) {
                    JOptionPane.showMessageDialog(pkf, "Sistem je našao korisnike po zadatim kriterijumima.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
                    mtk.setLista(korisniciPoKrit);
                } else {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe korisnike po zadatim kriterijumima.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    if (korisniciPoKrit.isEmpty()) {
                        mtk.setLista(new ArrayList<>());
                        mtk.fireTableDataChanged();
                    }
                }
            }
        });

        pkf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
                pkf.getjTextFieldIme().setText("");
                pkf.getjTextFieldPrezime().setText("");
            }
        });

        pkf.addBtnKreirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kordinator.Kordinator.getInstance().otvoriKreirajKorisnikaFormu();
            }
        });

        pkf.addBtnPromeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKorisnici().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da nadje korisnika.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pkf, "Sistem je nasao korisnika.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleKorisnici mtk = (ModelTabeleKorisnici) pkf.getjTableKorisnici().getModel();
                    Korisnik k = mtk.getLista().get(red);
                    kordinator.Kordinator.getInstance().dodajParam("korisnik", k);
                    kordinator.Kordinator.getInstance().otvoriIzmeniKorisnikaFormu();
                }
            }
        });

        pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKorisnici().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da obrise korisnika.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pkf, "Sistem je nasao korisnika", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleKorisnici mtk = (ModelTabeleKorisnici) pkf.getjTableKorisnici().getModel();
                    Korisnik k = mtk.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiKorisnika(k);
                        JOptionPane.showMessageDialog(pkf, "Sistem je uspesno obrisao korisnika.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();//ova metoda opet ucitava listu iz baze gde je ovaj obrisan!!
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(pkf, "Sistem ne moze da obrise korisnika.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        pkf.getjButtonDetalji(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKorisnici().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da nadje korisnika.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pkf, "Sistem je nasao korisnika.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleKorisnici mtk = (ModelTabeleKorisnici) pkf.getjTableKorisnici().getModel();
                    Korisnik k = mtk.getLista().get(red);
                    Kordinator.getInstance().dodajParam("korisnik", k);
                    Kordinator.getInstance().setLukaKoriniska(k.getLuka());
                    kordinator.Kordinator.getInstance().otvoriDetaljiKorisnikFormu();
                }
            }
        });

    }

    public void osveziFormu() {
        pripremiFormu();
    }
}
