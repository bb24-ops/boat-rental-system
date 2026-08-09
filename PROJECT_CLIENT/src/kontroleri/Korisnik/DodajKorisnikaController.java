/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.Korisnik;

import domen.Korisnik;
import domen.Luka;
import domen.Skiper;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.Korisnik.DodajKorniskaForma;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import kordinator.Kordinator;

/**
 *
 * @author boris
 */
public class DodajKorisnikaController {

    private final DodajKorniskaForma dkf;

    public DodajKorisnikaController(DodajKorniskaForma dkf) {
        this.dkf = dkf;

        dkf.pack();
        dkf.setLocationRelativeTo(null);
        dkf.setResizable(false);
        dkf.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        dkf.setIconImage(icon);

        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dkf.setVisible(true);
        pripremiFormu(mod);
        popuniComboBox();
    }

    private void addActionListener() {
        dkf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    String ime = dkf.getjTextFieldIme().getText().trim();
                    String prezime = dkf.getjTextFieldPrezime().getText().trim();
                    String brojTelefona = dkf.getjTextFieldBrTelefona().getText().trim();
                    Luka l = (Luka) dkf.getjComboBoxLuka().getSelectedItem();

                    // --- VALIDACIJA ---
                    if (ime.isEmpty() || prezime.isEmpty() || brojTelefona.isEmpty() || l == null || l.getIdLuka() <= 0) {
                        JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti korisnika", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    if (!ime.matches("[a-zA-Z]+") || !prezime.matches("[a-zA-Z]+")) {
                        JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti korisnika", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Korisnik k = new Korisnik();
                    k.setIdKorisnik(-1); // za dodavanje novi korisnik, id se generise u bazi
                    k.setIme(ime);
                    k.setPrezime(prezime);
                    k.setBrojTelefona(brojTelefona);
                    k.setLuka(l);

                    // --- Cuvanje ---
                    try {
                        komunikacija.Komunikacija.getInstanca().dodajKorisnika(k);
                        JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio korisnika", "Obavestenje!", JOptionPane.INFORMATION_MESSAGE);
                        Kordinator.getInstance().osveziKorisnikForma();
                        dkf.dispose();
                    } catch (Exception ex) {
                        dkf.dispose();
                    }

                } catch (Exception exp) {
                    dkf.dispose();
                }
            }
        });

        dkf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                try {
                    int id = Integer.parseInt(dkf.getjTextFieldIDKorisnika().getText().trim());
                    String ime = dkf.getjTextFieldIme().getText().trim();
                    String prezime = dkf.getjTextFieldPrezime().getText().trim();
                    String brojTelefona = dkf.getjTextFieldBrTelefona().getText().trim();
                    Luka l = (Luka) dkf.getjComboBoxLuka().getSelectedItem();

                    if (id <= 0 || ime.isEmpty() || prezime.isEmpty() || brojTelefona.isEmpty() || l == null || l.getIdLuka() <= 0) {
                        JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti korisnika", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!ime.matches("[a-zA-Z]+") || !prezime.matches("[a-zA-Z]+")) {
                        JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti korisnika", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Korisnik k = new Korisnik();
                    k.setIdKorisnik(id);
                    k.setIme(ime);
                    k.setPrezime(prezime);
                    k.setBrojTelefona(brojTelefona);
                    k.setLuka(l);

                    try {
                        komunikacija.Komunikacija.getInstanca().promeniKorisnika(k);
                        Kordinator.getInstance().osveziKorisnikForma();
                        JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio korisnika", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        dkf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti korisnika", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti korisnika", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dkf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // kad korisnik zatvori formu → osveži comboBoxeve
                Kordinator.getInstance().osveziComboBoxKorinsika();
            }
        });
        
        dkf.OtkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otkazi(e);
            }

            private void otkazi(ActionEvent e) {
                dkf.dispose();
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dkf.getjButtonIzmeni().setVisible(false);
                dkf.getjButtonDodaj().setVisible(true);
                dkf.getjButtonDodaj().setEnabled(true);
                dkf.getjTextFieldIDKorisnika().setEnabled(false);
                break;
            case IZMENI:
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjButtonIzmeni().setVisible(true);
                dkf.getjButtonIzmeni().setEnabled(true);
                dkf.getjTextFieldIDKorisnika().setEnabled(false);

                Korisnik k = (Korisnik) Kordinator.getInstance().vratiParam("korisnik");
                dkf.getjTextFieldIDKorisnika().setText(k.getIdKorisnik() + "");
                dkf.getjTextFieldIme().setText(k.getIme());
                dkf.getjTextFieldPrezime().setText(k.getPrezime());
                dkf.getjTextFieldBrTelefona().setText(k.getBrojTelefona());
                dkf.getjComboBoxLuka().setSelectedItem(k.getLuka());
                break;
            default:
                throw new AssertionError();
        }
    }

    private void popuniComboBox() {
        List<Luka> luke = komunikacija.Komunikacija.getInstanca().ucitajLuke();

        dkf.getjComboBoxLuka().removeAllItems();
        for (Luka l : luke) {
            dkf.getjComboBoxLuka().addItem(l);
        }
    }
}
