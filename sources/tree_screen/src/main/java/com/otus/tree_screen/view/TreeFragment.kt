package com.otus.tree_screen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.otus.core_api.dto.Character
import com.otus.core_api.providers.ExternalAppHub
import com.otus.tree_screen.databinding.FragmentTreeBinding
import com.otus.tree_screen.di.TreeComponent
import com.otus.tree_screen.viewmodel.TreeViewModel
import com.otus.tree_screen.viewmodel.TreeViewModelFactory
import javax.inject.Inject

class TreeFragment : Fragment(), NamesAdapter.NameClickListener {

    private var _binding: FragmentTreeBinding? = null
    private val binding get() = _binding!!

    private var namesList = listOf<String>()
    private lateinit var namesAdapter: NamesAdapter

    private var charList = listOf<Character>()
    private lateinit var treeAdapter: TreeAdapter

    @Inject
    lateinit var treeVMFactory: TreeViewModelFactory
    private val treeViewModel by viewModels<TreeViewModel> { treeVMFactory }

    companion object {
        fun newInstance() = TreeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TreeComponent
            .create((requireActivity().application as ExternalAppHub).getExternalProvider())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fitNamesRecycler()
        fitTreeRecycler()

        treeViewModel.getAllCharacters()
        treeViewModel.treeLiveData.observe(viewLifecycleOwner, treeObserver)
    }


    private val treeObserver = Observer<List<Character>> {
        charList = it
        namesList = treeViewModel.getNamesList(it)
        namesAdapter.differ.submitList(namesList)
        setFamilyTreeList(namesAdapter.getChosenPosition())
    }

    private fun fitNamesRecycler() {
        binding.namesRecycler.apply {
            namesAdapter = NamesAdapter(this@TreeFragment)
            adapter = namesAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
    private fun fitTreeRecycler() {
        binding.treeRecycler.apply {
            treeAdapter = TreeAdapter(requireContext())
            adapter = treeAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onNameClick(position: Int) {
        namesAdapter.setChosenPosition(position)
        setFamilyTreeList(position)
    }

    private fun setFamilyTreeList(position: Int) {
        val familyList = treeViewModel.getFamilyByName(charList, namesList[position])
        treeAdapter.differ.submitList(familyList)
    }
}