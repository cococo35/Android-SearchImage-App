package com.android.searchimageapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.searchimageapp.data.Document
import com.android.searchimageapp.databinding.FragmentSearchBinding
import com.android.searchimageapp.presentation.SearchImageAdapter
import com.android.searchimageapp.retrofit.NetWorkClient
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var _binding: FragmentSearchBinding
    private val binding get() = _binding
    var items = listOf<Document>()
//    private val searchImageAdapter by lazy { SearchImageAdapter(items) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnSearch.setOnClickListener {
                saveData()

                communicateNetWork(setUpSearchParam(binding.edSearch.text.toString()))
                // items = responseData.searchDocuments

            }
        }

//        val searchImageAdapter = SearchImageAdapter(items)

        binding.recyclerviewSearch.apply {
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = SearchImageAdapter(items)
            layoutManager = LinearLayoutManager(requireContext())
        }

        loadData()
    }

    private fun saveData() {
        val pref = requireActivity().getSharedPreferences("pref", 0)
        val edit = pref.edit()
        edit.putString("name", binding.edSearch.text.toString())
        edit.apply()
    }

    private fun loadData() {
        val pref = requireActivity().getSharedPreferences("pref", 0)
        binding.edSearch.setText(pref.getString("name", ""))
    }

//    @SuppressLint("NotifyDataSetChanged")
    private fun communicateNetWork(param: HashMap<String, String>) = lifecycleScope.launch {
        val responseData = NetWorkClient.searchNetWork.getSearch(param)
        items = responseData.searchDocuments
//        searchImageAdapter.notifyDataSetChanged()
    }

    private fun setUpSearchParam(search: String): HashMap<String, String> {
        return hashMapOf(
            "query" to search, // edittext에서 입력한 값 넣기
            "page" to "1",
            "size" to "50" // 80
        )
    }
}