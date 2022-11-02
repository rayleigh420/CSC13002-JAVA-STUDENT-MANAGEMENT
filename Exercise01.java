import java.util.*;
import java.io.*;

public class Exercise01
{

	public static void main(String[] args) throws IOException {
		Vector<Student> v = new Vector<Student>();
		Scanner s = new Scanner(System.in);	
		int choice;

		while(true){
			System.out.println();
			System.out.println("1. Add student: ");
			System.out.println("2. Update student: ");
			System.out.println("3. Delete student: ");
			System.out.println("4. View student list: ");
			System.out.println("5. Save student list binary file: ");
			System.out.println("6. Import student csv file: ");
			System.out.println("7. Export student csv file: ");
			System.out.println("Chose difference chosen to exit");
			System.out.print("Chose action: ");
			choice = s.nextInt();
			s.nextLine();

			if (choice == 1){
				System.out.println();
				System.out.println("Add new student");

				String id, name, address;
				double GPA;

					
				System.out.print("Enter id of student: ");
				id = s.nextLine();
				System.out.print("Enter name of student: ");
				name = s.nextLine();
				System.out.print("Enter GPA of student: ");
				GPA = Double.parseDouble(s.nextLine());
				System.out.print("Enter address of student: ");
				address = s.nextLine();

				Student st = new Student(id, name, GPA, address);
				v.add(st);
				
			}		
			else if (choice == 2){
				System.out.println();
				System.out.println("Add new student");

				String id, name, address;
				double GPA;

				System.out.print("Enter id student you want to update: ");
				id = s.nextLine();

				Boolean update = false;
				for (int i = 0; i < v.size(); i++){
					if (v.get(i).getID().equals(id)){
						update = true;

						System.out.print("Enter new id: ");
						id = s.nextLine();
						System.out.print("Enter new name: ");
						name = s.nextLine();
						System.out.print("Enter new GPA: ");
						GPA = Double.parseDouble(s.nextLine());
						System.out.print("Enter new address: ");
						address = s.nextLine();

						v.get(i).updateInfo(id, name, GPA, address);
					}
				}

				if (update == false){
					System.out.println("Id does not exist!");
				}
			}
			else if (choice == 3){
				System.out.println();
				System.out.println("Add new student");

				String id;
				System.out.print("Enter id of student you want to delete: ");
				id = s.nextLine();

				Boolean delete = false;
				for (int i = 0; i < v.size(); i++){
					if (v.get(i).getID().equals(id)){
						delete = true;
						v.remove(i);
					}
				}	

				if (delete == false){
					System.out.println("Id does not exist!");
				}			
			}
			else if (choice == 4){
				for (int i = 0; i < v.size(); i++){
					System.out.println();
					v.get(i).getInfo();
				}
			}
			else if (choice == 5){

			}
			else if (choice == 6){
				String fileName;
				System.out.println();
				System.out.print("Enter name of file you want to import: ");
				fileName = s.nextLine();
				try{
					BufferedReader br = new BufferedReader(new FileReader(fileName));
					String line;
					while ((line = br.readLine()) != null) {
						String[] values = line.split(", ");
						Student st = new Student(values[0], values[1], Double.parseDouble(values[2]), values[3]);
						v.add(st);
					}
					br.close();
					System.out.println("Export Success!");
				}
				catch(IOException e){
					System.out.println("Error message: " + e);
				}

			}
			else if (choice == 7){
				String fileName;
				System.out.println();
				System.out.print("Enter name of file you want to export: ");
				fileName = s.nextLine();
				try{
					FileWriter fw = new FileWriter(fileName);
					for (int i = 0; i < v.size(); i++)
					{
						fw.write(String.valueOf(v.get(i).getID()));
						fw.write(", ");
						fw.write(String.valueOf(v.get(i).getName()));
						fw.write(", ");
						fw.write(String.valueOf(v.get(i).getGPA()));
						fw.write(", ");
						fw.write(String.valueOf(v.get(i).getAddress()));
						fw.write("\n");
					}
					fw.close();
					System.out.println("Import Success!");
				}
				catch(IOException e){
					System.out.println("Error message: " + e);
				}
			}
			else {
				break;
			}
		}
	}
}

class Student {
	private String id;
	private String name;
	private String address;
	private double GPA;

	public Student(String id, String name, double GPA, String address) {
		this.id = id;
		this.name = name;
		this.GPA = GPA;
		this.address = address;
	}

	public void getInfo() {
		System.out.println("ID: " + this.id);
		System.out.println("Name: " + this.name);
		System.out.println("GPA: " + this.GPA);
		System.out.println("Address: " + this.address);
	}

	public String getID(){
		return this.id;
	}

	public String getName(){
		return this.name;
	}

	public double getGPA(){
		return this.GPA;
	}

	public String getAddress(){
		return this.address;
	}

	public void updateInfo(String id, String name, double GPA, String address){
		this.id = id;
		this.name = name;
		this.GPA = GPA;
		this.address = address;
	}
}

