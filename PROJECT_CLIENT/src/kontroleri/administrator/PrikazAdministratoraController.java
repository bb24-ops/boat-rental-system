/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.administrator;

import domen.Administrator;
import domen.Korisnik;
import forme.Korisnik.PrikazKorisnikaForma;
import forme.administrator.PrikazAdministratorForma;
import forme.model.ModelTabeleAdmini;
import forme.model.ModelTabeleKorisnici;
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
public class PrikazAdministratoraController {

    private final PrikazAdministratorForma paf;

    public PrikazAdministratoraController(PrikazAdministratorForma paf) {
        this.paf = paf;

        paf.pack();
        paf.setLocationRelativeTo(null);
        paf.setResizable(false);
        paf.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        paf.setIconImage(icon);

        //ovde kreces sa dugmetom za brisanje!
        addActionListener();

    }

    public void otvoriFormu() {
        pripremiFormu();
        paf.setVisible(true);
        //klijentski zahtev da ucitamo sve moguce skipere i da kreiramo model tabele!
    }

    public void pripremiFormu() {
        List<Administrator> admini = Komunikacija.getInstanca().ucitajAdmine();
        ModelTabeleAdmini mta = new ModelTabeleAdmini(admini);
        paf.getjTableAdmini().setModel(mta);
    }

    private void addActionListener() {
        paf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = paf.getjTextFieldIme().getText().trim();
                String prezime = paf.getjTextFieldPrezime().getText().trim();

                // Validacija: mora biti samo slova ili prazno
                if ((!ime.isEmpty() && !ime.matches("[a-zA-Z]+")) || (!prezime.isEmpty() && !prezime.matches("[a-zA-Z]+"))) {
                    JOptionPane.showMessageDialog(paf, "Sistem ne moze da nadje admina po zadatim kriterijumima", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ModelTabeleAdmini mta = (ModelTabeleAdmini) paf.getjTableAdmini().getModel();
                mta.pretrazi(ime, prezime);

                // Provera da li tabela ima rezultate
                if (mta.getRowCount() > 0) {
                    JOptionPane.showMessageDialog(paf, "Sistem je nasao admina po zadatim kriterijumima", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(paf, "Sistem ne moze da nadje admina po zadatim kriterijumima", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        paf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paf.getjButtonPretrazi().setEnabled(true);
                paf.getjTextFieldIme().setText("");
                paf.getjTextFieldPrezime().setText("");
                pripremiFormu();
            }
        });

        paf.addBtnKreirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kordinator.Kordinator.getInstance().otvoriKreirajAdminaFormu();
            }
        });

        paf.addBtnPromeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = paf.getjTableAdmini().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(paf, "Sistem ne moze da azurira admina.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleAdmini mta = (ModelTabeleAdmini) paf.getjTableAdmini().getModel();
                    Administrator a = mta.getLista().get(red);
                    kordinator.Kordinator.getInstance().dodajParam("administrator", a);
                    //kordinator.Kordinator.getInstance().otvoriAutentifikacijaFormu();
                    kordinator.Kordinator.getInstance().otvoriIzmeniAdminaFormu();
                }
            }
        });

        paf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = paf.getjTableAdmini().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(paf, "Sistem ne moze da obrise admina.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleAdmini mta = (ModelTabeleAdmini) paf.getjTableAdmini().getModel();
                    Administrator a = mta.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiAdmina(a);
                        //kordinator.Kordinator.getInstance().otvoriAutentifikacijaFormu();
                        JOptionPane.showMessageDialog(paf, "Sistem je uspesno obrisao admina.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();//ova metoda opet ucitava listu iz baze gde je ovaj obrisan!!
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(paf, "Sistem ne moze da obrise admina.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
        
        paf.addBtnDetaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = paf.getjTableAdmini().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(paf, "Sistem ne moze da ucita detalje.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleAdmini mta = (ModelTabeleAdmini) paf.getjTableAdmini().getModel();
                    Administrator a = mta.getLista().get(red);
                    kordinator.Kordinator.getInstance().dodajParam("administrator", a);
                    kordinator.Kordinator.getInstance().otvoriAutentifikacijaFormu();
                }
            }
        });
    }

    public void zatvoriFormu() {
        paf.dispose();
    }
}
