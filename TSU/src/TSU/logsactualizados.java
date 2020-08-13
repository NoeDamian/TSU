package TSU;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class logsactualizados {
	static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
	static DateFormat time = new SimpleDateFormat("HH:mm:ss");
	static Date date = new Date();
	static String fecha =sdf.format(date);
	public static String contenido;
	
	
	static File archivolog = new File("M:\\\\Test Software Utilities\\log\\"+fecha+".txt");
	static long archivologviejo  =  archivolog.length();
	public static void verlog() throws IOException {
		 while (true) {
             try {
                 Thread.sleep(100);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
           
		if(!archivolog.exists()) {
			JOptionPane.showMessageDialog(null, "no existe ningun archivo log con el nombre de: "+fecha+".txt");
		}
		else {
			if(archivolog.length() > archivologviejo) {
				
				FileReader fr = new FileReader(archivolog);
				BufferedReader br = new BufferedReader(fr);
				 String linea = br.readLine();
				while((linea=br.readLine())!=null)
						contenido = linea;
					 try{                    
				            if( null != fr ){   
				               fr.close();     
				            }                  
				         }catch (Exception e2){ 
				            e2.printStackTrace();
				         }
				archivologviejo = archivolog.length();
			}
		}
		
	 }
	}
	public static void main(String [] args) {
		try {
			verlog();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
