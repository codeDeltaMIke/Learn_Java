//getione Biblioteca
import java.util.ArrayList;
import java.util.Scanner;


class Book{
    String author;
    String titel;
    double price;
    int amount;
    
    Book(String titel, String author, double price, int amount){
        this.titel = titel;
        this.price = price;
        this.author = author;
        this.amount = amount;
    }
    
    @Override
    public String toString(){
        return "[titolo: "+titel+" prezzo: "+price+" autore: "+author+" quantita disp.: "+amount+"]";
    }
    
    Integer borrow(){
        if(amount == 0){
            return 0;
        }
        amount -= 1;
        return amount;
    }
    
    Integer giveBack(){
        amount += 1;
        return amount;
    }
}

public class Main
{
    static void menu(){
        System.out.println("=== Liberia ===");
        System.out.println("[1] inserire Libro");
        System.out.println("[2] vedi tutti Libri");
        System.out.println("[3] cerca Libro");
        System.out.println("[4] prendi Inprestito");
        System.out.println("[5] ristituisci");
        System.out.println("[6] uscire");
        System.out.println("=================");
        System.out.println("scelto: ");
    }
    
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Book> bookList = new ArrayList<>();
		
		boolean on = true;
		while(on==true){
		    menu();
		    int choiceMenu = scanner.nextInt();
		    scanner.nextLine();
		    
		    switch(choiceMenu){
		        case 1:
		            System.out.println("inserisci il titolo del libro: ");
		            String titelSet = scanner.nextLine();
		            
		            System.out.println("inserisci l'aurore del libro: ");
		            String authorSet = scanner.nextLine();
		            
		            System.out.println("inserisci il prezzo del libro: ");
		            double priceSet = scanner.nextDouble();
		            
		            System.out.println("inserisci la quantita dei libri: ");
		            int amountSet = scanner.nextInt();
		            
		            Book b = new Book(titelSet,authorSet,priceSet,amountSet);
		            bookList.add(b);
		            break;
		        
		        case 2:
		            if(bookList.size()==0){System.out.println("non hai nessun libro a disposizione"); break;}
		            System.out.println("Libri in disposizione:");
		            
		            for(Book book : bookList){
		                System.out.println(book);
		            }
		            break;
		        
		        case 3:
		            if(bookList.size()==0){System.out.println("non hai nessun libro a disposizione"); break;}
		            
		            System.out.println("che libro cerchi (titolo o nome dell'autore) ? ");
		            String findBook = scanner.nextLine();
		            boolean find = false;
		            
		            for(Book book : bookList){
		                if(book.titel.equalsIgnoreCase(findBook) || book.author.equalsIgnoreCase(findBook)){
		                    System.out.println("trovato : "+book);
		                    find = true;
		                }
		            }
		            if(find == false){System.out.println("titolo o autore non trovato si prega di verificare che sia correto");}
		            break;
		        
		        case 4:
		            if(bookList.size()==0){System.out.println("non hai nessun libro a disposizione"); break;}
		            System.out.println("che libro vuoi prendere in prestito (titolo) ?");
		            String onLoan = scanner.nextLine();
		            boolean findOnLoan = false;
		            
		            for(Book book : bookList){
		                
		                if(book.titel.equalsIgnoreCase(onLoan)){
		                    findOnLoan = true;
		                    if(book.amount == 0){System.out.println("esauriti!"); break;}
		                  
    		                book.amount = book.borrow();
		                    System.out.println("trovato : "+book);
		                }
		            }
		            
		            if(findOnLoan == false){System.out.println("titolo o autore non trovato si prega di verificare che sia correto");}
		            break;
		        
		        case 5:
		            System.out.println("che libro vuoi ristituire (titolo) ?");
		            String giveBookBack = scanner.nextLine();
		            boolean findGiveBack = false;
		            
		            for(Book book : bookList){
		                if(book.titel.equalsIgnoreCase(giveBookBack)){
		                    findGiveBack = true;
		                    book.amount = book.giveBack();
		                    System.out.println("libro restituito: "+book);}
		            }
		            if(findGiveBack == false){System.out.println("titolo o autore non trovato si prega di verificare che sia correto");}
		            break;
		            
                case 6:
                    System.out.println("Uscita ...");
                    on = false;
                    break;
		    }
		}
	}
}
//by deltaMike