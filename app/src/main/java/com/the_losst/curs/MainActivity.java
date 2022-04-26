package com.the_losst.curs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static include_bd dataBase = null;
    private Button regButton = null;
    private EditText loginField = null;
    private EditText phoneField = null;
    private EditText pass0Field = null;
    private EditText pass1Field = null;
    private Toast toastMismatch = null;
    private  Toast toastErrorInFields = null;
    private Toast loginAlreadyUsed = null;
    private Toast youDied = null;
    private Toast sucReg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toastMismatch = Toast.makeText(this, "Пароли не совпадают!", Toast.LENGTH_LONG);
        toastErrorInFields = Toast.makeText(this, "Проверьте введенные данные!", Toast.LENGTH_LONG);
        loginAlreadyUsed = Toast.makeText(this, "Логин занят!", Toast.LENGTH_LONG);
        youDied = Toast.makeText(this, "Толик ***лик первый день, ***!", Toast.LENGTH_LONG);
        sucReg = Toast.makeText(this, "Регистрация успешна, пожалуйста войдите в свой аккаунт", Toast.LENGTH_LONG);
        toastMismatch.setGravity(Gravity.TOP, 0, 160);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        dataBase = new include_bd();
        regButton = findViewById(R.id.register_1);
        loginField = findViewById(R.id.login_field);
        phoneField = findViewById(R.id.phone_field);
        pass0Field = findViewById(R.id.password_field);
        pass1Field = findViewById(R.id.password_repeat_field);
        listeners();


    }

    private void listeners() {
        regButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (pass0Field.getText().equals(pass1Field.getText())) {
                    switch (dataBase.getInstance().registerUser(loginField.getText().toString(), pass0Field.getText().toString(), phoneField.getText().toString())) {
                        case 0:
                            sucReg.show();

                            break;
                        case -1:
                            toastErrorInFields.show();
                            break;
                        case -2:
                            loginAlreadyUsed.show();
                            break;
                        case -3:
                            youDied.show();
                    }
                } else {
                    toastMismatch.show();
                }
            }
        });
    }


}