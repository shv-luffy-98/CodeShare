package com.example.shv.codeshareluffy;

public class UserProject {
    String email, projectKey;
    public UserProject(){

    }
    public UserProject(String email , String projectKey){
        this.email = email;
        this.projectKey = projectKey;
    }
    public String email() {return email;}
    public String projectKey() {return projectKey;}
}
