package view.tempatlahir;

import java.awt.event.*;
import java.util.UUID;
import model.TempatLahir;
import dao.TempatLahirDao;

public class TempatLahirButtonSimpanActionListener implements ActionListener {
    private TempatLahirFrame tempatLahirFrame;
    private TempatLahirDao tempatLahirDao;

    public TempatLahirButtonSimpanActionListener(TempatLahirFrame tempatLahirFrame, TempatLahirDao tempatLahirDao){
        this.tempatLahirFrame = tempatLahirFrame;
        this.tempatLahirDao = tempatLahirDao;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String nama = this.tempatLahirFrame.getNamaKota();
        TempatLahir tempatLahir = new TempatLahir();
        tempatLahir.setId(UUID.randomUUID().toString());
        tempatLahir.setNamaKota(nama);

        this.tempatLahirFrame.addTempatLahir(tempatLahir);
        this.tempatLahirDao.insert(tempatLahir);
        this.tempatLahirDao.delete(tempatLahir);
        this.tempatLahirDao.update(tempatLahir);
    }
}
