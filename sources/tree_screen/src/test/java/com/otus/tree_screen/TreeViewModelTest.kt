package com.otus.tree_screen

import com.otus.core_api.remote.HpApi
import com.otus.tree_screen.viewmodel.TreeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock
import com.otus.core_api.dto.Character
import com.otus.core_api.dto.WandAttr
import org.junit.Assert
import org.junit.BeforeClass

@OptIn(ExperimentalCoroutinesApi::class)
class TreeViewModelTest {

    private val hpApi: HpApi = mock()
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var treeViewModel: TreeViewModel


    companion object {
        private lateinit var nameListMock: List<Character>

        @BeforeClass
        @JvmStatic
        fun setData() {
            nameListMock = listOf(
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


    @Before
    fun setUp() {
        treeViewModel = TreeViewModel(hpApi, testDispatcher)
    }


    @Test
    fun `get only unique surnames`() {
        val expected = listOf("Jefferson", "Lanwey", "Dawell", "Lews", "Millan")
        val actual = treeViewModel.getNamesList(nameListMock)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `filter characters by family surnames`() {
        val chosenName = "Millan"

        val expected = listOf(
            Character("8erds", "Terry Mac Millan", "", "",
                "", 1870, true, "", "",
                "", WandAttr("", "", 1.0), "",
                false, false, "", true, ""),

            Character("324afd", "Daniel Mac Millan", "", "",
                "", 1870, true, "", "",
                "", WandAttr("", "", 1.0), "",
                false, false, "", true, "")
        )

        val actual = treeViewModel.getFamilyByName(nameListMock, chosenName)

        Assert.assertEquals(expected, actual)
    }
}