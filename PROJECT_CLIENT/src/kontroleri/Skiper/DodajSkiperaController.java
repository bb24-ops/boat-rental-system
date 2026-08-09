/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.Skiper;

import domen.Administrator;
import domen.Skiper;
import forme.Skiper.DodajSkiperaForma;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import forme.Skiper.PrikazSkiperaForma;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;

/**
 *
 * @author boris
 */
public class DodajSkiperaController {

    private final DodajSkiperaForma dsf;

    public DodajSkiperaController(DodajSkiperaForma dsf) {
        this.dsf = dsf;

        dsf.pack();
        dsf.setLocationRelativeTo(null);
        dsf.setResizable(false);
        dsf.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        dsf.setIconImage(icon);

        addActionListener();

    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dsf.setVisible(true);
    }

    private void addActionListener() {
        dsf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String ime = dsf.getjTextFieldIme().getText().trim();
                String brTerminaText = dsf.getjTextFieldBrTermina().getText().trim();
                String sertifikat = dsf.getjTextFieldSertifikat().getText().trim();

                if (!ime.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne moze da zapamti skipera.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ime.isEmpty() || sertifikat.isEmpty() || brTerminaText.isEmpty()) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne moze da zapamti skipera.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int brTermina;
                try {
                    brTermina = Integer.parseInt(brTerminaText);
                    if (brTermina <= 0) {
                        JOptionPane.showMessageDialog(dsf, "Sistem ne moze da zapamti skipera.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne moze da zapamti skipera.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Skiper s = new Skiper(-1, ime, brTermina, sertifikat);

                try {
                    komunikacija.Komunikacija.getInstanca().dodajSkipera(s);
                    kordinator.Kordinator.getInstance().osveziPrikazSkiperaForma();
                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio skipera.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    dsf.dispose();
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio skipera.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    dsf.dispose();
                }

            }
        });

        dsf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                try {
                    int id = Integer.parseInt(dsf.getjTextFieldID().getText().trim());
                    String ime = dsf.getjTextFieldIme().getText().trim();
                    String sertifikat = dsf.getjTextFieldSertifikat().getText().trim();

                    int brTermina;
                    try {
                        brTermina = Integer.parseInt(dsf.getjTextFieldBrTermina().getText().trim());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dsf, "Sistem ne moze da izmeni skipera!", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (ime.isEmpty() || sertifikat.isEmpty() || brTermina <= 0) {
                        JOptionPane.showMessageDialog(dsf, "Sistem ne moze da izmeni skipera!", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Skiper s = new Skiper(id, ime, brTermina, sertifikat);

                    komunikacija.Komunikacija.getInstanca().azurirajSkipera(s);
                    JOptionPane.showMessageDialog(dsf, "Sistem je izmenio skipera!", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    kordinator.Kordinator.getInstance().osveziPrikazSkiperaForma();
                    dsf.dispose();

                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne moze da izmeni skipera!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        dsf.OtkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otkazi(e);
            }

            private void otkazi(ActionEvent e) {
                dsf.dispose();
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dsf.getjButtonAzuriraj().setVisible(false);
                dsf.getjButtonDodaj().setVisible(true);
                dsf.getjButtonDodaj().setEnabled(true);
                dsf.getjTextFieldID().setEnabled(false);
                break;
            case IZMENI:
                dsf.getjButtonDodaj().setVisible(false);
                dsf.getjButtonAzuriraj().setVisible(true);
                dsf.getjButtonAzuriraj().setEnabled(true);

                Skiper s = (Skiper) Kordinator.getInstance().vratiParam("skiper");
                dsf.getjTextFieldIme().setText(s.getIme());
                dsf.getjTextFieldID().setText(s.getIdSkiper() + "");
                dsf.getjTextFieldBrTermina().setText(s.getBrojTerminaUkupno() + "");
                dsf.getjTextFieldSertifikat().setText(s.getSertifikat());

                break;
            default:
                throw new AssertionError();
        }
    }

}
