package com.ecommerce.UserService;

import com.ecommerce.UserService.Repository.RoleRepository;
import com.ecommerce.UserService.Repository.UserRepository;
import com.ecommerce.UserService.model.Role;
import com.ecommerce.UserService.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {
	private RoleRepository roleRepository;
	private UserRepository userRepository;

	public UserServiceApplication(RoleRepository roleRepository,
								  UserRepository userRepository) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Role r = new Role();
//		r.setRoleName("student");
//		Role stu = roleRepository.save(r);

//		List<Role> roles = roleRepository.findAll();
//		User user = new User();
//		user.setEmail("a@b.com");
//		user.setPassword("1234");
//		Set<Role> roleSet = new HashSet<>();
//		roleSet.add(roles.iterator().next());
//		user.setRoles(roleSet);
//		userRepository.save(user);
	}
}
