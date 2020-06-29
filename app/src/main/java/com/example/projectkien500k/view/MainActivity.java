package com.example.projectkien500k.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.projectkien500k.R;
import com.example.projectkien500k.databinding.ActivityMainBinding;
import com.example.projectkien500k.model.data.Address;
import com.example.projectkien500k.model.data.Bill;
import com.example.projectkien500k.model.data.Client;
import com.example.projectkien500k.model.data.DetailBill;
import com.example.projectkien500k.model.response.AddressResponse;
import com.example.projectkien500k.model.response.BillDetailResponse;
import com.example.projectkien500k.model.response.BillResponse;
import com.example.projectkien500k.model.viewmodel.addressViewModel;
import com.example.projectkien500k.model.viewmodel.billViewModel;
import com.example.projectkien500k.model.viewmodel.clientViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    billViewModel billViewModel;
    addressViewModel addressViewModel;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        billViewModel = new ViewModelProvider(this).get(billViewModel.class);
        addressViewModel = new ViewModelProvider(this).get(addressViewModel.class);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                onDistinationChangedControll(controller, destination, arguments);
            }
        });
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()) {
                    case R.id.navigation_profile:

                    default:
                        break;
                }
            }
        });

        //       NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    public void setTitle(String title) {
        binding.toolbar.setTitle(title);
    }

    public void onDistinationChangedControll(NavController controller, NavDestination destination, Bundle arguments) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cart:
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                return true;
            case R.id.menu_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // chỉ định đâu là host
    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Client event) {
        client = event;
        loadBill(event.getId(), "b1"); // load danh sach bill vs detail bill
        loadAddress(client.getId());
    }

    private void loadAddress(Integer id) {
        addressViewModel.getAddress(id).observe(MainActivity.this, new Observer<AddressResponse>() {
            @Override
            public void onChanged(AddressResponse addressResponse) {
                if (addressResponse.getStatus().equals("SUCCESS")) {
                    List<Address> list = addressResponse.getData();
                    EventBus.getDefault().postSticky(list);
                }
            }
        });
    }

    private void loadBill(int id_client, String stt) {
        billViewModel.loadBill(id_client, stt).observe(MainActivity.this, new Observer<BillResponse>() {
            @Override
            public void onChanged(BillResponse billResponse) {
                if (billResponse.getStatus().equals("SUCCESS")) {
                    List<Bill> bills = billResponse.getData();
                    EventBus.getDefault().postSticky(bills.get(0));
                    loadDetailbills(bills.get(0).getIdBill());
                }else{
                    List<Bill> bills=new ArrayList<>();
                    EventBus.getDefault().postSticky(bills);
                }
            }
        });
    }

    private void loadDetailbills(Integer idBill) {
        billViewModel.loadDetailBill(idBill).observe(this, new Observer<BillDetailResponse>() {
            @Override
            public void onChanged(BillDetailResponse billDetailResponse) {
                if (billDetailResponse.getStatus().equals("SUCCESS")) {
                    List<DetailBill> listDetail = billDetailResponse.getData();
                    EventBus.getDefault().postSticky(listDetail);
                }

            }
        });
    }
}
