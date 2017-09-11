package com.git.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.git.client.CallRestService;
import com.git.model.GitAccount;
import com.git.service.GitHubService;

@RestController
public class GitHubController {
	
	@Autowired
	private GitHubService service;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public GitAccount[] getAllUsers() {
		System.out.println("GitHubController..getAllUsers.");
		return CallRestService.getAllUsers();
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public GitAccount getUsers(@PathVariable("id") String id) {
		System.out.println("GitHubController..getUsers.");
		return CallRestService.getUser(id);
	}
	
	@RequestMapping(value = "/users/{id}/followers", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public GitAccount[] getFollowersOfUser(@PathVariable("id") String id) {
		System.out.println("GitHubController..getFollowersOfUser.");
		return service.getFollowers(id);
	}
	
	@RequestMapping(value = "/users/{id}/followersrecur", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public GitAccount[] getFollowersOfUserRecr(@PathVariable("id") String id) {
		return service.getFollowersRecursive(id);
	}
}
