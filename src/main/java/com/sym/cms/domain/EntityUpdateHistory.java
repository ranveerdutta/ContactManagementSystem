package com.sym.cms.domain;


/**
 * EntityUpdateHistory eni=tity class represents table update log
 * @author ranveer
 *
 */
public class EntityUpdateHistory extends BaseEntity {
	
	private String tableName;
	
	private long rowId;
	
	private String columnName;
	
	private String oldValue;
	
	private String newValue;
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public long getRowId() {
		return rowId;
	}

	public void setRowId(long rowId) {
		this.rowId = rowId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	
}
