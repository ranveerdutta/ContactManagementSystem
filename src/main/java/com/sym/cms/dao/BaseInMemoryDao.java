package com.sym.cms.dao;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.sym.cms.annotation.TableUpdateLogEnable;
import com.sym.cms.domain.BaseEntity;
import com.sym.cms.utils.InMemoryDataStoreMap;

/**
 * Abstract class having DB operations for in-memory database
 * @author ranveer
 *
 * @param <T>
 */
public abstract class BaseInMemoryDao<T extends BaseEntity> implements IBaseDao<T>{
	
	@Autowired
	private EntityUpdateHistoryDao logDao;
	
	private InMemoryDataStoreMap<T> inMemoryObjects;

	{
		if(null == inMemoryObjects) {
			inMemoryObjects = new InMemoryDataStoreMap<>();
		}
	}
	
	@Override
	public T insert(T t) {
		return inMemoryObjects.insert(t);
		
	}
	
	@Override
	public T update(T t, T exisitngEntity) {
		try {
			//if update log is enabled through annotations then log it
			Annotation[] annotations = exisitngEntity.getClass().getAnnotations();
			if(annotations != null) {
				for(Annotation annotation : annotations){
				    if(annotation instanceof TableUpdateLogEnable){
				    	TableUpdateLogEnable myAnnotation = (TableUpdateLogEnable) annotation;
				        if("true".equals(myAnnotation.enable())) {
				        	String[] fields = myAnnotation.fields().split(",");
				        	logDao.logTableUpdates(fields, t, exisitngEntity);
				        }
				    }
				}
			}
		}catch(Exception ex) {
			System.out.println("exception while logging the table update in the history table");
			ex.printStackTrace();
		}
		
		return inMemoryObjects.update(t, exisitngEntity);
		
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
