package banca.presentation.Admin.Retiro;
import banca.logic.Cliente;
import banca.logic.Cuenta;
import banca.logic.Moneda;
import banca.logic.Movimiento;
import banca.logic.Retiro;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private Cliente cliente;
    private Cuenta cuenta;
    private List<Moneda> monedas;
    private Retiro retiro;
    private boolean listo;
    private Movimiento movimiento;

    public Model(Cliente cliente, Cuenta cuenta, List<Moneda> monedas, Retiro retiro, boolean listo, Movimiento movimiento) {
        this.cliente = cliente;
        this.cuenta = cuenta;
        this.monedas = monedas;
        this.retiro = retiro;
        this.listo = listo;
        this.movimiento = movimiento;
    }
   

public Model() {
        this.cliente = new Cliente();
        this.monedas = new ArrayList<>();
        this.retiro = new Retiro();
        this.cuenta = new Cuenta();
        this.listo = false;
        this.movimiento = new Movimiento();
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public boolean isListo() {
        return listo;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

    public Retiro getRetiro() {
        return retiro;
    }

    public void setRetiro(Retiro retiro) {
        this.retiro = retiro;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    
    
}
