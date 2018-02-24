/**   
* @Title: TestController.java 
* @Package com.mxm.java.learn_demo.controller  
* @author maxm@uubee.com  
* @date 2017年11月10日 下午2:15:10 
* @version V1.0   
*/
package com.mxm.java.learn_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
 * @author maxm@uubee.com
 * @date 2017年11月10日 下午2:15:10 
 */
@Controller
@RequestMapping("demo")
public class DemoController {
	
	@RequestMapping("index")
	public String demo(){
		return "/index";
	}
}
