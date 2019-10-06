package commonClasses;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by f3307530 on 2015/02/16.
 */
public class CommonFileTester {

    public CommonFileTester(){};

    public void saveParamChanges() {

        try {
            Properties props = new Properties();
            props.setProperty("EMSServer", "tcp://10.5.1.7:7222");
            props.setProperty("EMSUser", "fdane");
            props.setProperty("EMSPassword", "d8Adkd(gdf");
            props.setProperty("FTPServer", "10.5.1.9");
            props.setProperty("FTPPort", "22");
            props.setProperty("FTPUser", "sftp_tiplus");
            props.setProperty("FTPPassword", "sftp_tiplus");
            props.setProperty("JDBCUrl", "jdbc:postgresql://localhost:5433/TestDataWharehouse");
            props.setProperty("JDBCUser", "postgres");
            props.setProperty("JDBCPassword", "admin");


            File f = new File("server.properties");
            OutputStream out = new FileOutputStream( f );
            props.store(out, "This is an optional header comment string");
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public String readFile(String file ) {
        BufferedReader reader = null;
        StringBuilder  stringBuilder = null;
        try {
            reader = new BufferedReader( new FileReader(file));
            String line = null;
            stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");

            while( ( line = reader.readLine() ) != null ) {
                stringBuilder.append( line );
                stringBuilder.append( ls );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public Properties loadParams() {
        Properties props = new Properties();
        InputStream is = null;
        // First try loading from the current directory
        try {
            File f = new File("environment/activeenvironment/server.properties");
            is = new FileInputStream( f );
        }
        catch ( Exception e ) { is = null; }

        try {
            if ( is == null ) {
                // Try loading from classpath
                is = getClass().getResourceAsStream("server.properties");
            }
            // Try loading properties from the file (if found)
            props.load( is );
        }
        catch ( Exception e ) {
        }
        return props;
    }

    public void writeFile(String fileName, String newContent){
        try {

            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(newContent);
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
           // System.out.println(    "Error writing to file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public String readFile(String path, Charset encoding)throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();

    }

       public boolean writeMessageToFile(String directory,String fileName,String Msg)throws IOException{

        boolean flag = false;

        String[] holdMsg = Msg.split("#");
        File file = new File(directory+File.separator+fileName);

        if (!file.exists()){
            file.createNewFile();
        }
        else if (file.exists())
            file.delete();

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (int j = 0; j <holdMsg.length; j++ ){
            bw.write(holdMsg[j]);
            if (!holdMsg[j].contains("-}"))
                bw.newLine();
        }
        bw.close();
        fw.close();
        flag = true;

        return flag;
    }


    /** This method deletes a file or a directory
     * @param file  - the file instance to be deleted
     */
    public void deleteFiles(File file)throws Exception {

        try {
            if(file.isDirectory()){
                //directory is empty, then delete it
                if(file.list().length==0){
                    file.delete();
                    // System.out.println("Directory is deleted : " + file.getAbsolutePath());
                }else{
                    //list all the directory contents
                    String files[] = file.list();
                    for (String temp : files) {
                        //construct the file structure
                        File fileDelete = new File(file, temp);
                        //recursive delete
                        deleteFiles(fileDelete);
                    }
                    //check the directory again, if empty then delete it
                    if(file.list().length==0){
                        file.delete();
                        //  System.out.println("Directory is deleted : "
                    }
                }
            }else{
                //if file, then delete it
                file.delete();
            }
        }    catch (Exception ex){
            throw new Exception("Failed to delete files in "+file+"\t"+ex.getMessage());
        }

    }

}
