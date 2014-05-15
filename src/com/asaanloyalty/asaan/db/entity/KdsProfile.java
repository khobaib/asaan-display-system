package com.asaanloyalty.asaan.db.entity;

import lombok.Getter;
import lombok.Setter;

public class KdsProfile
{
	@Getter
	@Setter
	public long id;

	@Getter
	@Setter
	public String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 
	
	
}
