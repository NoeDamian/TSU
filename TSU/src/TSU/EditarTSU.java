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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 617, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneTsu = new JLabel("Seleccione TSU");
		lblSeleccioneTsu.setBounds(81, 11, 90, 14);
		contentPane.add(lblSeleccioneTsu);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(357, 8, 154, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(287, 11, 60, 14);
		contentPane.add(lblNombres);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(287, 64, 60, 14);
		contentPane.add(lblEstado);
		
		comboboxestado.setModel(new DefaultComboBoxModel(new String[] {"Habilitado", "Deshabilitado"}));
		comboboxestado.setBounds(357, 61, 154, 20);
		contentPane.add(comboboxestado);
		
		JLabel lblNewLabel = new JLabel("Comentarios");
		lblNewLabel.setBounds(287, 202, 116, 14);
		contentPane.add(lblNewLabel);
		
		txtpcomentarios.setBackground(new Color(230, 230, 250));
		txtpcomentarios.setBounds(367, 202, 184, 109);
		contentPane.add(txtpcomentarios);
		
		JButton btnNewButton_2 = new JButton("Guardar");
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
		btnNewButton_2.setBounds(253, 319, 116, 23);
		contentPane.add(btnNewButton_2);
		
		comboboxtsu.setBounds(10, 36, 223, 20);
		contentPane.add(comboboxtsu);
		
		JLabel lblVersion = new JLabel("Version:");
		lblVersion.setBounds(287, 39, 60, 14);
		contentPane.add(lblVersion);
		
		txtversiones = new JTextField();
		txtversiones.setBounds(357, 36, 154, 20);
		contentPane.add(txtversiones);
		txtversiones.setColumns(10);
		
		JLabel lblHash = new JLabel("SHA-1");
		lblHash.setBounds(287, 103, 60, 14);
		contentPane.add(lblHash);
		
		txthash = new JTextField();
		txthash.setBounds(357, 100, 234, 20);
		contentPane.add(txthash);
		txthash.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(287, 177, 46, 14);
		contentPane.add(lblFecha);
		
		txtfecha = new JTextField();
		txtfecha.setBounds(357, 171, 86, 20);
		contentPane.add(txtfecha);
		txtfecha.setColumns(10);
		
		JLabel lblTipo = new JLabel("tipo");
		lblTipo.setBounds(287, 139, 46, 14);
		contentPane.add(lblTipo);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				seleccionararchivo();
			} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		btnSeleccionar.setBounds(69, 77, 102, 23);
		contentPane.add(btnSeleccionar);
		
		comboboxtipo.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE", "SSD", "HD", "USB", "FLOPPY", "CD", "DVD", "FIREWARE"}));
		comboboxtipo.setBounds(357, 140, 154, 20);
		contentPane.add(comboboxtipo);
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
	            	  numeroestado=1;
	              }else if(estado.equals("Deshabilitado")){}
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
		logger.crearLog("Editando el archivo: "+remplazo);
		 String nombrez = null;
	        String versionesz = null;
	        String estadoz = null;
	        String hashz = null;
	        String fechaz = null;
	        String tipoz = null;
	        String comentariosz = null;
		
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
		if(!nombrex.equals(nombrez)) {
			logger.crearLog("Se Modifico el nombre del TSU:\""+nombrez+"\" a \""+nombrex+"\"");

		}
		else {
		logger.crearLog("Nombre del TSU: \""+nombrex+"\"");
		}
		Element versionesxml = doc.createElement("versiones");
		versionesxml.appendChild(doc.createTextNode(""+versionesx));
		nuevosdato.appendChild(versionesxml);
		if(!versionesx.equals(versionesz)) {
			logger.crearLog("Se Modifico las versiones: \""+versionesz+"\" a \""+versionesx+"\"");

		}
		else {
		logger.crearLog("Versiones: "+versionesx);
		}

		Element estado = doc.createElement("estado");
		estado.appendChild(doc.createTextNode(""+estadox));
		nuevosdato.appendChild(estado);
		if(!estadox.equals(estadoz)) {
			logger.crearLog("Se Modifico el estado: \""+estadoz+"\" a \""+estadox+"\"");

		}
		else {
			logger.crearLog("Estado: \""+estadox+"\'");
		}
		

		/*Element path =doc.createElement("ruta");
		path.appendChild(doc.createTextNode(""+ruta));
		nombre.appendChild(path);*/
		
		Element hash = doc.createElement("hash");
		hash.appendChild(doc.createTextNode(""+hashx));
		nuevosdato.appendChild(hash);
		if(!hashx.equals(hashz)) {
			logger.crearLog("Se Modifico el SHA-1: \""+hashz+"\" a \""+hashx+"\"");

		}
		else {
		logger.crearLog("SHA-1: \""+hashx+"\"");
		}

		
		Element fecha = doc.createElement("fecha");
		fecha.appendChild(doc.createTextNode(""+fechax));
		nuevosdato.appendChild(fecha);
		
		
		Element tipo = doc.createElement("tipo");
		tipo.appendChild(doc.createTextNode(""+tipox));
		nuevosdato.appendChild(tipo);
		
		if(!tipox.equals(tipoz)) {
			logger.crearLog("Se Modifico el Tipo: \""+tipoz+"\" a \""+tipox+"\"");

		}
		else {
		logger.crearLog("Tipo: \""+tipox+"\"");
		}

		
		Element comentarios = doc.createElement("comentarios");
		comentarios.appendChild(doc.createTextNode(""+comentariosx));
		nuevosdato.appendChild(comentarios);
		if(!comentariosx.equals(comentariosz)) {
			logger.crearLog("Se Modifico los comentarios: \""+comentariosz+"\" a \""+comentariosx+"\"");

		}
		else {
		logger.crearLog("Comentarios: \""+comentariosx+"\"");
		}

		
		nodoraiz.appendChild(nuevosdato);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		 
		StreamResult result = new StreamResult(new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\"+remplazo+".xml"));

		transformer.transform(source, result);
		logger.crearLog("Edicion finalizada de: "+nombrez+"......");

		 
	}
}
