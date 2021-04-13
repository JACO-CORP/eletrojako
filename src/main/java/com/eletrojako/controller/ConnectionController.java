package com.eletrojako.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("status")
public class ConnectionController {

	@GetMapping
	public String index() {
		return "A conexão está funcional";
	}
}
