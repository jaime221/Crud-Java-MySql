/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;
import java.util.*;
/**
 *
 * @author root
 */
public class ProductoDAO {
    PreparedStatement ps;           //preparar las sentencias a ejecutar
    ResultSet rs;                   //leer resultados del select
    conexion cn = new conexion();   //parametros de conexion
    Producto p;                     //crear productos
    
    Connection con() throws SQLException, ClassNotFoundException
    {
        Class.forName(cn.getDriver());
        return DriverManager.getConnection(cn.getUrl(), cn.getUsuario(), cn.getPass());
    }
    
    public ArrayList<Producto> mostrar()
    {
        ArrayList<Producto> al = new ArrayList<Producto>();
        try
        {
            ps = con().prepareStatement("select * from productos");
            rs = ps.executeQuery();
            while(rs.next())
            {
                p = new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
                al.add(p);
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
    
    public int insertar(Producto p)
    {
        int n=0;
        try
        {
            ps = con().prepareStatement("insert into productos(nombre, precio, cantidad) values(?, ?, ?)");
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            
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
    
    public int modificar(Producto p)
    {
        int n=0;
        try
        {
            ps = con().prepareStatement("update productos set nombre = ?, precio = ?, cantidad = ? where id = ?");
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getId());
            
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
    
    public int eliminar(Producto p)
    {
        int n=0;
        try
        {
            ps = con().prepareStatement("delete from productos where id = ?");
            ps.setInt(1, p.getId());
            
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
