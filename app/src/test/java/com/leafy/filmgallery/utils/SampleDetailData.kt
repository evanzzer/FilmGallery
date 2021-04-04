package com.leafy.filmgallery.utils

import com.leafy.filmgallery.data.source.local.entity.DetailEntity
import com.leafy.filmgallery.data.source.remote.response.moviedetail.MovieDetailResponse
import com.leafy.filmgallery.data.source.remote.response.moviedetail.MovieGenresItem

object SampleDetailData {
    fun generateSampleMovie(): List<DetailEntity> {
        val movie = ArrayList<DetailEntity>()

        movie.add(
            DetailEntity(
                332562,
                "A Star is Born",
                "R.drawable.movie_a_star_is_born",
                "05/10/2018",
                "2h 16m",
                "Drama, Romance, Music",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                0
            )
        )
        movie.add(
            DetailEntity(
                399579,
                "Alita: Battle Angel",
                "R.drawable.movie_alita",
                "14/02/2019",
                "2h 2m",
                "Action, Science Fiction, Adventure",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                0
            )
        )
        movie.add(
            DetailEntity(
                297802,
                "Aquaman",
                "R.drawable.movie_aquaman",
                "21/12/2018",
                "2h 24m",
                "Action, Adventure, Fantasy",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                0
            )
        )
        movie.add(
            DetailEntity(
                424694,
                "Bohemian Rhapsody",
                "R.drawable.movie_bohemian",
                "02/11/2018",
                "2h 15m",
                "Drama, Music",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                0
            )
        )
        movie.add(
            DetailEntity(
                438650,
                "Cold Pursuit",
                "R.drawable.movie_cold_pursuit",
                "08/02/2019",
                "1h 59m",
                "Action, Crime, Thriller",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                0
            )
        )
        movie.add(
            DetailEntity(
                480530,
                "Creed II",
                "R.drawable.movie_creed",
                "21/11/2018",
                "2h 10m",
                "Drama",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                0
            )
        )
        movie.add(
            DetailEntity(
                338952,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "R.drawable.movie_crimes",
                "16/11/2018",
                "2h 14m",
                "Adventure, Fantasy, Drama",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                0
            )
        )
        movie.add(
            DetailEntity(
                450465,
                "Glass",
                "R.drawable.movie_glass",
                "18/01/2019",
                "2h 9m",
                "Thriller, Drama, Science Fiction",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                0
            )
        )
        movie.add(
            DetailEntity(
                166428,
                "How to Train Your Dragon: The Hidden World",
                "R.drawable.movie_how_to_train",
                "22/02/2019",
                "1h 44m",
                "Animation, Family, Adventure",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                0
            )
        )
        movie.add(
            DetailEntity(
                299536,
                "Avengers: Infinity War",
                "R.drawable.movie_infinity_war",
                "27/04/2018",
                "2h 29m",
                "Adventure, Action, Science Fiction",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                0
            )
        )
        movie.add(
            DetailEntity(
                457136,
                "Mary Queen of Scots",
                "R.drawable.movie_mary_queen",
                "21/12/2018",
                "2h 4m",
                "Drama, History",
                "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
                0
            )
        )
        movie.add(
            DetailEntity(
                450001,
                "Master Z: Ip Man Legacy",
                "R.drawable.movie_master_z",
                "26/12/2018",
                "1h 47m",
                "Action",
                "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
                0
            )
        )
        movie.add(
            DetailEntity(
                428078,
                "Mortal Engines",
                "R.drawable.movie_mortal_engines",
                "14/12/2018",
                "2h 9m",
                "Adventure, Fantasy",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                0
            )
        )
        movie.add(
            DetailEntity(
                438799,
                "Overlord",
                "R.drawable.movie_overlord",
                "09/11/2018",
                "1h 50m",
                "Horror, War, Science Fiction",
                "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
                0
            )
        )
        movie.add(
            DetailEntity(
                404368,
                "Ralph Breaks the Internet",
                "R.drawable.movie_ralph",
                "21/11/2018",
                "1h 52m",
                "Family, Animation, Comedy, Adventure",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                0
            )
        )
        movie.add(
            DetailEntity(
                375588,
                "Robin Hood",
                "R.drawable.movie_robin_hood",
                "21/11/2018",
                "1h 56m",
                "Adventure, Action, Thriller",
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                0
            )
        )
        movie.add(
            DetailEntity(
                452832,
                "Serenity",
                "R.drawable.movie_serenity",
                "25/01/2019",
                "1h 42m",
                "Thriller, Mystery, Drama",
                "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
                0
            )
        )
        movie.add(
            DetailEntity(
                324857,
                "Spider-Man: Into the Spider-Verse",
                "R.drawable.movie_spiderman",
                "12/14/2018",
                "1h 57m",
                "Action, Adventure, Animation, Science Fiction, Comedy",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                0
            )
        )
        movie.add(
            DetailEntity(
                505954,
                "T-34",
                "R.drawable.movie_t34",
                "01/01/2019",
                "2h 19m",
                "War, Action, Drama, History",
                "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                0
            )
        )

        return movie
    }

    fun generateSampleTvShow(): List<DetailEntity> {
        val tvShow = ArrayList<DetailEntity>()

        tvShow.add(
            DetailEntity(
                1412,
                "Arrow",
                "R.drawable.tv_show_arrow",
                "10/10/2012",
                "42m",
                "Crime, Drama, Mystery, Action & Adventure",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                79501,
                "Doom Patrol",
                "R.drawable.tv_show_doom_patrol",
                "15/02/2019",
                "49m",
                "Sci-Fi & Fantasy, Action & Adventure",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                102092,
                "DragonBall Yabai",
                "R.drawable.tv_show_dragon_ball",
                "26/02/1986",
                "2h",
                "Animation, Comedy, Action & Adventure",
                "None",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                46261,
                "Fairy Tail",
                "R.drawable.tv_show_fairytail",
                "12/10/2009",
                "25m",
                "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                1434,
                "Family Guy",
                "R.drawable.tv_show_family_guy",
                "31/01/1999",
                "22m",
                "Animation, Comedy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                60735,
                "The Flash",
                "R.drawable.tv_show_flash",
                "07/10/2014",
                "44m",
                "Drama, Sci-Fi & Fantasy",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                1399,
                "Game of Thrones",
                "R.drawable.tv_show_got",
                "17/04/2011",
                "1h",
                "Sci-Fi & Fantasy, Drama, Action & Adventure, Mystery",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                60708,
                "Gotham",
                "R.drawable.tv_show_gotham",
                "22/09/2014",
                "43m",
                "Drama, Fantasy, Crime",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                1416,
                "Grey's Anatomy",
                "R.drawable.tv_show_grey_anatomy",
                "27/03/2005",
                "43m",
                "Drama",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                54155,
                "Hanna",
                "R.drawable.tv_show_hanna",
                "28/03/2019",
                "50m",
                "Action & Adventure, Drama",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                62127,
                "Marvel's Iron Fist",
                "R.drawable.tv_show_iron_fist",
                "17/03/2017",
                "55m",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                31910,
                "Naruto Shippūden",
                "R.drawable.tv_show_naruto_shipudden",
                "15/02/2007",
                "25m",
                "Animation, Comedy, Drama",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                4614,
                "NCIS",
                "R.drawable.tv_show_ncis",
                "23/09/2003",
                "45m",
                "Action & Adventure, Crime, Drama",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                69050,
                "Riverdale",
                "R.drawable.tv_show_riverdale",
                "26/01/2017",
                "45m",
                "Drama, Mystery",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                34307,
                "Shameless",
                "R.drawable.tv_show_shameless",
                "09/01/2011",
                "1h",
                "Drama, Comedy",
                "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                62688,
                "Supergirl",
                "R.drawable.tv_show_supergirl",
                "26/10/2015",
                "42m",
                "Action, Adventure, Drama, Science Fiction",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                1622,
                "Supernatural",
                "R.drawable.tv_show_supernatural",
                "13/09/2005",
                "45m",
                "Drama, Mystery, Sci-Fi & Fantasy",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                456,
                "The Simpson",
                "R.drawable.tv_show_the_simpson",
                "16/12/1989",
                "22m",
                "Animation, Comedy, Family, Drama",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                75006,
                "The Umbrella Academy",
                "R.drawable.tv_show_the_umbrella",
                "15/02/2019",
                "55m",
                "Action & Adventure, Sci-Fi & Fantasy, Comedy, Drama",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                1
            )
        )
        tvShow.add(
            DetailEntity(
                1402,
                "The Walking Dead",
                "R.drawable.tv_show_the_walking_dead",
                "31/10/2010",
                "42m",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                1
            )
        )

        return tvShow
    }

    fun generateRemoteMovieDetail(id: Int) : MovieDetailResponse {
        val movieEntity = generateSampleMovie()
        lateinit var sample: DetailEntity
        for (movie in movieEntity) {
            if (movie.id == id) sample = movie
            break
        }

        val genres = sample.category.split(", ".toRegex())
        val genresItem = ArrayList<MovieGenresItem>()
        genres.forEach { name ->
            genresItem.add(MovieGenresItem(name, 0))
        }

        val duration = sample.duration.split("h ".toRegex())
        val runtime = if(duration.size == 1) duration[0].replace("m", "").toInt()
            else duration[0].toInt()*60 + duration[1].replace("m", "").toInt()

        return MovieDetailResponse(
            sample.description,
            sample.date,
            genresItem as List<MovieGenresItem>,
            runtime,
            sample.id,
            sample.title,
            sample.imageUrl
        )
    }
}