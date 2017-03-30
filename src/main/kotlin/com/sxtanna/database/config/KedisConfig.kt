package com.sxtanna.database.config

import com.sxtanna.database.config.KedisConfig.*

data class KedisConfig(val server : ServerOptions = ServerOptions(), val user : UserOptions = UserOptions(), val pool : PoolOptions = PoolOptions()) : DatabaseConfig {

	data class ServerOptions(val address : String = "", val port : Int = 6379)

	data class UserOptions(val auth : String = "", val defaultDB : Int = 0)

	data class PoolOptions(val maxSize : Int = 200, val idleSize : Int = 200, val timeout : Int = 0)


	companion object {

		val DEFAULT = KedisConfig()

	}

}