package com.vobi.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobi.bank.domain.Users;
import com.vobi.bank.dto.UsersDTO;
import com.vobi.bank.mapper.UsersMapper;
import com.vobi.bank.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UsersMapper usersMapper;
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id )throws Exception{
		
		userService.deleteById(id);
		
	}
	
	@PostMapping
	public UsersDTO save(@Valid @RequestBody UsersDTO usersDTO)throws Exception{
		
		Users users=usersMapper.userDTOToUser(usersDTO);
		users=userService.save(users);
		usersDTO=usersMapper.userToUserDTO(users);
		
		return usersDTO;
	}
	
	@PutMapping
	public UsersDTO update(@Valid @RequestBody UsersDTO usersDTO)throws Exception{
		
		Users users=usersMapper.userDTOToUser(usersDTO);
		users=userService.update(users);
		usersDTO=usersMapper.userToUserDTO(users);
		
		return usersDTO;
	}
	
	@GetMapping()
	public List<UsersDTO> findAll()throws Exception{
		List<Users> users = userService.findAll();
		List<UsersDTO> usersDTO = usersMapper.usersToUsersDTO(users);
		return usersDTO;
	}
	
	@GetMapping("/{id}")
	public UsersDTO findById(@PathVariable("id") String id)throws Exception{
		if(userService.findById(id).isPresent()==false) {
			return null;
		}
		
		Users users = userService.findById(id).get();
		UsersDTO usersDTO = usersMapper.userToUserDTO(users);
		return usersDTO;
		
	}

}
