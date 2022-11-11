package com.example.apisamplecodes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.apisamplecodes.databinding.ActivityGlideSampleBinding

class GlideSampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlideSampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlideSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Glide.with(this)
//            .load("https://upload.wikimedia.org/wikipedia/commons/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg")
//            .into(binding.imgFood)

//        Glide.with(this)
//            .load("https://upload.wikimedia.org/wikipedia/commons/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg")
//            .override(200,200)
//            .into(binding.imgFood)

        Glide.with(this)
            .load("https://upload.wikimedia.org/wikipedia/commons/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg")
            .placeholder(R.mipmap.ic_launcher)
            .override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
            .into(binding.imgFood)

    }
}