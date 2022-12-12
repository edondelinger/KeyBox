package code.sio.keybox.model;

import java.sql.*;
import java.util.ArrayList;

public class KeyDAO{
    static final String DB_URL = "jdbc:mysql://a changer";
    static final String USER = "à changer";
    static final String PASS = "a changer";

    public KeyDAO(){

    }

    public ArrayList<Key> getAllKeys(){
        ArrayList<Key> lesCles = new ArrayList<Key>();
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from cle");) {
            // Extract data from result set
            while (rs.next()) {
                Key cleCourante = new Key();
                cleCourante.setNumero(rs.getInt("numero"));
                cleCourante.setLibelle(rs.getString("libelle"));
                cleCourante.setCouleur(rs.getString("couleur"));
                lesCles.add(cleCourante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesCles;
    }

    public Key getAKey(int id){
        Key laCle = null;
        // Open a connection
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from cle where numero = " + id);
            // Extract data from result set
            if (rs.next()) {
                laCle = new Key();
                laCle.setNumero(rs.getInt("numero"));
                laCle.setLibelle(rs.getString("libelle"));
                laCle.setCouleur(rs.getString("couleur"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laCle;
    }

    public boolean updateKey(int id, Key laCle){
        String SQL = "update cle set numero = ?,libelle = ?, couleur = ? where numero = ?";
        // Open a connection
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, laCle.getNumero());
            pstmt.setString(2, laCle.getLibelle());
            pstmt.setString(3, laCle.getCouleur());
            pstmt.setInt(4, id);
            pstmt.execute();
            // Extract data from result set

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createKey(Key laCle){
        String SQL = "insert into cle values(?,?,?)";
        // Open a connection
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, laCle.getNumero());
            pstmt.setString(2, laCle.getLibelle());
            pstmt.setString(3, laCle.getCouleur());
            pstmt.execute();
            // Extract data from result set

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteKey(int id){
        String SQL = "delete from cle where numero = ?";
        // Open a connection
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, id);
            pstmt.execute();
            // Extract data from result set

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}