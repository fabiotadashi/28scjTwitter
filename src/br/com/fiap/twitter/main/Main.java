package br.com.fiap.twitter.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.fiap.twitter.config.ConfiguraAcesso;
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

		System.out.println(dataInicial.format(formatador));
		System.out.println(dataFinal.format(formatador));

		Query query = new Query("#java");
		// query.setCount(100);
		query.setSince(dataInicial.format(formatador));
		query.setUntil(dataFinal.format(formatador));
		try {
			QueryResult result = twitter.search(query);
			for (Status status : result.getTweets()) {
				System.out.println("Data: " + status.getCreatedAt());
				System.out.println("Qtde: " + status.getRetweetCount());
				System.out.println("Qtde Retweet: " + status.getRetweetCount());
				System.out.println("Qtde Favoritação: " + status.getFavoriteCount());
				System.out.println("Qtde Nome: " + status.getUser());
				System.out.println("--************************************************--");
			}

		} catch (Exception e) {
			System.out.println("Erro ao efetuar a busca!!!");
			e.printStackTrace();
		}

	}

}
