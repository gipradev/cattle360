package com.android.cattle360.ui.executive.addCattle.enterLocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.android.cattle360.R
import com.android.cattle360.data.apiResponse.DataState
import com.android.cattle360.data.apiResponse.DataDistrict
import com.android.cattle360.data.apiResponse.DataArea
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.EnterLocationFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository


class EnterLocationFragment : BaseFragment<EnterLocationViewModel, EnterLocationFragmentBinding, AddCattleRepository>() {

    var invalid: Boolean = false
    var data: List<DataState>? = null

    lateinit var district_id:String
    private var stateList = mutableListOf<String>()
 //   private  var districtList = mutableListOf<String>()
    private  var areaList = mutableListOf<String>()

    var  selectedText:String = ""
    var  selectedTextdist:String = ""

    companion object {
        fun newInstance() = EnterLocationFragment()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): EnterLocationFragmentBinding {
        return EnterLocationFragmentBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<EnterLocationViewModel> {
        return EnterLocationViewModel::class.java
    }

    override fun getFragmentRepository(): AddCattleRepository {
        return AddCattleRepository(remoteDataSource.buildApi(ApiService::class.java))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addObserver()
        stateLoading()
        //districtLoading()
     // areaLoading()

        binding.locationNextButton.setOnClickListener(View.OnClickListener {
            val state_value = binding.stateEditText.text.toString()
            val district_value = binding.districtEditText.text.toString()
            val area_value = binding.areaEditText.text.toString()
            val pincode_value = binding.pincodeEditText.text.toString()


            val enterLocationFragment = EnterLocationFragment()
            val args = Bundle()
            args.putString("state_value", state_value)
            args.putString("district_value", district_value)
            args.putString("area_value", area_value)
            args.putString("pincode_value", pincode_value)
            enterLocationFragment.arguments = args

            NavHostFragment.findNavController(requireParentFragment())
                        .navigate(R.id.action_enterLocationFragment_to_enterCattleFragment,args)

        })

        binding.locationBackButton.setOnClickListener {
            requireActivity().onBackPressed()
//            NavHostFragment.findNavController(requireParentFragment())
//                .navigate(R.id.action_enterLocationFragment_to_enterCattleFragment)
        }

    }

    private fun stateLoading() = viewModel.state()


    private fun addObserver() {
        viewModel.stateResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    println("Loading ")
                }
                is Resource.Success -> {
                    if (it.value?.status.equals("1")) {

                        val data: List<DataState>? = it.value?.data

                        stateList = mutableListOf<String>()

                        if (data != null) {
                            for (state in data) {
                                stateList.add(state.state_name)
                            }
                           selectedText = stateList.first() // as default
                            val arrayAdapter = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                stateList)
                            binding.stateEditText.setAdapter(arrayAdapter)
                            binding.stateEditText.threshold = 1
                            binding.stateEditText.onItemClickListener=AdapterView.OnItemClickListener{
                                    parent, view, position, id ->
                               // selectedTextdist = districtList.first()
                                //selectedTextdist = stateList.first()
                                selectedText = data[position].state_code
                                println("..................selected............................$selectedText")
                                viewModel.district(selectedText)
                                addObserverForDistrict()
                            }
                 println(".....................................................................select$selectedText")

                }
                    }
                }
                is Resource.Failure -> {
                    println("Failure  : $it")
                }

            }
        })

    }

    private fun addObserverForDistrict() {

        viewModel.districtResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    println("Loading ")
                }
                is Resource.Success -> {
                    if (it.value?.status.equals("1")) {

                        val data: List<DataDistrict>? = it.value?.data

                       val districtList = mutableListOf<String>()

                        if (data != null) {
                            for (district in data) {
                                districtList.add(district.district_name)
                                //district_id=district.district_id
                            }
                            selectedTextdist = districtList.first() // as default
                            val arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, districtList)
                            binding.districtEditText.setAdapter(arrayAdapter)
                            binding.districtEditText.threshold = 1
                            binding.districtEditText.onItemClickListener=AdapterView.OnItemClickListener{
                                    _, _, position, _ ->
                                selectedTextdist = data[position].district_id
                                println("..................selected...dist_id......................$selectedTextdist")
                                viewModel.area(selectedTextdist)
                                addObserverForArea()
                            }

                        }
                    }
                }
                is Resource.Failure -> {
                    println("Failure  : $it")
                }

            }
        })
    }

    private fun addObserverForArea() {
        viewModel.areaResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    println("Loading ")
                }
                is Resource.Success -> {
                    if (it.value?.status.equals("1")) {
                        val data: List<DataArea>? = it.value?.data
                        val areaList = mutableListOf<String>()
                        if (data != null) {
                            for (area in data) {
                                areaList.add(area.c_area_name)
                                // var area_id=area.n_area_id
                            }

                            val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, areaList)
                            binding.areaEditText.setAdapter(arrayAdapter)
                            binding.areaEditText.threshold = 1
                            binding.areaEditText.onItemClickListener=AdapterView.OnItemClickListener{
                                    _, _, position, _ ->
                              var  selected = data[position].c_area_name
                                println("..................areaselected......................$selected")

                            }

                        }
                        println("........${data}")
                    }
                }
                is Resource.Failure -> {
                    println("Failure  : $it")
                }

            }
        })

    }


}








