package com.sym.cms.dao;

import java.util.Set;

import com.sym.cms.domain.BaseEntity;
import com.sym.cms.domain.EntityUpdateHistory;

/**
 * Interface representing all the methods for the EntityUpdateHistory entity Dao
 * @author ranveer
 *
 */
public interface EntityUpdateHistoryDao {
	
	void logTableUpdates(String[] fields, BaseEntity newEntity, BaseEntity oldEntity) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException;

	Set<EntityUpdateHistory> getAllLog();

}
