package br.com.fiap.twitter.entidade;

import twitter4j.Twitter;
import twitter4j.TwitterException;

public class RelatorioTwitter {

  private int qtdTweets;
  private final String hashtag;
  
  public RelatorioTwitter(String hashtag) {
    this.hashtag = hashtag;
  }
  public int getQtdTweets() {
    return qtdTweets;
  }
  public void setQtdTweets(int qtdTweets) {
    this.qtdTweets = qtdTweets;
  }
  
  public void enviarRelatorio(Twitter twitter) throws TwitterException{
    String professorId = "";
    // descomentar o código abaixo quando tudo estiver ok
    // professorId = "@michelpf"
    twitter.updateStatus(professorId+" trabalho finalizado. - Tag: "+ hashtag+", qtd tweets: "+qtdTweets+" na ultima semana. Github: fabiotadashi/28scjTwitter");
  }
  
}
