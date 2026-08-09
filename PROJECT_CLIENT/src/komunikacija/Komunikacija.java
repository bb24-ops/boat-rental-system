/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Administrator;
import domen.Brod;
import domen.Iznajmljivanje;
import domen.Korisnik;
import domen.Luka;
import domen.Skiper;
import domen.SkiperDez;
import domen.StavkaIzn;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author boris
 */
public class Komunikacija {

    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instanca;

    public static Komunikacija getInstanca() {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }

    private Komunikacija() {

    }

    public void konekcija() {
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            System.out.println("SERVER NIJE POVEZAN!");
        }
    }

    public Administrator login(String username, String password) {
        Administrator a = new Administrator();
        a.setUsername(username);
        a.setPassword(password);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, a);
        posiljalac.posalji(zahtev);
        //ceka se odgovor sa Servera!

        Odgovor odg = (Odgovor) primalac.primi();
        a = (Administrator) odg.getOdgovor();
        return a;
    }

    public List<Skiper> ucitajSkipere() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SKIPERE, null);
        List<Skiper> skiperi = new ArrayList<>();
        posiljalac.posalji(zahtev);
        //ceka se odg

        Odgovor odg = (Odgovor) primalac.primi();
        skiperi = (List<Skiper>) odg.getOdgovor();

        return skiperi;
    }

    public void obrisiSkipera(Skiper s) throws Exception {
        //Kod brisanja moze da ti pravi problem neki exception jer su ti u bazi povezani podaci (imas spoljne kljuceve) i samim tim ne mozes da obrises te podatke, to je kao normalno jedino sto mozes je da stavis u catch nest i stavis tu JOPTionPane
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_SKIPERA, s);
        posiljalac.posalji(zahtev);
        //ceka seeee
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je obrisao skipera.");
        } else {
            System.out.println("Sistem nije obirsao skipera.");
            //((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public void dodajSkipera(Skiper s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_SKIPERA, s);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je uspesno dodao skipera.");
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public void azurirajSkipera(Skiper s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_SKIPERA, s);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je azuriao skipera.");
            kordinator.Kordinator.getInstance().osveziPrikazSkiperaForma();
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_IZNAJMJIVANJE, null);
        List<Iznajmljivanje> iznajmljivanja = new ArrayList<>();
        posiljalac.posalji(zahtev);
        //ceka se odg

        Odgovor odg = (Odgovor) primalac.primi();
        iznajmljivanja = (List<Iznajmljivanje>) odg.getOdgovor();
        return iznajmljivanja;
    }
    
    public void kreirajIznajmljivanje(Iznajmljivanje i) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_IZNAJMLJIVANJE, i);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je dodao iznajmljivanje.");
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public void izmeniIznajmljivanje(Iznajmljivanje i) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_IZNAJMLJIVANJE, i);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je azurirao iznajmljivanje(racun).");
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public void obrisiIznajmljivanje(Iznajmljivanje i) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_IZNAJMLJIVANJE, i);
        posiljalac.posalji(zahtev);
        //ceka seeee
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je obrisao iznajmljivanje.");
        } else {
            System.out.println("Sistem nije obirsao iznajmljivanje.");
            throw new Exception("GRESKA");
        }
    }

    public List<Korisnik> ucitajKorisnike() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KORISNIKE, null);
        List<Korisnik> korisnici = new ArrayList<>();
        posiljalac.posalji(zahtev);
        //ceka se odg

        Odgovor odg = (Odgovor) primalac.primi();
        korisnici = (List<Korisnik>) odg.getOdgovor();

        return korisnici;
    }

    public List<Brod> ucitajBrodove() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_BRODOVE, null);
        List<Brod> brodovi = new ArrayList<>();
        posiljalac.posalji(zahtev);
        //ceka se odg

        Odgovor odg = (Odgovor) primalac.primi();
        brodovi = (List<Brod>) odg.getOdgovor();

        return brodovi;
    }

    public List<StavkaIzn> ucitajIznajmljivanjeS(int idIznajmljivanje) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE, idIznajmljivanje);
        List<StavkaIzn> stavke = new ArrayList<>();
        posiljalac.posalji(zahtev);
        //ceka se odg

        Odgovor odg = (Odgovor) primalac.primi();
        stavke = (List<StavkaIzn>) odg.getOdgovor();
        return stavke;
    }

    public List<Luka> ucitajLuke() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_LUKE, null);
        List<Luka> luke = new ArrayList<>();
        posiljalac.posalji(zahtev);
        //ceka se odg

        Odgovor odg = (Odgovor) primalac.primi();
        luke = (List<Luka>) odg.getOdgovor();

        return luke;
    }

    public void dodajKorisnika(Korisnik k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KORISNIKA, k);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je uspesno dodao korisnika");
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public void promeniKorisnika(Korisnik k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_KORISNIKA, k);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je azurirao korisnika");
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public void obrisiKorisnika(Korisnik k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KORISNIKA, k);
        posiljalac.posalji(zahtev);
        //ceka seeee
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je uspesno obrisao korisnika");
        } else {
            System.out.println("Sistem nije obrisao korinsika");
            //((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public List<Administrator> ucitajAdmine() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_ADMINE, null);
        List<Administrator> admini = new ArrayList<>();
        posiljalac.posalji(zahtev);
        //ceka se odg

        Odgovor odg = (Odgovor) primalac.primi();
        admini = (List<Administrator>) odg.getOdgovor();

        return admini;
    }

    public void dodajAdmina(Administrator a) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_ADMINA, a);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je uspesno dodao admina!");
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public void promeniAdmina(Administrator a) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_ADMINA, a);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je azurirao admina!");
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public void obrisiAdmina(Administrator a) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_ADMINA, a);
        posiljalac.posalji(zahtev);
        //ceka seeee
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Uspesno obirsan admin!");
        } else {
            System.out.println("Greska, admin nije obrisan, povezan je sa jos podataka u bazi!");
            //((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    //BRODOVI - imas i iznad jednu funkciju za ucitavanje svih brodova - za prikaz samo!
    public List<String> ucitajTipoveBrodova() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TIPOVE_BRODA, null);
        List<String> tipovi = new ArrayList<>();
        posiljalac.posalji(zahtev);
        //ceka se odg

        Odgovor odg = (Odgovor) primalac.primi();
        tipovi = (List<String>) odg.getOdgovor();

        return tipovi;
    }

    public List<String> ucitajKategorijeBrodova() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KATEGORIJE_BRODA, null);
        List<String> kategorije = new ArrayList<>();
        posiljalac.posalji(zahtev);
        //ceka se odg

        Odgovor odg = (Odgovor) primalac.primi();
        kategorije = (List<String>) odg.getOdgovor();

        return kategorije;
    }

    public void dodajBrod(Brod b) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_BROD, b);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je uspesno dodao brod");
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public void promeniBrod(Brod b) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_BROD, b);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je azurirao brod.");
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public void obrisiBrod(Brod b) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_BROD, b);
        posiljalac.posalji(zahtev);
        //ceka seeee
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je obrisao brod.");
        } else {
            System.out.println("Sistem nije obirsao brod.");
            throw new Exception("GRESKA");
        }
    }

    //LUKE - imas iznad isto ucitavanje luka kao listu!
    public void dodajLuku(Luka l) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_LUKU, l);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je uspesno dodao luku.");
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public void promeniLuku(Luka l) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_LUKU, l);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je azurirao luku.");
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public void obrisiLuku(Luka l) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_LUKU, l);
        posiljalac.posalji(zahtev);
        //ceka seeee
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je obrisao luku.");
        } else {
            System.out.println("Sistem nije obrisao luku.");
            throw new Exception("GRESKA");
        }
    }

    public void dodajSkiperDerzurstvo(SkiperDez sd) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_SKIPER_DEZURSTVO, sd);
        posiljalac.posalji(zahtev);
        //ceka see
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Sistem je uspesno dodao dezurstvo skipera.");
        } else {
            System.out.println(odg.getOdgovor());
            throw new Exception("GRESKA");
        }
    }

    public List<Iznajmljivanje> pretraziIznajmljivanja(Iznajmljivanje i) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_IZNAJMLJIVANJE, i);
        List<Iznajmljivanje> iznajmljivanja = new ArrayList<>();
        posiljalac.posalji(zahtev);
        //ceka se odg

        Odgovor odg = (Odgovor) primalac.primi();
        iznajmljivanja = (List<Iznajmljivanje>) odg.getOdgovor();
        return iznajmljivanja;
    }

    public List<Korisnik> pretraziKorisnika(Korisnik k) {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_KORISNIKE, k);
        List<Korisnik> korisnik = new ArrayList<>();
        posiljalac.posalji(zahtev);
        //ceka se odg

        Odgovor odg = (Odgovor) primalac.primi();
        korisnik = (List<Korisnik>) odg.getOdgovor();
        return korisnik;
    }

}
