/*
Will Johnke
CS3330
September 7th, 2016
Documents Java Class Homework
*/


package wmjxb2documents;

public class Wmjxb2Documents {

    
    public static void main(String[] args) {
        Document document1=new Document("Another Life", "Sally Smith");
        document1.setBody("The grass is always greener on the other side.");
        
        Document document2=new Document("Final Word", "Karen Jones", 
                "We should plan for the worst and hope for the best.");
        
        document2.setTitle("Final Words");
        
        System.out.printf("document1:\ntitle: %s\nauthor: %s\nbody: %s\nversion:%d\n\n",
                document1.getTitle(), document1.getAuthor(),
                document1.getBody(), document1.getVersion());
        
        System.out.printf("document2:\ntitle: %s\nauthor: %s\nbody: %s\nversion:%d",
                document2.getTitle(), document2.getAuthor(),
                document2.getBody(), document2.getVersion());
    }
    
}
