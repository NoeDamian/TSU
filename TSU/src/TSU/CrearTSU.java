package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.util.List;

import org.omg.CORBA_2_3.portable.InputStream;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLayeredPane;
import javax.swing.border.BevelBorder;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Button;
import java.awt.TextField;
import javax.swing.JScrollBar;
import java.awt.Scrollbar;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class CrearTSU extends JFrame {

	private JPanel contentPane;
	private JTextField txtversiones;
	
	JComboBox CBESTADO = new JComboBox();
	JTextPane txcomentario = new JTextPane();
	JTextField comentarios = new JTextField();
	JTextArea textArea = new JTextArea();
	private JTextPane textPane ;
	JTextArea txtcoemntarios = new JTextArea();
	String nl = System.getProperty("line.separator");
	JComboBox comboBox = new JComboBox();
	File Unidades[];
	 Object items[];
		JTextField txtTsu = new JTextField();
		private JTextField fechac;
		JComboBox tipod = new JComboBox();


	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearTSU frame = new CrearTSU();
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
	public CrearTSU() {
		buscarUnidades();


		setTitle("Crear TSU");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombres = new JLabel("Versiones");
		lblNombres.setBounds(10, 44, 96, 14);
		contentPane.add(lblNombres);
		
		txtversiones = new JTextField();
		txtversiones.setBounds(108, 41, 134, 20);
		contentPane.add(txtversiones);
		txtversiones.setColumns(10);
		
		JLabel lblNomnreDelTsu = new JLabel("Nombre del TSU");
		lblNomnreDelTsu.setBounds(10, 16, 96, 14);
		contentPane.add(lblNomnreDelTsu);
		
		txtTsu.setText("TSU-");
		txtTsu.setBounds(108, 13, 134, 20);
		contentPane.add(txtTsu);
		txtTsu.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Comentarios");
		lblNewLabel_1.setBounds(10, 106, 110, 14);
		contentPane.add(lblNewLabel_1);
		
		CBESTADO.setSelectedItem("Habilitado");
		CBESTADO.setModel(new DefaultComboBoxModel(new String[] {"Habilitado", "Desahibilitado"}));
		CBESTADO.setBounds(118, 72, 86, 20);
		contentPane.add(CBESTADO);
		
		JLabel lblNewLabel_2 = new JLabel("Estado");
		lblNewLabel_2.setBounds(10, 75, 55, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("GUARDAR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String N = (txtTsu.getText());
				File archivo = new File("C:\\Archivo\\TSUCATEGORIAS\\"+N+".xml");
				

				if(txtTsu.getText().equals("TSU-")&&txtversiones.getText().isEmpty()&&txtversiones.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Favor de rellenar los espacios en blanco");
					}else if (txtTsu.getText().equals("TSU-")) {
						JOptionPane.showMessageDialog(null, "Falta agregar el Nombre"+nl+" despues del TSU-");
					}
					else if (txtTsu.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Falta agregar informacion(TSU-)");
					}else if(txtversiones.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Falta agregar las versiones");
					}else if(txtversiones.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Falta agregar las versiones");
						
					}
					else if(archivo.exists()) {

						JOptionPane.showMessageDialog(null, "Nombre de TSU Existente");
					}
					else  {
							int resp = JOptionPane.showConfirmDialog(null, "¿Deseas guarar los datos?",nl, JOptionPane.YES_NO_OPTION);
							if(resp==JOptionPane.YES_OPTION) {
							
							try {
								creacionxml();
							} catch (NoSuchAlgorithmException | IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							try {
								leerxml();
								} catch (ParserConfigurationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									} catch (SAXException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							try {
								hash();
								} catch (NoSuchAlgorithmException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
							comboBox.setEnabled(false);
							txtTsu.setEnabled(false);
							txtversiones.setEnabled(false);
							CBESTADO.setEnabled(false);
							txtcoemntarios.setEnabled(false);
							//btnNewButton_1.setEnabled(false);
							
					}		
		}
	});
		btnNewButton_1.setBounds(167, 202, 110, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setToolTipText("Verifique La Infromacion");
		
		txtcoemntarios.setBounds(93, 106, 136, 85);
		contentPane.add(txtcoemntarios);
		
		JLabel lblSeleccioneArchivo = new JLabel("Seleccione Drive:");
		lblSeleccioneArchivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccioneArchivo.setBounds(252, 16, 129, 14);
		contentPane.add(lblSeleccioneArchivo);
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				comboBox.setToolTipText("Seleccione driver correcto");

			}
		});
		
		
			
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//driverequivocado() ;
				verificaciondedriver();
			}
			
		});
		
		comboBox.setBounds(375, 13, 84, 20);
		contentPane.add(comboBox);
		
		JLabel lblTipoDeDispositivo = new JLabel("Tipo de dispositivo");
		lblTipoDeDispositivo.setBounds(252, 44, 110, 14);
		contentPane.add(lblTipoDeDispositivo);
		
		tipod.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE", "SSD", "HD", "USB", "FLOPPY", "CD", "DVD", "HD", "FIREWARE"}));
		tipod.setBounds(363, 41, 96, 20);
		contentPane.add(tipod);
		
		JLabel lblFechaDeCreacion = new JLabel("Fecha de creacion");
		lblFechaDeCreacion.setBounds(239, 75, 100, 14);
		contentPane.add(lblFechaDeCreacion);
		
		fechac = new JTextField();
		fechac.setEditable(false);
		fechac.setEnabled(false);
		fechac.setBounds(363, 72, 86, 20);
		contentPane.add(fechac);
		fechac.setColumns(10);
		Calendar calendar = GregorianCalendar.getInstance();
		Date date = Calendar.getInstance().getTime();
		
		
		
			
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat año = new SimpleDateFormat("yyyy");

			fechac.setText(sdf.format(date));



	}
public void actualizar() {
		comboBox.removeAllItems();
		Unidades = File.listRoots();
		File unidades[] = File.listRoots();
		Object it[]=new Object[Unidades.length];
		  for (int i=0;i<unidades.length;i++) {
			  File s1 = (unidades[i]);
		         it[i]=s1;
		       }
		  
		  items=it;
		  comboBox.updateUI();


		  for(int i=0;i<it.length;i++){
		  comboBox.addItem(it[i]);
		
		  }
	}
public void creacionxml() throws NoSuchAlgorithmException, IOException {
	try {
		
		String nuevo = hash();
		String ES = ((String) (CBESTADO.getSelectedItem()));
		String FC = (txtcoemntarios.getText());
		String N = (txtTsu.getText());
		String V = (txtversiones.getText());
		String H =(nuevo);
		String F = (fechac.getText());
		String T = ((String) (tipod.getSelectedItem()));
		
		
		File Nombredelarchivo = new File(N);
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("TSUCATEGORIA");
				doc.appendChild(rootElement);
				
				
		 
				Element nombre = doc.createElement("nombre"+N);
				rootElement.appendChild(nombre);
		 
				
				
				Element versiones = doc.createElement("versiones");
				versiones.appendChild(doc.createTextNode(""+V));
				nombre.appendChild(versiones);

				Element estado = doc.createElement("estado");
				estado.appendChild(doc.createTextNode(""+ES));
				nombre.appendChild(estado);
				
				/*Element path =doc.createElement("ruta");
				path.appendChild(doc.createTextNode(""+ruta));
				nombre.appendChild(path);*/
				
				Element hash = doc.createElement("hash");
				hash.appendChild(doc.createTextNode(""+H));
				nombre.appendChild(hash);
				
				Element fecha = doc.createElement("fecha");
				fecha.appendChild(doc.createTextNode(""+F));
				nombre.appendChild(fecha);
				
				Element tipo = doc.createElement("tipo");
				tipo.appendChild(doc.createTextNode(""+T));
				nombre.appendChild(tipo);
		 
				
				Element comentarios = doc.createElement("comentarios");
				comentarios.appendChild(doc.createTextNode(""+FC));
				nombre.appendChild(comentarios);
				
				
		 
				
				
		
		// escribimos el contenido en un archivo .xml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("C:\\Archivo\\TSUCATEGORIAS\\"+N+".xml"));
		
		transformer.transform(source, result);
 
 
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
public void verificaciondeesxistencia() {
			String N = (txtTsu.getText());
			File archivo = new File("C:\\Archivo\\TSUCATEGORIAS\\"+N+".xml");
			
			if(archivo.exists()) {
			    JOptionPane.showMessageDialog(null, "Nombre de TSU no disponible");
			}
		}
		
public String leerxml() throws ParserConfigurationException, SAXException, IOException {
	String N = (txtTsu.getText());
	 String versiones ="";
	 String nombre ="";
	 
	File archivo = new File("C:\\Archivo\\TSUCATEGORIAS\\"+N+".xml");
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
	Document document = documentBuilder.parse(archivo);
	
	document.getDocumentElement().normalize();

	 NodeList listaEmpleados = document.getElementsByTagName(""+N);
	
	 for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
         Node nodo = listaEmpleados.item(temp);
         nodo.getChildNodes().getLength();
         NodeList nodeList = document.getDocumentElement().getChildNodes();
         for (int i = 0; i < nodeList.getLength(); i++) {
             Node node = nodeList.item(i);
             Element elem = (Element) node;
             nombre = elem.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue();
             versiones = elem.getElementsByTagName("versiones").item(0).getChildNodes().item(0).getNodeValue();
             String comentarios = elem.getElementsByTagName("comentarios").item(0).getChildNodes().item(0).getNodeValue();
             String estado = elem.getElementsByTagName("estado").item(0).getChildNodes().item(0).getNodeValue();
             String fecha = elem.getElementsByTagName("fecha").item(0).getChildNodes().item(0).getNodeValue();
             String tipo = elem.getElementsByTagName("tipo").item(0).getChildNodes().item(0).getNodeValue();


             
            
        


             } 
        
	 	}
     return versiones;


  	}  		

public void buscarUnidades() {

	Unidades = File.listRoots();
	File unidades[] = File.listRoots();
	FileSystemView fsv = FileSystemView.getFileSystemView();
	
	Object it[]=new Object[Unidades.length];
	
	  for (int i=0;i<unidades.length;i++) {
		 // File s1 = (unidades[i]);
		  String s1 = (FileSystemView.getFileSystemView().getSystemDisplayName(unidades[i]));

	         it[i]=s1;
	         
	       
	       }
	  
	  items=it;
	  
	  comboBox.removeAllItems();
	  comboBox.update(null);
	  for(int i=0;i<it.length;i++){
	  comboBox.addItem(it[i]);
	  }
	 
}
public File verificaciondedriver() {
	String driver = (comboBox.getSelectedItem())+"\\EFI\\BOOT\\mt86.cfg";
	String MPtool = (comboBox.getSelectedItem())+"\\temp\\SM_PCIe\\MPTool.exe";
	File Respuesta = null;
	//\\CfgFiles\\SMI\\SM2263XT\\Firmware\\R1115F0_B1xA\\MPTool.exe
	
	File Mp= new File(MPtool);
	File Mm = new File(driver);
	if(Mm.exists()) {
		System.out.print("El archivo es de memtest");
		Respuesta = (Mm);

	}
	else if(Mp.exists()) {
		
		System.out.print("Es MPTool");
		Respuesta = (Mp);
	}
	return Respuesta;
	
}

public String hash() throws NoSuchAlgorithmException, IOException {
	
	File sFichero = verificaciondedriver();
	
	
	byte[] buffer= new byte[8192];
	int count;
	MessageDigest digest = MessageDigest.getInstance("SHA-1");
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sFichero));
	while ((count = bis.read(buffer)) > 0) {
    digest.update(buffer, 0, count);
		}
	bis.close();

	byte[] hash = digest.digest();
		String filehash= Base64.getEncoder().encodeToString(hash);
		
		return filehash;
}

public void nuevaversion() throws ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException {
	String V= leerxml();
	String ver= txtversiones.getText();
	if(V!=ver) {
int resp =JOptionPane.showConfirmDialog(null, "Nueva version se agregara al TSU ya creado",nl, JOptionPane.YES_NO_OPTION);

	if (resp==JOptionPane.YES_OPTION) {
		System.out.println("Version lista");
		
	
			}
		}
	}
public void driverequivocado() {
	String sFichero = (comboBox.getSelectedItem())+"\\EFI\\BOOT\\mt86.cfg";
	File fichero = new File(sFichero);
	if(fichero.exists()) ;
		else{
			
		JOptionPane.showMessageDialog(null, "Driver equivocado"+nl+"Seleccionde Diver"+nl+"Correcto");
		actualizar();
		}
	
	  
}
}

