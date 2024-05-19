package com.c0d3v9.adopet.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.c0d3v9.adopet.home.HomeActivity
import com.c0d3v9.adopet.ProfileActivity
import com.c0d3v9.adopet.R
import com.c0d3v9.adopet.databinding.ActivityAddBinding
import com.c0d3v9.adopet.model.Post

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var viewModel: AddViewModel

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AddViewModel::class.java)

        //val myPost = Post(binding.addageEditText.text.toString().toInt(), binding.addweightEditText.text.toString().toInt(), binding.addnameEditText.text.toString(), binding.addgenderAutoComplete.text.toString())
        binding.addBtn.setOnClickListener {
            val ageText = binding.addageEditText.text.toString()
            val weightText = binding.addweightEditText.text.toString()
            val nameText = binding.addnameEditText.text.toString()
            val genderText = binding.addgenderAutoComplete.text.toString()

            if (ageText.isNotEmpty() && weightText.isNotEmpty() && nameText.isNotEmpty() && genderText.isNotEmpty()) {
                val age = ageText.toIntOrNull()
                val weight = weightText.toIntOrNull()

                if (age != null && weight != null) {
                    val myPost = Post(userId = age,id = 0, title = nameText, body = genderText)
                    viewModel.pushPost(myPost)
                } else {
                    Toast.makeText(this, "Invalid age or weight", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }



        viewModel.post.observe(this, Observer { response ->
            if (response != null && response.isSuccessful) {
                Log.d("AddActivity", "Post request successful: ${response.body()}")
                Toast.makeText(this, "Post successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                response?.let {
                    Log.e("AddActivity", "Post request failed: ${it.code()} ${it.message()}")
                    Toast.makeText(this, "Error: ${it.code()}", Toast.LENGTH_SHORT).show()
                } ?: run {
                    Log.e("AddActivity", "Failed to get response")
                    Toast.makeText(this, "Failed to get response", Toast.LENGTH_SHORT).show()
                }
            }
        })





        val genderItems = resources.getStringArray(R.array.gender)
        val gender_adapter = ArrayAdapter(this, R.layout.list_item_gender,genderItems)
         binding.addgenderAutoComplete.setAdapter(gender_adapter)

        binding.profileNotSelectedAdd.setOnClickListener { startActivity(Intent(this@AddActivity, ProfileActivity::class.java)) }
        binding.homeNotSelectedAdd.setOnClickListener { startActivity(Intent(this@AddActivity, HomeActivity::class.java)) }

        binding.addpicEditText.setOnClickListener {
            pickImageGallery()
        }


    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
        binding.petPicHolderAdd.setImageURI(data?.data)
        }
    }
}