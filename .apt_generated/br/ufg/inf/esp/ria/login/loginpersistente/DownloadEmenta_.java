//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package br.ufg.inf.esp.ria.login.loginpersistente;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public final class DownloadEmenta_
    extends DownloadEmenta
{

    private Context context_;
    private Handler handler_ = new Handler();

    private DownloadEmenta_(Context context) {
        context_ = context;
        init_();
    }

    public void afterSetContentView_() {
        if (!(context_ instanceof Activity)) {
            return ;
        }
    }

    /**
     * You should check that context is an activity before calling this method
     * 
     */
    public View findViewById(int id) {
        Activity activity_ = ((Activity) context_);
        return activity_.findViewById(id);
    }

    @SuppressWarnings("all")
    private void init_() {
        if (context_ instanceof Activity) {
            Activity activity = ((Activity) context_);
        }
    }

    public static DownloadEmenta_ getInstance_(Context context) {
        return new DownloadEmenta_(context);
    }

    public void rebind(Context context) {
        context_ = context;
        init_();
    }

    @Override
    public void execute(final Bemvindo minhaLista) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    DownloadEmenta_.super.execute(minhaLista);
                } catch (RuntimeException e) {
                    Log.e("DownloadEmenta_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

}
