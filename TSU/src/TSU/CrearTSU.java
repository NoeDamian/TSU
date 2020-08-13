package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import java.net.InetAddress;
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
import java.awt.Toolkit;
import javax.swing.ImageIcon;

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
	File Unidades[];
	 Object items[];
		JTextField txtTsu = new JTextField();
		private JTextField fechac;
		JComboBox tipod = new JComboBox();
		File Archivos[];
	 	Object[] Arc;
	 	String Rutadrive;
		JButton btnBuscar = new JButton("Buscar");
		public static String equipolocal;


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
		setIconImage(Toolkit.getDefaultToolkit().getImage("M:\\\\Test Software Utilities\\Users\\noe_moreno\\Downloads\\tecnologia.png"));
		try {
			String localMachine = InetAddress.getLocalHost().getHostName();
			equipolocal = localMachine;
		}
		catch(IOException e) {e.printStackTrace();}
		setTitle("Crear TSU");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 336);
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
				String V = (txtversiones.getText());
				File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\"+N+V+".xml");
				
				

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
							} catch (SAXException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							try {
								hash();
								} catch (NoSuchAlgorithmException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ParserConfigurationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SAXException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}
							
							txtTsu.setEnabled(false);
							txtversiones.setEnabled(false);
							CBESTADO.setEnabled(false);
							txtcoemntarios.setEnabled(false);
							btnBuscar.setEnabled(false);
							tipod.setEnabled(false);
							//btnNewButton_1.setEnabled(false);
							
					}		
		}
	});
		btnNewButton_1.setBounds(176, 215, 110, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setToolTipText("Verifique La Infromacion");
		
		txtcoemntarios.setBounds(93, 106, 387, 85);
		contentPane.add(txtcoemntarios);
		
		JLabel lblSeleccioneArchivo = new JLabel("Buscar Drive:");
		lblSeleccioneArchivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccioneArchivo.setBounds(252, 16, 129, 14);
		contentPane.add(lblSeleccioneArchivo);
		
		JLabel lblTipoDeDispositivo = new JLabel("Tipo de dispositivo");
		lblTipoDeDispositivo.setBounds(271, 44, 110, 14);
		contentPane.add(lblTipoDeDispositivo);
		
		tipod.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE", "SSD", "HD", "USB", "FLOPPY", "CD", "DVD", "HD", "FIREWARE"}));
		tipod.setBounds(271, 69, 96, 20);
		contentPane.add(tipod);
		
		JLabel lblFechaDeCreacion = new JLabel("Fecha de creacion");
		lblFechaDeCreacion.setBounds(385, 44, 100, 14);
		contentPane.add(lblFechaDeCreacion);
		
		fechac = new JTextField();
		fechac.setEditable(false);
		fechac.setEnabled(false);
		fechac.setBounds(395, 72, 86, 20);
		contentPane.add(fechac);
		fechac.setColumns(10);
		//Calendar calendar = GregorianCalendar.getInstance();
		Date date = Calendar.getInstance().getTime();
		
		
		
			
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//SimpleDateFormat año = new SimpleDateFormat("yyyy");

			fechac.setText(sdf.format(date));
			
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						creacionruta();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			btnBuscar.setBounds(391, 12, 89, 23);
			contentPane.add(btnBuscar);



	}
public String creacionruta() throws IOException {
		
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setCurrentDirectory(new java.io.File("M:\\\\Test Software Utilities\\Users\\noe_moreno\\Desktop\\"));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CFG","cfg");
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		 if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
	       
              Rutadrive =  (fileChooser.getSelectedFile().toString());

		 	}else if(fileChooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION) {
		 		dispose();
		 		}
		 
		return Rutadrive;	
	}

public void creacionxml() throws NoSuchAlgorithmException, IOException, SAXException {
	try {
		
		String nuevo = hash();
		String ES = ((String) (CBESTADO.getSelectedItem()));
		String FC = (txtcoemntarios.getText());
		String N = (txtTsu.getText());
		String V = (txtversiones.getText());
		String H =(nuevo);
		String F = (fechac.getText());
		String T = ((String) (tipod.getSelectedItem()));
		
		logger.crearLog("Creacion de nuevo TSU en proceso");
	//	File Nombredelarchivo = new File(N);
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		logger.crearLog("Creado por: "+login.USER);
		logger.crearLog("En el equipo: "+equipolocal);


				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("TSUCATEGORIA");
				doc.appendChild(rootElement);
				
				
		 
				Element catalogo = doc.createElement("Catalogo");
				rootElement.appendChild(catalogo);
				
				Element Nombre = doc.createElement("Nombre");
				Nombre.appendChild(doc.createTextNode(""+N+V));
				catalogo.appendChild(Nombre);
				
				logger.crearLog("Nombre del TSU: "+N+V);
				
				Element versiones = doc.createElement("versiones");
				versiones.appendChild(doc.createTextNode(""+V));
				catalogo.appendChild(versiones);
				logger.crearLog("versiones: "+V);


				Element estado = doc.createElement("estado");
				estado.appendChild(doc.createTextNode(""+ES));
				catalogo.appendChild(estado);
				logger.crearLog("Estado: "+ES);

				/*Element path =doc.createElement("ruta");
				path.appendChild(doc.createTextNode(""+ruta));
				nombre.appendChild(path);*/
				
				Element hash = doc.createElement("hash");
				hash.appendChild(doc.createTextNode(""+H));
				catalogo.appendChild(hash);
				logger.crearLog("SHA-1: "+H);

				
				Element fecha = doc.createElement("fecha");
				fecha.appendChild(doc.createTextNode(""+F));
				catalogo.appendChild(fecha);
				
				
				Element tipo = doc.createElement("tipo");
				tipo.appendChild(doc.createTextNode(""+T));
				catalogo.appendChild(tipo);
				logger.crearLog("Tipo: "+T);

				
				Element comentarios = doc.createElement("comentarios");
				comentarios.appendChild(doc.createTextNode(""+FC));
				catalogo.appendChild(comentarios);
				
				
		 
				
				
		
		// escribimos el contenido en un archivo .xml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\"+N+V+".xml"));
		
		transformer.transform(source, result);
		logger.crearLog("Archivo TSU Creado Exitosamente");

 
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
public void verificaciondeesxistencia() {
			String N = (txtTsu.getText());
			String V = (txtversiones.getText());
			File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\"+N+V+".xml");
			
			if(archivo.exists()) {
			    JOptionPane.showMessageDialog(null, "Nombre de TSU no disponible");
			}
		}
		




public  String hash() throws NoSuchAlgorithmException, IOException, ParserConfigurationException, SAXException {
	File sFichero = new File(Rutadrive);
	
	
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



}

