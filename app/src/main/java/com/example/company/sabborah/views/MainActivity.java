package com.example.company.sabborah.views;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.company.sabborah.R;
import com.example.company.sabborah.presenters.UserContract;
import com.example.company.sabborah.presenters.UserPresenter;
import com.example.company.sabborah.presenters.UserView;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.services.ApiService.UserService;
import com.example.company.sabborah.models.User;
import com.example.company.sabborah.utils.MyAlertDialog;
import com.example.company.sabborah.utils.MySharedPreferences;
import com.example.company.sabborah.utils.TokenUtil;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import butterknife.BindView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends BaseActivity implements UserContract.View {
    @Email
    @NotEmpty
    @BindView(R.id.emailET)
    EditText emailET;
    @Password
    @BindView(R.id.passwordET)
    EditText passwordET;
    private AlertDialog alertDialog;
    ProgressBar progressBar;
    UserPresenter userPresenter;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userPresenter = UserPresenter.getInstance();
        userPresenter.setView(this);
        alertDialog = MyAlertDialog.createAlertDialog(this, "");
    }

    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void login(View view) {
        validator.validate();
        if (validationSucceeded) {
            String email = emailET.getText().toString();
            String password = passwordET.getText().toString();
            User user = new User(email, password);
            userPresenter.login(user);
          //  startActivity(new Intent(this, SplashActivity.class));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(CommonResponse response) {
        String token=response.getResult().getToken();
        MySharedPreferences.getReference(this).setToken(token);
        startActivity(new Intent(this, SplashActivity.class));
    }

    @Override
    public void onFailure(CommonResponse commonResponse) {
        alertDialog.setMessage(commonResponse.getErrors().get(0));
        alertDialog.show();
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        pbLoading.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    protected void onDestroy() {
        userPresenter.unSubscribe();
        super.onDestroy();
    }
}
