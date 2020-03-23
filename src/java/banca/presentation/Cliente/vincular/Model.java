package banca.presentation.Cliente.vincular;

import banca.logic.Cuenta;

public class Model {
    private Cuenta seleccionado;

    public Model() {
        this.seleccionado = null;
    }
    public Cuenta getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cuenta seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    
}
