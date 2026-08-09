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
public class PromeniIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Iznajmljivanje)) {
            throw new Exception("Sistem ne moze da doda iznajmljivanje: neispravan objekat.");
        }
        Iznajmljivanje izn = (Iznajmljivanje) objekat;

        if (izn.getDatum() == null) {
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
        Iznajmljivanje i = (Iznajmljivanje) param;
        broker.edit(i);
        String uslov = " JOIN  brod on brod.idBrod = stavkaizn.brod WHERE iznajmljivanje=" + i.getIdIznajmljivanje() + " ";
        List<StavkaIzn> stareStavke = broker.getAll(new StavkaIzn(), uslov);
        List<StavkaIzn> noveStavke = i.getStavke();
        
        //Ako stavka vise ne postoji - brisem je
        for (StavkaIzn stara : stareStavke) {
            boolean postoji = false;
            for (StavkaIzn nova : noveStavke) {
                if (stara.getRbStavke() == nova.getRbStavke()) {
                    postoji = true;
                    break;
                }
            }
            if (!postoji) {
                broker.delete(stara);
            }
        }
        
        //Ako stavka nedostaje - dodam je
        for (StavkaIzn nova : noveStavke) {
            nova.setIdiznajmljivanje(i.getIdIznajmljivanje());
            boolean postoji = false;
            for (StavkaIzn stara : stareStavke) {
                if (stara.getRbStavke() == nova.getRbStavke()) {
                    postoji = true;
                    break;
                }
            }
            if (!postoji) {
                broker.add(nova);
            }
        }

    }

}
