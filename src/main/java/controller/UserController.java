package controller;

import entity.User;
import lombok.AllArgsConstructor;
import service.IUserService;

import java.util.List;

@AllArgsConstructor
public class UserController {
    private final IUserService service;

    public List<User> findAll() {
        return service.findAll();
    }

    public User findById(int id) {
        return service.findById(id);
    }

    public User findByEmailAndPassword(String email, String password) {
        return service.findByEmailAndPassword(email, password);
    }

    public int create(String fullName, String email) {
        validateUser(fullName); // Kiểm tra tính hợp lệ của tên người dùng
        return service.create(fullName, email);
    }

    public int deleteById(int id) {
        return service.deleteById(id);
    }

    public void saveUser(User user) {
        validateUser(user.getFullName()); // Kiểm tra tính hợp lệ của tên người dùng
        service.create(user.getFullName(), user.getEmail());
    }

    public void deleteUser(int id) {
        service.deleteById(id);
    }

    public List<User> getAllUsers() {
        return service.findAll();
    }

    private void validateUser(String fullName) {
        if (fullName == null) {
            throw new IllegalArgumentException("User name cannot be null");
        }
    }
}
