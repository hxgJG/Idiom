package com.hxg.idiom

import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.ConcurrentHashMap
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println(" \n\n------------------------------------------------------------")

        val testString =
            " 樽酒论文  拼音：zūn jiǔ lùn wén释义：一边喝酒，一边议论文章。出处：唐·杜甫《春日忆李白》诗何时一樽酒，重与细论文。”示例：连年客里度初度，～第一遭。★陈世宜《上巳社集是日值余初度》诗\n"

        val testStr2 = " 举十知九拼音：jǔ shí zhī jiǔ释义：列举出的十件事情中，通晓的就有九件。比喻学识渊博。出处：唐·张说《唐故豫州刺史魏君神道碑》圣人之所志，闻一而反三；君子之所能，举十而知九。”示例：德才兼备，～的教师，在学生中间自然有一种崇高的威望。"

        val s1 = Integer.toHexString('拼'.code)
        val s2 = Integer.toHexString('音'.code)
        val s3 = Integer.toHexString('：'.code)
        val s4 = Integer.toHexString(' '.code)
        println("[hxg] s1 = $s1, s2 = $s2, s3 = $s3, s4 = $s4")

        val REGEX_1 =" ([\u4e00-\u9fa5]+)  \u62fc\u97f3\uff1a*"
        val REGEX_2 =" ([\u4e00-\u9fa5]+)\u62fc\u97f3\uff1a*"

        val matcher: Matcher = compile(REGEX_1).matcher(testString)

//        while (matcher.find()) {
//            val group = matcher.group()
//            println("1 group = $group")
//        }
        matcher.find()
        val group = matcher.group()
        println("1 group = $group")

        val matcher2: Matcher = compile(REGEX_2).matcher(testStr2)

        while (matcher2.find()) {
            val group = matcher2.group()
            println("2 group = $group")
        }
        println("------------------------------------------------------------\n\n ")
    }

    companion object {
        private val patterns = ConcurrentHashMap<String, Pattern>();

        fun compile(regex: String): Pattern {
            var cache: Pattern? = patterns[regex]
            if (cache != null) {
                return cache
            }
            cache = Pattern.compile(regex)
            patterns[regex] = cache
            return cache
        }
    }


    @Test
    fun test() {
        val ascii = 97

        val char:Char = ascii.toChar()

        val fs = 12288.toChar().toString()

        println("----------------------------")
        println("ascii:$ascii, char:$char")
        println("fs:$fs")
        println("----------------------------")
    }
}