/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Iznajmljivanje;
import domen.StavkaIzn;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author boris
 */
public class ModelTabeleStavkaIzn extends AbstractTableModel {
    List<StavkaIzn> lista;
    String[] kolone = {"Rb Stavke","Datum izdavanja","Datum povratka", "Ukupan broj dana", "Brod", "Cena stavke"};

    public ModelTabeleStavkaIzn(List<StavkaIzn> lista) {
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
        StavkaIzn s = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        switch (columnIndex) {
            case 0: return s.getRbStavke();
            case 1: return s.getDatumIzdavanja() != null ? sdf.format(s.getDatumIzdavanja()) : "";
            case 2: return s.getDatumPovratka() != null ? sdf.format(s.getDatumPovratka()) : "";
            case 3: return s.getBrojDana();
            case 4: return s.getBrod().getNaziv();
            case 5: return s.getIznosJedneStavke();
               
            default:
                return "N/A";
        }
    }

    public List<StavkaIzn> getLista() {
        return lista;
    }

    public void setLista(List<StavkaIzn> lista) {
        this.lista = lista;
    }

    public void dodajStavku(StavkaIzn stavka) { 
        int trenutniRB = lista.size()+1;
        stavka.setRbStavke(trenutniRB);
        lista.add(stavka);
        fireTableDataChanged();
    }

    public void obrisiStavku(StavkaIzn si) {
        lista.remove(si);
        fireTableDataChanged();
    }
}
