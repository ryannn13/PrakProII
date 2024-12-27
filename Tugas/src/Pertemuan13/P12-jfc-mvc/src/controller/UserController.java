package controller;

import view.UserView;
import javax.swing.*;
import model.User;
import model.UserMapper;
import view.UserPdf;
import view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.apache.ibatis.session.*;

import controller.UserController.AddUserListener;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;
    private SqlSession session;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf, SqlSession session){
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;
        this.session = session;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshUserListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();
            if (!name.isEmpty() && !email.isEmpty()) {
                // Non-blocking operation using SwingWorker
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        User user = new User();
                        user.setName(name);
                        user.setEmail(email);
                        mapper.insertUser (user);
                        session.commit();
                        return null;
                    }

                    @Override
                    protected void done() {
                        JOptionPane.showMessageDialog(view, "User  added successfully!");
                    }
                }.execute();
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new SwingWorker<List<User>, Void>() {
                @Override
                protected List<User> doInBackground() throws Exception {
                    return mapper.getAllUsers();
                }

                @Override
                protected void done() {
                    try {
                        List<User> users = get();
                        String[] userArray = users.stream()
                                .map(u -> u.getName() + " (" + u.getEmail() + ")")
                                .toArray(String[]::new);
                        view.setUserList(userArray);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, "Error fetching users");
                    }
                }
            }.execute();
        }
    }

    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    List<User> users = mapper.getAllUsers();
                    pdf.exportPdf(users);
                    return null;
                }

                @Override
                protected void done() {
                    JOptionPane.showMessageDialog(view, "Export PDF Berhasil!");
                }
            }.execute();
        }
    }
}
