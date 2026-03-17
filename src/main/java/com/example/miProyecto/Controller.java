package com.example.miProyecto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

	@GetMapping("/api/hola")
	public Mono<String> hola(){
		return Mono.just("Hola");
	}
}
