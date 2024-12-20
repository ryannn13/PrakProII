package views;

import models.Kos;
import models.KosMapper;
import models.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import controller.KosController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class KosView extends JFrame{
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public KosView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Aplikasi Kos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Alamat", "Harga", "Status"}, 0);
        table = new JTable(tableModel);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton btnAdd = new JButton("Tambah");
        JButton btnUpdate = new JButton("Ubah");
        JButton btnDelete = new JButton("Hapus");

        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        frame.add(panel, BorderLayout.SOUTH);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Input data baru
                JTextField txtNama = new JTextField(20);
                JTextField txtAlamat = new JTextField(20);
                JTextField txtHarga = new JTextField(20);
                String[] statuses = {"kosong", "terisi"};
                JComboBox<String> cbStatus = new JComboBox<>(statuses);

                JPanel inputPanel = new JPanel();
                inputPanel.add(new JLabel("Nama:"));
                inputPanel.add(txtNama);
                inputPanel.add(new JLabel("Alamat:"));
                inputPanel.add(txtAlamat);
                inputPanel.add(new JLabel("Harga:"));
                inputPanel.add(txtHarga);
                inputPanel.add(new JLabel("Status:"));
                inputPanel.add(cbStatus);

                int result = JOptionPane.showConfirmDialog(frame, inputPanel, "Tambah Kos", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Kos kos = new Kos();
                    kos.setNama(txtNama.getText());
                    kos.setAlamat(txtAlamat.getText());
                    kos.setHarga(Double.parseDouble(txtHarga.getText()));
                    kos.setStatus((String) cbStatus.getSelectedItem());

                    KosController controller = new KosController(new KosView());
                    controller.addKos(kos);
                    loadData();
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    String nama = (String) tableModel.getValueAt(selectedRow, 1);
                    String alamat = (String) tableModel.getValueAt(selectedRow, 2);
                    double harga = (double) tableModel.getValueAt(selectedRow, 3);
                    String status = (String) tableModel.getValueAt(selectedRow, 4);

                    // Edit data
                    JTextField txtNama = new JTextField(nama);
                    JTextField txtAlamat = new JTextField(alamat);
                    JTextField txtHarga = new JTextField(String.valueOf(harga));
                    String[] statuses = {"kosong", "terisi"};
                    JComboBox<String> cbStatus = new JComboBox<>(statuses);
                    cbStatus.setSelectedItem(status);

                    JPanel inputPanel = new JPanel();
                    inputPanel.add(new JLabel("Nama:"));
                    inputPanel.add(txtNama);
                    inputPanel.add(new JLabel("Alamat:"));
                    inputPanel.add(txtAlamat);
                    inputPanel.add(new JLabel("Harga:"));
                    inputPanel.add(txtHarga);
                    inputPanel.add(new JLabel("Status:"));
                    inputPanel.add(cbStatus);

                    int result = JOptionPane.showConfirmDialog(frame, inputPanel, "Ubah Kos", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        Kos kos = new Kos();
                        kos.setId(id);
                        kos.setNama(txtNama.getText());
                        kos.setAlamat(txtAlamat.getText());
                        kos.setHarga(Double.parseDouble(txtHarga.getText()));
                        kos.setStatus((String) cbStatus.getSelectedItem());

                        KosController controller = new KosController(new KosView());
                        controller.updateKos(kos);
                        loadData();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Silakan pilih baris yang ingin diubah.");
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin menghapus kos dengan ID " + id + "?", "Hapus Kos", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Menghapus data dari database
                        KosController controller = new KosController(new KosView());
                        controller.deleteKos(id);
                        loadData();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Silakan pilih baris yang ingin dihapus.");
                }
            }
        });

        frame.setVisible(true);
        loadData();
    }

    public void loadData() {
        tableModel.setRowCount(0);
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            KosMapper mapper = session.getMapper(KosMapper.class);
            List<Kos> kosList = mapper.getAllKos();
            for (Kos kos : kosList) {
                tableModel.addRow(new Object[]{kos.getId(), kos.getNama(), kos.getAlamat(), kos.getHarga(), kos.getStatus()});
            }
        }
    }
}
