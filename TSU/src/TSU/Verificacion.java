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
	 private JTextField estado1;
	 public String rutamaestra;

	JComboBox comboboxtsu = new JComboBox();
	public String ESTADO;
    String idusb = null;
    public String usuario= System.getProperty("user.name");
    public String Equipolocal;
    public String hashbootia;
	public String hashbootx;
	JTextField txthash;
	JTextField txthash32;
	JTextField txthash64;
	int cantidadestados ;
	int fallascfg;
	int fallasboot32;
	int fallasboot64;
	
	
	
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
				//System.out.println(Equipolocal);

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
		
		estado1 = new JTextField();
		estado1.setForeground(new Color(255, 255, 255));
		estado1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		estado1.setHorizontalAlignment(SwingConstants.CENTER);
		estado1.setBackground(new Color(255, 255, 255));
		estado1.setEditable(false);
		estado1.setBounds(10, 69, 206, 54);
		ventana.add(estado1);
		estado1.setColumns(10);
		
		comboboxtsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombre = null;
		        String versiones =null;
		        String estado= null;
		        String hash=null;
		        String fecha= null;
		        String tipo=null;
		        String comentarios=null;
		        String hashbootai = null;
		        String hashbootx= null;
				String Ruta = (String) (comboboxtsu.getSelectedItem());
				 File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\"+Ruta);
				 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = null;
					try {
						documentBuilder = dbf.newDocumentBuilder();
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Document document = null;
					try {
						document = documentBuilder.parse(archivo);
					} catch (SAXException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
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
			//al no ser simepre mementes se creara una condicion para saber cuantos datos estan en el xml
				             //cantidad de nodos hijos de algun otro archivo 7
				             if(nodo.getChildNodes().getLength()==7) {
				            	 ventana.removeAll();
				            	 ventana.add(lblSeleccioneTsu);
				            	 ventana.add(btnVerificar);
				            	 ventana.add(comboboxtsu);
				            	 ventana.add(estado1);
				            	 ventana.repaint();
				            	 nombre = elem.getElementsByTagName("Nombre").item(0).getChildNodes().item(0).getNodeValue();
					              versiones = elem.getElementsByTagName("versiones").item(0).getChildNodes().item(0).getNodeValue();
					              estado = elem.getElementsByTagName("estado").item(0).getChildNodes().item(0).getNodeValue();
					              hash = elem.getElementsByTagName("hash").item(0).getChildNodes().item(0).getNodeValue();		         
					              fecha = elem.getElementsByTagName("fecha").item(0).getChildNodes().item(0).getNodeValue();
					              tipo = elem.getElementsByTagName("tipo").item(0).getChildNodes().item(0).getNodeValue();
					              comentarios = elem.getElementsByTagName("comentarios").item(0).getChildNodes().item(0).getNodeValue();
					              
					              ventana.updateUI();
					              }
				             else if(nodo.getChildNodes().getLength()==9) {
				            	 	ventana.removeAll();
									ventana.repaint();
									try {
										loggerfallas.crearLog("!!!Iniciando Verificacion!!!");
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									nombre = elem.getElementsByTagName("Nombre").item(0).getChildNodes().item(0).getNodeValue();
									versiones = elem.getElementsByTagName("versiones").item(0).getChildNodes().item(0).getNodeValue();
						            estado = elem.getElementsByTagName("estado").item(0).getChildNodes().item(0).getNodeValue();
						            hash = elem.getElementsByTagName("hash").item(0).getChildNodes().item(0).getNodeValue();
						            hashbootai = elem.getElementsByTagName("hashbootia32").item(0).getChildNodes().item(0).getNodeValue();						            
						            hashbootx = elem.getElementsByTagName("hashbootx64").item(0).getChildNodes().item(0).getNodeValue();
						            fecha = elem.getElementsByTagName("fecha").item(0).getChildNodes().item(0).getNodeValue();
						            tipo = elem.getElementsByTagName("tipo").item(0).getChildNodes().item(0).getNodeValue();
						            comentarios = elem.getElementsByTagName("comentarios").item(0).getChildNodes().item(0).getNodeValue();
						            
						            JLabel lblSeleccioneTsu = new JLabel("Seleccione  TSU");
						    		lblSeleccioneTsu.setBounds(44, 11, 101, 14);
						    		ventana.add(lblSeleccioneTsu);
						    		comboboxtsu.setBounds(20, 36, 176, 20);
						    		ventana.add(comboboxtsu);
						    		
						    		JLabel lblHash = new JLabel("hash");
						    		lblHash.setBounds(10, 70, 30, 30);
						    		ventana.add(lblHash);
						    		
						    			txthash = new JTextField();
						    			txthash.setBounds(130, 70, 90, 20);
						    			txthash.setEditable(false);
						    			ventana.add(txthash);
						    		
						    		JLabel lblHash32 = new JLabel("hash bootia32: ");
						    		lblHash32.setBounds(10, 100, 100, 30);
						    		ventana.add(lblHash32);
						    			
						    			txthash32 = new JTextField();
						    			txthash32.setBounds(130, 105, 90, 20);
						    			txthash32.setEditable(false);
						    			ventana.add(txthash32);
						    		
						    		JLabel lblHash64 = new JLabel("hash bootx64: ");
						    		lblHash64.setBounds(10, 130, 100, 30);
						    		ventana.add(lblHash64);
						    		
						    			txthash64 = new JTextField();
						    			txthash64.setBounds(130, 135, 90, 20);
						    			txthash64.setEditable(false);
						    			ventana.add(txthash64);
						    		
						    		JButton btnVerificar = new JButton("<html><center>Seleccionar Driver </br>  Verificar</center></html>");
						    		btnVerificar.setFont(new Font("Tahoma", Font.PLAIN, 16));
						    		btnVerificar.addActionListener(new ActionListener() {
						    			public void actionPerformed(ActionEvent arg0) {
						    				//System.out.println(Equipolocal);

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
						    		
						    		
						    		
						    		 
						    		btnVerificar.setBounds(20, 165, 176, 54);
						    		ventana.add(btnVerificar);
						     }
				       }
				 }			
			}
		});
		
		comboboxtsu.setBounds(20, 36, 176, 20);
		ventana.add(comboboxtsu);
		
		
		
		
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
		String hash32;
		 String hash64;
		 
		hashbootai();
		hashbootx();
		String Ruta = (String) (comboboxtsu.getSelectedItem());
		 File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\"+Ruta);
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = null;
			try {
				documentBuilder = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Document document = null;
			try {
				document = documentBuilder.parse(archivo);
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
		             String rutaarchivo = "M:\\\\Test Software Utilities\\Archivo\\Registros\\Registro de Fallas\\RegistroFallas.xml";
	            	 DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
	            	 DocumentBuilder documentBuilder1 =dbf1.newDocumentBuilder();
	            	 Document doc = documentBuilder1.parse(rutaarchivo);
	            	 Node nodoraiz = doc.getDocumentElement();

		         		Element identificacion = doc.createElement("Resultado");
		         		nodoraiz.appendChild(identificacion);
		         		
		         		Element DI = doc.createElement("ID");
		         		DI.appendChild(doc.createTextNode(""+idusb));
		         		identificacion.appendChild(DI);
		         		
		             if(nodo.getChildNodes().getLength()==7) {
		            	 if (filehash.equals(hsh)){
		            		 estado1.setText("Coinciden");
		            		 estado1.setBackground(new Color(102, 204, 51));
		            		 ESTADO = "HABILITADO";
		            		 loggerfallas.crearLog("La codificacion del archivo: "+hsh+" COINCIDE con la base de datos: "+filehash);
		            	 	}
		            	 else {
	    	
		            		 estado1.setText("No Coinciden");
		            		 estado1.setBackground(new Color(255, 0, 0));
		            		 cantidadestados=1;
		            		 ESTADO="DESHABILITADO";
		            		 loggerfallas.crearLog("La codificacion del archivo: "+hsh+" fallo al no COINCIDER con la base de datos: "+filehash);
		            	 }

		            	 	registro();
		             }
		             else if (nodo.getChildNodes().getLength()==9) {
		            	  hash32 = elem.getElementsByTagName("hashbootia32").item(0).getChildNodes().item(0).getNodeValue();
				          hash64 = elem.getElementsByTagName("hashbootx64").item(0).getChildNodes().item(0).getNodeValue();
		            	 if(filehash.equals(hsh)) {
		            		txthash.setText("COINCIDE");
		            		txthash.setBackground(Color.GREEN);
		            		Element hashcfg = doc.createElement("HASHCFG");
			         		hashcfg.appendChild(doc.createTextNode("COINCIDE"));
			         		identificacion.appendChild(hashcfg);
		            		loggerfallas.crearLog("La codificacion del archivo COINCIDE "+hsh+" a "+filehash);
		            	 	}else {
		            	 		txthash.setText("NO COINCIDE");
		            	 		txthash.setBackground(Color.RED);
		            	 		ESTADO="DESHABILITADO";
		            	 		Element hashcfg = doc.createElement("HASHCFG");
				         		hashcfg.appendChild(doc.createTextNode("NO COINCIDE"));
				         		identificacion.appendChild(hashcfg);
		            	 		loggerfallas.crearLog("La codificacion del archivo NO COINCIDE "+hsh+" a "+filehash);
		            	 		fallascfg=1;
		            		 cantidadestados = 1;
		            	 }
		            	if(hashbootia.contentEquals(hash32)) {
		            		 	txthash32.setText("COINCIDE");
		            		 	txthash32.setBackground(Color.GREEN);
		            		 	Element hashbootia = doc.createElement("HASHBOOTIA32");
				         		hashbootia.appendChild(doc.createTextNode("COINCIDE"));
				         		identificacion.appendChild(hashbootia);
		            		 	loggerfallas.crearLog("El segundo archivo critico COINCIDE con la base de datos "+hashbootia+" a "+hash32);
		            		}else {
		            			txthash32.setText("NO COINCIDE");
		            			txthash32.setBackground(Color.RED);
		            			ESTADO="DESHABILITADO";
		            			Element hashbootia = doc.createElement("HASHBOOTIA32");
				         		hashbootia.appendChild(doc.createTextNode("NO COINCIDE"));
				         		identificacion.appendChild(hashbootia);
		            			loggerfallas.crearLog("El segundo archivo critico NO COINCIDE con la base de datos "+hashbootia+" a "+hash32);
		            			fallasboot32=1;
		            			cantidadestados=2;
		            		 
	            		 }	
		            	if(hashbootx.equals(hash64)) {
		            			 	txthash64.setText("COINCIDE");
		            			 	txthash64.setBackground(Color.GREEN);
		            			 	ESTADO = "HABILITADO";
		            			 	Element hashbootx = doc.createElement("HASHBOOTX64");
		    		         		hashbootx.appendChild(doc.createTextNode("COINCIDE"));
		    		         		identificacion.appendChild(hashbootx);
				            		loggerfallas.crearLog("El tercer archivo critico COINCIDE con la base de datos "+hashbootx+" a "+hash64);


		            	}else {
		            			 txthash64.setText("NO COINCIDE ");
		            			 txthash64.setBackground(Color.RED);
		            			 ESTADO="DESHABILITADO";
		            			 Element hashbootx = doc.createElement("HASHBOOTX64");
		            			 hashbootx.appendChild(doc.createTextNode("NO COINCIDE"));
		            			 identificacion.appendChild(hashbootx);
		            			 fallasboot64=1;
		            			 cantidadestados = 3;
		            			 loggerfallas.crearLog("El tercer archivo critico NO COINCIDE con la base de datos "+hashbootx+" a "+hash64);

		            			 		} 
		            	
		            	int suma= fallascfg+fallasboot32+fallasboot64;
		            	if(fallascfg==1&&fallasboot32==1) {
		            		Element comentahashbootia = doc.createElement("Comentariohashbootia32");
			         		comentahashbootia.appendChild(doc.createTextNode("CFG Corrupto y BOOTIA32.efi Corrupto"));
			         		identificacion.appendChild(comentahashbootia);
		            	}
		            			            	TransformerFactory transformerFactory = TransformerFactory.newInstance();
		         		Transformer transformer = transformerFactory.newTransformer();
		         		DOMSource source = new DOMSource(doc);

		         		StreamResult result = new StreamResult(rutaarchivo);
		         			
		         				transformer.transform(source, result);
		            		 		}
		            		 
		            	 }
		            	 registro();
		            	 loggerfallas.crearLog("!!!Verificacion Terminada!!!");
		            	 
		         		//en documentbuilder.newdocument sirve para crear un documento xml 
		            	 //Document doc = documentBuilder1.newDocument();
		            	 
		            	// doc.getDocumentElement().normalize();
		            	 
		            	/* Element rootElement = doc.createElement("Registro");
		         		doc.appendChild(rootElement);*/
		         		
		         		
		         		
		         		
			 }
			}
		         			
		         		
		         		
		         		
		         		
		         		/*
		         		Element comenta = doc.createElement("Comentarios");
		         		comenta.appendChild(doc.createTextNode("Archivo criticos Pasaron Exitosamente"));
		         		identificacion.appendChild(comenta);
		         		}
		         		
		         		if(cantidadestados==3) {
		         			Element hashcfg = doc.createElement("HASHCFG");
			         		hashcfg.appendChild(doc.createTextNode("COINCIDE"));
			         		identificacion.appendChild(hashcfg);
			         		
		         			Element hashbootia = doc.createElement("HASHBOOTIA32");
			         		hashbootia.appendChild(doc.createTextNode("COINCIDE"));
			         		identificacion.appendChild(hashbootia);
			         		
		         			Element hashbootx = doc.createElement("HASHBOOTX64");
			         		hashbootx.appendChild(doc.createTextNode("NO COINCIDE"));
			         		identificacion.appendChild(hashbootx);
			         		
			         		Element comentahash = doc.createElement("Comentariohash");
			         		comentahash.appendChild(doc.createTextNode("BOOTX64.efi Corrupto"));
			         		identificacion.appendChild(comentahash);
			         		
		         		}if(cantidadestados==2) {
		         			Element hashcfg = doc.createElement("HASHCFG");
			         		hashcfg.appendChild(doc.createTextNode("COINCIDE"));
			         		identificacion.appendChild(hashcfg);
			         		
		         			Element hashbootia = doc.createElement("HASHBOOTIA32");
			         		hashbootia.appendChild(doc.createTextNode("NO COINCIDE"));
			         		identificacion.appendChild(hashbootia);
			         		
			         		Element hashbootx = doc.createElement("HASHBOOTX64");
			         		hashbootx.appendChild(doc.createTextNode("COINCIDE"));
			         		identificacion.appendChild(hashbootx);
			         		
			         		Element comentahashbootia = doc.createElement("Comentariohashbootia32");
			         		comentahashbootia.appendChild(doc.createTextNode("BOOTIA32.efi Corrupto"));
			         		identificacion.appendChild(comentahashbootia);
			         		
		         		}if(cantidadestados==1){
		         			Element hashcfg = doc.createElement("HASHCFG");
			         		hashcfg.appendChild(doc.createTextNode("NO COINCIDE"));
			         		identificacion.appendChild(hashcfg);
			         		
			         		Element hashbootia = doc.createElement("HASHBOOTIA32");
			         		hashbootia.appendChild(doc.createTextNode("COINCIDE"));
			         		identificacion.appendChild(hashbootia);
			         		
			         		Element hashbootx = doc.createElement("HASHBOOTX64");
			         		hashbootx.appendChild(doc.createTextNode("NO COINCIDE"));
			         		identificacion.appendChild(hashbootx);
			         		
			         		Element comentahashbootx = doc.createElement("Comentariohashbootx64");
			         		comentahashbootx.appendChild(doc.createTextNode("CFG Corrupto "));
			         		identificacion.appendChild(comentahashbootx);
		         		}		         		
		         		         	*/	

		         		
		            	 
		             
		             
		             
		         
		      
		
		
	
	
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
loggerfallas.crearLog("~~Iniciando Registro~~");
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
     if(cantidadestados==0) {
		if(ES.equals("HABILITADO")) {
			Habilitado();
			logger.crearLog("Equipo "+idusb+" se encuentra Habilitado");
			
		}}else if(cantidadestados==1 ||cantidadestados==2||cantidadestados==3) {
			if(ES.equals("DESHABILITADO")) {
			Deshabilitado();
			loggerfallas.crearLog("Equipo "+idusb+" se encuentra DESHABILITADO");
			
		}
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


public void hashbootai() throws NoSuchAlgorithmException, IOException {
String rutamodificada = rutamaestra+"EFI\\BOOT\\BOOTIA32.efi";
File rutanueva = new File(rutamodificada);
	
	byte[] buffer= new byte[8192];
	int count;
	MessageDigest digest = MessageDigest.getInstance("SHA-1");
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(rutanueva));
	while ((count = bis.read(buffer)) > 0) {
    digest.update(buffer, 0, count);
}
bis.close();

byte[] hash = digest.digest();
String filehash= Base64.getEncoder().encodeToString(hash);
hashbootia = filehash;
	
}
public void hashbootx() throws NoSuchAlgorithmException, IOException {
	String rutamodificada = rutamaestra+"EFI\\\\BOOT\\\\BOOTX64.efi";
	File rutanueva = new File(rutamodificada);
		
		byte[] buffer= new byte[8192];
		int count;
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(rutanueva));
		while ((count = bis.read(buffer)) > 0) {
	    digest.update(buffer, 0, count);
	}
	bis.close();

	byte[] hash = digest.digest();
	String filehash= Base64.getEncoder().encodeToString(hash);
	//txtbootx64.setText(filehash);
	hashbootx = filehash;
	
}	
}
	
	

	
