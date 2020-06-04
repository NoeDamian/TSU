package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;



public class Registrar extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField nombres;
	private JTextField versiones;
	private JTextField fechac;
	private JTextField fechav;
	private JTextField comentarios;
	JComboBox Estado = new JComboBox();
	JComboBox equipo = new JComboBox();
	JLabel lblComentarios = new JLabel("Comentarios");
    static Document document = null;


	int  autoid=0 ;
	int fechaactual;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrar frame = new Registrar();
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
	public Registrar() {

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Registro de Programa");
		setBounds(100, 100, 623, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblid = new JLabel("#ID:");
		lblid.setBounds(21, 11, 46, 14);
		contentPane.add(lblid);
		
		
		id = new JTextField();
		
		id.setEditable(false);
		id.setBounds(51, 8, 46, 20);
		contentPane.add(id);
		id.setColumns(10);
		id.setText("0");
		/*llamado de las clases*/
			
			autoid();
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(107, 11, 62, 14);
		contentPane.add(lblNombres);
		
		JLabel lblVersionrev = new JLabel("Version/rev");
		lblVersionrev.setBounds(308, 11, 76, 14);
		contentPane.add(lblVersionrev);
		
		nombres = new JTextField();
		nombres.setBounds(157, 8, 141, 20);
		contentPane.add(nombres);
		nombres.setColumns(10);
		
		versiones = new JTextField();
		versiones.setBounds(365, 8, 86, 20);
		contentPane.add(versiones);
		versiones.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado Actual");
		lblEstado.setBounds(461, 11, 93, 14);
		contentPane.add(lblEstado);
		
		JLabel lblFechaDeCreacion = new JLabel("Fecha de Creacion");
		lblFechaDeCreacion.setBounds(193, 50, 105, 14);
		contentPane.add(lblFechaDeCreacion);
		
		fechac = new JTextField();
		fechac.setEditable(false);
		fechac.setBounds(193, 75, 105, 20);
		contentPane.add(fechac);
		fechac.setColumns(10);
		
		Calendar calendar = GregorianCalendar.getInstance();
		Date date = Calendar.getInstance().getTime();
		
		
		
			
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat año = new SimpleDateFormat("yyyy");

			fechac.setText(sdf.format(date));
			
			
			
				
	
			 
		
		JLabel lblFechaDeVerificacion = new JLabel("Fecha de Verificacion");
		lblFechaDeVerificacion.setBounds(193, 106, 142, 14);
		contentPane.add(lblFechaDeVerificacion);
		
		
		
		fechav = new JTextField();
		fechav.setBounds(193, 131, 86, 20);
		contentPane.add(fechav);
		fechav.setColumns(10);
		
		fechav.setText("10/01/"+año.format(date));;
		
		JLabel lblComentarios = new JLabel("Comentarios");
		lblComentarios.setBounds(365, 50, 86, 14);
		contentPane.add(lblComentarios);
		
		comentarios = new JTextField();
		comentarios.setHorizontalAlignment(SwingConstants.LEFT);
		comentarios.setBounds(365, 75, 232, 145);
		contentPane.add(comentarios);
		comentarios.setColumns(10);
		
		JComboBox equipo = new JComboBox();
		equipo.setToolTipText("");
		equipo.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE", "SSD", "HD", "USB", "FLOPPY", "CD", "DVD", "HD", "FIREWARE"}));
		equipo.setSelectedIndex(0);
		equipo.setBounds(21, 75, 98, 20);
		contentPane.add(equipo);
		
		JLabel lblSeleccioneEquipo = new JLabel("Seleccione Equipo");
		lblSeleccioneEquipo.setBounds(21, 50, 98, 14);
		contentPane.add(lblSeleccioneEquipo);
		
		
		Estado.setModel(new DefaultComboBoxModel(new String[] {"Habilitado", "Deshabilitado"}));
		Estado.setBounds(461, 32, 93, 20);
		contentPane.add(Estado);
		
		JButton btnNewButton = new JButton("GUARDAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				creacionxml();
				leerxml();
				
			}
		});
		
		
		btnNewButton.setBounds(51, 230, 112, 23);
		contentPane.add(btnNewButton);
		
	}
	public void creacionxml() {

		  try {
			  
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				String N = (nombres.getText());
				String V = (versiones.getText());
				String ES = (String) (Estado.getSelectedItem());
				String FC = (fechac.getText());
				String FV = (fechav.getText());
				String ID = (id.getText());
				String EQ = (String) (equipo.getSelectedItem());
				String CM = (comentarios.getText());

				
				// elemento raiz
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("categorias");
				doc.appendChild(rootElement);
		 
				// empleado
				Element tsu = doc.createElement("TSU");
				rootElement.appendChild(tsu);
		 
				
				
				//entrada de informacion
				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(""+id));
				tsu.appendChild(id);
				
				Element nombre = doc.createElement("nombre");
				nombre.appendChild(doc.createTextNode(""+N));
				tsu.appendChild(nombre);
		 
				Element apellidos = doc.createElement("versiones");
				apellidos.appendChild(doc.createTextNode(""+V));
				tsu.appendChild(apellidos);
		 
				Element seccion = doc.createElement("Estado");
				seccion.appendChild(doc.createTextNode(""+ES));
				tsu.appendChild(seccion);
		 
				Element fc = doc.createElement("Fechacreacion");
				fc.appendChild(doc.createTextNode(""+FC));
				tsu.appendChild(fc);
				
				Element fechaverificacion = doc.createElement("Fechaverificacion");
				fechaverificacion.appendChild(doc.createTextNode(""+FV));
				tsu.appendChild(fechaverificacion);
				
				Element comentario = doc.createElement("comentarios");
				comentario.appendChild(doc.createTextNode(""+CM));
				tsu.appendChild(comentario);
				
				
		 
				// escribimos el contenido en un archivo .xml
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				
			
				StreamResult result = new StreamResult(new File("C:\\Archivo\\Registros\\"+autoid+".xml"));
				
		 
				transformer.transform(source, result);
		 
				
		 
				} catch (ParserConfigurationException pce) {
					pce.printStackTrace();
				} catch (TransformerException tfe) {
					tfe.printStackTrace();
				}
			}
	public  void autoid() {
		String ruta = ("\\C:\\Archivo\\Registros\\"+id.getText()+".xml");
		String numeroid=(id.getText());
		int numEntero = Integer.parseInt(numeroid);
		File fichero = new File(ruta);
		if(fichero.exists()) {
		//	System.out.print("Id ya creado");
			int autosuma=numEntero+1;
			id.setText(""+autosuma);
			id.updateUI();
			id.repaint();
		}
		
	}
	public void leerxml() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ( );
		Document documento = null;

		try
		{
		   DocumentBuilder builder = factory.newDocumentBuilder();
		   documento = builder.parse( new File("C:\\Archivo\\Registros\\"+autoid+".xml") );
		  documento.getDocumentElement().normalize();
		 // NodeList listaEmpleados = document.getElementsByTagName("nombre");
		  String padre = documento.getDocumentElement().getNodeName();
		  String hijo = documento.getDocumentElement().getTextContent();
		  NodeList nombre = documento.getElementsByTagName("nombre");
		  int temp = 0;
		  Node text = nombre.item(temp);
		 // int hijos2 = hijo;
		  
		
		 
		  System.out.println("Raiz: "+padre+"Hijo: "+hijo);
		 
		  
		  //for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
             // Node nodo = listaEmpleados.item(temp);
           //   System.out.println("Elemento:" + nodo.getNodeName());
		//  }
		}
		catch (Exception spe)
		{
		   // Algún tipo de error: fichero no accesible, formato de XML incorrecto, etc.
		}
	}
}
	
	
	

