package com.sxtanna.database.config

import com.sxtanna.database.config.KedisConfig.PoolOptions
import com.sxtanna.database.config.KedisConfig.UserOptions

data class KedisConfig(val user : UserOptions = UserOptions("", 6379, ""), val pool : PoolOptions = PoolOptions()) : DatabaseConfig {

	data class UserOptions(val address : String, val port : Int, val auth : String)

	data class PoolOptions(val maxSize : Int = 200, val idleSize : Int = 200, val timeout : Int = 0)


	companion object {

		val DEFAULT = KedisConfig()

	}

}