-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 24, 2017 at 09:14 PM
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
(2, 1, 'Manifesto', '<p>  We are Sobakaists. We didn’t join forces for political reasons and no propaganda brought us together. No chains of nationalism or religion hold us down nor do substances as insignificant as blood tie us together. We don’t bow down to money. We do not stand under one flag nor has the same star guided us to this very place. Our relations are purely existentialist in nature – we live for art. We are continually drowning in an ocean of everyday banality. This group represents our refuge, our very own islet, a harbour we land in to rest, for life is terribly long and the ocean is seldom steady. Each one of us reached this inlet relying solely on the sense of smell. We knew one another by a secret sign no one revealed to us previously. We heard music and traced its sound. Each one of us discovered a mark within, an odd emblem of fate, for we rest assured we are the chosen ones, prophets of a new world wherein art bears far more importance than television, more significance than fun, wherein it constitutes a dire need, a physical and spiritual necessity.</p>\r\n\r\n<p>  We are Sobakaists. We are hounds that furiously bite for art, we are the keepers of the riches of the world cultural heritage, and we guide the unaware down these paths. We are no postmodernists, we do not propose L’art pour l’art, nor are we solitary – we recognize our predecessors’ efforts. However, none of them have devoted themselves to art as much as all of us are willing to. We find in ourselves the traces of the early 20th century force, when literature partook in wars and bloody conflicts, when paintings bombarded and movies executed masses. We possess a degree of that commitment but we don’t crave new conflicts, the terrible inhumanity of the world. We merely want human kind to nurture humanism even in times of peace and question the meaning of life, which one may only accomplish through art. We do not consider a greasy sausage more important than Shakespeare nor do we believe that Shakespeare may sate an empty stomach, but we howl at those nose-diving into the triviality of existence, we snarl at fat guts not craving art.</p>\r\n\r\n<p>  We are Sobakaists and the creative process is our sanctity. We believe in Creation, in phenomena that endure corruption and destruction, that reach further than any periodicals and are not drowned in the silence of the centuries. We maintain either one of us can change the world with his or her creative invention. Timing is of no concern to us, as it may happen tomorrow or in thousands of years. To us, Altamira is a museum of mankind, the same as the Museum of Contemporary Art in Belgrade (inaccessible as the cave itself). Everything we’ve sown so far may grow at any moment throughout the eternity of existence, as we discovered our roots throughout the world. Our roots are embedded deeply in South America, the Incan and Mayan culture, in Africa and its abhorrent history, in jazz, Louvre, Russia and the East and deep in the very heart of the Balkans. More adherents are to find us in the same way we located one another in the dense forest of stupidity, for whoever seeks shall eventually discover.</p>\r\n\r\n<p>  We are Sobakaists and we do not fear mistakes, improvisations, honesty, attempts, grief and sweat. We do not fear being left outside the spotlight and we do not crave fame. Our goal is to change reality and we have opted for a difficult path in our mission. We aim to create a better world out of the existing one, instead of simply ruining everything in our present experience and recreating a new reality from the ashes of the previous one. Some of us have seen the future, while others are firmly aware that art will save mankind and that planet Earth will be our homeland. These Words represent a new beginning.</p>\r\n\r\n  We shall work vigorously. And expand. We shall beckon each other and bond. We shall be no strangers to any kind of art and provide a new reality to each of them. Literature shall be our starting point, since in the beginning was the Logos.\r\n\r\n  We, the Sobakaists, swear to eternally serve Creation and proclaim eternal fidelity to Art.', 'manifesto', '2017-03-15 07:00:00', 'en', NULL),
(3, 1, '?lanak broj #1', '<p>NO Since id is defined as primitive int, it will be default initialized with 0 and it will never be null. There is no need to check primitive types, if they are null. They will never be null. If you want, you can compare against the default value 0 as if(person.getId()==0){}.</p>', 'clanak-broj-1', NULL, 'rs', 0),
(4, 16, 'drugi ?lanak po redu tzv. second', '<p style="border: 0px; outline: 0px; vertical-align: baseline; background: #ffffff; margin: 0px 0px 0.9em; padding: 0px; line-height: 1.55em !important; color: #131313; font-size: 15px; font-family: Roboto, Helvetica, Tahoma, Arial, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;">Jeste nam sajt bio u haosu, malo smo ga zapostavili... Ne&scaron;to sam ?a?kao pre par dana pa sam poremetio.<br />Sredio sam sada, rade "Radovi", sad su dobri linkovi.</p>\n<p style="border: 0px; outline: 0px; vertical-align: baseline; background: #ffffff; margin: 0px 0px 0.9em; padding: 0px; line-height: 1.55em !important; color: #131313; font-size: 15px; font-family: Roboto, Helvetica, Tahoma, Arial, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;">Da, postoji stara verzija sajta pre nego smo postavili uvodnu&nbsp;animaciju. Posle toga sajt sam migrirao na /movement/ a kopija sajta je ostala u pozadini. Nju cu da ubijem, i da na taj stari sajt stižu spam komentari. To je postao problem zadnih mesec dana... Kad sklonim sajt presta?e komentari.<br />Stavio sam za&scaron;titu protiv spama, to su ovi Captha provere na komentarima i logovanjima.</p>\n<p style="border: 0px; outline: 0px; vertical-align: baseline; background: #ffffff; margin: 0px 0px 0.9em; padding: 0px; line-height: 1.55em !important; color: #131313; font-size: 15px; font-family: Roboto, Helvetica, Tahoma, Arial, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;">Kontakt strana ga?a info@sobakaisti.org, taj mail pratim. Za sve ostale blogove mi stizu obavestenja na moj gmail.&nbsp;<br />u su&scaron;tini mejlovi idu po nazivima sobakaista, znaci:</p>\n<p style="border: 0px; outline: 0px; vertical-align: baseline; background: #ffffff; margin: 0px 0px 0.9em; padding: 0px; line-height: 1.55em !important; color: #131313; font-size: 15px; font-family: Roboto, Helvetica, Tahoma, Arial, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;">ime.prezime@sobakaisti.org (sve malim slovima i latinicom!)<br />i ima&scaron; onaj generalni info@sobakaisti.org<br />iskopacu sifru pa cu ti naknadno napisati.</p>\n<p style="border: 0px; outline: 0px; vertical-align: baseline; background: #ffffff; margin: 0px 0px 0.9em; padding: 0px; line-height: 1.55em !important; color: #131313; font-size: 15px; font-family: Roboto, Helvetica, Tahoma, Arial, sans-serif; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;">Uglavnom bitne poruke sto do?u do mene ti prosledim, ali obezbedi thunderbird svakako.&nbsp;</p>\n<p>&nbsp;</p>', 'drugi-clanak-po-redu-tzv-second', '2017-03-20 21:22:53', 'rs', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
