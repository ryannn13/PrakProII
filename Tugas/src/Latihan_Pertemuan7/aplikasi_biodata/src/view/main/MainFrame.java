package view.main;

import java.awt.FlowLayout;
import javax.swing.*;
import view.tempatlahir.TempatLahirFrame;
import view.biodata.BiodataFrame;
import dao.*;

public class MainFrame extends JFrame{
    private TempatLahirFrame tempatLahirFrame;
    private BiodataFrame biodataFrame;
    private JButton buttonTempatLahir;
    private JButton buttonBiodata;
    private TempatLahirDao tempatLahirDao;
    private BiodataDao biodataDao;

    public MainFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);

        this.tempatLahirDao = new TempatLahirDao();
        this.biodataDao = new BiodataDao();

        this.tempatLahirFrame = new TempatLahirFrame(tempatLahirDao);
        this.biodataFrame = new BiodataFrame(biodataDao, tempatLahirDao);

        this.setLayout(new FlowLayout());
        MainButtonActionListener actionListener = new MainButtonActionListener(this);

        this.buttonTempatLahir = new JButton("Tempat Lahir");
        this.buttonBiodata = new JButton("Biodata");

        this.buttonTempatLahir.addActionListener(actionListener);
        this.buttonBiodata.addActionListener(actionListener);

        this.add(buttonTempatLahir);
        this.add(buttonTempatLahir);
    }

    public JButton getButtonTempatLahir(){
        return buttonTempatLahir;
    }

    public JButton getButtonBiodata(){
        return buttonBiodata;
    }

    public void showTempatLahirFrame(){
        if (tempatLahirFrame == null) {
            tempatLahirFrame = new TempatLahirFrame(tempatLahirDao);
        }
        tempatLahirFrame.setVisible(true);
    }

    public void showBiodataFrame(){
        if (biodataFrame == null) {
            biodataFrame = new BiodataFrame(biodataDao, tempatLahirDao);
        }
        biodataFrame.populateComboTempat();
        biodataFrame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                MainFrame f = new MainFrame();
                f.setVisible(true);
            }
        });
    }
}
