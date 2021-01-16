package ir.com.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ir.com.application.service.UserAppService;
import ir.com.domain.dto.UserAppDto;
import ir.com.domain.dto.UserRoleDto;
import ir.com.domain.enums.Permission;

@RestController
public class RegisterUser {

	@Autowired
	private UserAppService userAppService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public UserAppDto register() {

		UserAppDto userApp = new UserAppDto();
		
		try {
			
			userApp.setUsername("mamad@test.com");
			userApp.setPassword("123");
			userApp.setAccountNonExpired(true);
			userApp.setAccountNonLocked(true);
			userApp.setCredentialsNonExpired(true);
			userApp.setEnabled(true);
			
			List<Permission> permissions = new ArrayList<>();
			permissions.add(Permission.GET_USER);
			permissions.add(Permission.SEE_USERS);
			
			UserRoleDto roleDto = new UserRoleDto();
			roleDto.setPermissions(permissions);
			
			List<UserRoleDto> userRoles = new ArrayList<>();
			roleDto.setName("admin");
			roleDto.setPermissions(permissions);
			
			userRoles.add(roleDto);
			
			userApp.setUserRoles(userRoles);
			
			userApp = userAppService.create(userApp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userApp;
	}

}
