/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jfilescontroller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jose Francisco
 */
public class ExecuteProcedure
{
    static conexion conex;
    static java.sql.Connection con;
    static java.sql.Statement stament;
    static java.sql.CallableStatement callableStament;
    static java.sql.PreparedStatement preparedStament;
    static java.sql.ResultSet rs;
    static java.sql.ResultSet rs1;

    public ExecuteProcedure(conexion conex)
    {
        this.conex = conex;
        con = conexion.getConnection();
        stament = conexion.getStatement();
    }

    public static void executeProcedure(ProcedureEnum enu, String... var)
    {
        try
        {
            callableStament = con.prepareCall(enu.getProcedure());
            int i = 0;

            for(String ar : var)
            {
               callableStament.setString(++i, ar);
            }
            callableStament.execute();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(ExecuteProcedure.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static java.sql.ResultSet ExecuteQuery(String query)
    {
        try
        {
            rs = stament.executeQuery(query);
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ExecuteProcedure.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public static String getValue(String sql)
    {
        String value = "";
        try
        {
            if (stament.execute(sql))
            {
                rs = stament.executeQuery(sql);

                while (rs.next())
                {
                    value = rs.getString(1);
                }
            }
            rs.close();
            return value;
        }
        catch(java.sql.SQLException e)
        {
            return null;
        }
    }

    public static boolean executeSelectExist(String sql, String... var)
    {
        try
        {
            preparedStament = con.prepareCall(sql);
            int i = 0;
            for (String ar: var)
            {
                preparedStament.setString(++i, ar);
            }
            rs = preparedStament.executeQuery();
            return (rs.next()) ? true : false;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ExecuteProcedure.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean executeSelectExist(String sql)
    {
        try
        {
            rs = stament.executeQuery(sql);
            return (rs.next()) ? true : false;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ExecuteProcedure.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //Estos metodos son nuevos

    public static boolean executeDelete(String sql)
    {
        try
        {
            int numeroDML = stament.executeUpdate(sql);
            return true;
        }
        catch(SQLException ex)
        {
            //Logger.getLogger(ExecuteProcedure.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static String[] getArrayItems(String sql)
    {
        String[] ArrayItems = null;

        try
        {
            rs = ExecuteQuery(sql);

            int i = 0;
            Map<String, String> map = new HashMap<String, String>();
            while (rs.next())
            {
                map.put(String.valueOf(i), rs.getString(1));
                i++;
            }
            rs.close();
            ArrayItems = new String[map.size()];
            for (int j = 0; j < map.size(); j++)
            {
                ArrayItems[j] = map.get(String.valueOf(j));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ExecuteProcedure.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArrayItems;
    }

    public static void FillRowData(JTable tbl, String eti, int cant)
    {
        int rows = tbl.getRowCount();
        int colums = tbl.getColumnCount();

        executeProcedure(ProcedureEnum.INGRESAR_UNIDAD, eti, String.valueOf(cant));

        for(int i = 0; i < rows; i++)
        {
            executeProcedure(ProcedureEnum.INGRESAR_DETALLE_UNIDAD, (String)tbl.getValueAt(i, 0), (String)tbl.getValueAt(i, 1),eti);
        }
    }

    public static JTable addRowData(JTable tbl,String query)
    {
        int c = 0;

            try
            {
                rs = ExecuteQuery(query);
                int currentRow = 0;

                DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();

                while(rs.next())
                {
                    Vector fila = new Vector();
                    dtm.addRow(fila);
                    tbl.setModel(dtm);

                    tbl.setValueAt(rs.getString(1), currentRow, 0);
                    tbl.setValueAt(rs.getString(2), currentRow, 1);
                    tbl.setValueAt(rs.getString(3), currentRow, 2);
                    tbl.setValueAt(rs.getString(4), currentRow, 3);

                    currentRow++;
                }
                rs.close();
            }
            catch(SQLException ex)
            {
                Logger.getLogger(ExecuteProcedure.class.getName()).log(Level.SEVERE, null, ex);
            }
          return tbl;
      }

    public String Mostrar_Cantidad_Archivos(int r)
    {
        String res = "";
        try
        {
            String q = "select * from unidad where Etiqueta = '" + String.valueOf(Principal.jTable2.getValueAt(r, 3)) + "';";
            rs = ExecuteQuery(q);
            rs.next();
            res = rs.getString(2);
            //System.out.println(res);
        }
        catch(Exception e)
        {
        }
        return res;
    }

    public static void Borrar_Archivo(String archivo, String unidad)
    {
        try
        {
            String q = "update unidad set Cantidad_Archivos = Cantidad_Archivos - 1 where Etiqueta = '" + unidad + "';";
            int n = stament.executeUpdate(q);
            q = "delete from detalle_unidad where Nombre = '" + archivo + "';";
            n = stament.executeUpdate(q);
            JOptionPane.showMessageDialog(null, "El archivo fue borrado satisfactoriamente","JFilesController",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
        }
    }

    public static void Borrar_Unidad(String unidad)
    {
        try
        {
            String q = "delete from detalle_unidad where Etiqueta = '" + unidad + "';";
            int n = stament.executeUpdate(q);
            q = "delete from unidad where Etiqueta = '" + unidad + "';";
            n = stament.executeUpdate(q);
            JOptionPane.showMessageDialog(null, "La unidad fue borrada satisfactoriamente","JFilesController",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
        }
    }

    public static void Borrar_Todo()
    {
        try
        {
            String q = "delete from detalle_unidad;";
            int n = stament.executeUpdate(q);
            q = "delete from unidad;";
            n = stament.executeUpdate(q);
            JOptionPane.showMessageDialog(null, "Los datos fueron borrados satisfactoriamente","JFilesController",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
        }
    }

    public static void Respaldar_BD(String dir)
    {
        try
        {
            //String q = "mysqldump --opt --password = sa --user = root bd_files_controller > " + dir + ".sql";
            //int n = stament.executeUpdate(q);
            //JOptionPane.showMessageDialog(null, "La copia de seguridad se ha realizado satisfactoriamente","JFilesController",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}