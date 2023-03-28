package com.example.automotomaintenance.bottomdialog.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.automotomaintenance.model.Company
import com.example.automotomaintenance.repository.FifeBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemShowViewModel @Inject constructor() : ViewModel(), FifeBaseRepository.Listener {

    @Inject
    lateinit var fifeBaseRepository: FifeBaseRepository
    val infoCompany = MutableLiveData<List<Company>>()
    val updateSuccess = MutableLiveData<Boolean>()
    val validFail = MutableLiveData<Boolean>()
    private val onCompanyUpdated = MutableLiveData<Boolean>()

    fun addCompanyListener() {
        fifeBaseRepository.addCompanyListener(this)
    }

    fun removeCompanyListener() {
        fifeBaseRepository.removeCompanyListener(this)
    }

    fun getItemCompany(idCompany: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val companyInfo = fifeBaseRepository.loadOneCompany(idCompany)

            withContext(Dispatchers.Main) {
                infoCompany.postValue(companyInfo)
            }
        }
    }

    fun validUpdateCompany(oldInfoCompany: Map<String, String>) {
        val idCompany: String = oldInfoCompany["idCompany"]!!
        val name = oldInfoCompany["name"]
        val phone = oldInfoCompany["phone"]
        val human = oldInfoCompany["human"]
        val information = oldInfoCompany["information"]
        val address = oldInfoCompany["address"]

        if (name.isNullOrBlank() || information.isNullOrBlank() || phone.isNullOrBlank()) {
            validFail.postValue(false)
        } else {
            fifeBaseRepository.updateCompany(
                idCompany,
                name,
                information,
                phone,
                human,
                address
            )
            updateSuccess.postValue(true)
        }
    }

    override fun onCompanyUpdated(updateCompany: Company) {
        onCompanyUpdated.postValue(true)
    }
}