package com.otus.tree_screen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.otus.core_api.providers.ExternalAppHub
import com.otus.tree_screen.R
import com.otus.tree_screen.databinding.FragmentTreeBinding
import com.otus.tree_screen.di.DaggerTreeComponent
import com.otus.tree_screen.di.TreeComponent

class TreeFragment : Fragment() {

    private var _binding: FragmentTreeBinding? = null
    private val binding get() = _binding!!

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
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}