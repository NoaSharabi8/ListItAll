package dev.noash.shoplist;

import android.os.Bundle;
import android.widget.*;

import androidx.fragment.app.FragmentTransaction;

import dev.noash.common.*;

import java.util.ArrayList;

public class MainActivity extends MainActivity_parent {
    private EditText ET_pName;
    private EditText ET_category;
    private EditText ET_amount;
    private Button BTN_addProduct;
    private Item selectedItem;
    private ItemListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
        setupFragment();
    }

    private void findViews() {
        ET_pName = findViewById(R.id.ET_product_name);
        ET_category = findViewById(R.id.ET_product_category);
        ET_amount = findViewById(R.id.ET_product_amount);
        BTN_addProduct = findViewById(R.id.BTN_add_product);
    }
    private void initViews() {
        BTN_addProduct.setOnClickListener(v -> saveProduct());
    }
    private void saveProduct() {
        String name = ET_pName.getText().toString().trim();
        String category = ET_category.getText().toString().trim();
        String amount = ET_amount.getText().toString().trim();

        if (!name.isEmpty() && !category.isEmpty() && !amount.isEmpty()) {
            ArrayList<Item> currentItems = loadItems();
            currentItems.add(new Item(name, category, false, amount));
            saveItems(currentItems);
            listFragment.refresh();
            clearFields();
        } else {
            showToast("נא למלא את כל השדות");
        }
    }
    private void setupFragment() {
        listFragment = new ItemListFragment();
        listFragment.setInfoLabel("כמות");
        listFragment.setMarkedLabel("נקנה");
        listFragment.setCallBack(item -> setSelectedItem(item));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FL_products, listFragment);
        ft.commit();
    }
    private void clearFields() {
        ET_pName.setText("");
        ET_category.setText("");
        ET_amount.setText("");
        hideKeyboard();
    }
    private void setSelectedItem(Item item) {
        this.selectedItem = item;
    }
}
