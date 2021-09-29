package hu.econsult.is4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CacheService {

	private final CacheManager cacheManager;

	@Autowired
	public CacheService(CacheManager cacheManager) {
		super();
		this.cacheManager = cacheManager;
	}

	public void evictSingleCacheValue(String cacheName, String cacheKey) {
	    cacheManager.getCache(cacheName).evict(cacheKey);
	}

	public void evictAllCacheValues(String cacheName) {
	    cacheManager.getCache(cacheName).clear();
	}
	
	
	@Scheduled(cron = "0 0 0 * * *")
	public void evictAllCaches() {
		log.info("Clear all caches started...");
	    cacheManager.getCacheNames().stream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	    log.info("Clear all caches finished...");
	}
}
