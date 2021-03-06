/**
 * 
 */
package finaldom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.tools.shell.Global;
import org.mozilla.javascript.tools.shell.Main;

/**
 * @author Raimbault Juste <br/> <a href="mailto:juste.raimbault@polytechnique.edu">juste.raimbault@polytechnique.edu</a>
 *
 */
public class Test {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//always init the context first
		JSRunner.init();
		Log.setDebugMode(true);
		
		//testJSRunner();
		//printFromURL("https://www.facebook.com");
		//testExecuteScript();
		testload();
		
	}
	
	
	
	
	public static void testload(){
		//JSRunner.loadLibrary("js/env.rhino.1.2.js");
		//System.out.println(JSRunner.runCommand("f(7);"));	
		//JSRunner.loadScriptFromURL("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yL/r/ut2d0ouCEt9.js");
		

		Context cx = ContextFactory.getGlobal().enterContext();
		cx.setOptimizationLevel(-1);
		cx.setLanguageVersion(Context.VERSION_1_5);
		Global global = Main.getGlobal();
		global.init(cx);
		try {
			Main.processSource(cx, "js/env.rhino.1.2.js");
			Main.processSource(cx, "js/jquery-1.9.1.js");
			cx.evaluateString(global, "window.location=\"https://www.facebook.com\";console.log($(document.body).html())", "log/text.log", 1, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void testExecuteScript(){
		Document doc = getDOMFromURL("https://www.facebook.com");
		//System.out.println(doc);
		
		for(Element e: doc.getElementsByTag("script")){
			
			System.out.println(e);
			
			String libpath = e.attr("src");
			Log.debug(libpath);
			if (libpath.length()>0){JSRunner.loadScriptFromURL(libpath);}
			//else{JSRunner.runCommand(e.html());}
			
			//first test script embedded in the page and not from external sources
			//if(e.html().length()>1){System.out.println(JSRunner.runCommand(e.html()));break;}
			
		}
	}
	

	public static void testJSRunner(){
		System.out.println(JSRunner.runCommand("alert(\"TOS\")"));	
	}
	
	
	public static org.jsoup.nodes.Document getDOMFromURL(String urlpath){
		try{
			org.jsoup.nodes.Document document = Jsoup.parse(new URL(urlpath), 3000);
			
			return document;
		}
		catch(java.net.SocketTimeoutException e){return new org.jsoup.nodes.Document("<html></html>");}
		catch(org.jsoup.HttpStatusException e){return new org.jsoup.nodes.Document("<html></html>");}
		catch(Exception e){System.out.println("ERROR:"+e.toString());return null;}
	}
	
	
	
	/**
	 * Prints the source of the given url.
	 * 
	 * @param urlstring path of the url
	 */
	public static void printFromURL(String urlstring){
		try{
			URL url = new URL(urlstring);
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(url.openStream()));
	
	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	            System.out.println(inputLine);
	        in.close();
		}
		catch(Exception e){System.out.println("ERROR:"+e.toString());}
	}
	

}
