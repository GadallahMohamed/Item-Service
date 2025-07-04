package com.item.service.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;

import com.item.model.Item;
import com.item.model.ItemDetails;
import com.item.service.ItemService;
import com.item.service.ItemDetailsService;

public class ItemServiceImpl implements ItemService {

    private final DataSource dataSource;

    public ItemServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean saveItem(Item item) {
        String query = "INSERT INTO item (name, price, total_number) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getTotalNumber());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("Error saving item: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeItem(int id) {
        if (loadItem(id) == null) return false;

        String query = "DELETE FROM item WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("Error removing item: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateItem(Item item) {
        String query = "UPDATE item SET name = ?, price = ?, total_number = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getTotalNumber());
            ps.setInt(4, item.getId());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("Error updating item: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Item loadItem(int id) {
        String query = "SELECT * FROM item WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("total_number")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error loading item: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Item> loadItems() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM item ORDER BY id";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ItemDetailsService detailsService = new ItemDetailsServiceImpl(dataSource);

            while (rs.next()) {
                Item item = new Item(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("total_number")
                );

                // Load item details
                ItemDetails details = detailsService.loadItemDetails(item.getId());
                item.setDetails(details);

                items.add(item);
            }

        } catch (SQLException e) {
            System.err.println("Error loading items: " + e.getMessage());
        }

        return items;
    }
}
