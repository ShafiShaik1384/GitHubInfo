package com.git.service.impl;

import org.springframework.stereotype.Component;

import com.git.client.CallRestService;
import com.git.model.GitAccount;
import com.git.service.GitHubService;

@Component
public class GitHubServiceImpl implements GitHubService {
	static int level = 2;	//used to mention dynamic level

	@Override
	public GitAccount[] getFollowers(String id) {
		// TODO Auto-generated method stub
		String url = "https://api.github.com/users";
		if(null != id) {	//if id is null then use above url to return all available users
			url = url +"/"+ id+"/followers";
		}
		GitAccount[] gitAccount = CallRestService.getFollowers(url);
		
		for(int index1=0; index1<gitAccount.length; index1++) {
			if(null != gitAccount[index1]) {
				gitAccount[index1].setFollowers(CallRestService.getFollowers(gitAccount[index1].getFollowers_url()));
				for(int index2=0; index2<gitAccount[index1].getFollowers().length; index2++) {
					if(null != gitAccount[index1].getFollowers()[index2]) {
						gitAccount[index1].getFollowers()[index2].setFollowers(CallRestService.getFollowers(gitAccount[index1].getFollowers()[index2].getFollowers_url()));
						for(int index3=0; index3<gitAccount[index1].getFollowers()[index2].getFollowers().length; index3++) {
							if(null != gitAccount[index1].getFollowers()[index2].getFollowers()[index3]) {
								gitAccount[index1].getFollowers()[index2].getFollowers()[index3].setFollowers(CallRestService.getFollowers(gitAccount[index1].getFollowers()[index2].getFollowers()[index3].getFollowers_url()));
							}
						}
					}
				}
			}
		}
		return gitAccount;
	}

	@Override
	public GitAccount[] getFollowersRecursive(String id) {
		// TODO Auto-generated method stub
		String url = "https://api.github.com/users";
		if(null != id) {
			url = url +"/"+ id+"/followers";
		}
		return getFollowersRecu(url, 1);
	}
	
	public GitAccount[] getFollowersRecu(String url, int dept) {
		// TODO Auto-generated method stub
		GitAccount[] gitAccount = CallRestService.getFollowers(url);
		//System.out.println("GitHubServiceImpl..getFollowersRecu...url===:"+url+"  dept:"+dept+" length:"+gitAccount.length);
		for(int i=0; i<gitAccount.length;i++) {
			//System.out.println("GitHubServiceImpl..getFollowersRecu...url:"+url+"  dept:"+dept+" i:"+i);
			if(null != gitAccount[i] && dept <= level) {
				//System.out.println("calling getFollowersRecu....");
				gitAccount[i].setFollowers(getFollowersRecu(gitAccount[i].getFollowers_url(),++dept));
			}
		}
		return gitAccount;
	}

}
