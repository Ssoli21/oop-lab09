package it.unibo.mvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;


/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private final static String USER_HOME = System.getProperty("user.home");
    private final static String FILE_SEPARATOR = System.getProperty("file.separator");
    private final static String DEFAULT_FILE = "output.txt";
    private File current = new File(USER_HOME + FILE_SEPARATOR + DEFAULT_FILE);

    public void setCurrent(final File file){
        current = file;
    }

    public File getCurrent(){
        return current;
    }

    public String getCurrentPath(){
        return current.toPath().toString();
    }

    public void write(final String string){
        current.setWritable(true);
        try {
           PrintStream ps = new PrintStream(current);
           ps.println(string);
           ps.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't fine file: " + current.toPath().toString());
            e.printStackTrace();
        }
    }


}
