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
			System.out.println(j[primeiroJogador].getNomeJogador());
			opcao = em.escolha("\nRealizar novo sorteio? ([S]im / [N]ão)",'S','N','N');
		}while(opcao == 'S');
		
		return primeiroJogador;
	}
	
	public int [][] RealizaRodada(int jogadorDaVez, int cartas[][], int baralho[][]){
		int cartaMonteDisponivel = 25;
		int cartaEscolhida[] = new int [5];
		int venceTurno;
		int valorCartaEsolhida[] = new int [5];
		boolean temK;
		
		do{
			temK = false;
			for (int i=0; i<6; i++){		//um ciclo a mais para o proximo turno ter novo primero jogador
				if (i < 5){
					VisualizaMesa(jogadorDaVez, cartas, baralho);

					//escolhe a carta a ser colocada na mesa
					System.out.println("["+(jogadorDaVez+1)+" - "+j[jogadorDaVez].getNomeJogador()+"], escolha uma carta para descartar");
					cartaEscolhida[jogadorDaVez] = em.escolhaCarta(jogadorDaVez, baralho);

					//posiciona a carta na mesa e grava valor para saber o maior
					baralho[(cartaEscolhida[jogadorDaVez])][1] = 6;
					valorCartaEsolhida[jogadorDaVez] = cartas[(baralho[(cartaEscolhida[jogadorDaVez])][0])][1];
					
					//compra carta se disponivel
					if (cartaMonteDisponivel < 52){
						baralho[(cartaMonteDisponivel)][1] = jogadorDaVez+1;
						cartaMonteDisponivel++;
					}

					//registra se há uma carta de valor K na jogada
					if (valorCartaEsolhida[jogadorDaVez] == 12){
						temK = true;
					}
				}else{
					venceTurno = 0;
					for (int jogador=0; jogador<5; jogador++){	//localiza maior e ajusta baralho
						if ((valorCartaEsolhida[venceTurno] == valorCartaEsolhida[jogador])
						&& (venceTurno != jogador)){
							System.out.println("Há jogadores empatados!!! Cartas fora do jogo.");
							venceTurno = 99;					//o valor indica o empate
							jogador = 6;						//se há empate encerra o <for>: localiza maior
						}else{
							if ((valorCartaEsolhida[jogador] == 0 && temK)
							|| (valorCartaEsolhida[jogador] > valorCartaEsolhida[venceTurno])){
								venceTurno = jogador;
							}
						}
					}
					for (int j=0; j<5; j++){
						if (venceTurno == 99){
							baralho[(cartaEscolhida[j])][1] = 7;
						}else{
							baralho[(cartaEscolhida[j])][1] = (venceTurno+1);
						}
					}
				}
				if (jogadorDaVez == 4){
					jogadorDaVez = 0;
				}else{
					jogadorDaVez++;
				}
			}
		}while(cartaMonteDisponivel < 55);
		
		return baralho;
	}
	
	public void VisualizaMesa(int jogadorDaVez, int cartas[][], int baralho[][]){
		int cartaEncontrada, ocorrencia;
		int prxCartaJogador[] = new int [5];
		boolean fimLista;
		
		//cabeçalho relatório
		System.out.println("\n\n                      >>>    S I T U A Ç Ã O      D A      M E S A    <<<");
		System.out.println("-----------------------------------------------------------------------------------------------");
		for (int jogador=0; jogador<5; jogador++){
			prxCartaJogador[jogador] = 1;			//inicializa indicando para apresentar a 1a carta do jogador
			if (jogador == jogadorDaVez){
				try {
					Thread.sleep(5);		//delay para garantir a impressão do texto abaixo após (err)
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.err.print(" ["+(jogador+1)+" - "+j[jogador].getNomeJogador().substring(0,12)+"]");
				try {
					Thread.sleep(5);		//delay para garantir a impressão do texto acima antes (err)
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				System.out.print(" ["+(jogador+1)+" - "+j[jogador].getNomeJogador().substring(0,12)+"]");
			}
		}
		System.out.print("\n-----------------------------------------------------------------------------------------------");

		
		do {
			fimLista = true;							//assume que já listou todas as cartas de todos os jogadores
			System.out.print("\n");
			
			for (int jogador=0; jogador<5; jogador++){
				cartaEncontrada = 99;
				ocorrencia = 0;
				for (int cartaBaralho=0; cartaBaralho<52; cartaBaralho++){
					if (baralho[cartaBaralho][1] == (jogador+1)){
						ocorrencia++;						//indica quantas cartas foram localizadas para o jogador
						if (prxCartaJogador[jogador] == ocorrencia){
							cartaEncontrada = baralho[cartaBaralho][0];
							ImprimeVlrCarta(cartaBaralho, cartaEncontrada, cartas);
							ImprimeNaipeCarta(cartaEncontrada, cartas);
							prxCartaJogador[jogador]++;		//indica que deve verificar existencia de carta no prx ciclo
							fimLista = false;				//indica que ainda há cartas a listar
							cartaBaralho = 52;				//localizando a carta, encerra o <for>
						}
					}
				}
				
				if (cartaEncontrada == 99){	//imprime em branco
					System.out.print("                   ");
				}
			}
		}while(!fimLista);

		System.out.println("\n                      >>>            D E S C A R T A D A S            <<<");
		System.out.println("-----------------------------------------------------------------------------------------------");

		ocorrencia = 0;
		for (int cartaBaralho=0; cartaBaralho<52; cartaBaralho++){
			if (baralho[cartaBaralho][1] == 6){
				ocorrencia++;
				if (ocorrencia > 5){
					System.out.print("\n");						//quebra um linha a cada cinco ocorrencias
					ocorrencia = 1;
				}
				cartaEncontrada = baralho[cartaBaralho][0];
				ImprimeVlrCarta(cartaBaralho, cartaEncontrada, cartas);
				ImprimeNaipeCarta(cartaEncontrada, cartas);
			}
		}
		System.out.println("\n-----------------------------------------------------------------------------------------------");
	}
	
	public void ImprimeVlrCarta(int cartaBaralho, int cartaSolicitada, int cartas[][]){
		if (cartaBaralho > 9){
			try {
				Thread.sleep(5);		//delay para garantir a impressão do texto abaixo após (err)
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.err.print(" ["+cartaBaralho+"] ");
			try {
				Thread.sleep(5);		//delay para garantir a impressão do texto acima antes (err)
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				Thread.sleep(5);		//delay para garantir a impressão do texto abaixo após (err)
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.err.print(" [ "+cartaBaralho+"] ");
			try {
				Thread.sleep(5);		//delay para garantir a impressão do texto acima antes (err)
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		switch(cartas[cartaSolicitada][1]){
		case 0:
			System.out.print(" A");
			break;
		case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8:
			System.out.print(" "+(cartas[cartaSolicitada][1]+1));
			break;
		case 9:
			System.out.print((cartas[cartaSolicitada][1]+1));
			break;
		case 10:
			System.out.print(" J");
			break;
		case 11:
			System.out.print(" Q");
			break;
		case 12:
			System.out.print(" K");
			break;
		}
	}
	
	public void ImprimeNaipeCarta(int cartaSolicitada, int cartas[][]){
		switch(cartas[cartaSolicitada][0]){
		case 0:
			System.out.print(" de Ouros  ");
			break;
		case 1:
			System.out.print(" de Espadas");
			break;
		case 2:
			System.out.print(" de Copas  ");
			break;
		case 3:
			System.out.print(" de Paus   ");
			break;
		}
	}
}
