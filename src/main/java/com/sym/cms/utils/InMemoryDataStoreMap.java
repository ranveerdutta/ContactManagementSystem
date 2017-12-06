package com.sym.cms.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.sym.cms.domain.BaseEntity;

/**
 * Collection to hold unique elements in thread safe way.
 * @author Ranveer
 *
 * @param <K>
 */
public class InMemoryDataStoreMap<K extends BaseEntity>{
	
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
	public K insert(K k) {
		Long id = primaryId.addAndGet(1);
		k.setId(id);
		k.setCreatedDate(new Date());
		k.setLastUpdatedDate(new Date());
		map.put(k,k);
		return k;
	}
	
	public K update(K k, K existingEntity) {
		k.setId(existingEntity.getId());
		k.setCreatedDate(existingEntity.getCreatedDate());
		k.setLastUpdatedDate(new Date());
		map.remove(existingEntity);
		map.put(k,k);
		return k;
	}
	
	/**
	 * Returns all the elements from the Set
	 * @return Set<K>
	 */
	public Set<K> getAll(){
		Set<K> allEnitites = map.keySet();
		Set<K> activeEntities = new HashSet<>();
		for(K entity : allEnitites) {
			if(!entity.getIsDeleted()) {
				activeEntities.add(entity);
			}
		}
		
		return activeEntities;
			
	}
	
	/**
	 * Removes all the elements from the Set
	 */
	public void cleanAll() {
		map.clear();
	}

	public K get(K k) {
		K entity = map.get(k);
		if(entity != null && !entity.getIsDeleted()) {
			return entity;
		}
		return null;
	}
	
	public void remove(K k) {
		K exitingEntity = map.get(k);
		if(null == exitingEntity) {
			return;
		}
		exitingEntity.setIsDeleted(Boolean.TRUE);
		exitingEntity.setLastUpdatedDate(new Date());
	}
	
}
