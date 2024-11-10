package dao;

import conn.MySqlConnection;
import model.Biodata;
import model.TempatLahir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class BiodataDao {
    public int insert(view.biodata.Biodata biodata){
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();){
            PreparedStatement statement = connection.prepareStatement("insert into biodata (id, nama, tempat_lahir_id, tanggal_lahir, alamat, umur, jenis_kelamin) values (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, biodata.getId());
            statement.setString(2, biodata.getNama());
            statement.setString(3, biodata.getTempatLahir().getId());
            statement.setString(4, biodata.getTanggalLahir());
            statement.setString(5, biodata.getAlamat());
            statement.setString(6, biodata.getUmur());
            statement.setString(7, biodata.getUmur());

            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(view.biodata.Biodata biodata){
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();){
            PreparedStatement statement = connection.prepareStatement("update biodata set nama = ?, tempat_lahir_id = ?, tanggal_lahir = ?, alamat = ?, umur = ?, jenis_kelamin = ? where id = ?");
            statement.setString(1, biodata.getNama());
            statement.setString(2, biodata.getTempatLahir().getId());
            statement.setString(3, biodata.getTanggalLahir());
            statement.setString(4, biodata.getAlamat());
            statement.setString(5, biodata.getUmur());
            statement.setString(6, biodata.getJenisKelamin());
            statement.setString(7, biodata.getId());

            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(view.biodata.Biodata biodata){
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection();){
            PreparedStatement statement = connection.prepareStatement("delete from biodata where id = ?");
            statement.setString(1, biodata.getId());

            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public List<Biodata> findAll(){
        List<Biodata> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();){
            try(ResultSet resultSet = statement.executeQuery("select biodata.id, biodata.nama, tempat_lahir.id tempat_lahir_id, tempat_lahir.nama_kota " + 
            "tempat_lahir_nama_kota from biodata join tempat_lahir on tempat_lahir.id = biodata.tempat_lahir_id");){
                while (resultSet.next()) {
                    Biodata biodata = new Biodata();
                    biodata.setId(resultSet.getString("id"));
                    biodata.setNama(resultSet.getString("nama"));
                    biodata.setTanggalLahir(resultSet.getString("tanggal_lahir"));
                    biodata.setAlamat(resultSet.getString("alamat"));
                    biodata.setUmur(resultSet.getString("umur"));
                    biodata.setJenisKelamin(resultSet.getString("jenis_kelamin"));

                    TempatLahir tempatLahir = new TempatLahir();
                    tempatLahir.setId(resultSet.getString("tempat_lahir_id"));
                    tempatLahir.setNamaKota(resultSet.getString("tempat_lahir_nama_kota"));

                    biodata.setTempatLahir(tempatLahir);
                    
                    list.add(biodata);
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
