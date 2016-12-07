package br.com.fiap.twitter.entidade;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import twitter4j.Twitter;
import twitter4j.TwitterException;

public class RelatorioTwitter {

	private final String hashtag;
	private int qtdTotalTweets;
	private Map<DayOfWeek, Integer> qtdTweetDia = new HashMap<>();
	private Map<DayOfWeek, Integer> qtdRetweetDia = new HashMap<>();
	private Map<DayOfWeek, Integer> qtdFavDia = new HashMap<>();

	public RelatorioTwitter(String hashtag) {
		this.hashtag = hashtag;
	}

	public int getQtdTweets() {
		return qtdTotalTweets;
	}

	public void setQtdTweets(int qtdTweets) {
		this.qtdTotalTweets = qtdTweets;
	}

	public void adicionarTweetPorDia(DayOfWeek dayOfWeek, int qtd) {
		if (getQtdTweetDia().get(dayOfWeek) != null) {
			qtd += getQtdTweetDia().get(dayOfWeek);
		}
		getQtdTweetDia().put(dayOfWeek, +qtd);
	}

	public Map<DayOfWeek, Integer> getQtdTweetDia() {
		return qtdTweetDia;
	}

	public void adicionarRetweetPorDia(DayOfWeek dayOfWeek, int qtd) {
		if (getQtdRetweetDia().get(dayOfWeek) != null) {
			qtd += getQtdRetweetDia().get(dayOfWeek);
		}
		getQtdRetweetDia().put(dayOfWeek, +qtd);
	}

	public Map<DayOfWeek, Integer> getQtdRetweetDia() {
		return qtdRetweetDia;
	}

	public void adicionarFavPorDia(DayOfWeek dayOfWeek, int qtd) {
		if (getQtdFavDia().get(dayOfWeek) != null) {
			qtd += getQtdFavDia().get(dayOfWeek);
		}
		getQtdFavDia().put(dayOfWeek, +qtd);
	}

	public Map<DayOfWeek, Integer> getQtdFavDia() {
		return qtdFavDia;
	}

	public void enviarRelatorio(Twitter twitter) throws TwitterException {
		String professorId = "";
		// descomentar o codigo abaixo quando tudo estiver ok
		// professorId = "@michelpf"
		twitter.updateStatus(professorId + " trabalho finalizado. - Tag: " + hashtag + ", qtd tweets: " + qtdTotalTweets
				+ " na ultima semana. Github: fabiotadashi/28scjTwitter");
	}

	public void ordenaEExibePrimeiroEUltimoNome(List<DadosTwitter> listaTwitter) {
		List<DadosTwitter> listaOrdenada = listaTwitter.stream()
				.sorted((l1, l2) -> l1.getAutor().compareTo(l2.getAutor())).collect(Collectors.toList());
		System.out.println("Primeiro Nome: " + listaOrdenada.get(0).getNome());
		System.out.println("Ultimo Nome: " + listaOrdenada.get(listaOrdenada.size() - 1).getNome());
	}

	public void ordenaEExibeDataMaisEMenosRecente(List<DadosTwitter> listaTwitter) {
		List<DadosTwitter> listaOrdenada = listaTwitter.stream()
				.sorted((l1, l2) -> l2.getData().compareTo(l1.getData())).collect(Collectors.toList());
		System.out.println("Data mais recente: " + listaOrdenada.get(0).getData());
		System.out.println("Data menos recente: " + listaOrdenada.get(listaOrdenada.size() - 1).getData());

	}

}
