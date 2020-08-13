package TSU;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.net.InetAddress;


public class Verificacion extends JFrame {

	public static JPanel ventana;
	File Unidades[];
		Object items[];
		
	File Archivos[];
	 	Object[] Arc;
	 	
		String nl = System.getProperty("line.separator");
	 private JTextField estado;
	 public String rutamaestra;

	JComboBox comboboxtsu = new JComboBox();
	public String ESTADO;
    String idusb = null;
    public String usuario= System.getProperty("user.name");
    public String Equipolocal;
   
	//fpublic String ruta;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				

				try {
					Verificacion frame = new Verificacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public Verificacion(){
		
		listadearchivo();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Verificacion");
		setBounds(100, 100, 242, 259);
		ventana = new JPanel();
		ventana.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ventana);
		ventana.setLayout(null);

		JButton btnVerificar = new JButton("<html><center>Seleccionar Driver </br>  Verificar</center></html>");
		btnVerificar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(Equipolocal);

						try {
							verificar();
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParserConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SAXException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TransformerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					
				}
		});				
		
		
		
		 
		btnVerificar.setBounds(20, 134, 176, 84);
		ventana.add(btnVerificar);
		
		JLabel lblSeleccioneTsu = new JLabel("Seleccione  TSU");
		lblSeleccioneTsu.setBounds(44, 11, 101, 14);
		ventana.add(lblSeleccioneTsu);
		
		comboboxtsu.setBounds(20, 36, 176, 20);
		ventana.add(comboboxtsu);
		
		estado = new JTextField();
		estado.setForeground(new Color(255, 255, 255));
		estado.setFont(new Font("Times New Roman", Font.BOLD, 18));
		estado.setHorizontalAlignment(SwingConstants.CENTER);
		estado.setBackground(new Color(255, 255, 255));
		estado.setEditable(false);
		estado.setBounds(10, 69, 206, 54);
		ventana.add(estado);
		estado.setColumns(10);
		
		
	}
	public String creacionruta() throws IOException {
		
		JFileChooser fileChooser = new JFileChooser();
		//problema al momento de cerrar la app o darle cancelar, se vuelve a abrir y da un error en la funcion hash al no recivir la ruta.
		//fileChooser.setCurrentDirectory(new java.io.File("M:\\\\Test Software Utilities\\Users\\noe_moreno\\Desktop\\"));
		//fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//FileNameExtensionFilter filter = new FileNameExtensionFilter("CFG","cfg");
		//fileChooser.setFileFilter(filter);
		//fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		 if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
	       
			  rutamaestra =  (fileChooser.getSelectedFile().toString());

		 	}else if(fileChooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION) {
		 	}		 	
			 
		return rutamaestra;	
	}

	
	public  String leerxml() throws ParserConfigurationException, SAXException, IOException {
		String N = (String) (comboboxtsu.getSelectedItem());
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
	public  String hash() throws NoSuchAlgorithmException, IOException, ParserConfigurationException, SAXException {
		//necesita la ruta para acceder al archivo seleccionado y ponerle el hash
		
		String ruta= creacionruta();
		String rutaexistente = null ;
		File rutadrive = new File(ruta);
		File rutamemtestdrive = new File(ruta+"\\EFI\\BOOT\\mt86.cfg");
		File rutanetwork  = new File(ruta+"\\Test Software Utilities\\");
		//creamos rutas ya conocidas para hacer el proceso mas sencillo de solo seleccionar el drive para hacer la verificacion
		if(rutamemtestdrive.exists()) {
			rutaexistente = rutamemtestdrive.toString();
		}else if(rutanetwork.exists()) {
			JOptionPane.showMessageDialog(null, "Es el drive"+ruta+"de test Engineering");

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
	public void verificar() throws ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException, TransformerException {
		String filehash = hash();
		String hsh = leerxml();
		
		if (filehash.equals(hsh)){
		    estado.setText("Coinciden");
			estado.setBackground(new Color(102, 204, 51));
			ESTADO = "HABILITADO";
	    }
	    else {
	    	
	    		estado.setText("No Coinciden");
	    		estado.setBackground(new Color(255, 0, 0));
	    		ESTADO="DESHABILITADO";
	    		}

		registro();
	}
	
	public void listadearchivo() {
	File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\");
		CrearTSU frametsu = new CrearTSU();
		String[] listado = archivo.list();
		if (listado == null || listado.length == 0) {
			
		 JOptionPane.showMessageDialog(null, "No hay elementos TSU"+nl+"Crear TSU desado");
		 frametsu.setVisible(true);
		 
		}
		else {
			Object arc[]=new Object[listado.length];
			for(int i =0;i<listado.length;i++) {
				
				String s2=(listado[i]);
				arc[i]=s2;
			}
			Arc = arc;
			comboboxtsu.removeAllItems();
			  comboboxtsu.update(null);
		 for (int i=0; i< listado.length; i++) {
			 comboboxtsu.addItem(Arc[i]);

		 }
		}
		}
public void registro() throws IOException, ParserConfigurationException, TransformerException, SAXException {

	String ruta =  rutamaestra;
	String Es = ESTADO;
	String rutamodificada = ruta.replace("EFI\\BOOT\\mt86.cfg", "");
	
	 File archivo = null;
     FileReader fr = null;
     BufferedReader br = null;
     try {
    	 archivo = new File (rutamodificada+"ID.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         String linea;
         while((linea=br.readLine())!=null) {
         idusb=linea;
         
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
     String rutaregistro = "M:\\\\Test Software Utilities\\Archivo\\Registros\\Registro.xml";
     File registrofile = new File(rutaregistro);
     String ES = ESTADO;
     
     if(!registrofile.exists()) {
     DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docbuilder = docfactory.newDocumentBuilder();

		Document doc = docbuilder.newDocument();
		Element rootElement = doc.createElement("Registro");
		doc.appendChild(rootElement);
		Element identificacion = doc.createElement("Resultado");
		rootElement.appendChild(identificacion);
		
		
		Element DI = doc.createElement("ID");
		DI.appendChild(doc.createTextNode(""+idusb));
		identificacion.appendChild(DI);
		
		Element ESTA = doc.createElement("ESTADO");
		ESTA.appendChild(doc.createTextNode(""+ES));
		identificacion.appendChild(ESTA);
		

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(registrofile);
			
				transformer.transform(source, result);
     }
		if(ES.equals("HABILITADO")) {
			Habilitado();
			
		}
		else if(ES.equals("DESHABILITADO")) {
			Deshabilitado();
			
		}
	
		}


public void Habilitado() throws ParserConfigurationException, SAXException, IOException, TransformerException {
	String rutaarchivo = "M:\\\\Test Software Utilities\\Archivo\\Registros\\Registro.xml";
	String user =usuario;
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat time = new SimpleDateFormat("HH:mm:ss");

	//Date date = Calendar.getInstance().getTime();
	String fecha =sdf.format(date);
	String hora = time.format(date);
	
	
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
	Document doc = documentBuilder.parse(rutaarchivo);
	doc.getDocumentElement().normalize();
	
	Node nodoraiz  = doc.getDocumentElement();
	
	Element Nuevodato = doc.createElement("Resultado");
	
	Element nuevoid = doc.createElement("ID");
	nuevoid.setTextContent(idusb);
	Nuevodato.appendChild(nuevoid);
	
	Element nuevoestado = doc.createElement("ESTADO");
	nuevoestado.setTextContent("HABILITADO");
	Nuevodato.appendChild(nuevoestado);
	
	Element usuario = doc.createElement("USUARIO");
	usuario.setTextContent(user);
	Nuevodato.appendChild(usuario);
	
	Element equipo = doc.createElement("EQUIPO");
	equipo.setTextContent(Equipolocal);
	Nuevodato.appendChild(equipo);
	
	Element F = doc.createElement("FECHA");
	F.setTextContent(fecha);
	Nuevodato.appendChild(F);
	
	Element H = doc.createElement("HORA");
	H.setTextContent(hora);
	Nuevodato.appendChild(H);	
	
	
	nodoraiz.appendChild(Nuevodato);
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);

	StreamResult result = new StreamResult(new File("M:\\\\Test Software Utilities\\Archivo\\Registros\\Registro.xml"));
		
			transformer.transform(source, result);
}
public void Deshabilitado() throws ParserConfigurationException, SAXException, IOException, TransformerException {
	String rutaarchivo = "M:\\\\Test Software Utilities\\Archivo\\Registros\\Registro.xml";
	String user =usuario;
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat time = new SimpleDateFormat("HH:mm:ss");

	//Date date = Calendar.getInstance().getTime();
	String fecha =sdf.format(date);
	String hora = time.format(date);
	
	
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
	Document doc = documentBuilder.parse(rutaarchivo);
	doc.getDocumentElement().normalize();
	
	Node nodoraiz  = doc.getDocumentElement();
	
	Element Nuevodato = doc.createElement("Resultado");
	
	Element nuevoid = doc.createElement("ID");
	nuevoid.setTextContent(idusb);
	Nuevodato.appendChild(nuevoid);
	
	Element nuevoestado = doc.createElement("ESTADO");
	nuevoestado.setTextContent("DESHABILITADO");
	Nuevodato.appendChild(nuevoestado);
	
	Element usuario = doc.createElement("USUARIO");
	usuario.setTextContent(user);
	Nuevodato.appendChild(usuario);
	
	Element equipo = doc.createElement("EQUIPO");
	equipo.setTextContent(Equipolocal);
	Nuevodato.appendChild(equipo);
	
	
	Element F = doc.createElement("FECHA");
	F.setTextContent(fecha);
	Nuevodato.appendChild(F);
	
	Element H = doc.createElement("HORA");
	H.setTextContent(hora);
	Nuevodato.appendChild(H);	
	
	
	nodoraiz.appendChild(Nuevodato);
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);

	StreamResult result = new StreamResult(new File("M:\\\\Test Software Utilities\\Archivo\\Registros\\Registro.xml"));
		
			transformer.transform(source, result);
}


	}
	
	

	
