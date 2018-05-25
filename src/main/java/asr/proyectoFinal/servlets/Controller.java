package asr.proyectoFinal.servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asr.proyectoFinal.dao.CloudantPalabraStore;
import asr.proyectoFinal.dominio.Palabra;
import asr.proyectoFinal.services.Traductor;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;


/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/listar", "/insertar", "/hablar","/tono"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset=\"UTF-8\"></head><body>");
		
		CloudantPalabraStore store = new CloudantPalabraStore();
		System.out.println(request.getServletPath());
		switch(request.getServletPath())
		{
			case "/listar":
				if(store.getDB() == null)
					  out.println("No hay DB");
				else
					out.println("Palabras traducidas en la BD Cloudant:<br />" + store.getAll());
				break;
				
			case "/insertar":
				Palabra palabra = new Palabra();
				String parametro = request.getParameter("palabra");			
				String parametroTraducido = Traductor.translate(parametro);

				if(parametro==null)
				{
					out.println("usage: /insertar?palabra=palabra_a_traducir");
				}
				else
				{
					if(store.getDB() == null) 
					{
						out.println(String.format("Palabra: %s", palabra));
					}
					else
					{
						palabra.setName(parametroTraducido);
						store.persist(palabra);
					    out.println(String.format("Traducida la palabra %s. Resultado de la traducción guardado en BD: %s", parametro,palabra.getName()));			    	  
					}
				}
				break;
			case "/tono":
				 
			      ToneAnalyzer service = new ToneAnalyzer("2017-09-21"); 
				  service.setUsernameAndPassword("b978973e-8848-4b24-b779-3e631482e9a2", "bRXpxg2hLJSW");
//				  service.setEndPoint("https://gateway.watsonplatform.net/tone-analyzer/api");				  
				  String tono = request.getParameter("palabra");
				  ToneOptions toneOptions = new ToneOptions.Builder().text(tono).build();
				  ToneAnalysis tone = service.tone(toneOptions).execute();
				  
				
				      
				     
				   System.out.println(tone);
				   request.setAttribute("transcript", tone);
				   
				   request.getRequestDispatcher("feedback.jsp").forward(request, response);
				   
				   
//				  tono= "" ;
//				  double scoreanterior=0;
//			      for (ToneScore toneScore : tone.getDocumentTone().getTones().get(0).) {
//			    	  if(toneScore.getScore()>scoreanterior)
//			    	  {
//			    		  scoreanterior=toneScore.getScore();
//			              String t = toneScore.getToneName()+ "\n";
//			              //toneString.append(t);
//			              tono=t;
//			    	  }
//			    	  
//			    	  out.println(String.format("Tono: %s", tono));			    	  
//		}		
			      	  
				break;
//			case "/traducir":
//				//Palabra palabrab = new Palabra();
//				String parametrob = request.getParameter("palabra");			
//				String parametroTraducidob = Traductor.translate(parametrob);

//				if(parametrob==null)
//				{
//					out.println("usage: /insertar?palabra=palabra_a_traducir");
//				}
//				else
//				{
//					if(store.getDB() == null) 
//					{
//						out.println(String.format("Palabra: %s", palabrab));
//					}
//					else
//					{
//						palabrab.setName(parametroTraducidob);
//						store.persist(palabrab);
//					    out.println(String.format("Traducida prueba la palabra %s. Resultado de la traducción guardado en BD: %s", parametrob,palabrab.getName()));			    	  
//					}
//				}
//				break;
	}
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
