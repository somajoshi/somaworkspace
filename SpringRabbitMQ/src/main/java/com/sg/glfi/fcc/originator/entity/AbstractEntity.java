package com.sg.glfi.fcc.originator.entity;

public abstract class AbstractEntity implements Entity {
	private static final long serialVersionUID = 8416359261165635435L;
	
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
