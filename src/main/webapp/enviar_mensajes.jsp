<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Google Cloud Messaging</title>
</head>
<body>

<h3>Envio de mensajes vía GCM</h3>
<form action="DispositivoServlet" method="post">
	Usuario: <input type="text" name="txtUsuario"/><br/>
	Mensaje: <input type="text" name="txtMensaje"/><br/>
	<input type="submit" value="Enviar"/>
</form>
<%
String msg = (String)request.getAttribute("MSG");
if ( msg != null){ 
	out.println(msg);
}
%>
</body>
</html>