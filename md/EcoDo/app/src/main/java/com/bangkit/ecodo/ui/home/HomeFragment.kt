package com.bangkit.ecodo.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.ecodo.databinding.FragmentHomeBinding
import com.bangkit.ecodo.ui.recommendation.RecommendationActivity
import com.bangkit.ecodo.ui.scan_trash.ScanTrashActivity
import com.bangkit.ecodo.ui.tutorial.TutorialActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.button1.setOnClickListener {
            val intent = Intent(context, RecommendationActivity::class.java)
            startActivity(intent)
        }

        binding.menu1.setOnClickListener {
            val intent = Intent(context, ScanTrashActivity::class.java)
            startActivity(intent)
        }

        binding.menu2.setOnClickListener {
            val intent = Intent(context, TutorialActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}