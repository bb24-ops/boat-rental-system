/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Administrator;
import domen.Brod;
import domen.Iznajmljivanje;
import domen.KategorijaBroda;
import domen.Korisnik;
import domen.Luka;
import domen.Skiper;
import domen.SkiperDez;
import domen.StavkaIzn;
import domen.TipBroda;
import java.util.Arrays;
import java.util.List;
import operacija.administratori.KreirajAdministratoraSO;
import operacija.administratori.ObrisiAdministratoraSO;
import operacija.administratori.PromeniAdministratoraSO;
import operacija.administratori.UcitajAdministratoreSO;
import operacija.brodovi.KreirajBrodSO;
import operacija.brodovi.ObrisiBrodSO;
import operacija.brodovi.PromeniBrodSO;
import operacija.brodovi.UcitajBrodoveSO;
import operacija.iznajmljivanja.KreirajIznajmljivanjeSO;
import operacija.iznajmljivanja.PretraziIznajmljivanjaSO;
import operacija.iznajmljivanja.PromeniIznajmljivanjeSO;
import operacija.iznajmljivanja.UcitajIznajmljivanjaSO;
import operacija.korisnici.KreirajKorisnikaSO;
import operacija.korisnici.ObrisiKorisnikaSO;
import operacija.korisnici.PretraziKorisnikeSO;
import operacija.korisnici.PromeniKorisnikaSO;
import operacija.korisnici.UcitajKorisnikeSO;
import operacija.skiperi.UcitajSkipereSO;
import operacija.login.LoginSO;
import operacija.luka.KreirajLukuSO;
import operacija.luka.ObrisiLukuSO;
import operacija.luka.PromeniLukuSO;
import operacija.luka.UcitajLukeSO;
import operacija.skiperDez.KreirajSkiperDezSO;
import operacija.skiperi.ObrisiSkiperaSO;
import operacija.skiperi.PromeniSkiperaSO;
import operacija.skiperi.KreirajSkiperaSO;
import slike.UcitajSIznajmljivana;

/**
 *
 * @author boris
 */
public class Controller {

    private static Controller instanca;

    public static Controller getInstanca() {
        if (instanca == null) {
            instanca = new Controller();
        }
        return instanca;
    }

    private Controller() {

    }

    public Administrator login(Administrator a) throws Exception {
        LoginSO operacija = new LoginSO();
        operacija.izvrsi(a, null);
        System.out.println("KLASA CONTROLLER:" + operacija.getAdmin());
        return operacija.getAdmin();
    }

    public List<Skiper> ucitajSkipere() throws Exception {
        UcitajSkipereSO operacija = new UcitajSkipereSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER:" + operacija.getSkiperi());
        return operacija.getSkiperi();
    }

    public void obrisiSkipera(Skiper s) throws Exception {
        ObrisiSkiperaSO operacija = new ObrisiSkiperaSO();
        operacija.izvrsi(s, null);
    }

    public void dodajSkipera(Skiper s) throws Exception {
        KreirajSkiperaSO operacija = new KreirajSkiperaSO();
        operacija.izvrsi(s, null);
    }

    public void azurirajSkipera(Skiper s1) throws Exception {
        PromeniSkiperaSO operacija = new PromeniSkiperaSO();
        operacija.izvrsi(s1, null);
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() throws Exception {
        UcitajIznajmljivanjaSO operacija = new UcitajIznajmljivanjaSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER:" + operacija.getIznajmljivanja());
        return operacija.getIznajmljivanja();
    }

    public List<StavkaIzn> ucitajStavke(int id) throws Exception {
        UcitajSIznajmljivana operacija = new UcitajSIznajmljivana();
        operacija.izvrsi(id, null);
        System.out.println("KLASA CONTROLLER:" + operacija.getStavke());
        return operacija.getStavke();
    }
    
    public List<Korisnik> ucitajKorisnike() throws Exception {
        UcitajKorisnikeSO operacija = new UcitajKorisnikeSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER:" + operacija.getKorisnici());
        return operacija.getKorisnici();
    }

    public List<Brod> ucitajBrodove() throws Exception {
        UcitajBrodoveSO operacija = new UcitajBrodoveSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER:" + operacija.getBrodovi());
        return operacija.getBrodovi();
    }

    public void dodajIznajmljivanje(Iznajmljivanje i) throws Exception {
        KreirajIznajmljivanjeSO operacija = new KreirajIznajmljivanjeSO();
        operacija.izvrsi(i, null);
    }

    public void azurirajIznajmljivanje(Iznajmljivanje izn) throws Exception {
        PromeniIznajmljivanjeSO operacija = new PromeniIznajmljivanjeSO();
        operacija.izvrsi(izn, null);
    }


    public List<Luka> ucitajLuke() throws Exception {
        UcitajLukeSO operacija = new UcitajLukeSO();
        operacija.izvrsi(null, null);
        return operacija.getLuke();
    }

    public void dodajKorisnika(Korisnik k) throws Exception {
        KreirajKorisnikaSO operacija = new KreirajKorisnikaSO();
        operacija.izvrsi(k, null);
    }

    public void promeniKorisnika(Korisnik ko) throws Exception {
        PromeniKorisnikaSO operacija = new PromeniKorisnikaSO();
        operacija.izvrsi(ko, null);
    }

    public void obrisiKorisnika(Korisnik kor) throws Exception {
        ObrisiKorisnikaSO operacija = new ObrisiKorisnikaSO();
        operacija.izvrsi(kor, null);
    }

    public List<Administrator> ucitajAdmine() throws Exception {
        UcitajAdministratoreSO operacija = new UcitajAdministratoreSO();
        operacija.izvrsi(null, null);
        return operacija.getAdmini();
    }

    public void dodajAdmina(Administrator adm) throws Exception {
        KreirajAdministratoraSO operacija = new KreirajAdministratoraSO();
        operacija.izvrsi(adm, null);
    }

    public void promeniAdmina(Administrator ad) throws Exception {
        PromeniAdministratoraSO operacija = new PromeniAdministratoraSO();
        operacija.izvrsi(ad, null);
    }

    public void obrisiAdmina(Administrator admin) throws Exception {
        ObrisiAdministratoraSO operacija = new ObrisiAdministratoraSO();
        operacija.izvrsi(admin, null);
    }

    public List<String> ucitajTipove() {
        TipBroda[] tipovi = TipBroda.values();
        List<String> tipoviString = Arrays.stream(tipovi).map(Enum::name).toList();
        return tipoviString;
    }

    public List<String> ucitajKategorije() {
        KategorijaBroda[] kategorije = KategorijaBroda.values();
        List<String> kategorijeString = Arrays.stream(kategorije).map(Enum::name).toList();
        return kategorijeString;
    }

    public void dodajBrod(Brod b) throws Exception {
        KreirajBrodSO operacija = new KreirajBrodSO();
        operacija.izvrsi(b, null);
    }

    public void promeniBrod(Brod br) throws Exception {
        PromeniBrodSO operacija = new PromeniBrodSO();
        operacija.izvrsi(br, null);
    }

    public void obrisiBrod(Brod brod) throws Exception {
        ObrisiBrodSO operacija = new ObrisiBrodSO();
        operacija.izvrsi(brod, null);
    }

    public void dodajLuku(Luka l) throws Exception {
        KreirajLukuSO operacija = new KreirajLukuSO();
        operacija.izvrsi(l, null);
    }

    public void promeniLuku(Luka lu) throws Exception {
        PromeniLukuSO operacija = new PromeniLukuSO();
        operacija.izvrsi(lu, null);
    }

    public void obrisiLuku(Luka luka) throws Exception {
        ObrisiLukuSO operacija = new ObrisiLukuSO();
        operacija.izvrsi(luka, null);
    }

    public void dodajSkiperDez(SkiperDez sd) throws Exception {
        KreirajSkiperDezSO operacija = new KreirajSkiperDezSO();
        operacija.izvrsi(sd, null);
    }

    public List<Iznajmljivanje> pretraziIznajmljivanjaPoKrit(Iznajmljivanje i) throws Exception {
        PretraziIznajmljivanjaSO operacija = new PretraziIznajmljivanjaSO();
        operacija.izvrsi(i, null);
        return operacija.getIznajmljivanja();
    }

    public List<Korisnik> pretraziKorisnikePoKrit(Korisnik k) throws Exception {
        PretraziKorisnikeSO operacija = new PretraziKorisnikeSO();
        operacija.izvrsi(k, null);
        return operacija.getListaKorisnika();
    }

}
