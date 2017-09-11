"# GitHubInfo" 
1. Import project as Maven project
	or
	Import as general project and then convert to Maven project.
2. Run the Application.java as Java application
3. Hit "localhost:8078/users/{id}/followers"
		Ex: localhost:8078/users/mojombo/followers

	localhost:8078/users/mojombo/followers -> Using multiple For loops
	localhost:8078/users/mojombo/followersrecur -> Using recursive function
	
	To change the follower count - change level variable value inside GitHubServiceImpl.java
	To change the level - change the arraySize variable value inside CallRestService.java (getFollowers function) 