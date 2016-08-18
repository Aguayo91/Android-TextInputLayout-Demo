package com.danielme.textinputlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

  private TextInputEditText editTextEmail;
  private TextInputEditText editTextPassword;
  private TextInputLayout textInputEmail;
  private TextInputLayout textInputPassword;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    editTextEmail = (TextInputEditText) findViewById(R.id.editTextEmail);
    editTextPassword = (TextInputEditText) findViewById(R.id.editTextPassword);
    textInputEmail = (TextInputLayout) findViewById(R.id.text_input_layout_email);
    textInputPassword = (TextInputLayout) findViewById(R.id.text_input_layout_pass);
  }

  public void validate(View view) {
    String mailError = null;
    if (TextUtils.isEmpty(editTextEmail.getText())) {
      mailError = getString(R.string.mandatory);
    }
    toggleTextInputLayoutError(textInputEmail, mailError);

    String passError = null;
    if (TextUtils.isEmpty(editTextPassword.getText())) {
      passError = getString(R.string.mandatory);
    }
    toggleTextInputLayoutError(textInputPassword, passError);

    clearFocus();
  }

  /**
   * Display/hides TextInputLayout error.
   *
   * @param msg the message, or null to hide
   */
  private static void toggleTextInputLayoutError(@NonNull TextInputLayout textInputLayout,
                                                String msg) {
    textInputLayout.setError(msg);
    if (msg == null) {
      textInputLayout.setErrorEnabled(false);
    } else {
      textInputLayout.setErrorEnabled(true);
    }
  }

  private void clearFocus() {
    View view = this.getCurrentFocus();
    if (view != null && view instanceof EditText) {
      InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context
          .INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
      view.clearFocus();
    }
  }

}
