package com.capstonebangkit.plantdoc.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.databinding.FragmentHomeBinding
import com.capstonebangkit.plantdoc.data.PupukData
import androidx.navigation.fragment.findNavController
import com.capstonebangkit.plantdoc.data.BeritaData
import com.capstonebangkit.plantdoc.data.PenyakitTanamanData
import com.capstonebangkit.plantdoc.ui.berita.BeritaActivity
import com.capstonebangkit.plantdoc.ui.camera.ScanActivity
import com.capstonebangkit.plantdoc.ui.penyakit.PenyakitActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var pupukAdapter: HomeAdapter
    private lateinit var penyakitAdapter: HomePenyakitAdapter
    private lateinit var beritaAdapter: HomeBeritaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupRecyclerViews()

        binding.buttonCamera.setOnClickListener {
            val intent = Intent(requireContext(), ScanActivity::class.java)
            startActivity(intent)
        }
        binding.clickPenyakitTanaman.setOnClickListener {
            val intent = Intent(requireContext(), PenyakitActivity::class.java)
            startActivity(intent)
        }
        binding.clickPupuk.setOnClickListener {
            val navController = findNavController()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.navigation_home, true)
                .build()
            navController.navigate(R.id.navigation_pupuk, null, navOptions)
        }
        binding.clickBeritaTanaman.setOnClickListener {
            val intent = Intent(requireContext(), BeritaActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerViews() {
        setupPenyakitRecyclerView()
        setupPupukRecyclerView()
        setupBeritaRecyclerView()
    }

    private fun setupPenyakitRecyclerView() {
        val penyakitList = PenyakitTanamanData.penyakit
        penyakitAdapter = HomePenyakitAdapter(penyakitList)
        binding.rvPenyakit.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = penyakitAdapter
        }
    }

    private fun setupPupukRecyclerView() {
        val pupukList = PupukData.pupuk
        pupukAdapter = HomeAdapter(pupukList)
        binding.rvPupuk.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = pupukAdapter
        }
    }

    private fun setupBeritaRecyclerView() {
        val beritaList = BeritaData.berita
        beritaAdapter = HomeBeritaAdapter(beritaList)
        binding.rvBerita.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = beritaAdapter
        }
    }
}