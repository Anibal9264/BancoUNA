
package banca.logic;

import static java.sql.Types.NULL;

public class Deposito {
   private int id;
   private double monto;
   private String motivo;
   private String fecha;
   private String NombreDepositante;
   Cuenta cuenta;
   Usuario user;

    public Deposito(int id, double monto, String motivo, String fecha, String NombreDepositante, Cuenta cuenta, Usuario user) {
        this.id = NULL;
        this.monto = monto;
        this.motivo = motivo;
        this.fecha = fecha;
        this.NombreDepositante = NombreDepositante;
        this.cuenta = cuenta;
        this.user = user;
    }

    public Deposito() {
        this.id = 0;
        this.monto = 0.0;
        this.motivo = "";
        this.fecha = "";
        this.NombreDepositante = "";
        this.cuenta = new Cuenta();
        this.user = new Usuario();
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreDepositante() {
        return NombreDepositante;
    }

    public void setNombreDepositante(String NombreDepositante) {
        this.NombreDepositante = NombreDepositante;
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
