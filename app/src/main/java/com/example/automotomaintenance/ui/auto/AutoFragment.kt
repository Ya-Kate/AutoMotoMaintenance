package com.example.automotomaintenance.ui.auto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.automotomaintenance.R
import com.example.automotomaintenance.databinding.FragmentAutoBinding
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//private const val NUMBER_AUTO = "numberAuto"

@AndroidEntryPoint
class AutoFragment : Fragment() {

    private val viewModel: AutoViewModel by viewModels()
    private lateinit var binding: FragmentAutoBinding

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAutoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: AutoFragmentArgs by navArgs()
        val number = args.autoArg
        viewModel.getOneAuto(number)

        viewModel.oneAuto.observe(viewLifecycleOwner) { list ->
            if(list.isNotEmpty()){
                list.subList(0, 0)
                binding.brand.text = list[0].brand
                binding.number.text = list[0].number
                binding.year.text = list[0].year + ","
                binding.volume.text = list[0].volume + "cc"
            }
        }
        binding.back.setOnClickListener{
            findNavController().navigate(R.id.action_autoFragment_to_ListVehicleFragment)
        }

    }

//    companion object {
//        fun getAuto() :AutoFragment {
//            val number = arguments.getString()
////            AutoFragment().apply {
////                arguments = bundleOf(NUMBER_AUTO to number)
////            }
//        }
//    }
}