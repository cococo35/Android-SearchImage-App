package com.android.searchimageapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.android.searchimageapp.data.Document
import com.android.searchimageapp.data.SelectedItems
import com.android.searchimageapp.databinding.FragmentSearchBinding
import com.android.searchimageapp.presentation.SearchImageAdapter
import com.android.searchimageapp.retrofit.NetWorkClient
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var _binding: FragmentSearchBinding
    private val binding get() = _binding
    private val searchImageAdapter by lazy { SearchImageAdapter{selectedItem -> adapterOnClick(selectedItem)} }

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
                saveData()      // sharedPreference
                communicateNetWork(setUpSearchParam(binding.edSearch.text.toString()))      // 서버 통신
                activity?.let { it1 -> hideKeyBoard(it1) }      // 키보드 숨김
            }
        }

        loadData()      // sharedPreference

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

    @SuppressLint("NotifyDataSetChanged")   // notifyItemChanged() 로 바꿔보기(성능 문제)
    private fun communicateNetWork(param: HashMap<String, String>) = lifecycleScope.launch {
        val responseData = NetWorkClient.searchNetWork.getSearch(param)
        binding.recyclerviewSearch.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = searchImageAdapter
            searchImageAdapter.data = responseData.searchDocuments
            searchImageAdapter.notifyDataSetChanged()
        }
    }

    private fun setUpSearchParam(search: String): HashMap<String, String> {
        return hashMapOf(
            "query" to search, // edittext에서 입력한 값 넣기
            "page" to "1",
            "size" to "80"
        )
    }

    @SuppressLint("NotifyDataSetChanged")   // notifyItemChanged() 로 바꿔보기(성능 문제)
    private fun adapterOnClick(selectedItem: Document) {
        val index = searchImageAdapter.data.indexOf(selectedItem)   // recyclerView 에서 선택한 item
        if(index != -1) {
            // 좋아요 버튼 온오프
            val updatedItems = searchImageAdapter.data.toMutableList()
            updatedItems[index] = selectedItem.copy(isSelected = !selectedItem.isSelected)
            searchImageAdapter.data = updatedItems
            searchImageAdapter.notifyDataSetChanged()

            // selectedItems 에 선택한 index 넣기
            if(searchImageAdapter.data[index].isSelected) {
//                selectedItems.add(searchImageAdapter.data[index])
                SelectedItems.addItem(searchImageAdapter.data[index])
                Toast.makeText(requireActivity(), R.string.my_storage_add, Toast.LENGTH_SHORT).show()
            }
            else {
//                selectedItems.forEach {   이건 왜 안되지?
//                    if(it.thumbnailUrl == searchImageAdapter.data[index].thumbnailUrl) {
//                        selectedItems.remove(selectedItem)
//                        Log.d("selectedItems_remove", "$selectedItems")
//                        Toast.makeText(requireActivity(), R.string.my_storage_remove, Toast.LENGTH_SHORT).show()

//                selectedItems =  selectedItems.filterNot{
//                    it.thumbnailUrl == searchImageAdapter.data[index].thumbnailUrl
//                }.toMutableList()
                SelectedItems.removeItem(searchImageAdapter.data[index])
                Toast.makeText(requireActivity(), R.string.my_storage_remove, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hideKeyBoard(activity: Activity) {
        val keyBoard = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyBoard.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
    }
}