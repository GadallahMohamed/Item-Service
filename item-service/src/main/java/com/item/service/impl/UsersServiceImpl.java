package com.item.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.item.model.Users;
import com.item.service.UsersService;

public class UsersServiceImpl implements UsersService{

	private final DataSource dataSource;
    public UsersServiceImpl(DataSource ds) { this.dataSource = ds; }

    /* CREATE */
    @Override
    public boolean saveUser(Users u) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?,?,?)";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmial());
            ps.setString(3, u.getPassword());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    /* UPDATE */
    @Override
    public boolean updateUser(Users u) {
        String sql = "UPDATE users SET username=?, email=?, password=? WHERE id=?";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmial());
            ps.setString(3, u.getPassword());
            ps.setInt(4, u.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    /* DELETE */
    @Override
    public boolean removeUser(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    /* LOGIN */
    @Override
    public Users authenticate(String email, String pwd) {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, pwd);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return new Users(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

}
