package com.symantec.cms.dao;

import java.util.Set;

import com.symantec.cms.domain.BaseEntity;
import com.symantec.cms.utils.InMemoryDataStoreMap;

/**
 * Abstract class having DB operations for in-memory database
 * @author ranveer
 *
 * @param <T>
 */
public abstract class BaseInMemoryDao<T extends BaseEntity> implements IBaseDao<T>{
	
	private InMemoryDataStoreMap<T> inMemoryObjects;

	{
		if(null == inMemoryObjects) {
			inMemoryObjects = new InMemoryDataStoreMap<>();
		}
	}
	
	@Override
	public T insertOrReplace(T t) {
		return inMemoryObjects.insertOrReplace(t);
		
	}
	
	@Override
	public Set<T> getAll() {
		return inMemoryObjects.getAll();
	}
	
	@Override
	public T get(T t) {
		return inMemoryObjects.get(t);
	}
	
	@Override
	public void cleanAll() {
		inMemoryObjects.cleanAll();
	}
	
	public void remove(T t) {
		inMemoryObjects.remove(t);
	}
	
	
}
