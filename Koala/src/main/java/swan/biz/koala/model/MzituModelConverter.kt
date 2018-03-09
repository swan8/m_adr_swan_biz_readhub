package swan.biz.koala.model

import org.jsoup.Jsoup

/**
 * Created by stephen on 18-3-9.
 */
class MzituModelConverter {

    companion object {

        const val LIST = "ul[id=pins]"
    }

    fun mConverterList(xml: String?): MutableList<MzituImage> {
        val list: MutableList<MzituImage> = mutableListOf<MzituImage>()

        xml?.let {
            Jsoup.parse(xml)
        }.let {
            it?.select(LIST)
        }?.first()?.select("li")?.map {
            val image: MzituImage = MzituImage()
            image.url = it?.select("a")?.first()?.attr("href")
            image.original = it?.select("img")?.first()?.attr("data-original")
            image.title = it?.select("img")?.first()?.attr("alt")
            image.date = it?.select("span[class=time]")?.first()?.html()
            image.views = it?.select("span[class=view]")?.first()?.html()

            list.add(image)
        }

        return list
    }
}