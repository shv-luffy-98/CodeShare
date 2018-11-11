package com.example.shv.codeshareluffy;

public class UserProject {
    public String email, projectKey;
    public UserProject(){

    }
    public UserProject(String email , String projectKey){
        this.email = email;
        this.projectKey = projectKey;
    }
    public String getemail() {return email;}
    public String getprojectKey() {return projectKey;}
}
