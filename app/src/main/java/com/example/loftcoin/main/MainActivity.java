package com.example.loftcoin.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.loftcoin.R;
import com.example.loftcoin.converter.ConverterFragment;
import com.example.loftcoin.rates.RatesFragment;
import com.example.loftcoin.util.Supplier;
import com.example.loftcoin.wallets.WalletsFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final SparseArrayCompat<Supplier<Fragment>> FRAGMENTS;

    static {
        FRAGMENTS = new SparseArrayCompat<>();
        FRAGMENTS.put(R.id.wallets, WalletsFragment::new);
        FRAGMENTS.put(R.id.rates, RatesFragment::new);
        FRAGMENTS.put(R.id.converter, ConverterFragment::new);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainViewModel viewModel = ViewModelProviders
                .of(this)
                .get(MainViewModel.class);

        setSupportActionBar(findViewById(R.id.toolbar));

        final BottomNavigationView navView = findViewById(R.id.bottom_nav);
        navView.setOnNavigationItemSelectedListener(menuItem -> {
            viewModel.submitSelectedId(menuItem.getItemId());
            return true;
        });

        viewModel.title().observe(this, title -> Objects
                .requireNonNull(getSupportActionBar())
                .setTitle(title));

        viewModel.selectedId().observe(this, this::replaceFragment);
        viewModel.selectedId().observe(this, navView::setSelectedItemId);
    }

    private void replaceFragment(int itemId) {
        final Supplier<Fragment> factory = FRAGMENTS.get(itemId);
        if (factory != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_host, factory.get())
                    .commit();
        }
    }

}
