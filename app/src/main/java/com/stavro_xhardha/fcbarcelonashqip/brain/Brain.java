package com.stavro_xhardha.fcbarcelonashqip.brain;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.security.PublicKey;

/**
 * Created by stavro_xhardha on 21/04/2018.
 */

public class Brain {

    public static final String TAG = "BrainTag";

    //Api
    public static final String HEADER_RESPONSE_CONTROL = "X-Response-Control";
    public static final String AUTHORIZATION = "X-Auth-Token";
    public static final String RESPONSE_HEADER_VALUE = "full";
    public static final String TOKEN = "88a3894f29564aa09808e373fca97d84";

    //Cashe data
    public static int MODE;

    //Flags

    //Fragment Tags
    public static final String WHATS_NEW_ON_CLUB_FRAGMENT_TAG = "What's new on club";
    public static final String TABLE_FRAGMENT_TAG = "Table Fragment";
    public static final String TEAM_FRAGMENT_TAG = "Team Fragment";
    public static final String SCHEDULED_MATCHES_FRAGMENT_TAG =  "Scheduled Matches";
    public static final String HISTORY_MATCH_FRAGMENT_TAG = "History Matches";
    public static final String FC_BARCELONA_PAGE_FRAGMENT_TAG = "Barcelona Page Fragment";
    public static final String SPLASH_FRAGMENT_TAG = "Splash Fragment ";
    public static final String LATEST_NEWS_FRAGMENT_TAG = "Latest News";

    //Api
    public static final String YOUTUBE_API_KEY = "&key=AIzaSyAmxLV2RcpoW2EOe51NbeWvWrRGD7QZgM8";
    public static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&channelId=UC14UlmYlSNiQCBe9Eookf_A&maxResults=20";
    public static final String YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/search?";
    public static final String YOUTUBE_DETAILS = "part=snippet&order=date&channelId=UC14UlmYlSNiQCBe9Eookf_A&maxResults=20";
    public static final String YOUTUBE_PAGE_TOKEN = "pageToken=";
    public static final String YOUTUBE_WATCH_URL = "https://www.youtube.com/watch?v=";
    public static final String PLAYERS_URL = "http://api.football-data.org/v1/teams/81/players";
    public static final String MATCH_URL = "http://api.football-data.org/v1/teams/81/fixtures";
    public static final String NEWS_URL = "http://press.snet-al.com/index_sport.php?/api/get_latest_news/";
    public static final String VIEWS_URL = "http://press.snet-al.com/index_sport.php?/api/viewed/";

    //Player Names
    public static final String CUTINHO = "Philippe Coutinho";
    public static final String DEMBELE = "Ousmane Dembélé";
    public static final String TER_STEGEN = "Marc-André ter Stegen";
    public static final String CILLESEN = "Jasper Cillessen";
    public static final String PIQUE = "Gerard Piqué";
    public static final String UMTITI = "Samuel Umtiti";
    public static final String JORDI_ALBA = "Jordi Alba";
    public static final String DIGNE = "Lucas Digne";
    public static final String ROBERTO = "Sergi Roberto";
    public static final String VIDAL = "Aleix Vidal";
    public static final String BUSQUETS = "Sergio Busquets";
    public static final String RAKITIC = "Ivan Rakitic";
    public static final String INIESTA = "Andrés Iniesta";
    public static final String ANDRE_GOMES = "André Gomes";
    public static final String DENIS_SUAREZ = "Denis Suárez";
    public static final String MESSI = "Lionel Messi";
    public static final String LUIS_SUAREZ = "Luis Suárez";
    public static final String ALCACER = "Paco Alcácer";
    public static final String SAMPER = "Sergi Samper";
    public static final String VERMALEN = "Thomas Vermaelen";
    public static final String SEMEDO = "Nélson Semedo";
    public static final String MINA = "Yerry Mina";

    //Player Image Url
    public static final String URL_MESSI = "https://www.biography.com/.image/c_limit%2Ccs_srgb%2Cq_80%2Cw_500/MTQ4MDU5NDU0MzgwNzEzNDk0/lionel_messi_photo_josep_lago_afp_getty_images_664928892_resizedjpg.webp";
    public static final String URL_INIESTA = "https://cdn.images.express.co.uk/img/dynamic/67/590x/Andres-Iniesta-Barcelona-Transfer-News-808775.jpg";
    public static final String URL_CUTINHO = "https://www.thesun.co.uk/wp-content/uploads/2018/02/nintchdbpict000384444283-e1519152984552.jpg?strip=all&w=960";
    public static final String URL_DEMBELE = "https://cdn.images.dailystar.co.uk/dynamic/58/photos/572000/620x/Ousmane-Dembele-684392.jpg";
    public static final String URL_TER_STEGEN = "http://the18.com/sites/default/files/styles/feature_image_with_focal/public/feature-images/20180124-The18-Image-Ter-Stegen.jpg?itok=tgWaD_ax";
    public static final String URL_CILLESEN = "https://www.thenational.ae/image/policy:1.136506:1499280402/image/jpeg.jpg?f=16x9&w=1200&$p$f$w=dfa40e8";
    public static final String URL_PIQUE = "https://images.cdn.fourfourtwo.com/sites/fourfourtwo.com/files/styles/image_landscape/public/gerard-pique_1tpo7vazzxkhw1cu8rhwco5vla.jpg?itok=swHi8SJO&c=87b6d99828d88c1b8ffe17a08d24fc7d";
    public static final String URL_JORDI = "http://www.squawka.com/news/wp-content/uploads/2017/07/GettyImages-671990412.jpg";
    public static final String URL_UMTITI = "https://metrouk2.files.wordpress.com/2018/04/um.jpg";
    public static final String URL_MINA = "http://e2.365dm.com/18/01/16-9/20/skysports-yerry-mina-barcelona-transfer_4204456.jpg?20180113130303";
    public static final String URL_BUSQUETS = "https://i.ytimg.com/vi/-m7kGoswJU0/maxresdefault.jpg";
    public static final String URL_SEMEDO = "https://cdn.vox-cdn.com/thumbor/XN0e-vM7SnFODLaTjnqBGOVLQv4=/0x0:3081x4500/1200x800/filters:focal(1352x591:1844x1083)/cdn.vox-cdn.com/uploads/chorus_image/image/58034955/881915690.jpg.0.jpg";
    public static final String URL_VERMAELEN = "https://90l.tribuna.com/images/a1/40/45/a1404539780048858b9e39fd74630646.jpg";
    public static final String URL_SAMPER = "http://www.squawka.com/news/wp-content/uploads/2015/06/SSamper.jpg";
    public static final String URL_ALCACER = "http://i.dailymail.co.uk/i/pix/2016/10/22/09/39712CA400000578-0-image-a-1_1477123726760.jpg";
    public static final String URL_SUAREZ = "https://cdn.vox-cdn.com/thumbor/khmfaHJCPQ6vMMzOjm6zme-N7hI=/0x0:3500x2417/1200x800/filters:focal(1363x570:1923x1130)/cdn.vox-cdn.com/uploads/chorus_image/image/57514395/844990382.0.jpg";
    public static final String URL_DENIS_SUAREZ = "https://images0.minutemediacdn.com/production/912x516/59be5dcf2ee40fd16f000001.jpg";
    public static final String URL_GOMEZ = "http://i.dailymail.co.uk/i/pix/2016/07/29/12/36A26CCB00000578-3714485-image-a-32_1469791809043.jpg";
    public static final String URL_RAKITIC = "https://dj0j0ofql4htg.cloudfront.net/cms2/image_manager/uploads/News/245970/7/default.jpg";
    public static final String URL_ROBERTO = "https://90l.tribuna.com/images/b6/5e/b4/b65eb4788c2540eb98db2efb219242b3.jpg";
    public static final String URL_VIDAL = "https://i0.wp.com/sportxclusive.com/wp-content/uploads/2018/01/fbl.jpg?fit=960%2C540";
    public static final String URL_DIGNE = "https://images.performgroup.com/di/library/goal_de/88/7/lucas-digne-fc-barcelona-10082016_1htqbz1cmgt1g1ek31dngiae6w.jpg?t=-1479372730&w=940";
    public static final String URL_DEFAULT = "http://s3.amazonaws.com/37assets/svn/765-default-avatar.png";

    //URL
    public static final String NEWS_IMAGE_URL = "http://news.snet-al.com/app_press/public_html/uploads/";

    public static boolean isNetworkAvailable(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static int getMODE() {
        return MODE;
    }

    public static void setMODE(int MODE) {
        Brain.MODE = MODE;
    }
}
