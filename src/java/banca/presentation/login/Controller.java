package banca.presentation.login;
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

@WebServlet(name = "LoginController", urlPatterns = {"/presentation/login/show","/presentation/login/login","/presentation/login/logout"})
public class Controller extends HttpServlet {

  protected void processRequest(HttpServletRequest request,HttpServletResponse response)
         throws ServletException, IOException {
        request.setAttribute("model",new Model()); 
        String viewUrl="";
        switch(request.getServletPath()){
            case "/presentation/login/show":
                viewUrl=this.show(request);
                break; 
            case "/presentation/login/login":
                viewUrl=this.login(request);
                break;               
            case "/presentation/login/logout":
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
        return "/presentation/cliente/login/view.jsp"; 
    } 
// ==================MENU LOGIN================
    private String login(HttpServletRequest request) {
        try {
            Map<String, String> errores = this.validar(request);
            if (errores.isEmpty()) {
                this.updateModel(request);
                return this.loginAction(request);
            } else {
                return "/presentation/cliente/login/view.jsp";
            }
        } catch (Exception e) {
                return "/presentation/cliente/login/view.jsp";
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
        session.setAttribute("usuario",real);
        return "/presentation/Index.jsp";
    }   

    public String logout(HttpServletRequest request){
        return this.logoutAction(request);
    }
    
    public String logoutAction(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.removeAttribute("usuario");
        session.invalidate();
        return "/presentation/Index.jsp";   
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
