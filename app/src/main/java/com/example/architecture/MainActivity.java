package com.example.architecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.architecture.viewmodels.DogRandomViewModel;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.image_view);
        mButton = findViewById(R.id.random_btn);

        DogRandomViewModel dogRandomViewModel = new DogRandomViewModel(getApplication());
        observeDogVM(dogRandomViewModel);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogRandomViewModel.fetchDogData();
            }
        });
    }
    public void observeDogVM(DogRandomViewModel vm) {
        vm.getDogRandomLiveData().observe(this, dogRandomResponse -> {
            if (dogRandomResponse.getStatus().equals("success")) {
                Picasso.get().load(dogRandomResponse.getMessage()).into(mImageView);
            }
        });

    }
}