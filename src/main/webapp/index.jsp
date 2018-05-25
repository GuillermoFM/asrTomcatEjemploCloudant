<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proyecto TRADUCTOR</title>
<script type="text/javascript">function traduccion () { 
	document.getElementById("linkDinamico1").setAttribute('href', 'insertar?palabra='+ document.getElementById("palabra").value);
	return true;
	}
function habla () { 
	document.getElementById("linkDinamico2").setAttribute('href', 'reproducir?palabra='+ document.getElementById("palabra").value);
	return true;
	}
function tono () { 
	document.getElementById("linkDinamico3").setAttribute('href', 'tono?palabra='+ document.getElementById("palabra").value);
	return true;
	}
</script>
</head>
<body>
<h1>Práctica Final Alfonso-Guillermonmnm,m </h1>

<p>Introduce una palabra a traducir </p>
<input id="palabra" ></input>
<p>Opciones :</p>
<ul>
<li><a href="listar">Listar base de datos</a></li>
<li><a id="linkDinamico1" href="insertar?palabra=TOKEN" onclick="return traduccion();">Traducir</a></li>
<li><a id="linkDinamico2" href="reproducir?palabra=TOKEN" onclick="return habla();">Hablar</a></li>
<li><a id="linkDinamico3" href="tono?palabra=TOKEN" onclick="return tono();">Sentimiento</a></li>

<form class="form-horizontal" action="toText" enctype="multipart/form-data" method="POST">
	    <fieldset>
	        <legend class="text-center h1">Procesamiento de audio</legend>
	        <div class="form-group" style="padding-top: 20px;">
	            <span class="col-md-1 col-md-offset-2 text-center">Introduzca el audio a analizar</span>
	            <div class="col-md-8">
	                <input type="file" name="audio" placeholder="Audio" class="form-control">
	            </div>
	        </div>

	        <div class="form-group">
	            <div class="col-md-12 text-center">
	                <button type="submit" class="btn btn-primary btn-lg">Procesar</button>
	            </div>
	        </div>
	    </fieldset>
	</form>
</ul>


</body>
</html>