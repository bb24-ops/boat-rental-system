/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import repository.Repozitorijum;
import repository.db.DbRepository;
import repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author boris
 */
public abstract class ApstraktnaGenerickaOperacija {

    protected final Repozitorijum broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }

    public final void izvrsi(Object objekat, String kljuc) throws Exception {
        try {
            preduslovi(objekat);//provera preduslova
            zapocniTransakciju();//otvara se konekcija
            izvrsiOperaciju(objekat, kljuc);//sql upit
            potvrdiTransakciju();//potvrda ako je sve uredu
        }catch(Exception e){
            ponistiTransakciju();//ponistavanje ako nesto nije okej!
            throw e;
        }
    }

    protected abstract void preduslovi(Object objekat) throws Exception;

    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DbRepository)broker).connect();
    }

    private void potvrdiTransakciju() throws Exception {
        ((DbRepository)broker).commit();
    }

    private void ponistiTransakciju()throws Exception {
        ((DbRepository)broker).rollback();
    }

}
