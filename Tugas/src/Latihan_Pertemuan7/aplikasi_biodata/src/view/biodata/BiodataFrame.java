package view.biodata;

import javax.swing.*;
import java.util.*;
import model.*;
import dao.BiodataDao;
import dao.TempatLahirDao;

public class BiodataFrame extends JFrame {
    private List<TempatLahir> tempatLahirList;
    private List<model.Biodata> biodataList;
    private JTextField textFieldNama, textFieldTanggalLahir, textFieldAlamat, textFieldUmur;
    private JRadioButton rbPria, rbWanita;
    private JComboBox<String> comboTempat;
    private BiodataTableModel tableModel;
    private BiodataDao biodataDao;
    private TempatLahirDao tempatLahirDao;
    private JButton btnSave, btnUpdate, btnDelete;
    private JTable table;

    public BiodataFrame(BiodataDao biodataDao, TempatLahirDao tempatLahirDao) {
        this.biodataDao = biodataDao;
        this.tempatLahirDao = tempatLahirDao;
        this.biodataList = this.biodataDao.findAll();
        this.tempatLahirList = this.tempatLahirDao.findAll();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Aplikasi Biodata");
        this.setLayout(null);
        this.setSize(600, 500);

        // Nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 15, 100, 25);
        this.add(labelNama);
        textFieldNama = new JTextField();
        textFieldNama.setBounds(150, 15, 200, 25);
        this.add(textFieldNama);

        // Tempat Lahir
        JLabel labelTempat = new JLabel("Tempat Lahir:");
        labelTempat.setBounds(15, 45, 100, 25);
        this.add(labelTempat);
        comboTempat = new JComboBox<>();
        comboTempat.setBounds(150, 45, 200, 25);
        populateComboTempat();
        this.add(comboTempat);

        // Tanggal Lahir
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        labelTanggalLahir.setBounds(15, 75, 100, 25);
        this.add(labelTanggalLahir);
        textFieldTanggalLahir = new JTextField();
        textFieldTanggalLahir.setBounds(150, 75, 200, 25);
        this.add(textFieldTanggalLahir);

        // Alamat
        JLabel labelAlamat = new JLabel("Alamat:");
        labelAlamat.setBounds(15, 105, 100, 25);
        this.add(labelAlamat);
        textFieldAlamat = new JTextField();
        textFieldAlamat.setBounds(150, 105, 200, 25);
        this.add(textFieldAlamat);

        // Umur
        JLabel labelUmur = new JLabel("Umur:");
        labelUmur.setBounds(15, 135, 100, 25);
        this.add(labelUmur);
        textFieldUmur = new JTextField();
        textFieldUmur.setBounds(150, 135, 200, 25);
        this.add(textFieldUmur);

        // Jenis Kelamin
        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        labelJenisKelamin.setBounds(15, 165, 100, 25);
        this.add(labelJenisKelamin);
        rbPria = new JRadioButton("Pria");
        rbWanita = new JRadioButton("Wanita");
        rbPria.setBounds(150, 165, 70, 25);
        rbWanita.setBounds(230, 165, 70, 25);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbPria);
        genderGroup.add(rbWanita);
        this.add(rbPria);
        this.add(rbWanita);

        // Tabel untuk menampilkan data
        tableModel = new BiodataTableModel(biodataList);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 250, 550, 150);
        this.add(scrollPane);

        // Tombol Simpan, Update, dan Delete
        btnSave = new JButton("Simpan");
        btnSave.setBounds(15, 210, 80, 30);
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(100, 210, 80, 30);
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(185, 210, 80, 30);

        this.add(btnSave);
        this.add(btnUpdate);
        this.add(btnDelete);

        // Listener untuk tombol
        BiodataButtonSimpanActionListener actionListener = new BiodataButtonSimpanActionListener(this, biodataDao);
        btnSave.addActionListener(actionListener);
        btnUpdate.addActionListener(actionListener);
        btnDelete.addActionListener(actionListener);
    }

    public void populateComboTempat() {
        comboTempat.removeAllItems();
        for (TempatLahir tempatLahir : this.tempatLahirList) {
            comboTempat.addItem(tempatLahir.getNamaKota());
        }
    }

    // Getter untuk mengambil nilai input
    public String getNama() { return textFieldNama.getText(); }
    public TempatLahir getTempatLahir() { return tempatLahirList.get(comboTempat.getSelectedIndex()); }
    public String getTanggalLahir() { return textFieldTanggalLahir.getText(); }
    public String getAlamat() { return textFieldAlamat.getText(); }
    public String getUmur() { return textFieldUmur.getText(); }
    public String getJenisKelamin() { return rbPria.isSelected() ? "Pria" : "Wanita"; }

    public void addBiodata(model.Biodata biodata) {
        tableModel.add(biodata);
        textFieldNama.setText("");
        textFieldTanggalLahir.setText("");
        textFieldAlamat.setText("");
        textFieldUmur.setText("");
        rbPria.setSelected(false);
        rbWanita.setSelected(false);
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
