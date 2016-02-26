package upc.edu.pe.cloud;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import upc.edu.pe.dao.DispositivoDao;
import upc.edu.pe.type.Dispositivo;

/**
 *
 * @author Miguel Cardoso
 */
@Path("/dispositivos")
public class DispositivoRest {
    
    
    @GET
    public Response getMsg() {

        String output = "CloudMessage Service is OK";

        return Response.status(200).entity(output).build();

    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertar(String dispositivo) {

        System.out.println("Dispositivo Insertar : " + dispositivo);
        String respuesta = "ERROR"; 
        Gson gson = new Gson();
        Response response = null;
        
        try {           
             //Lee el json que es enviado
            Type stringStringMap = new TypeToken<Map<String, Object>>() {}.getType();
            final Map<String, Object> informacion = gson.fromJson(dispositivo, stringStringMap);
            String usuario = String.valueOf(informacion.get("usuario"));
            String codigoGCM = String.valueOf(informacion.get("codigoGCM"));

            //Instanciamos nuestro type
            Dispositivo modelo = new Dispositivo();
            modelo.setUsuario(usuario);
            modelo.setCodigoGCM(codigoGCM);            

            //Intanciamos nuestro DAO
            DispositivoDao dao = new DispositivoDao();
            Dispositivo vo = dao.obtenerCodigoGCMPorUsuario(usuario);
            
            if (null == vo.getCodigoGCM()) {
                dao.insertar(modelo);
            } else {
                dao.actualizar(modelo);
            }
            respuesta = "CORRECTO";
            response = Response.status(201).entity(respuesta).build();

        } catch (Exception e) {
            respuesta = "ERROR";
            response = Response.status(404).entity(respuesta).build();
            System.out.println(e.getMessage());
        }

        return response;
    }

}