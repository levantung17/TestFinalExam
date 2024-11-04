package repository;

import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {
    List<User> findAll() throws SQLException;
    User findById(int id) throws SQLException;
    User findByEmailAndPassword(String email, String password) throws SQLException;
    int create(String fullName, String email) throws SQLException;
    int deleteById(int id) throws SQLException;
}