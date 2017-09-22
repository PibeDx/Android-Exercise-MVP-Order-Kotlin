# This project is just a exercise
# Android-Exercise-MVP-Order-Kotlin
## Base in:

### Design preview:

<img src="https://raw.githubusercontent.com/PibeDx/Android-Exercise-MVP-Order-Kotlin/master/art/device-2017-09-22-113834.png" width="360">
<img src="https://raw.githubusercontent.com/PibeDx/Android-Exercise-MVP-Order-Kotlin/master/art/device-2017-09-22-114051.png" width="360">

### Add data dummy:

For more data dummy in `getItems()`

```kotlin
class MainDummy {

    companion object {
        fun getItems(): MutableList<Item> {
            val itemList = ArrayList<Item>()
            itemList.add(Item(1, R.color.main_red))
            itemList.add(Item(2, R.color.main_blue))
            itemList.add(Item(3, R.color.main_green))
            //HERE ADD
            return itemList
        }
    }
}
```

Do you want to contribute?
--------------------------
Feel free to report or add any useful feature, I will be glad to improve it with your help, before submitting your code please check the [codestyle](https://github.com/square/java-code-styles).

Developed By
------------

* José Norberto Cuentas Turpo  - <jcuentast@gmail.com>

License
-------

    Copyright 2017 José Norberto Cuentas Turpo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.