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
import java.io.FileInputStream;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;
import java.io.ByteArrayOutputStream;
import javax.servlet.ServletOutputStream;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/reproducir"})
public class Speech extends HttpServlet {
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {
    TextToSpeech service = new TextToSpeech();
    service.setUsernameAndPassword("db7fafaf-e144-42e7-8cc9-3fc26187db37", "OthRXbJqXAIf");

    //if(request.getParameter("language").equals("en")){

    String path = this.getServletContext().getRealPath("/")+"/Translate.wav";
    File yourFile = new File(path);
    yourFile.createNewFile(); // if file already exists will do nothing 
      try {
          String text = request.getParameter("palabrab");
          InputStream stream = service.synthesize(text, Voice.EN_ALLISON,AudioFormat.WAV).execute();
          InputStream in = WaveUtils.reWriteWaveHeader(stream);
          response.setContentType("audio/wav");
          response.setHeader("Content-Disposition", "attachment;filename=Translate.wav");
          response.setHeader("Pragma", "private");
          response.setHeader("Cache-Control", "private, must-revalidate");
          response.setHeader("Accept-Ranges", "bytes");
          response.setContentLength((int) yourFile.length());
          OutputStream output = response.getOutputStream();


          byte[] buffer = new byte[1024];
          int length;
          while ((length = in.read(buffer)) > 0) {
            output.write(buffer, 0, length);
          }
          output.close();
          in.close();
          stream.close();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
//      }else if(request.getParameter("language").equals("fr")){
//
//          String path = this.getServletContext().getRealPath("/")+"/Traduction.wav";
//          File yourFile = new File(path);
//          yourFile.createNewFile(); // if file already exists will do nothing 
//          try {
//            String text = request.getParameter("palabra");
//            InputStream stream = service.synthesize(text, Voice.FR_RENEE,AudioFormat.WAV).execute();
//            InputStream in = WaveUtils.reWriteWaveHeader(stream);
//            response.setContentType("audio/wav");
//            response.setHeader("Content-Disposition", "attachment;filename=Traduction.wav");
//            response.setHeader("Pragma", "private");
//            response.setHeader("Cache-Control", "private, must-revalidate");
//            response.setHeader("Accept-Ranges", "bytes");
//            response.setContentLength((int) yourFile.length());
//            OutputStream output = response.getOutputStream();
//
//
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = in.read(buffer)) > 0) {
//              output.write(buffer, 0, length);
//            }
//            output.close();
//            in.close();
//            stream.close();
//          }
//          catch (Exception e) {
//            e.printStackTrace();
//          }
//      }else if(request.getParameter("language").equals("es")){
//          String path = this.getServletContext().getRealPath("/")+"/Traduccion.wav";
//          File yourFile = new File(path);
//          yourFile.createNewFile(); // if file already exists will do nothing 
//          try {
//          String text = request.getParameter("palabra");
//          InputStream stream = service.synthesize(text, Voice.ES_LAURA,AudioFormat.WAV).execute();
//          InputStream in = WaveUtils.reWriteWaveHeader(stream);
//          response.setContentType("audio/wav");
//          response.setHeader("Content-Disposition", "attachment;filename=Traduccion.wav");
//          response.setHeader("Pragma", "private");
//          response.setHeader("Cache-Control", "private, must-revalidate");
//          response.setHeader("Accept-Ranges", "bytes");
//          response.setContentLength((int) yourFile.length());
//          OutputStream output = response.getOutputStream();
//
//
//          byte[] buffer = new byte[1024];
//          int length;
//          while ((length = in.read(buffer)) > 0) {
//            output.write(buffer, 0, length);
//          }
//          output.close();
//          in.close();
//          stream.close();
//        }
//        catch (Exception e) {
//          e.printStackTrace();
//        }
     // }
      
  }
}