package com.gigaspaces.settlement.model;

public class Entity {

	private String entityId;
	private String entityName;
	private String entityType;
	private boolean active = true;

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getEntityId() {
		return entityId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
