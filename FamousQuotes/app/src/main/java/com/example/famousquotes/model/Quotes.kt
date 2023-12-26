package com.example.famousquotes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

//@StringRes & @DrawableRes are there so that the caller doesn't swap the parameters
data class Quotes(@StringRes val stringResourcesId: Int,
                  @DrawableRes val imageResourceId: Int) {
}