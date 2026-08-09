/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author boris
 */
public enum Operacija implements Serializable {
    LOGIN,
    UCITAJ_SKIPERE,
    OBRISI_SKIPERA, 
    DODAJ_SKIPERA,
    AZURIRAJ_SKIPERA, 
    UCITAJ_IZNAJMJIVANJE, 
    UCITAJ_STAVKE, 
    OBRISI_IZNAJMLJIVANJE, 
    UCITAJ_KORISNIKE, 
    UCITAJ_BRODOVE, 
    DODAJ_IZNAJMLJIVANJE, 
    OBRISI_STAVKU, 
    AZURIRAJ_STAVKU,
    AZURIRAJ_IZNAJMLJIVANJE, 
    AZURIRAJ_IZNAJMLJIVANJE_UKUPNU_CENU, 
    UCITAJ_IZNAJMJIVANJE_PO_ID, 
    UCITAJ_LUKE, 
    DODAJ_KORISNIKA, 
    AZURIRAJ_KORISNIKA, 
    OBRISI_KORISNIKA, 
    UCITAJ_ADMINE,  
    DODAJ_ADMINA, 
    AZURIRAJ_ADMINA, 
    OBRISI_ADMINA, 
    UCITAJ_TIPOVE_BRODA, 
    UCITAJ_KATEGORIJE_BRODA, 
    DODAJ_BROD, 
    IZMENI_BROD, 
    OBRISI_BROD, 
    DODAJ_LUKU, IZMENI_LUKU, OBRISI_LUKU, DODAJ_SKIPER_DEZURSTVO, PRETRAZI_IZNAJMLJIVANJE, PRETRAZI_KORISNIKE;   
}
