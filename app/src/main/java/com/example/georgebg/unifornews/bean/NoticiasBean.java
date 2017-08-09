package com.example.georgebg.unifornews.bean;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NoticiasBean extends RealmObject {

    public static final String TIPO_NOTICIA_GERAL = "NOTICIA";
    public static final String TIPO_NOTICIA_EVENTO = "EVENTO";
    public static final String TIPO_NOTICIA_ESPORTIVO = "ESPORTIVO";
    public static final String TIPO_NOTICIA_FAVORITO = "FAVORITO";

    @PrimaryKey
    private Integer id;

    private String dataPublicacao;

    @SerializedName("urlImg")
    private String urlImagem;
    private String titulo;
    private String corpo;//(texto da noticia)
    private String resumo;
    private String tipo;
    private Integer registro;
    private Integer totalRegistros;

    public NoticiasBean(){

    }

//    public NoticiasBean(Integer id, String dataPublicacao, String urlImagem, String titulo, String corpo, String resumo, String tipo, Integer registro, Integer totalRegistros) {
//        this.id = id;
//        this.dataPublicacao = dataPublicacao;
//        this.urlImagem = urlImagem;
//        this.titulo = titulo;
//        this.corpo = corpo;
//        this.resumo = resumo;
//        this.tipo = tipo;
//        this.registro = registro;
//        this.totalRegistros = totalRegistros;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getRegistro() {
        return registro;
    }

    public void setRegistro(Integer registro) {
        this.registro = registro;
    }

    public Integer getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(Integer totalRegistros) {
        this.totalRegistros = totalRegistros;
    }


    //FUNÇÃO DE COPIAR OS PARÂMETROS

    public void Copy (NoticiasBean noticiasBean1, NoticiasBean noticiasBean2)
    {
        noticiasBean1.setTitulo(noticiasBean2.getTitulo());
        noticiasBean1.setCorpo(noticiasBean2.getCorpo());
        noticiasBean1.setRegistro(noticiasBean2.getRegistro());
        noticiasBean1.setDataPublicacao(noticiasBean2.getDataPublicacao());
        noticiasBean1.setResumo(noticiasBean2.getResumo());
        noticiasBean1.setTotalRegistros(noticiasBean2.getTotalRegistros());
        noticiasBean1.setUrlImagem(noticiasBean2.getUrlImagem());
    }

}
