package com.cuneytuzunsakal.crptocurrencyapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuneytuzunsakal.crptocurrencyapp.R
import com.cuneytuzunsakal.crptocurrencyapp.adapter.RecyclerViewAdapter
import com.cuneytuzunsakal.crptocurrencyapp.model.CryptocurrencyModel
import com.cuneytuzunsakal.crptocurrencyapp.model.CryptocurrencyResponse
import com.cuneytuzunsakal.crptocurrencyapp.service.CryptocurrencyAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val cryptoModels = ArrayList<CryptocurrencyModel>()
    private val BASE_URL = "https://api.btcturk.com/api/v2/"
    private lateinit var retrofit: Retrofit
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView'i tanımlayın
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Retrofit & JSON
        val gson: Gson = GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        loadData()

        // RecyclerView'i ayarlayın
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerViewAdapter = RecyclerViewAdapter(cryptoModels)
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun loadData() {
        val cryptoAPI: CryptocurrencyAPI = retrofit.create(CryptocurrencyAPI::class.java)

        compositeDisposable.add(cryptoAPI.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> handleResponse(response.data) },
                { throwable -> throwable.printStackTrace() }
            ))
    }

    private fun handleResponse(cryptoModelList: List<CryptocurrencyModel>) {
        cryptoModels.addAll(cryptoModelList)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }
}
