package TSU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vistalog extends JFrame {

	private JPanel contentPane;
	public boolean swcontrol;
	public String txt = logsactualizados.contenido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistalog frame = new vistalog();
					frame.setVisible(true);
					funcionactiva();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
	static DateFormat time = new SimpleDateFormat("HH:mm:ss");
	static Date date = new Date();
	static String fecha =sdf.format(date);
	public static String contenido;
	public static JTextArea textArea = new JTextArea(21,55);

	
	
	static File archivolog = new File("M:\\\\Test Software Utilities\\log\\"+fecha+".txt");
	static long archivologviejo  =  archivolog.length();
public vistalog() {
		try {
			verlog();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("LOG");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 676, 399);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.add(textArea, BorderLayout.CENTER);


		//textArea.setCaretPosition(textArea.getDocument().getLength());
		System.out.println(textArea.getDocument().getLength());
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 334, 640, -321);
		contentPane.add(scrollPane);
		
		
		
		}
	public static void verlog() throws IOException {
	
		
				FileReader fr = new FileReader(archivolog);
				BufferedReader br = new BufferedReader(fr);
				 String linea = br.readLine();
				while(linea != null) {
					contenido = linea;
					textArea.append(linea+"\n");
					linea = br.readLine();
						
				}
				           
				               fr.close();     
				            
				
			
		
		
	 
	}
	public static void logactualizado() throws IOException {
		 while (true) {
             try {
                 Thread.sleep(100);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
           
		if(!archivolog.exists()) {
			JOptionPane.showMessageDialog(null, "no existe ningun archivo log con el nombre de: "+fecha+".txt");
		}
		else {
			if(archivolog.length() > archivologviejo) {
				
				FileReader fr = new FileReader(archivolog);
				BufferedReader br = new BufferedReader(fr);
				 String linea = br.readLine();
				while((linea=br.readLine())!=null) {
						contenido = linea;
				textArea.append(contenido+"\n");
				}
					 try{                    
				            if( null != fr ){   
				               fr.close();     
				            }                  
				         }catch (Exception e2){ 
				            e2.printStackTrace();
				         }
				archivologviejo = archivolog.length();
				textArea.setCaretPosition(textArea.getDocument().getLength());

			}
		}
		
	 }
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
	                try {
	                	logactualizado();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	               
					}

	            }
	    });
	    t.start();
	}

}
