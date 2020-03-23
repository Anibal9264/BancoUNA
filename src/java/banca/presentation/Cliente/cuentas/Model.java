
package banca.presentation.Cliente.cuentas;

import banca.logic.Cuenta;
import banca.logic.Movimiento;
import banca.logic.Usuario;
import java.util.ArrayList;
import java.util.List;

public class Model{
    private List<Cuenta> cuentas;
    
    private Usuario Uselect;
    private List<Movimiento> movimientos;
    public Model() {
        this.reset();
    }

    public void reset(){ 
        List<Cuenta> rows = new ArrayList<>();
        List<Movimiento> movi = new ArrayList<>();
      
        Uselect = null;
        this.setCuentas(rows);
        this.setMovimientos(movi);
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
    
    public void setCuentas(List<Cuenta> cuentas){
        this.cuentas =cuentas;    
    }

     public List<Cuenta> getCuentas() {
        return cuentas;
    }
    public Usuario getUselect() {
        return Uselect;
    }

    public void setUselect(Usuario Uselect) {
        this.Uselect = Uselect;
    }
    
    
}
