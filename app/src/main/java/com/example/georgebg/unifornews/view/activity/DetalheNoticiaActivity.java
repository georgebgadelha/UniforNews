package com.example.georgebg.unifornews.view.activity;

import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import com.example.georgebg.unifornews.R;
import com.example.georgebg.unifornews.bean.NoticiasBean;
import com.example.georgebg.unifornews.service.NoticiasService;

import java.util.Objects;

@EActivity(R.layout.activity_detalhe_noticia)
public class DetalheNoticiaActivity extends AppCompatActivity {

    @ViewById(R.id.img_view_detalhe)
    protected ImageView imgViewDetalhe;

    @ViewById(R.id.txt_view_corpo)
    protected TextView txtViewCorpo;

    @ViewById(R.id.txt_view_detalhe)
    protected TextView txtViewTitulo;

    @Bean
    protected NoticiasService noticiasService;

    private NoticiasBean noticiasBean;

    @Extra
    protected Integer idNoticia;

    @AfterViews
    public void afterViews(){


        NoticiasBean noticiasBean_detalhe = noticiasService.getNoticiasById(idNoticia);

        Picasso.with(imgViewDetalhe.getContext())
                .load(noticiasBean_detalhe.getUrlImagem())
                .resize(2000, 1000)
                .into(imgViewDetalhe);

        txtViewTitulo.setText(noticiasBean_detalhe.getTitulo());

        Spanned spanned = stripHtml(noticiasBean_detalhe.getCorpo());

        txtViewCorpo.setText(spanned);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalhe_noticia, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.favoritar:
                Favoritar();
                invalidateOptionsMenu();
                break;
            default:
                Log.d("Return: ", "Eu não conheço esse Menu");
                return false;
        }
        return false;
    }

    private void Favoritar() {



        if(noticiasService.addFavoritos(noticiasService.getNoticiasById(idNoticia)) == 1){
//            noticiasService.addFavoritos(noticiasService.getNoticiasById(idNoticia));
            Snackbar.make(txtViewTitulo, "Notícia removida dos favoritos", Snackbar.LENGTH_LONG).show();
        }else{
//            if(noticiasService.addFavoritos(noticiasService.getNoticiasById(idNoticia)) == 0){

//                noticiasService.RemoverFavorito(idNoticia);
                Snackbar.make(txtViewTitulo, "Notícia adicionada aos favoritos", Snackbar.LENGTH_LONG).show();

//            }
//            else
//            {
//                Snackbar.make(txtViewTitulo, "Noticia ja adicionada aos favoritos", Snackbar.LENGTH_SHORT).show();
//            }

        }

    }

    private Spanned stripHtml(String html){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else{
            return Html.fromHtml(html);
        }
    }

}
