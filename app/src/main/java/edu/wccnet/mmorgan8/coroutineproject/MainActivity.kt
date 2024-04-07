package edu.wccnet.mmorgan8.coroutineproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import edu.wccnet.mmorgan8.coroutineproject.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    private lateinit var viewModel: MainViewModel

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerView.layoutManager = layoutManager

        adapter = RecyclerAdapter()

        binding.recyclerView.adapter = adapter

        binding.button .setOnClickListener{
            coroutineScope.launch(Dispatchers.Main) {
                addName()
            }
        }
    }

    suspend fun addName(){
        val name = binding.enterName.text.toString()

        binding.enterName.text.clear()

        binding.enterName.requestFocus()

        val delay = ((0..9)).random() * 1000

        delay(delay.toLong())

        val output = "The name is " + name + " and the delay was " + delay + " milliseconds"

        viewModel.addCardText(output)

        adapter?.notifyDataSetChanged()
    }

}