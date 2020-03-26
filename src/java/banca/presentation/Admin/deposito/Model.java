package banca.presentation.Admin.deposito;
import banca.logic.Cliente;
import banca.logic.Deposito;
import banca.logic.Moneda;
import java.util.ArrayList;
import java.util.List;

public class Model {
    Cliente cliente;
    List<Moneda> monedas;
    Deposito deposito;

    public Model(Cliente cliente, List<Moneda> monedas, Deposito deposito) {
        this.cliente = cliente;
        this.monedas = monedas;
        this.deposito = deposito;
    }

public Model() {
        this.cliente = new Cliente();
        this.monedas = new ArrayList<>();
        this.deposito = new Deposito();
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
