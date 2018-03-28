import java.util.Scanner;

public class Jogo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char itemMenu;
		int rodada = 0;
		int primeiroJogador;
		
		int carta [][] = new int[52][2];
		int baralho [][] = new int[52][2];
		
		EntradaMenu em = new EntradaMenu();
		Baralho b = new Baralho();
		Rodada r = new Rodada();
		
		System.out.println(">>> Sistema de Jogo de Cartas <<<");
		
		do{
			itemMenu = em.leiaMenuPrincipal();
			switch (itemMenu){
			case 'N':
				rodada ++;
				b.InicializaBaralho();		//rotinas para inicializar e buscar o baralho gerado
				carta = b.getCarta();
				baralho = b.getBaralho();
				
				r.RecebeJogadores(rodada);	//rotinas para realizar a rodada
				primeiroJogador = r.SorteiaPrimeiroJogador();
				baralho = b.Atribui5Cartas(primeiroJogador);
				
				b.setCarta(carta);			//rotinas para atualizar e visualizar o baralho
				b.setBaralho(baralho);
				b.VisualizaBaralho();
				break;
			case 'R':
				if (rodada > 0){
					System.out.println("Implementar apresentação resultado do último jogo");
				}else{
					System.err.println("Ainda não houve nenhuma rodada!!!");
				}
				break;
			}
		}while(itemMenu == 'N' || itemMenu == 'R');
		
		System.err.println(">>> Sistema Encerrado <<<");
	}
}
