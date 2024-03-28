package com.example.highDemand_xml

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var dropdownButton: Button
    private lateinit var spinner: Spinner
    private lateinit var postRecyclerView: RecyclerView

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dropdownButton = findViewById(R.id.dropdown_button)
        spinner = findViewById(R.id.spinner)
        postRecyclerView = findViewById(R.id.postRecyclerView)

        // Set an OnClickListener on the Button
        dropdownButton.setOnClickListener {
            // Programmatically trigger the Spinner's dropdown menu to show
            spinner.performClick()
        }

        val items = resources.getStringArray(R.array.dropdown_menu).toMutableList()

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            items
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Find the LinearLayout
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)

        // Define the parameters for the new RelativeLayout
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(10, 0, 10, 0) // Set margins if needed

        // Loop to add multiple instances of RelativeLayout
        val numberOfItems = 10
        val marginInDp = 16
        val density = resources.displayMetrics.density

        for (i in 0 until numberOfItems) {
            // Inflate layout based on even or odd
            val layoutResId = if (i % 2 == 0) R.layout.imageitem_layout else R.layout.imageitem2_layout
            val constraintLayout = LayoutInflater.from(this).inflate(layoutResId, null) as ConstraintLayout

            // Add the ConstraintLayout to the LinearLayout
            linearLayout.addView(constraintLayout)

            // Add space between items
            if (i < numberOfItems - 1) {
                val spaceView = View(this)
                val params = LinearLayout.LayoutParams((marginInDp * density).toInt(), LinearLayout.LayoutParams.MATCH_PARENT)
                spaceView.layoutParams = params
                linearLayout.addView(spaceView)
            }
        }

        val scrollView = findViewById<ScrollView>(R.id.scrollView)
        val linearLayout2 = findViewById<LinearLayout>(R.id.linearLayout2)

        val numberOfPosts = 5 // Change this to the desired number of items

        for (i in 0 until numberOfPosts) {
            // Inflate the card layout
            val cardLayout = layoutInflater.inflate(if (i % 2 == 0) R.layout.post1_layout else R.layout.post2_layout, null) as ConstraintLayout
            val imageView = cardLayout.findViewById<ImageView>(R.id.imageView)
            val imageViewIcon1 = cardLayout.findViewById<ImageView>(R.id.heartIcon)
            val imageViewIcon2 = cardLayout.findViewById<ImageView>(R.id.shareIcon)
            val imageViewIcon3 = cardLayout.findViewById<ImageView>(R.id.commentIcon)
            imageView.setImageResource(if (i % 2 == 0) R.drawable.photo3 else R.drawable.photo4) // Set your image resource here
            imageViewIcon1.setImageResource (R.drawable.heart_121) // Set your image resource here
            imageViewIcon2.setImageResource (R.drawable.share) // Set your image resource here
            imageViewIcon3.setImageResource (R.drawable.vector) // Set your image resource here
            // Add the card layout to the linear layout inside the ScrollView
            linearLayout2.addView(cardLayout)
        }

        val post1Images = MutableList(9) { R.drawable.photo3 }

        postRecyclerView.layoutManager = GridLayoutManager(this, 3)
        postRecyclerView.addItemDecoration(GridSpacingItemDecoration(3, 16, true))

        // Set adapter with data for the RecyclerView using ImageAdapter
        postRecyclerView.adapter = ImageAdapter(this, post1Images, 300, 300)

        spinner.adapter = adapter
    }
}