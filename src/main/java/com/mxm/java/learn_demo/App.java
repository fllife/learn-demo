package com.mxm.java.learn_demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;


/**
 * Hello world!
 *
 */
public class App 
{   
	private static ThreadLocal<String> local = new ThreadLocal<String>();
    public static void main( String[] args ) throws Exception
    {   
    	Map<String, String> map = new ConcurrentHashMap<String, String>();
    	map.size();
    	local.set("123");
    	local.get();
        System.out.println( "Hello World!" );
        SynchronousQueue<String> queue = new SynchronousQueue<String>();
       queue.put("456");
    }
}
