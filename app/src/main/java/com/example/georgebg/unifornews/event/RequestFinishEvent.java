package com.example.georgebg.unifornews.event;

//ESCONDER O DIALOG MOSTRADO
public class RequestFinishEvent {

    //ATRIBUTOS

    private String mensagem;

    //CONSTRUTOR (VAZIO)

    public RequestFinishEvent() {

    }

    //CONSTRUTOR RECEBENDO STRING

    public RequestFinishEvent(String mensagem) {
        this.mensagem = mensagem;
    }



    //GETTERS
    public String getMensagem() {
        return mensagem;
    }

    //SETTERS
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
