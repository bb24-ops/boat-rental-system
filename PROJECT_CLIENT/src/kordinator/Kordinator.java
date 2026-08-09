/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kordinator;

import domen.Administrator;
import domen.Luka;
import forme.Brod.DodajBrodForma;
import forme.Brod.PrikazBrodovaForma;
import forme.Skiper.DodajSkiperaForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.Iznajmljivanje.DodajIznajmljivanjeFormu;
import forme.LoginForma;
import forme.Iznajmljivanje.PrikazIznajmljivanjaForma;
import forme.Korisnik.DodajKorniskaForma;
import forme.Korisnik.KorisnikDetalji;
import forme.Korisnik.PrikazKorisnikaForma;
import forme.Luka.DodajLukuForma;
import forme.Luka.PrikazLukaForma;
import forme.Skiper.PrikazSkiperaForma;
import forme.administrator.Autentifikacija;
import forme.administrator.DodajAdministratoraForma;
import forme.administrator.PrikazAdministratorForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.Brod.DodajBrodController;
import kontroleri.Brod.PrikazBrodaController;
import kontroleri.Skiper.DodajSkiperaController;
import kontroleri.GlavnaFormaController;
import kontroleri.Iznajmljivanje.DodajIznajmljivanjeController;
import kontroleri.LoginController;
import kontroleri.Iznajmljivanje.PrikazIznajmljivanjaController;
import kontroleri.Korisnik.DodajKorisnikaController;
import kontroleri.Korisnik.KorisnikDetaljiController;
import kontroleri.Korisnik.PrikazKorisnikaController;
import kontroleri.Luka.DodajLukuController;
import kontroleri.Luka.PrikazLukaController;
import kontroleri.Skiper.PrikazSkiperaController;
import kontroleri.administrator.AutentifikacijaController;
import kontroleri.administrator.DodajAdministratoraController;
import kontroleri.administrator.PrikazAdministratoraController;

/**
 *
 * @author boris
 */
public class Kordinator {

    private static Kordinator instanca;
    private Administrator ulogovani;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;

    private PrikazSkiperaController prikazSkiperaController;
    private DodajSkiperaController dodajSkiperaController;

    private PrikazIznajmljivanjaController prikazIznajmljivanjaController;
    private DodajIznajmljivanjeController dodajIznController;

   

    private PrikazKorisnikaController prikazKorisnikaController;
    private DodajKorisnikaController dodajKorController;

    private PrikazAdministratoraController prikazAdminController;
    private DodajAdministratoraController dodajAdminaController;

    private PrikazBrodaController prikazBrodController;
    private DodajBrodController dodajBrodController;

    private PrikazLukaController prikazLukaController;
    private DodajLukuController dodajLukuController;
    
    private AutentifikacijaController autentifikacijaController;
    
    private KorisnikDetaljiController korisnikDetController;
    private Luka lukaKoriniska;

    private Map<String, Object> parametri;

    private Kordinator() {
        parametri = new HashMap<>();
    }

    public static Kordinator getInstance() {
        if (instanca == null) {
            instanca = new Kordinator();
        }
        return instanca;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }

    public void otvoriPrikazSkiperaFormu() {
        prikazSkiperaController = new PrikazSkiperaController(new PrikazSkiperaForma());
        prikazSkiperaController.otvoriFormu();
    }

    public Administrator getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Administrator ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriDodajSkiperaFormu() {
        dodajSkiperaController = new DodajSkiperaController(new DodajSkiperaForma());
        dodajSkiperaController.otvoriFormu(FormaMod.DODAJ);
    }

    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    public Object vratiParam(String s) {
        return parametri.get(s);
    }

    public void otvoriIzmeniSkiperaFormu() {
        dodajSkiperaController = new DodajSkiperaController(new DodajSkiperaForma());
        dodajSkiperaController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPrikazSkiperaForma() {
        prikazSkiperaController.osveziFormu();
    }

    public void otvoriPrikazIznajmljivanjaFormu() {
        prikazIznajmljivanjaController = new PrikazIznajmljivanjaController(new PrikazIznajmljivanjaForma());
        prikazIznajmljivanjaController.otvoriFormu();
    }

  

    

//    public void otvoriGlavnuFormu(FormaMod formaMod) {
//        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
//        glavnaFormaController.otvoriFormu(formaMod);
//    }
    public void osveziObeTabele() {
        prikazIznajmljivanjaController.pripremiFormu();
    }

    public void otvoriPrikazKorisnikaFormu() {
        prikazKorisnikaController = new PrikazKorisnikaController(new PrikazKorisnikaForma());
        prikazKorisnikaController.otvoriFormu();
    }

    public void otvoriKreirajKorisnikaFormu() {
        dodajKorController = new DodajKorisnikaController(new DodajKorniskaForma());
        dodajKorController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniKorisnikaFormu() {
        dodajKorController = new DodajKorisnikaController(new DodajKorniskaForma());
        dodajKorController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziKorisnikForma() {
        prikazKorisnikaController.osveziFormu();
    }

    //ADMIN
    public void otvoriPrikazAdminaFormu() {
        prikazAdminController = new PrikazAdministratoraController(new PrikazAdministratorForma());
        prikazAdminController.otvoriFormu();
    }

    public void otvoriKreirajAdminaFormu() {
        dodajAdminaController = new DodajAdministratoraController(new DodajAdministratoraForma());
        dodajAdminaController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniAdminaFormu() {
        dodajAdminaController = new DodajAdministratoraController(new DodajAdministratoraForma());
        dodajAdminaController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziAdminFormu() {
        prikazAdminController.otvoriFormu();
    }

    //BROD
    public void otvoriPrikazBrodovaFormu() {
        prikazBrodController = new PrikazBrodaController(new PrikazBrodovaForma());
        prikazBrodController.otvoriFormu();
    }

    public void otvoriKreirajBrodFormu() {
        dodajBrodController = new DodajBrodController(new DodajBrodForma());
        dodajBrodController.otvoriFormu(FormaMod.DODAJ);

    }

    public void otvoriIzmeniBrodFormu() {
        dodajBrodController = new DodajBrodController(new DodajBrodForma());
        dodajBrodController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziBrodFormu() {
        if (prikazBrodController != null) {
            prikazBrodController.osveziFormu();
        }
    }

    //LUKE
    public void otvoriPrikazLukaFormu() {
        prikazLukaController = new PrikazLukaController(new PrikazLukaForma());
        prikazLukaController.otvoriFormu();
    }

    public void otvoriKreirajLukuFormu() {
        dodajLukuController = new DodajLukuController(new DodajLukuForma());
        dodajLukuController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniLukuFormu() {
        dodajLukuController = new DodajLukuController(new DodajLukuForma());
        dodajLukuController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziLukaFormu() {
        if (prikazLukaController != null) {
            prikazLukaController.otvoriFormu();
        }
    }

    public void otvoriKreirajIznajmljivanjeFormu() {
        dodajIznController = new DodajIznajmljivanjeController(new DodajIznajmljivanjeFormu());
        dodajIznController.otvoriFormu(FormaMod.DODAJ);
    }

//    public void otvoriKreirajIznajmljivanjeFormu() {
//        dodajIznController = new DodajIznajmljivanjeController(new DodajIznajmljivanjeFormu ());
//        dodajIznController.otvoriFormu(FormaMod.DODAJ);
//    }
    public void otvoriIzmeniIznajmljivanjeFormu() {
        dodajIznController = new DodajIznajmljivanjeController(new DodajIznajmljivanjeFormu());
        dodajIznController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormuPrikazIznajmljivanja() {
        if (prikazIznajmljivanjaController != null) {
            prikazIznajmljivanjaController.pripremiFormu();
        }
    }

    public void otvoriKreirajKorisnikaFormu(DodajIznajmljivanjeFormu dif) {
        dodajKorController = new DodajKorisnikaController(new DodajKorniskaForma());
        dodajKorController.otvoriFormu(FormaMod.DODAJ);
    }

    public void osveziComboBoxKorinsika() {
        if(dodajIznController != null){
             dodajIznController.popuniComboBoxeve();
        }
    }

    public void otvoriAutentifikacijaFormu() {
        autentifikacijaController = new AutentifikacijaController(new Autentifikacija());
        autentifikacijaController.otvoriFormu();
    }
    

    public void zabraniPristupAdminDelu() {
        if(glavnaFormaController != null){
            glavnaFormaController.zabraniPrisup();
        }
    }

    public void otvoriDetaljiAdminaFormu() {
        dodajAdminaController = new DodajAdministratoraController(new DodajAdministratoraForma());
        dodajAdminaController.otvoriFormu(FormaMod.DETALJI);
    }

    public void zatvoriPrikazAdminaFormu() {
        if(prikazAdminController != null){
            prikazAdminController.zatvoriFormu();
        }
    }

    public void otvoriDetaljiKorisnikFormu() {
        korisnikDetController = new KorisnikDetaljiController(new KorisnikDetalji());
        korisnikDetController.otvoriFormu();
    }

    public Luka getLukaKoriniska() {
        return lukaKoriniska;
    }

    public void setLukaKoriniska(Luka lukaKoriniska) {
        this.lukaKoriniska = lukaKoriniska;
    }
    
    
}
