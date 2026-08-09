/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.Luka;

import domen.Administrator;
import domen.Luka;
import forme.Luka.PrikazLukaForma;
import forme.administrator.PrikazAdministratorForma;
import forme.model.ModelTabeleAdmini;
import forme.model.ModelTabeleLuke;
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
public class PrikazLukaController {

    private final PrikazLukaForma plf;

    public PrikazLukaController(PrikazLukaForma plf) {
        this.plf = plf;

        plf.pack();
        plf.setLocationRelativeTo(null);
        plf.setResizable(false);
        plf.setVisible(true);
        //ovde kreces sa dugmetom za brisanje!
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        plf.setIconImage(icon);
        addActionListener();

    }

    public void otvoriFormu() {
        pripremiFormu();
        plf.setVisible(true);
        //klijentski zahtev da ucitamo sve moguce skipere i da kreiramo model tabele!
    }

    public void pripremiFormu() {
        List<Luka> luke = Komunikacija.getInstanca().ucitajLuke();
        ModelTabeleLuke mta = new ModelTabeleLuke(luke);
        plf.getjTableLuka().setModel(mta);
    }

    private void addActionListener() {
        plf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = plf.getjTextFieldNaziv().getText().trim();

                if (naziv.isEmpty() || !naziv.matches("[a-zA-Z]+")) {
                    JOptionPane.showMessageDialog(plf, "Sistem ne moze da nadje luku po zadatim kriterijumima", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleLuke mtl = (ModelTabeleLuke) plf.getjTableLuka().getModel();
                mtl.pretrazi(naziv);

                if (mtl.getRowCount() > 0) {
                    JOptionPane.showMessageDialog(plf, "Sistem je nasao luku po zadatim kriterijumima", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(plf, "Sistem ne moze da nadje luku po zadatim kriterijumima", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        plf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plf.getjTextFieldNaziv().setText("");
                pripremiFormu();
            }
        });

        plf.addBtnKreirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kordinator.Kordinator.getInstance().otvoriKreirajLukuFormu();
            }
        });

        plf.addBtnPromeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = plf.getjTableLuka().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(plf, "Sistem ne moze da azurira luku", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleLuke mtl = (ModelTabeleLuke) plf.getjTableLuka().getModel();
                    Luka l = mtl.getLista().get(red);
                    kordinator.Kordinator.getInstance().dodajParam("luka", l);
                    kordinator.Kordinator.getInstance().otvoriIzmeniLukuFormu();
                }
            }
        });

        plf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = plf.getjTableLuka().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(plf, "Sistem ne moze da obrise luku", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleLuke mtl = (ModelTabeleLuke) plf.getjTableLuka().getModel();
                    Luka l = mtl.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiLuku(l);
                        JOptionPane.showMessageDialog(plf, "Sistem je uspesno obrisao luku", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();//ova metoda opet ucitava listu iz baze gde je ovaj obrisan!!
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(plf, "Sistem ne moze da obrise luku", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
    }
}
