package com.example.apiproject

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)


        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<DummyJasonProduct?> {
            override fun onResponse(p0: Call<DummyJasonProduct?>, p1: Response<DummyJasonProduct?>) {
                val responseBody=p1.body()
                val productList = responseBody?.products!!

                myAdapter = MyAdapter(this@MainActivity, productList)
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

//                val collectDataInSB = StringBuilder()
//
//                for(myData in userList){
//                    collectDataInSB.append(myData.firstName)
//                }
//
//                val tv = findViewById<TextView>(R.id.textView)
//                tv.text = collectDataInSB


            }

            override fun onFailure(p0: Call<DummyJasonProduct?>, p1: Throwable) {

                Log.d("Main Activity", "onFailure" + p1.message)
            }
        })
        }
    }
