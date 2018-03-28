import java.util.Random;

public class Rodada {

	EntradaMenu em = new EntradaMenu();
	Jogador[] j = new Jogador[5];
	
	public void RecebeJogadores (int rodada){
		String nomeLido;
		
		//recebe os nomes dos 5 jogadores via console e atribui pontuação zero
		for (int nroJogador=0; nroJogador<5; nroJogador++){
			j[nroJogador] = new Jogador();
			nomeLido = em.leiaNomeJogador(nroJogador).toUpperCase();
			j[nroJogador].InicializaJogador(nomeLido,0);
		}
	}
	
	public int SorteiaPrimeiroJogador(){
		int primeiroJogador;
		char opcao;
		
		Random sorteia = new Random();
		
		do{
			//sorteia primeiro jogador da rodada
			primeiroJogador = sorteia.nextInt(5);
			System.out.print("\nO(a) primeiro(a) jogador(a) da rodada será ["+(primeiroJogador+1)+"] ");
			System.out.print(j[primeiroJogador].getNomeJogador());
			opcao = em.escolha("\nRealizar novo sorteio? ([S]im / [N]ão)",'S','N','N');
		}while(opcao == 'S');
		
		return primeiroJogador;
	}
}
