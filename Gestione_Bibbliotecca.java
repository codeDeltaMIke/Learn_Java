//Gestione Bibbliotecca
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.InputMismatchException;// questa liberia server per verificare se ci sono erorri con int

class Prodoct{
    String name;
    String date;
    double price;
    int stack;
    String author;
    int id;
    static int setId = 83940;
    
    Prodoct(String name,String date,double price,int stack,String author){
        this.id = setId++;//ci agiorna l'id
        this.name = name;
        this.date = date;
        this.price = price;
        this.stack = stack;
        this.author = author;
    }

    public int buyProdoct(){
        return stack -= 1;
    }

    @Override
    public String toString(){
        return "[titel: "+name+" data: "+date+" prezzo: "+price+" disponibili: "+stack+"]";
    }
}

class Book extends Prodoct{
    String type;
    int mannyPag;
    
    Book(String name,String date,double price,String author,int stack,String type,int mannyPag){
        super(name,date,price,stack,author);
        this.type = type;
        this.mannyPag = mannyPag;
    }
    
    @Override
    public String toString(){
        return "[tipo: [libro] id:["+id+"] titel: "+name+" autore: "+author+" genere ["+type+"] pagine totali:["+mannyPag+"]data: "+date+" prezzo: "+price+" disponibili: "+stack+"]";
    }
}

class AudioBook extends Prodoct{
    String duration;
    
    AudioBook(String name,String date,double price,String duration,int stack,String author){
        super(name,date,price,stack,author);
        this.duration = duration;
    }

    @Override
    public String toString(){
        return "[tipo: [audio libro] id:["+id+"] titel: "+name+" durata: "+duration+" narratore: "+author+" data: "+date+" prezzo: "+price+" disponibili: "+stack+"]";
    }
}

class Revist extends Prodoct{
    String volum;
    
    Revist(String name,String date,double price,String volum,int stack,String author){
        super(name,date,price,stack,author);
        this.volum = volum;
    }

    @Override
    public String toString(){
        return "[tipo: [audio libro] id:["+id+"] titel: "+name+" volume: "+volum+" data: "+date+" prezzo: "+price+" disponibili: "+stack+"]";
    }
}

class Library{
    private boolean returned;
    ArrayList<Prodoct> saveProd = new ArrayList<Prodoct>();
    
    public void setReturn(boolean returned){
        this.returned = returned;
    }

    public Boolean booking(Prodoct p,ArrayList<Prodoct> saveProd){
        if(p.stack == 0){System.out.println("\nnon disponibile"); return false;}
        p.buyProdoct();
        saveProd.add(p);

        System.out.println("\nprestito effetuato con succeso!");
        return true;
    }

    public Boolean returned(Prodoct p,ArrayList<Prodoct> saveProd){
        if(saveProd.size()==0){System.out.println("\nnon hai preso nulla in prestito"); return false;}
        p.stack += 1;
        saveProd.remove(p);
        
        System.out.println("\nristituito con succeso!");
        return true;
    }
    
}

class User{
    String email;
    String userName;

    User (String userName,String email){     // si come non chiesto non faro un file con pure la password 
        this.userName = userName;
        this.email = email;
    }
}

public class Main
{
    static void menu(){
        System.out.println("\n==== delta_Library =====");
        System.out.println("[1] metti prodotto");
        System.out.println("[2] vedi prodotti");
        System.out.println("[3] cerca prodotto");
        System.out.println("[4] compra prodotti");
        System.out.println("");
        System.out.println("|per prendere in prestito devi registrarti|");
        System.out.println("[5] login");
        System.out.println("[6] prendere in prestito");
        System.out.println("[7] restituire il prestito");
        System.out.println("[8] vedere prodotti in prestito");
        System.out.println("[0] esci");
        System.out.println("=======================");
        System.out.println("scelta: \n");
    }
	public static void main(String[] args) {
        //                giorno                                  mese                    anno
	    String Date = "([1-9]|0[1-9]|1[0-9]|2[0-9]|3[0-1])/([1-9]|1[0-2]|0[1-9])/(0\\d{3}|1\\d{3}|20[0-1]\\d|202[0-5])";
        String Login = "[a-z,0-9]+@+[a-z]+.+[a-z]";

	    Pattern ptDate = Pattern.compile(Date);
        Pattern ptEmail = Pattern.compile(Login);
	    
        Scanner scan = new Scanner(System.in);
        Library lib = new Library();
		ArrayList<Prodoct> listProdoct = new ArrayList<>();
        ArrayList<Prodoct> prestiti = new ArrayList<>();
        ArrayList<User> userLogin = new ArrayList<>();
        
		boolean on = true;
		
		while(on){
		    try{
		        menu();
		        int choiceMenu = scan.nextInt();
		        scan.nextLine();
		        
		        switch(choiceMenu){
		            case 1:
		                System.out.println("inserisci il tipo (tutto in minuscolo (libro/audio libro/ reviste)): ");
		                String type = scan.nextLine();
		                
		                switch(type){
		                    case "libro":
		                        System.out.println("\ninserisci il titolo: ");
		                        String titel = scan.nextLine();
		                        
		                        System.out.println("\ninserisci l'autore: ");
		                        String autore = scan.nextLine();

		                        System.out.println("\ninserisci quante pagine ha: ");
		                        int pagine = scan.nextInt();
		                        scan.nextLine();//messo per evitare che salti il genere

		                        System.out.println("\ninserisci il genere: ");
		                        String genere = scan.nextLine();
		                        
		                        System.out.println("\ninserisci il prezzo: ");
		                        double price = scan.nextDouble();
		                        
		                        System.out.println("\ninserisci il stack: ");
		                        int stack = scan.nextInt();
		                        
		                        boolean dataCoretta = true;
		                        scan.nextLine();

		                        //verifica se la data è coretta

		                        while(dataCoretta){
    		                        System.out.println("\ninserisci il data: ");
    		                        String dateUser = scan.nextLine();
    		                        
    		                        Matcher matchDate = ptDate.matcher(dateUser);
    		                        boolean coferm = matchDate.matches();
    		                        
    		                        if(coferm == true){
    		                            dataCoretta = false;
    		                            Book book = new Book(titel,dateUser,price,autore,stack,genere,pagine);
    		                            listProdoct.add(book);
    		                            break;
    		                        }
    		                        else{System.out.println("\ndata falsa riprova!");}
		                        }
		                        break;
		                        
		                    case "audio libro":
		                        System.out.println("\ninserisci il titolo: ");
		                        String titelA = scan.nextLine();

		                        System.out.println("\ninserisci l'autore: ");
		                        String autoreA = scan.nextLine();
		                        
		                        System.out.println("\ninserisci la durata: ");
		                        String durationA = scan.nextLine();
		                        
		                        System.out.println("\ninserisci il prezzo: ");
		                        double priceA = scan.nextDouble();
		                        
		                        System.out.println("\ninserisci lo stack: ");
		                        int stackA = scan.nextInt();
		                        
		                        boolean dataCorettaA = true;
		                        scan.nextLine();
		                        
		                        while(dataCorettaA){
    		                        System.out.println("\ninserisci il data: ");
    		                        String dateUserD = scan.nextLine();
    		                        
    		                        Matcher matchDate = ptDate.matcher(dateUserD);
    		                        boolean coferm = matchDate.matches();
    		                        
    		                        if(coferm == true){
    		                            dataCorettaA = false;
    		                            Prodoct aBook = new AudioBook(titelA,dateUserD,priceA,durationA,stackA,autoreA);
    		                            listProdoct.add(aBook);
    		                            break;
    		                        }
    		                        else{System.out.println("\ndata falsa riprova!");}
		                        }
		                        break;

		                    case "reviste":
		                        System.out.println("\ninserisci il titolo: ");
		                        String titelR = scan.nextLine();

		                        System.out.println("\ninserisci l'autore: ");
		                        String authorR = scan.nextLine();
		                        
		                        System.out.println("\ninserisci il volime: ");
		                        String volumR = scan.nextLine();
		                        
		                        System.out.println("\ninserisci il prezzo: ");
		                        double priceR = scan.nextDouble();
		                        
		                        System.out.println("\ninserisci lo stack: ");
		                        int stackR = scan.nextInt();
		                        
		                        boolean dataCorettaR = true;
		                        scan.nextLine();

		                        

		                        while(dataCorettaR){
    		                        System.out.println("\ninserisci il data: ");
    		                        String dateUserD = scan.nextLine();
    		                        
    		                        Matcher matchDate = ptDate.matcher(dateUserD);
    		                        boolean coferm = matchDate.matches();
    		                        
    		                        if(coferm == true){
    		                            dataCorettaR = false;
    		                            Prodoct revist = new Revist(titelR,dateUserD,priceR,volumR,stackR,authorR);
    		                            listProdoct.add(revist);
    		                            break;
    		                        }
    		                        else{System.out.println("\ndata falsa riprova!");}
		                        }
		                        break;
                                
		                }
                        break;

                    case 2:
                        for(Prodoct p : listProdoct){
                            System.out.println(p);
                        }
                        break;

                    case 3:
                        if(listProdoct.size()==0){System.out.println("non ce inseritto nessun prodotto"); break;}
                        boolean findP = false;
                        System.out.println("che prodotto cerchi (inserisci titoli)? ");
                        String findProdoct = scan.nextLine();

                        for(Prodoct p : listProdoct){
                            if(p.name.equalsIgnoreCase(findProdoct)){
                                System.out.println("prodotto trovato: "+p);
                                findP = true;
                            }
                        }
                        if(findP == false){System.out.println("prodotto non trovato"); break;}
                        break;

                    case 4:
                        if(listProdoct.size()==0){System.out.println("non ce inseritto nessun prodotto"); break;}
                        boolean findPB = false;
                        System.out.println("che prodotto vuoi comprare (inserisci titoli)? ");
                        String findProdoctBuy = scan.nextLine();

                        for(Prodoct p : listProdoct){
                            if(p.name.equalsIgnoreCase(findProdoctBuy)){
                                System.out.println("prodotto trovato: "+p);
                                System.out.println("\nvuoi aquistarlo: (si/no)?");
                                String yesNo = scan.nextLine();
                                switch(yesNo){
                                    case "si":
                                        if(p.stack==0){System.out.println("mi dispiace prodotto esaurito."); break;}
                                        p.buyProdoct();
                                        System.out.println("\naquistato con successo !");
                                        break;
                                    case "no":
                                        System.out.println("\nOkay!");
                                        break;
                                }
                                findPB = true;
                            }
                        }
                        if(findPB == false){System.out.println("prodotto non trovato"); break;}
                        break;

                    case 5:
                        System.out.println("\ninserisci il nome utente: ");
                        String nameU = scan.nextLine();
                        
                        boolean emailCorrect = false;
                        while(!emailCorrect){
                            System.out.println("\ninserisci l'email: ");
                            String emailU = scan.nextLine();

                            Matcher matchEmail = ptEmail.matcher(emailU);
                            if(matchEmail.matches() == true){
                                User user = new User(nameU,emailU);
                                userLogin.add(user);
                                System.out.println("\nlogin effetuato con succeso!");
                                emailCorrect = true;
                                break;
                            }
                        }
                        break;

                    case 6:
                        if(userLogin.size() == 0){System.out.println("\nDevi effetuare il login !!!"); break;}
                        if(listProdoct.size()==0){System.out.println("non ce inseritto nessun prodotto"); break;}
                        boolean findPBP = false;
                        System.out.println("che prodotto vuoi prendere in prestito (inserisci titoli)? ");
                        String findProdoctPrestit = scan.nextLine();

                        for(Prodoct p : listProdoct){
                            if(p.name.equalsIgnoreCase(findProdoctPrestit)){

                                System.out.println("prodotto trovato: "+p);
                                System.out.println("\nvuoi prenderlo in prestito: (si/no)?");
                                String yesNoP = scan.nextLine();

                                switch(yesNoP){
                                    case "si":
                                        if(p.stack==0){System.out.println("mi dispiace prodotto esaurito."); break;}
                                        
                                        lib.booking(p,prestiti);
                                        break;

                                    case "no":
                                        System.out.println("\nOkay!");
                                        break;
                                }
                                findPBP = true;
                            }
                        }
                        if(findPBP == false){System.out.println("prodotto non trovato"); break;}
                        break;

                    case 7:
                        if(prestiti.size()==0){System.out.println("\nnon hai preso nulla in prestito.");break;}
                        boolean findPBR = false;
                        System.out.println("che prodotto vuoi restituire: (inserisci titoli)? ");
                        String findProdoctReturned = scan.nextLine();

                        for(Prodoct p : listProdoct){
                            if(p.name.equalsIgnoreCase(findProdoctReturned)){

                                System.out.println("prodotto trovato: "+p);
                                System.out.println("\nvuoi restiteuirlo prestito: (si/no)?");
                                String yesNoR = scan.nextLine();

                                switch(yesNoR){
                                    case "si":
                                        if(p.stack==0){System.out.println("mi dispiace prodotto esaurito."); break;}
                                        lib.returned(p,prestiti);
                                        break;

                                    case "no":
                                        System.out.println("\nOkay!");
                                        break;
                                }
                                findPBR = true;
                            }
                        }
                        if(findPBR == false){System.out.println("\nprodotto non trovato"); break;}
                        break;

                    case 8:
                        if(prestiti.size()==0){System.out.println("\nnon hai preso nulla in prestito.");break;}
                        for(Prodoct p : prestiti){
                            System.out.println(p);
                        }
                        break;

                    case 0:
                        System.out.println("Uscita ...");
                        on = false;
                        break;
                        
                    default:
                        System.out.println("\nil tasto scelto non è tra le opsioni.");
                    
		        }
		    }catch(InputMismatchException e){
		        System.out.println("\nNon devi inserire una stringa ma un intero!!!");
		        System.out.println("clicca invio!");
		        String invio = scan.nextLine();
		    }
            catch(Exception e){
                System.out.println("ce un erorre");
                String invioErrore = scan.nextLine();
            }
		}
	}
}
//by deltaMike