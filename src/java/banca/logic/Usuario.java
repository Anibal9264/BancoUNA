package banca.logic;
public class Usuario {
   String cedula;
   String nombre;
   String apellido1;
   String apellido2;
   String contraseña;
   boolean is;
   String telefono;

    public Usuario(String cedula, String nombre, String apellido1, String apellido2, String contraseña, boolean is, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.contraseña = contraseña;
        this.is = is;
        this.telefono = telefono;
    }
   
    public Usuario() {
        this.cedula = "";
        this.nombre = "";
        this.apellido1 = "";
        this.apellido2 = "";
        this.contraseña = "";
        this.is = false;
        this.telefono = "";
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean getIs() {
        return is;
    }

    public void setIs(boolean is) {
        this.is = is;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre +" "+ apellido1 +" "+ apellido2 ;
    }
    
    public int toIs() {
        if(is){
            return 1;
        }
        return 0;
    }
   
}
