/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Administrator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author boris
 */
public class ModelTabeleAdmini extends AbstractTableModel {

    List<Administrator> lista;
    String[] kolone = {"ID Admina", "Ime", "Prezime", "Broj Telefona", "Username", "Password"};

    public ModelTabeleAdmini(List<Administrator> lista) {
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
        Administrator a = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getIdAdministrator();
            case 1:
                return a.getIme();
            case 2:
                return a.getPrezime();
            case 3:
                return a.getBrojTelefona();
            case 4:
                return a.getUsername();
            case 5:
                return a.getPassword() == null ? "" : "*".repeat(a.getPassword().length());
            default:
                return "N/A";
        }
    }

    public List<Administrator> getLista() {
        return lista;
    }

    public void setLista(List<Administrator> lista) {
        this.lista = lista;
    }

    public void pretrazi(String ime, String prezime) {
        List<Administrator> filterLista = (List<Administrator>) lista.stream()
                .filter(k -> ime == null || ime.isEmpty() || k.getIme().toLowerCase().contains(ime.toLowerCase()))
                .filter(k -> prezime == null || prezime.isEmpty() || k.getPrezime().toLowerCase().contains(prezime.toLowerCase()))
                .collect(Collectors.toList());

        this.lista = filterLista;
        fireTableDataChanged();
    }
}
