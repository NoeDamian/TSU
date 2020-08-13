package TSU;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public final class DriverChecker {
    private static boolean[] drivers = new boolean[26];
    static File[] oldListRoot = File.listRoots();
    public static String usbdesc;
    public static String driver;
    public static String desc; 
    public static String estado;
  	public static File[] Unidades = File.listRoots();
  	public static JTextField [] estados = new JTextField[Unidades.length];
  	public static JTextField [] ids = new JTextField[Unidades.length];
  	public static String idusbtxt = null;


    public static JCheckBox [] dispocitivos = new JCheckBox[Unidades.length];


    public static void deteccion() throws NoSuchAlgorithmException, IOException, ParserConfigurationException, SAXException {
    	   while (true) {
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               
               if (File.listRoots().length > oldListRoot.length) {
                   	System.out.println("new drive detected");
	        		verificacionmultiple.ventana.removeAll();

                	int fila = 10; 

                	
                   	oldListRoot = File.listRoots();
                  	File[] Unidades = File.listRoots();
          			Object it[]=new Object[Unidades.length];
          			JCheckBox [] dispocitivos = new JCheckBox[Unidades.length];
          			          			
          			for (int i=0;i<Unidades.length;i++) {
          			  File s1 = (Unidades[i]);
          		         it[i]=s1;
          		         String s2 = FileSystemView.getFileSystemView().getSystemTypeDescription(Unidades[i]);
          		         if(s2.equals("Removable Disk")) {
          		        	 usbdesc = (it[i]+s2);
          		        	 desc=s2;
          		        	 driver = it[i].toString(); 
          		        	 verificacionmulti();
                            System.out.println(usbdesc+driver+desc);

          		        	dispocitivos[i] = new JCheckBox();
          		        	estados[i]= new JTextField();
          		        	ids[i] = new JTextField();
          		        	
          	        		dispocitivos[i].setText(driver+desc);
          	        		estados[i].setText(estado);
          	        		if(estado.equals("COINCIDE")) {
          	        			estados[i].setBackground(new Color(102, 204, 51));
          	        		}else if (estado.equals("NO COINCIDE")) {
          	        			estados[i].setBackground(new Color(255, 0, 0));
          	        		}
          	        		isusb();
          	        		ids[i].setText(idusbtxt);
          	        		
          	        		dispocitivos[i].setBounds(0, fila, 140, 23);         	      		
          	        		estados[i].setBounds(314, fila, 86, 20);
          	        		ids[i].setBounds(194, fila, 86, 20);
          	      		
          	        		dispocitivos[i].setSelected(true);
          	        		estados[i].setEnabled(false);
          	        		ids[i].setEnabled(false);
          	        		
          	        		verificacionmultiple.ventana.add(dispocitivos[i]);
          	        		verificacionmultiple.ventana.add(estados[i]);
          	        		verificacionmultiple.ventana.add(ids[i]);
          	        		fila+=30; 
          	        		verificacionmultiple.ventana.repaint();
          	        		
          		        	
            		       }
          		         
          		     }
                   

               } else if (File.listRoots().length < oldListRoot.length) {
            	   System.out.println(" drive removed");
                   oldListRoot = File.listRoots();
                   int fila = 10; 
                   verificacionmultiple.ventana.removeAll();

               	
                  	oldListRoot = File.listRoots();
                 	File[] Unidades = File.listRoots();
         			Object it[]=new Object[Unidades.length];
         			JCheckBox [] dispocitivos = new JCheckBox[Unidades.length];
         			
         			for (int i=0;i<Unidades.length;i++) {
         			  File s1 = (Unidades[i]);
         		         it[i]=s1;
         		         String s2 = FileSystemView.getFileSystemView().getSystemTypeDescription(Unidades[i]);
         		         if(s2.equals("Removable Disk")) {
         		        	 usbdesc = (it[i]+s2);
         		        	 desc=s2;
         		        	 driver = it[i].toString();
         		       
                           System.out.println(usbdesc+driver+desc);

         		        	dispocitivos[i] = new JCheckBox();
         	        		dispocitivos[i].setText(driver+desc);
         	        		dispocitivos[i].setBounds(0, fila, 140, 23);
          	        		dispocitivos[i].setSelected(true);

         	        		verificacionmultiple.ventana.add(dispocitivos[i]);         	        	
         	        		fila+=30;         	        		
         	        		verificacionmultiple.ventana.repaint();
         		        	
           		       }
         		         
         		     }
                  
                   
               }

           }
       }
    public static void verificacionmulti() throws NoSuchAlgorithmException, IOException, ParserConfigurationException, SAXException  {
    	String filehash = hash();
		String hsh = leerxml();
		
		if (filehash.equals(hsh)){
		    /*id.setText("Coinciden");
			id.setBackground(new Color(102, 204, 51));
			ESTADO = "HABILITADO";*/
			System.out.print("coinciden");
			estado = "COINCIDE";
	    }
	    else {
	    	
	    		/*estado.setText("No Coinciden");
	    		estado.setBackground(new Color(255, 0, 0));
	    		ESTADO="DESHABILITADO";*/
	    	System.out.print("no coinciden");
	    	estado = "NO COINCIDE";
	    		}

    	
    }
    public static String hash() throws IOException, NoSuchAlgorithmException {
    	String  ruta = driver;
    	String rutaexistente = null;
    	File rutamemtestdrive = new File(ruta+"\\EFI\\BOOT\\mt86.cfg");
		File rutanetwork  = new File(ruta+"\\Test Software Utilities\\");
		//creamos rutas ya conocidas para hacer el proceso mas sencillo de solo seleccionar el drive para hacer la verificacion
		if(!ruta.equals(null)) {
			if(rutamemtestdrive.exists()) {
			rutaexistente = rutamemtestdrive.toString();
				}else if(rutanetwork.exists()) {
			JOptionPane.showMessageDialog(null, "Es el drive"+ruta+"de test Engineering");

				}else {
					JOptionPane.showMessageDialog(null, "Ruta Invalida"+ruta);
				}
		}
     	File fichero = new File(rutaexistente);
		
		byte[] buffer= new byte[8192];
		int count;
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fichero));
		while ((count = bis.read(buffer)) > 0) {
        digest.update(buffer, 0, count);
    }
    bis.close();

    byte[] hash = digest.digest();
    String filehash= Base64.getEncoder().encodeToString(hash);
    
	return filehash;
    }
    
    public static  String leerxml() throws ParserConfigurationException, SAXException, IOException {
		String N = (String) (verificacionmultiple.comboboxtsu.getSelectedItem());
		String remplazo = N.replace(".xml", "");
        File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\"+remplazo+".xml");
		String hash21 = "";
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
		Document document = documentBuilder.parse(archivo);
		
		document.getDocumentElement().normalize();
		//en la creacion de XML hagarra como  nombre del nodo como nodo hijo principal
		 NodeList listaEmpleados = document.getElementsByTagName("Catalogo");
		 
		 for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
	         Node nodo = listaEmpleados.item(temp);
	         nodo.getChildNodes().getLength();
	         NodeList nodeList = document.getDocumentElement().getChildNodes();
	         for (int i = 0; i < nodeList.getLength(); i++) {
	             Node node = nodeList.item(i);
	             Element elem = (Element) node;

	              hash21 = elem.getElementsByTagName("hash").item(0).getChildNodes().item(0).getNodeValue();
                  }
		 }
		return hash21;
		
		}
   public static void isusb() {
	   String ruta =  driver;
		//String Es = ESTADO;
		String rutamodificada = ruta.replace("EFI\\BOOT\\mt86.cfg", "");
		
		 File archivo = null;
	     FileReader fr = null;
	     BufferedReader br = null;
	     try {
	    	 archivo = new File (ruta+"ID.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	         String linea;
	         while((linea=br.readLine())!=null) {
	         idusbtxt=linea;
	         
	         }
	      		}
	     		catch(Exception e){
	    	  	e.printStackTrace();
	      		}finally{
	    	  	try{                    
	        	 	if( null != fr ){   
	            		fr.close();     
	            	}                  
	         	}catch (Exception e2){ 
	        	 	e2.printStackTrace();
	         		}
	      		}
   }

    
}