package main;
import java.io.*;  
import java.net.*;
public class EmailChallenge {

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Email");
		try {
		String email=reader.readLine();
		reader.close();
		String name = email.split("@")[0];
		name = name.replace(" ", "");
		URL website = new URL ("https://www.ecs.soton.ac.uk/people/" + name);
		BufferedReader data = new BufferedReader(new InputStreamReader(website.openStream()));
		
		String line;
        while ((line = data.readLine()) != null)
            if (line.contains("property=\"name\"")) {
            	int location = line.indexOf("property=\"name\"");
            	line = line.substring(location + 16);
            	int location2 = line.indexOf("<");
            	line = line.substring(0,location2);
            	System.out.println(line);
            	name = line;
            }
        data.close();
    	System.out.println("Anagrams:");
        
        website = new URL ("https://new.wordsmith.org/anagram/anagram.cgi?anagram=" + name.replace(" ", "+") + "&t=10&a=n");
		data = new BufferedReader(new InputStreamReader(website.openStream()));
		int number = 0;
		while ((line = data.readLine()) != null)
            if (line.contains("<script>document.body.style.cursor='wait';</script><b>")) {
            	String strNumber = (line.substring(54));
            	strNumber = (strNumber.substring(0,strNumber.indexOf(" ")));
            	number = Integer.parseInt(strNumber);
            } else if (number > 0) {
            	number = number - 1;
            	if (line.contains("<script>document.body.style.cursor='default';</script></div>")) {
                	number = 0;
                }else {
                	try {
                		System.out.println(line.replace("<br>", "").replace("</b>", ""));
                	}catch(Exception e){
     			   		e.printStackTrace();
                	}
                }
            }
        data.close();
        
        
        
        
        
        
		
		}catch(IOException ioe){
			   ioe.printStackTrace();
		}
		
	}

}
