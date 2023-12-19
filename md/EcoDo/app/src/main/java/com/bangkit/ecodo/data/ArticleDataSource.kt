package com.bangkit.ecodo.data

object ArticleDataSource {
    val articles: List<Article> = listOf(
        Article(
            title = "5 Cara Mengurangi Sampah Plastik. Yuk, Mulai dari Sekarang!",
            url = "https://sohib.indonesiabaik.id/article/cara-mengurangi-sampah-plastik-CKZxS",
            imageUrl = "https://sohib.indonesiabaik.id/thumbnail/article-730/articles/2022/11/18/cara-mengurangi-sampah-plastik-djjCSX0ARK.jpg"
        ),
        Article(
            title = "Tips Hemat Kertas Demi Lingkungan",
            url = "https://environment-indonesia.com/tips-hemat-kertas-demi-lingkungan/",
            imageUrl = "https://environment-indonesia.com/wp-content/uploads/2015/06/best-ways-to-save-paper.jpg.webp"
        ),
        Article(
            title = "Ways to Reuse Cardboard and Why it is Important",
            url = "https://www.colorado.edu/ecenter/2021/02/09/ways-reuse-cardboard-and-why-it-important",
            imageUrl = "https://www.colorado.edu/ecenter/sites/default/files/styles/large/public/callout/cardboard_bales.png?itok=bvykKAYP"
        ),
        Article(
            title = "3R (Reuse Reduce Recycle) Sampah",
            url = "https://indonesiasustainability.com/reduce-reuse-recycle-adalah-contohnya/",
            imageUrl = "https://umumsetda.bulelengkab.go.id/public/uploads/konten/3r-reuse-reduce-recycle-sampah-49.jpg"
        ),
        Article(
            title = "Reduce Reuse Recycle Adalah: Pengertian Dan Contohnya",
            url = "https://indonesiasustainability.com/reduce-reuse-recycle-adalah-contohnya/",
            imageUrl = "https://indonesiasustainability.com/wp-content/uploads/2022/07/Reduce-Reuse-Recycle-Adalah-Pengertian-Dan-Contohnya-1.jpg"
        ),
    )
}

data class Article(
    val title: String,
    val url: String,
    val imageUrl: String,
)