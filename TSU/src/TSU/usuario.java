package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class usuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usuario frame = new usuario();
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
	public usuario() {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 488, 351);
		setTitle(login.USER);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnAgregarUsuarios = new JButton("Agregar Usuarios");
		btnAgregarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarusuarios.main(null);
				
			}
		});
		menuBar.add(btnAgregarUsuarios);
		
		JButton btnLog = new JButton("LOG");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			vistalog.main(null);
			}
		});
		menuBar.add(btnLog);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbnombre = new JLabel("");
		lbnombre.setBounds(10, 11, 96, 14);
		lbnombre.setText(login.USER);
		contentPane.add(lbnombre);
		
		JLabel lbtipo = new JLabel("");
		lbtipo.setText(login.TIPO);
		lbtipo.setBounds(10, 36, 96, 14);
		contentPane.add(lbtipo);
		
		JLabel imagen = new JLabel("");
		imagen.setBounds(215, 0, 247, 279);
		contentPane.add(imagen);
		imagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==3) {
					
					
					
				}
				
				
			}
		});
		imagen.setIcon(new ImageIcon(""));
		imagen.repaint();
			
	}
	public void paintComponent (Graphics g){
		
		}
}
