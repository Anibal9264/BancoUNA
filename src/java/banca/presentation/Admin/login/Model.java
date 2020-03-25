package banca.presentation.Admin.login;
import banca.logic.Usuario;
public class Model {
    Usuario user;

    public Model() {
        this.reset();
    }
    
    public void reset(){
        setUser(new Usuario());        
    }
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
   
}
