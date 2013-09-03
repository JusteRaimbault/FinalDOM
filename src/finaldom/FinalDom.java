/**
 * 
 */
package finaldom;

import org.jsoup.nodes.Document;

/**
 * @author Raimbault Juste <br/> <a href="mailto:juste.raimbault@polytechnique.edu">juste.raimbault@polytechnique.edu</a>
 *
 * Dynamic class wrapping the document.
 * JS executions are done during construction
 *
 */
public class FinalDom {
	
	private Document dom;
	/**
	 * Returns the final dom.
	 * The document is loaded during construction so always existing and finished when called.
	 * 
	 * @return final dom
	 */
	public Document getFinalDom(){return dom;}
	
	private String sourcePath;
	
	

}
