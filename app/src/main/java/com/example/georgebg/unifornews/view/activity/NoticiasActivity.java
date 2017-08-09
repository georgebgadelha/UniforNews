package com.example.georgebg.unifornews.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.example.georgebg.unifornews.R;
import com.example.georgebg.unifornews.adapter.NoticiasAdapter;
import com.example.georgebg.unifornews.bean.NoticiasBean;
import com.example.georgebg.unifornews.event.RequestFinishEvent;
import com.example.georgebg.unifornews.service.NoticiasService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

@EActivity (R.layout.activity_noticias)
public class NoticiasActivity extends AppCompatActivity {


    @Bean
    NoticiasService noticiasService;

    @Bean
    NoticiasAdapter noticiasAdapter;

    @ViewById(R.id.navigation)
    protected  BottomNavigationView navigation;


//    //FRAGMENTS
//    private GeralFragment geralFragment;
//    private EsportesFragment esportesFragment;
//    private EventosFragment eventosFragment;
//    private FavoritosFragment favoritosFragment;




    protected BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.content, new GeralFragment_()).commit();
                    return true;
                case R.id.navigation_esportes:
                    transaction.replace(R.id.content, new EsportesFragment_()).commit();
                    return true;
                case R.id.navigation_eventos:
                    transaction.replace(R.id.content, new EventosFragment_()).commit();
                    return true;
                case R.id.navigation_favoritos:
                    transaction.replace(R.id.content, new FavoritosFragment_()).commit();
                    return true;
            }
            return false;
        }

    };



    @AfterViews
    public void afterViews(){

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        noticiasService.requestBuscarNoticias();

        for(NoticiasBean noticiasBean: noticiasService.getNoticias()){
            System.out.println(">>>>>>>>>>>>>>>>>>>>"+noticiasBean.getTipo()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
    }

    @Subscribe
    public void onEvent (RequestFinishEvent event){
        if (event.getMensagem() != null) {
            Snackbar.make(navigation, event.getMensagem(), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
