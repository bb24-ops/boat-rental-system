/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.skiperDez;

import domen.SkiperDez;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class KreirajSkiperDezSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        SkiperDez sd = (SkiperDez) objekat;

        // admin mora biti setovan
        if (sd.getAdministrator() == null || sd.getAdministrator().getIdAdministrator() <= 0) {
            throw new Exception("Sistem ne može da zapamti iznajmljivanje jer admin nije validan.");
        }

        // skiper mora biti setovan
        if (sd.getSkiper() == null || sd.getSkiper().getIdSkiper() <= 0) {
            throw new Exception("Sistem ne može da zapamti iznajmljivanje jer skiper nije validan.");
        }

        // datum dežurstva mora biti setovan
        if (sd.getDatumDezurstva() == null) {
            throw new Exception("Sistem ne može da zapamti iznajmljivanje jer datum dežurstva nije unet.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((SkiperDez) param);
    }

}
