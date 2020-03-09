package banca.presentation.login;
import banca.logic.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/presentation/login/login","/presentation/login/logout"})
public class Controller extends HttpServlet {

  protected void processRequest(HttpServletRequest request,HttpServletResponse response)
         throws ServletException, IOException {
        request.setAttribute("model",new Model()); 
        String viewUrl="";
        switch(request.getServletPath()){
            case "/presentation/login/login":
                System.out.print("entro a controller");
                viewUrl=this.login(request);
                break;               
            case "/presentation/login/logout":
                viewUrl=this.logout(request);
                break;
        }
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

    private String login(HttpServletRequest request) { 
         Model model = (Model) request.getAttribute("model");
         Usuario u = new Usuario();
         u.setCedula(request.getParameter("cedula"));
         u.setContraseña(request.getParameter("password"));
         model.setUser(u);
         System.out.print(u.toString());
         return this.loginAction(request);  
        
    }
        
    public String loginAction(HttpServletRequest request) {
        
        Model model= (Model) request.getAttribute("model");
        System.out.print("Login Action1");
        banca.logic.Model domainModel = banca.logic.Model.instance();
        System.out.print("Login Action2");
        HttpSession session = request.getSession(true);
        try {
            System.out.print("Login Action3");
            Usuario real = domainModel.usuarioFind(model.getUser().getCedula(),model.getUser().getContraseña());
            System.out.print(real.toString());
            session.setAttribute("usuario", real);
            String viewUrl="";
            
            if(!real.getIs()){
            viewUrl="/presentation/Index.jsp";
            }else{
            viewUrl="";
            }
            return viewUrl;
        } catch (Exception ex) {
            return ""; 
        }        
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
