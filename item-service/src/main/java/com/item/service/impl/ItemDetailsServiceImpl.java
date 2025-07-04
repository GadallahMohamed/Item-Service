package com.item.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.item.model.ItemDetails;
import com.item.service.ItemDetailsService;

public class ItemDetailsServiceImpl implements ItemDetailsService {

    private final DataSource dataSource;

    public ItemDetailsServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean saveItemDetails(ItemDetails itemDetails) {
        String query = "INSERT INTO item_details (description, itemId) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, itemDetails.getDescription());
            ps.setInt(2, itemDetails.getItemId());

            int result = ps.executeUpdate();
            return result == 1;

        } catch (SQLException e) {
            System.err.println("Error saving item details: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ItemDetails loadItemDetails(int itemId) {
        String query = "SELECT * FROM item_details WHERE itemId = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, itemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ItemDetails(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getInt("itemId")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error loading item details: " + e.getMessage());
        }

        return null;
    }
}
