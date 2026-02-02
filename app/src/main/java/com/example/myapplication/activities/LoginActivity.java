package com.example.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiClient;
import com.example.myapplication.api.WeatherApi;
import com.example.myapplication.models.api.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText email, surname, password, passwordConfirm;
    private Button buttonLogin;

    private TextView textAccount, textAccountClickable;

    private boolean isLogin = true;

    private final float HEIGHT_VIEW = 70f;
    private final int ANIMATE_DURATION = 700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Проверка связи с апишкой
        // Должна была быть наша, но за ее неимением берем популярную апишку погоды для проверки
        fetchWeather();

        email = findViewById(R.id.email);
        surname = findViewById(R.id.surname);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.password_confirm);

        textAccount = findViewById(R.id.text_account);
        textAccountClickable = findViewById(R.id.text_account_clickable);

        buttonLogin = findViewById(R.id.btn_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();

                if(isLogin){
                     if (!validateLogin(emailText, passwordText)) return;

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else{
                    String surnameText = surname.getText().toString();
                    String passwordConfirmText = passwordConfirm.getText().toString();

                    if (!validateRegister(emailText, passwordText, surnameText, passwordConfirmText)) return;

                    //
                }
            }
        });

        textAccountClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin){
                    animateView(surname, HEIGHT_VIEW, 1f, ANIMATE_DURATION);
                    animateView(password, HEIGHT_VIEW, 1f, ANIMATE_DURATION);
                    animateView(passwordConfirm, HEIGHT_VIEW * 2, 1f, ANIMATE_DURATION);
                    animateView(textAccount, HEIGHT_VIEW * 2, 1f, ANIMATE_DURATION);
                    animateView(textAccountClickable, HEIGHT_VIEW * 2, 1f, ANIMATE_DURATION);
                    animateView(buttonLogin, HEIGHT_VIEW * 2, 1f, ANIMATE_DURATION);

                    textAccount.setText("Уже есть аккаунт?");
                    textAccountClickable.setText("Войти");
                    buttonLogin.setText("Зарегистрироваться");
                    isLogin = false;
                }else{
                    animateView(surname, 0, 0f, ANIMATE_DURATION);
                    animateView(password, 0, 1f, ANIMATE_DURATION);
                    animateView(passwordConfirm, 0, 0f, ANIMATE_DURATION);
                    animateView(textAccount, 0, 1f, ANIMATE_DURATION);
                    animateView(textAccountClickable, 0, 1f, ANIMATE_DURATION);
                    animateView(buttonLogin, 0, 1f, ANIMATE_DURATION);

                    textAccount.setText("Еще нет аккаунта?");
                    textAccountClickable.setText("Зарегистрируйтесь!");
                    buttonLogin.setText("Войти");
                    isLogin = true;
                }
            }
        });

    }

    private void fetchWeather() {
        WeatherApi api = ApiClient.getClient().create(WeatherApi.class);

        Call<WeatherResponse> call = api.getWeather(
                "Moscow",
                "5630cd2c2ec38ff7a3cbc1d393954bee",
                "metric",
                "ru"
        );

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weather = response.body();
                    String result = weather.getName() + ": "
                            + weather.getMain().getTemp() + "°C, "
                            + weather.getWeather()[0].getDescription();
                    Log.d("WEATHER", result);
                } else {
                    Log.e("WEATHER", "Ошибка: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("WEATHER", "Ошибка сети", t);
            }
        });
    }

    private boolean validateLogin(String emailText, String passwordText) {
        emailText = emailText.trim();
        passwordText = passwordText.trim();

        if (emailText.isEmpty()) {
            email.setError("Введите email");
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError("Некорректный email");
            return false;
        }

        if (passwordText.isEmpty()) {
            password.setError("Введите пароль");
            return false;
        }

        if (passwordText.length() < 6) {
            password.setError("Пароль от 6 символов");
            return false;
        }

        return true;
    }

    private boolean validateRegister(String emailText, String passwordText, String surnameText, String passwordConfirmText) {
        if (!validateLogin(emailText, passwordText)) return false;

        surnameText = surnameText.trim();
        passwordConfirmText = passwordConfirmText.trim();
        passwordText = passwordText.trim();

        if (surnameText.isEmpty()) {
            surname.setError("Введите фамилию");
            return false;
        }

        if (passwordConfirmText.isEmpty()) {
            passwordConfirm.setError("Подтвердите пароль");
            return false;
        }

        if (!passwordConfirmText.equals(passwordText)) {
            passwordConfirm.setError("Пароли не совпадают");
            return false;
        }

        return true;
    }


    public static int dpToPx(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void animateView(View view, float dpTranslationY, float alpha, long duration) {
        if(alpha == 1f) {
            view.animate()
                    .translationY(dpToPx(view.getContext(), dpTranslationY))
                    .alpha(alpha)
                    .setDuration(duration)
                    .withStartAction(() -> view.setVisibility(View.VISIBLE))
                    .start();
        }else{
            view.animate()
                    .translationY(dpToPx(view.getContext(), dpTranslationY))
                    .alpha(alpha)
                    .setDuration(duration)
                    .withEndAction(() -> view.setVisibility(View.GONE))
                    .start();
        }
    }
}