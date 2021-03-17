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
import com.android.cattle360.data.apiResponse.Data
import com.android.cattle360.data.apiResponse.DataX
import com.android.cattle360.data.apiResponse.DataXX
import com.android.cattle360.data.network.ApiService
import com.android.cattle360.data.network.Resource
import com.android.cattle360.databinding.EnterLocationFragmentBinding
import com.android.cattle360.ui.base.BaseFragment
import com.android.cattle360.ui.executive.addCattle.AddCattleRepository


class EnterLocationFragment : BaseFragment<EnterLocationViewModel, EnterLocationFragmentBinding, AddCattleRepository>() {

   // var invalid: Boolean = false
    var data: List<Data>? = null
    var state_code: String = ""
    lateinit var district_id:String
    var pos:Int = 0
    private var stateList = mutableListOf<String>()
    private  val areaList = mutableListOf<String>()
    var st:String = ""

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

            when {

                binding.stateEditText.equals("") -> {
                   // invalid = true
                    binding.stateEditText.requestFocus()
                    binding.stateEditText.error = "Please! fill your postal address"
                }

                binding.districtEditText.equals("") -> {
                 //   invalid = true
                    binding.districtEditText.requestFocus()
                    binding.districtEditText.error = "Please! fill your date of birth"

                }

                binding.areaEditText.equals("") -> {
                 //   invalid = true
                    binding.areaEditText.requestFocus()
                    binding.areaEditText.error = "Please! fill your name"

                }

                binding.pincodeEditText.equals("") -> {
                  //  invalid = true
                    binding.pincodeEditText.requestFocus()
                    binding.pincodeEditText.error = "Please! fill your sponsors name"
                }

                else -> {
                 //   invalid = false
                    println("invalid false")
                    val pincode_value = binding.pincodeEditText.text.toString()
                    val area_value = binding.areaEditText.text.toString()
                    val district_value = binding.districtEditText.text.toString()
                    val state_value = binding.stateEditText.text.toString()

                    val enterLocationFragment = EnterLocationFragment()
                    val args = Bundle()
                    args.putString("pincode_value", pincode_value)
                    args.putString("area_value", area_value)
                    args.putString("district_value", district_value)
                    args.putString("state_value", state_value)
                    enterLocationFragment.arguments = args

                    NavHostFragment.findNavController(requireParentFragment())
                        .navigate(R.id.action_enterLocationFragment_to_enterCattleFragment)
                }
            }
        })

        binding.locationBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    private fun stateLoading() = viewModel.state()

    // private fun districtLoading()=
    private fun addObserver() {
        viewModel.stateResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    println("Loading ")
                }
                is Resource.Success -> {
                    if (it.value?.status.equals("1")) {

                        var data: List<Data>? = it.value?.data

                        stateList = mutableListOf<String>()

                        if (data != null) {
                            for (state in data) {
                                stateList.add(state.state_name)
                            }
                            var selectedText = stateList.first() // as default
                            val arrayAdapter = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                stateList)
                            binding.stateEditText.setAdapter(arrayAdapter)
                            binding.stateEditText.threshold = 1

                            binding.stateEditText.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    override fun onNothingSelected(parent: AdapterView<*>?) {
                                        binding.stateEditText.error = "please select your state"
                                    }

                                    override fun onItemSelected(
                                        parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long
                                    ) {
                                        pos = arrayAdapter.getItem(position)?.toInt()!!
                                        selectedText = stateList[pos]

                                        println("..................selected............................$selectedText")
                                    }
                                }

                            var state_cod: String = ""

                             for (state in data) {
                                 stateList.add(selectedText)
                                     state_cod=state.state_code

                        }
                            viewModel.district(state_cod)


                        addObserverForDistrict()

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

                        val data: List<DataX>? = it.value?.data

                        val districtList = mutableListOf<String>()

                        if (data != null) {
                            for (district in data) {
                                districtList.add(district.district_name)
                                district_id=district.district_id
                            }

                            val arrayAdapter =ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, districtList)
                            binding.districtEditText.setAdapter(arrayAdapter)
                            binding.districtEditText.threshold = 1

                            binding.stateEditText.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                    binding.stateEditText.error = "please select your state"
                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                val pos = arrayAdapter.getItem(position)?.toInt()
                                    for (district in data) {

                                        val district= district.district_id[pos!!].toString()
                                        println("..................dist.............................$district")
                                    }
                                }
                            }

                            viewModel.area(district_id)
                            addObserverForArea()
                                    // setDistrictUi(districtList)
                            //println("dist...........................................................$district_id")

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
                        val data: List<DataXX>? = it.value?.data
                        val areaList = mutableListOf<String>()
                        if (data != null) {
                            for (area in data) {
                                areaList.add(area.c_area_name)
                                // var area_id=area.n_area_id
                            }
                            setAreaUi(areaList)


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


    //  private fun areaLoading(district_id: String) =viewModel.area(this.district_id)

    private fun setStateUi(stateList: MutableList<String>) {
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, stateList)
        binding.stateEditText.setAdapter(arrayAdapter)
        binding.stateEditText.threshold = 1

    }

    private fun setDistrictUi(districtList: MutableList<String>) {
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, districtList)
        binding.districtEditText.setAdapter(arrayAdapter)
        binding.districtEditText.threshold = 1

    }

    private fun setAreaUi(areaList: MutableList<String>) {
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, areaList)
        binding.areaEditText.setAdapter(arrayAdapter)
        binding.areaEditText.threshold = 1

    }
}








