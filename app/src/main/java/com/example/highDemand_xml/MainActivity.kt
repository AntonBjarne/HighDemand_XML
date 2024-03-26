package com.example.highDemand_xml

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
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
        for (i in 0 until numberOfItems) {
            // Create a new RelativeLayout
            val relativeLayout = RelativeLayout(this)
            relativeLayout.layoutParams = layoutParams


            // Create a new ImageView
            val imageView = ImageView(this)
            imageView.id = View.generateViewId()
            imageView.layoutParams = RelativeLayout.LayoutParams(
                100, // Width
                100 // Height
            )
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            if (i % 2 == 0) {
                imageView.setImageResource(R.drawable.photo3) // Set your image resource here
            } else {
                imageView.setImageResource(R.drawable.photo4) // Set your image resource here
            }
            relativeLayout.addView(imageView)

            // Create a new TextView
            val textView = TextView(this)
            textView.layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            textView.text = "Text"
            textView.setTextColor(Color.BLACK)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
            val textParams = textView.layoutParams as RelativeLayout.LayoutParams
            textParams.addRule(RelativeLayout.BELOW, imageView.id)
            textParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
            textView.layoutParams = textParams
            relativeLayout.addView(textView)

            // Add the RelativeLayout to the LinearLayout
            linearLayout.addView(relativeLayout)

        }

        val scrollView = findViewById<ScrollView>(R.id.scrollView)
        val linearLayout2 = findViewById<LinearLayout>(R.id.linearLayout2)

        val numberOfPosts = 5 // Change this to the desired number of items

        for (i in 0 until numberOfPosts) {
            // Inflate the card layout
            if(i%2 == 0){
                val cardLayout = layoutInflater.inflate(R.layout.post1_layout, null) as ConstraintLayout
                val imageView = cardLayout.findViewById<ImageView>(R.id.imageView)
                imageView.setImageResource(R.drawable.photo3) // Set your image resource here
                // Add the card layout to the linear layout inside the ScrollView
                linearLayout2.addView(cardLayout)
            }
            else{
                val cardLayout = layoutInflater.inflate(R.layout.post2_layout, null) as ConstraintLayout
                val imageView = cardLayout.findViewById<ImageView>(R.id.imageView)
                imageView.setImageResource(R.drawable.photo4) // Set your image resource here
                // Add the card layout to the linear layout inside the ScrollView
                linearLayout2.addView(cardLayout)
            }


        }

        val post1Images = MutableList(9) { R.drawable.photo3 }

        postRecyclerView.layoutManager = GridLayoutManager(this, 3)
        postRecyclerView.addItemDecoration(GridSpacingItemDecoration(3, 16, true))

        // Set adapter with data for the RecyclerView using ImageAdapter
        postRecyclerView.adapter = ImageAdapter(this, post1Images, 300, 300)

        spinner.adapter = adapter
    }
}