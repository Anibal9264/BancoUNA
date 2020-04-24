package banca.presentation.Cliente.actualizar;
import banca.logic.Usuario;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "updateUserController", urlPatterns = {"/presentation/cliente/actualizar/show","/presentation/cliente/actualizar/update"})
public class Controller extends HttpServlet {
protected void processRequest(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException {
         request.setAttribute("model",new Model());
        HttpSession session = request.getSession(true);
        Usuario real = (Usuario) session.getAttribute("cliente");
        String viewUrl="";
        if(real!= null){
        switch(request.getServletPath()){
            case "/presentation/cliente/actualizar/show":
                viewUrl=this.show(request);
                break; 
            case "/presentation/cliente/actualizar/update":
                viewUrl=this.update(request);
                break; 
        } 
         }else{viewUrl="/presentation/sesionCaducada.jsp";} 
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }
 //================= Show menu=================
   public String show(HttpServletRequest request){
        return this.showAction(request);
    }
    public String showAction(HttpServletRequest request){
        return "/presentation/cliente/Actualizar/view.jsp"; 
    } 

   private String update(HttpServletRequest request) {
      return this.updateAction(request);
    }

    Map<String,String> validar(HttpServletRequest request){
        Map<String,String> errores = new HashMap<>();
        banca.logic.Model domainModel = banca.logic.Model.instance();
        Usuario real = domainModel.usuarioFind(request.getParameter("Cedula_C"),"");
        if (real != null){
            errores.put("Cedula_C","Usuario Existe");
            request.setAttribute("errores",errores);
        }
        return errores;
    }

    private String updateAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Usuario real = (Usuario) session.getAttribute("cliente");
        banca.logic.Model domainModel = banca.logic.Model.instance();
         Usuario cliente = new Usuario();
         cliente.setCedula(real.getCedula());
         cliente.setNombre(request.getParameter("Nombre_C"));
         cliente.setApellido1(request.getParameter("Apll1_C"));
         cliente.setApellido2(request.getParameter("Apll2_C"));
         cliente.setTelefono(request.getParameter("Tel_C"));
         cliente.setIs(real.getIs());
         cliente.setContraseña(request.getParameter("Pass_C"));
         session.setAttribute("cliente",cliente);
         domainModel.UsuarioUpdate(cliente);
         return this.show(request);
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
