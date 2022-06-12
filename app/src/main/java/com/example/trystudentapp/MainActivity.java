package com.example.trystudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.trystudentapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding binding;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationview;

    //auto slider

//    SliderView sliderView;
//    int[] images = {R.drawable.coep2,R.drawable.impress,R.dr}

    SliderView sliderView;
    int[] images = {
            R.drawable.impress,R.drawable.psf,R.drawable.mind,R.drawable.coep2,R.drawable.zest};

    //    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());


        drawerLayout = findViewById(R.id.Drawer_layout);
        navigationview = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationview.setNavigationItemSelectedListener(this);


        sliderView = findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
        sliderView.startAutoCycle();


        binding.bottomNavigationView2.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.notice:
                    replaceFragment(new NoticeFragment());
                    break;

                case R.id.about:
                    replaceFragment(new AboutFragment());
                    break;
                case R.id.gallery:
                    replaceFragment(new GalleryFragment());
                    break;
                case R.id.ebook:
                    replaceFragment(new EbookFragment());
                    break;
            }


            return true;
        });


    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.home_menu:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dashboard_menu:
                Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show();
                break;

            case R.id.courses_menu:
                Toast.makeText(this, "course", Toast.LENGTH_SHORT).show();
                break;

            case R.id.login_menu:
                Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout_menu:
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_menu:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.refer_menu:
                Toast.makeText(this, "refer", Toast.LENGTH_SHORT).show();
                break;



        }
        return true;
    }
}