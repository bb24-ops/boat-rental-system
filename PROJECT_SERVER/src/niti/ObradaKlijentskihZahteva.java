/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import static komunikacija.Operacija.AZURIRAJ_ADMINA;
import static komunikacija.Operacija.AZURIRAJ_SKIPERA;
import static komunikacija.Operacija.DODAJ_BROD;
import static komunikacija.Operacija.DODAJ_LUKU;
import static komunikacija.Operacija.DODAJ_SKIPERA;
import static komunikacija.Operacija.LOGIN;
import static komunikacija.Operacija.OBRISI_SKIPERA;
import static komunikacija.Operacija.UCITAJ_IZNAJMJIVANJE;
import static komunikacija.Operacija.UCITAJ_SKIPERE;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 *
 * @author boris
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();
                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        try {
                        Administrator a = (Administrator) zahtev.getParametar();
                        a = Controller.getInstanca().login(a);
                        odgovor.setOdgovor(a);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;

                    //SKIPERI <=================
                    case UCITAJ_SKIPERE:
                        List<Skiper> skiperi = Controller.getInstanca().ucitajSkipere();
                        odgovor.setOdgovor(skiperi);
                        break;
                    case OBRISI_SKIPERA:
                        try {
                        Skiper s = (Skiper) zahtev.getParametar();
                        Controller.getInstanca().obrisiSkipera(s);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case DODAJ_SKIPERA:
                        try {
                        Skiper s = (Skiper) zahtev.getParametar();
                        Controller.getInstanca().dodajSkipera(s);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case AZURIRAJ_SKIPERA:
                        try {
                        Skiper s1 = (Skiper) zahtev.getParametar();
                        Controller.getInstanca().azurirajSkipera(s1);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;

                    //IZNAJMLJIVANJA <====================
                    case UCITAJ_IZNAJMJIVANJE:
                        List<Iznajmljivanje> iznajmljivanja = Controller.getInstanca().ucitajIznajmljivanja();
                        //System.out.println("KLASA OKZ: ");
                        //System.out.println(iznajmljivanja);
                        odgovor.setOdgovor(iznajmljivanja);
                        break;
                    case UCITAJ_STAVKE:
                        List<StavkaIzn> stavke = Controller.getInstanca().ucitajStavke((int) zahtev.getParametar());
                        //System.out.println("KLASA OKZ: ");
                        //System.out.println(stavke);
                        odgovor.setOdgovor(stavke);
                        break;

                    case UCITAJ_KORISNIKE:
                        List<Korisnik> korisnici = Controller.getInstanca().ucitajKorisnike();
                        odgovor.setOdgovor(korisnici);
                        break;
                    case UCITAJ_BRODOVE:
                        List<Brod> brodovi = Controller.getInstanca().ucitajBrodove();
                        odgovor.setOdgovor(brodovi);
                        break;
                    case DODAJ_IZNAJMLJIVANJE:
                        try {
                        Iznajmljivanje i = (Iznajmljivanje) zahtev.getParametar();
                        Controller.getInstanca().dodajIznajmljivanje(i);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case AZURIRAJ_IZNAJMLJIVANJE:
                        try {
                        Iznajmljivanje izn = (Iznajmljivanje) zahtev.getParametar();
                        Controller.getInstanca().azurirajIznajmljivanje(izn);
                        odgovor.setOdgovor(null);  // uspešno
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case UCITAJ_LUKE:
                        List<Luka> luke = Controller.getInstanca().ucitajLuke();
                        odgovor.setOdgovor(luke);
                        break;

                    //KORISNIK <==============
                    case DODAJ_KORISNIKA:
                        try {
                        Korisnik k = (Korisnik) zahtev.getParametar();
                        Controller.getInstanca().dodajKorisnika(k);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case AZURIRAJ_KORISNIKA:
                        try {
                        Korisnik ko = (Korisnik) zahtev.getParametar();
                        Controller.getInstanca().promeniKorisnika(ko);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case OBRISI_KORISNIKA:
                        try {
                        Korisnik kor = (Korisnik) zahtev.getParametar();
                        Controller.getInstanca().obrisiKorisnika(kor);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;

                    //ADMINISTRATOR <=============
                    case UCITAJ_ADMINE:
                        List<Administrator> admini = Controller.getInstanca().ucitajAdmine();
                        odgovor.setOdgovor(admini);
                        break;
                    case DODAJ_ADMINA:
                        try {
                        Administrator adm = (Administrator) zahtev.getParametar();
                        Controller.getInstanca().dodajAdmina(adm);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case AZURIRAJ_ADMINA:
                        try {
                        Administrator ad = (Administrator) zahtev.getParametar();
                        Controller.getInstanca().promeniAdmina(ad);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case OBRISI_ADMINA:
                        try {
                        Administrator admin = (Administrator) zahtev.getParametar();
                        Controller.getInstanca().obrisiAdmina(admin);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;

                    //BRODOVIIIII <====================
                    case UCITAJ_TIPOVE_BRODA:
                        List<String> tipovi = Controller.getInstanca().ucitajTipove();
                        odgovor.setOdgovor(tipovi);
                        break;
                    case UCITAJ_KATEGORIJE_BRODA:
                        List<String> kategorije = Controller.getInstanca().ucitajKategorije();
                        odgovor.setOdgovor(kategorije);
                        break;
                    case DODAJ_BROD:
                        try {
                        Brod b = (Brod) zahtev.getParametar();
                        Controller.getInstanca().dodajBrod(b);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case IZMENI_BROD:
                        try {
                        Brod br = (Brod) zahtev.getParametar();
                        Controller.getInstanca().promeniBrod(br);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case OBRISI_BROD:
                        try {
                        Brod brod = (Brod) zahtev.getParametar();
                        Controller.getInstanca().obrisiBrod(brod);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;

                    //LUKE <==========================
                    case DODAJ_LUKU:
                        try {
                        Luka l = (Luka) zahtev.getParametar();
                        Controller.getInstanca().dodajLuku(l);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case IZMENI_LUKU:
                        try {
                        Luka lu = (Luka) zahtev.getParametar();
                        Controller.getInstanca().promeniLuku(lu);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case OBRISI_LUKU:
                        try {
                        Luka luka = (Luka) zahtev.getParametar();
                        Controller.getInstanca().obrisiLuku(luka);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case DODAJ_SKIPER_DEZURSTVO:
                        try {
                        SkiperDez sd = (SkiperDez) zahtev.getParametar();
                        Controller.getInstanca().dodajSkiperDez(sd);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case PRETRAZI_IZNAJMLJIVANJE:
                        Iznajmljivanje i = (Iznajmljivanje) zahtev.getParametar();
                        List<Iznajmljivanje> iznajmljivanjaPoKrit = Controller.getInstanca().pretraziIznajmljivanjaPoKrit(i);
                        odgovor.setOdgovor(iznajmljivanjaPoKrit);
                        break;
                    case PRETRAZI_KORISNIKE:
                        Korisnik k = (Korisnik) zahtev.getParametar();
                        List<Korisnik> korisniciPoKrit = Controller.getInstanca().pretraziKorisnikePoKrit(k);
                        odgovor.setOdgovor(korisniciPoKrit);
                        break;
                    default:
                        System.out.println("Greska, operacija ne postoji!");
                }
                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void prekini() {
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }

}
