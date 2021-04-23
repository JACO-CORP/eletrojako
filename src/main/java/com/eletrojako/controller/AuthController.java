package com.eletrojako.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eletrojako.models.Acesso;
import com.eletrojako.models.Usuario;
import com.eletrojako.payload.request.LoginRequest;
import com.eletrojako.payload.request.SignupRequest;
import com.eletrojako.payload.response.JwtResponse;
import com.eletrojako.payload.response.MessageResponse;
import com.eletrojako.repository.AcessoRepository;
import com.eletrojako.repository.UsuarioRepository;
import com.eletrojako.security.jwt.JwtUtils;
import com.eletrojako.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	AcessoRepository acessoRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(),
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Usuario usuario =  new Usuario(signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getSenha()));

		Set<String> strAcessos = signUpRequest.getAcessos();
		Set<Acesso> acessos = new HashSet<>();

		if (strAcessos == null) {
			Acesso acessoUsuario = acessoRepository
					.findByAcesso(Acesso.TipoDeAcesso.USUARIO)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			acessos.add(acessoUsuario);
		} else {
			strAcessos.forEach(role -> {
				switch (role) {
					case "ADMIN":
					Acesso acessoAdmin = acessoRepository.findByAcesso(Acesso.TipoDeAcesso.ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					acessos.add(acessoAdmin);

					break;
				default:
					Acesso acessoUsuario = acessoRepository.findByAcesso(Acesso.TipoDeAcesso.USUARIO)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			acessos.add(acessoUsuario);
				}
			});
		}

		usuario.setAcesso(acessos);
		usuarioRepository.save(usuario);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}