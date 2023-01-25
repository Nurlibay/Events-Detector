package uz.nurlibaydev.eventdetector.presentation.dialog

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.nurlibaydev.eventdetector.presentation.main.MainViewModel
import uz.nurlibaydev.eventdetector.presentation.main.MainViewModelImpl
import uz.unidev.eventdetector.BuildConfig
import uz.unidev.eventdetector.R
import uz.unidev.eventdetector.databinding.ScreenBottomSheetBinding

@AndroidEntryPoint
class MenuBottomSheet : BottomSheetDialogFragment() {

    private val binding: ScreenBottomSheetBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.screen_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            containerEnableAllEvents.setOnClickListener {
                viewModel.enableAllEvents()
                requireDialog().dismiss()
            }
            containerDisableAllEvents.setOnClickListener {
                viewModel.disableAllEvents()
                requireDialog().dismiss()
            }
            containerShareApp.setOnClickListener {
                shareApp()
                requireDialog().dismiss()
            }
            containerRateApp.setOnClickListener {
                rateApp()
                requireDialog().dismiss()
            }
            containerQuitApp.setOnClickListener {
                requireActivity().finish()
            }
        }
    }

    private fun shareApp() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name)
            var shareMessage = "Events detector app".trim() + "\n"
            shareMessage = "${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}".trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share app"))
        } catch (e: java.lang.Exception) {
            e.toString()
        }
    }

    private fun rateApp() {
        val uri: Uri = Uri.parse("market://details?id=uz.unidev.eventdetector")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=uz.unidev.eventdetector")
                )
            )
        }
    }
}