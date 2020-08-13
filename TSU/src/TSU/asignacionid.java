package TSU;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class asignacionid extends JFrame {

	private JPanel contentPane;
	private JTextField IDA;
	File Unidades[];
	 Object items[];
	String nl = System.getProperty("line.separator");
	

		//private String ruta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					asignacionid frame = new asignacionid();
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
	public asignacionid() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\noe_moreno\\Downloads\\tecnologia.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 260, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAsignarId = new JButton("Asignar ID");
		
		btnAsignarId.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		File Ruta = new File("M:\\\\Test Software Utilities\\Archivo\\TSUREGISTRO\\");
		String[] Listado = Ruta.list();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Date date = Calendar.getInstance().getTime();
		String N ="ID";
		File RutaArchivo = new File(Ruta+"\\"+N+".xml");
		if(Listado.length == 0 || !RutaArchivo.exists() ){
			try {
				crearxml();
				} catch (IOException | ParserConfigurationException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
					} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
			}
		btnAsignarId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File Ruta = new File("M:\\\\Test Software Utilities\\Archivo\\TSUREGISTRO\\");
				String[] Listado = Ruta.list();
				SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
				Date date = Calendar.getInstance().getTime();
				String N = "ID";
				File  rutaarchivo = new File(Ruta+"\\"+N+".xml"); 
				if(rutaarchivo.exists()) {
						try {
							ID();
						} catch (IOException | ParserConfigurationException | SAXException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (TransformerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} 
					
	
			}
			
		});
		btnAsignarId.setBounds(71, 11, 89, 23);
		contentPane.add(btnAsignarId);
		
		IDA = new JTextField();
		IDA.setEditable(false);
		IDA.setBounds(62, 70, 121, 34);
		contentPane.add(IDA);
		IDA.setColumns(10);
		
		JLabel lblIdAsignado = new JLabel("ID ASIGNADO");
		lblIdAsignado.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblIdAsignado.setBounds(81, 45, 102, 14);
		contentPane.add(lblIdAsignado);
	}
	public String ID() throws IOException, ParserConfigurationException, SAXException, TransformerException {
		String R=creacionruta();
		String id = leerxml();
		String ruta = R+"\\ID.txt";
		String contenido= "TSU-"+id;
		String ruta2 = R+"\\AUTORUN.inf";
		String contenido2= "[AUTORUN] "+nl+ "LABEL=TSU-"+id;
			File file = new File(ruta);
			File file2 = new File(ruta2);

			if(file.exists()) {
				Scanner entrada = new Scanner(file);
				

				while(entrada.hasNext()) {
					IDA.setText("ID existente: "+entrada.nextLine());
					IDA.setBackground(new Color(255	,0	,0));

										}
								}
					else  {
						file.createNewFile();	
						FileWriter fw = new FileWriter(file);
						BufferedWriter bw = new BufferedWriter(fw);	
						bw.write(contenido);
						bw.close();
						
						IDA.setText(contenido);
						IDA.setBackground(new Color(102, 204, 51));
						
						file2.createNewFile();
						file2.isHidden();
						FileWriter fw2= new FileWriter(file2);
						BufferedWriter bw2 = new BufferedWriter(fw2);	
						bw2.write(contenido2);
						bw2.close();		
						//IDA.setText(contenido2);
						Agregardatos();
					}
			
			return contenido;
			}
	
	public String creacionruta() throws IOException {
		String R = "";
		JFileChooser fileChooser = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));

		fileChooser.setCurrentDirectory(workingDirectory);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		int seleccion = fileChooser.showOpenDialog(this);
		 if ( seleccion == JFileChooser.APPROVE_OPTION) { 
	       
			  R =  (fileChooser.getSelectedFile().toString());

		 	}
		return R;	
	}
	
	
	
	public void crearxml() throws IOException, ParserConfigurationException, SAXException {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			Date date = Calendar.getInstance().getTime();
			
			String N ="ID";
				String id = "0";	
				try {
					DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docbuilder = docfactory.newDocumentBuilder();
		
					Document doc = docbuilder.newDocument();
					Element rootElement = doc.createElement("Registro");
					doc.appendChild(rootElement);
					
					Element identificacion = doc.createElement("Identificacion");
					rootElement.appendChild(identificacion);
					identificacion.setAttribute("numero", id);
					
					Element DI = doc.createElement("ID");
					DI.appendChild(doc.createTextNode(""+id));
					identificacion.appendChild(DI);
					
		
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
		
					StreamResult result = new StreamResult(new File("M:\\\\Test Software Utilities\\Archivo\\TSUREGISTRO\\"+N+".xml"));
						
							transformer.transform(source, result);
						}	
				
			 catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			}
						
		String Ruta = "M:\\\\Test Software Utilities\\Archivo\\TSUREGISTRO\\"+N+".xml";

					
				
			}
		  
	public String leerxml() throws IOException, ParserConfigurationException, SAXException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Date date = Calendar.getInstance().getTime();
		
		String N ="ID";
		String ruta = "M:\\\\Test Software Utilities\\Archivo\\TSUREGISTRO\\"+N+".xml";
		
		File archivo = new File(ruta);
		String ID = "";
		String idnuevo="";
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
		Document document = documentBuilder.parse(archivo);
		dbf.setNamespaceAware(true);

		
		
		NodeList listapadres = document.getElementsByTagName("Registro").item(0).getChildNodes();
		
		
		 for (int temp = 0; temp < listapadres.getLength(); temp++) {
	         Node nodo = listapadres.item(temp);
	         nodo.getChildNodes().getLength();
	         NodeList nodeList = document.getDocumentElement().getChildNodes(); 
	         
	         for (int i = 0; i < nodeList.getLength(); i++) {
	             Node node = nodeList.item(i);
	             Element elem = (Element) node.getChildNodes();
	            
	             
	             ID = elem.getElementsByTagName("ID").item(0).getLastChild().getNodeValue();
	             //System.out.print(ID);
	             int b = Integer.parseInt(ID);
	             int suma=0;
	             for(int a = 0; a <= b; a++) {
	            		  suma= b+1;
	            	  
	             }
                idnuevo = String.valueOf(suma);
	        }
		 }
		 //ID.replace("TSU-","");
	
	return idnuevo;
		
	}
	
	public void Agregardatos() throws SAXException, IOException, ParserConfigurationException, TransformerException {
		
		String Idnuevo = leerxml();
		File Ruta = new File("M:\\\\Test Software Utilities\\Archivo\\TSUREGISTRO\\");
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Date date = Calendar.getInstance().getTime();
		String N ="ID";
		File  rutaarchivo = new File(Ruta+"\\"+N+".xml");
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
		Document doc = documentBuilder.parse(rutaarchivo);
		doc.getDocumentElement().normalize();
		
		Node nodoraiz = doc.getDocumentElement();
		
		Element Nuevaidentificacion = doc.createElement("Identificacion");
		Nuevaidentificacion.setAttribute("numero", Idnuevo);
		
		Element nuevoid = doc.createElement("ID");
		nuevoid.setTextContent(Idnuevo);
		Nuevaidentificacion.appendChild(nuevoid);
		
		nodoraiz.appendChild(Nuevaidentificacion);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(new File("M:\\\\Test Software Utilities\\Archivo\\TSUREGISTRO\\"+N+".xml"));
			
				transformer.transform(source, result);
			
			
			
			
 		}
		

}
