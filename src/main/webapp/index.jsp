<html>

<head>
	<link rel="stylesheet" type="text/css" href="estilos.css">
	<link rel="icon" href="images/icono-1.ico" type="image/x-icon" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
	<title>
		Guillarino's Dictionary
	</title>
</head>



<body>
<div id="cabecera">
	<div class="col-sm-6 col-sm-offset-3" id="titulo" style="text-align:center; color:#FFF; font-size:30px; padding-top:20px;">
			Guillarino's Dictionary
	</div> 
</div>

<div id="cuerpo">

	<form class="form-horizontal" action="toText" enctype="multipart/form-data" method="POST">
	    <fieldset>
	        <legend class="text-center h1">Procesamiento de audio</legend>
	        <br>
	        <div class="form-group" style="padding-top: 20px;">
	            <span class="col-md-3 col-md-offset-3 text-center">Introduzca un audio .MP3 en español para traducirlo al inglés y realizar un análisis de sentimientos</span>
	            <div class=" col-md-5">
	                <input type="file" name="audio" placeholder="Audio" class="form-control">
	            </div>
	        </div>
	        <div class="form-group">
	            <div class="col-md-12 text-center">
	            <br>
	            <br>
	                <button type="submit" class="btn btn-primary btn-lg">Procesar</button>
	            </div>
	        </div>
	    </fieldset>
	</form>
	
	<form class="col-md-offset-5 form-horizontal" action="listar" method="GET">
		<button type="submit" class="btn btn-success btn-lg">Traducciones anteriores</button>
	</form>


</div>
<footer id="footer">
	<a href="https://es-es.facebook.com/ibmwatson" ><div class="col-md-offset-2 col-md-8"><img style="height:70%;width:8%;padding-top:20px;padding-left:12px" src="images/facebook.png"></a><a href="https://twitter.com/ibmwatson?lang=es"><img style="height:70%;width:8%;padding-top:20px;padding-left:12px" src="images/twitter.png"></a></div>
</footer>
</body>
</html>