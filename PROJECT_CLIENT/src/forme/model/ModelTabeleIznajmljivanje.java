/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Iznajmljivanje;
import domen.Skiper;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author boris
 */
public class ModelTabeleIznajmljivanje extends AbstractTableModel {

    List<Iznajmljivanje> lista;
    String[] kolone = {"ID Iznajmljivanja", "Datum", "Ukupan iznos", "Administrator", "Ime korisnika"};

    public ModelTabeleIznajmljivanje(List<Iznajmljivanje> lista) {
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
        Iznajmljivanje z = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return z.getIdIznajmljivanje();
            case 1:
                return z.getDatum();
            case 2:
                return z.getUkupanIznos();
            case 3:
                return z.getAdministrator().getIme() + " " + z.getAdministrator().getPrezime();
            case 4:
                return z.getKorisnik().getIme();

            default:
                return "N/A";
        }
    }

    public List<Iznajmljivanje> getLista() {
        return lista;
    }

    public void setLista(List<Iznajmljivanje> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
}
