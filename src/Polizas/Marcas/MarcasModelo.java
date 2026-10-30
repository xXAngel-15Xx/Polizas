package Polizas.Marcas;

import Polizas.General.Conexion;
import Polizas.General.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcasModelo extends Conexion implements DAO {
    public MarcasModelo() {
        super();
    }

    @Override
    public List<Marca> read() {
        List<Marca> lista = new ArrayList<>();
        try {
            this.conectar();
            ps = cnx.prepareStatement("SELECT idMarca, nombreMarca FROM marca");
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Marca(rs.getInt("idMarca"), rs.getString("nombreMarca")));
            }
        } catch (SQLException ex) {
            System.out.println("Error en lectura: " + ex.getMessage());
        } finally {
            this.close();
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        int resultado = 0;
        try {
            this.conectar();
            Marca marca = (Marca) obj;
            if (existeNombre(marca.getNombre(), 0)) {
                return -1;
            }
            ps = cnx.prepareStatement("INSERT INTO marca (nombreMarca) VALUES (?)");
            ps.setString(1, marca.getNombre().trim());
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en creación: " + ex.getMessage());
        } finally {
            this.close();
        }
        return resultado;
    }

    @Override
    public int delete(Object obj) {
        int resultado = 0;
        try {
            this.conectar();
            Marca marca = (Marca) obj;
            ps = cnx.prepareStatement("DELETE FROM marca WHERE idMarca = ?");
            ps.setInt(1, marca.getId());
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en eliminación: " + ex.getMessage());
        } finally {
            this.close();
        }
        return resultado;
    }

    @Override
    public int update(Object obj) {
        int resultado = 0;
        try {
            this.conectar();
            Marca marca = (Marca) obj;
            if (existeNombre(marca.getNombre(), marca.getId())) {
                return -1;
            }
            ps = cnx.prepareStatement("UPDATE marca SET nombreMarca = ? WHERE idMarca = ?");
            ps.setString(1, marca.getNombre().trim());
            ps.setInt(2, marca.getId());
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en actualización: " + ex.getMessage());
        } finally {
            this.close();
        }
        return resultado;
    }

    private boolean existeNombre(String nombre, int idExcluir) throws SQLException {
        ps = cnx.prepareStatement(
                "SELECT idMarca FROM marca "
                + "WHERE LOWER(TRIM(nombreMarca)) = LOWER(TRIM(?)) AND idMarca <> ? LIMIT 1");
        ps.setString(1, nombre);
        ps.setInt(2, idExcluir);
        rs = ps.executeQuery();
        return rs.next(); 
    }
}
