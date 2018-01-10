/**   
* @Title: GroovyScript.java 
* @Package com.mxm.java.learn_demo.script  
* @author maxm@uubee.com  
* @date 2018年1月6日 上午11:17:33 
* @version V1.0   
*/
package com.mxm.java.learn_demo.script;

import java.util.Date;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**  
 * @author maxm@uubee.com
 * @date 2018年1月6日 上午11:17:33 
 */
public class GroovyScript {
	
	public static void main(String[] args) throws ScriptException, NoSuchMethodException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("groovy");
		Compilable compilable = (Compilable) engine;
		Bindings binding = engine.createBindings();  
		binding.put("date", new Date());  
		    //如果script文本来自文件,请首先获取文件内容  
		CompiledScript script = compilable.compile("def getTime(){return date.getTime();}");
	    Object object = script.eval();
	    System.out.println(object);  
	}
}
