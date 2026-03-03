package soma;
import java.util.*;

class Soma {
	public static void main(String[] args)
	{
		// cria objeto Scanner para leitura 
		Scanner dado = new Scanner(System.in);
		
		int x,y,soma;
		
		//leitura 
		System.out.println("Escreva numeros para soma: ");
		
		x = dado.nextInt();
		y = dado.nextInt();
		
		soma = x + y;
		
		//impressão
		System.out.println("Soma: " + soma);
			
	}
	
}
