package com.sxtanna.database.task

import redis.clients.jedis.Jedis

class KedisTask(override val resource : Jedis) : DatabaseTask<Jedis>() {


	//region Basic Key
	operator fun set(key: String, value: Any) {
		resource.set(key, value.toString())
	}

	operator fun get(key: String): String? {
		return resource.get(key)
	}

	operator fun contains(key: String): Boolean {
		return resource.exists(key)
	}

	fun rem(vararg key: String): Boolean {
		return resource.del(*key).toInt() == key.size
	}
	//endregion


	//region Hash
	fun setHash(key: String, hashKey: String, value: Any) {
		resource.hset(key, hashKey, value.toString())
	}

	fun getHash(key: String, hashKey: String): String? {
		return resource.hget(key, hashKey)
	}

	fun getHash(key : String): Map<String, String> {
		return resource.hgetAll(key)
	}

	fun getHashSize(key : String): Int {
		return resource.hlen(key).toInt()
	}

	fun hasHash(key: String, hashKey: String): Boolean {
		return resource.hexists(key, hashKey)
	}

	fun remHash(key: String, vararg hashKey: String): Boolean {
		if (hashKey.isEmpty()) return rem(key)
		return resource.hdel(key, *hashKey).toInt() == hashKey.size
	}
	//endregion


	//region Sorted Set
	fun setZRank(key: String, rank: Long, value: Any) {
		resource.zadd(key, rank.toDouble(), value.toString())
	}

	fun getZRank(key: String, value: Any): Long? {
		return resource.zrank(key, value.toString())
	}

	fun getZRankSize(key : String): Int {
		return resource.zcard(key).toInt()
	}

	fun hasZRank(key: String, value: Any): Boolean {
		return getZRank(key, value) != null
	}

	fun remZRank(key: String, vararg value: String): Boolean {
		if (value.isEmpty()) return rem(key)

		return resource.zrem(key, *value).toInt() == value.size
	}
	//endregion


	//region UnSorted Set
	fun setSet(key : String, vararg value : Any) {
		resource.sadd(key, *value.toStrings())
	}

	fun getSet(key : String): Set<String> {
		return resource.smembers(key)
	}

	fun getSetSize(key : String): Int {
		return resource.scard(key).toInt()
	}

	fun hasSet(key : String, value : Any): Boolean {
		return resource.sismember(key, value.toString())
	}

	fun remSet(key : String, vararg value : Any) {
		resource.srem(key, *value.toStrings())
	}
	//endregion


	fun push(channel: String, message: String) {
		resource.publish(channel, message)
	}


	private fun Array<out Any>.toStrings() = this.map(Any::toString).toTypedArray()
	
}