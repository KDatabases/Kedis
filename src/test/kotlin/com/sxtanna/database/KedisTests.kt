package com.sxtanna.database

import java.io.File
import kotlin.system.measureTimeMillis

class KedisTests(file : File) {

	val kedis = Kedis[file]


	fun enable() = kedis.enable()

	fun disable() = kedis.disable()


	fun runTest() = kedis {
		set("Hello", "World")
	}

}

fun main(args : Array<String>) {
	val rootFolder = File("").absoluteFile
	val test = KedisTests(File(rootFolder, "Config.json"))

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