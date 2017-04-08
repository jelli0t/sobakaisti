-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 08, 2017 at 03:10 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sobakaisti`
--

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext,
  `slug` varchar(255) DEFAULT NULL,
  `post_date` timestamp NULL DEFAULT NULL,
  `lang` varchar(3) DEFAULT NULL,
  `active` int(2) DEFAULT NULL COMMENT 'is published'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`id`, `author_id`, `title`, `content`, `slug`, `post_date`, `lang`, `active`) VALUES
(1, 1, 'Mafifest', '<p class="western" lang="" style="text-align: justify" align="CENTER">  Ми смо Собакаисти. Нисмо се удружили из политичких разлога, није нас никаква пропаганда спојила, не држе нас националне ни верске стеге, ништа тако јефтино као крв нас не спаја, новцу се не клањамо. Нисмо се окупили око једне заставе, нити нас је иста звезда водила до овог места. Наше су везе егзистенцијалне – ми живимо ради уметности. Ми смо сви дављеници у мору баналне свакодневице, а овај кружок је наше острво, наша мала ада, наша лука у коју пристајемо да се одморимо, јер живот је проклето дуг, а море је ретко мирно. Сваки од нас је свој њух пратио да би дошао довде. Препознали смо се међусобно по неком тајном знаку који нам нико није показао, чули смо музику и пратили смо звук, свако је у себи нашао обележје, чудни белег наше судбине, јер ми сви верујемо да смо изабраници, да смо пророци у новом свету где ће уметност бити важнија од телевизије, важнија од забаве, где ће уметност бити потреба, физичка и духовна неопходност.</p>\r\n<p class="western" lang="" style="text-align: justify">  Ми смо Собакаисти. Ми смо пси што бесно гризу за уметност, ми смо чувари богате ризнице светске културне традиције, ми смо водичи онима што не знају за овај пут. Нисмо постмодернисти, нисмо лапурлатисти, али нисмо ни инокосни - знамо ко је пре нас био овде, али нико се до кости није дао као што је сваки од нас спреман. Има у нама оне силе са почетка двадесетог века, када је у ратовима, у крвавим окршајима, и књижевност учествовала, када су слике бомбардовале, а филмови решетали масе. Има у нама нешто од те ангажованости, али ми нећемо нове обрачуне, срамну нељудскост света, ми хоћемо да се човечанство и у миру сети хуманизма, да се пита о смислу живота, а како другачије него - уметношћу. Не мислимо да је масна кобасица важнија од Шекспира, нити да Шекспир засити празан стомак, али лајемо на оне што срљају у тривијалност постојања, лајемо на дебеле мешине што нису гладне уметности.</p>\r\n<p class="western" lang="" style="text-align: justify">  Ми смо Собакаисти и стварање нам је светиња. Верујемо у Креацију, у оно што надвисује пропадљивост и деструкцију, оно што досеже даље од сваке периодике, оно што може да се огласи у тишини векова. Верујемо да ће све што створи и један од нас променити свет; не бринемо се када ће се то догодити, да ли сутра или за хиљаду година, јер за нас је Алтамира музеј човечанства, исто као и Музеј савремене уметности у Београду, (недоступан колико и сама пећина). Све што смо данас посејали, може да избије из земље било када у вечности постојања, као што смо ми нашли корене у целом свету и жиле су нам дубоко у Јужној Америци, у култури Инка и Маја, у Африци и њеној болној историји, у џезу, у Лувру, у Русији и Истоку, и дубоко, дубоко, дубоко на Балкану. И како смо појединачно налазили пут кроз густу шуму глупости, тако ће и нас неко наћи, јер онај који се даје тражењу, тај и налази.</p>\r\n<p class="western" lang="" style="text-align: justify">  Ми смо Собакаисти и не плашимо се грешке, импровизације, искрености, покушаја, муке и зноја. Не плашимо се да ћемо остати незапажени, а не жудимо за славом. Наши су циљеви да променимо стварност, а за то смо одабрали тежак пут, да од овога што имамо створимо бољи свет, а не да уништимо све постојеће и из пепела подижемо нашу реалност. Ми знамо, неки од нас су и видели будућност – уметност ће спасити човечанство и планета Земља биће домовина свих нас. Ове Речи су нови почетак.</p>\r\n<p class="western" style="text-align: justify"><span lang="">  Радићемо неуморно. И прошириваћемо се. Дозиваћемо једни друге и спајати се. Нећемо бити странци ниједној уметности и у свакој ћемо дати нову стварност. Крећемо од књижевности, јер је и у првом кругу на почетку била Реч. </span></p>\r\n<p class="western" lang="" style="text-align: justify">Ми, Собакаисти, заклињемо се да ћемо верно служити Стварању, заклињемо се на вечну верност Уметности.</p>', 'manifesto', '2017-03-16 10:00:00', 'rs', 1),
(2, 1, 'Manifesto', '<p>  We are Sobakaists. We didn’t join forces for political reasons and no propaganda brought us together. No chains of nationalism or religion hold us down nor do substances as insignificant as blood tie us together. We don’t bow down to money. We do not stand under one flag nor has the same star guided us to this very place. Our relations are purely existentialist in nature – we live for art. We are continually drowning in an ocean of everyday banality. This group represents our refuge, our very own islet, a harbour we land in to rest, for life is terribly long and the ocean is seldom steady. Each one of us reached this inlet relying solely on the sense of smell. We knew one another by a secret sign no one revealed to us previously. We heard music and traced its sound. Each one of us discovered a mark within, an odd emblem of fate, for we rest assured we are the chosen ones, prophets of a new world wherein art bears far more importance than television, more significance than fun, wherein it constitutes a dire need, a physical and spiritual necessity.</p>\r\n\r\n<p>  We are Sobakaists. We are hounds that furiously bite for art, we are the keepers of the riches of the world cultural heritage, and we guide the unaware down these paths. We are no postmodernists, we do not propose L’art pour l’art, nor are we solitary – we recognize our predecessors’ efforts. However, none of them have devoted themselves to art as much as all of us are willing to. We find in ourselves the traces of the early 20th century force, when literature partook in wars and bloody conflicts, when paintings bombarded and movies executed masses. We possess a degree of that commitment but we don’t crave new conflicts, the terrible inhumanity of the world. We merely want human kind to nurture humanism even in times of peace and question the meaning of life, which one may only accomplish through art. We do not consider a greasy sausage more important than Shakespeare nor do we believe that Shakespeare may sate an empty stomach, but we howl at those nose-diving into the triviality of existence, we snarl at fat guts not craving art.</p>\r\n\r\n<p>  We are Sobakaists and the creative process is our sanctity. We believe in Creation, in phenomena that endure corruption and destruction, that reach further than any periodicals and are not drowned in the silence of the centuries. We maintain either one of us can change the world with his or her creative invention. Timing is of no concern to us, as it may happen tomorrow or in thousands of years. To us, Altamira is a museum of mankind, the same as the Museum of Contemporary Art in Belgrade (inaccessible as the cave itself). Everything we’ve sown so far may grow at any moment throughout the eternity of existence, as we discovered our roots throughout the world. Our roots are embedded deeply in South America, the Incan and Mayan culture, in Africa and its abhorrent history, in jazz, Louvre, Russia and the East and deep in the very heart of the Balkans. More adherents are to find us in the same way we located one another in the dense forest of stupidity, for whoever seeks shall eventually discover.</p>\r\n\r\n<p>  We are Sobakaists and we do not fear mistakes, improvisations, honesty, attempts, grief and sweat. We do not fear being left outside the spotlight and we do not crave fame. Our goal is to change reality and we have opted for a difficult path in our mission. We aim to create a better world out of the existing one, instead of simply ruining everything in our present experience and recreating a new reality from the ashes of the previous one. Some of us have seen the future, while others are firmly aware that art will save mankind and that planet Earth will be our homeland. These Words represent a new beginning.</p>\r\n\r\n  We shall work vigorously. And expand. We shall beckon each other and bond. We shall be no strangers to any kind of art and provide a new reality to each of them. Literature shall be our starting point, since in the beginning was the Logos.\r\n\r\n  We, the Sobakaists, swear to eternally serve Creation and proclaim eternal fidelity to Art.', 'manifesto', '2017-03-15 07:00:00', 'en', 1),
(3, 1, 'Članak broj #1', '<p>NO Since id is defined as primitive int, it will be default initialized with 0 and it will never be null. There is no need to check primitive types, if they are null. They will never be null. If you want, you can compare against the default value 0 as if(person.getId()==0){}.</p>', 'clanak-broj-1', '2017-03-07 23:00:00', 'rs', 0),
(4, 16, 'drugi ?lanak po redu tzv. second', '<p style="border: 0px; outline: 0px; vertical-align: baseline; background: #ffffff; margin: 0px 0px 0.9em; padding: 0px; line-height: 1.55em !important; color: #131313; font-size: 15px; font-family: Roboto, Helvetica, Tahoma, Arial, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;">Jeste nam sajt bio u haosu, malo smo ga zapostavili... Ne&scaron;to sam ?a?kao pre par dana pa sam poremetio.<br />Sredio sam sada, rade "Radovi", sad su dobri linkovi.</p>\n<p style="border: 0px; outline: 0px; vertical-align: baseline; background: #ffffff; margin: 0px 0px 0.9em; padding: 0px; line-height: 1.55em !important; color: #131313; font-size: 15px; font-family: Roboto, Helvetica, Tahoma, Arial, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;">Da, postoji stara verzija sajta pre nego smo postavili uvodnu&nbsp;animaciju. Posle toga sajt sam migrirao na /movement/ a kopija sajta je ostala u pozadini. Nju cu da ubijem, i da na taj stari sajt stižu spam komentari. To je postao problem zadnih mesec dana... Kad sklonim sajt presta?e komentari.<br />Stavio sam za&scaron;titu protiv spama, to su ovi Captha provere na komentarima i logovanjima.</p>\n<p style="border: 0px; outline: 0px; vertical-align: baseline; background: #ffffff; margin: 0px 0px 0.9em; padding: 0px; line-height: 1.55em !important; color: #131313; font-size: 15px; font-family: Roboto, Helvetica, Tahoma, Arial, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;">Kontakt strana ga?a info@sobakaisti.org, taj mail pratim. Za sve ostale blogove mi stizu obavestenja na moj gmail.&nbsp;<br />u su&scaron;tini mejlovi idu po nazivima sobakaista, znaci:</p>\n<p style="border: 0px; outline: 0px; vertical-align: baseline; background: #ffffff; margin: 0px 0px 0.9em; padding: 0px; line-height: 1.55em !important; color: #131313; font-size: 15px; font-family: Roboto, Helvetica, Tahoma, Arial, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;">ime.prezime@sobakaisti.org (sve malim slovima i latinicom!)<br />i ima&scaron; onaj generalni info@sobakaisti.org<br />iskopacu sifru pa cu ti naknadno napisati.</p>\n<p style="border: 0px; outline: 0px; vertical-align: baseline; background: #ffffff; margin: 0px 0px 0.9em; padding: 0px; line-height: 1.55em !important; color: #131313; font-size: 15px; font-family: Roboto, Helvetica, Tahoma, Arial, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;">Uglavnom bitne poruke sto do?u do mene ti prosledim, ali obezbedi thunderbird svakako.&nbsp;</p>\n<p>&nbsp;</p>', 'drugi-clanak-po-redu-tzv-second', '2017-03-20 21:22:53', 'rs', 0),
(5, 1, 'Ovo je proba za Stranicu svi ?lanci', '<p><span style="color: #000000; font-family: Roboto, arial, sans-serif; font-size: 13px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: pre-wrap; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; display: inline !important; float: none;">The lips of the righteous teach many. But fools die for want of wisdom. The rich man''s wealth is in his city. The righteous'' wealth in in His holy place.</span></p>', 'ovo-je-proba-za-stranicu-svi-clanci', '2017-04-02 18:06:40', 'rs', 0),
(6, 1, 'dfdsdsdfsdf', '<p>fsdfsdfsdfsd</p>', 'dfdsdsdfsdf', '2017-04-07 22:10:00', 'rs', 0),
(7, 1, 'sadasd asd asd asdas dasdasd', '', 'sadasd-asd-asd-asdas-dasdasd', '2017-04-07 22:12:37', 'rs', 0),
(8, 1, 'proba ?lanka s kategorijama', '', 'proba-clanka-s-kategorijama', '2017-04-07 22:16:18', 'rs', 0),
(9, 1, 'proba kategorija', '<p>fdsfsdf sd f</p>', 'proba-kategorija', '2017-04-07 22:28:58', 'rs', 0),
(10, 1, 'ovo je 3ca proba ?lanaka sa kategorijama', '<p>fdssdfsdfsd</p>', 'ovo-je-3ca-proba-clanaka-sa-kategorijama', '2017-04-07 22:33:03', 'rs', 0),
(11, 1, '?lanak #4', '<p>asddas asdasdasdasdas</p>', 'clanak-4', '2017-04-08 12:56:14', 'rs', 0),
(12, 1, '?lanak br #5', '<p>ddfsfdsf sdf sdfsd fsd</p>', 'clanak-br-5', '2017-04-08 13:05:37', 'rs', 0);

-- --------------------------------------------------------

--
-- Table structure for table `article_category`
--

CREATE TABLE `article_category` (
  `article_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `article_category`
--

INSERT INTO `article_category` (`article_id`, `category_id`) VALUES
(1, 1),
(3, 2),
(7, 1),
(7, 2),
(8, 1),
(8, 2),
(9, 1),
(9, 2),
(10, 1),
(10, 2),
(12, 1),
(12, 2);

-- --------------------------------------------------------

--
-- Table structure for table `article_tag`
--

CREATE TABLE `article_tag` (
  `article_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `article_tag`
--

INSERT INTO `article_tag` (`article_id`, `tag_id`) VALUES
(1, 1),
(1, 3),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `AUTHOR`
--

CREATE TABLE `AUTHOR` (
  `ID` int(11) NOT NULL,
  `FIRST_NAME` varchar(30) DEFAULT NULL,
  `LAST_NAME` varchar(30) DEFAULT NULL,
  `DATE_OF_BIRTH` date DEFAULT NULL,
  `BIRTHPLACE` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  `SHORT_BIO` varchar(255) DEFAULT NULL,
  `PROFESSION` varchar(50) DEFAULT NULL,
  `AVATAR_PATH` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `AUTHOR`
--

INSERT INTO `AUTHOR` (`ID`, `FIRST_NAME`, `LAST_NAME`, `DATE_OF_BIRTH`, `BIRTHPLACE`, `EMAIL`, `WEBSITE`, `SHORT_BIO`, `PROFESSION`, `AVATAR_PATH`) VALUES
(1, 'Andrea', 'Kane', '1988-10-10', 'Milano, Italia', 'andrea.kane@sobakaisti.org', 'andreakane.sobakaisti.org', '', 'Pesnik / blogger', 'avatar_path_value'),
(2, 'Nemnaj', 'djsndjn', NULL, '', '', '', '', 'nsdjnjdns', 'avatar_path_value'),
(16, 'Nemanja', 'Jelesijevic', NULL, 'Gornji Milanovac', 'jelles86@yahoo.co.yu', '', '', 'programer', 'avatar_path_value');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `slug` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `slug`) VALUES
(1, 'sobakisti', NULL),
(2, 'književnost', NULL),
(3, 'Književnost', 'knjizevnost'),
(4, 'Muzika', 'muzika');

-- --------------------------------------------------------

--
-- Table structure for table `intro_article`
--

CREATE TABLE `intro_article` (
  `id` int(11) NOT NULL,
  `content` text,
  `lang` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `intro_article`
--

INSERT INTO `intro_article` (`id`, `content`, `lang`) VALUES
(1, 'Mi smo Sobakaisti. Nismo se udružili iz političkih razloga, nije nas nikakva propaganda spojila, ne drže nas nacionalne ni verske stege, ništa tako jeftino kao krv nas ne spaja, novcu se ne klanjamo. Nismo se okupili oko jedne zastave, niti nas je ista zvezda vodila do ovog mesta. Naše su veze egzistencijalne – mi živimo radi umetnosti. Mi smo svi davljenici u moru banalne svakodnevice, a ovaj kružok je naše ostrvo, naša mala ada, naša luka u koju pristajemo da se odmorimo, jer život je prokleto dug, a more je retko mirno. Svaki od nas je svoj njuh pratio da bi došao dovde. Prepoznali smo se međusobno po nekom tajnom znaku koji nam niko nije pokazao, čuli smo muziku i pratili smo zvuk, svako je u sebi našao obeležje, čudni beleg naše sudbine, jer mi svi verujemo da smo izabranici, da smo proroci u novom svetu gde će umetnost biti važnija od televizije, važnija od zabave, gde će umetnost biti potreba, fizička i duhovna neophodnost. Mi smo Sobakaisti. Mi smo psi što besno grizu za umetnost, mi smo čuvari bogate riznice svetske kulturne tradicije, mi smo vodiči onima što ne znaju za ovaj put. Nismo postmodernisti, nismo lapurlatisti, ali nismo ni inokosni – znamo ko je pre nas bio ovde, ali niko se do kosti nije dao kao što je svaki od nas spreman. Ima u nama one sile sa početka dvadesetog veka, kada je u ratovima, u krvavim okršajima, i književnost učestvovala, kada su slike bombardovale, a filmovi rešetali mase. Ima u nama nešto od te angažovanosti, ali mi nećemo nove obračune, sramnu neljudskost sveta, mi hoćemo da se čovečanstvo i u miru seti humanizma, da se pita o smislu života, a kako drugačije nego – umetnošću. Ne mislimo da je masna kobasica važnija od Šekspira, niti da Šekspir zasiti prazan stomak, ali lajemo na one što srljaju u trivijalnost postojanja, lajemo na debele mešine što nisu gladne umetnosti. Mi smo Sobakaisti i stvaranje nam je svetinja. Verujemo u Kreaciju, u ono što nadvisuje propadljivost i destrukciju, ono što doseže dalje od svake periodike, ono što može da se oglasi u tišini vekova. Verujemo da će sve što stvori i jedan od nas promeniti svet; ne brinemo se kada će se to dogoditi, da li sutra ili za hiljadu godina, jer za nas je Altamira muzej čovečanstva, isto kao i Muzej savremene umetnosti u Beogradu, (nedostupan koliko i sama pećina). Sve što smo danas posejali, može da izbije iz zemlje bilo kada u večnosti postojanja, kao što smo mi našli korene u celom svetu i žile su nam duboko u Južnoj Americi, u kulturi Inka i Maja, u Africi i njenoj bolnoj istoriji, u džezu, u Luvru, u Rusiji i Istoku, i duboko, duboko, duboko na Balkanu. I kako smo pojedinačno nalazili put kroz gustu šumu gluposti, tako će i nas neko naći, jer onaj koji se daje traženju, taj i nalazi. Mi smo Sobakaisti i ne plašimo se greške, improvizacije, iskrenosti, pokušaja, muke i znoja. Ne plašimo se da ćemo ostati nezapaženi, a ne žudimo za slavom. Naši su ciljevi da promenimo stvarnost, a za to smo odabrali težak put, da od ovoga što imamo stvorimo bolji svet, a ne da uništimo sve postojeće i iz pepela podižemo našu realnost. Mi znamo, neki od nas su i videli budućnost – umetnost će spasiti čovečanstvo i planeta Zemlja biće domovina svih nas. Ove Reči su novi početak. Radićemo neumorno. I proširivaćemo se. Dozivaćemo jedni druge i spajati se. Nećemo biti stranci nijednoj umetnosti i u svakoj ćemo dati novu stvarnost. Krećemo od književnosti, jer je i u prvom krugu na početku bila Reč. Mi, Sobakaisti, zaklinjemo se da ćemo verno služiti Stvaranju, zaklinjemo se na večnu vernost Umetnosti.', 'rs'),
(2, 'We are Sobakaists. We didn’t join forces for political reasons and no propaganda brought us together. No chains of nationalism or religion hold us down nor do substances as insignificant as blood tie us together. We don’t bow down to money. We do not stand under one flag nor has the same star guided us to this very place. Our relations are purely existentialist in nature – we live for art. We are continually drowning in an ocean of everyday banality. This group represents our refuge, our very own islet, a harbour we land in to rest, for life is terribly long and the ocean is seldom steady. Each one of us reached this inlet relying solely on the sense of smell. We knew one another by a secret sign no one revealed to us previously. We heard music and traced its sound. Each one of us discovered a mark within, an odd emblem of fate, for we rest assured we are the chosen ones, prophets of a new world wherein art bears far more importance than television, more significance than fun, wherein it constitutes a dire need, a physical and spiritual necessity. We are Sobakaists. We are hounds that furiously bite for art, we are the keepers of the riches of the world cultural heritage, and we guide the unaware down these paths. We are no postmodernists, we do not propose L’art pour l’art, nor are we solitary – we recognize our predecessors’ efforts. However, none of them have devoted themselves to art as much as all of us are willing to. We find in ourselves the traces of the early 20th century force, when literature partook in wars and bloody conflicts, when paintings bombarded and movies executed masses. We possess a degree of that commitment but we don’t crave new conflicts, the terrible inhumanity of the world. We merely want human kind to nurture humanism even in times of peace and question the meaning of life, which one may only accomplish through art. We do not consider a greasy sausage more important than Shakespeare nor do we believe that Shakespeare may sate an empty stomach, but we howl at those nose-diving into the triviality of existence, we snarl at fat guts not craving art. We are Sobakaists and the creative process is our sanctity. We believe in Creation, in phenomena that endure corruption and destruction, that reach further than any periodicals and are not drowned in the silence of the centuries. We maintain either one of us can change the world with his or her creative invention. Timing is of no concern to us, as it may happen tomorrow or in thousands of years. To us, Altamira is a museum of mankind, the same as the Museum of Contemporary Art in Belgrade (inaccessible as the cave itself). Everything we’ve sown so far may grow at any moment throughout the eternity of existence, as we discovered our roots throughout the world. Our roots are embedded deeply in South America, the Incan and Mayan culture, in Africa and its abhorrent history, in jazz, Louvre, Russia and the East and deep in the very heart of the Balkans. More adherents are to find us in the same way we located one another in the dense forest of stupidity, for whoever seeks shall eventually discover. We are Sobakaists and we do not fear mistakes, improvisations, honesty, attempts, grief and sweat. We do not fear being left outside the spotlight and we do not crave fame. Our goal is to change reality and we have opted for a difficult path in our mission. We aim to create a better world out of the existing one, instead of simply ruining everything in our present experience and recreating a new reality from the ashes of the previous one. Some of us have seen the future, while others are firmly aware that art will save mankind and that planet Earth will be our homeland. These Words represent a new beginning. We shall work vigorously. And expand. We shall beckon each other and bond. We shall be no strangers to any kind of art and provide a new reality to each of them. Literature shall be our starting point, since in the beginning was the Logos. We, the Sobakaists, swear to eternally serve Creation and proclaim eternal fidelity to Art.', 'en');

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE `tag` (
  `id` int(11) NOT NULL,
  `tag` varchar(30) DEFAULT NULL,
  `slug` varchar(50) DEFAULT NULL COMMENT 'url friendly string'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`id`, `tag`, `slug`) VALUES
(1, 'sobakaisti', 'sobakaisti'),
(2, 'poezija', 'poezija'),
(3, 'Gornji Milanovac', 'gornji-milanovac'),
(4, 'Andrea Kane', 'andrea-kane'),
(5, 'Predrag Šapa', 'predrag-sapa'),
(6, 'fotografija', 'fotografija'),
(7, 'umetnost', 'umetnost'),
(8, 'pleh orkestar', 'plah-orkestar'),
(9, 'slikarstvo', 'slikarstvo'),
(10, 'dućan', 'ducan'),
(11, 'muzika', 'muzika');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `article_category`
--
ALTER TABLE `article_category`
  ADD PRIMARY KEY (`article_id`,`category_id`),
  ADD KEY `fk_article` (`article_id`),
  ADD KEY `fk_category` (`category_id`);

--
-- Indexes for table `article_tag`
--
ALTER TABLE `article_tag`
  ADD PRIMARY KEY (`article_id`,`tag_id`),
  ADD KEY `article_id` (`article_id`),
  ADD KEY `fk_tag` (`tag_id`);

--
-- Indexes for table `AUTHOR`
--
ALTER TABLE `AUTHOR`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `intro_article`
--
ALTER TABLE `intro_article`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `lang_code` (`lang`);

--
-- Indexes for table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `AUTHOR`
--
ALTER TABLE `AUTHOR`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `intro_article`
--
ALTER TABLE `intro_article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tag`
--
ALTER TABLE `tag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `article_category`
--
ALTER TABLE `article_category`
  ADD CONSTRAINT `fk_art_to_cat` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  ADD CONSTRAINT `fk_cat_to_art` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `article_tag`
--
ALTER TABLE `article_tag`
  ADD CONSTRAINT `fk_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  ADD CONSTRAINT `fk_tag` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
