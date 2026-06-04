package Ejercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PersonaDao {

    private Connection obtenerConexion() {
        // Utilice aquí su motor de BD preferido
        // Ejemplo:
        // return DriverManager.getConnection(
        //     "jdbc:mysql://localhost:3306/tu_bd",
        //     "root",
        //     ""
        // );

        throw new RuntimeException("Implementar conexión");
    }

    public Persona personaPorId(int id) {
        String sql = "select nombre from personas where id = ?";

        try (
                Connection conn = obtenerConexion();
                PreparedStatement statement = conn.prepareStatement(sql)
        ) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                throw new RuntimeException("No existe persona con id: " + id);
            }

            String nombre = result.getString("nombre");

            return new Persona(
                    id,
                    nombre,
                    new TelefonosProxy(id)
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private class TelefonosProxy extends AbstractSet<Telefono> {

        private int idPersona;
        private Set<Telefono> telefonosReales;

        public TelefonosProxy(int idPersona) {
            this.idPersona = idPersona;
        }

        private void cargarTelefonosSiHaceFalta() {
            if (telefonosReales == null) {
                telefonosReales = new HashSet<>();

                String sql = "select numero from telefonos where idPersona = ?";

                try (
                        Connection conn = obtenerConexion();
                        PreparedStatement statement = conn.prepareStatement(sql)
                ) {
                    statement.setInt(1, idPersona);

                    ResultSet result = statement.executeQuery();

                    while (result.next()) {
                        telefonosReales.add(
                                new Telefono(result.getString("numero"))
                        );
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public Iterator<Telefono> iterator() {
            cargarTelefonosSiHaceFalta();
            return telefonosReales.iterator();
        }

        @Override
        public int size() {
            cargarTelefonosSiHaceFalta();
            return telefonosReales.size();
        }
    }
}