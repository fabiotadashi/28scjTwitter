package br.com.fiap.twitter.entidade;

import twitter4j.Twitter;
import twitter4j.TwitterException;

public class RelatorioTwitter {

  private int qtdTweets;
  private int qtdRetweets;
  private int qtdFavoritacoes;
  
  public int getQtdTweets() {
    return qtdTweets;
  }
  public void setQtdTweets(int qtdTweets) {
    this.qtdTweets = qtdTweets;
  }
  public int getQtdRetweets() {
    return qtdRetweets;
  }
  public void setQtdRetweets(int qtdRetweets) {
    this.qtdRetweets = qtdRetweets;
  }
  public int getQtdFavoritacoes() {
    return qtdFavoritacoes;
  }
  public void setQtdFavoritacoes(int qtdFavoritacoes) {
    this.qtdFavoritacoes = qtdFavoritacoes;
  }
  
  public void enviarRelatorio(Twitter twitter, String hashtag) throws TwitterException{
    String professorId = "";
    // descomentar o código abaixo quando tudo estiver ok
    // professorId = "@michelpf"
    twitter.updateStatus(professorId+" - Tag: "+ hashtag+", qtd tweets: "+qtdTweets+", qtd re-tweets: "+qtdRetweets+", qtd favoritações: "+qtdFavoritacoes+" na ultima semana");
  }
  
}
