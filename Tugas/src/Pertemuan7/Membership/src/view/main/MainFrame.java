package view.main;

import java.awt.FlowLayout;
import javax.swing .*;
import view.JenisMember.JenisMemberFrame;
import view.member.MemberFrame;
import dao.*;

public class MainFrame extends JFrame{
    private JenisMemberFrame jenisMemberFrame;
    private MemberFrame memberFrame;
    private JButton buttonJenisMember;
    private JButton buttonMember;
    private JenismemberDao JenismemberDao;
    private MemberDao memberDao;

    public MainFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,500);

        this.JenismemberDao = new JenismemberDao();
        this.memberDao = new MemberDao();

        this.jenisMemberFrame = new JenisMemberFrame(JenismemberDao);
        this.memberFrame = new MemberFrame(memberDao, JenismemberDao);

        this.setLayout(new FlowLayout());
        MainButtonActionListener actionListener = new MainButtonActionListener(this);

        this.buttonJenisMember = new JButton("Jenis Member");
        this.buttonMember = new JButton("Member");

        this.buttonJenisMember. addActionListener(actionListener);
        this.buttonMember. addActionListener(actionListener);

        this.add(buttonJenisMember);
        this.add(buttonMember);
    }
    public JButton getButtonJenisMember(){
        return buttonJenisMember;
    }
    public JButton getButtonMember(){
        return buttonMember;
    }
    public void showJenisMemberFrame() {
        if(jenisMemberFrame == null){
            jenisMemberFrame = new JenisMemberFrame(JenismemberDao);
        }
        jenisMemberFrame.setVisible(true);
    }
    public void showMemberFrame() {
        if(memberFrame == null){
            memberFrame = new MemberFrame(memberDao, JenismemberDao);
        }
        memberFrame.populatedComboJenis();
        memberFrame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame f = new MainFrame();
                f.setVisible(true);
            }
        });
    }
}