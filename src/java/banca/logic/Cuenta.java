package banca.logic;
public class Cuenta {

    
    int numero;
    double saldo;
    boolean estado;
    String descripcion;
    double interesesG;
    double limite;
    Usuario usuario;
    Moneda moneda;
    
    public Cuenta(int numero, double saldo, boolean estado, String descripcion, double interesesG, double limite, Usuario usuario, Moneda moneda) {
        this.numero = numero;
        this.saldo = saldo;
        this.estado = estado;
        this.descripcion = descripcion;
        this.interesesG = interesesG;
        this.limite = limite;
        this.usuario = usuario;
        this.moneda = moneda;
    }
    public Cuenta() {
        this.numero = 0;
        this.saldo = 0.0;
        this.estado = false;
        this.descripcion = "";
        this.interesesG = 0.0;
        this.limite = 0.0;
        this.usuario = new Usuario();
        this.moneda = new Moneda();  
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getInteresesG() {
        return interesesG;
    }

    public void setInteresesG(double interesesG) {
        this.interesesG = interesesG;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
    public String getEstado() {
       if(estado){
       return "Activa";
       }
       return "Inactiva";
    }

    @Override
    public String toString() {
        return "Cuenta" + " Numero: " + numero + ", Saldo: " + saldo + ", Estado: " + estado;
    }
    public String toStringValor0() {
        if(numero == 0){
        return "";
        }
        return String.valueOf(numero);
    }
    }
