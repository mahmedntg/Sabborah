package com.example.company.sabborah.views;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.company.sabborah.R;
import com.example.company.sabborah.models.Country;
import com.example.company.sabborah.presenters.CommonContract;
import com.example.company.sabborah.presenters.CommonPresenter;
import com.example.company.sabborah.presenters.UserContract;
import com.example.company.sabborah.presenters.UserPresenter;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.responses.CountryResponse;
import com.example.company.sabborah.services.ApiService.UserService;
import com.example.company.sabborah.models.User;
import com.example.company.sabborah.utils.MyAlertDialog;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class RegisterActivity extends BaseActivity implements CommonContract.View, UserContract.View {
    CommonPresenter commonPresenter;
    UserPresenter userPresenter;
    @NotEmpty
    @BindView(R.id.nameET)
    EditText nameET;
    @NotEmpty
    @Email
    @BindView(R.id.emailET)
    EditText emailET;
    @NotEmpty
    @Password
    @BindView(R.id.passwordET)
    EditText passwordET;
    @NotEmpty
    @ConfirmPassword
    @BindView(R.id.confirmPasswordET)
    EditText confirmPassworET;
    @NotEmpty
    @BindView(R.id.mobileET)
    EditText mobileET;
    @BindView(R.id.typeRadioGroup)
    RadioGroup type;
    @BindView(R.id.tutorRadio)
    RadioButton tutorRadioButton;
    @BindView(R.id.countrySpinnerId)
    Spinner countrySpinner;
    private List<CountryResponse> countries;
    private Integer countryId;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    private AlertDialog alertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateSpinner();
        commonPresenter = CommonPresenter.getInstance();
        commonPresenter.setView(this);
        userPresenter = UserPresenter.getInstance();
        userPresenter.setView(this);
        alertDialog = MyAlertDialog.createAlertDialog(this, "");
    }

    private void populateSpinner() {
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getSelectedItem();
                countryId = country.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
            User user = new User(name, mobile, email, password, isTutor, countryId.toString());
            userPresenter.register(user);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }


    @Override
    protected void onDestroy() {
        commonPresenter.unSubscribe();
        userPresenter.unSubscribe();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        commonPresenter.getCountries();
    }


    @Override
    public void initializeCountryList(List<Country> countries) {
        ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(this, android.R.layout.simple_spinner_dropdown_item, countries);
        countrySpinner.setAdapter(adapter);
    }

    @Override
    public void onSuccess(CommonResponse response) {
        alertDialog.setMessage(response.getMessage());
        alertDialog.show();
    }

    @Override
    public void onFailure(CommonResponse response) {
        alertDialog.setMessage(response.getMessage());
        alertDialog.show();
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        pbLoading.setVisibility(isVisible ? VISIBLE : GONE);
    }

}
