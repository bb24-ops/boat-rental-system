/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import domen.Administrator;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class LoginSO extends ApstraktnaGenerickaOperacija {

    Administrator admin;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Administrator)) {
            throw new Exception("Logovanje neuspesno: neispravan objekat.");
        }
        
        Administrator a = (Administrator) param;
        
        if (a.getUsername() == null || a.getUsername().trim().isEmpty()) {
            throw new Exception("Logovanje neuspesno: username nije ispravan.");
        }
        
        
        if (a.getPassword() == null) {
            throw new Exception("Logovanje neuspesno: password mora da ima bar 6 karaktera, slovo i broj.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Administrator> sviAdmini = broker.getAll((Administrator) param, null);
        

        if (sviAdmini.contains((Administrator) param)) {
            for (Administrator a : sviAdmini) {
                if (a.equals((Administrator) param)) {
                    admin = a;
                    return;
                }
            }
        } else {
            admin = null;
        }
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

}
