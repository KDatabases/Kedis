package com.sxtanna.database

import com.sxtanna.database.base.Database
import com.sxtanna.database.config.DatabaseConfigManager
import com.sxtanna.database.config.KedisConfig
import com.sxtanna.database.ext.loadOrSave
import com.sxtanna.database.task.KedisTask
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import java.io.File

class Kedis(override val conf : KedisConfig) : Database<Jedis, KedisConfig, KedisTask>() {

	override val name : String = "Kedis"
	lateinit var pool : JedisPool
		private set


	override fun load() {
		val jedisConfig = JedisPoolConfig().apply {
			maxTotal = conf.pool.maxSize
			maxIdle = conf.pool.idleSize
			testOnBorrow = true
		}

		pool = JedisPool(jedisConfig, conf.server.address, conf.server.port, conf.pool.timeout, conf.user.auth)
	}

	override fun poison() = pool.destroy()


	override fun poolResource() : Jedis? = pool.resource?.apply { select(conf.user.defaultDB) }

	override fun createTask(resource : Jedis) : KedisTask = KedisTask(resource)


	companion object : DatabaseConfigManager<KedisConfig, Kedis> {

		@JvmStatic
		override fun get(file : File) = Kedis(getConfig(file))

		@JvmStatic
		override fun getConfig(file : File) = file.loadOrSave(KedisConfig.DEFAULT)

	}

}