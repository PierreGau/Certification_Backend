package com.kwiky.backend.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kwiky.backend.dto.CanalDTO;
import com.kwiky.backend.dto.ServerDTO;
import com.kwiky.backend.dto.UserDTO;
import com.kwiky.backend.model.Canal;
import com.kwiky.backend.model.Server;
import com.kwiky.backend.model.User;

public class serverToDto
{
	public static ServerDTO mapToDTO(Server server)
	{
		ServerDTO toReturn = new ServerDTO();
		Set<CanalDTO> cannaux = new HashSet<CanalDTO>();
		
		for(Canal c : server.getCanaux())
		{
			cannaux.add(CanalToDto.mapToDTO(c));
		}
		toReturn.setCanaux(cannaux);
		toReturn.setName(server.getName());
		toReturn.setCreator(UserToDto.mapToDTO(server.getCreator()));
		toReturn.setId(server.getId());
		
		Set<UserDTO> users = new HashSet<UserDTO>();	
		for(User u : server.getUsers())
		{
			users.add(UserToDto.mapToDTO(u));
		}
		toReturn.setUsers(users);
		
		return toReturn;
	}
	
	public static List<ServerDTO> mapToDTOs(List<Server> servers)
	{
		List<ServerDTO> toReturn = new ArrayList<ServerDTO>();
		
		for(Server server : servers)
		{
			ServerDTO item = new ServerDTO();
			item.setName(server.getName());
			Set<CanalDTO> cannaux = new HashSet<CanalDTO>();
			
			for(Canal c : server.getCanaux())
			{
				cannaux.add(CanalToDto.mapToDTO(c));
			}
			item.setCanaux(cannaux);
			item.setCreator(UserToDto.mapToDTO(server.getCreator()) );
			item.setId(server.getId());
			
			Set<UserDTO> users = new HashSet<UserDTO>();	
			for(User u : server.getUsers())
			{
				users.add(UserToDto.mapToDTO(u));
			}
			item.setUsers(users);
			
			toReturn.add(item);
		}
		
		
		return toReturn;
	}
}
