package com.capstonebangkit.plantdoc.ui.pupuk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstonebangkit.plantdoc.R
import com.capstonebangkit.plantdoc.data.PupukData
import com.capstonebangkit.plantdoc.databinding.ActivityMainBinding
import com.capstonebangkit.plantdoc.databinding.FragmentPupukBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class PupukFragment : Fragment() {

    private var _binding: FragmentPupukBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPupukBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val rvPupuk: RecyclerView = binding.rvPupuk
        val pupukAdapter = PupukAdapter(PupukData.pupuk)

        rvPupuk.adapter = pupukAdapter
        rvPupuk.layoutManager = LinearLayoutManager(requireContext())

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}