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

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.josecuentas.android_exercise_mvp_order_kotlin.R
import com.josecuentas.android_exercise_mvp_order_kotlin.data.local.ItemRepository
import com.josecuentas.android_exercise_mvp_order_kotlin.domain.model.Item
import com.josecuentas.android_exercise_mvp_order_kotlin.ui.adapters.ItemAdapter
import com.josecuentas.android_exercise_mvp_order_kotlin.ui.main.detail.MainDetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemAdapterListener, MainContract.View {

    lateinit var itemAdapter: ItemAdapter
    val presenter: MainPresenter by lazy { MainPresenter(getSharedPreferences(ItemRepository.PATH, Context.MODE_PRIVATE)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectPresenter()
        setup()
    }

    override fun onResume() {
        super.onResume()
        //call service
        presenter.getItems()
    }

    private fun restoreState(savedInstanceState: Bundle) {
        val itemList: List<Item> = savedInstanceState.getSerializable(Item.BUNDLE_LIST) as List<Item>
        presenter.loadPresenterState(itemList)
    }

    private fun saveState(outState: Bundle?) {
        outState?.putSerializable(Item.BUNDLE_LIST, presenter.itemList as ArrayList)
    }

    private fun injectPresenter() {
        //presenter = MainPresenter()
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
        Toast.makeText(this, "itemId: ${item.itemId} point: ${item.point} touch: ${item.touch}", Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putSerializable(Item.BUNDLE, item)
        val intent = Intent(this@MainActivity, MainDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyed()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        saveState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            restoreState(savedInstanceState)
        }
        super.onRestoreInstanceState(savedInstanceState)
    }
}
