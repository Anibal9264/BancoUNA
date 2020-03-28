package banca.presentation.Admin.addUser;
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

@WebServlet(name = "addUserController", urlPatterns = {"/presentation/admin/addUser/show","/presentation/admin/addUser/add"})
public class Controller extends HttpServlet {
protected void processRequest(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException {
         request.setAttribute("model",new Model());
        HttpSession session = request.getSession(true);
        Usuario real = (Usuario) session.getAttribute("admin");
        String viewUrl="";
        if(real!= null){
        switch(request.getServletPath()){
            case "/presentation/admin/addUser/show":
                viewUrl=this.show(request);
                break; 
            case "/presentation/admin/addUser/add":
                viewUrl=this.add(request);
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
        return "/presentation/admin/addUser/view.jsp"; 
    } 

   private String add(HttpServletRequest request) {
        try {
            Map<String, String> errores = this.validar(request);
            if (errores.isEmpty()) {
             return this.addAction(request);
            } else {
                return "/presentation/admin/addUser/view.jsp";
            }
        } catch (Exception e) {
                return "/presentation/admin/addUser/view.jsp";
        }
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

    private String addAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.logic.Model domainModel = banca.logic.Model.instance();
         Usuario cliente = new Usuario();
         cliente.setCedula(request.getParameter("Cedula_C"));
         cliente.setNombre(request.getParameter("Nombre_C"));
         cliente.setApellido1(request.getParameter("Apll1_C"));
         cliente.setApellido2(request.getParameter("Apll2_C"));
         cliente.setTelefono(request.getParameter("Tel_C"));
         cliente.setIs(is(request));
         cliente.setContraseña(GenPass());
         model.setUser(cliente);
         domainModel.UsuarioAdd(cliente);
         return "/presentation/admin/addUser/view.jsp";
    }

    private boolean is(HttpServletRequest request) {
      if(request.getParameter("Is_C").equals("Cliente")){
           return false;
      }
      return true;
    }

    private String GenPass() {
        String Key = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String pswd = ""; 
		for (int i = 0; i < 8; i++) {
			pswd+=(Key.charAt((int)(Math.random() * Key.length())));
                             }
     return pswd;
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
