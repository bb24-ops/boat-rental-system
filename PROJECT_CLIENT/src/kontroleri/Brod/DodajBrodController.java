/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.Brod;

import domen.Administrator;
import domen.Brod;
import domen.KategorijaBroda;
import domen.TipBroda;
import forme.Brod.DodajBrodForma;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.administrator.DodajAdministratoraForma;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import kordinator.Kordinator;

/**
 *
 * @author boris
 */
public class DodajBrodController {

    private final DodajBrodForma dbf;

    public DodajBrodController(DodajBrodForma dbf) {
        this.dbf = dbf;

        dbf.pack();
        dbf.setLocationRelativeTo(null);
        dbf.setResizable(false);
        dbf.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        dbf.setIconImage(icon);

        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        //pripremiFormu(mod);  ????!
        dbf.setVisible(true);
        popuniComboBoxeve();
        pripremiFormu(mod);
    }

    private void addActionListener() {
        dbf.dodajBrodAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    String naziv = dbf.getjTextFieldNaziv().getText().trim();
                    String selektovanTipString = (String) dbf.getjComboBoxTip().getSelectedItem();
                    String katString = (String) dbf.getjComboBoxKategorija().getSelectedItem();
                    String cenaString = dbf.getjTextFieldCena().getText().trim();

                    // VALIDACIJA
                    if (naziv == null || naziv.isEmpty() || !naziv.matches("[a-zA-Z]+")
                            || selektovanTipString == null || selektovanTipString.isEmpty()
                            || katString == null || katString.isEmpty()
                            || cenaString == null || cenaString.isEmpty()) {
                        JOptionPane.showMessageDialog(dbf, "Sistem ne moze da kreira brod", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double cenaPoDanu;
                    try {
                        cenaPoDanu = Double.parseDouble(cenaString);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dbf, "Sistem ne moze da kreira brod", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (cenaPoDanu <= 0) {
                        JOptionPane.showMessageDialog(dbf, "Sistem ne moze da kreira brod", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    TipBroda tip = TipBroda.valueOf(selektovanTipString);
                    KategorijaBroda kat = KategorijaBroda.valueOf(katString);

                    Brod b = new Brod();
                    b.setIdBrod(-1);
                    b.setNaziv(naziv);
                    b.setTip(tip);
                    b.setKategorija(kat);
                    b.setCenaPoDanu(cenaPoDanu);

                    komunikacija.Komunikacija.getInstanca().dodajBrod(b);
                    JOptionPane.showMessageDialog(dbf, "Sistem je kreirao brod", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().osveziBrodFormu();
                    dbf.dispose();
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(dbf, "Sistem ne moze da kreira brod", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    exp.printStackTrace();
                }
            }
        });

        dbf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                try {
                    int id = Integer.parseInt(dbf.getjTextFieldIDBrod().getText());
                    String naziv = dbf.getjTextFieldNaziv().getText().trim();
                    String selektovanTipString = (String) dbf.getjComboBoxTip().getSelectedItem();
                    String katString = (String) dbf.getjComboBoxKategorija().getSelectedItem();
                    String cenaString = dbf.getjTextFieldCena().getText().trim();

                    // VALIDACIJA
                    if (naziv == null || naziv.isEmpty() || !naziv.matches("[a-zA-Z]+")
                            || selektovanTipString == null || selektovanTipString.isEmpty()
                            || katString == null || katString.isEmpty()
                            || cenaString == null || cenaString.isEmpty()) {
                        JOptionPane.showMessageDialog(dbf, "Sistem ne moze da zapamti iznajmljivanje", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double cenaPoDanu;
                    try {
                        cenaPoDanu = Double.parseDouble(cenaString);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dbf, "Sistem ne moze da zapamti iznajmljivanje", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (cenaPoDanu <= 0) {
                        JOptionPane.showMessageDialog(dbf, "Sistem ne moze da zapamti iznajmljivanje", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    TipBroda tip = TipBroda.valueOf(selektovanTipString);
                    KategorijaBroda kat = KategorijaBroda.valueOf(katString);

                    Brod b = new Brod();
                    b.setIdBrod(id);
                    b.setNaziv(naziv);
                    b.setTip(tip);
                    b.setKategorija(kat);
                    b.setCenaPoDanu(cenaPoDanu);

                    komunikacija.Komunikacija.getInstanca().promeniBrod(b);
                    JOptionPane.showMessageDialog(dbf, "Sistem je zapamtio izmene broda", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().osveziBrodFormu();
                    dbf.dispose();
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(dbf, "Sistem ne moze da zapamti iznajmljivanje", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    exp.printStackTrace();
                }
            }
        });
        
        dbf.OtkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otkazi(e);
            }

            private void otkazi(ActionEvent e) {
                dbf.dispose();
            }
        });

    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dbf.getjButtonIzmeni().setVisible(false);
                dbf.getjButtonDodaj().setVisible(true);
                dbf.getjButtonDodaj().setEnabled(true);
                dbf.getjTextFieldIDBrod().setEnabled(false);
                break;
            case IZMENI:
                dbf.getjButtonDodaj().setVisible(false);
                dbf.getjButtonIzmeni().setVisible(true);
                dbf.getjButtonIzmeni().setEnabled(true);
                dbf.getjTextFieldIDBrod().setEnabled(false);

                Brod b = (Brod) Kordinator.getInstance().vratiParam("brod");
                dbf.getjTextFieldIDBrod().setText(b.getIdBrod() + "");
                dbf.getjTextFieldNaziv().setText(b.getNaziv());
                //ovde moras da uradis .toString() kako bi pokupio string po kom ce da prepozna, ne moze kao enum!
                dbf.getjComboBoxTip().setSelectedItem(b.getTip().toString());
                dbf.getjComboBoxKategorija().setSelectedItem(b.getKategorija().toString());
                dbf.getjTextFieldCena().setText(b.getCenaPoDanu() + "");

                break;
            default:
                throw new AssertionError();
        }
    }

    private void popuniComboBoxeve() {
        List<String> tipoviString = komunikacija.Komunikacija.getInstanca().ucitajTipoveBrodova();
        dbf.getjComboBoxTip().setModel(new DefaultComboBoxModel<>(tipoviString.toArray(new String[0])));

        List<String> kategorijeString = komunikacija.Komunikacija.getInstanca().ucitajKategorijeBrodova();
        dbf.getjComboBoxKategorija().setModel(new DefaultComboBoxModel<>(kategorijeString.toArray(new String[0])));
    }

}
