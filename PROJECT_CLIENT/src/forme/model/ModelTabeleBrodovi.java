/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Administrator;
import domen.Brod;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author boris
 */
public class ModelTabeleBrodovi extends AbstractTableModel {

    List<Brod> lista;
    String[] kolone = {"ID Broda", "Naziv", "Tip", "Kategorija", "Cena po danu"};

    public ModelTabeleBrodovi(List<Brod> lista) {
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
        Brod b = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return b.getIdBrod();
            case 1:
                return b.getNaziv();
            case 2:
                return b.getTip();
            case 3:
                return b.getKategorija();
            case 4:
                return b.getCenaPoDanu();
            default:
                return "N/A";
        }
    }

    public List<Brod> getLista() {
        return lista;
    }

    public void setLista(List<Brod> lista) {
        this.lista = lista;
    }

    public void pretrazi(String naziv, double minCena, double maxCena) {
        List<Brod> filterLista = lista.stream()
                .filter(p -> naziv == null || naziv.isEmpty() || p.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
                .filter(p -> minCena <= 0 || p.getCenaPoDanu() >= minCena)
                .filter(p -> maxCena <= 0 || p.getCenaPoDanu() <= maxCena)
                .collect(Collectors.toList());
        this.lista = filterLista;
        fireTableDataChanged();
    }
}
