import java.util.Scanner;

public class EntradaMenu {

	public char leiaMenuPrincipal(){
		char itemMenu;
		Scanner leia = new Scanner(System.in);
		System.out.println("\nInforme a opção desejada:");
		System.out.println("[N]ova Rodada");
		System.out.println("[S]core Atual");
		System.out.println("[E]cerrar Sistema");
		try {
			itemMenu = leia.next().charAt(0);
			itemMenu = Character.toUpperCase(itemMenu);
			if (itemMenu != 'N' && itemMenu != 'S' && itemMenu != 'E'){
				throw new Exception("OPÇÃO INEXISTENTE");
			}
		}catch(Exception e){
        	System.err.println(">> "+e.getMessage()+" << Informe novamente");
        	itemMenu = leiaMenuPrincipal();
		}
		return itemMenu;
	}
	
	public char escolha(String msg, char opc1, char opc2, char opc3){
		char opcao;
		Scanner leia = new Scanner(System.in);
		System.out.println(msg);
		try {
			opcao = leia.next().charAt(0);
			opcao = Character.toUpperCase(opcao);
			if (opcao != opc1 && opcao != opc2 && opcao != opc3){
				throw new Exception("OPÇÃO INEXISTENTE");
			}
		}catch(Exception e){
        	System.err.println(">> "+e.getMessage()+" << Informe novamente");
        	opcao = escolha(msg, opc1, opc2, opc3);
		}
		return opcao;
	}
	
	public String leiaNomeJogador(int jogador){
		String nome = " ";
		Scanner leia = new Scanner(System.in);
		System.out.println("Informe o nome do(a) "+(jogador+1)+"º(ª) jogador(a): ");
		try {
			nome = leia.nextLine();
			if (!nome.trim().isEmpty()){
				for (int i=0; i<nome.length();i++){
					if (!(Character.isLetter(nome.charAt(i)) || Character.isDigit(nome.charAt(i)) || (nome.charAt(i) == ' '))){
						throw new Exception(nome.charAt(i)+" << É CARACTER NÃO PERMITIDO.");
					}
				}
			}
		}catch(Exception e){
        	System.err.println("Entrada inválida!! >> "+e.getMessage()+" Informe novamente!!");
        	nome = leiaNomeJogador(jogador);
		}
		while (nome.length()<13){
			nome = nome + " ";
		}
		nome = nome.toUpperCase();
		return nome;
	}
	
	public int escolhaCarta(int jogadorDaVez, int baralho[][]){
		int carta;

		Scanner leia = new Scanner(System.in);
		try {
			carta = leia.nextInt();
			if (carta < 0 || carta > 51){
				throw new Exception("A carta "+carta+" não existe");
			}
			if (baralho[carta][1] != (jogadorDaVez+1)){
				throw new Exception("Você não possui a carta "+carta);
			}
		}catch(Exception e){
        	System.err.println(">> "+e.getMessage()+" << Escolha outra carta");
        	carta = escolhaCarta(jogadorDaVez, baralho);
		}
		return carta;
	}

}
