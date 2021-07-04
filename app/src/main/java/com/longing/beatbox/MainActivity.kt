package com.longing.beatbox

import android.os.Bundle
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.longing.beatbox.databinding.ActivityMainBinding
import com.longing.beatbox.databinding.ListItemSoundBinding

class MainActivity : AppCompatActivity() {


    private lateinit var soundAdapter: SoundAdapter
    private val beatBoxViewModel: BeatBoxViewModel by lazy {
        ViewModelProvider(this, BeatBoxViewModelFactory(assets))
            .get(BeatBoxViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        soundAdapter = SoundAdapter(beatBoxViewModel.beatBox.sounds)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            val gridSpacing = resources.getDimensionPixelSize(R.dimen.grid_spacing)
            addItemDecoration(GridSpacingDecoration(gridSpacing))
            adapter = soundAdapter
        }
        binding.playbackSpeedLabel.text =
            resources.getString(R.string.play_speed_rate_label, binding.speedSeekBar.progress)

        binding.speedSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.playbackSpeedLabel.text =
                    resources.getString(R.string.play_speed_rate_label, progress)
                beatBoxViewModel.beatBox.rate = progress.toFloat() / 100 + 1f
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }


    private inner class SoundHolder(
        private val binding: ListItemSoundBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewModel = SoundViewModel(beatBoxViewModel.beatBox)

        }

        fun bind(sound: Sound) {
            binding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
        }
    }

    private inner class SoundAdapter(private val sounds: List<Sound>) :
        RecyclerView.Adapter<SoundHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            )

            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            holder.bind(sounds[position])
        }

        override fun getItemCount(): Int = sounds.size
    }


}