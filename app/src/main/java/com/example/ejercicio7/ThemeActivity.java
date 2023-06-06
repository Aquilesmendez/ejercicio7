package com.example.ejercicio7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ThemeActivity extends AppCompatActivity {

    private static final String THEME_PREFERENCES = "themePreferences";
    private static final String KEY_THEME_EJERCICIO7 = "themeEjercicio7";

    private boolean isThemeEjercicio7 = true;
    private SharedPreferences sharedPreferences;

    private ImageView imageView;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(THEME_PREFERENCES, MODE_PRIVATE);
        isThemeEjercicio7 = sharedPreferences.getBoolean(KEY_THEME_EJERCICIO7, true);
        setTheme(isThemeEjercicio7 ? R.style.Theme_Ejercicio7 : R.style.Theme_EjercicioDark);
        setContentView(R.layout.activity_theme);

        imageView = findViewById(R.id.imageView);
        btnSalir = findViewById(R.id.btnSalir);

        Switch switchTheme = findViewById(R.id.switchTheme);
        switchTheme.setChecked(isThemeEjercicio7);
        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isThemeEjercicio7 = isChecked;
                saveThemePreference(isThemeEjercicio7);
                applyTheme();
            }
        });

        showMessage("¡Bienvenido!");

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void applyTheme() {
        setTheme(isThemeEjercicio7 ? R.style.Theme_Ejercicio7 : R.style.Theme_EjercicioDark);
        recreate();
    }

    private void saveThemePreference(boolean isThemeEjercicio7) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_THEME_EJERCICIO7, isThemeEjercicio7);
        editor.apply();
    }

    private void showMessage(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
        // Opción alternativa: Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
