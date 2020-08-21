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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
				
				try {
					leercategorias();
				} catch (ParserConfigurationException | SAXException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		 JButton [] botones = new JButton[listaEmpleados.getLength()];

		 JLabel lblId = new JLabel("ID");
			lblId.setBounds(40, 0, 46, 14);
			panel.add(lblId);
			
			JLabel lblNombre = new JLabel("NOMBRE");
			lblNombre.setBounds(85, 0, 57, 14);
			panel.add(lblNombre);
			
			JLabel lblEstado = new JLabel("ESTADO");
			lblEstado.setBounds(175, 0, 66, 14);
			panel.add(lblEstado);
			
			JLabel lblFecha = new JLabel("FECHA");
			lblFecha.setBounds(275, 0, 46, 14);
			panel.add(lblFecha);
			
			JLabel lblHora = new JLabel("HORA");
			lblHora.setBounds(375, 0, 46, 14);
			panel.add(lblHora);
			
			JLabel lbldetalles = new JLabel("DETALLES");
			lbldetalles.setBounds(440, 0, 60, 14);
			panel.add(lbldetalles);



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
	              botones[temp] = new JButton();

	              ids[temp].setBounds(20, fila, 45, 20);
	              usuarios[temp].setBounds(65, fila, 85,20 );
	              estados[temp].setBounds(150, fila, 100, 20);
	              fechas[temp].setBounds(250, fila, 100, 20);
	              horas[temp].setBounds(350, fila, 100, 20);
	              botones[temp].setBounds(455, fila, 20, 20);

	              	         
	              ids[temp].setText(idxml);
	              usuarios[temp].setText(usuarioxml);
	              estados[temp].setText(estadoxml);
	              if(estadoxml.equals("DESHABILITADO")) {
	            	  botones[temp].setBackground(Color.RED);
	            	  //botones[temp].setEnabled(true);  
	            	  botones[temp].setToolTipText("Reparar");
	              }
	              else if(estadoxml.equals("HABILITADO")){
	            	  botones[temp].setBackground(Color.GREEN);
	            	  botones[temp].setEnabled(false);
	            	  botones[temp].setToolTipText("Bueno");
	              }
	              
	              fechas[temp].setText(fechaxml);
	              horas[temp].setText(horaxml);
	              
	              ids[temp].setEditable(false);
	              usuarios[temp].setEditable(false);
	              estados[temp].setEditable(false);
	              fechas[temp].setEditable(false);
	              horas[temp].setEditable(false);
	              
	              botones[temp].addActionListener(new ActionListener() {
	                	public void actionPerformed(ActionEvent arg0) {
	                		JPanel panel1 = new JPanel();
	                		JFrame frame = new JFrame();
	                		

	                  		JLabel lblid = new JLabel("ID:");
	                  		lblid.setBounds(10, 10, 60, 14);
	                  		panel1.add(lblid);
	                  		
	                  			JTextField txtid = new JTextField();
	                  			txtid.setBounds(30,8,80,20);
	                  			txtid.setEditable(false);
	                  			//txtnombre.setText(usuarioxml1);
	                  			panel1.add(txtid);
	                  		
	                  		JLabel lblhashcfg = new JLabel("HASH CFG: ");
	                  		lblhashcfg.setBounds(10, 30, 100, 14);
	                  		panel1.add(lblhashcfg);
	                  		
	                  			JTextField txthashcfg = new JTextField();
	                  			txthashcfg.setBounds(80,28,100,20);
	                  			txthashcfg.setEditable(false);
	                  			//txtnombre.setText(usuarioxml1);
	                  			panel1.add(txthashcfg);
	                  		
	                  		JLabel lblhashia32 = new JLabel("HASH BOOTIA32.efi: ");
	                  		lblhashia32.setBounds(10, 50, 120, 14);
	                  		panel1.add(lblhashia32);
	                  		
	                  			JTextField txthash32 = new JTextField();
	                  			txthash32.setBounds(130,49,100,20);
	                  			txthash32.setEditable(false);
                  				panel1.add(txthash32);
	                  		
	                  		JLabel lblhashx64 = new JLabel("HASH BOOTX64.efi: ");
	                  		lblhashx64.setBounds(10, 70, 120, 14);
	                  		panel1.add(lblhashx64);
	                  		
	                  			JTextField txthash64 = new JTextField();
	                  			txthash64.setBounds(130,70,100,20);
	                  			txthash64.setEditable(false);
	                  			panel1.add(txthash64);
	                  			
	                  		JLabel lblcomentarios = new JLabel("Comentarios: ");
	                  		lblcomentarios.setBounds(10, 100, 120, 14);
		                  	panel1.add(lblcomentarios);
		                  		
		                  		JTextArea txtacomentarios = new JTextArea();
		                  		txtacomentarios.setBounds(10, 130, 250, 100);
		                  		panel1.add(txtacomentarios);
	                	
	                		
	                		
	                		
	                		JButton btnNewButton = new JButton("Guardar");
	                      btnNewButton.addActionListener(new ActionListener() {
	                      	public void actionPerformed(ActionEvent arg0) {
	                      		
	                      	}
	                      });
	                      btnNewButton.setIcon(new ImageIcon(ConsultaTSU.class.getResource("/recursos/editar.png")));
	                      btnNewButton.setBounds(85, 255, 100, 30);
	                      panel1.add(btnNewButton);
	                      
	                      JButton btnverificar = new JButton("Verificar");
	                      btnverificar.addActionListener(new ActionListener() {
	                      	public void actionPerformed(ActionEvent arg0) {
	                      		
	                      	}
	                      });
	                      btnverificar.setIcon(new ImageIcon(ConsultaTSU.class.getResource("/recursos/editar.png")));
	                      btnverificar.setBounds(45, 233, 70, 20);
	                      panel1.add(btnverificar);
	                      
	                		panel1.setBounds(55,55,280,280);
	                		frame.setVisible(true);
	                		panel1.setLayout(null);
	                		frame.setSize(280, 330);   
	                		//frame.setTitle(usuarioxml1);
	                		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	                      frame.setLayout(new BorderLayout());
	                      frame.setContentPane(panel1);
	                       
	                      
	                		
	              	}
	              });



	              
	              panel.add(ids[temp]);
	              panel.add(usuarios[temp]);
	              panel.add(estados[temp]);
	              panel.add(fechas[temp]);
	              panel.add(horas[temp]);
	              panel.add(botones[temp]);

	              fila+=23;
	  	       
	             
	            
	             
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
public void leercategorias() throws ParserConfigurationException, SAXException, IOException {
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
	 JButton [] botones = new JButton[listado.length];
	 String [] categorias1 =  new String[listado.length];
	for (int i=0; i< listado.length; i++) {
		
		String remplazo = listado[i].replace(".xml", "");
		JFrame frame = new JFrame();
   		frame.setTitle(listado[i]);
		
		categorias[i] = new JTextField();
        botones[i] = new JButton();

		categorias[i].setBounds(20, fila, 170, 20);
        botones[i].setBounds(200, fila, 24, 19);

		categorias[i].setText(listado[i]);
        botones[i].setIcon(new ImageIcon(ConsultaTSU.class.getResource("/recursos/interoga.png")));
        botones[i].setToolTipText("DETALLEs");

        categorias[i].setEditable(false);
        botones[i].addActionListener(new ActionListener() {
        	
               	public void actionPerformed(ActionEvent arg0) {
               	JPanel	panel1 = new JPanel();
               
               		
               		JLabel lblnombre = new JLabel("Nombre:");
               		lblnombre.setBounds(10, 13, 60, 14);
               		panel1.add(lblnombre);
               		
               			JTextField txtnombre = new JTextField();
               			txtnombre.setBounds(80,8,120,20);
               			txtnombre.setEditable(false);
               			//txtnombre.setText(usuarioxml1);
               			panel1.add(txtnombre);
               		
               		JLabel lblversiones = new JLabel("Versiones: ");
               		lblversiones.setBounds(10, 37, 65, 14);
               		panel1.add(lblversiones);
               		
               			JTextField txtversiones = new JTextField();
               			txtversiones.setBounds(80,35,120,20);
               			txtversiones.setEditable(false);
               			//txttipo.setText(tipoxml1);
               			panel1.add(txtversiones);
               		
               		JLabel lblestado = new JLabel("Estado: ");
               		lblestado.setBounds(50, 65, 60, 14);
               		panel1.add(lblestado);
               		
               			JComboBox CBESTADO = new JComboBox();
               			CBESTADO.setModel(new DefaultComboBoxModel(new String[] {"Habilitado", "Deshabilitado"}));
               			CBESTADO.setBounds(100, 62, 100, 20);
               			CBESTADO.setEnabled(false);
               			//CBESTADO.setEditable(false);
               			
               			panel1.add(CBESTADO);
               			
               		JLabel lblHashcfg = new JLabel("Hash: ");
               		lblHashcfg.setBounds(60, 90, 60, 14);
               		panel1.add(lblHashcfg);
               		
               			JTextField txthash = new JTextField();
               			txthash.setBounds(100,88,250,20);
               			txthash.setEditable(false);
           				//txttipo.setText(tipoxml1);
           				panel1.add(txthash);
               		
               		JLabel lblhashia32 = new JLabel("hash bootia32: ");
               		lblhashia32.setBounds(10, 118, 100, 14);
               		panel1.add(lblhashia32);
               		
               			JTextField txthash32 = new JTextField();
               			txthash32.setBounds(100,115,250,20);
               			txthash32.setEditable(false);
           				//txttipo.setText(tipoxml1);
           				panel1.add(txthash32);
               			
               		JLabel lblhashx64 = new JLabel("hash bootx64: ");
               		lblhashx64.setBounds(10, 144, 100, 14);
               		panel1.add(lblhashx64);
               		
               			JTextField	txthash64 = new JTextField();
               			txthash64.setBounds(100,142,250,20);
               			txthash64.setEditable(false);
               			//txttipo.setText(tipoxml1);
               			panel1.add(txthash64);
               		
               		
               		JLabel lblfecha = new JLabel("fecha: ");
               		lblfecha.setBounds(60, 172, 60, 14);
               		panel1.add(lblfecha);
               		
               			JTextField txtfecha = new JTextField();
               			txtfecha.setBounds(100,170,120,20);
               			txtfecha.setEditable(false);
               			//txttipo.setText(tipoxml1);
               			panel1.add(txtfecha);
               		
               		JLabel lbltipo = new JLabel("tipo: ");
               		lbltipo.setBounds(68, 200, 60, 14);
               		panel1.add(lbltipo);
               		
               			JComboBox	cbtipo = new JComboBox();
               			cbtipo.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE", "SSD", "HD", "USB", "FLOPPY", "CD", "DVD", "HD", "FIREWARE"}));
               			cbtipo.setBounds(100, 198, 100, 20);
               			cbtipo.setEditable(false);
               			panel1.add(cbtipo);
           				
               		
               		JLabel lblcomentarios = new JLabel("comentarios: ");
               		lblcomentarios.setBounds(10, 230, 80, 14);
               		panel1.add(lblcomentarios);
               		
               		
               			JTextArea	txtcoemntarios = new JTextArea();
               			txtcoemntarios.setBounds(10, 258, 387, 85);
               			panel1.add(txtcoemntarios);
               		
   
                     
               		panel1.setBounds(55,55,400,400);
               		frame.setVisible(true);
               		panel1.setLayout(null);
               		frame.setSize(400, 400);   
               		
               		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                     frame.setLayout(new BorderLayout());
                     frame.setContentPane(panel1);
                     
       
        File archivo1 = new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\"+remplazo+".xml");
        String nombre = null;
        String versiones =null;
        String estado= null;
        String hash=null;
        String fecha= null;
        String tipo=null;
        String comentarios=null;
        String hashbootai = null;
        String hashbootx= null;
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = null;
		try {
			document = documentBuilder.parse(archivo1);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		document.getDocumentElement().normalize();
		//en la creacion de XML hagarra como  nombre del nodo como nodo hijo principal
		 NodeList listaEmpleados = document.getElementsByTagName("Catalogo");
		
		 for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
	         Node nodo = listaEmpleados.item(temp); 
	         nodo.getChildNodes().getLength();
	         NodeList nodeList = document.getDocumentElement().getChildNodes();
	         for (int ix = 0; ix < nodeList.getLength(); ix++) {
	             Node node = nodeList.item(ix);
	             Element elem = (Element) node;
//al no ser simepre mementes se creara una condicion para saber cuantos datos estan en el xml
	             //cantidad de nodos hijos de algun otro archivo 7
	             if(nodo.getChildNodes().getLength()==7) {
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
		            	  numeroestado=0;
		              }else if(estado.equals("Deshabilitado")){
		            	  numeroestado=1;
		              }
		              CBESTADO.setSelectedIndex(numeroestado);
		              
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
		              cbtipo.setSelectedIndex(numerotipo); 
		             txthash.setText(hash);
		            txtfecha.setText(fecha);
		            txtcoemntarios.setText(comentarios);
		           
	             }
	             //cantidad de nodos hijos de un xml de Memtest 9
	             else if(nodo.getChildNodes().getLength()==9) {
	            	 nombre = elem.getElementsByTagName("Nombre").item(0).getChildNodes().item(0).getNodeValue();
	            	 versiones = elem.getElementsByTagName("versiones").item(0).getChildNodes().item(0).getNodeValue();
	            	 estado = elem.getElementsByTagName("estado").item(0).getChildNodes().item(0).getNodeValue();
	            	 hash = elem.getElementsByTagName("hash").item(0).getChildNodes().item(0).getNodeValue();
	            	 hashbootai = elem.getElementsByTagName("hashbootia32").item(0).getChildNodes().item(0).getNodeValue();
	            	 hashbootx = elem.getElementsByTagName("hashbootx64").item(0).getChildNodes().item(0).getNodeValue();
		             fecha = elem.getElementsByTagName("fecha").item(0).getChildNodes().item(0).getNodeValue();
		             tipo = elem.getElementsByTagName("tipo").item(0).getChildNodes().item(0).getNodeValue();
		             comentarios = elem.getElementsByTagName("comentarios").item(0).getChildNodes().item(0).getNodeValue();
	              
	              txtnombre.setText(nombre);
	              txtversiones.setText(versiones);
	              int numeroestado = 0;
	              if(estado.equals("Habilitado")) {
	            	  numeroestado=0;
	              }else if(estado.equals("Deshabilitado")){
	            	  numeroestado=1;
	              }
	              CBESTADO.setSelectedIndex(numeroestado);
	              
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
	              cbtipo.setSelectedIndex(numerotipo);
	              
	             txthash.setText(hash);
	             txthash32.setText(hashbootai);
	             txthash64.setText(hashbootx);
	          
	             
	            txtfecha.setText(fecha);
	            txtcoemntarios.setText(comentarios);

	             		}
                  }
		 		} 		
            }
          });
       
		
        panel.add(categorias[i]);
        panel.add(botones[i]);
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
              		
              		JLabel lblnombre = new JLabel("Usuario:");
              		lblnombre.setBounds(10, 10, 60, 14);
              		panel1.add(lblnombre);
              		
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
                    		
                    		JLabel passold = new JLabel("Pass OLD:");
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
