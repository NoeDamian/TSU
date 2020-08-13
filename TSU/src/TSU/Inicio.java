package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Point;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;

public class Inicio extends JFrame {

	private static JPanel contentPane;
	JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
	public String Equipolocal;
	public boolean swcontrol;
	public static JLabel label = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					funcionactiva();
					frame.addWindowListener(new WindowAdapter() {
					    @Override
					    public void windowClosing(WindowEvent windowEvent) {
					       
					    	if (JOptionPane.showConfirmDialog(frame, 
					            "Seguro Que Deseas Salir?", "Cerrar", 
					            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					    		try {
									logger.crearLog("Sesion Finalizada...");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
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
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public Inicio() throws ParserConfigurationException, TransformerException, SAXException, IOException {
		
		
		JOptionPane.showConfirmDialog(null,"okok");

		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/recursos/tecnologia.png")));
		crearcarpetas();
		
		setTitle("Test Software Utilities");
		setBounds(100, 100, 290, 133);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_2 = new JMenu("Login");
		menuBar.add(mnNewMenu_2);
		mntmCerrarSesion.setIcon(new ImageIcon(Inicio.class.getResource("/recursos/cerrar.png")));
		
		
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cerrarsecion();
				} catch (ParserConfigurationException | TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					logger.crearLog("Sesion Finalizada...");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_2.add(lblUsuario);
		
		login log = new login();
		
		String usuarioxml =log.USER;
		String tipo = log.TIPO;
		try {
			String localMachine = InetAddress.getLocalHost().getHostName();
			 Equipolocal = localMachine;
		}
		catch(IOException e) {e.printStackTrace();}
		

		JMenuItem menuItemusuario = new JMenuItem("");
		
		
		menuItemusuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usuario.main(null);
			}
		});
		mnNewMenu_2.add(menuItemusuario);
		mnNewMenu_2.add(mntmCerrarSesion);
		
		JMenu menuconsulta = new JMenu("Consulta");
		menuBar.add(menuconsulta);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Consulta TSU");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaTSU.main(null);
			}
		});
		menuconsulta.add(mntmNewMenuItem);
		
		JMenu menutsu = new JMenu("TSU");
		menuBar.add(menutsu);
		
		JMenuItem mntmCrearTsu = new JMenuItem("Crear TSU");
		mntmCrearTsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearTSU CTSU= new CrearTSU();
				CTSU.setVisible(true);
			}
			
		});
		menutsu.add(mntmCrearTsu);
		
		JMenuItem mntmEditarTsu = new JMenuItem("Editar TSU");
		mntmEditarTsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarTSU ETSU = new EditarTSU();
				ETSU.setVisible(true);
			}
		});
		menutsu.add(mntmEditarTsu);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setIcon(new ImageIcon(Inicio.class.getResource("/recursos/botonverde.ico")));
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		JMenuItem mntmTutorial = new JMenuItem("Tutorial");
		mntmTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file = new File("M:\\Test Software Utilities\\Recursos\\TSU tutorial.pdf");
				try {
					Desktop.getDesktop().open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnAyuda.add(mntmTutorial);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVerificar = new JButton("VERIFICAR");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					Verificacion V = null;	
					Registrar R = new Registrar();

					V = new Verificacion();
				
					//R.setBounds(0, 200, 500, 500);
					//R.setVisible(true);
					
					V.setVisible(true);
				
				}
			
		});
		btnVerificar.setBounds(51, 11, 113, 23);
		contentPane.add(btnVerificar);
		
		JButton btnAsignarId = new JButton("Asignar ID");
		btnAsignarId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				asignacionid A =new asignacionid();
				A.setVisible(true);
				
			}
			
		});
		btnAsignarId.setBounds(51, 45, 113, 23);
		contentPane.add(btnAsignarId);
		
		label.setBackground(Color.GREEN);
		label.setBounds(253, 0, 21, 23);
		contentPane.add(label);
		
		
		if(tipo.equals("ADMINISTRADOR")) {
			menuItemusuario.setIcon(new ImageIcon(Inicio.class.getResource("/recursos/administrador.png")));
			menuItemusuario.setText(usuarioxml);
			logger.crearLog("| Usuario ingresado: "+usuarioxml+" | Tipo:"+tipo+"| Equipo: "+Equipolocal);

			
		}else if(tipo.equals("USER")) {
			
			menuItemusuario.setIcon(new ImageIcon(Inicio.class.getResource("/recursos/cuentausuer.png")));
			menuItemusuario.setText(usuarioxml);
			menuItemusuario.setEnabled(false);
			menutsu.setEnabled(false);
			btnAsignarId.setEnabled(false);
			menuconsulta.setEnabled(false);
			logger.crearLog("| Usuario ingresado: "+usuarioxml+" | Tipo:"+tipo+"| Equipo: "+Equipolocal);
		}
		

	}
	public void crearcarpetas() throws IOException {
		File carpetaorigen = new File("M:\\Test Software Utilities");
		File carpetasuborigen = new File("M:\\\\Test Software Utilities\\Archivo");
		File rutaregistros = new File("M:\\\\Test Software Utilities\\Archivo\\Registros");
		File rutacategorias = new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS");
		File rutatsuregistro = new File("M:\\\\Test Software Utilities\\Archivo\\TSUREGISTRO");
		if(!carpetaorigen.exists()) {
			carpetaorigen.mkdirs();
			carpetaorigen.isHidden();
			
			carpetasuborigen.mkdirs();
			carpetasuborigen.isHidden();
			
			rutaregistros.mkdirs();
			rutaregistros.isHidden();
			
			rutacategorias.mkdirs();
			rutacategorias.isHidden();
			
			rutatsuregistro.mkdirs();
			rutatsuregistro.isHidden();
			logger.crearLog("Se crearon carpetas");

		}else {
			
		}
		
	}
	
	public void cerrarsecion() throws ParserConfigurationException, TransformerException {
		login.main(null);
		setVisible(false);
		
	}
	
	public static void funcionactiva () {
		Thread t = new Thread(new Runnable() {
	        public void run() {
	            while (true) {
	                try {
	                    Thread.sleep(100);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                } 
	        		File ruta = new File("M:\\Test Software utilities\\");
	        		if(!ruta.exists()) {
	        			System.out.println("rojo");
	        			label.setIcon(new ImageIcon("/recursos/cerrar.png"));
	        			contentPane.repaint();
	        			if (JOptionPane.showConfirmDialog(null, 
					            "Coneccion Perdida \n Deseas Reconectar?",new ImageIcon("C:\\Users\\noe_moreno\\Downloads\\cerrar.png")+"(X)Desconectado(X)"+new ImageIcon("C:\\Users\\noe_moreno\\Downloads\\cerrar.png"), 
					            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
	        				try {
								login.networkdrive();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        			}else if(JOptionPane.QUESTION_MESSAGE == JOptionPane.NO_OPTION) {
	        		      			}
	        		}
	        		else if(ruta.exists()){
	        			label.setIcon(new ImageIcon("/recursos/wifi(2).png"));



	        		}
	          
	                	 
	                
	                
	               
					}

	            }
	    });
	    t.start();
	}
}