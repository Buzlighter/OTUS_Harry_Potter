package com.otus.characters_screen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.otus.characters_screen.databinding.FragmentCharactersBinding
import com.otus.characters_screen.di.CharactersComponent
import com.otus.core_api.providers.ExternalAppHub


class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CharactersComponent
            .create((requireActivity().application as ExternalAppHub).getExternalProvider())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = CharactersFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}