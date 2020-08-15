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
	        setSize(538, 366);
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
		 panel.updateUI();
			}
	       }
public void leerids() throws ParserConfigurationException, SAXException, IOException {
		
		
	panel.removeAll();
	panel.repaint();
		
		File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\TSUREGISTRO\\ID.xml");
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
	              ids[temp].setText("TSU-"+idxml);
	              ids[temp].setEditable(false);
	              panel.add(ids[temp]);
	           
	              fila+=30;
	     		 panel.setPreferredSize(new Dimension(500, fila));
	    		 panel.updateUI();


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
		 panel.updateUI();


 		}

	}
public void leerusuarios() throws ParserConfigurationException, SAXException, IOException {
	panel.removeAll();
	panel.repaint();

	File archivo = new File("M:\\\\Test Software Utilities\\Usuarios\\RegistroDeUsuarios.xml");
	
	String usuarioxml = null;
	String tipoxml = null;
	
	int fila = 20;
	
 int a=2;
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
	Document document = documentBuilder.parse(archivo);
	
	document.getDocumentElement().normalize();
	 NodeList listaEmpleados = document.getElementsByTagName("USUARIOS");
	 
	
	 JTextField [] usuarios = new JTextField[listaEmpleados.getLength()];
	 JTextField [] tipos = new JTextField[listaEmpleados.getLength()];
	 JButton [] botones = new JButton[listaEmpleados.getLength()];
	 
	 	JLabel lblId = new JLabel("Usuarios");
		lblId.setBounds(20, 0, 100, 14);
		panel.add(lblId);
		
		JLabel lblNombre = new JLabel("Tipo");
		lblNombre.setBounds(120, 0, 100, 14);
		panel.add(lblNombre);
		
		/*JButton btnNewButton = new JButton("");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        btnNewButton.setIcon(new ImageIcon(ConsultaTSU.class.getResource("/recursos/interoga.png")));
        btnNewButton.setBounds(449, 43, 24, 19);
        panel.add(btnNewButton);
		*/
		


	 for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
		 
         Node nodo = listaEmpleados.item(temp);
         Element elem = (Element) nodo;
         
             if(nodo.getNodeType()==Node.ELEMENT_NODE) {
            	
              
            String  usuarioxml1 = elem.getElementsByTagName("USER").item(0).getTextContent();
            String  tipoxml1 = elem.getElementsByTagName("TIPO").item(0).getTextContent();
              
              usuarios[temp] = new JTextField();
              tipos[temp] = new JTextField();
              botones[temp] = new JButton();

              usuarios[temp].setBounds(20, fila, 85, 20);
              tipos[temp].setBounds(120, fila, 120, 20 );
              botones[temp].setBounds(250, fila, 24, 19);
             
             

              	         
              usuarios[temp].setText(usuarioxml1);
              tipos[temp].setText(tipoxml1);
              botones[temp].setIcon(new ImageIcon(ConsultaTSU.class.getResource("/recursos/interoga.png")));
              
              
             
              usuarios[temp].setEditable(false);
              tipos[temp].setEditable(false);
              botones[temp].addActionListener(new ActionListener() {
              	public void actionPerformed(ActionEvent arg0) {
              		JPanel panel1 = new JPanel();
              		JFrame frame = new JFrame();
              		
              		JLabel nombre = new JLabel("Usuario:");
              		nombre.setBounds(10, 10, 60, 14);
              		panel1.add(nombre);
              		
              		JLabel tipo = new JLabel("Tipo: ");
              		tipo.setBounds(10, 30, 60, 14);
              		panel1.add(tipo);
              		
              		JTextField txtnombre = new JTextField();
              		txtnombre.setBounds(80,8,120,20);
              		txtnombre.setEditable(false);
              		txtnombre.setText(usuarioxml1);
              		panel1.add(txtnombre);
              		
              		JTextField txttipo = new JTextField();
              		txttipo.setBounds(80,28,120,20);
              		txttipo.setEditable(false);
              		txttipo.setText(tipoxml1);
              		panel1.add(txttipo);
              		
              		JButton btnNewButton = new JButton("Editar");
                    btnNewButton.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent arg0) {
                    		txtnombre.setEditable(true);
                    		txttipo.setEditable(true);
                    		
                    		JLabel passold = new JLabel("Contras\ne�a \nVieja:");
                    		passold.setBounds(10, 50, 100, 20);
                      		panel1.add(passold);
                      		
                      		JTextField txtpassold = new JTextField();
                      		txtpassold.setBounds(80,48,120,20);
                      		panel1.add(txtpassold);
                    		panel1.updateUI();
                    	}
                    });
                    btnNewButton.setIcon(new ImageIcon(ConsultaTSU.class.getResource("/recursos/editar.png")));
                    btnNewButton.setBounds(100, 125, 100, 30);
                    panel1.add(btnNewButton);
                    
              		panel1.setBounds(55,55,100,100);
              		frame.setVisible(true);
              		panel1.setLayout(null);
              		frame.setSize(280, 200);   
              		frame.setTitle(usuarioxml1);
              		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setLayout(new BorderLayout());
                    frame.setContentPane(panel1);
                     
                    
              		
            	}
            });


              
              panel.add(usuarios[temp]);
              panel.add(tipos[temp]);
              panel.add(botones[temp]);

              fila+=30;
              
     		 panel.setPreferredSize(new Dimension(500, fila));
    		 panel.updateUI();


              
            
             
             }
              }
	 
	
	}
}
