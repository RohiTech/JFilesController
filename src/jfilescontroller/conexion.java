package jfilescontroller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 *
 * @author Jose Francisco
 */
//ESTA CLASE CONEXION HEREDA DE MESSAGES Y PERMITE HACER
//USO DIRECTO DE LOS METODOS DE JOPTIONPANE
public class conexion
{
    //VARIABLES QUE UTILIZO PARA LA CONEXION
    public  static Connection conexion;
    private  static Statement statement;
    //NOMBRE DE USUARIO Y PASSWORD POR DEFECTO PARA LA CONEXION SIN PARAMETROS
    private String user="root",password="root",server="localhost";
    private String truncation="false";
    //CONECTED ME DIRA SI ESTA O NO CONECTADO
    private boolean test = false;
    private boolean conected = false;


    public conexion()
    {
        try
        {
            Driver driver=(Driver)Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties property=new Properties();
            property.setProperty("user",user);
            property.setProperty("password",password);
            property.setProperty("jdbcCompliantTruncation",truncation);
            conexion =DriverManager.getConnection("jdbc:mysql://" + server + ":3306/bd_files_controller", property);
            statement=conexion.createStatement();
            test = true;
            conected = true;
            //System.out.println("Conexion satisfactoria");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public boolean getConected()
    {
        return this.conected;
    }

    public static Connection getConnection()
    {
        return conexion;
    }

    public static  Statement getStatement()
    {
        return statement;
    }

    //EL METODO CLOSE CIERRA LA CONEXION MYSQL
    public void close()throws SQLException
    {
        if(conexion!=null)
        {
            statement.close();
            conexion.close();
            conected = false;
        }
    }
}