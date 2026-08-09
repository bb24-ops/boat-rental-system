/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.luka;

import domen.Luka;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class UcitajLukeSO extends ApstraktnaGenerickaOperacija{
    List<Luka> luke;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //nema preduslovaaa - uctivanje!
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
       luke = broker.getAll(new Luka(),"");
       
    }

    public List<Luka> getLuke() {
        return luke;
    }
    
}
