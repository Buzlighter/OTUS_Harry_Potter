package com.otus.potions_screen.view

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.OnHierarchyChangeListener
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.otus.core_api.dto.Elixir
import com.otus.core_api.dto.Ingredient
import com.otus.core_api.providers.ExternalAppHub
import com.otus.potions_screen.databinding.FragmentPotionsBinding
import com.otus.potions_screen.di.PotionsComponent
import com.otus.potions_screen.viewmodel.PotionsViewModel
import com.otus.potions_screen.viewmodel.PotionsViewModelFactory
import javax.inject.Inject


class PotionsFragment : Fragment() {
    private var _binding: FragmentPotionsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var potionsViewModelFactory: PotionsViewModelFactory
    private val potionViewModel by viewModels<PotionsViewModel> { potionsViewModelFactory }

    private var elixirList = listOf<Elixir>()
    private var tableIngredientList = listOf<Ingredient>()
    private var resultIngredientList = mutableListOf<Ingredient>()
    lateinit var randomResultElixir: Elixir

    companion object {
        fun newInstance(): Fragment = PotionsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PotionsComponent
            .create((requireActivity().application as ExternalAppHub).getExternalProvider())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPotionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        potionViewModel.getElixirs()
        potionViewModel.elixirLiveData.observe(viewLifecycleOwner, elixirObserver)

        binding.ingredientsLayout.setOnDragListener(dragListener)
        binding.resultLayout.setOnDragListener(dragListener)

        binding.cookBtn.setOnClickListener(cookClickListener)
        binding.nextBtn.setOnClickListener(nextElixirClickListener)

        binding.resultLayout.setOnHierarchyChangeListener(onHierarchyChangeListener)
    }

    private val elixirObserver = Observer<List<Elixir>> {
        elixirList = potionViewModel.getElixirListWithLimitedAmount(it)
        randomResultElixir = potionViewModel.getRandomElixir(elixirList)
        tableIngredientList = potionViewModel.getTableIngredientsList(elixirList, randomResultElixir.ingredients)

        binding.potionView.setIngredientsQuantity(tableIngredientList.size)
        setCookingTable()
    }

    private fun setCookingTable() {
        binding.elixirName.text = randomResultElixir.name

        tableIngredientList.forEach { ingredient ->
            binding.ingredientsLayout.addView(
                getIngredientView(ingredient)
            )
        }
    }

    private fun getIngredientView(ingredient: Ingredient): TextView {
        val ingredientView = TextView(requireContext())

        ingredientView.apply {
            text = ingredient.name
            gravity = Gravity.CENTER
            setOnLongClickListener(ingredientLongClickListener)
            textSize = 18F
            setTextColor(Color.DKGRAY)
            setPadding(20)
            setTypeface(ingredientView.typeface, Typeface.ITALIC)
            paintFlags = Paint.UNDERLINE_TEXT_FLAG
        }
        return ingredientView
    }

    private val ingredientLongClickListener = View.OnLongClickListener {
        if (binding.resultLayout.contains(it)) {
           return@OnLongClickListener false
        }
        val clipText = "clipData"
        val item = ClipData.Item(clipText)
        val mimesTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
        val data = ClipData(clipText, mimesTypes, item)

        val dragShadowBuilder = View.DragShadowBuilder(it)
        it.startDragAndDrop(data, dragShadowBuilder, it, 0)

        it.visibility = View.INVISIBLE
        true
    }


    private val dragListener = View.OnDragListener { view, event ->
        when(event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> true

            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                view.invalidate()
                    val v = event.localState as View
                    val owner = v.parent as ViewGroup
                    owner.removeView(v)

                    val destination = view as LinearLayout
                    destination.addView(v)
                    v.visibility = View.VISIBLE
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                true
            }
            else -> false
        }
    }

    private val cookClickListener = View.OnClickListener {
        binding.resultLayout.children.forEach { view ->
            val resultIngredientName = (view as TextView).text

            val resultIngredient = tableIngredientList.find { ingredient ->
                ingredient.name == resultIngredientName
            }
            resultIngredientList.add(resultIngredient!!)
        }
        checkCookResult()
    }

    private fun checkCookResult() {
        if (resultIngredientList == randomResultElixir.ingredients) {
            binding.potionView.succeed()

        } else {
            binding.potionView.failed()
        }
        resultIngredientList.clear()
    }

    private val nextElixirClickListener = View.OnClickListener {
        binding.resultLayout.removeAllViews()
        binding.ingredientsLayout.removeAllViews()
        potionViewModel.getElixirs()
        binding.potionView.clearFlask()
    }

    private val onHierarchyChangeListener = object: OnHierarchyChangeListener {
        override fun onChildViewAdded(parent: View?, child: View?) {
            binding.potionView.startFillingAnimation()
        }
        override fun onChildViewRemoved(parent: View?, child: View?) {
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}