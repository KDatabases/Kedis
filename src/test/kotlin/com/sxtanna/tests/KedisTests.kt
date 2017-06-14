package com.sxtanna.tests

import com.sxtanna.database.Kedis
import java.io.File
import kotlin.system.measureTimeMillis

class KedisTests(file : File) {

	val kedis = Kedis[file]


	fun enable() = kedis.enable()

	fun disable() = kedis.disable()


	fun runTest() = kedis {
		set("Hello", "World")
		setHash("users", "Hello", "World")

		println("Value ${get("Hello")}")
		println("Hash Value ${getHash("users", "Hello")}")
	}

}

fun main(args : Array<String>) {
	val test = KedisTests(File("RedisConfig.json"))

	val entireTestTime = measureTimeMillis {
		println("Enable took ${measureTimeMillis {
			test.enable()
		}}")

		println("Test took ${measureTimeMillis {
			test.runTest()
		}}")

		println("Disable took ${measureTimeMillis {
			test.disable()
		}}")
	}

	println("Entire Test took $entireTestTime")
}