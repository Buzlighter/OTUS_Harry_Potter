package com.otus.characters_screen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.otus.characters_screen.databinding.FragmentCharactersBinding
import com.otus.characters_screen.di.CharactersComponent
import com.otus.characters_screen.viewmodel.CharactersViewModel
import com.otus.characters_screen.viewmodel.CharactersViewModelFactory
import com.otus.core_api.providers.ExternalAppHub
import javax.inject.Inject
import com.otus.core_api.dto.Character


class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private lateinit var charactersAdapter: CharactersAdapter

    @Inject
    lateinit var charactersVMFactory: CharactersViewModelFactory
    private val charactersViewModel by viewModels<CharactersViewModel> { charactersVMFactory }

    companion object {
        fun newInstance() = CharactersFragment()
    }

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
        fitRecyclerView()

        charactersViewModel.getCharacters()
        charactersViewModel.charactersLiveData.observe(viewLifecycleOwner, characterObserver)
    }

    private val characterObserver = Observer<List<Character>> {
        charactersAdapter.differ.submitList(it)
    }

    private fun fitRecyclerView() {
        binding.charactersRecycler.apply {
            charactersAdapter = CharactersAdapter()
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}