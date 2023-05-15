package com.otus.potions_screen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.otus.core_api.providers.ExternalAppHub
import com.otus.potions_screen.R
import com.otus.potions_screen.databinding.FragmentPotionsBinding
import com.otus.potions_screen.di.PotionsComponent


class PotionsFragment : Fragment() {
    private var _binding: FragmentPotionsBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = PotionsFragment()
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
        binding.potionView.setIngredientsQuantity(4)

        binding.fillBtn.setOnClickListener {
            binding.potionView.startFillingAnimation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}