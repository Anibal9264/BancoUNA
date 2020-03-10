package banca.logic;
public class Moneda {
   String id;
   double tipo_cambio;
   double interes;

    public Moneda(String id, double tipo_cambio, double interes) {
        this.id = id;
        this.tipo_cambio = tipo_cambio;
        this.interes = interes;
    }
   
     public Moneda() {
        this.id = "";
        this.tipo_cambio = 0.0;
        this.interes = 0.0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTipo_cambio() {
        return tipo_cambio;
    }

    public void setTipo_cambio(double tipo_cambio) {
        this.tipo_cambio = tipo_cambio;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }
   
     
}
