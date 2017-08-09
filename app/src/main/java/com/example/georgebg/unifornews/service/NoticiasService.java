package com.example.georgebg.unifornews.service;

import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.georgebg.unifornews.bean.NoticiasBean;
import com.example.georgebg.unifornews.event.RequestBegunEvent;
import com.example.georgebg.unifornews.event.RequestFinishEvent;

@EBean
public class NoticiasService {

    private static API api;

    private static String TAG;


    public NoticiasService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo6508156.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

    }


    public List<NoticiasBean> getNoticias() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<NoticiasBean> noticiasBean = realm.where(NoticiasBean.class)
                .equalTo("tipo", NoticiasBean.TIPO_NOTICIA_GERAL)
                .findAll();

        if (noticiasBean != null) {
            return noticiasBean;
        } else {
            return new ArrayList<>();
        }

    }

    public List<NoticiasBean> getNoticiasEventos() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<NoticiasBean> noticiasBean = realm.where(NoticiasBean.class)
                .equalTo("tipo", NoticiasBean.TIPO_NOTICIA_EVENTO)
                .findAll();

        if (noticiasBean != null) {
            return realm.copyFromRealm(noticiasBean);
        } else {
            return new ArrayList<>();
        }
    }

    public List<NoticiasBean> getNoticiasEsportes() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<NoticiasBean> noticiasBean = realm.where(NoticiasBean.class)
                .equalTo("tipo", NoticiasBean.TIPO_NOTICIA_ESPORTIVO)
                .findAll();

        if (noticiasBean != null) {
            return realm.copyFromRealm(noticiasBean);
        } else {
            return new ArrayList<>();
        }
    }

    public List<NoticiasBean> getNoticiasFavoritos() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<NoticiasBean> noticiasBean = realm.where(NoticiasBean.class)
                .equalTo("tipo", NoticiasBean.TIPO_NOTICIA_FAVORITO)
                .findAll();

        if (noticiasBean != null) {
            return realm.copyFromRealm(noticiasBean);
        } else {
            return new ArrayList<>();
        }
    }

    public NoticiasBean getNoticiasById(Integer idNoticia) {
        Realm realm = Realm.getDefaultInstance();

        NoticiasBean noticias = realm.where(NoticiasBean.class)
                .equalTo("id", idNoticia)
                .findFirst();

        if (noticias != null) {
            return noticias;
        } else {
            return new NoticiasBean();
        }

    }

    public void salvaNoticia(NoticiasBean noticiasBean) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(noticiasBean);
        realm.commitTransaction();
    }

    public void salvaNoticia(List<NoticiasBean> noticiasBean) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(noticiasBean);
        realm.commitTransaction();
    }


    public void requestBuscarNoticias() {


        api.listarNoticias().enqueue(new Callback<List<NoticiasBean>>() {

            @Override
            public void onResponse(Call<List<NoticiasBean>> call, Response<List<NoticiasBean>> response) {

                if (response.code() == 200) {
                    List<NoticiasBean> noticiasBean = response.body();
                    salvaNoticia(noticiasBean);
                    Log.d(TAG, "Deu certo!");

                } else {
                    EventBus.getDefault().post(new RequestFinishEvent("Não foi 200"));
                }
            }

            @Override
            public void onFailure(Call<List<NoticiasBean>> call, Throwable t) {
                EventBus.getDefault().post(new RequestFinishEvent("Falta na requisição"));
            }
        });

    }
//    public void addFavoritos(NoticiasBean noticiasBean) {
//
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//
//        NoticiasBean noticiasBeanCopia = realm.copyFromRealm(noticiasBean);
//        Number max = realm.where(NoticiasBean.class).max("id");
//        Integer nextId = 0;
//        nextId = max.intValue() + 1;
//        noticiasBeanCopia.setId(nextId);
//        noticiasBeanCopia.setTipo(NoticiasBean.TIPO_NOTICIA_FAVORITO);
//
//        System.out.println("ID=" + noticiasBeanCopia.getId() + "TIPO=" + noticiasBeanCopia.getTipo());
//        realm.copyToRealmOrUpdate(noticiasBeanCopia);
//        realm.commitTransaction();
//
//    }

    public int addFavoritos(NoticiasBean noticia) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        NoticiasBean noticia_teste = realm.where(NoticiasBean.class)
                .equalTo("titulo", noticia.getTitulo())
                .equalTo("tipo", NoticiasBean.TIPO_NOTICIA_FAVORITO)
                .findFirst();


        if (noticia_teste != null) {//a noticia já esta nos favoritos, então vou excluir

            realm.commitTransaction();
            RemoverFavorito(noticia_teste.getId());
            return 1;

        } else {//a noticia não esta nos favoritos, então vou adcionar


//            if(noticia_teste.getTipo() == NoticiasBean.TIPO_NOTICIA_FAVORITO) {

            Number max = realm.where(NoticiasBean.class).max("id");
            Integer nextId = 0;
            nextId = max.intValue() + 1;

            noticia_teste = new NoticiasBean();
            noticia_teste.setId(nextId);
            noticia_teste.setTipo(NoticiasBean.TIPO_NOTICIA_FAVORITO);
            noticia_teste.setTitulo(noticia.getTitulo());
            noticia_teste.setCorpo(noticia.getCorpo());
            noticia_teste.setRegistro(noticia.getRegistro());
            noticia_teste.setDataPublicacao(noticia.getDataPublicacao());
            noticia_teste.setResumo(noticia.getResumo());
            noticia_teste.setTotalRegistros(noticia.getTotalRegistros());
            noticia_teste.setUrlImagem(noticia.getUrlImagem());
//              noticia_teste.Copy(noticia_teste, noticia);
            realm.copyToRealmOrUpdate(noticia_teste);
            realm.commitTransaction();
            return 0;
//            }
//            else
//            {
//                realm.commitTransaction();
//                return 2;
//            }
        }
    }

    public void Favoritar(int idNoticia) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        NoticiasBean noticiasBean = realm.where(NoticiasBean.class)
                .equalTo("id", idNoticia)
                .findFirst();

        NoticiasBean noticiasBeanCopia = realm.copyFromRealm(noticiasBean);
        Number maximo = realm.where(NoticiasBean.class).max("id");
        Integer proxId = 0;
        proxId = maximo.intValue() + 1;
        noticiasBeanCopia.setId(proxId);
        noticiasBeanCopia.setTipo("FAVORITO");
        realm.copyToRealmOrUpdate(noticiasBeanCopia);
        realm.commitTransaction();
    }
//    public void Desfavoritar (int idNoticia){
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        NoticiasBean noticiasBean = realm.where(NoticiasBean.class)
//                .equalTo("id", idNoticia)
//                .findFirst();
//        noticiasBean.deleteFromRealm();
//        realm.commitTransaction();
//    }

    public void RemoverFavorito(Integer id) {
        Log.i("RA", "Entrou no metodo");
        Realm realm = Realm.getDefaultInstance();

        NoticiasBean noticiaApagar = realm
                .where(NoticiasBean.class)
                .equalTo("id", id)
//                .equalTo("tipo", NoticiasBean.TIPO_NOTICIA_FAVORITO)
                .findFirst();

        realm.beginTransaction();
        noticiaApagar.deleteFromRealm();
        realm.commitTransaction();
    }
}
