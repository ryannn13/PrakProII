package view.tempatlahir;

import javax.swing.*;
import java.util.*;
import model.TempatLahir;
import dao.TempatLahirDao;

public class TempatLahirFrame extends JFrame {
    private List<TempatLahir> tempatLahirList;
    private JTextField textFieldNamaKota;
    private TempatLahirTableModel tableModel;
    private TempatLahirDao tempatLahirDao;

    public TempatLahirFrame(TempatLahirDao tempatLahirD){
        this.tempatLahirDao = tempatLahirDao;
        this.tempatLahirList = tempatLahirDao.findAll();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelInput = new JLabel("Nama Kota");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNamaKota = new JTextField();
        textFieldNamaKota.setBounds(15, 60, 350, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 100, 100, 40);

        javax.swing.JTable table = new JTable();
        JScrollPane scrollPaneTable = new JScrollPane(table);
        scrollPaneTable.setBounds(15, 150, 350, 200);

        tableModel = new TempatLahirTableModel(tempatLahirList);
        table.setModel(tableModel);

        TempatLahirButtonSimpanActionListener actionListener = new TempatLahirButtonSimpanActionListener(this, tempatLahirDao);
        button.addActionListener(actionListener);
        this.add(button);
        this.add(textFieldNamaKota);
        this.add(labelInput);
        this.add(scrollPaneTable);

        this.setSize(400,500);
        this.setLayout(null);
    }

    public String getNamaKota(){
        return textFieldNamaKota.getText();
    }

    public void addTempatLahir(TempatLahir tempatLahir){
        tableModel.add(tempatLahir);
        textFieldNamaKota.setText("");
    }

}
