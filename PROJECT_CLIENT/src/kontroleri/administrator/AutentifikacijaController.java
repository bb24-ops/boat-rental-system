/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.administrator;

import forme.LoginForma;
import forme.administrator.Autentifikacija;
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
public class AutentifikacijaController {

    private final Autentifikacija au;
    private int brojPokusaja = 0;

    public AutentifikacijaController(Autentifikacija au) {
        this.au = au;

        au.pack();
        au.setLocationRelativeTo(null);
        au.setResizable(false);
        au.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        au.setIconImage(icon);

        addActionListeners();
    }

    private void addActionListeners() {
        au.autentifikacijaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String password = String.valueOf(au.getjPasswordField1().getPassword());

                if (password.equals("admin")) {
                    JOptionPane.showMessageDialog(au, "Lozinka ispravna.","Obajestenje",JOptionPane.INFORMATION_MESSAGE);
                    kordinator.Kordinator.getInstance().otvoriDetaljiAdminaFormu();
                    au.dispose();
                } else {
                    brojPokusaja++;
                    int preostalo = 3 - brojPokusaja;

                    if (preostalo > 0) {
                        JOptionPane.showMessageDialog(au,"Lozinka neispravna. Preostalo pokušaja: " + preostalo,"Upozorenje",JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(au,"Iskoristili ste sva 3 pokušaja!");
                        Kordinator.getInstance().zabraniPristupAdminDelu();
                        Kordinator.getInstance().zatvoriPrikazAdminaFormu();
                        au.dispose();
                    }
                }
            }
        });
    }

public void otvoriFormu() {
        au.setVisible(true);
    }
}
