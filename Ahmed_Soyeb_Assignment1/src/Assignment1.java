import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Assignment1{
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		FileInputStream fstream = null; 
		BufferedReader bf = null;
		
		try {
			fstream = new FileInputStream("Assignment1.txt");
			bf = new BufferedReader(new InputStreamReader(fstream));
			if (fstream == null || bf == null) { 	//if Filestream or bf is null then return nothing
				return;
			}

			CircularLinkedList<String> circularLinkedList = new CircularLinkedList<>();
			System.out.println("Reading File line by line by BufferedReader");
			String line = null;
			
			while ((line = bf.readLine()) != null){
				System.out.println(line);
				String[] CL = line.split(" ");
				if (CL.length != 0) {
					String cmd = CL[0];
					int index = -1;
					if ((!cmd.equalsIgnoreCase("add_last")) && CL.length > 1) {
						index = Integer.valueOf(CL[1]);
					}

					String string = "";
					int SI = 3;

					if (cmd.equalsIgnoreCase("add_last")) {
						SI = 2;
					}

					if (CL.length > SI) {
						for (int i = SI; i < CL.length; i++) {
							string += CL[i];
							if (i != CL.length - 1) {
								string += " ";
							}
						}
					}

					switch (cmd) { 
						case "clear":
							circularLinkedList.clear();
							System.out.println("The linked list is now empty." + "\n");
							break;

						case "size":
							System.out.println( "The linked list contains" + circularLinkedList.size() + " strings." + "\n");
							break;

						case "is_empty":
							if (circularLinkedList.isEmpty()) {
								System.out.println(" empty" + "\n");
							} 
							else {
								System.out.println(" not empty." + "\n");
							}
							break;
							
						case "get":
							if (circularLinkedList.isEmpty() || circularLinkedList.size() < index) {
								System.out.println("Empty." + "\n");
							} 
							else {
								System.out.println("The string at position " + index + " in the list is: " + circularLinkedList.get(index) + "\n");
							}
							break;

						case "set":
							if (circularLinkedList.isEmpty() || circularLinkedList.size() < index) {
								System.out.println("No element found." + "\n");
							} 
							else {
								String oldValue = circularLinkedList.set(index, string);
								System.out.println("The string \"" + string + "\" replaces the string \"" + oldValue + "\" at position " + index + " in the list." + "\n");
							}
							break;

						case "add":
							if (circularLinkedList.size() < index) {
								System.out.println("Empty." + "\n");
							} 
							else {
								circularLinkedList.add(index, string);
								System.out.println("Added the string \"" + string + "\" at position " + index + " in the list.\n");
							}
							break;

						case "add_last":
							circularLinkedList.add(string);
							System.out.println("Added the string " + string + " at the end position of the list.\n");
							break;
						case "remove":

							if (circularLinkedList.isEmpty() || circularLinkedList.size() < index) {
								System.out.println("Empty." + "\n");
							} 
							else {
								String value = circularLinkedList.remove(index);
								System.out.println("Removed the string " + value + " at position " + index + " from the circular linked list.\n");
							}
							break;

						case "rotate":
							if (circularLinkedList.isEmpty()) {
								System.out.println("Empty" + "\n");
							} 
							else {
								circularLinkedList.rotate();
								System.out.println( "The value at the head of the list was rotated to the tail of the list.\n");
							}
							break;

						case "print_list":
							if (!circularLinkedList.isEmpty()) {
								Iterator<String> iterator = circularLinkedList.iterator();
								while (iterator.hasNext()) {
									System.out.println(iterator.next() + "\n");
								}
							}

						default:
							break;
						}
					}
				}
			}
		
			catch (FileNotFoundException e) {
				System.out.println("No Command File found");
				e.printStackTrace();
			}
		
			catch (IOException e){
				e.printStackTrace();
			}
		}
}