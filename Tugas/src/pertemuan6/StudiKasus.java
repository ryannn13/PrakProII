package pertemuan6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudiKasus extends JFrame {
    private JTextField txtNama;
    private JTextArea txtAlamat;
    private JRadioButton rbPria, rbWanita;
    private JRadioButton rbAktif, rbTidakAktif;
    private JComboBox<String> cbTipeMembership;
    private JSpinner spUsia;
    private JSlider slTahunAktif;
    private JTable table;
    private DefaultTableModel tableModel;

    public StudiKasus() {
        setTitle("Form Pendaftaran Membership");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem resetTable = new JMenuItem("Reset");
        JMenuItem exit = new JMenuItem("Exit");

        resetTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(resetTable);
        menu.add(exit);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Panel Utama
        JPanel panel = new JPanel(new BorderLayout());

        // Panel Input
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Nama:"), gbc);
        txtNama = new JTextField();
        gbc.gridx = 1;
        inputPanel.add(txtNama, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Alamat:"), gbc);
        txtAlamat = new JTextArea(3, 20);
        JScrollPane scrollAlamat = new JScrollPane(txtAlamat);
        gbc.gridx = 1;
        gbc.gridheight = 3;
        inputPanel.add(scrollAlamat, gbc);
        gbc.gridheight = 1;

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Jenis Kelamin:"), gbc);
        JPanel genderPanel = new JPanel();
        rbPria = new JRadioButton("Pria");
        rbWanita = new JRadioButton("Wanita");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbPria);
        genderGroup.add(rbWanita);
        genderPanel.add(rbPria);
        genderPanel.add(rbWanita);
        gbc.gridx = 1;
        inputPanel.add(genderPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Keanggotaan:"), gbc);
        JPanel statusPanel = new JPanel();
        rbAktif = new JRadioButton("Aktif");
        rbTidakAktif = new JRadioButton("Tidak Aktif");
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(rbAktif);
        statusGroup.add(rbTidakAktif);
        statusPanel.add(rbAktif);
        statusPanel.add(rbTidakAktif);
        gbc.gridx = 1;
        inputPanel.add(statusPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Tipe Membership:"), gbc);
        cbTipeMembership = new JComboBox<>(new String[]{"Silver", "Gold", "Platinum"});
        gbc.gridx = 1;
        inputPanel.add(cbTipeMembership, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        inputPanel.add(new JLabel("Usia:"), gbc);
        spUsia = new JSpinner(new SpinnerNumberModel(18, 1, 100, 1));
        gbc.gridx = 1;
        inputPanel.add(spUsia, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        inputPanel.add(new JLabel("Tahun Aktif Keanggotaan:"), gbc);
        slTahunAktif = new JSlider(0, 50, 0);
        slTahunAktif.setMajorTickSpacing(5);
        slTahunAktif.setPaintTicks(true);
        slTahunAktif.setPaintLabels(true);
        gbc.gridx = 1;
        inputPanel.add(slTahunAktif, gbc);

        panel.add(inputPanel, BorderLayout.NORTH);

        // Tabel untuk Menampilkan Data
        tableModel = new DefaultTableModel(new String[]{"Nama", "Alamat", "Jenis Kelamin", "Keanggotaan", "Tipe Membership", "Usia", "Tahun Aktif Keanggotaan"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollTable = new JScrollPane(table);
        panel.add(scrollTable, BorderLayout.CENTER);

        // Tombol Simpan
        JButton btnSave = new JButton("Simpan");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tambahData();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSave);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void tambahData() {
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String jenisKelamin = rbPria.isSelected() ? "Pria" : rbWanita.isSelected() ? "Wanita" : "";
        String status = rbAktif.isSelected() ? "Aktif" : rbTidakAktif.isSelected() ? "Tidak Aktif" : "";
        String tipeMembership = (String) cbTipeMembership.getSelectedItem();
        int usia = (Integer) spUsia.getValue();
        int thnAktif = slTahunAktif.getValue();

        tableModel.addRow(new Object[]{nama, alamat, jenisKelamin, status, tipeMembership, usia, thnAktif + " Tahun"});

        txtNama.setText("");
        txtAlamat.setText("");
        rbPria.setSelected(false);
        rbWanita.setSelected(false);
        rbAktif.setSelected(false);
        rbTidakAktif.setSelected(false);
        cbTipeMembership.setSelectedIndex(0);
        spUsia.setValue(18);
        slTahunAktif.setValue(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudiKasus().setVisible(true);
        });
    }
}
