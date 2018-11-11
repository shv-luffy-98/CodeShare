package com.example.shv.codeshareluffy;

public class Project {
    String projectName, projectPassword;
    public Project(){
    }
    public Project(String  projectName, String projectPassword){
        this.projectName = projectName ;
        this.projectPassword = projectPassword;
    }
    public String getprojectName(){return projectName;}
    public String getprojectPassword() { return projectPassword; }
}
