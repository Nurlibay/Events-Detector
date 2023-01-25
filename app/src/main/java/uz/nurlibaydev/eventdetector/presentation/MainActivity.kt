package uz.nurlibaydev.eventdetector.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.nurlibaydev.eventdetector.navigation.NavigationHandler
import uz.unidev.eventdetector.R
import uz.unidev.eventdetector.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navigationHandler.navStack
            .onEach { it.invoke(fragment.findNavController()) }
            .launchIn(lifecycleScope)
    }
}