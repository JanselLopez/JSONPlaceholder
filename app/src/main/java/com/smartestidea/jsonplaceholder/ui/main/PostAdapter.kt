package com.smartestidea.jsonplaceholder.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartestidea.jsonplaceholder.data.model.Post
import com.smartestidea.jsonplaceholder.databinding.CvPostBinding
import com.smartestidea.jsonplaceholder.ui.comment.CommentsActivity

class PostAdapter(
    private val posts:List<Post>
):RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private lateinit var context:Context

    inner class PostViewHolder(val binding: CvPostBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        context = parent.context
        val binding = CvPostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        with(holder){
            with(posts[position]){
                 binding.tvTitle.text = title
                binding.tvBody.text = body
                binding.btnGoToComments.setOnClickListener {
                    Intent(context,CommentsActivity::class.java).apply {
                        putExtra("postId",id)
                        context.startActivity(this)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = posts.size


}