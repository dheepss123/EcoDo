package com.bangkit.ecodo.ui.trash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ecodo.databinding.FragmentTrashBinding
import com.bangkit.ecodo.ui.recommendation.RecommendationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrashFragment : Fragment() {
    private var _binding: FragmentTrashBinding? = null
    private val viewModel: TrashViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTrashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHistory().observe(viewLifecycleOwner) { listTrash ->
            val layoutManager = LinearLayoutManager(context)
            binding.rvList.layoutManager = layoutManager
            binding.rvList.adapter = TrashAdapter(listTrash) {
                val intent = Intent(context, RecommendationActivity::class.java)
                intent.putExtra(RecommendationActivity.TRASH_ID, it)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}