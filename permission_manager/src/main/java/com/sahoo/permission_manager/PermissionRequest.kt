package com.sahoo.permission_manager

/**
 * @permission : list of permission to request
 * @permission : rationale why we need this permission
 */
data class PermissionRequest(val permissions : List<String>, val rationaleMessage:String? = null)
