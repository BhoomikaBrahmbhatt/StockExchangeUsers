package com.android.demo.stockexchangeusers

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.android.demo.stockexchangeusers.adapter.UserListAdapter
import com.android.demo.stockexchangeusers.databinding.ActivityMainBinding
import com.android.demo.stockexchangeusers.network.RetrofitService
import com.android.demo.stockexchangeusers.repository.AllApi.FILTER
import com.android.demo.stockexchangeusers.repository.AllApi.ORDER
import com.android.demo.stockexchangeusers.repository.AllApi.PAGE
import com.android.demo.stockexchangeusers.repository.AllApi.PAGE_SIZE
import com.android.demo.stockexchangeusers.repository.AllApi.SITE
import com.android.demo.stockexchangeusers.repository.AllApi.SORT
import com.android.demo.stockexchangeusers.repository.MainRepository
import com.android.demo.stockexchangeusers.repository.MainViewModel
import com.android.demo.stockexchangeusers.repository.MyViewModelFactory
import com.android.demo.stockexchangeusers.utils.EspressoIdlingResource

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val adapter = UserListAdapter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter
EspressoIdlingResource.increment()
        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)


        viewModel.usersList.observe(this, {
            adapter.setUsers(it)
            EspressoIdlingResource.decrement()
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            EspressoIdlingResource.decrement()
        })

        viewModel.loading.observe(this, {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getAllUsers(PAGE,PAGE_SIZE,ORDER, SORT, SITE, FILTER)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (binding.recyclerview.adapter as UserListAdapter).filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}