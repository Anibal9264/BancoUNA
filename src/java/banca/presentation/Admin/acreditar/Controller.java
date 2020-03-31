
package banca.presentation.Admin.acreditar;

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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AcreditarController", urlPatterns = {"/presentation/admin/acreditar/show","/presentation/admin/acreditar/action"})
public class Controller extends HttpServlet{
       protected void processRequest(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException {
         request.setAttribute("model",new Model());
        HttpSession session = request.getSession(true);
        Usuario real = (Usuario) session.getAttribute("admin");
        String viewUrl="";
        if(real!= null){
        switch(request.getServletPath()){
            case "/presentation/admin/acreditar/show":
                viewUrl=this.show(request);
                break; 
            case "/presentation/admin/acreditar/action":
                viewUrl=this.action(request);
                break; 
        } 
         }else{viewUrl="/presentation/sesionCaducada.jsp";} 
        request.getRequestDispatcher(viewUrl).forward( request,response); 
  }
       
       
    private String show(HttpServletRequest request) {
             CargarCuentas(request);
             return this.showAction(request);
    }


    private String action(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
       banca.logic.Model domainModel = banca.logic.Model.instance();    
        CargarCuentas(request);
        Movimiento m;
        Deposito d;
        for(Cuenta c :model.getCuentas()){
            d = CrearDeposito(GetFecha(),CalcularMonto(c),"Acreditación","Banco UNA");
            m =  CrearMovimiento(c,new Retiro(),d,"Acreditación");
            model.getMovimientos().add(m);
            domainModel.AgregarMovimientoDeposito(m);
            c.setSaldo(c.getSaldo()+m.getDeposito().getMonto());
            domainModel.CuentaUpdate(c);
        }
          return "/presentation/admin/acreditar/view.jsp"; 
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
    
    private double CalcularMonto(Cuenta c) {
       return  c.getSaldo()*c.getMoneda().getInteres();
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

    private void CargarCuentas(HttpServletRequest request) {
    Model model = (Model) request.getAttribute("model");
    banca.logic.Model domainModel = banca.logic.Model.instance();
    model.setCuentas(domainModel.cuentasFind(new Cliente("","",null)));
    }

     public String showAction(HttpServletRequest request){
        return "/presentation/admin/acreditar/view.jsp"; 
    }

    
}
