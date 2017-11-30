import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        BinarySearchTree bTree = new BinarySearchTree();
        Boolean go = false;
        initialStudents(bTree);

        while(!go){
            clearScreen();
            System.out.printf("%-10s %-20s %s","ID","NAME","MAJOR\n");
            bTree.print();
            try{
                switch (Menu()){
                    case "A":
                        addStudent(bTree);
                        break;
                    case "D":
                        displayStudent(bTree);
                        break;
                    case "R":
                        removeStudent(bTree);
                        break;
                    case "Q":
                        System.out.println("END OF STUDENT SEARCH");
                        go = true;
                        break;
                    default:
                        System.out.println("Please enter one of the choices.");
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static void initialStudents(BinarySearchTree bTree){
        try {
            Student a = new Student(12345, "Cole Hamels", "DPR");
            Student b = new Student(54321, "Randy Wolf", "ENG");
            Student c = new Student(98765, "Roy Oswalt", "MAT");
            Student d = new Student(17563, "Cliff Lee", "DPR");
            Student e = new Student(45678, "Roy Halladay", "BUS");

            bTree.add(a);
            bTree.add(b);
            bTree.add(c);
            bTree.add(d);
            bTree.add(e);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static String Menu(){
        Scanner in = new Scanner(System.in);
        String x;
        System.out.print( "STUDENT SEARCH MENU:\n" +
                "Add a student:     'A'\n" +
                "Display students:  'D'\n" +
                "Remove a student:  'R'\n" +
                "Quit:              'Q'\n" +
                "Enter one of the following choices: ");
        x = in.next().toUpperCase();
        System.out.println();
        return x;
    }

    private static void clearScreen(){
        System.out.print("\f" + "\n");
    }

    private static void pause() throws IOException {
        System.out.print("Press Enter key to continue: ");
        char c = (char) System.in.read();
    }

    private static void addStudent(BinarySearchTree bTree){
        Student tmp = new Student();
        Scanner in = new Scanner(System.in);
        Boolean end = false;
        int pos = 0;
        while(!end){
            try {
                switch (pos) {
                    case 0:
                        System.out.print("Enter the student's ID number:  ");
                        if(in.hasNextInt()){
                            tmp.setStudentId(in.nextInt());
                            in.nextLine();
                            if(bTree.find(tmp) != null){
                                System.out.println("A student with that ID already exists");
                                break;
                            }
                        }else{
                            System.out.println("The student's ID number must be an integer.");
                            in.nextLine();
                            pos = 0;
                            break;
                        }
                        pos++;
                    case 1:
                        System.out.print("Enter the student's name:  ");
                        //in.nextLine();
                        tmp.setStudentName(in.nextLine());
                        pos++;
                    case 2:
                        System.out.print("Enter the student's major or (Leave blank for undecided):  ");
                        tmp.setStudentMajor(in.nextLine());
                    case 3:
                        end = true;
                        break;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        bTree.add(tmp);
    }

    private static void displayStudent(BinarySearchTree bTree)throws IOException{
        Scanner in = new Scanner(System.in);
        System.out.print("Enter student ID or major ID: ");
        if(in.hasNextInt()){
            singleStudentDisplay(in.nextInt(), bTree);
        }else{
            majorDisplay(in.nextLine(), bTree);
        }
    }

    private static void removeStudent(BinarySearchTree bTree) throws IOException{
        Scanner in = new Scanner(System.in);
        Student tmp;
        System.out.print("Enter the ID of the student to remove: ");
        if(in.hasNextInt()){
            int x = in.nextInt();
            tmp = new Student(x);
            if(bTree.find(tmp) == null){
                System.out.println("That ID does not exist.");
                pause();
            }else{
                bTree.remove(tmp);
            }
        }else{
            System.out.println("You must enter the ID of the Student ");
            pause();
        }
    }

    private static void singleStudentDisplay(int x, BinarySearchTree bTree) throws IOException {
        Student tmp = new Student(x);
        if(bTree.find(tmp) == null){
            System.out.println("That student ID does not exist.");
        }else{
            System.out.println(bTree.find(tmp));
        }
        pause();
    }

    private static void majorDisplay(String x, BinarySearchTree bTree) throws IOException {
        ArrayList<Object> tmp = bTree.getList();
        Student t;
        int counter = 0;
        x = x.toUpperCase();
        for(Object obj: tmp){
            t = (Student)obj;
            if(t.getStudentMajor().equals(x)){
                System.out.println(t);
                counter++;
            }
        }
        System.out.println("There are " + counter + " students in the major " + x + ".");
        pause();
    }
}
