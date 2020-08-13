package TSU;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ejemplo extends JFrame{
	static JPanel panel = new JPanel();
	public ejemplo() throws HeadlessException {
        
        panel.setBorder(BorderFactory.createLineBorder(Color.red));
        panel.setPreferredSize(new Dimension(400, 300));

        final JScrollPane scroll = new JScrollPane(panel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        setSize(300, 300);
        setVisible(true);
    }
	 public static void main(final String[] args) throws Exception {
         SwingUtilities.invokeLater(new Runnable() {
             @Override
             public void run() {
                 new ejemplo().setVisible(true);
             }
         });
     }
	 public void leerdispocitivos() throws ParserConfigurationException, SAXException, IOException {
			
			

			File archivo = new File("M:\\\\Test Software Utilities\\Archivo\\Registros\\Registro.xml");
			String idxml = null;
			String estadoxml = null;
			String usuarioxml = null;
			String fechaxml = null;
			String horaxml = null;
			int fila = 20;
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder =dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			
			document.getDocumentElement().normalize();
			 NodeList listaEmpleados = document.getElementsByTagName("Resultado");
			 JTextField [] ids = new JTextField[listaEmpleados.getLength()];
			 JTextField [] usuarios = new JTextField[listaEmpleados.getLength()];
			 JTextField [] estados = new JTextField[listaEmpleados.getLength()];
			 JTextField [] fechas = new JTextField[listaEmpleados.getLength()];
			 JTextField [] horas = new JTextField[listaEmpleados.getLength()];
			 JTextField [] lables = new JTextField[listaEmpleados.getLength()];
			 
			 JLabel lblId = new JLabel("ID");
				lblId.setBounds(20, 0, 46, 14);
				panel.add(lblId);
				
				JLabel lblNombre = new JLabel("NOMBRE");
				lblNombre.setBounds(102, 0, 57, 14);
				panel.add(lblNombre);
				
				JLabel lblEstado = new JLabel("ESTADO");
				lblEstado.setBounds(185, 0, 66, 14);
				panel.add(lblEstado);
				
				JLabel lblFecha = new JLabel("FECHA");
				lblFecha.setBounds(285, 0, 46, 14);
				panel.add(lblFecha);
				
				JLabel lblHora = new JLabel("HORA");
				lblHora.setBounds(385, 0, 46, 14);
				panel.add(lblHora);



			 for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
				 
		         Node nodo = listaEmpleados.item(temp);
		         Element elem = (Element) nodo;
		         
		             if(nodo.getNodeType()==Node.ELEMENT_NODE) {
		            	 
		              idxml = elem.getElementsByTagName("ID").item(0).getTextContent();
		              estadoxml = elem.getElementsByTagName("ESTADO").item(0).getTextContent();
		              usuarioxml = elem.getElementsByTagName("USUARIO").item(0).getTextContent();
		              fechaxml = elem.getElementsByTagName("FECHA").item(0).getTextContent();
		              horaxml = elem.getElementsByTagName("HORA").item(0).getTextContent();
		              
		              ids[temp] = new JTextField();
		              usuarios[temp] = new JTextField();
		              estados[temp] = new JTextField();
		              fechas[temp] = new JTextField();
		              horas[temp] = new JTextField();

		              ids[temp].setBounds(20, fila, 80, 20);
		              usuarios[temp].setBounds(100, fila, 85,20 );
		              estados[temp].setBounds(185, fila, 100, 20);
		              fechas[temp].setBounds(285, fila, 100, 20);
		              horas[temp].setBounds(385, fila, 100, 20);

		              	         
		              ids[temp].setText(idxml);
		              usuarios[temp].setText(usuarioxml);
		              estados[temp].setText(estadoxml);
		              fechas[temp].setText(fechaxml);
		              horas[temp].setText(horaxml);
		              
		              ids[temp].setEditable(false);
		              usuarios[temp].setEditable(false);
		              estados[temp].setEditable(false);
		              fechas[temp].setEditable(false);
		              horas[temp].setEditable(false);


		              
		              panel.add(ids[temp]);
		              panel.add(usuarios[temp]);
		              panel.add(estados[temp]);
		              panel.add(fechas[temp]);
		              panel.add(horas[temp]);

		              fila+=30;
		              
		              
		            
		              //label.setText("\r\n "+idxml+" "+estadoxml+" "+usuarioxml+" "+fechaxml+" "+horaxml);
		           /* if(usuarioxml.equals(usuariolocal)) {
		            	  temp = listaEmpleados.getLength();
		              		}*/
		             }
		             
	                  }
				
			
			
		        }
	
}
