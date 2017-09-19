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
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by jcuentas on 19/09/17.
 */
class ItemMapper {

    class ToJSON {
        companion object {
            fun transform(item: Item): JSONObject {
                val jsonObject = JSONObject()
                jsonObject.put(Item.ITEM_ID, item.itemId)
                jsonObject.put(Item.RESOURCE_COLOR_ID, item.resourceColorId)
                jsonObject.put(Item.POINT, item.point)
                jsonObject.put(Item.TIMESTAMP, item.timestamp.time)
                jsonObject.put(Item.TOUCH, item.touch)
                return jsonObject
            }

            fun transformList(itemList: List<Item>): JSONArray {
                val jsonArray = JSONArray()
                itemList.forEach {
                    jsonArray.put(transform(it))
                }
                return jsonArray
            }
        }
    }

    class ToModel {
        companion object {
            fun transform(jsonObject: JSONObject): Item {
                val item = Item(
                        jsonObject.getInt(Item.ITEM_ID),
                        jsonObject.getInt(Item.RESOURCE_COLOR_ID),
                        jsonObject.getInt(Item.POINT)
                )
                item.timestamp = Date(jsonObject.getLong(Item.TIMESTAMP))
                item.touch = jsonObject.getInt(Item.TOUCH)
                return item
            }

            fun transformList(jsonArray: JSONArray): List<Item> {
                val itemList = ArrayList<Item>()
                val length = jsonArray.length() - 1
                for (i in 0..length) {
                    itemList.add(transform(jsonArray.getJSONObject(i)))
                }
                return itemList
            }
        }
    }

}