/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.presentation.cuentas;

import banca.logic.Cuenta;
import banca.logic.Movimiento;
import banca.logic.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Escinf
 */
public class Model{
    private List<Cuenta> cuentas;
    private Cuenta seleccionado;
    private Usuario Uselect;
    private List<Movimiento> movimientos;
    public Model() {
        this.reset();
    }

    public void reset(){ 
        List<Cuenta> rows = new ArrayList<>();
        List<Movimiento> movi = new ArrayList<>();
        seleccionado=new Cuenta();
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

    public Cuenta getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cuenta seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Usuario getUselect() {
        return Uselect;
    }

    public void setUselect(Usuario Uselect) {
        this.Uselect = Uselect;
    }
    
    
}
