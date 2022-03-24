package com.android.demo.stockexchangeusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
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

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)


        viewModel.movieList.observe(this, {
            adapter.setUsers(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getAllMovies(PAGE,PAGE_SIZE,ORDER, SORT, SITE, FILTER)

    }

}