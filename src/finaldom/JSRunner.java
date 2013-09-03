/**
 * 
 */
package finaldom;

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
	
	
	
	public static String runCommand(String command){
		
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();
            Object result = cx.evaluateString(scope, command, "log/text.log", 1, null);
            return Context.toString(result);
        } finally {
            Context.exit();
        }
    }
	

}
