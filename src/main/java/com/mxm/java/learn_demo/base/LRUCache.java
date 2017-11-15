/**   
* @Title: LRUCache.java 
* @Package com.mxm.java.learn_demo.base  
* @author maxm@uubee.com  
* @date 2017年11月15日 上午9:17:34 
* @version V1.0   
*/
package com.mxm.java.learn_demo.base;

import java.util.LinkedHashMap;
import java.util.Map;

/**  
 * @author maxm@uubee.com
 * @date 2017年11月15日 上午9:17:34 
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V>{
	
	/**内部缓存大小*/
	public final static Integer cacheSize = 1000;
	
	public LRUCache() {
		super(cacheSize, 0.75f, true);
	}
	
	@Override
	public boolean removeEldestEntry(Map.Entry<K,V> eldest) {
		if (size() > cacheSize) {
			return true;
		}
        return false;
    }
}
