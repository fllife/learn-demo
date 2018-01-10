/**   
* @Title: ListScriptEngines.java 
* @Package com.mxm.java.learn_demo.script  
* @author maxm@uubee.com  
* @date 2018年1月6日 上午9:58:04 
* @version V1.0   
*/
package com.mxm.java.learn_demo.script;

import java.util.List;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author maxm@uubee.com
 * @date 2018年1月6日 上午9:58:04
 */
public class ListScriptEngines {
	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		// 得到所有的脚本引擎工厂

		List<ScriptEngineFactory> factories = manager.getEngineFactories();
	    // 这是Java SE 5 和Java SE 6的新For语句语法

		for (ScriptEngineFactory factory: factories){
		 // 打印脚本信息
	
		System.out.printf("Name: %s%n" +
		"Version: %s%n" +
		"Language name: %s%n" +
		"Language version: %s%n" +
		"Extensions: %s%n" +
		"Mime types: %s%n" +
		"Names: %s%n",
		factory.getEngineName(),
		factory.getEngineVersion(),
		factory.getLanguageName(),
		factory.getLanguageVersion(),
		factory.getExtensions(),
		factory.getMimeTypes(),
		factory.getNames());
		// 得到当前的脚本引擎

	   }
	  ScriptEngine engine = manager.getEngineByName("javascript");
	  engine.put("name", "abcdefg");
	  // 开始执行脚本
	  Compilable compEngine = (Compilable)engine;
	  CompiledScript script = compEngine.compile("var output ='' ;" +
	   "for (i = 0; i <= name.length; i++) {" +
	   " output = name.charAt(i) + output" +
	   "}");
	  // 得到output变量的值

      String name = (String)script.eval();
	  System.out.printf("被翻转后的字符串：%s", name);
	}
}
