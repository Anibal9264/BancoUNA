package banca.presentation.Admin.AddCuenta;

import banca.logic.Cuenta;
import banca.logic.Moneda;
import banca.logic.Usuario;
import java.util.ArrayList;
import java.util.List;

 public class Model {
 private  Usuario user;
 private Cuenta cuenta;
 private List<Moneda> monedas;

    public Model(Usuario user, Cuenta cuenta, List<Moneda> monedas) {
        this.user = user;
        this.cuenta = cuenta;
        this.monedas = monedas;
    }

    public Model() {
        this.user = new Usuario();
        this.cuenta = new Cuenta();
          this.monedas = new ArrayList<>();
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

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }
 
}
