package com.git.service;

import java.util.List;

import com.git.model.GitAccount;

public interface GitHubService {
	public GitAccount[] getFollowers(String id);
	public GitAccount[] getFollowersRecursive(String id);
}
