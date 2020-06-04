package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AsignarID extends JFrame {
	File Unidades[];
	 Object items[];
	private JPanel contentPane;
	private JTextField textField;
	JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AsignarID frame = new AsignarID();
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
	public AsignarID() {
		buscarUnidades();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 231, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione Drive");
		lblNewLabel.setBounds(10, 11, 91, 14);
		contentPane.add(lblNewLabel);
		
		
		comboBox.setBounds(120, 8, 85, 20);
		contentPane.add(comboBox);
		
		JButton id = new JButton("Asignar ID");
		id.setBounds(45, 115, 89, 23);
		contentPane.add(id);
		
		JLabel lblNewLabel_1 = new JLabel("ID Asignado");
		lblNewLabel_1.setBounds(21, 53, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(119, 50, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
	public void buscarUnidades() {

		Unidades = File.listRoots();
		File unidades[] = File.listRoots();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		
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
}
