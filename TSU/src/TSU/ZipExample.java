package TSU;

import java.util.Calendar;
import java.util.Date;
import java.util.zip.*;
import java.io.*;
import java.text.SimpleDateFormat;


public class ZipExample {
	static Date date = Calendar.getInstance().getTime();
	static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

	public static String nombre = sdf.format(date);
       public static void main(String[] args){
           ZipHelper zippy = new ZipHelper();
           try {
               zippy.zipDir("M:\\Test Software Utilities","M:\\Copias De Seguridad TSU\\"+nombre+".rar");
           } catch(IOException e2) {
               System.err.println(e2);
           }
       }
}

class ZipHelper  
{
       public void zipDir(String dirName, String nameZipFile) throws IOException {
           ZipOutputStream zip = null;
           FileOutputStream fW = null;
           
           fW = new FileOutputStream(nameZipFile);
           zip = new ZipOutputStream(fW);
           addFolderToZip("", dirName, zip);
           zip.close();
           fW.close();
       }

       private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws IOException {
           File folder = new File(srcFolder);
           if (folder.list().length == 0) {
               addFileToZip(path , srcFolder, zip, true);
           }
           else {
               for (String fileName : folder.list()) {
                   if (path.equals("")) {
                       addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip, false);
                   } 
                   else {
                        addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip, false);
                   }
               }
           }
       }

       private void addFileToZip(String path, String srcFile, ZipOutputStream zip, boolean flag) throws IOException {
           File folder = new File(srcFile);
           if (flag) {
               zip.putNextEntry(new ZipEntry(path + "/" +folder.getName() + "/"));
           }
           else {
               if (folder.isDirectory()) {
                   addFolderToZip(path, srcFile, zip);
               }
               else {
                   byte[] buf = new byte[1024];
                   int len;
                   FileInputStream in = new FileInputStream(srcFile);
                   zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
                   while ((len = in.read(buf)) > 0) {
                       zip.write(buf, 0, len);
                   }
               }
           }
       }
}