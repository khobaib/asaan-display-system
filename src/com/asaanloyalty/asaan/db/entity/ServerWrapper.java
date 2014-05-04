package com.asaanloyalty.asaan.db.entity;

import lombok.Getter;
import lombok.Setter;

public class ServerWrapper
{
	
	@Getter
	@Setter
	long oldServerId;
	
	@Getter
	@Setter
	String oldServerPeerId;
	
	@Getter
	@Setter
	long newServerId;
	
	@Getter
	@Setter
	String newServerPeerId;
}
