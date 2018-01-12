package com.example.company.sabborah.views;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.company.sabborah.R;
import com.example.company.sabborah.presenters.UserPresenter;
import com.example.company.sabborah.presenters.UserView;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.services.ApiService.UserService;
import com.example.company.sabborah.models.User;
import com.example.company.sabborah.utils.MyAlertDialog;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

public class RegisterActivity extends BaseActivity {
    @NotEmpty
    private EditText nameET;
    @NotEmpty
    @Email
    private EditText emailET;
    @NotEmpty
    @Password
    private EditText passwordET;
    @NotEmpty
    @ConfirmPassword
    private EditText confirmPassworET;
    @NotEmpty
    private EditText mobileET;
    private RadioGroup type;
    private RadioButton tutorRadioButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderView();
    }

    private void renderView() {
        nameET = (EditText) findViewById(R.id.nameET);
        mobileET = (EditText) findViewById(R.id.mobileET);
        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        confirmPassworET = (EditText) findViewById(R.id.confirmPasswordET);
        type = (RadioGroup) findViewById(R.id.typeRadioGroup);
        tutorRadioButton = (RadioButton) findViewById(R.id.tutorRadio);

    }

    public void register(View view) {
        validator.validate();
        if (validationSucceeded) {
            String name = nameET.getText().toString();
            String mobile = mobileET.getText().toString();
            String email = emailET.getText().toString();
            String password = passwordET.getText().toString();
            String confirmPassword = confirmPassworET.getText().toString();
            boolean isTutor = false;
            if (tutorRadioButton.isChecked()) {
                isTutor = true;
            }
            User user = new User(name, mobile, email, password, isTutor);
            user.setCountryId("1");
            UserPresenter.getInstance(UserService.getInstance(), this).register(user);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
}
