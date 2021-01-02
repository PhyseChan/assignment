package com.example.assignment.base.adapter;


/**
 * TODO RecyclerView Item 长按、点击事件
 */

public interface OnItemClickListener<Data> {

    /**
     * Item click 点击事件
     *
     * @param data     item data 数据
     * @param position item subscript 下标
     */
    void onItemClick(Data data, int position);

    /**
     * Item long clicking 长按事件
     *
     * @param data     item data数据
     * @param position item subscript下标
     */
    boolean onItemLongClick(Data data, int position);

}
