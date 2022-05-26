package dao;

import model.Ads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdsDaoImpl implements BaseDao<Ads> {

    private static final String INSERT_ADS = "INSERT INTO Ads(title, subtitle, description, price, user_id) VALUES (?,?,?,?,?);";
    private static final String UPDATE_ADS = "UPDATE Ads SET title = ?, subtitle = ?, description=?, price=? where id = ?;";
    private static final String SELECT_ADS_ID = " SELECT * FROM Ads where id = ?;";
    private static final String DELETE_ADS_ID = " DELETE FROM Ads where id = ?;";
    private static final String SELECT_ALL_ADS = "SELECT * FROM Ads";


    @Override
    public void insert(Ads ads) {
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADS)) {
            preparedStatement.setString(1, ads.getTitle());
            preparedStatement.setString(2, ads.getSubtitle());
            preparedStatement.setString(3, ads.getDescription());
            preparedStatement.setDouble(4, ads.getPrice());
            preparedStatement.setInt(5, ads.getUser_id());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Ads ads) {
        boolean flagUpdate = false;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADS)) {
            preparedStatement.setString(1, ads.getTitle());
            preparedStatement.setString(2, ads.getSubtitle());
            preparedStatement.setString(3, ads.getDescription());
            preparedStatement.setDouble(4, ads.getPrice());
            preparedStatement.setInt(5, ads.getId());
            System.out.println(preparedStatement);
            flagUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flagUpdate;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADS_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Ads read(int id) {
        Ads ads = new Ads();
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADS_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ads = Ads.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .subtitle(resultSet.getString(3))
                        .description(resultSet.getString(4))
                        .price(resultSet.getDouble(5))
                        .user_id(resultSet.getInt(6))
                        .image(resultSet.getString(7))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ads;
    }

    @Override
    public List<Ads> read() {
        List<Ads> adsAll = new ArrayList<>();
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADS)) {
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                adsAll.add(Ads.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .subtitle(resultSet.getString(3))
                        .description(resultSet.getString(4))
                        .price(resultSet.getDouble(5))
                        .user_id(resultSet.getInt(6))
                        .image(resultSet.getString(7))
                        .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adsAll;
    }
}
