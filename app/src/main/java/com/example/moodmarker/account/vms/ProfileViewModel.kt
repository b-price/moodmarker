package com.example.moodmarker.account.vms

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodmarker.db.IMoodMarkerRepository
import com.example.moodmarker.db.MoodMarkerRepository
import com.example.moodmarker.db.entities.User
import kotlinx.coroutines.launch

/** Viewmodel for profile screen - Not currently in use **/
class ProfileViewModel(app: Application): AndroidViewModel(app) {
    private val _userList: MutableState<List<User>> = mutableStateOf(listOf())
    val userList: State<List<User>> = _userList
    private val _repository: IMoodMarkerRepository = MoodMarkerRepository(getApplication())

    private var _userToBeDeleted: User?
    private val _showDialog: MutableState<Boolean>
    val showDialog: State<Boolean>
    private var _isEdit: Boolean = false


    private var _exists: Boolean = false

    private var _emptyUser: User

    init {
        viewModelScope.launch {
            _userList.value = _repository.getUsers()
        }
        _userToBeDeleted = null
        _showDialog = mutableStateOf(false)
        showDialog = _showDialog

        _emptyUser  = User(0, "", "", "", "", "")
    }

    fun getUsers(): List<User>{
        return _userList.value
    }

    fun deleteUser(user: User){
        if (_userToBeDeleted == null){
            return
        }
        viewModelScope.launch {
            _repository.deleteUser(_userToBeDeleted!!)
            _userList.value = _repository.getUsers()
            dismissDialog()
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch {
            _repository.updateUser(user)
            _userList.value = _repository.getUsers()
        }
    }

    fun dismissDialog() {
        _userToBeDeleted = null
        _showDialog.value = false
    }

    fun prepareDelete(user: User) {
        _userToBeDeleted = user
        _showDialog.value = true
    }

    fun setIsEdit() {
        _isEdit = !_isEdit
    }

    fun getIsEdit(): Boolean {
        return _isEdit
    }


}