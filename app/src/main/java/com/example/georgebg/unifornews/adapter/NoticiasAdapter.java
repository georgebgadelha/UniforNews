package com.example.georgebg.unifornews.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.georgebg.unifornews.R;
import com.example.georgebg.unifornews.bean.NoticiasBean;
import com.example.georgebg.unifornews.service.NoticiasService;
import com.example.georgebg.unifornews.view.itemview.ItemNoticias;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

@EBean
public class NoticiasAdapter extends RecyclerView.Adapter<ItemNoticias> {

    private List<NoticiasBean> itens = new ArrayList<NoticiasBean>();

//    private String tipo;

    @Bean
    protected NoticiasService noticiasService;


    @Override
    public ItemNoticias onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noticia_item, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ItemNoticias vh = new ItemNoticias(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ItemNoticias holder, int position) {
        holder.bind(itens.get(position), position);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }


    public NoticiasBean getItemAt(int position){
        return itens.get(position);
    }

//    public void setTipo (String tipo) { this.tipo = tipo; }

    @AfterInject
    public void refreshGeral(){

        itens = noticiasService.getNoticias();
        notifyDataSetChanged();
    }
    @AfterInject
    public void refreshEventos(){

        itens = noticiasService.getNoticiasEventos();
        notifyDataSetChanged();
    }
    @AfterInject
    public void refreshEsportes(){

        itens = noticiasService.getNoticiasEsportes();
        notifyDataSetChanged();
    }
    @AfterInject
    public void refreshFavoritos(){

        itens = noticiasService.getNoticiasFavoritos();
        notifyDataSetChanged();
    }

}
