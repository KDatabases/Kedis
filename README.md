# Kedis
Redis Kotlin wrapper based on Jedis

## How to get it!

### Maven
```xml
<dependency>
    <groupId>com.sxtanna.database</groupId>
    <artifactId>Kedis</artifactId>
    <version>LATEST</version>
</dependency>
```

### Gradle
```groovy
compile "com.sxtanna.database:Kedis:+"
```

## How it works!

### 1-0. To create a new instance of [Kedis](src/main/kotlin/com/sxtanna/database/Kedis.kt), you would follow this syntax

#### From Kotlin
``` kotlin
val kedis = Kedis[file: File]
```

#### From Java
``` java
final Kedis kedis = Kedis.get(file: File);
```

### 1-1. To initialize and shutdown a database use these two methods
``` kotlin
kedis.enable()
```
and
``` kotlin
kedis.disable()
```


### 2-0. After you have an instance, to get a resource

#### From Kotlin
``` kotlin
val resource = kedis.resource()
```

#### From Java
``` java
final Jedis resource = kedis.resource();
```
*[Database#resource](https://github.com/KDatabases/Core/blob/master/src/main/kotlin/com.sxtanna/database/base/Database.kt#L96) will throw an IllegalStateException if it's unable to create a resource a/o the database isn't enabled*


### 2-1. Or you could utilize the Database's ability to automatically manage the connection with the *invoke* methods

#### From Kotlin
``` kotlin
kedis {                       
	set("Username", "Sxtanna")
}                                                                     
```

#### From Java
``` java
kedis.execute(task -> task.set("Username", "Sxtanna"));
```
