package com.capstonebangkit.plantdoc.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.databinding.FragmentHomeBinding
import com.capstonebangkit.plantdoc.data.PupukData
import androidx.navigation.fragment.findNavController
import com.capstonebangkit.plantdoc.data.PenyakitTanamanData
import com.capstonebangkit.plantdoc.ui.penyakit.PenyakitAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var pupukAdapter: HomeAdapter
    private lateinit var penyakitAdapter: PenyakitAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupRecyclerViews()

        binding.clickPenyakitTanaman.setOnClickListener {
            // findNavController().navigate(R.id.navigation_pupuk)
        }

        binding.clickPupuk.setOnClickListener {
            findNavController().navigate(R.id.navigation_pupuk)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerViews() {
        setupPupukRecyclerView()
        setupPenyakitRecyclerView()
    }

    private fun setupPupukRecyclerView() {
        val pupukList = PupukData.pupuk
        pupukAdapter = HomeAdapter(pupukList)
        binding.rvPupuk.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = pupukAdapter
        }
    }

    private fun setupPenyakitRecyclerView() {
        val penyakitList = PenyakitTanamanData.penyakit
        penyakitAdapter = PenyakitAdapter(penyakitList)
        binding.rvPenyakit.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = penyakitAdapter
        }
    }
}