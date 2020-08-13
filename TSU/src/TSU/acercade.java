package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javax.management.Notification;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;


public class acercade extends JFrame {

	public static JPanel ventanaprincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					acercade frame = new acercade();
					frame.setVisible(true);
					AutoDetect auto = new AutoDetect();
					DriverChecker checker = new DriverChecker();
					//checker.deteccion();
					auto.demonio();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * Create the frame.
	 */
	public acercade() {
		
		ventanaprincipal = new JPanel();
		ventanaprincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ventanaprincipal);
		ventanaprincipal.setLayout(null);
		
		JButton btnPress = new JButton("press");
		btnPress.setBounds(70, 55, 59, 23);
		ventanaprincipal.add(btnPress);
		btnPress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Verificacion veri  = new Verificacion();
				ventanaprincipal.removeAll();
				setContentPane(veri.ventana);
				veri.ventana.repaint();
			}
		});
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 239, 329);
		

		
		
		
	}
	public void notificacion() {
		
		
	}
	
}
	        
