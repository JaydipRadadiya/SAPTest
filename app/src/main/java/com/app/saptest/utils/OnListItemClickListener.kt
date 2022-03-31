package com.app.saptest.utils

import com.app.saptest.model.UserModel

interface OnListItemClickListener {
    fun onItemClick(userModel: UserModel)
}