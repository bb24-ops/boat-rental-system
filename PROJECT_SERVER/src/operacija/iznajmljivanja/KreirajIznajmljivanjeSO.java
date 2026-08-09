/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanja;

import domen.Iznajmljivanje;
import domen.StavkaIzn;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author boris
 */
public class KreirajIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Iznajmljivanje)) {
            throw new Exception("Sistem ne moze da doda iznajmljivanje: neispravan objekat.");
        }

        Iznajmljivanje izn = (Iznajmljivanje) objekat;

        if (izn.getDatum()== null) {
            throw new Exception("Sistem ne moze da doda iznajmljivanje: datum ne sme biti null.");
        }

        if (izn.getUkupanIznos() <= 0) {
            throw new Exception("Sistem ne moze da doda iznajmljivanje: ukupan iznos mora biti veci od 0.");
        }

        if (izn.getAdministrator() == null || izn.getAdministrator().getIdAdministrator() <= 0) {
            throw new Exception("Sistem ne moze da doda iznajmljivanje: administrator nije validan.");
        }

        if (izn.getKorisnik() == null || izn.getKorisnik().getIdKorisnik() <= 0) {
            throw new Exception("Sistem ne moze da doda iznajmljivanje: korisnik nije validan.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        //prvo se dodaje celo Iznajmljivanje i dobijamo njegov id 
        Iznajmljivanje i = (Iznajmljivanje) param;
        int idIzajmljivanje = broker.addReturnKey(i);

        //sada tek idu stavke - ubacujemo stavkama idIznajmljivanja
        List<StavkaIzn> stavke = i.getStavke();
        for (StavkaIzn s : stavke) {
            s.setIdiznajmljivanje(idIzajmljivanje);
            broker.add(s);
        }
    }

}
