package banca.presentation.Admin.login;
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

@WebServlet(name = "LoginAdminController", urlPatterns = {"/presentation/admin/login/show","/presentation/admin/login/login","/presentation/admin/login/logout"})
public class Controller extends HttpServlet {

  protected void processRequest(HttpServletRequest request,HttpServletResponse response)
         throws ServletException, IOException {
        request.setAttribute("model",new Model()); 
        String viewUrl="";
        switch(request.getServletPath()){
            case "/presentation/admin/login/show":
                viewUrl=this.show(request);
                break; 
            case "/presentation/admin/login/login":
                viewUrl=this.login(request);
                break;               
            case "/presentation/admin/login/logout":
                viewUrl=this.logout(request);
                break;
        }
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }
 //================= Show menu=================
   public String show(HttpServletRequest request){
        return this.showAction(request);
    }
    public String showAction(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.setAttribute("admin",new Usuario());
        return "/presentation/admin/login/view.jsp"; 
    } 
// ==================MENU LOGIN================
    private String login(HttpServletRequest request) {
        try {
            Map<String, String> errores = this.validar(request);
            if (errores.isEmpty()) {
                this.updateModel(request);
                return this.loginAction(request);
            } else {
                return "/presentation/admin/login/view.jsp";
            }
        } catch (Exception e) {
                return "/presentation/admin/login/view.jsp";
        }
    }
    
    Map<String,String> validar(HttpServletRequest request){
        Map<String,String> errores = new HashMap<>();
        banca.logic.Model domainModel = banca.logic.Model.instance();
        Usuario real = domainModel.usuarioFind(request.getParameter("cedula_login"),request.getParameter("pass_login"));
        
        if (real == null){
            errores.put("cedula_login","Cliente no Existe");
            request.setAttribute("errores",errores);
        }
        if (!real.getIs()){
            errores.put("cedula_login","No tienes acceso");
            request.setAttribute("errores",errores);
        }
        if (real.getContraseña().isEmpty()){
            errores.put("pass_login","Clave Incorrecta");
            request.setAttribute("errores",errores);
        }
        return errores;
    }
    
     void updateModel(HttpServletRequest request){
       Model model= (Model) request.getAttribute("model");
       model.getUser().setCedula(request.getParameter("cedula_login"));
       model.getUser().setContraseña(request.getParameter("pass_login"));
      }
        
    public String loginAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.logic.Model domainModel = banca.logic.Model.instance();
        HttpSession session = request.getSession(true);
        Usuario real = domainModel.usuarioFind(model.getUser().getCedula(), model.getUser().getContraseña());
        session.setAttribute("admin",real);
        return "/presentation/Index.jsp";
    }   

    public String logout(HttpServletRequest request){
        return this.logoutAction(request);
    }
    
    public String logoutAction(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.removeAttribute("admin");
        session.invalidate();
        return "/presentation/admin/login/view.jsp";   
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
