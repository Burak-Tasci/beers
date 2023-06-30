package com.tsci.beers.ext

/**
 * Created by tasci on 30.06.2023.
 */

fun List<String>.extractBulletPoints(fontStyle: String = ""): String {
    val bulletPoints = joinToString(separator = "\n") {
        it.wrapInBulletPoint(fontStyle)
    }
    val html = "   <ul>\n" +
            bulletPoints +
    "   </ul>"
    return html
}

private fun String.wrapInBulletPoint(fontStyle: String = ""): String {
    if (fontStyle.isNotEmpty()){
        return "<li style=\"font-family:$fontStyle\">$this</li>"
    } else {
        return "<li>$this</li>"
    }
}