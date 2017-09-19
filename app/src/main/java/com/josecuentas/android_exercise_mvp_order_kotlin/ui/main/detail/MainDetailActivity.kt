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

package com.josecuentas.android_exercise_mvp_order_kotlin.ui.main.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.josecuentas.android_exercise_mvp_order_kotlin.R
import com.josecuentas.android_exercise_mvp_order_kotlin.domain.model.Item
import kotlinx.android.synthetic.main.activity_main_detail.*

/**
 * Created by jcuentas on 19/09/17.
 */
class MainDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail)
        extras()
    }

    private fun extras() {
        val item = intent?.extras?.getSerializable(Item.BUNDLE)?.let { it as Item }
        item?.let {
            claContainerMain.setBackgroundColor(ContextCompat.getColor(this, item.resourceColorId))
        }

    }
}
