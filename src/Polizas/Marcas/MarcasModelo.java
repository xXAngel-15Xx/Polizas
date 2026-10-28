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
            ps = cnx.prepareStatement("INSERT INTO marca (nombreMarca) VALUES (?)");
            ps.setString(1, marca.getNombre());
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
        return 0;
    }

    @Override
    public int update(Object lista) {
        return 0;
    }
}