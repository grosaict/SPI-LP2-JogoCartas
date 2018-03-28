public class Jogador {

	private String nomeJogador;
	private int pontos;
	
	public void InicializaJogador(String nome, int pts){
		this.nomeJogador = nome;
		this.pontos = pts;
	}
	
	public void AtualizaPontos(int ptsGanhos){
		this.pontos =+ ptsGanhos;
	}

	public String getNomeJogador() {
		return this.nomeJogador;
	}

	public int getPontos() {
		return this.pontos;
	}

}
