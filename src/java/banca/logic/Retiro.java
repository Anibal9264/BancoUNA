
package banca.logic;

import static java.sql.Types.NULL;





public class Retiro {
    private int id;
    private double monto;
    private String fecha;
    Usuario user;
    Cuenta cuenta;

    public Retiro(int id, double monto, String fecha, Usuario user, Cuenta cuenta) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
        this.user = user;
        this.cuenta = cuenta;
    }

    public Retiro() {
        this.id = NULL;
        this.monto = 0.0;
        this.fecha = "";
        this.user = new Usuario();
        this.cuenta = new Cuenta();
    }
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    
    
    
}
