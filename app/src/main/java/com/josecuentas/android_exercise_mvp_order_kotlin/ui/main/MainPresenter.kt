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

import android.content.SharedPreferences
import com.josecuentas.android_exercise_mvp_order_kotlin.data.local.ItemLocalRepository
import com.josecuentas.android_exercise_mvp_order_kotlin.domain.model.Item

/**
 * Created by jcuentas on 18/09/17.
 */
class MainPresenter(preferences: SharedPreferences) : MainContract.Presenter, MainContract.Listener {


    private var view: MainContract.View? = null
    val itemList: MutableList<Item> = ArrayList()
    val itemRepository = ItemLocalRepository(preferences)

    override fun attached(view: MainContract.View) {
        this.view = view
    }

    override fun detached() {
        this.view = null
    }

    override fun destroyed() {
        detached()
    }

    override fun getItems() {
        this.view?.showLoading()
        if (itemList.isEmpty()) {
            itemList.addAll(itemRepository.getITems())
            reset()
        }

        val item = itemList.find { it.touch >= Item.MAX_TOUCH }
        if (item != null) {
            val byPoint = compareByDescending(Item::point)
            val byTimestamp = compareByDescending(Item::timestamp)
            val byPointThenTimestamp = byPoint.then(byTimestamp)
            itemList.sortWith(byPointThenTimestamp)
        }

        this.view?.loadItems(itemList)
        this.view?.hideLoading()
    }

    fun reset() {
        // reset touch
        itemList.map { it.resetTouch() }
    }

    override fun goItemDetail(item: Item) {
        item.addTouch()
        itemRepository.saveItems(itemList)
        this.view?.goItemDetail(item)
        getItems()
    }

    override fun loadPresenterState(itemList: List<Item>?) {
        if (itemList != null) {
            this.itemList.addAll(itemList)
        }
    }
}