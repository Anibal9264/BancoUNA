package banca.logic;
import static java.sql.Types.NULL;

public class Movimiento {
  int id;
  String fecha;
  String detalle;
  Deposito deposito;
  Retiro retiro;

    public Movimiento(int id, String fecha, String detalle, Deposito deposito, Retiro retiro) {
        this.id = id;
        this.fecha = fecha;
        this.detalle = detalle;
        this.deposito = deposito;
        this.retiro = retiro;
    }
   public Movimiento() {
        this.id = NULL;
        this.fecha = "";
        this.detalle = "";
        this.deposito = new Deposito();
        this.retiro = new Retiro();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public Retiro getRetiro() {
        return retiro;
    }

    public void setRetiro(Retiro retiro) {
        this.retiro = retiro;
    }
   
   
  
}
