package com.example.cachetest

import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite

@Suite
@SelectClasses(PkSelectTest::class, IndexSelectTest::class, AllSelectTest::class)
class AllTest

@Suite
@SelectClasses(SpringTest::class, NonTest::class, TemplateTest::class)
class PkSelectTest

@Suite
@SelectClasses(Spring2Test::class, NonTest2::class, TemplateTest2::class)
class IndexSelectTest

@Suite
@SelectClasses(Spring3Test::class, NonTest3::class, TemplateTest3::class)
class AllSelectTest

@Suite
@SelectClasses(SpringHit0::class, SpringHit10::class, SpringHit20::class, SpringHit50::class, SpringHit70::class, SpringHit100::class)
class SpringTest

@Suite
@SelectClasses(Spring2Hit0::class, Spring2Hit10::class, Spring2Hit20::class, Spring2Hit50::class, Spring2Hit70::class, Spring2Hit100::class)
class Spring2Test

@Suite
@SelectClasses(CacheTestApplicationTests3::class)
class Spring3Test

@Suite
@SelectClasses(NonCacheTestApplicationTests::class)
class NonTest

@Suite
@SelectClasses(NonCacheTestApplicationTests2::class)
class NonTest2

@Suite
@SelectClasses(NonCacheTestApplicationTests3::class)
class NonTest3

@Suite
@SelectClasses(RedisHit0::class, RedisHit10::class, RedisHit20::class, RedisHit50::class, RedisHit70::class, RedisHit100::class)
class TemplateTest

@Suite
@SelectClasses(Redis2Hit0::class, Redis2Hit10::class, Redis2Hit20::class, Redis2Hit50::class, Redis2Hit70::class, Redis2Hit100::class)
class TemplateTest2

@Suite
@SelectClasses(RedisTestApplicationTests3::class)
class TemplateTest3