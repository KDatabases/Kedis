package com.sxtanna.database

import com.sxtanna.database.config.KedisConfig
import com.sxtanna.database.task.KedisTask
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

class Kedis(override val config : KedisConfig) : Database<Jedis, KedisConfig>() {

	override val name : String = "Kedis"
	lateinit var pool : JedisPool
		private set


	override fun load() {
		val jedisConfig = JedisPoolConfig().apply {
			maxTotal = config.pool.maxSize
			maxIdle = config.pool.idleSize
			testOnBorrow = true
		}

		pool = JedisPool(jedisConfig, config.user.address, config.user.port, config.pool.timeout, config.user.auth)
	}

	override fun poison() {
		pool.destroy()
	}


	override fun poolResource() : Jedis? = pool.resource

	override fun createTask(resource : Jedis) : KedisTask = KedisTask(resource)

}