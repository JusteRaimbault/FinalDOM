/**
 * 
 */
package finaldom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * @author Raimbault Juste <br/> <a href="mailto:juste.raimbault@polytechnique.edu">juste.raimbault@polytechnique.edu</a>
 * 
 * Class proceeding to js includes and runs through rhino embedding.
 * 
 * Forked from Rhino Run example ©Rhino framework
 * 
 */
public class JSRunner {
		
	private static Context context;
	private static Scriptable scope;

	public static void init(){
		context = Context.enter();
		scope = context.initStandardObjects();
	}
	
	public static void exit(){Context.exit();}
	
	/**
	 * 
	 * @param command js commands as a string
	 * @return output by rhino
	 */
	public static String runCommand(String command){
		try {
			Log.debug("Executing command: "+command);

            Object result = context.evaluateString(scope, command, "log/text.log", 1, null);
            return Context.toString(result);
        }catch(Exception e){e.printStackTrace();return null;}
    }
	
	
	/**
	 * Loads js functions from file.
	 * 
	 * @param filePath
	 */
	public static void loadLibrary(String filePath){
		try{
			BufferedReader r = new BufferedReader(new FileReader(filePath));
			int currentchar=0;
			String command = "";
			while(currentchar!=-1){
				currentchar=r.read();
				if (currentchar!=-1){command += ((char) currentchar);}
			}
			
			Log.debug("Loading library "+filePath+"\n"+command);
			
			//same global context
			//pb, should also have a global scope
			//yes since objects are stored in the scope
            context.evaluateString(scope, command, "log/text.log", 1, null);
			
		}catch(Exception e){e.printStackTrace();}
	}
	
	/**
	 * Loads js functions from URL.
	 * 
	 * @param filePath
	 */
	public static void loadScriptFromURL(String urlPath){
		try{
			URLConnection con = new URL(urlPath).openConnection();
			con.setReadTimeout(5000);
			BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream()));
			int currentchar=0;
			String command = "";
			while(currentchar!=-1){
				currentchar=r.read();
				if (currentchar!=-1){command += ((char) currentchar);}
			}
			
			Log.debug("Loading library from "+urlPath+"\n"+command);
			
			//same global context
			//pb, should also have a global scope
			//yes since objects are stored in the scope
            context.evaluateString(scope, command, "log/text.log", 1, null);
			
		}catch(Exception e){e.printStackTrace();}
	}
	
	

}
