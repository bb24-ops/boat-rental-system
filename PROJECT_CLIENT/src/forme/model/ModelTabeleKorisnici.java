/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Korisnik;
import domen.Skiper;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author boris
 */
public class ModelTabeleKorisnici extends AbstractTableModel {

    List<Korisnik> lista;
    String[] kolone = {"ID Korisnika", "Ime", "Prezime", "Broj Telefona", "Povratna Luka"};

    public ModelTabeleKorisnici(List<Korisnik> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik k = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return k.getIdKorisnik();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getBrojTelefona();
            case 4:
                return k.getLuka().getNaziv();

            default:
                return "N/A";
        }
    }

    public List<Korisnik> getLista() {
        return lista;
    }

    public void setLista(List<Korisnik> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
}
