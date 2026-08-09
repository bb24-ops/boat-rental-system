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
public class PromeniBrodSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Brod)) {
            throw new Exception("Sistem ne moze da kreira brod: neispravan objekat.");
        }

        Brod brod = (Brod) objekat;

        if (brod.getNaziv() == null || brod.getNaziv().trim().isEmpty()) {
            throw new Exception("Sistem ne moze da kreira brod: naziv ne sme biti prazan.");
        }

        if (brod.getTip() == null) {
            throw new Exception("Sistem ne moze da kreira brod: tip ne sme biti prazan.");
        }

        if (brod.getKategorija() == null) {
            throw new Exception("Sistem ne moze da kreira brod: kategorija ne sme biti prazna.");
        }

        if (brod.getCenaPoDanu() <= 0) {
            throw new Exception("Sistem ne moze da kreira brod: cena po danu mora biti veca od 0.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Brod b = (Brod) param;
        broker.edit(b);
    }

}
