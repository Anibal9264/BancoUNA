package banca.presentation.Admin.deposito;
import banca.logic.Cliente;
import banca.logic.Cuenta;
import banca.logic.Deposito;
import banca.logic.Moneda;
import banca.logic.Movimiento;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private Cliente cliente;
    private List<Moneda> monedas;
    private Deposito deposito;
    private boolean listo;
    private Movimiento movimiento;

    public Model(Cliente cliente, List<Moneda> monedas, Deposito deposito, boolean listo, Movimiento movimiento) {
        this.cliente = cliente;
        this.monedas = monedas;
        this.deposito = deposito;
        this.listo = listo;
        this.movimiento = movimiento;
    }
   

public Model() {
        this.cliente = new Cliente();
        this.monedas = new ArrayList<>();
        this.deposito = new Deposito();
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

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }
 
}
