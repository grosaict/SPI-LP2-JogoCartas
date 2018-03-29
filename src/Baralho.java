import java.util.Random;

public class Baralho {

	private int carta [][] = new int[52][2];
	private int baralho [][] = new int[52][2];
	
	public void InicializaBaralho(){
		/*	naipe:					valor carta:			local:
		 * 		0 = Ouros				A = 0					0 = monte
		 * 		1 = Espadas				2 a 10 = valor - 1		1 = jogador 1
		 * 		2 = Copas				J = 10					2 = jogador 2
		 * 		3 = Paus				Q = 11  				3 = jogador 3
		 * 								K = 12  				4 = jogador 4
		 *														5 = jogador 5
		 *    													6 = mesa
		 *    													7 = fora do jogo*/
		Random randomiza = new Random();
		int cartaBaralho = 0;
		int cartaQualquer;
		boolean cartaExistente;
		
		//inicializa o baralho com valor fora do range [0-51] pra não dar problema ao embaralhar
		for (int i=0; i<52; i++){
			this.baralho[i][0] = 99;
		}
		
		//monta o baralho
		for (int naipe=0; naipe<4; naipe++){
			for (int vlrCarta=0; vlrCarta<13; vlrCarta++){
				
				//define as cartas (HARD CODE)
				this.carta[cartaBaralho][0] = naipe;
				this.carta[cartaBaralho][1] = vlrCarta;
				
				//embaralha a sequência
				do{
					cartaQualquer = randomiza.nextInt(52);
					cartaExistente = false;
					for (int i=0; i<52; i++){
						if (this.baralho[i][0] == cartaQualquer){
							cartaExistente = true;
						}
					}
				}while(cartaExistente);

				//monta baralho
		    	this.baralho[cartaBaralho][0] = cartaQualquer;	//atribui carta
		    	this.baralho[cartaBaralho][1] = 0;				//posiciona no monte
		    	
		    	cartaBaralho++;
			}
		}
	}

	public void Atribui5Cartas(int primeiroJogador) {
		int jogadorAtual = primeiroJogador;
		
		for (int cartaBaralho=0; cartaBaralho<25; cartaBaralho++){
			this.baralho[cartaBaralho][1] = jogadorAtual+1;
			if (jogadorAtual == 4){
				jogadorAtual = 0;
			}else{
				jogadorAtual++;
			}
		}
	}
	
	public void VisualizaBaralho(){
		int cartaMonte = 0;
		
		//cabeçalho relatório
		System.err.println("                             >>>    S I T U A Ç Ã O      D O      B A R A L H O    <<<");
		try {
			Thread.sleep(25);		//delay para garantir a impressão do texto acima antes (err)
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------------------------------------------------------------------------------------------");
		System.out.println("      CARTA      [L o c a l]      CARTA      [L o c a l]      CARTA      [L o c a l]      CARTA      [L o c a l]");
		System.out.print("-------------------------------------------------------------------------------------------------------------------");

		//montagem em quatro colunas por linha (4x2=52)
		for (int linha=0; linha<13; linha++){
			System.out.print("\n");
			for (int coluna=0; coluna<4; coluna++){
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//valor da carta
				switch(this.carta[(this.baralho[cartaMonte][0])][1]){
				case 0:
					System.out.print("    A");
					break;
				case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8:
					System.out.print("    "+(this.carta[(this.baralho[cartaMonte][0])][1]+1));
					break;
				case 9:
					System.out.print("   "+(this.carta[(this.baralho[cartaMonte][0])][1]+1));
					break;
				case 10:
					System.out.print("    J");
					break;
				case 11:
					System.out.print("    Q");
					break;
				case 12:
					System.out.print("    K");
					break;
				}
				//naipe da carta
				switch(this.carta[(this.baralho[cartaMonte][0])][0]){
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
				//localização da carta
				switch(this.baralho[cartaMonte][1]){
				case 0:
					System.out.print(" [  monte  ]");
					break;
				case 1: case 2: case 3: case 4: case 5:
					System.out.print(" [Jogador "+this.baralho[cartaMonte][1]+"]");
					break;
				case 6:
					System.out.print(" [ m e s a ]");
					break;
				case 7:
					System.out.print(" [ f o r a ]");
					break;
				}
				cartaMonte++;
			}
		}
		System.out.println("\n-------------------------------------------------------------------------------------------------------------------");
	}

	public int[][] getBaralho() {
		return this.baralho;
	}

	public void setBaralho(int[][] baralho) {
		this.baralho = baralho;
	}

	public int[][] getCarta() {
		return this.carta;
	}

}
