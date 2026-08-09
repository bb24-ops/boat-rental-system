/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Administrator;
import forme.LoginForma;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author boris
 */
public class LoginController {

    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;

        lf.pack();
        lf.setLocationRelativeTo(null);
        lf.setResizable(false);
        lf.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        lf.setIconImage(icon);

        addActionListeners();
    }

    LoginController() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String username = lf.getjTextFieldUsername().getText().trim();
                String password = String.valueOf(lf.getjPasswordField1().getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i lozinka ne smeju biti prazni!", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Provera da lozinka sadrži bar jedno slovo i bar jedan broj
                if (!password.matches(".*[a-zA-Z].*") || !password.matches(".*[0-9].*")) {
                    JOptionPane.showMessageDialog(lf, "Lozinka mora da sadrži bar jedno slovo i bar jedan broj!", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Administrator a = new Administrator
                komunikacija.Komunikacija.getInstanca().konekcija();
                Administrator ulogovani = Komunikacija.getInstanca().login(username, password);

                if (ulogovani == null) {
                    JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra nisu ispravni!", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(lf, "Ne moze da se otvori glavna forma i meni.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                } else {
                    //otvranje glavne forme
                    kordinator.Kordinator.getInstance().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Korisnik " + ulogovani.getIme() + " " + ulogovani.getPrezime() + " je uspesno ulogovan!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    kordinator.Kordinator.getInstance().otvoriGlavnuFormu();
                    lf.dispose();
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }

}
