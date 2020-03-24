package banca.presentation.Cliente.transferecias;
import banca.logic.Cliente;
import banca.logic.Cuenta;
import banca.logic.CuentaFavorita;
import banca.logic.Deposito;
import banca.logic.Movimiento;
import banca.logic.Retiro;
import banca.logic.Usuario;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TransferenciasController", urlPatterns = {"/presentation/transferencia/show","/presentation/transferencia/transferencia"})
public class Controller extends HttpServlet {

  protected void processRequest(HttpServletRequest request,HttpServletResponse response)
         throws ServletException, IOException {
        request.setAttribute("model",new Model()); 
        String viewUrl="";
        switch(request.getServletPath()){
            case "/presentation/transferencia/show":
                viewUrl=this.show(request);
                break; 
            case "/presentation/transferencia/transferencia":
                viewUrl=this.transferencia(request);
                break;               
        }
        request.getRequestDispatcher(viewUrl).forward(request, response); 
  }
  
  //=====================SHOW==============================
    private String show(HttpServletRequest request){
        Cargar_Datos_de_Salida(request);
        return this.showAction(request);
    }
    private String showAction(HttpServletRequest request){
        return "/presentation/cliente/transferencia/view.jsp"; 
    }
    
    private void Cargar_Datos_de_Salida(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.logic.Model domainModel = banca.logic.Model.instance();
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cliente cliente;
        try {
            cliente = new Cliente(usuario.getCedula(),usuario.toString(),usuario);
        } catch (Exception ex) {
            cliente=null;
        }
        try {
           model.setC_salida(domainModel.cuentasFind(cliente));
        } catch (Exception ex) {
           model.setC_salida(new ArrayList<>());
        }
      try {
          model.setC_Favoritas(domainModel.favoritasFind(cliente));
      } catch (Exception ex) {
          model.setC_Favoritas(new ArrayList<>());
      }
    }
  //========================TRANSFERENCIAS===========================
    private String transferencia(HttpServletRequest request) {
    try{
        Map<String,String> errores =  this.validar(request);
        if(errores.isEmpty()){
                return transferenciaAction(request);  
        }else{
            return "/presentation/cliente/transferencia/view.jsp"; 
        }   
       }catch(Exception e){
           return "/presentation/cliente/transferencia/view.jsp"; 
       }  
    }

  //*********************************************************************************//
  //====================== VERIFICACIONES =========================

   private Map<String, String> validar(HttpServletRequest request) {
       Map<String, String> errores = new HashMap<>();
       banca.logic.Model domainModel = banca.logic.Model.instance();
       HttpSession session = request.getSession(true);
       Usuario real = (Usuario) session.getAttribute("usuario");
       Cuenta cuenta = domainModel.CuentaFind(Integer.valueOf(request.getParameter("Cuenta_S")));
       double monto = Double.valueOf(request.getParameter("monto_t"));
 //===================Verificar monto===================
       if (cuenta.getSaldo() < monto) {
           errores.put("monto_t", "No tienes saldo suficiente!!");
       }
       if (monto == 0.0) {
           errores.put("monto_t","Ingrese un Monto");
       }
       if(monto>cuenta.getLimite()){
           errores.put("monto_t","Monto sobrepasa el limite diario");
       }
      Cargar_Datos_de_Salida(request);
      request.setAttribute("errores", errores);
      return errores;
   }

  //========================================================
   
    private String transferenciaAction(HttpServletRequest request) {
       banca.logic.Model domainModel = banca.logic.Model.instance();
       HttpSession session = request.getSession(true);
       Usuario user = (Usuario)session.getAttribute("usuario");
       Cuenta cuenta = domainModel.CuentaFind(Integer.valueOf(request.getParameter("Cuenta_S")));
       String Ncuenta = String.valueOf(cuenta.getNumero());
       double monto = Double.valueOf(request.getParameter("monto_t"));
       double tipoC = cuenta.getMoneda().getTipo_cambio();
       String motivo = request.getParameter("motivo_t");
       Cuenta Favorita = domainModel.CuentaFind(Integer.valueOf(request.getParameter("Cuenta_F")));
      //Primero Un movimiento retiro de la cuenta
       domainModel.AgregarMovimientoRetiro(CrearMovimiento(cuenta,CrearRetiro(monto),new Deposito(),motivo));
       cuenta.setSaldo(cuenta.getSaldo()-monto);
       domainModel.CuentaUpdate(cuenta);
      //Segundo Un movimiento Deposito de la cuenta
       domainModel.AgregarMovimientoDeposito(CrearMovimiento(Favorita,
       new Retiro(),CrearDeposito(GetFecha(),monto/tipoC,motivo,user.toString()),motivo));
       Favorita.setSaldo(Cambio(Favorita,monto,tipoC));
       domainModel.CuentaUpdate(Favorita);
        return "/presentation/cliente/cuentas/detalles?numeroFld="+Ncuenta;
    }

        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Movimiento CrearMovimiento( Cuenta cuenta,Retiro retiro,Deposito deposito,String detalle){
       return new Movimiento(0,GetFecha(),detalle,deposito, retiro, cuenta);
    }

    private Retiro CrearRetiro(double monto) {
      return new Retiro(1,monto,GetFecha());
    }
    private Deposito CrearDeposito(String fecha, double monto, String motivo, String nombre) {
     return new Deposito(1,monto, motivo, fecha, nombre);
    }

   private String GetFecha(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        return dateFormat.format(date);
   }
   
   private double Cambio(Cuenta cuenta,double monto,double tipoC){
       double saldo =  cuenta.getSaldo();
       double Resul = monto/tipoC;
       saldo += Resul;
       return saldo;
   }
}  
