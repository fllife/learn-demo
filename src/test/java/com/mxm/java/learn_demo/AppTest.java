/**   
* @Title: AppTest.java 
* @Package com.mxm.java.learn_demo  
* @author maxm@uubee.com  
* @date 2017年10月14日 下午12:52:15 
* @version V1.0   
*/
package com.mxm.java.learn_demo;

import org.junit.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**  
 * @author maxm@uubee.com
 * @date 2017年10月14日 下午12:52:15 
 */
public class AppTest extends BaseTest {
   @Test
   public void mainTest() {
	  
   }
   public static void main(String[] args) {
	   XmlWebApplicationContext ctx = new XmlWebApplicationContext();
	   ctx.setConfigLocations(new String[] {"applicationContext.xml"});
	   ctx.setServletContext(new MockServletContext());
	   ctx.refresh(); 
    }
}
