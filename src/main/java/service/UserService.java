package service;

import entity.User;
import lombok.AllArgsConstructor;
import repository.IUserRepository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class UserService implements IUserService {
    private IUserRepository repository;

    @Override
    public List<User> findAll() {
        try {
            return repository.findAll();
        } catch (SQLException exception) {
            return Collections.emptyList();
        }
    }

    @Override
    public User findById(int id) {
        try {
            return repository.findById(id);
        } catch (SQLException exception) {
            return null;
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        try {
            return repository.findByEmailAndPassword(email, password);
        } catch (SQLException exception) {
            return null;
        }
    }

    @Override
    public int create(String fullName, String email) {
        try {
            return repository.create(fullName, email);
        } catch (SQLException exception) {
            return 0;
        }
    }

    @Override
    public int deleteById(int id) {
        try {
            return repository.deleteById(id);
        } catch (SQLException exception) {
            return 0;
        }
    }
}