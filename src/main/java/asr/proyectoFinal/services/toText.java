package asr.proyectoFinal.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;

import java.io.ByteArrayOutputStream;
import javax.servlet.ServletOutputStream;

/**
 * Servlet implementation class Controller
 */
@MultipartConfig
@WebServlet(urlPatterns = {"/toText"})
public class toText extends HttpServlet {
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {
    SpeechToText service = new SpeechToText();
    service.setUsernameAndPassword("420e2b65-c8d4-47b3-9d71-f1f5e3d0a12f", "KSJxK8YkVofE");

    Part filePart = request.getPart("audio"); // Retrieves <input type="file" name="file">
    
    
    
    
    String appPath = request.getServletContext().getRealPath("");
	 // constructs path of the directory to save uploaded file
	 String savePath = appPath + "audio";
	 // creates the save directory if it does not exists
	 File fileSaveDir = new File(savePath);
	 if (!fileSaveDir.exists()) {
	     fileSaveDir.mkdir();
	 }
	 
	 String savedFile = "";
	
	 for (Part part : request.getParts()) {
	         String fileName = extractFileName(part);
	         // refines the fileName in case it is an absolute path
	         fileName = new File(fileName).getName();
	         savedFile=savePath + File.separator + fileName;
	         part.write(savedFile);
	 }
    
	 String ext = savedFile.substring(savedFile.lastIndexOf(".")+1);    
    
    
    
    
    RecognizeOptions options = new RecognizeOptions.Builder()
      .model("es-ES_BroadbandModel").contentType("audio/mp3")
      .interimResults(true).maxAlternatives(3)
      .keywords(new String[]{"colorado", "tornado", "tornadoes"})
      .keywordsThreshold(0.5).build();
           

    BaseRecognizeCallback callback = new BaseRecognizeCallback() {
      @Override
      public void onTranscription(SpeechResults speechResults) {
        System.out.println(speechResults);
        //transcript = speechResults;
		
      }

      @Override
      public void onDisconnected() {
        System.exit(0);
      }
    };
    String resultado = new String("");
    
    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
    SpeechResults result = service.recognize(new File(savedFile), options).execute();
	if(result.getResults().size()>0) {
		 for (int i = 0; i <  result.getResults().size() ; i++)  {
		System.out.println(result.getResults().get(i).getAlternatives().get(0).getTranscript());
		resultado = resultado.concat(result.getResults().get(i).getAlternatives().get(0).getTranscript());
		}
	}
		
	
     //SpeechResults speechResults = service.recognize(dest, options).execute();
	request.setAttribute("transcript", resultado);  
	
	request.getRequestDispatcher("index.jsp").forward(request, response);
  }
  
  private String extractFileName(Part part) {
      String contentDisp = part.getHeader("content-disposition");
      String[] items = contentDisp.split(";");
      for (String s : items) {
          if (s.trim().startsWith("filename")) {
              return s.substring(s.indexOf("=") + 2, s.length()-1);
          }
      }
      return "";
  }
}

