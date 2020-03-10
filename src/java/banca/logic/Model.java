package banca.logic;
import banca.data.Dao;
import java.util.ArrayList;
import java.util.List;
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
            System.out.print("UsuarioFind");
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
    
    public Cliente clienteFind(Usuario usuario) throws Exception{
//        if (clientes.get(usuario.getCedula())!=null) return clientes.get(usuario.getCedula());
//        else throw new Exception("Cliente no existe");
     return null;
    }
     public List<Cuenta> cuentasFind(Cliente cliente) throws Exception{
        List<Cuenta> result = new ArrayList();
//        for(Cuenta c: cuentas.values()){
//            if(c.getCliente().equals(cliente)){
//                result.add(c);
//            }
//        }
        return result;
    }
}
