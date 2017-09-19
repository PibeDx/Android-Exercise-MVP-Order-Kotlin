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

package com.josecuentas.android_exercise_mvp_order_kotlin.data.local

import android.content.SharedPreferences
import com.josecuentas.android_exercise_mvp_order_kotlin.data.mapper.ItemMapper
import com.josecuentas.android_exercise_mvp_order_kotlin.domain.model.Item
import org.json.JSONArray


/**
 * Created by jcuentas on 19/09/17.
 */
class ItemLocalRepository(val preferences: SharedPreferences) : ItemRepository {

    val ITEMS = ItemRepository.PATH + ".itemList"

    override fun saveItems(items: List<Item>) {
        val jsonItemList = ItemMapper.ToJSON.transformList(items)
        editor().putString(ITEMS, jsonItemList.toString()).apply()
    }

    override fun getITems(): List<Item> {
        val itemList: List<Item>
        val stringItemList = preferences.getString(ITEMS, "[]")
        val itemJsonArray = JSONArray(stringItemList)
        itemList = ItemMapper.ToModel.transformList(itemJsonArray)
        return itemList
    }

    private fun editor(): SharedPreferences.Editor {
        return preferences.edit()
    }
}