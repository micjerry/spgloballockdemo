package com.sculler.globallock.controller;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalLockController {
	private static final String LOCK_SUCCESS = "{result:\"ok\"}";
	
	private static final String LOCK_FAILED = "{result:\"fail\"}";
	
	@Autowired
	private RedisLockRegistry redisLockRegistry;
	
	@RequestMapping(value = "/globallock/test", method = RequestMethod.GET)
	public String globalLock() {
		Lock lock = redisLockRegistry.obtain("lock");
		try {
			if (lock.tryLock(1, TimeUnit.SECONDS)) {
				System.out.println("lock 1 success");
				TimeUnit.SECONDS.sleep(10);
				lock.unlock();
				return LOCK_SUCCESS;
			} else {
				System.out.println("lock 1 failed");
				return LOCK_FAILED;
			}
		} catch (Exception except) {
			return LOCK_FAILED;
		} 
		
	}
}
