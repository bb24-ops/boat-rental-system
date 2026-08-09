/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Skiper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author boris
 */
public class ModelTabeleSkiper extends AbstractTableModel {

    List<Skiper> lista;
    String[] kolone = {"ID Skipera", "Ime", "Broj odradjenih termina", "Sertifikat"};

    public ModelTabeleSkiper(List<Skiper> lista) {
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
        Skiper s = lista.get(rowIndex);

        switch (columnIndex) {
            //ako imas datum, mora SimpleDateFormat da ide ono cudo i formater?
            case 0:
                return s.getIdSkiper();
            case 1:
                return s.getIme();
            case 2:
                return s.getBrojTerminaUkupno();
            case 3:
                return s.getSertifikat();

            default:
                return "N/A";
        }
    }

    public List<Skiper> getLista() {
        return lista;
    }

    public void setLista(List<Skiper> lista) {
        this.lista = lista;
    }

    //Ovo ti valjda ne pretreazuje lepo i treba da se sredi! 09.08.2025
    public void pretrazi(String ime, int minTermin, int maxTermin) {
        List<Skiper> filterLista = lista.stream()
                // filtriranje po imenu (ako je null ili prazan, preskače se)
                .filter(s -> ime == null || ime.isEmpty() || s.getIme().toLowerCase().contains(ime.toLowerCase()))
                // donja granica termina (ako je <= 0, preskače se)
                .filter(s -> minTermin <= 0 || s.getBrojTerminaUkupno() >= minTermin)
                // gornja granica termina (ako je <= 0, preskače se)
                .filter(s -> maxTermin <= 0 || s.getBrojTerminaUkupno() <= maxTermin)
                .collect(Collectors.toList());

        this.lista = filterLista;
        fireTableDataChanged();
    }

}
