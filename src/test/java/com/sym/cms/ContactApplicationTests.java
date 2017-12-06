package com.sym.cms;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sym.cms.rest.service.ContactRestService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactApplicationTests {
	
	@Autowired
	private ContactRestService contactRestService;

	//test case to verify the application context is initialized properly
	@Test
	public void contextLoads() {
		
		Assert.assertNotNull(contactRestService);
	}

}
