package com.vobi.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vobi.bank.domain.Users;
import com.vobi.bank.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Validator validator;
	
	@Override
	@Transactional(readOnly = true)
	public List<Users> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Users> findById(String id) {
		return userRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return userRepository.count();
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Users save(Users users) throws Exception {
		if(users==null){
			throw new Exception("El usuario es nulo"); 
		}
		
		validate(users);
		
		if(userRepository.existsById(users.getUserEmail())) {
			throw new Exception("El usuario ya existe");
		}
			
		return userRepository.save(users);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Users update(Users users) throws Exception {
		if(users==null){
			throw new Exception("El usuario es nulo"); 
		}
		
		validate(users);
		
		if(userRepository.existsById(users.getUserEmail())==false) {
			throw new Exception("El usuario no existe");
		}
			
		return userRepository.save(users);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(Users users) throws Exception {
		if(users==null) {
			throw new Exception("El users es nulo");
		}
		
		if(users.getUserEmail()==null) {
			throw new Exception("El users email es nulo");
		}
		
		if(userRepository.existsById(users.getUserEmail())==false) {
			throw new Exception("El users no existe");
		}
		
		findById(users.getUserEmail()).ifPresent(entity->{
			if(users.getTransactions()!=null && users.getTransactions().isEmpty()==false) {
				throw new RuntimeException("El user tiene transacciones");
			}
		});
		
		userRepository.deleteById(users.getUserEmail());
		
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		if(id==null) {
			throw new Exception("El id es nulo");
		}
		
		if(userRepository.existsById(id)==false) {
			throw new Exception("El user no existe");
		}
		
		delete(userRepository.getById(id));	
		
	}

	@Override
	public void validate(Users entity) throws Exception {
		Set<ConstraintViolation<Users>> constraintViolations=validator.validate(entity);
		if(constraintViolations.isEmpty()==false) {
			throw new ConstraintViolationException(constraintViolations);
		}	
		
	}

	

}
