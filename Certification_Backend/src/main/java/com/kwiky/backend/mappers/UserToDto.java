package com.kwiky.backend.mappers;

import com.kwiky.backend.dto.UserDTO;
import com.kwiky.backend.model.User;

public class UserToDto 
{
	public static UserDTO mapToDTO(User user)
	{
		UserDTO toReturn = new UserDTO();
		toReturn.setId(user.getId());
		toReturn.setActif(user.isActif());
		toReturn.setName(user.getName());
		return toReturn;
	}
}
