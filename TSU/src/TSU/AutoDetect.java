package TSU;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class AutoDetect {




public static void demonio() {
    Thread t = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } 
                
                //crea un demonio aqui hace la funcion que quieres que haga el demonio.
                try {
					DriverChecker.deteccion();
				} catch (NoSuchAlgorithmException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
               
                
               
            }
        }
    });
    t.start();
}
}