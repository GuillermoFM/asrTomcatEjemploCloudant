<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proyecto TRADUCTOR</title>
<script>document.getElementById("linkDinamico").onclick = function () { 
	document.getElementById("linkDinamico").href.replace("TOKEN", document.getElementByID("palabra").value);
	}
</script>
</head>
<body>
<h1>Ejemplo de Proyecto de GuillermoDevOps- Traductor</h1>

<p>Introduce una palabra a traducir</p>
<input id="palabra" ></input>
<p>Opciones :</p>
<ul>
<li><a href="listar">Listar base de datos</a></li>
<li><a id="linkDinamico" href="insertar?palabra=TOKEN">Traducir</a></li>
</ul>
</body>
</html>