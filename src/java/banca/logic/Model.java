
package banca.logic;

import banca.data.Dao;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {
    Dao base;
    public Model() {
      base = new Dao();
  }
   private static Model uniqueInstance;
   
   
   public static Model instance(){
       if (uniqueInstance == null) {
           uniqueInstance = new Model();
       }
       return uniqueInstance;
   }
   
    public Usuario usuarioFind(String cedula,String clave) {
        Usuario u;
        try {
            u = base.GetUsuario(cedula);
            if (clave.equals(u.getContraseña())) {
                return u;
            }else{
             return null;
            }
            
        } catch (Exception ex) {
            return null;
        }
       
    }

//    public Usuario clienteFind(Usuario usuario) throws Exception{
//        if (clientes.get(usuario.getCedula())!=null) return clientes.get(usuario.getCedula());
//        else throw new Exception("Cliente no existe");
//    } 
}
