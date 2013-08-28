package br.ufg.inf.esp.ria.login.loginpersistente;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.ufg.inf.esp.ria.login.dao.DataManager;
import br.ufg.inf.esp.ria.modelo.ELogado_;
import br.ufg.inf.esp.ria.modelo.EUsuario;
import br.ufg.inf.esp.ria.negocio.NUsuario;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.principal)
@Fullscreen
@NoTitle
public class Principal extends Activity {

  // private static final String CAMPO_USUARIO = "usuario";
  @Pref
  ELogado_ logado;

  @ViewById
  EditText entradaUsuario;

  @ViewById
  EditText entradaSenha;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // setContentView(R.layout.activity_main);
    // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    // requestWindowFeature(Window.FEATURE_NO_TITLE);
    // entradaUsuario = (EditText) findViewById(R.id.entrada_usuario);
    // entradaSenha = (EditText) findViewById(R.id.entrada_senha);
    NUsuario nUsuario = new NUsuario(DataManager.getDatabase(this));
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

      NUsuario nUsuario = new NUsuario(DataManager.getDatabase(this));

      if (nUsuario.validar(eUsuario)) {
        // Intent intencao = new Intent(this, Bemvindo_.class);
        // intencao.putExtra("usuario", entradaUsuario.getText().toString());

        eUsuario = nUsuario.consultar(eUsuario);

        Bemvindo_.intent(this).usuario(eUsuario.getNome()).start();

        logado.identificacao().put(eUsuario.getIdentificacao());
        // SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        // Editor editor = sharedPreferences.edit();
        // editor.putString(CAMPO_USUARIO, entradaUsuario.getText().toString());
        // editor.commit();
      } else {
        Toast.makeText(getApplicationContext(), "Acesso não permitido!", Toast.LENGTH_SHORT).show();
      }
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    // SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
    // String usuarioSalvo = sharedPreferences.getString(CAMPO_USUARIO, "");
    //
    // entradaUsuario.setText(usuarioSalvo);
    entradaUsuario.setText(logado.identificacao().get());

  }

}
