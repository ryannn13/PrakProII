package view.biodata;

import java.awt.event.*;
import java.util.UUID;
import model.*;
import dao.BiodataDao;

public class BiodataButtonSimpanActionListener implements ActionListener {
    private BiodataFrame biodataFrame;
    private BiodataDao biodataDao;

    public BiodataButtonSimpanActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        String nama = this.biodataFrame.getNama();
        String tanggalLahir = this.biodataFrame.getTanggalLahir();
        String alamat = this.biodataFrame.getAlamat();
        String umur = this.biodataFrame.getUmur();
        String jenisKelamin = this.biodataFrame.getJenisKelamin();

        if (nama.isEmpty() || tanggalLahir.isEmpty() || alamat.isEmpty() || umur.isEmpty()) {
            this.biodataFrame.showAlert("Semua kolom harus diisi");
            return;
        }

        TempatLahir tempatLahir = this.biodataFrame.getTempatLahir();
        Biodata biodata = new Biodata(UUID.randomUUID().toString(), nama, tempatLahir, tanggalLahir, alamat, umur, jenisKelamin);

        try {
            if (actionCommand.equals("Simpan")) {
                biodataDao.insert(biodata);
                biodataFrame.addBiodata(biodata);
                biodataFrame.showAlert("Data berhasil disimpan.");
            } else if (actionCommand.equals("Update")) {
                biodataDao.update(biodata);
                biodataFrame.showAlert("Data berhasil diperbarui.");
            } else if (actionCommand.equals("Delete")) {
                biodataDao.delete(biodata);
                biodataFrame.showAlert("Data berhasil dihapus.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            biodataFrame.showAlert("Terjadi kesalahan saat memproses data.");
        }
    }
}
