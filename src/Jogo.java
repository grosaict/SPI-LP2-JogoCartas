import java.util.Scanner;

public class Jogo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int cartas [][] = new int[52][2];
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
				
				if(rodada == 1){
					r.RecebeJogadores(rodada);		//rotinas para inicializar jogadores
				}
				primeiroJogador = r.SorteiaPrimeiroJogador();
				b.Atribui5Cartas(primeiroJogador);
				
				b.VisualizaBaralho();				//rotina para visualizar o baralho
				
				cartas = b.getCarta();				//rotinas para executar a jogada
				baralho = b.getBaralho();
				baralho = r.RealizaRodada(primeiroJogador, cartas, baralho);
				b.setBaralho(baralho);
				
				b.VisualizaBaralho();				//rotina para visualizar o baralho
				break;
			case 'S':
				if (rodada > 0){
					r.ScoreAtual(cartas, baralho);	//rotina para visualizar o score atual
				}else{
					System.err.println("Ainda não houve nenhuma rodada!!!");
				}
				break;
			}
		}while(itemMenu == 'N' || itemMenu == 'S');
		
		System.err.println(">>> Sistema Encerrado <<<");
	}
}
