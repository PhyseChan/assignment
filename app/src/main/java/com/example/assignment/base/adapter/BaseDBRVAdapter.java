package com.example.assignment.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Recyclerview adapter combined with databinding(结合dataBinding的RecyclerView Adapter)
 */
public abstract class BaseDBRVAdapter<Data, DB extends ViewDataBinding> extends RecyclerView.Adapter<BaseDBRVHolder> {

    private List<Data> data;
    private int itemId;
    protected Context context;
    protected int variableId;
    protected OnItemClickListener<Data> listener;


    public BaseDBRVAdapter(@LayoutRes int itemId, int variableId) {
        this.itemId = itemId;
        this.variableId = variableId;
        data = new ArrayList<>();
    }

    public BaseDBRVAdapter(List<Data> data, @LayoutRes int itemId, int variableId) {
        this.data = data == null ? new ArrayList<Data>() : data;
        this.itemId = itemId;
        this.variableId = variableId;
    }


    @NonNull
    @Override
    public BaseDBRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        DB binding = DataBindingUtil.inflate(inflater, itemId, parent, false);
        return new BaseDBRVHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseDBRVHolder holder, final int position) {
        DB binding = DataBindingUtil.getBinding(holder.itemView);
        final Data itemData = data.get(position);
        binding.setVariable(variableId, itemData);
        onBindViewHolder(itemData, binding, position);
        //Force data to bind immediately迫使数据立即绑定
        binding.executePendingBindings();
        //Set click events设置点击事件
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(itemData, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return listener.onItemLongClick(itemData, position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Binding data绑定数据
     */
    protected void onBindViewHolder(Data data, DB binding, int position) {
    }

    /**
     * Set up new data设置新数据
     *
     * @param data
     */
    public void setNewData(List<Data> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * add data添加数据
     *
     * @param data
     */
    public void addData(Data data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    /**
     * add data添加数据
     *
     * @param data
     */
    public void addData(List<Data> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * Set item,long press and click events 设置Item 长按、点击事件
     */
    public void setOnItemListener(OnItemClickListener<Data> listener) {
        this.listener = listener;
    }
}
