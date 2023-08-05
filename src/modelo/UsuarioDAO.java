/*
 * 
 */
package modelo;

import java.sql.*;
import java.util.*;


/**
 *
 * @author Alumno
 */
public class UsuarioDAO {
    PreparedStatement ps;           //preparar las sentencias a ejecutar
    ResultSet rs;                   //leer resultados del select
    conexion cn = new conexion();   //parametros de conexion
    Usuario u;                     //crear usuario
    
    Connection con() throws SQLException, ClassNotFoundException
    {
        Class.forName(cn.getDriver());
        return DriverManager.getConnection(cn.getUrl(), cn.getUsuario(), cn.getPass());
    }
    
    public ArrayList<Usuario> mostrar()
    {
        ArrayList<Usuario> al = new ArrayList<Usuario>();
        try
        {
            ps = con().prepareStatement("select * from usuario");
            rs = ps.executeQuery();
            while(rs.next())
            {
                u = new Usuario (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7));
                al.add(u);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
                ps.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return al;
    }
    
    public int insertar(Usuario p)
    {
        int n=0;
        try
        {
            ps = con().prepareStatement("insert into Usuario(nombre, apellido, edad, genero, correo, telefono) values(?, ?, ?, ?, ?, ?)");
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setInt(3, p.getEdad());
            ps.setString(4,p.getGenero());
            ps.setString(5, p.getCorreo());
            ps.setString(6, p.getTelefono());
            
            
            n = ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                ps.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return n;
    }   
    
}