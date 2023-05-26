package daos;


import entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        try {
            connection = H2Connection.getConnetion();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO ODONTOLOGO (MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, odontologo.getNumeroMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.execute();

            ResultSet key = ps.getGeneratedKeys();
            while (key.next()) {
                int id = key.getInt(1);
                odontologo.setId(id);
            }


            connection.commit();
            LOGGER.info("Odontologo Registrado: " + odontologo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Error 404");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }


        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {


        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            connection = H2Connection.getConnetion();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Odontologo odontologo = crearObjetoOdontologo(rs);
                odontologos.add(odontologo);
            }
            LOGGER.info("Listado de todos los Odotologos: " + odontologos);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Error 404");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }

        return odontologos;
    }

    private Odontologo crearObjetoOdontologo(ResultSet rs) throws SQLException {
        {
            int id = rs.getInt(1);
            int numeroMatricula = rs.getInt(2);
            String nombre = rs.getString(3);
            String apellido = rs.getString(4);
            return new Odontologo(id, numeroMatricula, nombre, apellido);
        }
    }
}