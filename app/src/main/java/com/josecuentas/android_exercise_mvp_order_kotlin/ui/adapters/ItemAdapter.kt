/**
 * Copyright 2017, José Norberto Cuentas Turpo.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.josecuentas.android_exercise_mvp_order_kotlin.ui.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.josecuentas.android_exercise_mvp_order_kotlin.R
import com.josecuentas.android_exercise_mvp_order_kotlin.domain.model.Item

/**
 * Created by jcuentas on 18/09/17.
 */
class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    var itemList: MutableList<Item> = ArrayList()
    var listener: OnItemAdapterListener? = null
    lateinit var context: Context

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList.get(position)
        holder.view.setBackgroundColor(ContextCompat.getColor(context, item.resourceColorId))
        holder.itemView.setOnClickListener { listener?.onItemClick(item) }
    }

    override fun getItemCount(): Int = itemList.size

    inner class ItemViewHolder : RecyclerView.ViewHolder {
        val view: View

        constructor(itemView: View) : super(itemView) {
            view = itemView.findViewById(R.id.view)
        }
    }

    interface OnItemAdapterListener {
        fun onItemClick(item: Item)
    }
}