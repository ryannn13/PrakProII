package dao;

import conn.MySqlConnection;
import model.TempatLahir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;;

public class TempatLahirDao {
    public int insert(TempatLahir tempatLahir){
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();){
            PreparedStatement statement = connection.prepareStatement("insert into tempat_lahir (id, nama_kota) values (?, ?)");
            statement.setString(1, tempatLahir.getId());
            statement.setString(2, tempatLahir.getNamaKota());

            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(TempatLahir tempatLahir){
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();){
            PreparedStatement statement = connection.prepareStatement("update tempat_lahir set nama_kota = ? where id = ?");
            statement.setString(1, tempatLahir.getNamaKota());
            statement.setString(2, tempatLahir.getId());

            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(TempatLahir tempatLahir){
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();){
            PreparedStatement statement = connection.prepareStatement("delete from tempat_lahir where id = ?");
            statement.setString(1, tempatLahir.getId());

            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public List<TempatLahir> findAll(){
        List<TempatLahir> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();){
            try(ResultSet resultSet = statement.executeQuery("select * from tempat_lahir");){
                while (resultSet.next()) {
                    TempatLahir tempatLahir = new TempatLahir();
                    tempatLahir.setId(resultSet.getString("id"));
                    tempatLahir.setNamaKota(resultSet.getString("nama_kota"));
                    list.add(tempatLahir);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
