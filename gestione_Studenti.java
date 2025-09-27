//gestione studenti
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

class Student{
    String name;
    String surname;
    ArrayList<Double> voteList;
    double media;
    
    Student(String name,String surname){
        this.name = name;
        this.surname = surname;
        this.voteList = new ArrayList<>();
        this.media = 0;
    }
    
    Double calMedia(){
        if(voteList.size() == 0){return 0.00;}
        
        double somma = 0;
        for(double v : voteList){
            somma += v;
        }
        
        return somma / voteList.size();
    }
    
    @Override
    public String toString(){
        return "[nome: "+name+" cognome: "+surname+" media: "+media+"]";
    }
}

public class Main
{
    static void menu(){
        System.out.println("=== registro ===");
        System.out.println("[1] inserisci Studente");
        System.out.println("[2] inserisci Voto");
        System.out.println("[3] visualiza Studenti");
        System.out.println("[4] cerca Studente");
        System.out.println("[5] uscire");
        System.out.println("=================");
        System.out.println("scelto: ");
    }

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Student> elencoStudent = new ArrayList<>();
		ArrayList<Double> voteList = new ArrayList<>();
	
		boolean on = true;
		while(on == true){
		    menu();
		    int choiceMenu = scanner.nextInt();
		    scanner.nextLine();
		    
		    switch(choiceMenu){
		        case 1:
		            System.out.println("inserisci il nome dello Studente: ");
		            String nameSet = scanner.nextLine();
		            
		            System.out.println("inserisci il cognome dello Studente: ");
		            String surnameSet = scanner.nextLine();
		            
		            Student s = new Student(nameSet,surnameSet);
		            elencoStudent.add(s);
		            break;
                
                case 2:
                    System.out.println("inserisci il nome dello studente: ");
                    String findNameForVote = scanner.nextLine();
                    
                    for(Student stud : elencoStudent){
                        
                        if(stud.name.equalsIgnoreCase(findNameForVote) || stud.surname.equalsIgnoreCase(findNameForVote)){
                            System.out.println("inserisci il voto: ");
                            double vote = scanner.nextDouble();
                           
                            stud.voteList.add(vote);
                            stud.media = stud.calMedia();
                        }
                    }
                break;
                
                case 3:
                    System.out.println("questi sono i tuoi studenti: ");
                    if(elencoStudent.size() == 0) {System.out.println("non hai studenti");  break;}
                    for (Student stud : elencoStudent){
                        System.out.println(stud);
                    }
                    break;
                
                case 4:
                    System.out.println("chi cerchi ? ");
                    String findStudent = scanner.nextLine();
                    
                    for(Student stud : elencoStudent){
                        if(stud.name.equalsIgnoreCase(findStudent) || stud.surname.equalsIgnoreCase(findStudent)){
                            System.out.println("studente trovato: "+ stud);
                        }
                    }
                    break;
                
                case 5:
                    System.out.println("Uscita ...");
                    on = false;
                    break;
		    }
		}
	}
}
