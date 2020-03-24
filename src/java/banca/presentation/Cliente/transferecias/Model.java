
package banca.presentation.Cliente.transferecias;

import banca.logic.Cuenta;
import banca.logic.Movimiento;
import banca.logic.Usuario;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Cuenta> C_salida;
    private List<Cuenta> C_Favoritas;
    private Cuenta destino_C;
    private Movimiento M_salida;
    private Movimiento M_destino;
    private Usuario U_Select;

    public Model(List<Cuenta> C_salida, List<Cuenta> C_Favoritas, Cuenta destino_C, Movimiento M_salida, Movimiento M_destino, Usuario U_Select) {
        this.C_salida = C_salida;
        this.C_Favoritas = C_Favoritas;
        this.destino_C = destino_C;
        this.M_salida = M_salida;
        this.M_destino = M_destino;
        this.U_Select = U_Select;
    }
 public Model() {
        this.C_salida =  new ArrayList<>();
        this.C_Favoritas =  new ArrayList<>();
        this.M_salida = new Movimiento();
        this.M_destino = new Movimiento();
        this.U_Select =new Usuario();
        this.destino_C = new Cuenta();
    }

    public List<Cuenta> getC_Favoritas() {
        return C_Favoritas;
    }

    public void setC_Favoritas(List<Cuenta> C_Favoritas) {
        this.C_Favoritas = C_Favoritas;
    }
    public Cuenta getDestino_C() {
        return destino_C;
    }

    public void setDestino_C(Cuenta destino_C) {
        this.destino_C = destino_C;
    }

    public List<Cuenta> getC_salida() {
        return C_salida;
    }

    public void setC_salida(List<Cuenta> C_salida) {
        this.C_salida = C_salida;
    }



    public Movimiento getM_salida() {
        return M_salida;
    }

    public void setM_salida(Movimiento M_salida) {
        this.M_salida = M_salida;
    }

    public Movimiento getM_destino() {
        return M_destino;
    }

    public void setM_destino(Movimiento M_destino) {
        this.M_destino = M_destino;
    }

    public Usuario getU_Select() {
        return U_Select;
    }

    public void setU_Select(Usuario U_Select) {
        this.U_Select = U_Select;
    }

    
}
