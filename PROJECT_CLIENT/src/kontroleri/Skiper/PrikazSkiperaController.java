/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.Skiper;

import com.sun.java.accessibility.util.AWTEventMonitor;
import domen.Skiper;
import forme.Skiper.PrikazSkiperaForma;
import forme.model.ModelTabeleBrodovi;
import forme.model.ModelTabeleSkiper;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author boris
 */
public class PrikazSkiperaController {

    private final PrikazSkiperaForma psf;

    public PrikazSkiperaController(PrikazSkiperaForma psf) {
        this.psf = psf;

        psf.pack();
        psf.setLocationRelativeTo(null);
        psf.setResizable(false);
        psf.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        psf.setIconImage(icon);

        //ovde kreces sa dugmetom za brisanje!
        addActionListener();

    }

    public void otvoriFormu() {
        pripremiFormu();
        psf.setVisible(true);
        //klijentski zahtev da ucitamo sve moguce skipere i da kreiramo model tabele!
    }

    public void pripremiFormu() {
        List<Skiper> skiperi = Komunikacija.getInstanca().ucitajSkipere();
        ModelTabeleSkiper mts = new ModelTabeleSkiper(skiperi);
        psf.getjTableSkiperi().setModel(mts);
    }

    private void addActionListener() {
        psf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = psf.getjTableSkiperi().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(psf, "Sistem ne moze da obrise izabranog skipera.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleSkiper mts = (ModelTabeleSkiper) psf.getjTableSkiperi().getModel();
                    Skiper s = mts.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiSkipera(s);
                        JOptionPane.showMessageDialog(psf, "Sistem je uspesno obrisao izabranog skipera.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();//ova metoda opet ucitava listu iz baze gde je ovaj obrisan!!
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(psf, "Sistem ne moze da obrise izabranog skipera", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        psf.addBtnKreirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kordinator.Kordinator.getInstance().otvoriDodajSkiperaFormu();
            }
        });

        psf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = psf.getjTableSkiperi().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(psf, "Sistem ne moze da azurira skipera.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleSkiper mts = (ModelTabeleSkiper) psf.getjTableSkiperi().getModel();
                    Skiper s = mts.getLista().get(red);
                    kordinator.Kordinator.getInstance().dodajParam("skiper", s);
                    kordinator.Kordinator.getInstance().otvoriIzmeniSkiperaFormu();
                }
            }
        });

        psf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = psf.getjTextFieldIme().getText().trim();
                String brTerminaText = psf.getjTextFieldBrTermina().getText().trim();
                int minTermin = 0;
                int maxTermin = 0;

                // --- VALIDACIJA IMENA ---
                if (!ime.isEmpty() && !ime.matches("[a-zA-Z]+")) { // samo slova
                    JOptionPane.showMessageDialog(psf, "Sistem ne moze da nadje skipera po zadatim kriterijumima.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // --- VALIDACIJA BROJA TERMINA ---
                try {
                    if (!brTerminaText.isEmpty()) {
                        if (brTerminaText.contains("-")) {
                            String[] delovi = brTerminaText.split("-");
                            if (delovi.length != 2) {
                                throw new NumberFormatException();
                            }

                            String minStr = delovi[0].trim();
                            String maxStr = delovi[1].trim();

                            if (!minStr.isEmpty()) {
                                minTermin = Integer.parseInt(minStr);
                            }
                            if (!maxStr.isEmpty()) {
                                maxTermin = Integer.parseInt(maxStr);
                            }

                            if (minTermin > maxTermin) {
                                JOptionPane.showMessageDialog(psf, "Sistem ne moze da nadje skipera po zadatim kriterijumima,", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        } else {
                            minTermin = maxTermin = Integer.parseInt(brTerminaText);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(psf, "Sistem ne moze da nadje skipera po zadatim kriterijumima.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // --- PRETRAGA ---
                ModelTabeleSkiper mts = (ModelTabeleSkiper) psf.getjTableSkiperi().getModel();
                mts.pretrazi(ime, minTermin, maxTermin);

                // --- OBAVEŠTENJE ---
                if (mts.getLista().isEmpty()) {
                    JOptionPane.showMessageDialog(psf, "Sistem ne moze da nadje skipera po zadatim kriterijumima", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(psf, "Sistem je nasao skipera po zadatim kriterijumima", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        psf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //psf.getjButtonPretrazi().setEnabled(true);
                psf.getjTextFieldIme().setText("");
                psf.getjTextFieldBrTermina().setText("");
                pripremiFormu();
            }
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }

}
