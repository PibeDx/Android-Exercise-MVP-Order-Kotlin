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

package com.josecuentas.android_exercise_mvp_order_kotlin.data.mapper

import com.josecuentas.android_exercise_mvp_order_kotlin.domain.model.Item
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by jcuentas on 19/09/17.
 */
class ItemMapperTest {

    @Test fun toJSONTransform() {
        val jsonObjectDummy = JSONObject("{\".itemId\":1,\".timestamp\":1505852315602,\".touch\":0,\".resourceColorId\":1,\".point\":0}")

        val item = Item(1, 1, 0)
        item.timestamp = Date(1505852315602L)
        item.touch = 0

        val jsonObject = ItemMapper.ToJSON.transform(item)
        assertEquals(jsonObjectDummy.toString(), jsonObject.toString())
    }

    @Test fun toModelTransform() {
        val itemDummy = Item(1, 1, 0)
        itemDummy.timestamp = Date(1505852315602L)
        itemDummy.touch = 0

        val jsonObject = JSONObject("{\".itemId\":1,\".timestamp\":1505852315602,\".touch\":0,\".resourceColorId\":1,\".point\":0}")
        val item: Item = ItemMapper.ToModel.transform(jsonObject)
        assertEquals(itemDummy, item)
    }

    @Test fun toJSONTransformList() {
        val itemList = ArrayList<Item>()
        val itemDummy = Item(1, 1, 0)
        itemDummy.timestamp = Date(1505852315602L)
        itemDummy.touch = 0
        itemList.add(itemDummy)

        val jsonArray = ItemMapper.ToJSON.transformList(itemList)

        val jsonArrayDummy = JSONArray("[{\".itemId\":1,\".timestamp\":1505852315602,\".touch\":0,\".resourceColorId\":1,\".point\":0}]")
        assertEquals(jsonArrayDummy.toString(), jsonArray.toString())
    }

    @Test fun toModelTransformList() {
        val jsonArray = JSONArray("[{\".itemId\":1,\".timestamp\":1505852315602,\".touch\":0,\".resourceColorId\":1,\".point\":0}]")

        val itemListDummy = ArrayList<Item>()
        val itemDummy = Item(1, 1, 0)
        itemDummy.timestamp = Date(1505852315602L)
        itemDummy.touch = 0
        itemListDummy.add(itemDummy)

        val itemList = ItemMapper.ToModel.transformList(jsonArray)


        assertEquals(itemListDummy, itemList)
    }

    @Test fun toModelTransformListEmpty() {
        val jsonArray = JSONArray("[]")
        val itemListDummy = ArrayList<Item>()
        val itemList = ItemMapper.ToModel.transformList(jsonArray)
        assertEquals(itemListDummy, itemList)
    }

    @Test fun toJSONTransformListEmpty() {
        val jsonArrayDummy = JSONArray("[]")
        val itemListDummy = ArrayList<Item>()


        val jsonArray = ItemMapper.ToJSON.transformList(itemListDummy)
        assertEquals(jsonArrayDummy.toString(), jsonArray.toString())
    }
}