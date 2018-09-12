package com.stavro_xhardha.fcbarcelonashqip.brain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by stavro_xhardha on 21/04/2018.
 */

class Brain {
    companion object {
        const val TAG = "BrainTag"

        //Api
        const val HEADER_RESPONSE_CONTROL = "X-StandingResponse-Control"
        const val AUTHORIZATION = "X-Auth-Token"
        const val RESPONSE_HEADER_VALUE = "full"
        const val TOKEN = "88a3894f29564aa09808e373fca97d84"

        //Fragment Tags
        const val WHATS_NEW_ON_CLUB_FRAGMENT_TAG = "What's new on club"
        const val TABLE_FRAGMENT_TAG = "Table Fragment"
        const val TEAM_FRAGMENT_TAG = "Team Fragment"
        const val SCHEDULED_MATCHES_FRAGMENT_TAG = "Scheduled Matches"
        const val HISTORY_MATCH_FRAGMENT_TAG = "History Matches"
        const val FC_BARCELONA_PAGE_FRAGMENT_TAG = "Barcelona Page Fragment"
        const val SPLASH_FRAGMENT_TAG = "Splash Fragment "
        const val LATEST_NEWS_FRAGMENT_TAG = "Latest News"

        //Api
        const val YOUTUBE_API_KEY = "&key=AIzaSyAmxLV2RcpoW2EOe51NbeWvWrRGD7QZgM8"
        const val YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&channelId=UC14UlmYlSNiQCBe9Eookf_A&maxResults=20"
        const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/search?"
        const val YOUTUBE_DETAILS = "part=snippet&order=date&channelId=UC14UlmYlSNiQCBe9Eookf_A&maxResults=20"
        const val YOUTUBE_PAGE_TOKEN = "pageToken="
        const val YOUTUBE_WATCH_URL = "https://www.youtube.com/watch?v="
        const val PLAYERS_URL = "http://api.football-data.org/v1/teams/81/players"
        const val MATCH_URL = "http://api.football-data.org/v1/teams/81/fixtures"
        const val NEWS_URL = "http://press.snet-al.com/index_sport.php?/api/get_latest_news/"
        const val NEWS_BODY = "http://press.snet-al.com/index_sport.php?/api/get/"
        const val VIEWS_URL = "http://press.snet-al.com/index_sport.php?/api/viewed/"
        const val TABLE_URL = "http://api.football-data.org/v1/competitions/455/leagueTable/"
        const val TABLE_URL_V2 = "http://api.football-data.org/v2/competitions/2014/standings"
        const val MATCHES_URL_V2 = "http://api.football-data.org//v2/teams/81/matches/"
        const val TEAM_URL_V2 = "http://api.football-data.org/v2/teams/81/"

        //Player Names
        const val CUTINHO = "Philippe Coutinho"
        const val DEMBELE = "Ousmane Dembélé"
        const val TER_STEGEN = "Marc-André ter Stegen"
        const val CILLESEN = "Jasper Cillessen"
        const val PIQUE = "Gerard Piqué"
        const val UMTITI = "Samuel Umtiti"
        const val JORDI_ALBA = "Jordi Alba"
        const val DIGNE = "Lucas Digne"
        const val ROBERTO = "Sergi Roberto"
        const val VIDAL = "Aleix Vidal"
        const val BUSQUETS = "Sergio Busquets"
        const val RAKITIC = "Ivan Rakitic"
        const val INIESTA = "Andrés Iniesta"
        const val ANDRE_GOMES = "André Gomes"
        const val DENIS_SUAREZ = "Denis Suárez"
        const val MESSI = "Lionel Messi"
        const val LUIS_SUAREZ = "Luis Suárez"
        const val ALCACER = "Paco Alcácer"
        const val SAMPER = "Sergi Samper"
        const val VERMALEN = "Thomas Vermaelen"
        const val SEMEDO = "Nélson Semedo"
        const val MINA = "Yerry Mina"

        //Player Image Url
        const val URL_MESSI = "https://www.biography.com/.image/c_limit%2Ccs_srgb%2Cq_80%2Cw_500/MTQ4MDU5NDU0MzgwNzEzNDk0/lionel_messi_photo_josep_lago_afp_getty_images_664928892_resizedjpg.webp"
        const val URL_INIESTA = "https://cdn.images.express.co.uk/img/dynamic/67/590x/Andres-Iniesta-Barcelona-Transfer-News-808775.jpg"
        const val URL_CUTINHO = "https://www.thesun.co.uk/wp-content/uploads/2018/02/nintchdbpict000384444283-e1519152984552.jpg?strip=all&w=960"
        const val URL_DEMBELE = "https://cdn.images.dailystar.co.uk/dynamic/58/photos/572000/620x/Ousmane-Dembele-684392.jpg"
        const val URL_TER_STEGEN = "http://the18.com/sites/default/files/styles/feature_image_with_focal/public/feature-images/20180124-The18-Image-Ter-Stegen.jpg?itok=tgWaD_ax"
        const val URL_CILLESEN = "https://www.thenational.ae/image/policy:1.136506:1499280402/image/jpeg.jpg?f=16x9&w=1200&\$p\$f\$w=dfa40e8"
        const val URL_PIQUE = "https://images.cdn.fourfourtwo.com/sites/fourfourtwo.com/files/styles/image_landscape/public/gerard-pique_1tpo7vazzxkhw1cu8rhwco5vla.jpg?itok=swHi8SJO&c=87b6d99828d88c1b8ffe17a08d24fc7d"
        const val URL_JORDI = "http://www.squawka.com/news/wp-content/uploads/2017/07/GettyImages-671990412.jpg"
        const val URL_UMTITI = "https://metrouk2.files.wordpress.com/2018/04/um.jpg"
        const val URL_MINA = "http://e2.365dm.com/18/01/16-9/20/skysports-yerry-mina-barcelona-transfer_4204456.jpg?20180113130303"
        const val URL_BUSQUETS = "https://i.ytimg.com/vi/-m7kGoswJU0/maxresdefault.jpg"
        const val URL_SEMEDO = "https://cdn.vox-cdn.com/thumbor/XN0e-vM7SnFODLaTjnqBGOVLQv4=/0x0:3081x4500/1200x800/filters:focal(1352x591:1844x1083)/cdn.vox-cdn.com/uploads/chorus_image/image/58034955/881915690.jpg.0.jpg"
        const val URL_VERMAELEN = "https://90l.tribuna.com/images/a1/40/45/a1404539780048858b9e39fd74630646.jpg"
        const val URL_SAMPER = "http://www.squawka.com/news/wp-content/uploads/2015/06/SSamper.jpg"
        const val URL_ALCACER = "http://i.dailymail.co.uk/i/pix/2016/10/22/09/39712CA400000578-0-image-a-1_1477123726760.jpg"
        const val URL_SUAREZ = "https://cdn.vox-cdn.com/thumbor/khmfaHJCPQ6vMMzOjm6zme-N7hI=/0x0:3500x2417/1200x800/filters:focal(1363x570:1923x1130)/cdn.vox-cdn.com/uploads/chorus_image/image/57514395/844990382.0.jpg"
        const val URL_DENIS_SUAREZ = "https://images0.minutemediacdn.com/production/912x516/59be5dcf2ee40fd16f000001.jpg"
        const val URL_GOMEZ = "http://i.dailymail.co.uk/i/pix/2016/07/29/12/36A26CCB00000578-3714485-image-a-32_1469791809043.jpg"
        const val URL_RAKITIC = "https://dj0j0ofql4htg.cloudfront.net/cms2/image_manager/uploads/News/245970/7/default.jpg"
        const val URL_ROBERTO = "https://90l.tribuna.com/images/b6/5e/b4/b65eb4788c2540eb98db2efb219242b3.jpg"
        const val URL_VIDAL = "https://i0.wp.com/sportxclusive.com/wp-content/uploads/2018/01/fbl.jpg?fit=960%2C540"
        const val URL_DIGNE = "https://images.performgroup.com/di/library/goal_de/88/7/lucas-digne-fc-barcelona-10082016_1htqbz1cmgt1g1ek31dngiae6w.jpg?t=-1479372730&w=940"
        const val URL_DEFAULT = "http://s3.amazonaws.com/37assets/svn/765-default-avatar.png"

        //Bundle KEYS
        const val NEWS_BODY_KEY = "news_body_key"

        //URL
        const val NEWS_IMAGE_URL = "http://news.snet-al.com/store/uploads/sport/"

        fun isNetworkAvailable(mContext: Context): Boolean {
            val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        //Cashe data
        var newsId = ""
        var imageEndpoint = ""

    }
}
