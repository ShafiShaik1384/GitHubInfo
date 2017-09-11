package com.git.client;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.git.model.GitAccount;

@Component
public class CallRestService implements CommandLineRunner{

	
	public static GitAccount[] getAllUsers() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.github.com/users";
		GitAccount[] gitAccount = restTemplate.getForObject(url, GitAccount[].class);
		return gitAccount;
	}
	
	public static GitAccount getUser(String id) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.github.com/users";
		if(null != id) {
			url = url +"/"+ id;
		}
		GitAccount gitAccount = restTemplate.getForObject(url, GitAccount.class);
		return gitAccount; 
	}
	
	public static GitAccount[] getFollowers(String url) {
		RestTemplate restTemplate = new RestTemplate();
		GitAccount[] gitAccount = restTemplate.getForObject(url, GitAccount[].class);
		int arraySize = 5;	//followers count to get for each user
		if(arraySize > gitAccount.length) {	//If followers count is less then use available followers count
			arraySize = gitAccount.length;
		}
		GitAccount[] gitAccountObj = new GitAccount[arraySize];
		//Get new array with required followers count
		for(int i=0; i<gitAccount.length && i<arraySize; i++) {
			gitAccountObj[i] = gitAccount[i];
		}
		return gitAccountObj;
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("CallRestService :: run : ");
	}
}
