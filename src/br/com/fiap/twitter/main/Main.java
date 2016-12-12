package br.com.fiap.twitter.main;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.twitter.config.ConfiguraAcesso;
import br.com.fiap.twitter.entidade.DadosTwitter;
import br.com.fiap.twitter.entidade.RelatorioTwitter;
import br.com.fiap.twitter.exception.ConexaoTwitterException;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;

public class Main {
	public static void main(String[] args) {

		Twitter twitter = null;
		try {
			twitter = ConfiguraAcesso.iniciaTwitter();
		} catch (ConexaoTwitterException e1) {
			e1.printStackTrace();
			return;
		}

		LocalDateTime dataInicial = LocalDateTime.now().minusDays(7);
		LocalDateTime dataFinal = LocalDateTime.now();

		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		List<DadosTwitter> listaTwitter = new ArrayList<>();
		

		RelatorioTwitter relatorioTwitter = new RelatorioTwitter("#javaOne");

		Query query = new Query(relatorioTwitter.getHashtag());
		query.setCount(100);
		query.setSince(dataInicial.format(formatador));
		query.setUntil(dataFinal.format(formatador));
		try {
			QueryResult result = twitter.search(query);

			relatorioTwitter.setQtdTweets(result.getTweets().size());
			boolean hasNext = false;
			do{
				for (Status status : result.getTweets()) {

					LocalDateTime dataTweet = LocalDateTime.ofInstant(status.getCreatedAt().toInstant(),
							ZoneId.systemDefault());
					relatorioTwitter.adicionarTweetPorDia(dataTweet.getDayOfWeek(), 1);
					relatorioTwitter.adicionarRetweetPorDia(dataTweet.getDayOfWeek(), status.getRetweetCount());
					relatorioTwitter.adicionarFavPorDia(dataTweet.getDayOfWeek(), status.getFavoriteCount());
					
					DadosTwitter dadosTwitter = new DadosTwitter(status.getUser().getId(), 
							status.getUser().getScreenName(), status.getUser().getName(), dataTweet, status.getText());
					listaTwitter.add(dadosTwitter);
					
				}
				hasNext = result.hasNext();
				if(hasNext){
					result = twitter.search(result.nextQuery());
				}
			}while (hasNext) ;
			relatorioTwitter.ordenarPorNome(listaTwitter);
			relatorioTwitter.ordenarPorData(listaTwitter);

			exibeRelatorio(dataInicial, dataFinal, formatador, relatorioTwitter);
			
			relatorioTwitter.enviarRelatorio(twitter);
			
		} catch (Exception e) {
			System.out.println("Erro ao efetuar a busca!!!");
			e.printStackTrace();
		}

	}

	private static void exibeRelatorio(LocalDateTime dataInicial, LocalDateTime dataFinal, DateTimeFormatter formatador,
			RelatorioTwitter relatorioTwitter) {
		System.out.println("*******************************************");
		System.out.println("Hashtag buscada: " + relatorioTwitter.getHashtag());
		System.out.println(
				"Data de parametro: " + dataInicial.format(formatador) + " ate " + dataFinal.format(formatador));
		System.out.println("Tweets por dia de semana: " + relatorioTwitter.getQtdTweetDia().toString());
		System.out.println("Retweets por dia de semana: " + relatorioTwitter.getQtdRetweetDia().toString());
		System.out.println("Favoritacoes por dia de semana: " + relatorioTwitter.getQtdFavDia().toString());
		System.out.println("Primeiro nome: "+relatorioTwitter.getPrimeiroNome());
		System.out.println("Ultimo nome: "+relatorioTwitter.getUltimoNome());
		System.out.println("Primeira data encontrada: "+relatorioTwitter.getPrimeiraData());
		System.out.println("Ultima data encontrada: "+relatorioTwitter.getUltimaData());
		System.out.println("*******************************************");
	}

}
