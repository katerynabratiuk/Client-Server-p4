package com.github.katerynabratiuk.repository.implementation;

import com.github.katerynabratiuk.entity.Category;
import com.github.katerynabratiuk.entity.Product;
import com.github.katerynabratiuk.repository.DbConnection;
import com.github.katerynabratiuk.repository.ProductRepository;
import com.github.katerynabratiuk.repository.criteria.ProductSearchCriteria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public Product create(Product product) {

        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO product(name, quantity, price, id_category) VALUES (?,?,?, ?)");
            pst.setString(1, product.getName());
            pst.setInt(2, product.getQuantity());
            pst.setBigDecimal(3, product.getPrice());
            pst.setInt(4, product.getCategory().getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void delete(Integer productId) {

        try(Connection connection = DbConnection.getConnection()) {

            PreparedStatement pst = connection.prepareStatement("DELETE FROM product WHERE id_product=?");
            pst.setInt(1, productId);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Product product) {

        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement pst = connection.prepareStatement("UPDATE product SET name = ?, quantity = ?, price = ?, id_category = ? WHERE id_product=?");
            pst.setString(1, product.getName());
            pst.setInt(2, product.getQuantity());
            pst.setBigDecimal(3, product.getPrice());
            pst.setInt(4, product.getCategory().getId());
            pst.setInt(5, product.getId());

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Product get(Integer productId) {
        try(Connection connection = DbConnection.getConnection()) {

            PreparedStatement pst = connection.prepareStatement("SELECT FROM product WHERE id_product=?");
            pst.setInt(1, productId);
            ResultSet rs = pst.executeQuery();

            return extractProductFromResultSet(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {

        try (Connection connection = DbConnection.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM Product");

            List<Product> res = new ArrayList<>();

            while(resultSet.next())
            {
                Product current = extractProductFromResultSet(resultSet);
                res.add(current);
            }
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("id_product"),
                rs.getString("name"),
                rs.getInt("quantity"),
                rs.getBigDecimal("price"),
                new Category(rs.getInt("id_category")));
    }


    @Override
    public List<Product> findByCriteria(ProductSearchCriteria criteria) {
        return null;
    }
}
