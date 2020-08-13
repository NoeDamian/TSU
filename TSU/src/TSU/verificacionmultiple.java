package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class verificacionmultiple extends JFrame {

	public static JPanel ventanaprincipal;
	public static JPanel ventana = new JPanel();
	static JComboBox comboboxtsu = new JComboBox();
	Object[] Arc;
	String nl = System.getProperty("line.separator");
	private final JLabel lblEstado = new JLabel("ESTADO");



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					verificacionmultiple frame = new verificacionmultiple();
					frame.setVisible(true);
					AutoDetect auto = new AutoDetect();
					auto.demonio();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public verificacionmultiple() {
		listadearchivo();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 389);
		ventanaprincipal = new JPanel();
		ventanaprincipal.setBorder(new EmptyBorder(7, 3, 3, 2));
		setContentPane(ventanaprincipal);
		ventanaprincipal.setLayout(null);
		
		ventana.setBounds(10, 21, 410, 284);
		ventanaprincipal.add(ventana);
		ventana.setLayout(null);
		
		comboboxtsu.setBounds(137, 316, 135, 20);
		ventanaprincipal.add(comboboxtsu);
		lblEstado.setBounds(347, -4, 59, 25);
		
		ventanaprincipal.add(lblEstado);
		
		JLabel lblIds = new JLabel("ID's");
		lblIds.setBounds(225, 1, 46, 14);
		ventanaprincipal.add(lblIds);
	}
	public void listadearchivo() {
		File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\TSUCATEGORIAS\\");
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
}
