package banca.logic;

import static java.sql.Types.NULL;
public class CuentaFavorita {
  private int id;
  private Cuenta cuenta;
  private Usuario user;

    public CuentaFavorita(int id, Cuenta cuenta, Usuario user) {
        this.id = id;
        this.cuenta = cuenta;
        this.user = user;
    }
    public CuentaFavorita() {
        this.id = NULL;
        this.cuenta = new Cuenta();
        this.user = new Usuario();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    
  
  
}
