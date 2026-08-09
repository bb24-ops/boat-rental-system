/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.administratori;

import domen.Administrator;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class ObrisiAdministratoraSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Administrator)) {
            throw new Exception("Sistem ne može da obriše administratora: neispravan objekat.");
        }
        Administrator a = (Administrator) objekat;
        if (a.getIdAdministrator() <= 0) {
            throw new Exception("Sistem ne može da obriše administratora: neispravan ID.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Administrator) param);
    }

}
