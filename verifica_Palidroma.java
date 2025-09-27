//verifica se la parola e un palidroma
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("inserisci la tua parola: ");
		String word = in.nextLine();
		String tempWord = word;
		
		String invers = new StringBuilder(word).reverse().toString();
	    
	    
		if(word.equalsIgnoreCase(invers)){
		    System.out.println("la tua parola "+ invers + " è una palidroma :O");
		}
		else{
		    System.out.println("la tua parola "+ word +" non è una palidroma -_-");
		}
	}
}
