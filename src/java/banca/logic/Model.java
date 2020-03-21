package banca.logic;
import banca.data.Dao;
import java.util.ArrayList;
import java.util.List;
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
            }else if(u!=null){
              u.setContraseña("");
              return u;
            } else {
                return null;
            }
            
        } catch (Exception ex) {
            return null;
        }
       
    }

    public List<Cuenta> favoritasFind(Cliente cliente) throws Exception{
        List<Cuenta> result = new ArrayList();
//        for(String nc: favoritas.get(cliente.getCedula())){
//                result.add(cuentas.get(nc));
//        }
        return result;
    } 
        
     public List<Cuenta> cuentasFind(Cliente cliente) throws Exception{
        return base.ListaCuentas(cliente.getCedula());
     }

    public List<Movimiento> MovimientosFind(int c) {
       return base.ListaMovimientos(c);
    }

    public Cuenta CuentaFind(int c){
        try {
            return base.GetCuenta(c);
        } catch (Exception ex) {
          return null;
        }
    }

    public void AgregarFavorita(int numero, String cedula) throws Exception {
        base.CuentaFavoritadd(numero,cedula);    
    }

    public CuentaFavorita FavoritaFindxCed(String cedF,String cedC) {
        try {
            return base.GetFavoritaxCed(cedF,cedC);
        } catch (Exception ex) {
            return null;
        }
    }

    public Object FavoritaFind(String ced, String num) {
     try {
            return base.GetFavorita(ced,num);
        } catch (Exception ex) {
            return null;
        }
    }
}
