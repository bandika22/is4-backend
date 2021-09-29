package hu.econsult.is4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.econsult.is4.service.CacheService;

@RestController
@RequestMapping("/rest/is4/cache")
public class CacheController {

	private final CacheService cacheService;

	@Autowired
	public CacheController(CacheService cacheService) {
		super();
		this.cacheService = cacheService;
	}
	
	
	@GetMapping("/clearall")
	public void clearAllCaches() {
		cacheService.evictAllCaches();
	}
	
	
	
}
