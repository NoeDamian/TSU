package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.FileChooserUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Formatter;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.math.BigInteger;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor; 



public class Verificacion extends JFrame {

	private JPanel contentPane;
	File Unidades[];
		Object items[];
		
	File Archivos[];
	 	Object[] Arc;
	 	
		String nl = System.getProperty("line.separator");

	 JComboBox comboBox=new JComboBox();
	 private JTextField estado;

	JComboBox comboboxtsu = new JComboBox();
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				

				try {
					Verificacion frame = new Verificacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public Verificacion() throws NoSuchAlgorithmException, IOException, ParserConfigurationException, SAXException {
		buscarUnidades();
		listadearchivo();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Verificacion");
		setBounds(100, 100, 242, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		comboBox.setBounds(10, 36, 83, 20);
		contentPane.add(comboBox);
			
		JLabel lblNewLabel = new JLabel("Seleccione Drive");
		lblNewLabel.setBounds(10, 11, 110, 14);
		contentPane.add(lblNewLabel);

		JButton btnVerificar = new JButton("verificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verificaciondedriver();
				File sFichero = verificaciondedriver();
				
				if(sFichero.exists()) {
						try {
							leerxml();
						} catch (ParserConfigurationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SAXException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							hash();
						} catch (NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParserConfigurationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SAXException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							verificar();
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
						

					
					}
				else{
			    JOptionPane.showMessageDialog(null, "No Existe documento");


					}
				}
		});				
		
		
		
		 
		btnVerificar.setBounds(56, 191, 89, 23);
		contentPane.add(btnVerificar);
		
		JLabel lblSeleccioneTsu = new JLabel("Seleccione  TSU");
		lblSeleccioneTsu.setBounds(113, 11, 101, 14);
		contentPane.add(lblSeleccioneTsu);
		
		comboboxtsu.setBounds(113, 36, 101, 20);
		contentPane.add(comboboxtsu);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				comboBox.removeAllItems();
				comboboxtsu.removeAllItems();
				File archivo = new File("C:\\Archivo\\TSUCATEGORIAS\\");

				CrearTSU frametsu = new CrearTSU();
				String[] listado = archivo.list();

				
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
				  Object arc[]=new Object[listado.length];
					for(int i =0;i<listado.length;i++) {
						String s2=(listado[i]);
						arc[i]=s2;
					}
					Arc = arc;
					comboboxtsu.removeAllItems();
				 for (int i=0; i< listado.length; i++) {
					 comboboxtsu.addItem(Arc[i]);

				 }
			}
		});
		
		btnActualizar.setBounds(48, 67, 110, 23);
		contentPane.add(btnActualizar);
		
		estado = new JTextField();
		estado.setForeground(new Color(255, 255, 255));
		estado.setFont(new Font("Times New Roman", Font.BOLD, 18));
		estado.setHorizontalAlignment(SwingConstants.CENTER);
		estado.setBackground(new Color(255, 255, 255));
		estado.setEditable(false);
		estado.setBounds(10, 126, 213, 54);
		contentPane.add(estado);
		estado.setColumns(10);
	}


	public void buscarUnidades() {

		Unidades = File.listRoots();
		File unidades[] = File.listRoots();
		
		Object it[]=new Object[Unidades.length];
		  for (int i=0;i<unidades.length;i++) {
			  File s1 = (unidades[i]);
		         it[i]=s1;
		       }
		  
		  items=it;
		  comboBox.removeAllItems();
		  comboBox.update(null);
		  for(int i=0;i<it.length;i++){
		  comboBox.addItem(it[i]);
		  }
		 
	}
	public  String leerxml() throws ParserConfigurationException, SAXException, IOException {
		String N = (String) (comboboxtsu.getSelectedItem());
		String remplazo = N.replace(".xml", "");
        File archivo = new File("C:\\Archivo\\TSUCATEGORIAS\\"+remplazo+".xml");
		String hash21 = "";
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
		Document document = documentBuilder.parse(archivo);
		
		document.getDocumentElement().normalize();

		 NodeList listaEmpleados = document.getElementsByTagName(remplazo);
		 
		 for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
	         Node nodo = listaEmpleados.item(temp);
	         nodo.getChildNodes().getLength();
	         NodeList nodeList = document.getDocumentElement().getChildNodes();
	         for (int i = 0; i < nodeList.getLength(); i++) {
	             Node node = nodeList.item(i);
	             Element elem = (Element) node;

	              hash21 = elem.getElementsByTagName("hash").item(0).getChildNodes().item(0).getNodeValue();
                  }
		 }
		return hash21;
		
		}
	public  String hash() throws NoSuchAlgorithmException, IOException, ParserConfigurationException, SAXException {
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
	public void verificar() throws ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException {
		String filehash = hash();
		String hsh = leerxml();
		

		if (filehash.equals(hsh)){
		    estado.setText("Coinciden");
			estado.setBackground(new Color(102, 204, 51));
	    }
	    else {
	    	
	    		estado.setText("No Coinciden");
	    		estado.setBackground(new Color(255, 0, 0));
	    		
	    		}
		
	}	
	public void listadearchivo() {
	File archivo = new File("C:\\Archivo\\TSUCATEGORIAS\\");
		CrearTSU frametsu = new CrearTSU();
	    

		String[] listado = archivo.list();
		
		if (listado == null || listado.length == 0) {
			
		 JOptionPane.showMessageDialog(null, "No hay elementos TSU"+nl+"Crear TSU desado");
		 frametsu.setVisible(true);
		 
		}
		else {
			Object arc[]=new Object[listado.length];
			for(int i =0;i<listado.length;i++) {
				String s2=(listado[i]);
				arc[i]=s2;
			}
			Arc = arc;
			
			comboboxtsu.removeAllItems();
			  comboboxtsu.update(null);
		 for (int i=0; i< listado.length; i++) {
			 comboboxtsu.addItem(Arc[i]);

		 }
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
	}
	
	

	
