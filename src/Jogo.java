import java.util.Scanner;

public class Jogo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int carta [][] = new int[52][2];
		int baralho [][] = new int[52][2];
		
		char itemMenu;
		int rodada = 0;
		int primeiroJogador;
		
		EntradaMenu em = new EntradaMenu();
		Baralho b = new Baralho();
		Rodada r = new Rodada();
		
		System.out.println(">>> Sistema de Jogo de Cartas <<<");
		
		do{
			itemMenu = em.leiaMenuPrincipal();
			switch (itemMenu){
			case 'N':
				rodada ++;
				b.InicializaBaralho();				//rotina para inicializar o baralho
				
				r.RecebeJogadores(rodada);			//rotinas para inicializar jogadores
				primeiroJogador = r.SorteiaPrimeiroJogador();
				b.Atribui5Cartas(primeiroJogador);
				
				b.VisualizaBaralho();				//rotina para visualizar o baralho
				
				carta = b.getCarta();				//rotinas para executar a jogada
				baralho = b.getBaralho();
				baralho = r.RealizaRodada(primeiroJogador, carta, baralho);
				b.setBaralho(baralho);
				
				//b.VisualizaBaralho();				//rotina para visualizar o baralho
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
