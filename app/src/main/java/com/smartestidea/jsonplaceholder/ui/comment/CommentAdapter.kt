package com.smartestidea.jsonplaceholder.ui.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.smartestidea.jsonplaceholder.data.model.Comment
import com.smartestidea.jsonplaceholder.databinding.CvCommentBinding
import dagger.hilt.android.lifecycle.HiltViewModel

class CommentAdapter(
    private val comments:List<Comment>
):RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(val binding: CvCommentBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = CvCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        with(holder){
            with(comments[position]){
                binding.tvName.text = name
                binding.tvEmail.text = email
                binding.tvBody.text = body
            }
        }
    }

    override fun getItemCount(): Int = comments.size

}