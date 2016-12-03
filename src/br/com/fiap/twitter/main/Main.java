package br.com.fiap.twitter.main;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import br.com.fiap.twitter.config.ConfiguraAcesso;
import br.com.fiap.twitter.entidade.RelatorioTwitter;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;

public class Main {
	public static void main(String[] args) {

		Twitter twitter = ConfiguraAcesso.iniciaTwitter();

		LocalDateTime dataInicial = LocalDateTime.now().minusDays(7);
		LocalDateTime dataFinal = LocalDateTime.now();

		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String hashtag = "#javaOne";

		RelatorioTwitter relatorioTwitter = new RelatorioTwitter(hashtag);

		Query query = new Query(hashtag);
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
				}
				hasNext = result.hasNext();
				if(hasNext){
					result = twitter.search(result.nextQuery());
				}
			}while (hasNext) ;

			System.out.println("Hashtag buscada: " + hashtag);
			System.out.println(
					"Data de parâmetro: " + dataInicial.format(formatador) + " até " + dataFinal.format(formatador));
			System.out.println("Tweets por dia de semana: " + relatorioTwitter.getQtdTweetDia().toString());
			System.out.println("Retweets por dia de semana: " + relatorioTwitter.getQtdRetweetDia().toString());
			System.out.println("Favoritações por dia de semana: " + relatorioTwitter.getQtdFavDia().toString());

		} catch (Exception e) {
			System.out.println("Erro ao efetuar a busca!!!");
			e.printStackTrace();
		}

	}

}
