package upc.edu.pe.servlet;

import com.google.android.gcm.server.Message;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import upc.edu.pe.dao.DispositivoDao;
import com.google.android.gcm.server.Sender;
/**
 *
 * @author Miguel Cardoso
 */
@WebServlet(name = "DispositivoServlet", urlPatterns = {"/DispositivoServlet"})
public class DispositivoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario = request.getParameter("txtUsuario");
        String mensaje = request.getParameter("txtMensaje");

//		long timestamp = (new Date()).getTime();
        DispositivoDao dao = new DispositivoDao();
        String codigoGCM = "";
        try {
            codigoGCM = dao.obtenerCodigoGCMPorUsuario(usuario).getCodigoGCM();
            System.out.println("CodigoGCM: " + codigoGCM);
            request.setAttribute("MSG", "Se envió correctamente el mensaje al dispositivo: " + usuario);
            


            Message msg = new Message.Builder().addData("MENSAJE", mensaje).build();
            Sender se = new Sender("AIzaSyCtx5Tx9tX0rEv6K9iuqZCnAlrdKVxC0hc");
            se.send(msg, codigoGCM, 0);

            RequestDispatcher rd = request.getRequestDispatcher("enviar_mensajes.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            System.out.println("Error en obtenerCodigoGCMPorUsuario(): " + e.getMessage());
            request.setAttribute("MSG", "Hubo un problema con el envío del mensaje: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("enviar_mensajes.jsp");
            rd.forward(request, response);
        }

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
