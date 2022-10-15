package br.com.alura.modelo;


public class Filme {
	
	private String titulo;
	private String urlImagem;
	private String ano;
	private String nota;
	
	public Filme(String titulo, String urlImagem, String ano, String nota) {
		this.titulo = titulo;
		this.urlImagem = urlImagem;
		this.ano = ano;
		this.nota = nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public String getAno() {
		return ano;
	}

	public String getNota() {
		return nota;
	}
	
	@Override
	public String toString() {
		
		return "[ Filme: " + this.titulo + 
				" | Poster: " + this.urlImagem + 
				" | Ano: " + this.ano + 
				" | Nota(IMDB): " + this.nota + "]";
	}
	
	

}
