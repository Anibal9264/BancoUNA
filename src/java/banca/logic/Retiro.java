package banca.logic;

import static java.sql.Types.NULL;

public class Retiro {
    private int id;
    private double monto;
    private String fecha;
    public Retiro(int id, double monto, String fecha) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Retiro() {
        this.id = NULL;
        this.monto = 0.0;
        this.fecha = "";
    }
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
  
}
