/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Administrator;
import domen.Luka;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author boris
 */
public class ModelTabeleLuke extends AbstractTableModel {

    List<Luka> lista;
    String[] kolone = {"ID Luke", "Naziv", "Broj mesta"};

    public ModelTabeleLuke(List<Luka> lista) {
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
        Luka l = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return l.getIdLuka();
            case 1:
                return l.getNaziv();
            case 2:
                return l.getBrMesta();
            default:
                return "N/A";
        }
    }

    public List<Luka> getLista() {
        return lista;
    }

    public void setLista(List<Luka> lista) {
        this.lista = lista;
    }

    public void pretrazi(String naziv) {
        List<Luka> filterLista = lista.stream()
                .filter(l -> naziv == null || naziv.isEmpty() || l.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
                .collect(Collectors.toList());
        this.lista = filterLista;
        fireTableDataChanged();
    }
}
