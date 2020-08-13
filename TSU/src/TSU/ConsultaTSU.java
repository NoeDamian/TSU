package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ConsultaTSU extends JFrame {

	static JPanel panel = new JPanel();
 	Object[] Arc;
 	static int largo = 300;

 	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaTSU frame = new ConsultaTSU();
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
	public ConsultaTSU() {
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Registro TSU");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmDispocitivos = new JMenuItem("Dispocitivos");
		mntmDispocitivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leerdispocitivos();
				} catch (ParserConfigurationException | SAXException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmDispocitivos);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("ID's");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					leerids();
				} catch (ParserConfigurationException | SAXException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmCategorias = new JMenuItem("Categorias");
		mntmCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				leercategorias();
			}
		});
		mnNewMenu.add(mntmCategorias);
		
		JMenu mnUsuarios = new JMenu("Registro De Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAdministradores = new JMenuItem("Usuarios");
		mntmAdministradores.setIcon(new ImageIcon(ConsultaTSU.class.getResource("/recursos/usuario (1).png")));
		mntmAdministradores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					leerusuarios();
				} catch (ParserConfigurationException | SAXException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnUsuarios.add(mntmAdministradores);
		
			panel.setBorder(new LineBorder(new Color(255, 0, 0)));
	        panel.setPreferredSize(new Dimension(500, largo));

	        final JScrollPane scroll = new JScrollPane(panel);
	        panel.setLayout(null);
	        setTitle("Consulta");
			setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/recursos/tecnologia.png")));
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        getContentPane().setLayout(new BorderLayout());
	        getContentPane().add(scroll, BorderLayout.CENTER);
	        setSize(541, 411);
	        setVisible(true);

		
	}
	
	
	public void leerdispocitivos() throws ParserConfigurationException, SAXException, IOException {
		panel.removeAll();
		panel.repaint();
		File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\Registros\\Registro.xml");
		String idxml = null;
		String estadoxml = null;
		String usuarioxml = null;
		String fechaxml = null;
		String horaxml = null;
		int fila = 20;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
		Document document = documentBuilder.parse(archivo);
		
		document.getDocumentElement().normalize();
		 NodeList listaEmpleados = document.getElementsByTagName("Resultado");
		 JTextField [] ids = new JTextField[listaEmpleados.getLength()];
		 JTextField [] usuarios = new JTextField[listaEmpleados.getLength()];
		 JTextField [] estados = new JTextField[listaEmpleados.getLength()];
		 JTextField [] fechas = new JTextField[listaEmpleados.getLength()];
		 JTextField [] horas = new JTextField[listaEmpleados.getLength()];
		 JTextField [] lables = new JTextField[listaEmpleados.getLength()];
		 
		 JLabel lblId = new JLabel("ID");
			lblId.setBounds(20, 0, 46, 14);
			panel.add(lblId);
			
			JLabel lblNombre = new JLabel("NOMBRE");
			lblNombre.setBounds(102, 0, 57, 14);
			panel.add(lblNombre);
			
			JLabel lblEstado = new JLabel("ESTADO");
			lblEstado.setBounds(185, 0, 66, 14);
			panel.add(lblEstado);
			
			JLabel lblFecha = new JLabel("FECHA");
			lblFecha.setBounds(285, 0, 46, 14);
			panel.add(lblFecha);
			
			JLabel lblHora = new JLabel("HORA");
			lblHora.setBounds(385, 0, 46, 14);
			panel.add(lblHora);



		 for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
			 
	         Node nodo = listaEmpleados.item(temp);
	         Element elem = (Element) nodo;
	         
	             if(nodo.getNodeType()==Node.ELEMENT_NODE) {
	            	 
	              idxml = elem.getElementsByTagName("ID").item(0).getTextContent();
	              estadoxml = elem.getElementsByTagName("ESTADO").item(0).getTextContent();
	              usuarioxml = elem.getElementsByTagName("USUARIO").item(0).getTextContent();
	              fechaxml = elem.getElementsByTagName("FECHA").item(0).getTextContent();
	              horaxml = elem.getElementsByTagName("HORA").item(0).getTextContent();
	              
	              ids[temp] = new JTextField();
	              usuarios[temp] = new JTextField();
	              estados[temp] = new JTextField();
	              fechas[temp] = new JTextField();
	              horas[temp] = new JTextField();

	              ids[temp].setBounds(20, fila, 80, 20);
	              usuarios[temp].setBounds(100, fila, 85,20 );
	              estados[temp].setBounds(185, fila, 100, 20);
	              fechas[temp].setBounds(285, fila, 100, 20);
	              horas[temp].setBounds(385, fila, 100, 20);

	              	         
	              ids[temp].setText(idxml);
	              usuarios[temp].setText(usuarioxml);
	              estados[temp].setText(estadoxml);
	              fechas[temp].setText(fechaxml);
	              horas[temp].setText(horaxml);
	              
	              ids[temp].setEditable(false);
	              usuarios[temp].setEditable(false);
	              estados[temp].setEditable(false);
	              fechas[temp].setEditable(false);
	              horas[temp].setEditable(false);


	              
	              panel.add(ids[temp]);
	              panel.add(usuarios[temp]);
	              panel.add(estados[temp]);
	              panel.add(fechas[temp]);
	              panel.add(horas[temp]);

	              fila+=30;
	  	       
	             
	            
	             
	             }
	             
           }
			if(largo<fila) {
		 panel.setPreferredSize(new Dimension(500, fila));
			}
	       }
public void leerids() throws ParserConfigurationException, SAXException, IOException {
		
		
	panel.removeAll();
	panel.repaint();
		
		File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\TSUREGISTRO\\16072020.xml");
		String idxml = null;
		String estadoxml = null;
		String usuarioxml = null;
		String fechaxml = null;
		String horaxml = null;
		int fila = 20;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
		Document document = documentBuilder.parse(archivo);
		
		document.getDocumentElement().normalize();
		 NodeList listaEmpleados = document.getElementsByTagName("Identificacion");
		 
		 
		 JTextField [] ids = new JTextField[listaEmpleados.getLength()];
		 
		 JLabel lblId = new JLabel("ID");
			lblId.setBounds(20, 0, 46, 14);
			panel.add(lblId);
			


		 for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
			 
	         Node nodo = listaEmpleados.item(temp);
	         Element elem = (Element) nodo;
	         
	             if(nodo.getNodeType()==Node.ELEMENT_NODE) {
	            	 
	              idxml = elem.getElementsByTagName("ID").item(0).getTextContent();
	              ids[temp] = new JTextField();
	              ids[temp].setBounds(20, fila, 80, 20);
	              ids[temp].setText(idxml);
	              ids[temp].setEditable(false);
	              panel.add(ids[temp]);
	           
	              fila+=30;
	     		 panel.setPreferredSize(new Dimension(500, fila));

	             }
                  }
		 
	}
public void leercategorias() {
	panel.removeAll();
	panel.repaint();
	File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS");
	String[] listado = archivo.list();
	int fila=20;
	Object arc[]=new Object[listado.length];
	for(int i =0;i<listado.length;i++) {
		
		String s2=(listado[i]);
		arc[i]=s2;
	}
	
	Arc = arc;
		JLabel lblId = new JLabel("Nombre del TSU");
		lblId.setBounds(20, 0, 170, 14);
		panel.add(lblId);
	 JTextField [] categorias = new JTextField[listado.length];

	for (int i=0; i< listado.length; i++) {
		categorias[i] = new JTextField();
		categorias[i].setBounds(20, fila, 170, 20);
		categorias[i].setText(listado[i]);
        categorias[i].setEditable(false);
        panel.add(categorias[i]);

        fila+=21;
		 panel.setPreferredSize(new Dimension(500, fila));

 		}

	}
public void leerusuarios() throws ParserConfigurationException, SAXException, IOException {
	panel.removeAll();
	panel.repaint();

	File archivo = new File("M:\\\\Test Software Utilities\\Usuarios\\RegistroDeUsuarios.xml");
	String idxml = null;
	String estadoxml = null;
	String usuarioxml = null;
	String tipoxml = null;
	String horaxml = null;
	int fila = 20;
	
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
	Document document = documentBuilder.parse(archivo);
	
	document.getDocumentElement().normalize();
	 NodeList listaEmpleados = document.getElementsByTagName("USUARIOS");
	 
	
	 JTextField [] usuarios = new JTextField[listaEmpleados.getLength()];
	 JTextField [] tipos = new JTextField[listaEmpleados.getLength()];
	 
	 
	 	JLabel lblId = new JLabel("Usuarios");
		lblId.setBounds(20, 0, 100, 14);
		panel.add(lblId);
		
		JLabel lblNombre = new JLabel("Tipo");
		lblNombre.setBounds(120, 0, 100, 14);
		panel.add(lblNombre);
		
		



	 for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
		 
         Node nodo = listaEmpleados.item(temp);
         Element elem = (Element) nodo;
         
             if(nodo.getNodeType()==Node.ELEMENT_NODE) {
            	 
              
              usuarioxml = elem.getElementsByTagName("USER").item(0).getTextContent();
              tipoxml = elem.getElementsByTagName("TIPO").item(0).getTextContent();
              
              usuarios[temp] = new JTextField();
              tipos[temp] = new JTextField();

              usuarios[temp].setBounds(20, fila, 85, 20);
              tipos[temp].setBounds(120, fila, 120,20 );
             

              	         
              usuarios[temp].setText(usuarioxml);
              tipos[temp].setText(tipoxml);
              
              
             
              usuarios[temp].setEditable(false);
              tipos[temp].setEditable(false);
             


              
              panel.add(usuarios[temp]);
              panel.add(tipos[temp]);

              fila+=30;
              
     		 panel.setPreferredSize(new Dimension(500, fila));

              
            
              //label.setText("\r\n "+idxml+" "+estadoxml+" "+usuarioxml+" "+fechaxml+" "+horaxml);
           /* if(usuarioxml.equals(usuariolocal)) {
            	  temp = listaEmpleados.getLength();
              		}*/
             }
              }
	 
	
	}
}
