package com.example.company.sabborah.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.company.sabborah.R;
import com.example.company.sabborah.models.Country;
import com.example.company.sabborah.presenters.CommonContract;
import com.example.company.sabborah.presenters.UserContract;
import com.example.company.sabborah.presenters.UserView;
import com.example.company.sabborah.responses.CommonResponse;
import com.example.company.sabborah.utils.MyAlertDialog;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Mohamed Sayed on 1/8/2018.
 */

public class BaseActivity extends AppCompatActivity implements Validator.ValidationListener {
    private ProgressBar progressBar;
    private AlertDialog alertDialog;
    protected boolean validationSucceeded;
    protected Validator validator;
    private RelativeLayout relativeLayout;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.progressTitle));
        validator = new Validator(this);
        validator.setValidationListener(this);
        relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.progress_bar, null);
        //progressBar=relativeLayout
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, // Width in pixels
                ViewGroup.LayoutParams.MATCH_PARENT // Height of progress bar
        );
        relativeLayout.setLayoutParams(lp);
        addContentView(relativeLayout, relativeLayout.getLayoutParams());
        alertDialog = MyAlertDialog.createAlertDialog(this, getString(R.string.register_validation));
    }


    @Override
    public void onValidationSucceeded() {
        validationSucceeded = true;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        validationSucceeded = false;
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
