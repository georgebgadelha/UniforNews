package com.example.georgebg.unifornews.view.activity;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.example.georgebg.unifornews.R;
import com.example.georgebg.unifornews.adapter.NoticiasAdapter;
import com.example.georgebg.unifornews.bean.NoticiasBean;
import com.example.georgebg.unifornews.event.NoticiaClicadaEvent;
import com.example.georgebg.unifornews.service.NoticiasService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

@EFragment(R.layout.fragment_geral)
public class GeralFragment extends Fragment {


    @ViewById(R.id.recyclerView_geral)
    protected RecyclerView recyclerView;

    @Bean
    protected NoticiasAdapter adapter;

    @Bean
    protected NoticiasService noticiasService;

    public GeralFragment() {
        // Required empty public constructor
    }


    @AfterViews
    public void afterViews(){
        recyclerView.setAdapter(adapter);
        noticiasService.requestBuscarNoticias();

    }

    @Subscribe
    public void onEvent(NoticiaClicadaEvent event){

        NoticiasBean noticiasBean = adapter.getItemAt(event.getPosition());
        DetalheNoticiaActivity_.intent(this).idNoticia(noticiasBean.getId()).start();
    }


    @Override
    public void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.refreshGeral();
    }

}
