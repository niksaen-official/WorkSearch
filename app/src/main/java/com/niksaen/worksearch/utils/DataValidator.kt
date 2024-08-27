package com.niksaen.worksearch.utils

import java.util.regex.Pattern

class DataValidator {
    companion object{
        private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        /**
         * Use with method for verification email*/
        fun emailVerification(email:String):Boolean{
            return if(email.isNotEmpty()){
                EMAIL_ADDRESS_PATTERN.matcher(email).matches()
            }
            else false
        }
    }
}