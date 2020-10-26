import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Command{
	
	public Command(FileInputStream fileinputstream, DequeUsingStacks<String> deque){
		
		String[] array = new String[50]; // Dequeue can take 50 elements 
		
		int count = 0;
		
		String nl = System.getProperty("line.separator");  //create new line
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(fileinputstream));
		
        StringBuilder sb = new StringBuilder();
        
        String line;
        
        try{
        	
			while ((line = reader.readLine()) != null){
				
			    sb.append(line + nl);
			    
			    array[count] = line;
			    
			    count++;
			}
			
		}
        
        catch (IOException e1){
			
			e1.printStackTrace();
			
		}        
        
        try{
        	
			reader.close();
			
		} 
        
        catch (IOException e) {
        	
			e.printStackTrace();
		}
  
        int i = 0;
        
        while(array[i]!=null){
        	
        	
        	if(array[i].contains("size")){
        		
        		System.out.println("The deque contains " + Integer.toString(deque.size()) + " string(s).");
        	}
        	
        	if(array[i].equals("is_empty")){
        		
        		if(deque.isEmpty()){
        			
        			System.out.println("The deque is empty.");
        			
        		}
        		
        		else System.out.println("The deque is not empty.");
        	}
        	
        	if(array[i].equals("first")){
        		
        		if(deque.size()==0){
        			
        			System.out.println("The deque was empty.");
        		}
        		
        		else{
        			
        			System.out.println("The string at the front of the deque is " + deque.front.peek() + ".");
        		}
        	}
        	
        	if(array[i].equals("last")){
        		
        		if(deque.size()==0){
        			
        			System.out.println("The deque was empty.");
        		}
        		
        		else{
        			
        			System.out.println("The string at the back of the deque is " + deque.back.peek() + ".");
        		}
        	}
        	
        	if(array[i].contains("add_first")){
        		
        		String[] words=array[i].split("\\s");
        		
        		deque.front.push(words[1]);
        		
        		System.out.println("Added the string " + words[1] + " to the front of the deque.");
        	}
        	
        	if(array[i].contains("add_last")){
        		
        		String[] words=array[i].split("\\s");
        		
        		deque.back.push(words[1]);
        		
        		System.out.println("Added the string " + words[1] + " to the back of the deque.");
        	}
        	
        	if(array[i].contains("remove_first")){
        		
        		if(deque.isEmpty()){
        			
        			System.out.println("The deque was empty.");
        		}
        		else{
        			
        			System.out.println("Removed the string " + deque.front.pop() + " from the front of the deque.");
        		}
        	}
        	
        	if(array[i].contains("remove_last")){
        		
        		if(deque.isEmpty()){
        			
        			System.out.println("The deque was empty.");
        		}
        		
        		else{
        			System.out.println("Removed the string " + deque.back.pop() + " from the back of the deque.");
        		}
        	}
        		
        	i++;
        }
	}
}