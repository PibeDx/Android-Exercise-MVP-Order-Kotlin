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

import com.josecuentas.android_exercise_mvp_order_kotlin.BuildConfig
import com.josecuentas.android_exercise_mvp_order_kotlin.domain.model.Item


/**
 * Created by jcuentas on 19/09/17.
 */
interface ItemRepository {


    companion object {
        val PATH = BuildConfig.APPLICATION_ID
    }

    fun saveItems(items: List<Item>)

    fun getITems(): List<Item>
}