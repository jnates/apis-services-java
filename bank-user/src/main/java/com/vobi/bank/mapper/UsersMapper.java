package com.vobi.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vobi.bank.domain.Users;
import com.vobi.bank.dto.UsersDTO;

@Mapper
public interface UsersMapper {

	@Mapping(source = "userType.ustyId", target = "ustyId")
	public UsersDTO userToUserDTO(Users users);
	
	@Mapping(target = "userType.ustyId", source = "ustyId")
	public Users userDTOToUser(UsersDTO users);
	
	public List<UsersDTO> usersToUsersDTO(List<Users> users);
	
	public List<Users> usersDTOToUsers(List<UsersDTO> users);
}
