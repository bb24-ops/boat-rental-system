/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.Brod;

import domen.Brod;
import forme.Brod.PrikazBrodovaForma;
import forme.model.ModelTabeleBrodovi;
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
public class PrikazBrodaController {

    private final PrikazBrodovaForma pbf;

    public PrikazBrodaController(PrikazBrodovaForma pbf) {
        this.pbf = pbf;

        pbf.pack();
        pbf.setLocationRelativeTo(null);
        pbf.setResizable(false);
        pbf.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        pbf.setIconImage(icon);
        //ovde kreces sa dugmetom za brisanje!
        addActionListener();

    }

    public void otvoriFormu() {
        pripremiFormu();
        pbf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Brod> brodovi = Komunikacija.getInstanca().ucitajBrodove();
        ModelTabeleBrodovi mtb = new ModelTabeleBrodovi(brodovi);
        pbf.getjTableBrodovi().setModel(mtb);
    }

    private void addActionListener() {
        pbf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = pbf.getjTextFieldNaziv().getText().trim();
                String cenaText = pbf.getjTextFieldCena().getText().trim();
                double minCena = 0;
                double maxCena = 0;

                // --- VALIDACIJA ---
                if ((naziv.isEmpty() && cenaText.isEmpty()) || (!naziv.isEmpty() && !naziv.matches("[a-zA-Z]+"))) {
                    JOptionPane.showMessageDialog(pbf, "Sistem ne moze da nadje brod po zadatom parametru", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    if (!cenaText.isEmpty()) {
                        if (cenaText.contains("-")) {
                            String[] delovi = cenaText.split("-");
                            if (delovi.length == 2) {
                                String minStr = delovi[0].trim();
                                String maxStr = delovi[1].trim();

                                if (!minStr.isEmpty()) {
                                    minCena = Double.parseDouble(minStr);
                                }
                                if (!maxStr.isEmpty()) {
                                    maxCena = Double.parseDouble(maxStr);
                                }
                            } else {
                                throw new NumberFormatException();
                            }
                        } else {
                            minCena = maxCena = Double.parseDouble(cenaText);
                        }

                        if (minCena < 0 || maxCena < 0) {
                            JOptionPane.showMessageDialog(pbf, "Sistem ne moze da nadje brod po zadatom parametru", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(pbf, "Sistem ne moze da nadje brod po zadatom parametru", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // --- Pretraga ---
                ModelTabeleBrodovi mtb = (ModelTabeleBrodovi) pbf.getjTableBrodovi().getModel();
                mtb.pretrazi(naziv, minCena, maxCena);

                if (mtb.getRowCount() > 0) {
                    JOptionPane.showMessageDialog(pbf, "Sistem je nasao brod(ove) po zadatom parametru", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pbf, "Sistem ne moze da nadje brod po zadatom parametru", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pbf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pbf.getjButtonPretrazi().setEnabled(true);
                pbf.getjTextFieldCena().setText("");
                pbf.getjTextFieldNaziv().setText("");
                pripremiFormu();
            }
        });

        pbf.addBtnKreirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kordinator.Kordinator.getInstance().otvoriKreirajBrodFormu();
            }
        });

        pbf.addBtnPromeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pbf.getjTableBrodovi().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pbf, "Sistem ne moze da azurira brod.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleBrodovi mtb = (ModelTabeleBrodovi) pbf.getjTableBrodovi().getModel();
                    Brod b = mtb.getLista().get(red);
                    kordinator.Kordinator.getInstance().dodajParam("brod", b);
                    kordinator.Kordinator.getInstance().otvoriIzmeniBrodFormu();
                }
            }
        });

        pbf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pbf.getjTableBrodovi().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pbf, "Sistem ne moze da obrise brod.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleBrodovi mtb = (ModelTabeleBrodovi) pbf.getjTableBrodovi().getModel();
                    Brod b = mtb.getLista().get(red);
                    try {
                        Komunikacija.getInstanca().obrisiBrod(b);
                        JOptionPane.showMessageDialog(pbf, "Sistem je uspesno obrisao brod", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();//ova metoda opet ucitava listu iz baze gde je ovaj obrisan!!
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(pbf, "Sistem ne moze da obrise brod.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

    }

    public void osveziFormu() {
        pripremiFormu();
    }
}
