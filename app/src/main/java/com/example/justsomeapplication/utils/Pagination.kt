package com.example.justsomeapplication.utils

import retrofit2.Response

private const val HEADER_LINK = "Link"
private const val META_REL = "rel"
private const val META_LAST = "last"
private const val META_NEXT = "next"
private const val META_FIRST = "first"
private const val META_PREV = "prev"
private const val DELIM_LINKS = ","
private const val DELIM_LINK_PARAM = ";"

class Pagination(response: Response<*>) {
    var first: Int = 0
    var last: Int = 0
    var next: Int = 0
    var prev: Int = 0

    init {
        val linkHeader: String? = response.headers().get(HEADER_LINK)

        if (linkHeader != null) {
            val links = linkHeader.split(DELIM_LINKS).toTypedArray()

            for (link in links) {
                val segments = link.split(DELIM_LINK_PARAM).toTypedArray()

                if (segments.size < 2) continue

                var linkPart = segments[0].trim { it <= ' ' }

                if (!linkPart.startsWith("<") || !linkPart.endsWith(">"))
                    continue

                linkPart = linkPart.substring(1, linkPart.length - 1)

                for (i in 1 until segments.size) {
                    val rel =
                        segments[i].trim { it <= ' ' }.split("=").toTypedArray()

                    if (rel.size < 2 || META_REL != rel[0]) continue

                    var relValue = rel[1]

                    if (relValue.startsWith("\"") && relValue.endsWith("\""))
                        relValue = relValue.substring(1, relValue.length - 1)

                    val pageNum =
                        linkPart.substring(linkPart.lastIndexOf("&page=") + 6).toInt()

                    when (relValue) {
                        META_FIRST -> first = pageNum
                        META_LAST -> last = pageNum
                        META_NEXT -> next = pageNum
                        META_PREV -> prev = pageNum
                    }
                }
            }
        }
    }
}
