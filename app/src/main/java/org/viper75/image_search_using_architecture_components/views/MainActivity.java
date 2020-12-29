package org.viper75.image_search_using_architecture_components.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.viper75.image_search_using_architecture_components.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}