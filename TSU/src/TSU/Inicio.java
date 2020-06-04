package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Point;
import java.awt.Frame;

public class Inicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
					//frame.setLocationRelativeTo(null);
					frame.setLocation(500, 400);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		
		setTitle("Test Software Utilities");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 109);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Consulta");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Consulta TSU");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaTSU CTSU = new ConsultaTSU();
				CTSU.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("TSU");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCrearTsu = new JMenuItem("Crear TSU");
		mntmCrearTsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearTSU CTSU= new CrearTSU();
				CTSU.setVisible(true);
			}
			
		});
		mnNewMenu_1.add(mntmCrearTsu);
		
		JMenuItem mntmEditarTsu = new JMenuItem("Editar TSU");
		mntmEditarTsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarTSU ETSU = new EditarTSU();
				ETSU.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmEditarTsu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("");
		menuBar.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVerificar = new JButton("VERIFICAR");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					Verificacion V = null;	
					Registrar R = new Registrar();

					try {
						V = new Verificacion();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//R.setBounds(0, 200, 500, 500);
					//R.setVisible(true);
					
					V.setVisible(true);
				
				}
			
		});
		btnVerificar.setBounds(62, 11, 105, 23);
		contentPane.add(btnVerificar);
		
		JButton btnAsignarId = new JButton("Asignar ID");
		btnAsignarId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAsignarId.setBounds(245, 11, 89, 23);
		contentPane.add(btnAsignarId);
	}
}
