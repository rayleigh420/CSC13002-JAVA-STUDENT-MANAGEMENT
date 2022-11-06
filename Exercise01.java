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
				System.out.println("Update student");

				String id, name, address;
				double GPA;

				System.out.print("Enter id student you want to update: ");
				id = s.nextLine();

				Boolean update = false;
				for (int i = 0; i < v.size(); i++){
					if (v.get(i).getID().equals(id)){
						update = true;

						int c;
						System.out.println();
						System.out.println("1. Update ID");
						System.out.println("2. Update Name");
						System.out.println("3. Update GPA");
						System.out.println("4. Update address");
						System.out.println("5. Update all");
						System.out.println("Anything else to exit");
						System.out.print("What kind do you want: ");
						c = s.nextInt();
						s.nextLine();

						System.out.println();
						if (c == 1){
							System.out.print("Enter new id: ");
							id = s.nextLine();

							v.get(i).setID(id);
						}
						else if (c == 2){
							System.out.print("Enter new name: ");
							name = s.nextLine();

							v.get(i).setName(name);
						}
						else if (c == 3){
							System.out.print("Enter new GPA: ");
							GPA = Double.parseDouble(s.nextLine());

							v.get(i).setGPA(GPA);
						}
						else if (c == 4){
							System.out.print("Enter new address: ");
							address = s.nextLine();

							v.get(i).setAddress(address);
						}
						else if (c == 5){
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
						else {
							break;
						}

					}
				}

				if (update == false){
					System.out.println("Id does not exist!");
				}
			}
			else if (choice == 3){
				System.out.println();
				System.out.println("Delete student");

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
				int c;
				System.out.println();
				System.out.println("1. Sort follow GPA ascending");
				System.out.println("2. Sort follow ID ascending");
				System.out.println("3. Not sort list");
				System.out.println("Anything else to exit!");
				System.out.print("What kind do you want: ");
				c = s.nextInt();

				if (c == 1){
					Collections.sort(v, new Comparator<Student>() {
					@Override
					public int compare(final Student a, Student b) {
						return a.getGPA() < b.getGPA()? -1 : a.getGPA() == b.getGPA() ? 0 : 1;
					}
					});	
				}
				else if (c == 2){
					Collections.sort(v, new Comparator<Student>() {
					@Override
					public int compare(final Student a, Student b) {
						return a.getID().compareToIgnoreCase(b.getID());
					}
					});	
				}
				else if (c == 3){}
				else {
					break;
				}

				for (int i = 0; i < v.size(); i++){
					System.out.println();
					v.get(i).getInfo();
				}
			}
			else if (choice == 5){
				String fileName;
				System.out.println();
				System.out.print("Enter name of file you want to save: ");
				fileName = s.nextLine();

				try (
					DataOutputStream outStream = new DataOutputStream(new FileOutputStream ("a3.out"));
				){
					for (int i = 0; i < v.size(); i++) {
						outStream.writeUTF(v.get(i).getID());
						outStream.writeUTF(v.get(i).getName());
						outStream.writeDouble(v.get(i).getGPA());
						outStream.writeUTF(v.get(i).getAddress());
					}

					outStream.close();
					System.out.println("Save success!");
				}
				catch(IOException e){
					System.out.println("Error message: " + e);
				}
			}
			else if (choice == 6){
				String fileName;
				System.out.println();
				System.out.print("Enter name of file you want to import: ");
				fileName = s.nextLine();
				try(
					BufferedReader br = new BufferedReader(new FileReader(fileName));
				){
					String line;
					while ((line = br.readLine()) != null) {
						String[] values = line.split(", ");
						Student st = new Student(values[0], values[1], Double.parseDouble(values[2]), values[3]);
						v.add(st);
					}
					br.close();
					System.out.println("Import Success!");
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
				try(
					FileWriter fw = new FileWriter(fileName);
				){
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
					System.out.println("Export Success!");
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

	public void setID(String id){
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setGPA(Double GPA){
		this.GPA = GPA;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public void updateInfo(String id, String name, double GPA, String address){
		this.id = id;
		this.name = name;
		this.GPA = GPA;
		this.address = address;
	}
}

