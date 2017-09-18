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

package com.josecuentas.android_exercise_mvp_order_kotlin.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.josecuentas.android_exercise_mvp_order_kotlin.R
import com.josecuentas.android_exercise_mvp_order_kotlin.domain.model.Item
import com.josecuentas.android_exercise_mvp_order_kotlin.ui.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemAdapterListener, MainContract.View {

    lateinit var itemAdapter: ItemAdapter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectPresenter()
        setup()
        //call service
        presenter.getItems()
    }

    private fun injectPresenter() {
        presenter = MainPresenter()
        presenter.attached(this)
    }

    private fun setup() {
        setupAdapter()
        setupRecycler()
    }

    private fun setupAdapter() {
        itemAdapter = ItemAdapter()
        itemAdapter.listener = this
    }

    private fun setupRecycler() {
        rviItem.layoutManager = LinearLayoutManager(this)
        rviItem.addItemDecoration(CustomItemDecoration(10))
        rviItem.adapter = itemAdapter
    }

    override fun onItemClick(item: Item) {
        presenter.goItemDetail(item)
    }

    override fun showLoading() {
        pbaLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbaLoading.visibility = View.GONE
    }

    override fun loadItems(itemList: List<Item>) {
        itemAdapter.itemList = itemList as MutableList<Item>
        itemAdapter.notifyDataSetChanged()
    }

    override fun goItemDetail(item: Item) {
        Toast.makeText(this, "itemId: ${item.itemId} point: ${item.point}", Toast.LENGTH_SHORT).show()
    }
}
