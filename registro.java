import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import static java.sql.DriverManager.drivers;
import static java.sql.DriverManager.getConnection;

public class registro {
    private JTextField cod;
    private JTextField id;
    private JTextField name;
    private JTextField fecha_ncm;
    private JComboBox zodiac;
    private JButton buscarCod;
    private JButton buscarNom;
    private JButton buscarSig;
    private JButton borrar;
    private JButton actualizar;
    private JButton ingresar;
    private JButton clear;
    private JPanel regis;

    public registro() {



    ingresar.addActionListener(new ActionListener() {

        static final String DB_URL = "jdbc:mysql://localhost/POO1";
        //cadena de conexion
        static final String user = "root";
        //usuario
        static final String pass= "root_bas3";
        //paswword
        static final String query = "SELECT * FROM REGISTRO ";
        @Override
        public void actionPerformed(ActionEvent e) {
            String codigo = cod.getText();
            String cedul = id.getText();
            String nombre= name.getText();
            String fechaN = fecha_ncm.getText();
            String signo= (String) zodiac.getSelectedItem();

            try(Connection conn = DriverManager.getConnection(DB_URL,user, pass)){
                String sql = "INSERT INTO REGISTRO (COD,CEDULA,NOMBRE,FECHA_DE_NACIMIENTO,SIGNO) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, codigo); // Obtener valor desde JTextField
                pstmt.setString(2, cedul); // Obtener valor desde JTextField
                pstmt.setString(3, nombre); // Obtener valor desde JTextField
                pstmt.setString(4, fechaN); // Obtener valor desde JTextField
                pstmt.setString(5, signo); // Obtener valor desde JTextField

                int filasAfectadas = pstmt.executeUpdate();
                System.out.println("Se han insertado " + filasAfectadas + " filas.");
                pstmt.close();



            } catch (SQLException e1){
                e1.printStackTrace();

            };




        }
    });
    actualizar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {


        }
    });
    borrar.addActionListener(new ActionListener() {
        static final String DB_URL = "jdbc:mysql://localhost/POO1";
        //cadena de conexion
        static final String user = "root";
        //usuario
        static final String pass= "root_bas3";
        //paswword
        static final String query = "SELECT * FROM REGISTRO ";
        @Override
        public void actionPerformed(ActionEvent e) {
            String codigo = cod.getText();
            String cedul = id.getText();
            String nombre= name.getText();
            String fechaN = fecha_ncm.getText();
            String signo= (String) zodiac.getSelectedItem();

            try(Connection conn = getConnection(DB_URL,user, pass)){
                String sql = "DELETE FROM REGISTRO WHERE COD = " + codigo;
                PreparedStatement pstmt = conn.prepareStatement(sql);
                String COD = cod.getText();

                int filasAfectadas = pstmt.executeUpdate();
                System.out.println("Se ha ELIMINADO " + filasAfectadas + " filas.");
                pstmt.close();



            } catch (SQLException e1){
                e1.printStackTrace();

            };
        }
    });

    clear.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
    buscarCod.addPropertyChangeListener(new PropertyChangeListener() {
        static final String DB_URL = "jdbc:mysql://localhost/POO1";
        //cadena de conexion
        static final String user = "root";
        //usuario
        static final String pass= "root_bas3";
        //paswword
        static final String query = "SELECT * FROM REGISTRO ";
        @Override
        public void propertyChange(PropertyChangeEvent evt) {

            String codigo = cod.getText();
            String cedul = id.getText();
            String nombre= name.getText();
            String fechaN = fecha_ncm.getText();
            String signo= (String) zodiac.getSelectedItem();

             try(Connection conn = DriverManager.getConnection(DB_URL,user, pass)) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    System.out.println("Código:" + rs.getInt("Código"));
                    cod.setText(rs.getString("Código"));
                    System.out.println("Cédula:" + rs.getString("Cédula"));
                    id.setText(rs.getString("Cédula"));
                    System.out.println("Nombre:" + rs.getInt("Nombre"));
                    name.setText(rs.getString("Nombre"));
                    System.out.println("Fecha de nacimiento:" + rs.getString("Fecha de nacimiento"));
                    fecha_ncm.setText(rs.getString("Fecha de nacimiento"));
                    System.out.println("Signo del zodiaco:" + rs.getInt("Signo del zodiaco"));
                    zodiac.setText(rs.getString("Signo del zodiaco"));

                    System.out.println("-------------------");
                    new registro.GuardarDatosEnArchivo();
                }
            }
            catch (SQLException e1){
                throw new RuntimeException(e1);

            };

        }
    });
    buscarNom.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
    buscarSig.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });


        zodiac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
    }
    public static void main(String[] args){
        JFrame frame = new JFrame("Registro");
        frame.setContentPane(new registro().regis);
        frame.setBounds(750,300,1000,1050);
        //   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public class GuardarDatosEnArchivo {
        String contenido = "Estos son los datos que quiero guardar en el archivo.";

        // Ruta del archivo donde se guardarán los datos
        String rutaArchivo = "datos.txt";

            try {
            // Crear un objeto FileWriter con la ruta del archivo
            FileWriter escritor = null;
            try {
                escritor = new FileWriter(rutaArchivo);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Escribir el contenido en el archivo
            try {
                escritor.write(contenido);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Cerrar el FileWriter
            try {
                escritor.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            System.out.println("Datos guardados correctamente en el archivo.");
        } catch (
        public IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }
}
