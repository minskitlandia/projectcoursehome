package dao;

import model.User;

public interface AuthDao {
    User login(User user);
    void signup(User user);
}
