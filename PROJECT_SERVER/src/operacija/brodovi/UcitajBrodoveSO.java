/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.brodovi;

import domen.Brod;
import domen.Iznajmljivanje;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class UcitajBrodoveSO extends ApstraktnaGenerickaOperacija{
    List<Brod> brodovi;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
       //nema preduslova za ucitavanje
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        brodovi = broker.getAll(new Brod(),"");
    }

    public List<Brod> getBrodovi() {
        return brodovi;
    }
    
}
