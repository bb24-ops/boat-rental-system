/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.Luka;

import domen.Administrator;
import domen.Luka;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.Luka.DodajLukuForma;
import forme.administrator.DodajAdministratoraForma;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import kordinator.Kordinator;

/**
 *
 * @author boris
 */
public class DodajLukuController {

    private final DodajLukuForma dlf;

    public DodajLukuController(DodajLukuForma dlf) {
        this.dlf = dlf;

        dlf.pack();
        dlf.setLocationRelativeTo(null);
        dlf.setResizable(false);
        dlf.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        dlf.setIconImage(icon);

        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        //pripremiFormu(mod);
        dlf.setVisible(true);
        pripremiFormu(mod);
    }

    private void addActionListener() {
        dlf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String naziv = dlf.getjTextFieldNaziv().getText().trim();
                String brMestaStr = dlf.getjTextFieldBrojMesta().getText().trim();
                int brMesta = 0;

                // --- VALIDACIJA ---
                if (naziv.isEmpty() || !naziv.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(dlf, "Sistem ne moze da kreira luku", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    brMesta = Integer.parseInt(brMestaStr);
                    if (brMesta <= 0) {
                        JOptionPane.showMessageDialog(dlf, "Sistem ne moze da kreira luku", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dlf, "Sistem ne moze da kreira luku", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // --- KREIRANJE I DODAVANJE ---
                Luka l = new Luka();
                l.setIdLuka(-1);
                l.setNaziv(naziv);
                l.setBrMesta(brMesta);

                try {
                    komunikacija.Komunikacija.getInstanca().dodajLuku(l);
                    JOptionPane.showMessageDialog(dlf, "Sistem je kreirao luku", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().osveziLukaFormu();
                    dlf.dispose();
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(dlf, "Sistem ne moze da kreira luku", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    exp.printStackTrace();
                }
            }
        });

        dlf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                try {
                    int id = Integer.parseInt(dlf.getjTextFieldIDLuke().getText());
                    String naziv = dlf.getjTextFieldNaziv().getText().trim();
                    String brMestaStr = dlf.getjTextFieldBrojMesta().getText().trim();
                    int brMesta;

                    if (naziv.isEmpty() || !naziv.matches("[a-zA-ZčćžšđČĆŽŠĐ\\s]+")) {
                        JOptionPane.showMessageDialog(dlf, "Sistem ne moze da zapamti izmene luke", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        brMesta = Integer.parseInt(brMestaStr);
                        if (brMesta <= 0) {
                            JOptionPane.showMessageDialog(dlf, "Sistem ne moze da zapamti izmene luke", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dlf, "Sistem ne moze da zapamti izmene luke", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Luka l = new Luka();
                    l.setIdLuka(id);
                    l.setNaziv(naziv);
                    l.setBrMesta(brMesta);

                    komunikacija.Komunikacija.getInstanca().promeniLuku(l);
                    JOptionPane.showMessageDialog(dlf, "Sistem je uspesno izmenio luku!", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().osveziLukaFormu();
                    dlf.dispose();
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(dlf, "Sistem nije izmenio luku!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        
        dlf.OtkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otkazi(e);
            }

            private void otkazi(ActionEvent e) {
                dlf.dispose();
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dlf.getjButtonIzmeni().setVisible(false);
                dlf.getjButtonDodaj().setVisible(true);
                dlf.getjButtonDodaj().setEnabled(true);
                dlf.getjTextFieldIDLuke().setEnabled(false);
                break;
            case IZMENI:
                dlf.getjButtonDodaj().setVisible(false);
                dlf.getjButtonIzmeni().setVisible(true);
                dlf.getjButtonIzmeni().setEnabled(true);
                dlf.getjTextFieldIDLuke().setEnabled(false);

                Luka l = (Luka) Kordinator.getInstance().vratiParam("luka");
                dlf.getjTextFieldIDLuke().setText(l.getIdLuka() + "");
                dlf.getjTextFieldNaziv().setText(l.getNaziv());
                dlf.getjTextFieldBrojMesta().setText(l.getBrMesta() + "");

                break;
            default:
                throw new AssertionError();
        }
    }
}
