/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmjxb2documents;

/**
 *
 * @author wjohn_000
 */
public class Document {
    private String title;
    private String author;
    private String body;
    private int version;
    
    public Document(String title, String author){
        this.title=title;
        this.author=author;
        version=0;
    }
    
    public Document(String title, String author, String body){
        this.title=title;
        this.author=author;
        this.body=body;
        version=1;
    }
    /*Setters*/
    public void setTitle(String title){
        this.title=title;
        version++;
    }
    public void setAuthor(String author){
        this.author=author;
    }
    public void setBody(String body){
        this.body=body;
        version++;
    }
    /*Getters*/
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getBody(){
        return body;
    }
    public int getVersion(){
        return version;
    }
    
}
