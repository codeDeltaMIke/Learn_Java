//esercizio gestione Lista
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Prodotto{
    String name;
    double price;
    int amount;
    
    Prodotto (String name,double price,int amount){
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
    
    @Override //soprascrivi
    public String toString(){
        return "[nome: "+name+" prezzo: "+price+" quanti: "+amount+"]"; //serve per ogni volta c print un oggeto mi
    }                                                                   //riporta cosi
}

public class Main
{
    static void menu(){
	    System.out.println("\n=== menu ===");
	    System.out.println("[1] inserisci Prodotti");
	    System.out.println("[2] vedi Prodotti");
	    System.out.println("[3] cerca Prodotto");
	    System.out.println("[4] stock dei Prodotti");
	    System.out.println("[5] uscire");
	    System.out.println("==============");
	    System.out.println("scelta: ");
	}
	public static void main(String[] args) {
	    
		ArrayList<Prodotto> list = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		boolean on = true;
		
		while(on){
		    menu();
		    int choiceMenu = scanner.nextInt();
		    scanner.nextLine();
		    switch(choiceMenu){
		        case 1:
                    System.out.println("inserisci nome del Prodotto: ");
                    String nameSet = scanner.nextLine();
                    
                    System.out.println("inserisci prezzo del Prodotto: ");
                    double priceSet = scanner.nextDouble();
                    
                    System.out.println("inserisci quanti Prodotto: ");
                    int amountSet = scanner.nextInt();
                    
                    Prodotto p = new Prodotto(nameSet,priceSet,amountSet);
                    list.add(p);
                    break;
                
                case 2:
                    System.out.println("questi sono i prodotti:");
                    
                    if(list.size() == 0)
                    {System.out.println("non hai inserito nessun prodotto");  break;}
                    
                    for (Prodotto prodoct : list){
                        System.out.println(prodoct);
                    }
                    break;
                
                case 3:
                    boolean find=false;
                    if(list.size() == 0){System.out.println("non ce nessun prodotto");  break;}
                    
                    System.out.println("cosa cerca ? ");
                    String searchProdoct = scanner.nextLine();
                    
                    for(Prodotto prodoct : list){

                        if (prodoct.name.equalsIgnoreCase(searchProdoct)){
                            System.out.println("Prodotto trovato!");
                            System.out.println(prodoct);
                            find = true;
                        }
                    }
                    
                    if(!find==true){
                        System.out.println(
                            "spiacenti non habbiamo trovato un prodotto con quel nome, verifica che sia correto!");
                    }
                    break;
            
                case 4:
                    double vauleStock = 0;
                    for(Prodotto prodoct : list){
                        vauleStock += prodoct.price * prodoct.amount;
                    }
                    System.out.println("lo stock è: "+vauleStock+" €");
                    break;
                
                case 5:
                    System.out.println("Uscita ...");
                    on = false;
                    break;
                default:
                    System.out.println("tasto non valido");
                    break;
		    }
		}
		
	}
}
//by deltaMike
