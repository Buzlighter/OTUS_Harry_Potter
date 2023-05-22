package com.otus.tree_screen
import com.otus.core_api.dto.Character
import com.otus.core_api.dto.WandAttr

object MockFactory {

    fun getCharactersList(): List<Character> {
        return listOf(
            Character("8er", "Henry Jefferson", "", "",
                "", 1870, true, "", "",
                "", WandAttr("", "", 1.0), "",
                false, false, "", true, ""),

            Character("1242df", "Ferral Lanwey", "", "",
                "", 1870, true, "", "",
                "", WandAttr("", "", 1.0), "",
                false, false, "", true, ""),

            Character("af23as", "Johny Dawell", "", "",
                "", 1870, true, "", "",
                "", WandAttr("", "", 1.0), "",
                false, false, "", true, ""),

            Character("2442rqeq", "Mitchel Lews", "", "",
                "", 1870, true, "", "",
                "", WandAttr("", "", 1.0), "",
                false, false, "", true, ""),

            Character("8erds", "Terry Mac Millan", "", "",
                "", 1870, true, "", "",
                "", WandAttr("", "", 1.0), "",
                false, false, "", true, ""),

            Character("2442rqeq", "Mitchel Lews", "", "",
                "", 1870, true, "", "",
                "", WandAttr("", "", 1.0), "",
                false, false, "", true, ""),

            Character("324afd", "Daniel Mac Millan", "", "",
                "", 1870, true, "", "",
                "", WandAttr("", "", 1.0), "",
                false, false, "", true, "")
        )
    }

}