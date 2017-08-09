package com.example.georgebg.unifornews.view.itemview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georgebg.unifornews.R;
import com.example.georgebg.unifornews.bean.NoticiasBean;
import com.example.georgebg.unifornews.event.NoticiaClicadaEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

public class ItemNoticias extends RecyclerView.ViewHolder{

    //ATRIBUTOS


    //Imagem
    private ImageView imageView;

    //Texto
    private TextView titulo;


    //MÉTODOS
    public ItemNoticias (View itemView) {
        super(itemView);

        //Imagem da Notícia
        imageView = (ImageView) itemView.findViewById( R.id.image_view);

        //Texto da Notícia
        titulo = (TextView) itemView.findViewById(R.id.info_text);

    }

    public void bind(NoticiasBean noticiasBean, final int position){

        Picasso.with(itemView.getContext()).load(noticiasBean.getUrlImagem()).into(imageView);

        titulo.setText(String.valueOf(noticiasBean.getResumo()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new NoticiaClicadaEvent(position));
            }
        });



    }




}
