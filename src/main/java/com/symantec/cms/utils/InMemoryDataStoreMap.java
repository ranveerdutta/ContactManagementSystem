package com.symantec.cms.utils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.symantec.cms.domain.BaseEntity;

/**
 * Collection to hold unique elements in thread safe way.
 * @author Ranveer
 *
 * @param <K>
 */
public class InMemoryDataStoreMap<K>{
	
	private AtomicLong primaryId = new AtomicLong(0);
	
	private Map<K, K> map;
	
	public InMemoryDataStoreMap() {
		map = new ConcurrentHashMap<>();
	}
	
	/**
	 * Inserts  the element k into the Set if not present and returns null
	 * Replaces the element k in the Set if already present and returns the previous version of the k
	 * @param 
	 * @return K
	 */
	public K insertOrReplace(K k) {
		if(null == map.get(k) || null == ((BaseEntity)k).getId()) {
			Long id = primaryId.addAndGet(1);
			((BaseEntity)k).setId(id);
		}else if(((BaseEntity)map.get(k)).getId() != null) {
			((BaseEntity)k).setId(((BaseEntity)map.get(k)).getId());
		}
		return map.put(k,k);
	}
	
	/**
	 * Returns all the elements from the Set
	 * @return Set<K>
	 */
	public Set<K> getAll(){
		return map.keySet();
	}
	
	/**
	 * Removes all the elements from the Set
	 */
	public void cleanAll() {
		map.clear();
	}

	public K get(K k) {
		return map.get(k);
	}
	
	public void remove(K k) {
		map.remove(k);
	}
	
}
