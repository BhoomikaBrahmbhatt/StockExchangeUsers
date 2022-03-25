package com.android.demo.stockexchangeusers

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.demo.stockexchangeusers.adapter.TagsListAdapter
import com.android.demo.stockexchangeusers.databinding.ActivityUserDetailBinding
import com.android.demo.stockexchangeusers.network.RetrofitService
import com.android.demo.stockexchangeusers.repository.*
import com.android.demo.stockexchangeusers.repository.AllApi.USER_DATA
import com.android.demo.stockexchangeusers.utils.EspressoIdlingResource
import com.bumptech.glide.Glide
import com.google.gson.Gson

class UserDetailActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityUserDetailBinding
    lateinit var viewModel: MainViewModel
    private val adapter = TagsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        EspressoIdlingResource.increment()

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        supportActionBar?.apply {
            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        val userdata = intent.extras!!.get(USER_DATA) as String

        binding.recyclerview.adapter = adapter

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)


        viewModel.tagsList.observe(this, {
            adapter.setTags(it)

            if(it.isNotEmpty())
            binding.textlabeltags.visibility = View.VISIBLE
            else
                binding.textlabeltags.visibility = View.GONE

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
            val data = Gson().fromJson(userdata, Items::class.java)

            Glide.with(this).load(data.profileImage).into(binding.imageview)
            binding.textName.text = data.displayName
            binding.textReputaionValue.text = data.reputation.toString()
            binding.textLocationValue.text = data.location
            binding.textGoldBadge.text = data.badgeCounts?.gold.toString()
            binding.textSilverBadge.text = data.badgeCounts?.silver.toString()
            binding.textBronzeBadge.text = data.badgeCounts?.bronze.toString()
         binding.textCreationDate.text = data.creationDate.toString()

        data.userId?.let {
                viewModel.getAllTags(
                    it,
                    AllApi.SITE
                )
            }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}