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
import org.xml.sax.SAXException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;

public class agregarusuarios extends JFrame {

	public static  JPanel contentPane;
	private JTextField txtusuario;
	private ButtonGroup grupotipo;
	JLabel labelpass = new JLabel("");
	private JPasswordField txtpass;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					agregarusuarios frame = new agregarusuarios();
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
	public agregarusuarios() {
		setTitle("Agregar Usuarios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 313, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("USUARIO: ");
		lblUsuario.setBounds(10, 23, 93, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setBounds(10, 57, 78, 14);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel = new JLabel("<html>REPETIR </br>PASSWORD:</html> ");
		lblNewLabel.setBounds(10, 82, 78, 35);
		contentPane.add(lblNewLabel);
		
		txtusuario = new JTextField();
		txtusuario.setBounds(81, 20, 86, 20);
		contentPane.add(txtusuario);
		txtusuario.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guardarusuario();
				} catch (ParserConfigurationException | SAXException | IOException | TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGuardar.setBounds(36, 143, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(173, 143, 89, 23);
		contentPane.add(btnCancelar);
		
		labelpass.setBounds(81, 82, 122, 14);
		contentPane.add(labelpass);
		
		JRadioButton radAdministrador = new JRadioButton("Administrador");
		radAdministrador.setBounds(173, 19, 109, 23);
		contentPane.add(radAdministrador);
		radAdministrador.setActionCommand("ADMINISTRADOR");
		
		JRadioButton radUsuarioNormal = new JRadioButton("Usuario Normal");
		radUsuarioNormal.setSelected(true);
		radUsuarioNormal.setBounds(173, 45, 134, 23);
		contentPane.add(radUsuarioNormal);
		radUsuarioNormal.setActionCommand("USER");
		
		grupotipo = new ButtonGroup();
		grupotipo.add(radAdministrador);
		grupotipo.add(radUsuarioNormal);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(81, 54, 86, 20);
		contentPane.add(txtpass);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(81, 97, 86, 20);
		contentPane.add(txtpassword);
	}
	public void guardarusuario() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		File rutausuarios = new File("M:\\Test Software Utilities\\Usuarios\\RegistroDeUsuarios.xml");
		String usuario = txtusuario.getText();
		String password = String.valueOf(txtpass.getPassword());
		String repitpass= String.valueOf(txtpassword.getPassword());
		String tipo = grupotipo.getSelection().getActionCommand();
		if(!password.equals(repitpass)) {
			labelpass.setText("Password Diferentes");
		}else if(tipo==null){
			labelpass.setText("selecciona un tipo de usuario");
			
		}else {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
			Document doc = documentBuilder.parse(rutausuarios);
			doc.getDocumentElement().normalize();
			
			Node nodoraiz  = doc.getDocumentElement();
			
			Element Nuevodato = doc.createElement("USUARIOS");
	
			
			Element usuarios = doc.createElement("USER");
			usuarios.appendChild(doc.createTextNode(usuario));
			Nuevodato.appendChild(usuarios);
			
			Element pass = doc.createElement("PASSWORD");
			pass.appendChild(doc.createTextNode(password));
			Nuevodato.appendChild(pass);
			
			Element tipousuario = doc.createElement("TIPO");
			tipousuario.appendChild(doc.createTextNode(tipo));
			Nuevodato.appendChild(tipousuario);
			
			nodoraiz.appendChild(Nuevodato);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(new File("M:\\\\Test Software Utilities\\Usuarios\\RegistroDeUsuarios.xml"));
				
					transformer.transform(source, result);
					setVisible(false);
			
		}
	}
}
