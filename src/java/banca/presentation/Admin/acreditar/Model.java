
package banca.presentation.Admin.acreditar;

import banca.logic.Cuenta;
import banca.logic.Movimiento;
import java.util.ArrayList;
import java.util.List;

public class Model {
  private List<Cuenta> cuentas;
  private List<Movimiento> movimientos;

    public Model(List<Cuenta> cuentas, List<Movimiento> movimientos) {
        this.cuentas = cuentas;
        this.movimientos = movimientos;
    }

    

     public Model() {
        this.cuentas = new ArrayList<>();
        this.movimientos =new ArrayList<>();
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
    
    
  
}
