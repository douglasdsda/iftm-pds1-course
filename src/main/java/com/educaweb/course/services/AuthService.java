package com.educaweb.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educaweb.course.dto.CredentialsDTO;
import com.educaweb.course.dto.TokenDTO;
import com.educaweb.course.entities.User;
import com.educaweb.course.repositories.UserRepository;
import com.educaweb.course.security.JWTUtil;
import com.educaweb.course.services.exceptions.JWTAuthenticationException;
import com.educaweb.course.services.exceptions.JWTAuthorizationException;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private UserRepository repository;

	@Transactional(readOnly = true)
	public TokenDTO authenticate(CredentialsDTO dto) {

		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(),
					dto.getPassword());
			authenticationManager.authenticate(authToken);

			String token = jwtUtil.generateToken(dto.getEmail());

			return new TokenDTO(dto.getEmail(), token);

		} catch (AuthenticationException e) {
			throw new JWTAuthenticationException("Bad credentials");
		}

	}

	public User authenticated() {

		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			return repository.findByEmail(userDetails.getUsername());

		} catch (Exception e) {
			throw new JWTAuthorizationException("Acess denied");
		}

	}

	public void validateSelfOrAdmin(Long userId) {
		User user = authenticated();
		if (user == null || (!user.getId().equals(userId)) && !user.hasRole("ROLE_ADMIN")) {
			throw new JWTAuthorizationException("Acess denied");
		}
	}

}
