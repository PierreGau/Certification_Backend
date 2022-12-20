package com.kwiky.backend.mappers;

import com.kwiky.backend.dto.CanalDTO;
import com.kwiky.backend.model.Canal;

public class CanalToDto 
{
	public static CanalDTO mapToDTO(Canal canal)
	{
		CanalDTO toReturn = new CanalDTO();
		toReturn.setName(canal.getName());
		toReturn.setUserID(canal.getUser().getId());
		toReturn.setId(canal.getId());
		return toReturn;
	}
}
