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

package com.josecuentas.android_exercise_mvp_order_kotlin.ui

import android.app.Application
import android.content.Context
import com.josecuentas.android_exercise_mvp_order_kotlin.data.local.ItemLocalRepository
import com.josecuentas.android_exercise_mvp_order_kotlin.data.local.ItemRepository
import com.josecuentas.android_exercise_mvp_order_kotlin.ui.main.MainDummy

/**
 * Created by jcuentas on 19/09/17.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()


        //region add data dummy
        val itemRepository: ItemRepository = ItemLocalRepository(getSharedPreferences(ItemRepository.PATH,Context.MODE_PRIVATE))
        val itemList = itemRepository.getITems()
        if (itemList.isEmpty()) {
            itemRepository.saveItems(MainDummy.getItems())
        }
        //endregion

    }
}