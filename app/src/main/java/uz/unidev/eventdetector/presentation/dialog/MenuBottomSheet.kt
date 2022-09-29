package uz.unidev.eventdetector.presentation.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.unidev.eventdetector.R
import uz.unidev.eventdetector.databinding.ScreenBottomSheetBinding

class MenuBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: ScreenBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_bottom_sheet, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenBottomSheetBinding.bind(view)
    }
}