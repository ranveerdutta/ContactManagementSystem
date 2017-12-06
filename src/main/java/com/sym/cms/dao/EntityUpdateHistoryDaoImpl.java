package com.sym.cms.dao;

import java.lang.reflect.Field;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.sym.cms.domain.BaseEntity;
import com.sym.cms.domain.EntityUpdateHistory;

/**
 * Implementation class for EntityUpdateHistory entity Dao
 * @author ranveer
 *
 */
@Component
public class EntityUpdateHistoryDaoImpl extends BaseInMemoryDao<EntityUpdateHistory> implements EntityUpdateHistoryDao{

	@Override
	public void logTableUpdates(String[] fields, BaseEntity newEntity, BaseEntity oldEntity) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		String tableName = newEntity.getClass().getSimpleName();
		for(String fieldName : fields) {
			Field field = newEntity.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			if(!field.get(newEntity).equals(field.get(oldEntity))) {
				EntityUpdateHistory history = new EntityUpdateHistory();
				history.setTableName(tableName);
				history.setColumnName(fieldName);
				history.setRowId(oldEntity.getId());
				history.setOldValue(field.get(oldEntity).toString());
				history.setNewValue(field.get(newEntity).toString());
				super.insert(history);
			}
		}
		
	}
	
	@Override
	public Set<EntityUpdateHistory> getAllLog(){
		return super.getAll();
	}
	
}
