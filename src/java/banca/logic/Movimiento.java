package banca.logic;
import static java.sql.Types.NULL;

public class Movimiento {
  int id;
  String fecha;
  String detalle;
  Deposito deposito;
  Retiro retiro;
  Cuenta cuenta;

    public Movimiento(int id, String fecha, String detalle, Deposito deposito, Retiro retiro,Cuenta cuenta) {
        this.id = id;
        this.fecha = fecha;
        this.detalle = detalle;
        this.deposito = deposito;
        this.retiro = retiro;
        this.cuenta = cuenta;
    }
   public Movimiento() {
        this.id = NULL;
        this.fecha = "";
        this.detalle = "";
        this.deposito = new Deposito();
        this.retiro = new Retiro();
        this.cuenta= new Cuenta();
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
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

    public String toStringTipo() {
        if(retiro.getId() != 0){
            return "Retiro";
        }else{
            return "Deposito";
        }
        
    }
   
   
  
}
