package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ConsultaTSU extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable tblDatos;
	File Archivos[];
 	Object[] Arc;
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
		
		setTitle("TSU Creados");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 403, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar TSU");
		lblNewLabel.setBounds(10, 11, 86, 14);
		contentPane.add(lblNewLabel);
		
		table = new JTable();
		table.setBounds(10, 233, 414, -151);
		contentPane.add(table);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(82, 7, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditarTSU editartsu = new EditarTSU();
				editartsu.setVisible(true);
			}
		});
		btnEditar.setBounds(181, 7, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.setBounds(280, 7, 89, 23);
		contentPane.add(btnNewButton);
		
		tblDatos = new JTable();
		tblDatos.setBounds(20, 82, 349, 276);
		contentPane.add(tblDatos);
	}
	/*public void mostrarxml() {
		String Ruta = ("C:\\Archivo\\TSUCATEGORIAS");
		File archivos = new File(Ruta);
		String[] listado = archivos.list();
		
		if (listado == null || listado.length == 0)
		{ JOptionPane.showMessageDialog(null, "No hay elementos dentro de la carpeta actual"); 
		return;}
		else 
		{ 
			
		}
	}*/
}
	