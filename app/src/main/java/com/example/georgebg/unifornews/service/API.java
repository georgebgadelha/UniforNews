package com.example.georgebg.unifornews.service;

import com.example.georgebg.unifornews.bean.NoticiasBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface API {

    //Leitura
    @GET("noticias")
    Call<List<NoticiasBean>> listarNoticias();
}
