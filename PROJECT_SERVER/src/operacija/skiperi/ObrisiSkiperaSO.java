/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.skiperi;

import domen.Skiper;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class ObrisiSkiperaSO extends ApstraktnaGenerickaOperacija {

   
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //nije ok ako nismo dobili nist i ako smo dobili nesto sto nije Skiper!
        if(objekat == null || !(objekat instanceof Skiper)){
            throw new Exception("Sistem ne može da obriše skipera: neispravan objekat.");
        }
        Skiper s = (Skiper) objekat;
        if (s.getIdSkiper()<= 0) {
            throw new Exception("Sistem ne može da obriše skipera: neispravan ID.");
        }
    }

    
    //ovde samo ovo nemamo nikakva polja i gettere jer ne treba nista da vracamo nazad! Samo brisemo i tjt!
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Skiper)param);
    }
    
}
