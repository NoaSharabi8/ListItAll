package dev.noash.todolist;

import android.os.Bundle;
import android.widget.*;

import androidx.fragment.app.FragmentTransaction;

import dev.noash.common.*;

import java.util.ArrayList;

public class MainActivity extends MainActivity_parent {
    private EditText ET_tName;
    private EditText ET_category;
    private EditText ET_urgency;
    private Button BTN_addTask;
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
        ET_tName = findViewById(R.id.ET_task_name);
        ET_category = findViewById(R.id.ET_task_category);
        ET_urgency = findViewById(R.id.ET_task_urgency);
        BTN_addTask = findViewById(R.id.BTN_add_task);
    }
    private void initViews() {
        BTN_addTask.setOnClickListener(v -> saveTask());
    }
    private void saveTask() {
        String name = ET_tName.getText().toString().trim();
        String category = ET_category.getText().toString().trim();
        String amount = ET_urgency.getText().toString().trim();

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
        listFragment.setInfoLabel("דחיפות");
        listFragment.setMarkedLabel("בוצע");
        listFragment.setCallBack(item -> setSelectedItem(item));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FL_tasks, listFragment);
        ft.commit();
    }
    private void clearFields() {
        ET_tName.setText("");
        ET_category.setText("");
        ET_urgency.setText("");
        hideKeyboard();
    }
    private void setSelectedItem(Item item) {
        this.selectedItem = item;
    }
}
