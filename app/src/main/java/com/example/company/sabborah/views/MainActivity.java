package com.example.company.sabborah.views;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.company.sabborah.R;
import com.example.company.sabborah.presenters.UserPresenter;
import com.example.company.sabborah.presenters.UserView;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.services.ApiService.UserService;
import com.example.company.sabborah.models.User;
import com.example.company.sabborah.utils.MyAlertDialog;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @Email
    @NotEmpty
    @BindView(R.id.emailET)
    EditText emailET;
    @Password
    @BindView(R.id.passwordET)
    EditText passwordET;
    public AlertDialog alertDialog;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            user.setCountryId("1");
            UserPresenter.getInstance(UserService.getInstance(), this).login(user);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
