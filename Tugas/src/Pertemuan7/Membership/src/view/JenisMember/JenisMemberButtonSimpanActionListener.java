package view.JenisMember;

import java.awt.event.*;
import java.util.UUID;
import model.JenisMember;
import dao.JenismemberDao;

public class JenisMemberButtonSimpanActionListener implements ActionListener{
    private JenisMemberFrame jenisMemberFrame;
    private JenismemberDao JenismemberDao;

    public JenisMemberButtonSimpanActionListener(JenisMemberFrame jenisMemberFrame, JenismemberDao JenismemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.JenismemberDao = JenismemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String nama = this.jenisMemberFrame.getNama();
        JenisMember jenisMember = new JenisMember();
        jenisMember.setId(UUID.randomUUID().toString());
        jenisMember.setNama(nama);

        this.jenisMemberFrame.addJenisMember(jenisMember);
        this.JenismemberDao.insert(jenisMember);
    }
}