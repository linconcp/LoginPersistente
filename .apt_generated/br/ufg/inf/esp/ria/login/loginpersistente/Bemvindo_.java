//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package br.ufg.inf.esp.ria.login.loginpersistente;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import br.ufg.inf.esp.ria.login.loginpersistente.R.id;
import br.ufg.inf.esp.ria.login.loginpersistente.R.layout;

public final class Bemvindo_
    extends Bemvindo
{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.bemvindo);
    }

    private void init_(Bundle savedInstanceState) {
        injectExtras_();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        downloadEmenta = DownloadEmenta_.getInstance_(this);
        listaRequerimentoAdapter = ListaRequerimentoAdapter_.getInstance_(this);
        downloadRequerimento = DownloadRequerimento_.getInstance_(this);
        listaEmentaAdapter = ListaEmentaAdapter_.getInstance_(this);
    }

    private void afterSetContentView_() {
        listaFavoritos = ((ListView) findViewById(id.listaFavoritos));
        tvBemvindo = ((TextView) findViewById(id.tvBemvindo));
        {
            View view = findViewById(id.consultarEmenta);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Bemvindo_.this.consultarEmenta(view);
                    }

                }
                );
            }
        }
        {
            View view = findViewById(id.consultarSolicitacao);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Bemvindo_.this.entrar(view);
                    }

                }
                );
            }
        }
        ((DownloadEmenta_) downloadEmenta).afterSetContentView_();
        ((ListaRequerimentoAdapter_) listaRequerimentoAdapter).afterSetContentView_();
        ((DownloadRequerimento_) downloadRequerimento).afterSetContentView_();
        ((ListaEmentaAdapter_) listaEmentaAdapter).afterSetContentView_();
        iniciar();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view, android.view.ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        afterSetContentView_();
    }

    public static Bemvindo_.IntentBuilder_ intent(Context context) {
        return new Bemvindo_.IntentBuilder_(context);
    }

    @SuppressWarnings("unchecked")
    private<T >T cast_(Object object) {
        return ((T) object);
    }

    private void injectExtras_() {
        Intent intent_ = getIntent();
        Bundle extras_ = intent_.getExtras();
        if (extras_!= null) {
            if (extras_.containsKey("usuario")) {
                try {
                    usuario = cast_(extras_.get("usuario"));
                } catch (ClassCastException e) {
                    Log.e("Bemvindo_", "Could not cast extra to expected type, the field is left to its default value", e);
                }
            }
        }
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        injectExtras_();
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, Bemvindo_.class);
        }

        public Intent get() {
            return intent_;
        }

        public Bemvindo_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (context_ instanceof Activity) {
                ((Activity) context_).startActivityForResult(intent_, requestCode);
            } else {
                context_.startActivity(intent_);
            }
        }

        public Bemvindo_.IntentBuilder_ usuario(String usuario) {
            intent_.putExtra("usuario", usuario);
            return this;
        }

    }

}
