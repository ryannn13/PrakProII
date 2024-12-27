package controller;
import view.UserView;
import javax.swing.*;
import model.User;
import model.UserMapper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController {
    private UserView view;
    private UserMapper mapper;

    public UserController(UserView view, UserMapper mapper){
        this.view = view;
        this.mapper = mapper;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshUserListener(new RefreshListener());
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
}
