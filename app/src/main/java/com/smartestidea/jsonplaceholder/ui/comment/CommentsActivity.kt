package com.smartestidea.jsonplaceholder.ui.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartestidea.jsonplaceholder.core.CheckInternet
import com.smartestidea.jsonplaceholder.databinding.ActivityCommentsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsActivity : AppCompatActivity() {

    private val binding:ActivityCommentsBinding by lazy{ ActivityCommentsBinding.inflate(layoutInflater)}

    private val commentViewModel:CommentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val postId:Int = intent.extras!!.getInt("postId")

        commentViewModel.onCreate(postId,CheckInternet(this))

        binding.rvComments.layoutManager = LinearLayoutManager(this)

        commentViewModel.comments.observe(this,{
            binding.rvComments.adapter = CommentAdapter(it)
        })

        commentViewModel.loading.observe(this,{
            binding.shimmer.isVisible = it
            binding.rvComments.isVisible = !it
        })
    }
}