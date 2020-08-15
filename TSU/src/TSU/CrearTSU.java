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
import java.io.FileNotFoundException;
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
		private JTextField txtarchivo;
		private JTextField txtbootia32;
		private JTextField txtbootx64;
		public String hashbootia;
		public String hashbootx;
		public String hashcfg;

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
		setBounds(100, 100, 575, 460);
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
		lblNewLabel_1.setBounds(10, 267, 110, 14);
		contentPane.add(lblNewLabel_1);
		
		CBESTADO.setSelectedItem("Habilitado");
		CBESTADO.setModel(new DefaultComboBoxModel(new String[] {"Habilitado", "Deshabilitado"}));
		CBESTADO.setBounds(108, 69, 86, 20);
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
		btnNewButton_1.setBounds(230, 388, 110, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setToolTipText("Verifique La Infromacion");
		
		txtcoemntarios.setBounds(10, 292, 387, 85);
		contentPane.add(txtcoemntarios);
		
		JLabel lblSeleccioneArchivo = new JLabel("Buscar Drive:");
		lblSeleccioneArchivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccioneArchivo.setBounds(10, 118, 129, 14);
		contentPane.add(lblSeleccioneArchivo);

		JLabel lblTipoDeDispositivo = new JLabel("Tipo de dispositivo");
		lblTipoDeDispositivo.setBounds(271, 16, 110, 14);
		contentPane.add(lblTipoDeDispositivo);
		
		tipod.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE", "SSD", "HD", "USB", "FLOPPY", "CD", "DVD", "HD", "FIREWARE"}));
		tipod.setBounds(373, 13, 96, 20);
		contentPane.add(tipod);
		
		JLabel lblFechaDeCreacion = new JLabel("Fecha de creacion");
		lblFechaDeCreacion.setBounds(281, 44, 100, 14);
		contentPane.add(lblFechaDeCreacion);
		
		fechac = new JTextField();
		fechac.setEditable(false);
		fechac.setEnabled(false);
		fechac.setBounds(383, 41, 86, 20);
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
			btnBuscar.setBounds(105, 114, 89, 23);
			contentPane.add(btnBuscar);
			
			JLabel lblNewLabel = new JLabel("Archivo:");
			lblNewLabel.setBounds(19, 150, 87, 14);
			contentPane.add(lblNewLabel);
			
			
			
			



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
             // File rutamemtest = new File(Rutadrive+"\\EFI\\BOOT\\");
              File rutamemtest = new File(Rutadrive);
              if(rutamemtest.exists()) {
      			JLabel lblSha = new JLabel("SHA-1:");
      			lblSha.setBounds(10, 175, 76, 14);
      			contentPane.add(lblSha);
      			
      			JLabel lblBootia = new JLabel("BOOTIA32.efi : ");
      			lblBootia.setBounds(10, 200, 86, 14);
      			contentPane.add(lblBootia);
      			
      			JLabel lblBootxefi = new JLabel("BOOTX64.efi: ");
      			lblBootxefi.setBounds(10, 225, 76, 14);
      			contentPane.add(lblBootxefi);
      			
      			txtarchivo = new JTextField();
      			txtarchivo.setBounds(108, 172, 191, 20);
      			contentPane.add(txtarchivo);
      			txtarchivo.setColumns(10);
      			
      			txtbootia32 = new JTextField();
      			txtbootia32.setBounds(108, 197, 191, 20);
      			contentPane.add(txtbootia32);
      			txtbootia32.setColumns(10);
      			
      			txtbootx64 = new JTextField();
      			txtbootx64.setBounds(108, 222, 191, 20);
      			contentPane.add(txtbootx64);
      			txtbootx64.setColumns(10);
      			try {
					hashbootai();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
      			try {
					hashbootx();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
      			try {
					hash();
				} catch (NoSuchAlgorithmException | ParserConfigurationException | SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
      			contentPane.repaint();;
      		}
              try {
				hash();
			} catch (NoSuchAlgorithmException | ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	}
		 
		return Rutadrive;	
	}

public void creacionxml() throws NoSuchAlgorithmException, IOException, SAXException {
	try {
		String nuevo = hashcfg;
		String ES = ((String) (CBESTADO.getSelectedItem()));
		String FC = (txtcoemntarios.getText());
		String N = (txtTsu.getText());
		String V = (txtversiones.getText());
		String H =(nuevo);
		String F = (fechac.getText());
		String T = ((String) (tipod.getSelectedItem()));
		String hashbootai = hashbootia ;
		String hashboot_x = hashbootx;
		
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

				
				
				Element hash = doc.createElement("hash");
				hash.appendChild(doc.createTextNode(""+H));
				catalogo.appendChild(hash);
				logger.crearLog("SHA-1: "+H);
				File ruta = new File(Rutadrive);
				
				if(ruta.exists()) {
					Element hashbootia = doc.createElement("hashbootia32");
					hashbootia.appendChild(doc.createTextNode(""+hashbootai));
					catalogo.appendChild(hashbootia);
					logger.crearLog("SHA-1 del archivo BOOTIA32.EFI: "+hashbootai);
					
					Element hashbootx = doc.createElement("hashbootx64");
					hashbootx.appendChild(doc.createTextNode(""+hashboot_x));
					catalogo.appendChild(hashbootx);
					logger.crearLog("SHA-1 del archivo BOOTX64.EFI: "+hashboot_x);
					
					
				}
				
				

				
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
		




public  void hash() throws NoSuchAlgorithmException, IOException, ParserConfigurationException, SAXException {
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
txtarchivo.setText(filehash);

hashcfg = filehash;


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

