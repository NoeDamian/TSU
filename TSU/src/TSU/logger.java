package TSU;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class logger {
	static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
	static DateFormat time = new SimpleDateFormat("HH:mm:ss");
	static Date date = new Date();

	static String fecha =sdf.format(date);
	String hora = time.format(date);
	static FileWriter archivo ;
	  public static void crearLog(String Operacion) throws IOException {
		  	//File nombrearchivo = new File("M:\\\\Test Software Utilities\\log\\"+fecha+".txt");
	        //Pregunta el archivo existe, caso contrario crea uno con el nombre log.txt
	        if (new File("M:\\Test Software Utilities\\log\\"+fecha+".txt").exists()==false){
	        	archivo=new FileWriter(new File("M:\\\\Test Software Utilities\\log\\"+fecha+".txt"),false);
	        	}
	             archivo = new FileWriter(new File("M:\\\\Test Software Utilities\\log\\"+fecha+".txt"), true);
	             Calendar fechaActual = Calendar.getInstance(); //Para poder utilizar el paquete calendar    
	             //Empieza a escribir en el archivo
	             archivo.write((String.valueOf(fechaActual.get(Calendar.DAY_OF_MONTH))
	                  +"/"+String.valueOf(fechaActual.get(Calendar.MONTH)+1)
	                  +"/"+String.valueOf(fechaActual.get(Calendar.YEAR))
	                  +";"+String.valueOf(fechaActual.get(Calendar.HOUR_OF_DAY))
	                  +":"+String.valueOf(fechaActual.get(Calendar.MINUTE))
	                  +":"+String.valueOf(fechaActual.get(Calendar.SECOND)))+";"+Operacion+"\r\n");
	             archivo.close(); //Se cierra el archivo
	     }//Fin del metodo crearLog

}