/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri.administrator;

import domen.Administrator;
import domen.Korisnik;
import domen.Luka;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.Korisnik.DodajKorniskaForma;
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
public class DodajAdministratoraController {

    private final DodajAdministratoraForma daf;

    public DodajAdministratoraController(DodajAdministratoraForma daf) {
        this.daf = daf;

        daf.pack();
        daf.setLocationRelativeTo(null);
        daf.setResizable(false);
        daf.setVisible(true);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/slike/sidro.png"));
        daf.setIconImage(icon);

        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        daf.setVisible(true);
        pripremiFormu(mod);
    }

    private void addActionListener() {
        daf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String ime = daf.getjTextFieldIme().getText().trim();
                String prezime = daf.getjTextFieldPrezime().getText().trim();
                String brojTelefona = daf.getjTextFieldBrTelefona().getText().trim();
                String username = daf.getjTextFieldUsername().getText().trim();
                String password = daf.getjTextFieldPassword().getText().trim();

                // --- VALIDACIJA ---
                if (ime.isEmpty() || prezime.isEmpty() || brojTelefona.isEmpty()
                        || username.isEmpty() || password.isEmpty() 
                        || !password.matches(".*\\d.*")) { // provera da li lozinka sadrzi bar jedan broj
                    JOptionPane.showMessageDialog(daf, "Sistem ne moze da izmeni admina", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                try {
                    Administrator a = new Administrator();
                    a.setIdAdministrator(-1);
                    a.setIme(ime);
                    a.setPrezime(prezime);
                    a.setBrojTelefona(brojTelefona);
                    a.setUsername(username);
                    a.setPassword(password);

                    komunikacija.Komunikacija.getInstanca().dodajAdmina(a);
                    JOptionPane.showMessageDialog(daf, "Sistem je kreirao admina", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().osveziAdminFormu();
                    daf.dispose();
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(daf, "Sistem ne moze da kreira admina", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        daf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                try {
                    int id = Integer.parseInt(daf.getjTextFieldIDAdmina().getText());
                    String ime = daf.getjTextFieldIme().getText().trim();
                    String prezime = daf.getjTextFieldPrezime().getText().trim();
                    String brojTelefona = daf.getjTextFieldBrTelefona().getText().trim();
                    String username = daf.getjTextFieldUsername().getText().trim();
                    String password = daf.getjTextFieldPassword().getText().trim();

                    // --- VALIDACIJA ---
                    if (ime.isEmpty() || prezime.isEmpty() || brojTelefona.isEmpty()
                            || username.isEmpty() || password.isEmpty() 
                            || !password.matches(".*\\d.*")) { // provera da li lozinka sadrzi bar jedan broj
                        JOptionPane.showMessageDialog(daf, "Sistem ne moze da izmeni admina", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Administrator a = new Administrator();
                    a.setIdAdministrator(id);
                    a.setIme(ime);
                    a.setPrezime(prezime);
                    a.setBrojTelefona(brojTelefona);
                    a.setUsername(username);
                    a.setPassword(password);

                    komunikacija.Komunikacija.getInstanca().promeniAdmina(a);
                    JOptionPane.showMessageDialog(daf, "Sistem je izmenio admina", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().osveziAdminFormu();
                    daf.dispose();
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(daf, "Sistem ne moze da izmeni admina", "Upozorenje", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        daf.OtkaziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                otkazi(e);
            }

            private void otkazi(ActionEvent e) {
                daf.dispose();
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                daf.getjButtonIzmeni().setVisible(false);
                daf.getjButtonDodaj().setVisible(true);
                daf.getjButtonDodaj().setEnabled(true);
                daf.getjTextFieldIDAdmina().setEnabled(false);
                break;
            case IZMENI:
                daf.getjButtonDodaj().setVisible(false);
                daf.getjButtonIzmeni().setVisible(true);
                daf.getjButtonIzmeni().setEnabled(true);
                daf.getjTextFieldIDAdmina().setEnabled(false);

                Administrator a = (Administrator) Kordinator.getInstance().vratiParam("administrator");
                daf.getjTextFieldIDAdmina().setText(a.getIdAdministrator() + "");
                daf.getjTextFieldIme().setText(a.getIme());
                daf.getjTextFieldPrezime().setText(a.getPrezime());
                daf.getjTextFieldBrTelefona().setText(a.getBrojTelefona());
                daf.getjTextFieldUsername().setText(a.getUsername());
                daf.getjTextFieldPassword().setText("");
                break;
            case DETALJI:
                daf.getjButtonDodaj().setVisible(false);
                daf.getjButtonOtkazite().setVisible(false);
                daf.getjButtonIzmeni().setVisible(false);
                daf.getjButtonIzmeni().setEnabled(false);
                daf.getjTextFieldIDAdmina().setEnabled(false);
                daf.getjTextFieldIme().setEnabled(false);
                daf.getjTextFieldPrezime().setEnabled(false);
                daf.getjTextFieldUsername().setEnabled(false);
                daf.getjTextFieldPassword().setEnabled(false);
                daf.getjTextFieldBrTelefona().setEnabled(false);
                

                Administrator aa = (Administrator) Kordinator.getInstance().vratiParam("administrator");
                daf.getjTextFieldIDAdmina().setText(aa.getIdAdministrator() + "");
                daf.getjTextFieldIme().setText(aa.getIme());
                daf.getjTextFieldPrezime().setText(aa.getPrezime());
                daf.getjTextFieldBrTelefona().setText(aa.getBrojTelefona());
                daf.getjTextFieldUsername().setText(aa.getUsername());
                daf.getjTextFieldPassword().setText(aa.getPassword());
                break;
            default:
                throw new AssertionError();
        }
    }

}
