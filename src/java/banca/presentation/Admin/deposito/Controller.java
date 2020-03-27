package banca.presentation.Admin.deposito;
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


 @WebServlet(name = "DepositoController", urlPatterns = {"/presentation/admin/deposito/show","/presentation/admin/deposito/add"})
public class Controller extends HttpServlet {
   protected void processRequest(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("model",new Model());
        String viewUrl="";
        switch(request.getServletPath()){
            case "/presentation/admin/deposito/show":
                viewUrl=this.show(request);
                break; 
            case "/presentation/admin/deposito/add":
                viewUrl=this.add(request);
                break; 
        } 
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }
   
   
       private String show(HttpServletRequest request) {
           CargarDatos(request);
           return this.showAction(request);
       }
        public String showAction(HttpServletRequest request){
        return "/presentation/admin/deposito/view.jsp"; 
        }
       private void CargarDatos(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.logic.Model domainModel = banca.logic.Model.instance();
        model.setMonedas(domainModel.getMonedas());
        String c = String.valueOf(request.getParameter("Cedula_C"));
        String n = String.valueOf(request.getParameter("Numero_C"));
        if( !c.equals("null") || !n.equals("null")){
            validar(request);
       }
       }
    private String add(HttpServletRequest request) {
        try{
        Map<String,String> errores =  this.validar(request);
        if(errores.isEmpty()){
                return DepositoAction(request);  
        }else{
             CargarDatos(request);
            return "/presentation/admin/deposito/view.jsp"; 
        }   
       }catch(Exception e){
            CargarDatos(request);
           return "/presentation/admin/deposito/view.jsp"; 
       }  
    } 
        private Map<String,String> validar(HttpServletRequest request){
        Map<String,String> errores = new HashMap<>();
        banca.logic.Model domainModel = banca.logic.Model.instance();
        Model model = (Model) request.getAttribute("model");
        if(request.getParameter("Cedula_C") != null){
        Usuario real = domainModel.usuarioFind(request.getParameter("Cedula_C"),"");
        if (real == null){
            errores.put("Cedula_C","Usuario NO Existe");
            errores.put("Cedula_C2",request.getParameter("Cedula_C"));
            request.setAttribute("errores",errores); 
        }else{
             model.setCliente(new Cliente(real.getCedula(),real.toString(),real));
             model.getCliente().setCuentas(domainModel.cuentasFind(model.getCliente()));
        }
        }else if(request.getParameter("Numero_C") != null){
          Cuenta cuenta = domainModel.CuentaFind(Integer.parseInt(request.getParameter("Numero_C")));
          if (cuenta == null){
            errores.put("Numero_C","Cuenta NO Existe");
            errores.put("Numero_C2",request.getParameter("Numero_C"));
            request.setAttribute("errores",errores);
          }else{
            model.setCuenta(cuenta);
          }
        }
        try{
         double monto = Double.valueOf(request.getParameter("Monto_D"));
         if (monto == 0.0) {
           errores.put("Monto_D","Ingrese un Monto");
           request.setAttribute("errores",errores);
           return errores;
         }
       }catch(Exception e){
        return errores;
       }
        return errores;
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

    private String DepositoAction(HttpServletRequest request) {
        banca.logic.Model domainModel = banca.logic.Model.instance();
        Model model = (Model) request.getAttribute("model");
        model.setCuenta(domainModel.CuentaFind(Integer.parseInt(request.getParameter("Numero_C"))));
        model.setMovimiento(CrearMovimiento(model.getCuenta(),
       new Retiro(),CrearDeposito(GetFecha(),CambioMoneda(request,model),
        request.getParameter("Motivo_D"),request.getParameter("NombreD_D")),request.getParameter("Motivo_D")));
       domainModel.AgregarMovimientoDeposito(model.getMovimiento());
       model.getCuenta().setSaldo(model.getCuenta().getSaldo()+CambioMoneda(request,model));
       domainModel.CuentaUpdate(model.getCuenta());
       model.setListo(true);
     return "/presentation/admin/deposito/view.jsp"; 
    }
    
    private Movimiento CrearMovimiento( Cuenta cuenta,Retiro retiro,Deposito deposito,String detalle){
       return new Movimiento(0,GetFecha(),detalle,deposito, retiro, cuenta);
    }
    
   private Deposito CrearDeposito(String fecha, double monto, String motivo, String nombre) {
     return new Deposito(1,monto, motivo, fecha, nombre);
    }
   
    private String GetFecha(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        return dateFormat.format(date);
   }
    
    private double CambioMoneda(HttpServletRequest request,Model model){
        double monto = Double.valueOf(request.getParameter("Monto_D"));
        double Tmoneda = Double.parseDouble(request.getParameter("Moneda_C"));
        double tipoC = model.getCuenta().getMoneda().getTipo_cambio();
        return (monto/Tmoneda)*tipoC;
    }

 

 
}
