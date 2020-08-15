package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;

public class EditarTSU extends JFrame {

	private JPanel contentPane;
	private static JTextField txtnombre;
	static JComboBox comboboxtsu = new JComboBox();
 	Object[] Arc;
 	String nl = System.getProperty("line.separator");
 	static private JTextField txtversiones;
 	static private JTextField txthash;
 	static private JTextField txtfecha;
	static JComboBox comboboxestado = new JComboBox();
	static JComboBox comboboxtipo = new JComboBox();
	static JTextPane txtpcomentarios = new JTextPane();
	private static JTextField txtbootia32;
	private static  JTextField txtbootx64;
	static String Rutadrive;
	static String hashcfg;
	JPanel panel;
	JPanel panel_1;
	public String hashbootia;
	public String hashbootx;


	/**
	 * Lunch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarTSU frame = new EditarTSU();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditarTSU() {
		archivosxml();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Editar TSU");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 641, 419);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(70, 90, 154, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(10, 93, 60, 14);
		contentPane.add(lblNombres);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 152, 60, 14);
		contentPane.add(lblEstado);
		
		comboboxestado.setModel(new DefaultComboBoxModel(new String[] {"Habilitado", "Deshabilitado"}));
		comboboxestado.setBounds(70, 149, 154, 20);
		contentPane.add(comboboxestado);
		
		JLabel lblNewLabel = new JLabel("Comentarios");
		lblNewLabel.setBounds(10, 213, 116, 14);
		contentPane.add(lblNewLabel);
		
		txtpcomentarios.setBackground(new Color(230, 230, 250));
		txtpcomentarios.setBounds(10, 238, 234, 68);
		contentPane.add(txtpcomentarios);
		
		JButton btnNewButton_2 = new JButton("Guardar");
		btnNewButton_2.setIcon(new ImageIcon(EditarTSU.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ZipExample.main(null);
				try {
					editar();
				} catch (ParserConfigurationException | SAXException | IOException | TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(244, 347, 116, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblVersion = new JLabel("Version:");
		lblVersion.setBounds(10, 118, 60, 14);
		contentPane.add(lblVersion);
		
		txtversiones = new JTextField();
		txtversiones.setBounds(70, 121, 154, 20);
		contentPane.add(txtversiones);
		txtversiones.setColumns(10);
		
		JLabel lblHash = new JLabel("SHA-1");
		lblHash.setBounds(235, 124, 60, 14);
		contentPane.add(lblHash);
		
		txthash = new JTextField();
		txthash.setBounds(287, 121, 234, 20);
		contentPane.add(txthash);
		txthash.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(473, 93, 46, 14);
		contentPane.add(lblFecha);
		
		txtfecha = new JTextField();
		txtfecha.setBounds(529, 90, 86, 20);
		contentPane.add(txtfecha);
		txtfecha.setColumns(10);
		
		JLabel lblTipo = new JLabel("tipo");
		lblTipo.setBounds(10, 188, 46, 14);
		contentPane.add(lblTipo);
		
		comboboxtipo.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE", "SSD", "HD", "USB", "FLOPPY", "CD", "DVD", "FIREWARE"}));
		comboboxtipo.setBounds(70, 185, 154, 20);
		contentPane.add(comboboxtipo);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(179, 11, 242, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSeleccioneTsu = new JLabel("Seleccione TSU");
		lblSeleccioneTsu.setBounds(72, 11, 90, 14);
		panel.add(lblSeleccioneTsu);
		comboboxtsu.setBounds(10, 36, 223, 20);
		panel.add(comboboxtsu);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				
				fileChooser.setCurrentDirectory(new java.io.File("M:\\\\Test Software Utilities\\Users\\noe_moreno\\Desktop\\"));
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CFG","cfg");
				fileChooser.setFileFilter(filter);
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				 if (fileChooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) { 
			       
		              Rutadrive =  (fileChooser.getSelectedFile().toString());
				 }
				
						File sFichero = new File(Rutadrive);
						
						
						byte[] buffer= new byte[8192];
						int count;
						MessageDigest digest = null;
						try {
							digest = MessageDigest.getInstance("SHA-1");
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						BufferedInputStream bis = null;
						try {
							bis = new BufferedInputStream(new FileInputStream(sFichero));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							while ((count = bis.read(buffer)) > 0) {
							digest.update(buffer, 0, count);
}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					try {
						bis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					byte[] hash = digest.digest();
					String filehash= Base64.getEncoder().encodeToString(hash);
					txthash.setText(filehash);

					hashcfg = filehash;
					try {
						hashbootai();
					} catch (NoSuchAlgorithmException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						hashbootx();
					} catch (NoSuchAlgorithmException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		});
 		btnNewButton.setIcon(new ImageIcon(EditarTSU.class.getResource("/com/sun/java/swing/plaf/windows/icons/TreeClosed.gif")));
 		btnNewButton.setBounds(539, 118, 32, 21);
 		contentPane.add(btnNewButton);
 		
 		panel_1 = new JPanel();
 		panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
 		panel_1.setBackground(Color.WHITE);
 		panel_1.setBounds(244, 152, 371, 184);
 		contentPane.add(panel_1);
 		panel_1.setLayout(null);
		
		
		comboboxtsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionararchivo();
				} catch (ParserConfigurationException | SAXException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	public void archivosxml() {
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
	public void seleccionararchivo() throws ParserConfigurationException, SAXException, IOException {
		String N = (String) (comboboxtsu.getSelectedItem());
		String remplazo = N.replace(".xml", "");
        File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\"+remplazo+".xml");
        String nombre = null;
        String versiones =null;
        String estado= null;
        String hash=null;
        String fecha= null;
        String tipo=null;
        String comentarios=null;
        String hashbootai = null;
        String hashbootx= null;
        
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
//al no ser simepre mementes se creara una condicion para saber cuantos datos estan en el xml
	             //cantidad de nodos hijos de algun otro archivo 7
	             if(nodo.getChildNodes().getLength()==7) {
	            	 nombre = elem.getElementsByTagName("Nombre").item(0).getChildNodes().item(0).getNodeValue();
		              versiones = elem.getElementsByTagName("versiones").item(0).getChildNodes().item(0).getNodeValue();
		              estado = elem.getElementsByTagName("estado").item(0).getChildNodes().item(0).getNodeValue();
		              hash = elem.getElementsByTagName("hash").item(0).getChildNodes().item(0).getNodeValue();		         
		              fecha = elem.getElementsByTagName("fecha").item(0).getChildNodes().item(0).getNodeValue();
		              tipo = elem.getElementsByTagName("tipo").item(0).getChildNodes().item(0).getNodeValue();
		              comentarios = elem.getElementsByTagName("comentarios").item(0).getChildNodes().item(0).getNodeValue();

		              txtnombre.setText(nombre);
		              txtversiones.setText(versiones);
		              int numeroestado = 0;
		              if(estado.equals("Habilitado")) {
		            	  numeroestado=0;
		              }else if(estado.equals("Deshabilitado")){
		            	  numeroestado=1;
		              }
		              comboboxestado.setSelectedIndex(numeroestado);
		              
		              int numerotipo = 0 ;
		              
		              if(tipo.equals("SSD")) {
		            	  numerotipo=1;
		              }
		              else if(tipo.equals("HD")) {
		            	  numerotipo=2;
		              }else if(tipo.equals("USB")) {
		            	  numerotipo=3;
		            	  
		              }else if(tipo.equals("FLOPPY")) {
		            	  numerotipo=4 ;
		              }else if(tipo.equals("CD")) {
		            	  numerotipo=5 ;
		              }else if(tipo.equals("DVD")) {
		            	  numerotipo=6 ;
		              }else if(tipo.equals("FIREWALL")) {
		            	  numerotipo=7;
		              }
		              comboboxtipo.setSelectedIndex(numerotipo); 
		             txthash.setText(hash);
		            txtfecha.setText(fecha);
		            txtpcomentarios.setText(comentarios);
		            panel_1.removeAll();
		            contentPane.updateUI();
	             }
	             //cantidad de nodos hijos de un xml de Memtest 9
	             else if(nodo.getChildNodes().getLength()==9) {
	             nombre = elem.getElementsByTagName("Nombre").item(0).getChildNodes().item(0).getNodeValue();
	              versiones = elem.getElementsByTagName("versiones").item(0).getChildNodes().item(0).getNodeValue();
	              estado = elem.getElementsByTagName("estado").item(0).getChildNodes().item(0).getNodeValue();
	              hash = elem.getElementsByTagName("hash").item(0).getChildNodes().item(0).getNodeValue();
	          
	             hashbootai = elem.getElementsByTagName("hashbootia32").item(0).getChildNodes().item(0).getNodeValue();
	            
	             hashbootx = elem.getElementsByTagName("hashbootx64").item(0).getChildNodes().item(0).getNodeValue();

	              fecha = elem.getElementsByTagName("fecha").item(0).getChildNodes().item(0).getNodeValue();
	              tipo = elem.getElementsByTagName("tipo").item(0).getChildNodes().item(0).getNodeValue();
	              comentarios = elem.getElementsByTagName("comentarios").item(0).getChildNodes().item(0).getNodeValue();

	              txtnombre.setText(nombre);
	              txtversiones.setText(versiones);
	              int numeroestado = 0;
	              if(estado.equals("Habilitado")) {
	            	  numeroestado=0;
	              }else if(estado.equals("Deshabilitado")){
	            	  numeroestado=1;
	              }
	              comboboxestado.setSelectedIndex(numeroestado);
	              
	              int numerotipo = 0 ;
	              
	              if(tipo.equals("SSD")) {
	            	  numerotipo=1;
	              }
	              else if(tipo.equals("HD")) {
	            	  numerotipo=2;
	              }else if(tipo.equals("USB")) {
	            	  numerotipo=3;
	            	  
	              }else if(tipo.equals("FLOPPY")) {
	            	  numerotipo=4 ;
	              }else if(tipo.equals("CD")) {
	            	  numerotipo=5 ;
	              }else if(tipo.equals("DVD")) {
	            	  numerotipo=6 ;
	              }else if(tipo.equals("FIREWALL")) {
	            	  numerotipo=7;
	              }
	              comboboxtipo.setSelectedIndex(numerotipo);
	              
	             txthash.setText(hash);
	             
	            	
	         		
	         		JLabel lblShaBootaiefi = new JLabel("SHA-1 BOOTAI32.EFI:");
	         		lblShaBootaiefi.setBounds(0, 0, 126, 14);
	         		panel_1.add(lblShaBootaiefi);
	         		
	         		JLabel lblShaBootxefi = new JLabel("SHA-1 BOOTX64.EFI:");
	         		lblShaBootxefi.setBounds(0, 50, 126, 14);
	         		panel_1.add(lblShaBootxefi);
	         		
	         		txtbootia32 = new JTextField();
	         		txtbootia32.setBounds(0, 20, 274, 20);
	         		panel_1.add(txtbootia32);
	         		txtbootia32.setColumns(10);
	         		txtbootia32.setText(hashbootai);
	         		
	         		txtbootx64 = new JTextField();
	         		txtbootx64.setBounds(0, 70, 274, 20);
	         		panel_1.add(txtbootx64);
	         		txtbootx64.setColumns(10);
	         		txtbootx64.setText(hashbootx);
	         		
	         		/*JButton btnNewButton_1 = new JButton("");
	         		btnNewButton_1.setIcon(new ImageIcon(EditarTSU.class.getResource("/com/sun/java/swing/plaf/windows/icons/NewFolder.gif")));
	         		btnNewButton_1.setBounds(295, 20, 32, 21);
	         		panel_1.add(btnNewButton_1);
	         		
	         		JButton btnNewButton_3 = new JButton("");
	         		btnNewButton_3.setIcon(new ImageIcon(EditarTSU.class.getResource("/com/sun/java/swing/plaf/windows/icons/NewFolder.gif")));
	         		btnNewButton_3.setBounds(295, 70, 32, 21);
	         		panel_1.add(btnNewButton_3);*/
	             
	             
	            txtfecha.setText(fecha);
	            txtpcomentarios.setText(comentarios);

	             		}
                  }
		 }
		
		
	}
	public static void editar() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		logger.crearLog("!!!!--Copia De Seguridad Realizada--!!!!");
		String N = (String) (comboboxtsu.getSelectedItem());
		String remplazo = N.replace(".xml", "");
        File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\"+remplazo+".xml");
        String nombrex = txtnombre.getText();
        String reemplazonombrex = nombrex.replace(".xml", "");
        String versionesx = txtversiones.getText();
        String estadox = (String) comboboxestado.getSelectedItem();
        String hashx =  txthash.getText();
        String fechax =  txtfecha.getText();
        String tipox = (String) comboboxtipo.getSelectedItem() ;
        String comentariosx = txtpcomentarios.getText();
        String hashbootia = txtbootia32.getText();
		String hashbootx = txtbootx64.getText();
		logger.crearLog("Editando el archivo: "+remplazo);
		 String nombrez = null;
	        String versionesz = null;
	        String estadoz = null;
	        String hashz = null;
	        String fechaz = null;
	        String tipoz = null;
	        String comentariosz = null;
	        String hashboot32 = null;
	        String hashboot64 = null;
	       
		
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
		Document doc = documentBuilder.parse(archivo);
		NodeList nodo = doc.getElementsByTagName("Catalogo");
		
		//lectura del archivo para maneo de datos 
		NodeList listaEmpleados = doc.getElementsByTagName("Catalogo");
		 for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
	         Node nodo2 = listaEmpleados.item(temp);
	         nodo2.getChildNodes().getLength();
	         NodeList nodeList = doc.getDocumentElement().getChildNodes();
	         if(nodo2.getChildNodes().getLength()==7) {
	         for (int i = 0; i < nodeList.getLength(); i++) {
	             Node node = nodeList.item(i);
	             Element elem = (Element) node;
	             
	              nombrez = elem.getElementsByTagName("Nombre").item(0).getChildNodes().item(0).getNodeValue();
	              versionesz= elem.getElementsByTagName("versiones").item(0).getChildNodes().item(0).getNodeValue();
	              estadoz = elem.getElementsByTagName("estado").item(0).getChildNodes().item(0).getNodeValue();
	              hashz = elem.getElementsByTagName("hash").item(0).getChildNodes().item(0).getNodeValue();
	              fechaz = elem.getElementsByTagName("fecha").item(0).getChildNodes().item(0).getNodeValue();
	              tipoz = elem.getElementsByTagName("tipo").item(0).getChildNodes().item(0).getNodeValue();
	              comentariosz = elem.getElementsByTagName("comentarios").item(0).getChildNodes().item(0).getNodeValue();
	         }
	        
		 
		for(int i = 0; i <nodo.getLength();i++ ) {
			Element element = (Element) nodo.item(i);
			element.getParentNode().removeChild(element);
			
		}
		doc.getDocumentElement().normalize();
		Node nodoraiz = doc.getDocumentElement();
		Element nuevosdato = doc.createElement("Catalogo");
		
		Element Nombre = doc.createElement("Nombre");
		Nombre.appendChild(doc.createTextNode(""+reemplazonombrex));
		nuevosdato.appendChild(Nombre);
		if(!nombrex.equals(nombrez)) {logger.crearLog("Se Modifico el nombre del TSU:\""+nombrez+"\" a \""+nombrex+"\"");}
		else {logger.crearLog("Nombre del TSU: \""+nombrex+"\"");}
		
		Element versionesxml = doc.createElement("versiones");
		versionesxml.appendChild(doc.createTextNode(""+versionesx));
		nuevosdato.appendChild(versionesxml);
		if(!versionesx.equals(versionesz)) {logger.crearLog("Se Modifico las versiones: \""+versionesz+"\" a \""+versionesx+"\"");}
		else {logger.crearLog("Versiones: "+versionesx);}

		Element estado = doc.createElement("estado");
		estado.appendChild(doc.createTextNode(""+estadox));
		nuevosdato.appendChild(estado);
		if(!estadox.equals(estadoz)) {logger.crearLog("Se Modifico el estado: \""+estadoz+"\" a \""+estadox+"\"");}
		else {logger.crearLog("Estado: \""+estadox+"\'");}
		
		Element hash = doc.createElement("hash");
		hash.appendChild(doc.createTextNode(""+hashx));
		nuevosdato.appendChild(hash);
		if(!hashx.equals(hashz)) {logger.crearLog("Se Modifico el SHA-1: \""+hashz+"\" a \""+hashx+"\"");}
		else {logger.crearLog("SHA-1: \""+hashx+"\"");}

		Element fecha = doc.createElement("fecha");
		fecha.appendChild(doc.createTextNode(""+fechax));
		nuevosdato.appendChild(fecha);
		
		Element tipo = doc.createElement("tipo");
		tipo.appendChild(doc.createTextNode(""+tipox));
		nuevosdato.appendChild(tipo);
		
		if(!tipox.equals(tipoz)) {logger.crearLog("Se Modifico el Tipo: \""+tipoz+"\" a \""+tipox+"\"");}
		else {logger.crearLog("Tipo: \""+tipox+"\"");}

		Element comentarios = doc.createElement("comentarios");
		comentarios.appendChild(doc.createTextNode(""+comentariosx));
		nuevosdato.appendChild(comentarios);
		if(!comentariosx.equals(comentariosz)) {logger.crearLog("Se Modifico los comentarios: \""+comentariosz+"\" a \""+comentariosx+"\"");}
		else {logger.crearLog("Comentarios: \""+comentariosx+"\"");}

		nodoraiz.appendChild(nuevosdato);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		 
		StreamResult result = new StreamResult(new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\"+remplazo+".xml"));

		transformer.transform(source, result);
		logger.crearLog("Edicion finalizada de: "+nombrez+"......");
	         }
	         
	  if(nodo2.getChildNodes().getLength()==9) {
		  
		         for (int i = 0; i < nodeList.getLength(); i++) {
		             Node node = nodeList.item(i);
		             Element elem = (Element) node;
		             
		              nombrez = elem.getElementsByTagName("Nombre").item(0).getChildNodes().item(0).getNodeValue();
		              versionesz= elem.getElementsByTagName("versiones").item(0).getChildNodes().item(0).getNodeValue();
		              estadoz = elem.getElementsByTagName("estado").item(0).getChildNodes().item(0).getNodeValue();
		              hashz = elem.getElementsByTagName("hash").item(0).getChildNodes().item(0).getNodeValue();
		              hashboot32 = elem.getElementsByTagName("hashbootia32").item(0).getChildNodes().item(0).getNodeValue();
		              hashboot64 = elem.getElementsByTagName("hashbootx64").item(0).getChildNodes().item(0).getNodeValue();
		              fechaz = elem.getElementsByTagName("fecha").item(0).getChildNodes().item(0).getNodeValue();
		              tipoz = elem.getElementsByTagName("tipo").item(0).getChildNodes().item(0).getNodeValue();
		              comentariosz = elem.getElementsByTagName("comentarios").item(0).getChildNodes().item(0).getNodeValue();
		         }
		        
			 
			for(int i = 0; i <nodo.getLength();i++ ) {
				Element element = (Element) nodo.item(i);
				element.getParentNode().removeChild(element);
				
			}
			doc.getDocumentElement().normalize();
			Node nodoraiz = doc.getDocumentElement();
			Element nuevosdato = doc.createElement("Catalogo");
			
			Element Nombre = doc.createElement("Nombre");
			Nombre.appendChild(doc.createTextNode(""+reemplazonombrex));
			nuevosdato.appendChild(Nombre);
			if(!nombrex.equals(nombrez)) {logger.crearLog("Se Modifico el nombre del TSU:\""+nombrez+"\" a \""+nombrex+"\"");}
			else {logger.crearLog("Nombre del TSU: \""+nombrex+"\"");}
			Element versionesxml = doc.createElement("versiones");
			versionesxml.appendChild(doc.createTextNode(""+versionesx));
			nuevosdato.appendChild(versionesxml);
			if(!versionesx.equals(versionesz)) {logger.crearLog("Se Modifico las versiones: \""+versionesz+"\" a \""+versionesx+"\"");}
			else {logger.crearLog("Versiones: "+versionesx);}

			Element estado = doc.createElement("estado");
			estado.appendChild(doc.createTextNode(""+estadox));
			nuevosdato.appendChild(estado);
			if(!estadox.equals(estadoz)) {logger.crearLog("Se Modifico el estado: \""+estadoz+"\" a \""+estadox+"\"");}
			else {logger.crearLog("Estado: \""+estadox+"\'");}
			
			Element hash = doc.createElement("hash");
			hash.appendChild(doc.createTextNode(""+hashx));
			nuevosdato.appendChild(hash);
			if(!hashx.equals(hashz)) {logger.crearLog("Se Modifico el SHA-1: \""+hashz+"\" a \""+hashx+"\"");}
			else {logger.crearLog("SHA-1: \""+hashx+"\"");}
			
			Element hashbootia32 = doc.createElement("hashbootia32");
			hashbootia32.appendChild(doc.createTextNode(""+hashbootia));
			nuevosdato.appendChild(hashbootia32);
			if(!hashboot32.equals(hashbootia)) {logger.crearLog("Se Modifico el SHA-1 del archivo BOOTIA32.EFI: \""+hashboot32+"\" a \""+hashbootia+"\"");			}
			else {logger.crearLog("SHA-1 BOOTIA32.EFI: \""+hashboot32+"\"");}
			
			Element hashbootx64 = doc.createElement("hashbootx64");
			hashbootx64.appendChild(doc.createTextNode(""+hashbootx));
			nuevosdato.appendChild(hashbootx64);
			if(!hashboot64.equals(hashbootx)) {logger.crearLog("Se Modifico el SHA-1 del archivo BOOTX64.EFI: \""+hashboot64+"\" a \""+hashbootx+"\"");}
			else {logger.crearLog("SHA-1 BOOTX64.EFI: \""+hashbootx+"\"");}

			
			Element fecha = doc.createElement("fecha");
			fecha.appendChild(doc.createTextNode(""+fechax));
			nuevosdato.appendChild(fecha);
			
			
			Element tipo = doc.createElement("tipo");
			tipo.appendChild(doc.createTextNode(""+tipox));
			nuevosdato.appendChild(tipo);
			if(!tipox.equals(tipoz)) {logger.crearLog("Se Modifico el Tipo: \""+tipoz+"\" a \""+tipox+"\"");}
			else {logger.crearLog("Tipo: \""+tipox+"\"");}

			
			Element comentarios = doc.createElement("comentarios");
			comentarios.appendChild(doc.createTextNode(""+comentariosx));
			nuevosdato.appendChild(comentarios);
			if(!comentariosx.equals(comentariosz)) {logger.crearLog("Se Modifico los comentarios: \""+comentariosz+"\" a \""+comentariosx+"\"");}
			else {logger.crearLog("Comentarios: \""+comentariosx+"\"");}

			
			nodoraiz.appendChild(nuevosdato);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			 
			StreamResult result = new StreamResult(new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\"+remplazo+".xml"));

			transformer.transform(source, result);
			logger.crearLog("Edicion finalizada de: "+nombrez+"......");
		         }
	      }
		 
		 
	}
	public void hashbootai() throws NoSuchAlgorithmException, IOException {
		File sFichero = new File(Rutadrive);
		String rutamodificada = Rutadrive;
		rutamodificada.replace("\\mt86.cfg", "\\BOOTIA32.efi");
		File rutanueva = new File(rutamodificada.replace("\\mt86.cfg", "\\BOOTIA32.efi"));
			
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
		txtbootia32.setText(filehash);
		hashbootia = filehash;
			
		}
		public void hashbootx() throws NoSuchAlgorithmException, IOException {
			File sFichero = new File(Rutadrive);
			String rutamodificada = Rutadrive;
			rutamodificada.replace("\\mt86.cfg", "\\BOOTIA32.efi");
			File rutanueva = new File(rutamodificada.replace("\\mt86.cfg", "\\BOOTX64.efi"));
				
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
			txtbootx64.setText(filehash);
			hashbootx = filehash;
			
		}
}
