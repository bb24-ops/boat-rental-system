/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.luka;

import domen.Luka;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class ObrisiLukuSO extends ApstraktnaGenerickaOperacija{
    @Override
    protected void preduslovi(Object objekat) throws Exception {
         if(objekat == null || !(objekat instanceof Luka)){
            throw new Exception("Sistem ne može da obriše luku: neispravan objekat.");
        }
        Luka l = (Luka) objekat;
        if (l.getIdLuka()<= 0) {
            throw new Exception("Sistem ne može da obriše luku: neispravan ID.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
         broker.delete((Luka)param);
    }
}
