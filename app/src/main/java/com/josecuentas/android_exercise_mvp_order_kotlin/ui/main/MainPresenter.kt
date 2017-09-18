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

import com.josecuentas.android_exercise_mvp_order_kotlin.domain.model.Item

/**
 * Created by jcuentas on 18/09/17.
 */
class MainPresenter : MainContract.Presenter, MainContract.Listener {

    private var view: MainContract.View? = null
    private val itemList: MutableList<Item>  by lazy { MainDummy.getItems() }
    var map: MutableMap<Int, Int> = HashMap()

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

        //val item = itemList.find { it.touch >= 3 }

        val byPoint = compareByDescending (Item::point)
        val byTimestamp = compareByDescending (Item::timestamp)
        val byPointThenTimestamp = byPoint.then(byTimestamp)
        itemList.sortWith(byPointThenTimestamp)

        this.view?.loadItems(itemList)
        this.view?.hideLoading()
    }

    override fun goItemDetail(item: Item) {
        item.addPoint()
        this.view?.goItemDetail(item)

        getItems()
//        val isContent = map.containsKey(item.itemId)
//        if (isContent) {
//            var point: Int = map.get(item.itemId)!!
//            map.put(item.itemId, point++)
//        } else map.put(item.itemId, 1)
    }
}