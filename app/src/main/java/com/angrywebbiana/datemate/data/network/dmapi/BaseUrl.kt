package com.angrywebbiana.datemate.data.network.dmapi

class BaseUrl {
    companion object {
        const val DM_API_BASE_URL = "http://ec2-54-227-64-212.compute-1.amazonaws.com:8080/datemate/api/"

        const val DM_API_POST_SIGN_UP = "member/signup"
        const val DM_API_POST_LOGIN = "member/signin"

        const val DM_API_GET_FOLLOWERS_LIST = "user/friend"
        const val DM_API_GET_USER_PROFILE = "user/profile"
        const val DM_API_POST_ADD_FRIEND = "user/friend"

        const val DM_API_GET_GROUP = "group"
        const val DM_API_POST_GROUP_CREATE = "group"
        const val DM_API_GET_GROUP_LIST = "group/list"
        const val DM_API_POST_GROUP_INVITE = "group/invite"
        const val DM_API_POST_GROUP_LEAVE = "group/leave"
    }
}