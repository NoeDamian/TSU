package TSU;


	import java.awt.BorderLayout;
	import java.awt.GridLayout;
	import java.awt.event.*;
	import java.io.File;
	import javax.swing.*;
	import javax.swing.filechooser.FileSystemView;



	public class Dispositivos extends JFrame {


	 File unidades[];
	 JComboBox comboBox;
	 Object items[];
	 
	 
	 public  Dispositivos(){
	  
	  unidades = File.listRoots();
	     
	         
	       
	  comboBox=new JComboBox();
	  buscarUnidades();
	  
	  comboBox.addItemListener(new ItemListener(){
	   public void itemStateChanged(ItemEvent e) {
	    if(comboBox.getSelectedIndex()!=-1){
	    buscarUnidades();
	    JPanel p=new JPanel();
	    p.setLayout(new GridLayout(2,2));
	    
	    ImageIcon img=(ImageIcon)FileSystemView.getFileSystemView().getSystemIcon(unidades[comboBox.getSelectedIndex()]);
	    
	    JLabel lnombre=new JLabel(""+items[comboBox.getSelectedIndex()],img,JLabel.CENTER);
	    JLabel ldescripcion=new JLabel(FileSystemView.getFileSystemView().getSystemTypeDescription(unidades[comboBox.getSelectedIndex()]));
	    p.updateUI();
	    p.repaint();
	    repaint();
	    }
	   }
	   
	  });
	  JPanel pboton=new JPanel();
	  add(pboton,BorderLayout.SOUTH);
	  add(comboBox,BorderLayout.NORTH);
	 }
	 
	 public void buscarUnidades(){
	  unidades = File.listRoots();
	  Object it[]=new Object[unidades.length];
	  for (int i=0;i<unidades.length;i++) {
	         String s1 = FileSystemView.getFileSystemView().getSystemDisplayName (unidades[i]);
	         String s2 = FileSystemView.getFileSystemView().getSystemTypeDescription(unidades[i]);
	         if(FileSystemView.getFileSystemView().isFloppyDrive(unidades[i])){
	          s1="Unidad de Disquete (A:)";
	         }
	         if(s1.equalsIgnoreCase("")){
	          s1=s2;
	         }
	         it[i]=s1;
	       } 
	  items=it;
	  comboBox.removeAllItems();
	  for(int i=0;i<it.length;i++){
	   comboBox.addItem(it[i]);
	  }
	 }
	 
	 public static void main(String arg[]){
	  
	  
	  
	 }
	}


