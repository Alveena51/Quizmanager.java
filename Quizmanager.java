
	
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.List;
	import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
	import java.util.Scanner;

	public class Quizmanager {

	    private Scanner scanner;

	    public Quizmanager () {
	        scanner = new Scanner(System.in);
	    }

	    public void displayWelcomeMessage() {
	        System.out.println("\033[1;36m***************************************************\033[0m");
	        System.out.println("\033[1;36m***************************************************\033[0m");
	        System.out.println("\033[1;35m             Welcome to the Quiz Manager!\033[0m");
	        System.out.println("\033[1;36m***************************************************\033[0m");
	        System.out.println("\033[1;36m***************************************************\033[0m");
	        System.out.println("This program allows to manage quizzes and student info.");
	        System.out.println("Please select an option from the menu below:");
	    }


	    public void displayMenu() {                                                                            //menu
	    	displayWelcomeMessage();                                                                  // Display the welcome message
	        while (true) {
	            System.out.println("\nWhich panel do you want to enter?");
	            System.out.println("Press 1 for Admin panel");
	            System.out.println("Press 2 for Student panel");
	            System.out.println("Press 3 to Exit");
	            System.out.print("> ");

	            int choice = Integer.parseInt(scanner.nextLine());

	            switch (choice) {
	                case 1:
	                    adminPanel();
	                    break;
	                case 2:
	                    studentPanel();
	                    break;
	                case 3:
	                    System.out.println("Exiting the program.");
	                    scanner.close();                                                   // Close the global scanner before exiting
	                    return;
	                default:
	                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
	            }
	        }
	    }

	    private void adminPanel() {                                                                            //admin panel
	        System.out.println("Welcome to the Admin panel");
	        while (true) {
	            System.out.println("\nAdmin Panel");
	            System.out.println("1. Add students");
	            System.out.println("2. View Student Info");
	            System.out.println("3. Back to main menu");
	          //  System.out.println("4. Switch to Student panel");
	            System.out.print("Enter your choice: ");
	            int adminChoice = Integer.parseInt(scanner.nextLine());

	            switch (adminChoice) {
	                case 1:
	                    addStudents();
	                    break;
	                case 2:
	                    viewTranscripts();
	                    break;
	                case 3:
	                    return; // Back to the main menu
	                case 4:
	                    return; // Switch to the student panel
	                default:
	                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
	            }
	        }
	    }

	                                                                                                           // student panel 
	    public void studentPanel() {
	        boolean accessGranted = false;

	        while (!accessGranted) {
	            System.out.println("Welcome to Student Panel");
	            System.out.println("Enter your Name:");
	            String name = scanner.nextLine();
	            System.out.println("Enter Password:");
	            String password = scanner.nextLine();

	            if (checkPassword(name, password)) {
	                accessGranted = true;
	                System.out.println("Password matched. Access granted to Student Panel.");
	            } else {
	                System.out.println("Incorrect password. Access denied.");
	            }
	        }

	                                                                                                        // student panel logic here
	        boolean backToMainMenu = false;
	        while (!backToMainMenu) {
	            System.out.println("\nStudent Panel Options:");
	            System.out.println("1. View Courses");
	     //       System.out.println("2. View Grades");
	            System.out.println("3. Back to Main Menu");
	            System.out.print("Enter your choice: ");
	            int studentChoice = Integer.parseInt(scanner.nextLine());

	            switch (studentChoice) {
	                case 1:
	                   viewCourses(); 
	                case 3:
	                    backToMainMenu = true; // Back to the main menu
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
	            }
	        }
	    
	    }

	    public static boolean checkPassword(String username, String enteredPassword) {
	        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\PMLS\\Desktop\\student.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split("\t");
	                if (parts.length >= 2 && parts[0].equalsIgnoreCase(username)) {
	                    String storedPassword = parts[1];

	                    return enteredPassword.equals(storedPassword);
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading student file: " + e.getMessage());
	        }
	        return false;
	    }
	                                                                                                 // view courses
	    public void viewCourses() {
	        System.out.println("Choice Taken\n");

	        int quiz;
	        do {
	            System.out.println("Enter the subject Quiz you want to attempt:");
	            System.out.println("1: General Knowledge");
	            System.out.println("2: English");
	            System.out.println("3: Mathematics");
	            System.out.println("4: Java");

	            try {
	                quiz = Integer.parseInt(scanner.nextLine());
	                String desktopPath = "C:\\Users\\PMLS\\Desktop\\";

	                switch (quiz) {                                                           //checking ans
	                    case 1:
	                        checkAnswers(desktopPath + "new.txt", desktopPath + "newans.txt");					
	                        break;
	                    case 2:
	                        checkAnswers(desktopPath + "1new.txt", desktopPath + "1newans.txt");
	                        break;
	                    case 3:
	                        checkAnswers(desktopPath + "2new.txt", desktopPath + "2newans.txt");
	                        break;
	                    case 4:
	                        checkAnswers(desktopPath + "3new.txt", desktopPath + "3newans.txt");
	                        break;
	                    default:
	                        System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid input. Please enter a number.");
	                quiz = 0; // Set quiz to an invalid value to continue the loop
	            }
	        } while (quiz < 1 || quiz > 4); // Continue until a valid choice is made
	    }

	    private void checkAnswers(String questionsFile, String answersFile) {
	        try (BufferedReader questionsReader = new BufferedReader(new FileReader(questionsFile));
	             BufferedReader answersReader = new BufferedReader(new FileReader(answersFile))) {

	            List<String> questions = new ArrayList<>();
	            List<String> answers = new ArrayList<>();

	            // Read questions and answers into lists
	            String questionLine;
	            String answerLine;
	            while ((questionLine = questionsReader.readLine()) != null && (answerLine = answersReader.readLine()) != null) {
	                questions.add(questionLine);
	                answers.add(answerLine);
	            }

	                                                                                                   // Shuffle questions
	            Collections.shuffle(questions);

	            int questionNumber = 1;

	            // Present shuffled questions to the user
	            for (String question : questions) {
	                String[] questionParts = question.split("\t");
	                String[] answerParts = answers.get(questionNumber - 1).split("\t");

	                if (questionParts.length == 2 && answerParts.length == 1) {
	                    String q = questionParts[0];
	                    String options = questionParts[1];
	                    String correctAnswer = answerParts[0];

	                    System.out.println("Q" + questionNumber + " " + q);
	                    String[] optionsArray = options.split("-");
	                    for (int i = 0; i < optionsArray.length; i++) {
	                        System.out.println(optionsArray[i]);
	                    }
	                    System.out.print("Your answer (A, B, C, D): ");
	                    String userAnswer = scanner.nextLine().toLowerCase(); // Convert input to lowercase

	                    if (userAnswer.equals(correctAnswer.toLowerCase())) {
	                        System.out.println("Correct!\n");
	                    } else {
	                        System.out.println("Wrong! The correct answer is: " + correctAnswer + "\n");
	                    }

	                    questionNumber++;
	                } else {
	                    System.out.println("Invalid question or answer format.");
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading question or answer file: " + e.getMessage());
	        }
	    }

	  //  public static void viewGrades(String username) {
	        // Implement code to view grades for the given student
	 //   }

	    

	    public void addStudents() {
	        System.out.println("Welcome to Admin Panel");
	                                                                                          // Ask how many students to register
	        System.out.println("How many students do you want to register?");
	        int numStudents = Integer.parseInt(scanner.nextLine());

	                                                                                     // Input student information and save to the file
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\PMLS\\Desktop\\student.txt", true))) {
	            for (int i = 0; i < numStudents; i++) {
	                System.out.println("Enter student name:");
	                String studentName = scanner.nextLine();
	                System.out.println("Enter student password:");
	                String studentPassword = scanner.nextLine();
	                System.out.println("Enter student home address:");
	                String studentAddress = scanner.nextLine();
	                 
	    	                                                                                               // Validate email address
	    	        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    	        
	    	                                                                                          // Validate phone number
	    	        String phonePattern = "\\d{11}"; // Exactly 11 digits
	    	        
	    	        boolean validEmail = false;
	    	        boolean validPhoneNumber = false;
	    	        String studentEmail = "";
	    	        String studentPhoneNumber = "";

	    	        // Validate email address
	    	        while (!validEmail) {
	    	            System.out.println("Enter student email:");
	    	            studentEmail = scanner.nextLine();

	    	            if (studentEmail.matches(emailPattern)) {
	    	                validEmail = true;
	    	            } else {
	    	                System.out.println("Invalid email pattern. Please enter a valid email address.");
	    	            }
	    	        }

	    	        // Validate phone number
	    	        while (!validPhoneNumber) {
	    	            System.out.println("Enter student phone number:");
	    	            studentPhoneNumber = scanner.nextLine();

	    	            if (studentPhoneNumber.matches(phonePattern)) {
	    	                validPhoneNumber = true;
	    	            } else {
	    	                System.out.println("Invalid phone number format. Please enter a 11-digit number.");
	    	            }
	    	        }

	    	
	    	    
	                // Write student information to the file
	                bw.write(studentName + "\t" + studentPassword + "\t" + studentEmail + "\t" + studentAddress + "\t" + studentPhoneNumber);
	                bw.newLine();
	                System.out.println("Student registered successfully.");
	            }
	        } catch (IOException e) {
	            System.out.println("An error occurred while writing to the file.");
	        }
	    }

	    public void viewTranscripts() {
	        System.out.println("Enter the name of the student whose Info you want to view:");
	        String studentToView = scanner.nextLine();

	        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\PMLS\\Desktop\\student.txt"))) {
	            String line;
	            boolean studentFound = false;

	            while ((line = br.readLine()) != null) {
	                String[] studentInfo = line.split("\t");
	                if (studentInfo.length >= 5 && studentInfo[0].equalsIgnoreCase(studentToView)) {
	                    System.out.println("Name: " + studentInfo[0]);
	                    System.out.println("Email: " + studentInfo[2]);
	                    System.out.println("Phone Number: " + studentInfo[4]);
	                    System.out.println("Address: " + studentInfo[3]);

	                    // Add code to display the transcript here
	                    displayTranscript(studentInfo[0]); // Assuming student name is used as a key+
	                    studentFound = true;
	                    break; // Exit the loop after finding and displaying student information
	                }
	            }

	            if (!studentFound) {
	                System.out.println("Student not found.");
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading student file: " + e.getMessage());
	        }
	    }



	    private void displayTranscript(String string) {
			// TODO Auto-generated method stub
			
		}

		public static void main(String[] args) {
			Quizmanager menu = new Quizmanager();
	        menu.displayMenu();
	    }
	
}
