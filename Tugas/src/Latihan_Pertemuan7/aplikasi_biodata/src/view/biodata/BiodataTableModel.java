package view.biodata;

import javax.swing.table.*;
import java.util.List;
import model.Biodata;

public class BiodataTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Tempat Lahir", "Tanggal Lahir", "Alamat", "Umur", "Jenis Kelamin"};
    public List<Biodata> data;

    public BiodataTableModel(List<Biodata> data){
        this.data = data;
    }

    public int getColumnCount(){
        return columnNames.length;
    }

    public int getRowCount(){
        return data.size();
    }

    public String getColumnName(int col){
        return columnNames[col];
    }

    public Object getValueAt(int row, int col){
        Biodata rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;
            case 1:
                value = rowItem.getTempatLahir().getNamaKota();
                break;
            case 2:
                value = rowItem.getTanggalLahir();
                break;
            case 3:
                value = rowItem.getAlamat();
                break;
            case 4:
                value = rowItem.getUmur();
                break;
            case 5:
                value = rowItem.getJenisKelamin();
                break;
        }
        return value;
    }

    public boolean isCellEditable(int row, int col){
        return false;
    }

    public void add(Biodata biodata) {
        data.add(biodata);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
