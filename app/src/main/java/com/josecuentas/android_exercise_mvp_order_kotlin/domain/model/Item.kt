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

package com.josecuentas.android_exercise_mvp_order_kotlin.domain.model

import java.io.Serializable
import java.util.*

/**
 * Created by jcuentas on 18/09/17.
 */
data class Item(val itemId: Int = -1, val resourceColorId: Int, var point: Int = 0) : Serializable {

    companion object {
        val BUNDLE = ".item"
        val MAX_TOUCH = 3
    }

    var timestamp: Date = Date()
    var touch: Int = 0


    fun addTouch() {
        touch += 1
        if (touch >= MAX_TOUCH) addPoint()
    }

    fun resetTouch() {
        touch = 0
    }

    fun addPoint() {
        updateDate()
        point += 1
    }

    fun updateDate() {
        timestamp = Date()
    }
}