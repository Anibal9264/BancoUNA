
package banca.presentation.Cliente.actualizar;

import banca.logic.Usuario;

public class Model {
 private  Usuario user;

    public Model(Usuario user) {
        this.user = user;
    }
    public Model() {
        this.user = new Usuario();
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
 
}
