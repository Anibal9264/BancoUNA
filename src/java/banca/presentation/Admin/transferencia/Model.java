
package banca.presentation.Admin.transferencia;
import banca.logic.Cliente;
import banca.logic.Deposito;
import banca.logic.Moneda;
import banca.logic.Movimiento;
import banca.logic.Retiro;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private Cliente cliente_S;
    private Cliente cliente_D;
    private Retiro retiro;
    private Deposito deposito;
    private boolean listo;
    private Movimiento movimiento_S;
    private Movimiento movimiento_D;
    private List<Moneda> monedas;

    public Model(Cliente cliente_S, Cliente cliente_D, Retiro retiro, Deposito deposito, boolean listo, Movimiento movimiento_S, Movimiento movimiento_D, List<Moneda> monedas) {
        this.cliente_S = cliente_S;
        this.cliente_D = cliente_D;
        this.retiro = retiro;
        this.deposito = deposito;
        this.listo = listo;
        this.movimiento_S = movimiento_S;
        this.movimiento_D = movimiento_D;
        this.monedas = monedas;
    }

    
    
    public Model() {
        this.cliente_S =new Cliente();
        this.cliente_D = new Cliente();
        this.retiro = new Retiro();
        this.deposito = new Deposito();
        this.listo = false;
        this.movimiento_S =  new Movimiento();
        this.movimiento_D =  new Movimiento();
 this.monedas = new ArrayList<>();
    }

    public List<Moneda> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<Moneda> monedas) {
        this.monedas = monedas;
    }

    public Cliente getCliente_S() {
        return cliente_S;
    }

    public void setCliente_S(Cliente cliente_S) {
        this.cliente_S = cliente_S;
    }

    public Cliente getCliente_D() {
        return cliente_D;
    }

    public void setCliente_D(Cliente cliente_D) {
        this.cliente_D = cliente_D;
    }

    public Retiro getRetiro() {
        return retiro;
    }

    public void setRetiro(Retiro retiro) {
        this.retiro = retiro;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public boolean isListo() {
        return listo;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }

    public Movimiento getMovimiento_S() {
        return movimiento_S;
    }

    public void setMovimiento_S(Movimiento movimiento_S) {
        this.movimiento_S = movimiento_S;
    }

    public Movimiento getMovimiento_D() {
        return movimiento_D;
    }

    public void setMovimiento_D(Movimiento movimiento_D) {
        this.movimiento_D = movimiento_D;
    }

  
    
    
    
}
