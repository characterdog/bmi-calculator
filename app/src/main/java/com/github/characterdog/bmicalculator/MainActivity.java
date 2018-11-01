package com.github.characterdog.bmicalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String PREF_IS_METRIC = "system_of_unit";

    TextView txt_result_bmi;
    TextView txt_result_cat;
    TextView txt_result_pct;
    AutoCompleteTextView txt_height;
    AutoCompleteTextView txt_weight;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        txt_height = findViewById(R.id.txt_height);
        txt_weight = findViewById(R.id.txt_weight);


        initTextField(txt_height);
        initTextField(txt_weight);


        txt_result_bmi = findViewById(R.id.txt_result_bmi);
        txt_result_cat = findViewById(R.id.txt_result_cat);
        txt_result_pct = findViewById(R.id.txt_result_cat3);

        Button btn_more_info = findViewById(R.id.btn_more_info);
        btn_more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.wikipedia_bmi_link)));
                startActivity(browserIntent);
            }
        });

        setSystemOfUnits();
    }

    private void initTextField(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateBmiIfPossible();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @SuppressLint("StringFormatMatches")
    private void calculateBmiIfPossible() {
        if (isValidInput(txt_height) && isValidInput(txt_weight)) {
            double bmi = calculateBmiAndCastIfNeeded(getTextAsDouble(txt_height), getTextAsDouble(txt_weight));
            int percentile = 0;

            RadioButton btn_man = findViewById(R.id.man);
            RadioButton btn_woman = findViewById(R.id.woman);

            if(btn_man.isChecked()) {
                if(bmi<20.8){percentile=5;}
                else if(bmi>=20.8 && bmi <22.2){percentile=10;}
                else if(bmi>=22.2 && bmi <24.0){percentile=20;}
                else if(bmi>=24.0 && bmi <25.5){percentile=30;}
                else if(bmi>=25.5 && bmi <26.8){percentile=40;}
                else if(bmi>=26.8 && bmi <28.0){percentile=50;}
                else if(bmi>=28.0 && bmi <29.5){percentile=60;}
                else if(bmi>=29.5 && bmi <32.4){percentile=70;}
            }
            else if(btn_woman.isChecked()) {
                if(bmi<19.9){percentile=5;}
                else if(bmi>=19.9 && bmi <21.3){percentile=10;}
                else if(bmi>=21.3 && bmi <22.9){percentile=20;}
                else if(bmi>=22.9 && bmi <24.9){percentile=30;}
                else if(bmi>=24.9 && bmi <26.5){percentile=40;}
                else if(bmi>=26.5 && bmi <28.3){percentile=50;}
                else if(bmi>=28.3 && bmi <30.3){percentile=60;}
                else if(bmi>=30.3 && bmi <32.4){percentile=70;}
            }
            txt_result_pct.setText(percentile+" Percentile");
            txt_result_bmi.setText(getString(R.string.bmi_result, bmi));
            txt_result_cat.setText(getCategory(bmi));
        } else {
            txt_result_bmi.setText("");
            txt_result_cat.setText("");
            txt_result_pct.setText("");
        }
    }

    private boolean isValidInput(EditText editText) {
        return getTextAsDouble(editText) > 0;
    }

    private double getTextAsDouble(EditText editText) {
        String input = editText.getText().toString().replace(',', '.');
        try {
            return Double.valueOf(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double calculateBmiAndCastIfNeeded(double height, double weight) {
        height = isMetric() ? height : height / 39.37008;
        weight = isMetric() ? weight : weight / 2.204623;
        return calculateBmi(height, weight);
    }

    public static double calculateBmi(double height, double weight) {
        return Math.round(weight / Math.pow(height, 2) * 10d) / 10d;
    }

    private String getCategory(double bmi) {
        if (bmi < 15) {
            return getString(R.string.bmi_cat_1);
        }
        if (bmi < 16) {
            return getString(R.string.bmi_cat_2);
        }
        if (bmi < 18.5) {
            return getString(R.string.bmi_cat_3);
        }
        if (bmi < 25) {
            return getString(R.string.bmi_cat_4);
        }
        if (bmi < 30) {
            return getString(R.string.bmi_cat_5);
        }
        if (bmi < 35) {
            return getString(R.string.bmi_cat_6);
        }
        if (bmi < 40) {
            return getString(R.string.bmi_cat_7);
        }
        if (bmi < 45) {
            return getString(R.string.bmi_cat_8);
        }
        if (bmi < 50) {
            return getString(R.string.bmi_cat_9);
        }
        if (bmi < 60) {
            return getString(R.string.bmi_cat_10);
        }
        return getString(R.string.bmi_cat_11);
    }

    private void setSystemOfUnits() {
        RadioButton btn_metric = findViewById(R.id.btn_metric);
        RadioButton btn_imperial = findViewById(R.id.btn_imperial);
        btn_metric.setChecked(isMetric());
        btn_imperial.setChecked(!isMetric());

        TextInputLayout txt_weight_outer = findViewById(R.id.txt_weight_outer);
        TextInputLayout txt_height_outer = findViewById(R.id.txt_height_outer);
        txt_weight_outer.setHint(isMetric() ? getString(R.string.weight_metric) : getString(R.string.weight_imperial));
        txt_height_outer.setHint(isMetric() ? getString(R.string.height_metric) : getString(R.string.height_imperial));
    }

    private boolean isMetric() {
        boolean defaultToMetric = getString(R.string.default_unit).equals(getString(R.string.metric));
        return sharedPreferences.getBoolean(PREF_IS_METRIC, defaultToMetric);
    }

    public void setSystemOfUnits(View v) {
        sharedPreferences.edit().putBoolean(PREF_IS_METRIC, v.getId() == R.id.btn_metric).apply();
        setSystemOfUnits();
        calculateBmiIfPossible();
    }
}