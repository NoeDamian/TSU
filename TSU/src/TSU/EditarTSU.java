package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarTSU extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarTSU frame = new EditarTSU();
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
	public EditarTSU() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Editar TSU");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 617, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneTsu = new JLabel("Seleccione TSU");
		lblSeleccioneTsu.setBounds(10, 11, 90, 14);
		contentPane.add(lblSeleccioneTsu);
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		table.setBackground(Color.LIGHT_GRAY);
		table.setBounds(10, 218, 223, -169);
		contentPane.add(table);
		
		textField = new JTextField();
		textField.setBounds(287, 41, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTsu = new JLabel("TSU");
		lblTsu.setBounds(287, 11, 46, 14);
		contentPane.add(lblTsu);
		
		textField_1 = new JTextField();
		textField_1.setBounds(395, 41, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(395, 11, 60, 14);
		contentPane.add(lblNombres);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(287, 89, 60, 14);
		contentPane.add(lblEstado);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Habilitado", "Deshabilitado"}));
		comboBox.setBounds(287, 114, 86, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Comentarios");
		lblNewLabel.setBounds(395, 89, 72, 14);
		contentPane.add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(230, 230, 250));
		textPane.setBounds(395, 114, 184, 109);
		contentPane.add(textPane);
		
		JLabel lblPrgramas = new JLabel("Prgramas");
		lblPrgramas.setBounds(287, 209, 60, 14);
		contentPane.add(lblPrgramas);
		
		JLabel lblNombreDelTsu = new JLabel("Nombres(versiones)");
		lblNombreDelTsu.setBounds(395, 256, 132, 14);
		contentPane.add(lblNombreDelTsu);
		
		textField_2 = new JTextField();
		textField_2.setBounds(395, 278, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.setBounds(392, 309, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Borrar");
		btnNewButton_1.setBounds(395, 336, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Guardar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		dispose();
			}
		});
		btnNewButton_2.setBounds(231, 429, 116, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnBuscartsu = new JButton("BuscarTSU");
		btnBuscartsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscarTSU btsu = new BuscarTSU();
				btsu.setVisible(true);
			}
		});
		btnBuscartsu.setBounds(117, 7, 89, 23);
		contentPane.add(btnBuscartsu);
	}
}
