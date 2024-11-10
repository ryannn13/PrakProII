package view.tempatlahir;

import javax.swing.table.*;
import java.util.List;
import model.TempatLahir;

class TempatLahirTableModel extends AbstractTableModel{
    private String[] columnNames = {"Nama Kota"};
    private List<TempatLahir> data;

    public TempatLahirTableModel(List<TempatLahir> data){
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
        TempatLahir rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNamaKota();
                break;
        }
        return value;
    }

    public boolean isCellEditable(int row, int col){
        return false;
    }

    public void add(TempatLahir value){
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
