package com.mxm.java.learn_demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{   
	private static ThreadLocal<String> local = new ThreadLocal<String>();
    public static void main( String[] args ) throws Exception {
    	Map<String, String> map = new HashMap<>();
    	map.keySet();
    	map.values();

    	/*map.size();
    	local.set("123");
    	local.get();
        System.out.println( "Hello World!" );
        SynchronousQueue<String> queue = new SynchronousQueue<String>();
       queue.put("456");*/
   /*     String cityId = ",1,2,3,4,6,";
        String[] result = cityId.split(",");
        result = StringUtils.split(cityId,",");
        System.out.println(result.length);*/

      /*  int num = (int) (Math.random() * (1 + 1));
        System.out.println(num);
        String[] str = "hello".split("");
        System.out.println(str[0]);*/
        Long test = 0123L;
        System.out.println(test);
    }
}
