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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//librerias para comprimir un archivo o carpeta en un archivo ZIP.
import java.util.zip.ZipEntry;
import java.util.zip.GZIPOutputStream;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusuario;
	public static String USER;
	public static String PASS;
	public static String TIPO;
	JLabel lblincorrecto = new JLabel("");
	private JPasswordField txtpass;
	JButton btnOk = new JButton("OK");
    public String Equipolocal;

	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					frame.addWindowListener(new WindowAdapter() {
					    @Override
					    public void windowClosing(WindowEvent windowEvent) {
					       
					    	if (JOptionPane.showConfirmDialog(frame, 
					            "Seguro Que Deseas Salir?", "Cerrar", 
					            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					            System.exit(0);
					        }
					        	
					        
					    }
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws TransformerException 
	 * @throws ParserConfigurationException 
	 */
	public login() throws ParserConfigurationException, TransformerException {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/recursos/tecnologia.png")));
		setTitle("LOGIN TEST SOFTWARE UTILITIES");
		
		File rutausuarios = new File("M:\\\\Test Software Utilities\\Usuarios\\RegistroDeUsuarios.xml");
		File ruta = new File("M:\\\\avmx-s03\\Departments\\Test Engineering");
		if(!ruta.exists()) {
			try {
				networkdrive();
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				}
			}
		if(!rutausuarios.exists()) {
		crearxmlusuarios();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 328, 203);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(43, 34, 46, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(43, 73, 75, 14);
		contentPane.add(lblContrasea);
		
		txtusuario = new JTextField();
		txtusuario.setBounds(120, 31, 86, 20);
		contentPane.add(txtusuario);
		txtusuario.setColumns(10);
		
		
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							verificarusuarios();
						} catch (ParserConfigurationException | SAXException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (TransformerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				btnOk.setBounds(117, 126, 89, 23);
				contentPane.add(btnOk);
		lblincorrecto.setForeground(Color.RED);
		
		lblincorrecto.setBounds(53, 101, 196, 14);
		contentPane.add(lblincorrecto);
		
		txtpass = new JPasswordField();
		txtpass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar()==KeyEvent.VK_ENTER) {
					try {
						verificarusuarios();
					} catch (ParserConfigurationException | SAXException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (TransformerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

				}
			}
		});
		txtpass.setBounds(120, 70, 86, 20);
		contentPane.add(txtpass);
	}
	public void crearxmlusuarios() throws ParserConfigurationException, TransformerException {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		
		Element Registro = doc.createElement("REGISTRO");
		doc.appendChild(Registro);
		
		
		Element user = doc.createElement("USUARIOS");
		Registro.appendChild(user);
		
		Element usuarios = doc.createElement("USER");
		usuarios.appendChild(doc.createTextNode("ADMIN"));
		user.appendChild(usuarios);
		
		Element pass = doc.createElement("PASSWORD");
		pass.appendChild(doc.createTextNode("0000"));
		user.appendChild(pass);
		
		Element tipo = doc.createElement("TIPO");
		tipo.appendChild(doc.createTextNode("ADMINISTRADOR"));
		user.appendChild(tipo);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		
	
		StreamResult result = new StreamResult(new File("M:\\\\Test Software Utilities\\Usuarios\\RegistroDeUsuarios.xml"));
		
 
		transformer.transform(source, result);
		
			
	}
	public  void  leerxml() throws ParserConfigurationException, SAXException, IOException {
		String usuarioxml = null;
		String passwordxml = null;
		String tipoxml = null;
        File archivo = new File("M:\\\\Test Software Utilities\\Usuarios\\RegistroDeUsuarios.xml");
		String usuariolocal =  txtusuario.getText();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
		Document document = documentBuilder.parse(archivo);
		
		document.getDocumentElement().normalize();
		 NodeList listaEmpleados = document.getElementsByTagName("USUARIOS");
		 
		 for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
	         Node nodo = listaEmpleados.item(temp);
	         
	             Element elem = (Element) nodo;
	             
	              usuarioxml = elem.getElementsByTagName("USER").item(0).getTextContent();
	              passwordxml = elem.getElementsByTagName("PASSWORD").item(0).getTextContent();
	              tipoxml= elem.getElementsByTagName("TIPO").item(0).getTextContent();
	            if(usuarioxml.equals(usuariolocal)) {
	            	  temp = listaEmpleados.getLength();
	              		}

                  }
		 USER = usuarioxml;
		 PASS = passwordxml;
		 TIPO = tipoxml;
		 
	
		
		}
	public String verificarusuarios() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		leerxml();
		String datoxmlusuario = USER;
		String datoxmlpass = PASS;
		String usuario = txtusuario.getText();
		String password = String.valueOf(txtpass.getPassword());
		if(usuario.isEmpty()) {
			lblincorrecto.setText("Usuario No Ingresado");
		}else if (password.isEmpty()) {
			lblincorrecto.setText("Password No Ingresado");

		}else if(usuario.equals(datoxmlusuario) ) {
			if(password.equals(datoxmlpass)) {
			logger.crearLog("Sesion Iniciada...");
			this.setVisible(false);
			
			Inicio.main(null);
			
			}
			else {lblincorrecto.setText("Contrasena Incorrecta");}
		}else {lblincorrecto.setText("Usuario no Encontrado");
}
		return TIPO;
		}
	
	
	
	public static  void networkdrive() throws InterruptedException {
		File ruta = new File("M:\\\\\\avmx-s03\\Departments\\Test Engineering");
		if(!ruta.exists()) {
			try {		
		Process proces = Runtime.getRuntime().exec("net\tuse\tM:\t"+(char)34+"\\\\avmx-s03\\Departments\\Test Engineering"+(char)34);
		proces.waitFor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(proces.getInputStream()));
		String Linea ="";
		while((Linea=reader.readLine())!=null) {
			System.out.print(Linea);
		}
		reader.close();
		}catch (IOException eio) {
	System.out.print(eio);
}
}

}
	
	
	
}
