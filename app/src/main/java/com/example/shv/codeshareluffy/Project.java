package com.example.shv.codeshareluffy;

public class Project {
    String projectName, projectPassword, code;
    public Project(){
    }
    public Project(String  projectName, String projectPassword){
        this.projectName = projectName ;
        this.projectPassword = projectPassword;
        code = "Enter Code";
    }
    public String getprojectName(){return projectName;}
    public String getprojectPassword() { return projectPassword; }
    public String getcode() { return code; }
}
