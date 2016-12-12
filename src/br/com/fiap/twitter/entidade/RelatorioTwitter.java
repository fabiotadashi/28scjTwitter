package br.com.fiap.twitter.entidade;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Classe que possui os dados para gerar os relatorios
 * 
 * @author FabioMiyasato
 * 
 */
public class RelatorioTwitter {

	private final String hashtag;
	private int qtdTotalTweets;
	private Map<DayOfWeek, Integer> qtdTweetDia = new HashMap<>();
	private Map<DayOfWeek, Integer> qtdRetweetDia = new HashMap<>();
	private Map<DayOfWeek, Integer> qtdFavDia = new HashMap<>();
	private String primeiroNome;
	private String ultimoNome;
	private LocalDateTime primeiraData;
	private LocalDateTime ultimaData;
	
	
	/**
	 * @param hashtag indica qual a hashtag que foram gerados os dados
	 */
	public RelatorioTwitter(String hashtag) {
		this.hashtag = hashtag;
	}

	public int getQtdTweets() {
		return qtdTotalTweets;
	}

	public void setQtdTweets(int qtdTweets) {
		this.qtdTotalTweets = qtdTweets;
	}

	
	/**
	 * Metodo que contabiliza tweets por dia de semana
	 * 
	 * @author FabioMiyasato
	 * @param dayOfWeek Enum representado o dia da semana
	 * @param qtd Quantidade de tweets a serem contabilizados neste dia
	 */
	public void adicionarTweetPorDia(DayOfWeek dayOfWeek, int qtd) {
		if (getQtdTweetDia().get(dayOfWeek) != null) {
			qtd += getQtdTweetDia().get(dayOfWeek);
		}
		getQtdTweetDia().put(dayOfWeek, +qtd);
	}

	/**
	 * Metodo retorna map com os tweets por dia de semana
	 * 
	 * @author FabioMiyasato
	 * @return HashMap com os tweets por dia de semana
	 */
	public Map<DayOfWeek, Integer> getQtdTweetDia() {
		return qtdTweetDia;
	}
	
	/**
	 * Metodo que contabiliza retweets por dia de semana
	 * 
	 * @author FabioMiyasato
	 * @param dayOfWeek Enum representado o dia da semana
	 * @param qtd Quantidade de retweets a serem contabilizados neste dia
	 */
	public void adicionarRetweetPorDia(DayOfWeek dayOfWeek, int qtd) {
		if (getQtdRetweetDia().get(dayOfWeek) != null) {
			qtd += getQtdRetweetDia().get(dayOfWeek);
		}
		getQtdRetweetDia().put(dayOfWeek, +qtd);
	}

	/**
	 * Metodo retorna map com os retweets por dia de semana
	 * 
	 * @author FabioMiyasato
	 * @return HashMap com os retweets por dia de semana
	 */
	public Map<DayOfWeek, Integer> getQtdRetweetDia() {
		return qtdRetweetDia;
	}

	/**
	 * Metodo que contabiliza favoritacoes por dia de semana
	 * 
	 * @author FabioMiyasato
	 * @param dayOfWeek Enum representado o dia da semana
	 * @param qtd Quantidade de favoritacoes a serem contabilizados neste dia
	 */
	public void adicionarFavPorDia(DayOfWeek dayOfWeek, int qtd) {
		if (getQtdFavDia().get(dayOfWeek) != null) {
			qtd += getQtdFavDia().get(dayOfWeek);
		}
		getQtdFavDia().put(dayOfWeek, +qtd);
	}

	/**
	 * Metodo retorna map com as favoritacoes por dia de semana
	 * 
	 * @author FabioMiyasato
	 * @return HashMap com as favoritacoes por dia de semana
	 */
	public Map<DayOfWeek, Integer> getQtdFavDia() {
		return qtdFavDia;
	}

	/**
	 * Metodo que envia relatorio avisando que o projeto foi finalizado
	 * 
	 * @author FabioMiyasato
	 * @param twitter Objeto twitter com conexao valida
	 */
	public void enviarRelatorio(Twitter twitter) throws TwitterException {
		String professorId = "@michelpf";
		twitter.updateStatus(professorId + " trabalho finalizado. - Tag: " + hashtag + ", qtd tweets: " + qtdTotalTweets
				+ " na ultima semana. Github: fabiotadashi/28scjTwitter");
	}

	/**
	 * Metodo que ordena lista por nome
	 * 
	 * @author Douglas
	 * @param listaTwiter Lista a ser ordenada
	 */
	public void ordenarPorNome(List<DadosTwitter> listaTwitter) {
		List<DadosTwitter> listaOrdenada = listaTwitter.stream()
				.sorted((l1, l2) -> l1.getNome().compareTo(l2.getNome())).collect(Collectors.toList());
		this.primeiroNome = listaOrdenada.get(0).getNome();
		this.ultimoNome = listaOrdenada.get(listaOrdenada.size() - 1).getNome();
	}

	/**
	 * Metodo que ordena lista por data
	 * 
	 * @author Douglas
	 * @param listaTwiter Lista a ser ordenada
	 */
	public void ordenarPorData(List<DadosTwitter> listaTwitter) {
		List<DadosTwitter> listaOrdenada = listaTwitter.stream()
				.sorted((l1, l2) -> l2.getData().compareTo(l1.getData())).collect(Collectors.toList());
		this.primeiraData= listaOrdenada.get(listaOrdenada.size() - 1).getData();
		this.ultimaData  = listaOrdenada.get(0).getData();

	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public LocalDateTime getPrimeiraData() {
		return primeiraData;
	}

	public void setPrimeiraData(LocalDateTime primeiraData) {
		this.primeiraData = primeiraData;
	}

	public LocalDateTime getUltimaData() {
		return ultimaData;
	}

	public void setUltimaData(LocalDateTime ultimaData) {
		this.ultimaData = ultimaData;
	}

	public String getHashtag() {
		return hashtag;
	}

}
