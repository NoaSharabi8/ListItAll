package dev.noash.common;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ItemListFragment extends Fragment {

    private ListView LV_itemList;
    private ArrayAdapter<Item> adapter;
    private CallBack_ItemClicked callBack_SendClick;
    private ArrayList<Item> items;
    private int selectedPosition = -1;
    private String infoLabel = "מידע";
    private String markedLabel = "סימון";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        LV_itemList = view.findViewById(R.id.LV_items);
        initListView();
        return view;
    }

    private void initListView() {
        items = SPManager.loadItems(requireContext());

        adapter = new ArrayAdapter<Item>(
                requireContext(),
                R.layout.item_layout,
                R.id.TV_name,
                items
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Item item = getItem(position);
                if (item == null) return view;

                TextView name = view.findViewById(R.id.TV_name);
                TextView category = view.findViewById(R.id.TV_category);
                TextView info = view.findViewById(R.id.TV_info);
                CheckBox isMarked = view.findViewById(R.id.CB_is_marked);

                name.setText(item.getName());
                category.setText(item.getCategory());
                info.setText(infoLabel + ": " + item.getInfo());
                isMarked.setText(markedLabel);
                isMarked.setChecked(item.isMarked());

                isMarked.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    item.setMarked(isChecked);
                    SPManager.saveItems(requireContext(), items);
                });

                view.setBackgroundColor(
                        position == selectedPosition ? Color.parseColor("#F6CBDB") : Color.TRANSPARENT
                );

                return view;
            }
        };

        LV_itemList.setAdapter(adapter);

        LV_itemList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            Item item = adapter.getItem(position);
            if (item != null && callBack_SendClick != null) {
                callBack_SendClick.userClicked(item);
            }
            adapter.notifyDataSetChanged();
        });
    }


    public void setCallBack(CallBack_ItemClicked callBack_SendClick) {
        this.callBack_SendClick = callBack_SendClick;
    }

    public void refresh() {
        if (adapter != null && items != null) {
            items.clear();
            items.addAll(SPManager.loadItems(requireContext()));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }
    public void setInfoLabel(String label) {
        this.infoLabel = label;
    }
    public void setMarkedLabel(String label) {
        this.markedLabel = label;
    }
}
