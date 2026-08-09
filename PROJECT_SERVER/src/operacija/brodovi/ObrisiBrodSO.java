/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.brodovi;

import domen.Brod;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class ObrisiBrodSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Brod)){
            throw new Exception("Sistem nije mogao da obrise administratora!-IZmeni ovo opet iz onoga sa rada");
        }
        Brod b = (Brod) objekat;
        if(b.getIdBrod() <= 0){
            throw new Exception("Sistem ne može da obriše brod: neispravan ID.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Brod)param);
    }
    
}
