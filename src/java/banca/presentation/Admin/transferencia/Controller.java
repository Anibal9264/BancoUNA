
package banca.presentation.Admin.transferencia;

import banca.logic.Cliente;
import banca.logic.Cuenta;
import banca.logic.Deposito;
import banca.logic.Movimiento;
import banca.logic.Retiro;
import banca.logic.Usuario;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 @WebServlet(name = "TransferenciaController", urlPatterns = {"/presentation/admin/transferencia/show","/presentation/admin/transferencia/add"})
public class Controller extends HttpServlet {
   protected void processRequest(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("model",new Model());
        HttpSession session = request.getSession(true);
        Usuario real = (Usuario) session.getAttribute("admin");
        String viewUrl="";
        if(real!= null){
        switch(request.getServletPath()){
            case "/presentation/admin/transferencia/show":
                viewUrl=this.show(request);
                break; 
            case "/presentation/admin/transferencia/add":
                viewUrl=this.add(request);
                break; 
        } 
         }else{viewUrl="/presentation/sesionCaducada.jsp";}   
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

    private String show(HttpServletRequest request) {
           CargarDatos(request);
           return this.showAction(request);
    }

     private void CargarDatos(HttpServletRequest request) {
    Model model = (Model) request.getAttribute("model");
        banca.logic.Model domainModel = banca.logic.Model.instance();
        String c_S = String.valueOf(request.getParameter("Cedula_S"));
        String n_S = String.valueOf(request.getParameter("Numero_S"));
        String c_D = String.valueOf(request.getParameter("Cedula_D"));
        String n_D = String.valueOf(request.getParameter("Numero_D"));
        model.setMonedas(domainModel.getMonedas());
        if( !c_S.equals("null") || !n_S.equals("null")){
            if(!c_D.equals("null") || !n_D.equals("null")){
                validar(request);
            }
         }
     }

      private Map<String, String> validar(HttpServletRequest request) {
        Map<String,String> errores = new HashMap<>();
        banca.logic.Model domainModel = banca.logic.Model.instance();
        Model model = (Model) request.getAttribute("model");
        Cuenta cuenta = null;
        Usuario real;
        if(request.getParameter("Cedula_S") != null){
        real = domainModel.usuarioFind(request.getParameter("Cedula_S"),"");
        if (real == null){
            errores.put("Cedula_S","Usuario NO Existe");
            errores.put("Valor_S1",request.getParameter("Cedula_S"));
            errores.put("Valor_D1",request.getParameter("Cedula_D"));
            errores.put("Valor_D2",request.getParameter("Numero_D"));
            request.setAttribute("errores",errores); 
        }else{
             model.setCliente_S(new Cliente(real.getCedula(),real.toString(),real));
             model.getCliente_S().setCuentas(domainModel.cuentasFind(model.getCliente_S()));
        }
        }else if(request.getParameter("Numero_S") != null){
           cuenta = domainModel.CuentaFind(Integer.parseInt(request.getParameter("Numero_S")));
          if (cuenta == null){
            errores.put("Numero_S","Cuenta NO Existe");
            errores.put("Valor_S2",request.getParameter("Numero_S"));
            errores.put("Valor_D1",request.getParameter("Cedula_D"));
            errores.put("Valor_D2",request.getParameter("Numero_D"));
            request.setAttribute("errores",errores);
          }else{
             model.setCliente_S(new Cliente(cuenta.getUsuario().getCedula(),cuenta.getUsuario().toString(),cuenta.getUsuario()));
             model.getCliente_S().getCuentas().add(cuenta);
          }
          
        }
        
       if(request.getParameter("Cedula_D") != null){
        real = domainModel.usuarioFind(request.getParameter("Cedula_D"),"");
        if (real == null){
            errores.put("Cedula_D","Usuario NO Existe");
            errores.put("Valor_D1",request.getParameter("Cedula_D"));
            errores.put("Valor_S1",request.getParameter("Cedula_S"));
            errores.put("Valor_S2",request.getParameter("Numero_S"));
            request.setAttribute("errores",errores); 
        }else{
             model.setCliente_D(new Cliente(real.getCedula(),real.toString(),real));
             model.getCliente_D().setCuentas(domainModel.cuentasFind(model.getCliente_D()));
        }
        }else if(request.getParameter("Numero_D") != null){
           cuenta = domainModel.CuentaFind(Integer.parseInt(request.getParameter("Numero_D")));
          if (cuenta == null){
            errores.put("Numero_D","Cuenta NO Existe");
            errores.put("Valor_D2",request.getParameter("Numero_D"));
             errores.put("Valor_S1",request.getParameter("Cedula_S"));
            errores.put("Valor_S2",request.getParameter("Numero_S"));
            request.setAttribute("errores",errores);
          }else{
             model.setCliente_D(new Cliente(cuenta.getUsuario().getCedula(),cuenta.getUsuario().toString(),cuenta.getUsuario()));
             model.getCliente_D().getCuentas().add(cuenta);
          }
        }
      try{
       double monto =Double.valueOf(request.getParameter("Monto_T"));
       if(monto==0.0){
          errores.put("Monto_T","Dijite un monto!!");
          request.setAttribute("errores",errores);
        }else if(model.getCliente_S().getCuentas().get(0).getSaldo()<CambioMoneda(request,model)){
          errores.put("Monto_T","Saldo Insuficiente!!");
          request.setAttribute("errores",errores);
       }
      }catch(Exception e){
        return errores;
       }
       return errores;
    }

    private String showAction(HttpServletRequest request) {
       return "/presentation/admin/transferencia/view.jsp";  
    }
    
    private String add(HttpServletRequest request) {
        banca.logic.Model domainModel = banca.logic.Model.instance();
        Model model = (Model) request.getAttribute("model");
        Cuenta cuenta;
       cuenta = domainModel.CuentaFind(Integer.parseInt(request.getParameter("Numero_C1")));
       model.setCliente_S(new Cliente(cuenta.getUsuario().getCedula(),cuenta.getUsuario().toString(),cuenta.getUsuario()));
       model.getCliente_S().getCuentas().add(cuenta);
       cuenta = domainModel.CuentaFind(Integer.parseInt(request.getParameter("Numero_C2")));
       model.setCliente_D(new Cliente(cuenta.getUsuario().getCedula(),cuenta.getUsuario().toString(),cuenta.getUsuario()));
       model.getCliente_D().getCuentas().add(cuenta);
        if(validar(request).isEmpty()){
         model.setMovimiento_S(CrearMovimiento(model.getCliente_S().getCuentas().get(0),
         CrearRetiro(CambioMoneda(request,model)),
         new Deposito(),"Retiro Movimiento A cliente : "+model.getCliente_D().getCedula()));
         domainModel.AgregarMovimientoRetiro(model.getMovimiento_S());
         cuenta = model.getCliente_S().getCuentas().get(0);
         cuenta.setSaldo(cuenta.getSaldo()-model.getMovimiento_S().getRetiro().getMonto());
         domainModel.CuentaUpdate(cuenta);
         
         model.setMovimiento_D(CrearMovimiento(model.getCliente_D().getCuentas().get(0),
         new Retiro(),
         CrearDeposito(GetFecha(),CambioMonedaD(request,model),request.getParameter("Motivo_T"),
                model.getCliente_S().getNombre()),"Deposito Movimiento de cliente : "+model.getCliente_S().getCedula()));
         domainModel.AgregarMovimientoDeposito( model.getMovimiento_D());
         cuenta = model.getCliente_D().getCuentas().get(0);
         cuenta.setSaldo(cuenta.getSaldo()+model.getMovimiento_D().getDeposito().getMonto());
         domainModel.CuentaUpdate(cuenta);
        }else{
        CargarDatos(request);
        return "/presentation/admin/transferencia/view.jsp";
       }
        model.setListo(true);
        return "/presentation/admin/transferencia/view.jsp";
    }
    
    
    
    
    private Movimiento CrearMovimiento( Cuenta cuenta,Retiro retiro,Deposito deposito,String detalle){
       return new Movimiento(0,GetFecha(),detalle,deposito, retiro, cuenta);
    }
    
   private Deposito CrearDeposito(String fecha, double monto, String motivo, String nombre) {
     return new Deposito(1,monto, motivo, fecha, nombre);
    }
   
   private Retiro CrearRetiro(double monto) {
      return new Retiro(1,monto,GetFecha());
    }
   
    private String GetFecha(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        return dateFormat.format(date);
   }
    
    
   private double CambioMoneda(HttpServletRequest request,Model model){
        double monto = Double.valueOf(request.getParameter("Monto_T"));
        double Tmoneda = Double.parseDouble(request.getParameter("Moneda_C"));
        double tipoC = model.getCliente_S().getCuentas().get(0).getMoneda().getTipo_cambio();
        return  (monto/Tmoneda)*tipoC;
    }
   private double CambioMonedaD(HttpServletRequest request,Model model){
        double monto = Double.valueOf(request.getParameter("Monto_T"));
        double tipoS = model.getCliente_S().getCuentas().get(0).getMoneda().getTipo_cambio();
        double tipoD = model.getCliente_D().getCuentas().get(0).getMoneda().getTipo_cambio();
        return (monto/tipoS)*tipoD;
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

  
 }