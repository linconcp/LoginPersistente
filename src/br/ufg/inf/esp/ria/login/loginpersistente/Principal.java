package br.ufg.inf.esp.ria.login.loginpersistente;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.ufg.inf.esp.ria.modelo.EUsuario;
import br.ufg.inf.esp.ria.modelo.preferencias.ELogado_;
import br.ufg.inf.esp.ria.negocio.NUsuario;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.principal)
@NoTitle
@Fullscreen
public class Principal extends Activity {

  @Pref
  ELogado_ logado;

  @ViewById
  EditText entradaUsuario;

  @ViewById
  EditText entradaSenha;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    NUsuario nUsuario = new NUsuario(this);
    EUsuario eUsuario = new EUsuario();
    eUsuario.setIdentificacao("admin");

    if (nUsuario.consultar(eUsuario) == null) {
      eUsuario = new EUsuario();
      eUsuario.setId(1L);
      eUsuario.setNome("Administrador");
      eUsuario.setIdentificacao("admin");
      eUsuario.setSenha("admin");

      nUsuario.incluir(eUsuario);
    }
  }

  @Click(R.id.button1)
  public void entrar(View v) {
    if (!((entradaUsuario.getText() == null) || entradaUsuario.getText().toString().isEmpty()
        || (entradaSenha.getText() == null) || entradaSenha.getText().toString().isEmpty())) {

      EUsuario eUsuario = new EUsuario();
      eUsuario.setIdentificacao(entradaUsuario.getText().toString());
      eUsuario.setSenha(entradaSenha.getText().toString());

      NUsuario nUsuario = new NUsuario(this);

      if (nUsuario.validar(eUsuario)) {
        eUsuario = nUsuario.consultar(eUsuario);

        Bemvindo_.intent(this).usuario(eUsuario.getNome()).start();

        logado.identificacao().put(eUsuario.getIdentificacao());
      } else {
        Toast.makeText(getApplicationContext(), "Acesso não permitido!", Toast.LENGTH_SHORT).show();
      }
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    entradaUsuario.setText(logado.identificacao().get());
  }
}
