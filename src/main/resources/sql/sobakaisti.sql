-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 21, 2018 at 09:54 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

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
  `featured_img_id` int(11) DEFAULT NULL,
  `lang` varchar(3) DEFAULT NULL,
  `active` int(2) DEFAULT NULL COMMENT 'is published'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`id`, `author_id`, `title`, `content`, `slug`, `post_date`, `featured_img_id`, `lang`, `active`) VALUES
(1, 1, 'Mafifest', 'Mi smo Sobakaisti. Nismo se udružili iz političkih razloga, nije nas nikakva propaganda spojila, ne drže nas nacionalne ni verske stege, ništa tako jeftino kao krv nas ne spaja, novcu se ne klanjamo. Nismo se okupili oko jedne zastave, niti nas je ista zvezda vodila do ovog mesta. Naše su veze egzistencijalne – mi živimo radi umetnosti. Mi smo svi davljenici u moru banalne svakodnevice, a ovaj kružok je naše ostrvo, naša mala ada, naša luka u koju pristajemo da se odmorimo, jer život je prokleto dug, a more je retko mirno. Svaki od nas je svoj njuh pratio da bi došao dovde. Prepoznali smo se međusobno po nekom tajnom znaku koji nam niko nije pokazao, čuli smo muziku i pratili smo zvuk, svako je u sebi našao obeležje, čudni beleg naše sudbine, jer mi svi verujemo da smo izabranici, da smo proroci u novom svetu gde će umetnost biti važnija od televizije, važnija od zabave, gde će umetnost biti potreba, fizička i duhovna neophodnost. Mi smo Sobakaisti. Mi smo psi što besno grizu za umetnost, mi smo čuvari bogate riznice svetske kulturne tradicije, mi smo vodiči onima što ne znaju za ovaj put. Nismo postmodernisti, nismo lapurlatisti, ali nismo ni inokosni – znamo ko je pre nas bio ovde, ali niko se do kosti nije dao kao što je svaki od nas spreman. Ima u nama one sile sa početka dvadesetog veka, kada je u ratovima, u krvavim okršajima, i književnost učestvovala, kada su slike bombardovale, a filmovi rešetali mase. Ima u nama nešto od te angažovanosti, ali mi nećemo nove obračune, sramnu neljudskost sveta, mi hoćemo da se čovečanstvo i u miru seti humanizma, da se pita o smislu života, a kako drugačije nego – umetnošću. Ne mislimo da je masna kobasica važnija od Šekspira, niti da Šekspir zasiti prazan stomak, ali lajemo na one što srljaju u trivijalnost postojanja, lajemo na debele mešine što nisu gladne umetnosti. Mi smo Sobakaisti i stvaranje nam je svetinja. Verujemo u Kreaciju, u ono što nadvisuje propadljivost i destrukciju, ono što doseže dalje od svake periodike, ono što može da se oglasi u tišini vekova. Verujemo da će sve što stvori i jedan od nas promeniti svet; ne brinemo se kada će se to dogoditi, da li sutra ili za hiljadu godina, jer za nas je Altamira muzej čovečanstva, isto kao i Muzej savremene umetnosti u Beogradu, (nedostupan koliko i sama pećina). Sve što smo danas posejali, može da izbije iz zemlje bilo kada u večnosti postojanja, kao što smo mi našli korene u celom svetu i žile su nam duboko u Južnoj Americi, u kulturi Inka i Maja, u Africi i njenoj bolnoj istoriji, u džezu, u Luvru, u Rusiji i Istoku, i duboko, duboko, duboko na Balkanu. I kako smo pojedinačno nalazili put kroz gustu šumu gluposti, tako će i nas neko naći, jer onaj koji se daje traženju, taj i nalazi. Mi smo Sobakaisti i ne plašimo se greške, improvizacije, iskrenosti, pokušaja, muke i znoja. Ne plašimo se da ćemo ostati nezapaženi, a ne žudimo za slavom. Naši su ciljevi da promenimo stvarnost, a za to smo odabrali težak put, da od ovoga što imamo stvorimo bolji svet, a ne da uništimo sve postojeće i iz pepela podižemo našu realnost. Mi znamo, neki od nas su i videli budućnost – umetnost će spasiti čovečanstvo i planeta Zemlja biće domovina svih nas. Ove Reči su novi početak. Radićemo neumorno. I proširivaćemo se. Dozivaćemo jedni druge i spajati se. Nećemo biti stranci nijednoj umetnosti i u svakoj ćemo dati novu stvarnost. Krećemo od književnosti, jer je i u prvom krugu na početku bila Reč. Mi, Sobakaisti, zaklinjemo se da ćemo verno služiti Stvaranju, zaklinjemo se na večnu vernost Umetnosti.', 'manifesto', '2017-03-16 10:00:00', NULL, 'rs', 1),
(2, 1, 'Manifesto', 'We are Sobakaists. We didn’t join forces for political reasons and no propaganda brought us together. No chains of nationalism or religion hold us down nor do substances as insignificant as blood tie us together. We don’t bow down to money. We do not stand under one flag nor has the same star guided us to this very place. Our relations are purely existentialist in nature – we live for art. We are continually drowning in an ocean of everyday banality. This group represents our refuge, our very own islet, a harbour we land in to rest, for life is terribly long and the ocean is seldom steady. Each one of us reached this inlet relying solely on the sense of smell. We knew one another by a secret sign no one revealed to us previously. We heard music and traced its sound. Each one of us discovered a mark within, an odd emblem of fate, for we rest assured we are the chosen ones, prophets of a new world wherein art bears far more importance than television, more significance than fun, wherein it constitutes a dire need, a physical and spiritual necessity. We are Sobakaists. We are hounds that furiously bite for art, we are the keepers of the riches of the world cultural heritage, and we guide the unaware down these paths. We are no postmodernists, we do not propose L’art pour l’art, nor are we solitary – we recognize our predecessors’ efforts. However, none of them have devoted themselves to art as much as all of us are willing to. We find in ourselves the traces of the early 20th century force, when literature partook in wars and bloody conflicts, when paintings bombarded and movies executed masses. We possess a degree of that commitment but we don’t crave new conflicts, the terrible inhumanity of the world. We merely want human kind to nurture humanism even in times of peace and question the meaning of life, which one may only accomplish through art. We do not consider a greasy sausage more important than Shakespeare nor do we believe that Shakespeare may sate an empty stomach, but we howl at those nose-diving into the triviality of existence, we snarl at fat guts not craving art. We are Sobakaists and the creative process is our sanctity. We believe in Creation, in phenomena that endure corruption and destruction, that reach further than any periodicals and are not drowned in the silence of the centuries. We maintain either one of us can change the world with his or her creative invention. Timing is of no concern to us, as it may happen tomorrow or in thousands of years. To us, Altamira is a museum of mankind, the same as the Museum of Contemporary Art in Belgrade (inaccessible as the cave itself). Everything we’ve sown so far may grow at any moment throughout the eternity of existence, as we discovered our roots throughout the world. Our roots are embedded deeply in South America, the Incan and Mayan culture, in Africa and its abhorrent history, in jazz, Louvre, Russia and the East and deep in the very heart of the Balkans. More adherents are to find us in the same way we located one another in the dense forest of stupidity, for whoever seeks shall eventually discover. We are Sobakaists and we do not fear mistakes, improvisations, honesty, attempts, grief and sweat. We do not fear being left outside the spotlight and we do not crave fame. Our goal is to change reality and we have opted for a difficult path in our mission. We aim to create a better world out of the existing one, instead of simply ruining everything in our present experience and recreating a new reality from the ashes of the previous one. Some of us have seen the future, while others are firmly aware that art will save mankind and that planet Earth will be our homeland. These Words represent a new beginning. We shall work vigorously. And expand. We shall beckon each other and bond. We shall be no strangers to any kind of art and provide a new reality to each of them. Literature shall be our starting point, since in the beginning was the Logos. We, the Sobakaists, swear to eternally serve Creation and proclaim eternal fidelity to Art.', 'manifesto', '2017-03-15 07:00:00', NULL, 'en', 1),
(14, 1, 'cuvanje nacrta', '<p>kdkajsdjassdk</p>', 'cuvanje-nacrta', '2017-05-07 17:17:29', NULL, 'rs', 1),
(16, 3, 'Proba za poslednji članak', '<p>Ea alii minim saepe sea. Et qui posse corrumpit. An fugit assum legendos ius, vidit laboramus vix ne. Bonorum aliquando no pri, omnesque prodesset reformidans cum ex, sit cu timeam adipisci. No illud antiopam reformidans eum, qui stet facete volutpat ne.</p>\r\n<p>Ea mel erant vocent expetendis. Mea adhuc putent consectetuer ad. Sea sanctus tibique cu, sea hinc exerci ne, usu harum accommodare ad. At nam possim numquam, sed ex impetus feugiat saperet. Sed reque delicatissimi id.</p>\r\n<p>Bonorum meliore epicuri in sea, has in dolorum vituperata, mediocrem neglegentur cum ea. Nam iracundia dignissim torquatos ne, ad omnes putant petentium ius ad.</p>', 'proba-za-poslednji-clanak', '2017-06-18 18:01:57', NULL, 'rs', 0),
(19, 5, 'Lorem Ipsum proba članka', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ac tellus tincidunt, malesuada massa id, feugiat elit. Interdum et malesuada fames ac ante ipsum primis in faucibus. Curabitur ultrices erat et diam tincidunt, in tristique dolor viverra. Nulla dictum tempor commodo. Integer egestas, arcu placerat ultrices maximus, ipsum nisl molestie enim, ultrices scelerisque erat nisl gravida ex. Quisque at diam at justo egestas pellentesque. Curabitur in sem dui. Nullam tristique quam eu mi fermentum malesuada. Integer a tempor tellus. In pulvinar, erat et malesuada efficitur, risus lorem venenatis orci, quis vehicula eros turpis non dolor.</p>\r\n<p>Suspendisse condimentum ornare scelerisque. Proin eu est id tortor consequat mattis. Phasellus sed pellentesque lacus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Mauris vitae mauris consequat, cursus lorem nec, rhoncus justo. Pellentesque volutpat iaculis mattis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</p>\r\n<p>Maecenas non augue id dolor vestibulum rutrum sed sed ante. Nunc at nibh commodo, porttitor dolor et, bibendum nunc. Quisque porttitor lacus a augue sodales consequat. Vivamus in erat ut dui cursus pellentesque. Vestibulum sed scelerisque magna. In faucibus metus id hendrerit pharetra. Suspendisse potenti. Curabitur tempus nisi rutrum metus faucibus, ut pulvinar mi pulvinar.</p>', 'lorem-ipsum-proba-clanka', '2017-08-19 14:38:59', NULL, NULL, 1),
(20, 5, 'Lorem Ipsum druga sa Laptopa', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ac tellus tincidunt, malesuada massa id, feugiat elit. Interdum et malesuada fames ac ante ipsum primis in faucibus. Curabitur ultrices erat et diam tincidunt, in tristique dolor viverra. Nulla dictum tempor commodo. Integer egestas, arcu placerat ultrices maximus, ipsum nisl molestie enim, ultrices scelerisque erat nisl gravida ex. Quisque at diam at justo egestas pellentesque. Curabitur in sem dui. Nullam tristique quam eu mi fermentum malesuada. Integer a tempor tellus. In pulvinar, erat et malesuada efficitur, risus lorem venenatis orci, quis vehicula eros turpis non dolor.</p>\r\n<p>Suspendisse condimentum ornare scelerisque. Proin eu est id tortor consequat mattis. Phasellus sed pellentesque lacus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Mauris vitae mauris consequat, cursus lorem nec, rhoncus justo. Pellentesque volutpat iaculis mattis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</p>\r\n<p>Maecenas non augue id dolor vestibulum rutrum sed sed ante. Nunc at nibh commodo, porttitor dolor et, bibendum nunc. Quisque porttitor lacus a augue sodales consequat. Vivamus in erat ut dui cursus pellentesque. Vestibulum sed scelerisque magna. In faucibus metus id hendrerit pharetra. Suspendisse potenti. Curabitur tempus nisi rutrum metus faucibus, ut pulvinar mi pulvinar.</p>', 'lorem-ipsum-proba-clanka-1', '2017-08-19 14:39:15', NULL, NULL, 1),
(21, 5, 'Lorem Ipsum druga sa Laptopa', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ac tellus tincidunt, malesuada massa id, feugiat elit. Interdum et malesuada fames ac ante ipsum primis in faucibus. Curabitur ultrices erat et diam tincidunt, in tristique dolor viverra. Nulla dictum tempor commodo. Integer egestas, arcu placerat ultrices maximus, ipsum nisl molestie enim, ultrices scelerisque erat nisl gravida ex. Quisque at diam at justo egestas pellentesque. Curabitur in sem dui. Nullam tristique quam eu mi fermentum malesuada. Integer a tempor tellus. In pulvinar, erat et malesuada efficitur, risus lorem venenatis orci, quis vehicula eros turpis non dolor.</p>\r\n<p>Suspendisse condimentum ornare scelerisque. Proin eu est id tortor consequat mattis. Phasellus sed pellentesque lacus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Mauris vitae mauris consequat, cursus lorem nec, rhoncus justo. Pellentesque volutpat iaculis mattis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</p>\r\n<p>Maecenas non augue id dolor vestibulum rutrum sed sed ante. Nunc at nibh commodo, porttitor dolor et, bibendum nunc. Quisque porttitor lacus a augue sodales consequat. Vivamus in erat ut dui cursus pellentesque. Vestibulum sed scelerisque magna. In faucibus metus id hendrerit pharetra. Suspendisse potenti. Curabitur tempus nisi rutrum metus faucibus, ut pulvinar mi pulvinar.</p>', 'lorem-ipsum-druga-sa-laptopa', '2017-08-19 14:40:06', NULL, NULL, 1),
(22, 8, 'Samo tu i tamo', '<p>Proin at dui semper, lobortis elit et, molestie felis. Ut scelerisque scelerisque lectus imperdiet ultrices. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec tristique pulvinar diam. Aenean fringilla nunc vitae felis molestie viverra. Nam tincidunt, erat nec aliquam convallis, sem nisl fermentum dolor, quis suscipit turpis nunc id turpis. Cras ornare tellus quis eros maximus venenatis. Proin facilisis eleifend leo elementum cursus. Duis sodales sapien a purus sagittis, a hendrerit elit blandit. Nulla in quam et ligula consectetur fermentum. Nullam vitae imperdiet tellus.</p>\r\n<p>Curabitur finibus, lacus sit amet pellentesque finibus, massa est posuere nisi, sit amet rhoncus nunc massa id turpis. Mauris quis condimentum lorem. Integer nec feugiat mauris. Sed eget pretium nulla, ut tristique diam. Aliquam a mollis odio, ac dapibus sapien. Nulla sed consectetur neque. Nunc ut nulla arcu. Fusce neque elit, sollicitudin at massa in, malesuada rhoncus sapien. Nulla urna justo, varius eget tellus eu, dictum venenatis tellus. Aenean porta, justo sed lobortis ultrices, risus orci posuere lacus, eget dictum nisi nisi sit amet orci. Nam tincidunt cursus neque, non dapibus tellus pulvinar sit amet. Maecenas et ante id nulla lobortis posuere. Nunc vitae pretium sem, eget tempor ex. Curabitur mauris elit, tempor ac vulputate id, maximus quis neque. Nulla ut ipsum a tortor varius hendrerit. Nulla blandit tortor volutpat est molestie placerat.</p>\r\n<p>Generated 5 paragraphs, 436 words, 2979 bytes of Lorem Ipsum</p>', 'samo-tu-i-tamo', '2017-08-19 14:41:55', NULL, NULL, 1),
(23, 3, 'Šolja kafe na stočiću', '<p>Proin at dui semper, lobortis elit et, molestie felis. Ut scelerisque scelerisque lectus imperdiet ultrices. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec tristique pulvinar diam. Aenean fringilla nunc vitae felis molestie viverra. Nam tincidunt, erat nec aliquam convallis, sem nisl fermentum dolor, quis suscipit turpis nunc id turpis. Cras ornare tellus quis eros maximus venenatis. Proin facilisis eleifend leo elementum cursus. Duis sodales sapien a purus sagittis, a hendrerit elit blandit. Nulla in quam et ligula consectetur fermentum. Nullam vitae imperdiet tellus.</p>\r\n<p>Curabitur finibus, lacus sit amet pellentesque finibus, massa est posuere nisi, sit amet rhoncus nunc massa id turpis. Mauris quis condimentum lorem. Integer nec feugiat mauris. Sed eget pretium nulla, ut tristique diam. Aliquam a mollis odio, ac dapibus sapien. Nulla sed consectetur neque. Nunc ut nulla arcu. Fusce neque elit, sollicitudin at massa in, malesuada rhoncus sapien. Nulla urna justo, varius eget tellus eu, dictum venenatis tellus. Aenean porta, justo sed lobortis ultrices, risus orci posuere lacus, eget dictum nisi nisi sit amet orci. Nam tincidunt cursus neque, non dapibus tellus pulvinar sit amet. Maecenas et ante id nulla lobortis posuere. Nunc vitae pretium sem, eget tempor ex. Curabitur mauris elit, tempor ac vulputate id, maximus quis neque. Nulla ut ipsum a tortor varius hendrerit. Nulla blandit tortor volutpat est molestie placerat.</p>\r\n<p>Generated 5 paragraphs, 436 words, 2979 bytes of Lorem Ipsum</p>', 'solja-kafe-na-stocicu', '2017-08-19 14:45:30', 1, NULL, 1),
(24, 2, '1000 Godina - Partibrejkers by Stefan Pas', '<p>Proin at dui semper, lobortis elit et, molestie felis. Ut scelerisque scelerisque lectus imperdiet ultrices. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec tristique pulvinar diam. Aenean fringilla nunc vitae felis molestie viverra. Nam tincidunt, erat nec aliquam convallis, sem nisl fermentum dolor, quis suscipit turpis nunc id turpis. Cras ornare tellus quis eros maximus venenatis. Proin facilisis eleifend leo elementum cursus. Duis sodales sapien a purus sagittis, a hendrerit elit blandit. Nulla in quam et ligula consectetur fermentum. Nullam vitae imperdiet tellus.</p>\r\n<p>!!Bio tekst!!</p>\r\n<p>Generated 5 paragraphs, 436 words, 2979 bytes of Lorem Ipsum</p>', '1000-godina-partibrejkers-by-stefan-pas', '2017-08-20 16:34:06', NULL, NULL, 1),
(25, 3, '1000 Godina - Partibrejkers by Lajka', '<p>Proin at dui semper, lobortis elit et, molestie felis. Ut scelerisque scelerisque lectus imperdiet ultrices. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec tristique pulvinar diam. Aenean fringilla nunc vitae felis molestie viverra. Nam tincidunt, erat nec aliquam convallis, sem nisl fermentum dolor, quis suscipit turpis nunc id turpis. Cras ornare tellus quis eros maximus venenatis. Proin facilisis eleifend leo elementum cursus. Duis sodales sapien a purus sagittis, a hendrerit elit blandit. Nulla in quam et ligula consectetur fermentum. Nullam vitae imperdiet tellus.</p>\r\n<p>Curabitur finibus, lacus sit amet pellentesque finibus, massa est posuere nisi, sit amet rhoncus nunc massa id turpis. Mauris quis condimentum lorem. Integer nec feugiat mauris. Sed eget pretium nulla, ut tristique diam. Aliquam a mollis odio, ac dapibus sapien. Nulla sed consectetur neque. Nunc ut nulla arcu. Fusce neque elit, sollicitudin at massa in, malesuada rhoncus sapien. Nulla urna justo, varius eget tellus eu, dictum venenatis tellus. Aenean porta, justo sed lobortis ultrices, risus orci posuere lacus, eget dictum nisi nisi sit amet orci. Nam tincidunt cursus neque, non dapibus tellus pulvinar sit amet. Maecenas et ante id nulla lobortis posuere. Nunc vitae pretium sem, eget tempor ex. Curabitur mauris elit, tempor ac vulputate id, maximus quis neque. Nulla ut ipsum a tortor varius hendrerit. Nulla blandit tortor volutpat est molestie placerat.</p>\r\n<p>Generated 5 paragraphs, 436 words, 2979 bytes of Lorem Ipsum</p>', '1000-godina-partibrejkers-edited-1', '2017-08-20 16:45:25', NULL, NULL, 1),
(26, 5, 'КОПИЛЕ ПОСТМОДЕРНЕ (ИЛИ: ШТА САМ НАУЧИО НА ФАКУЛТЕТУ)', '<p>Ја, Стефан Стефановић, од оца Гојка и мајке Мирјане, рођен тринаестог октобра 1990. године, признајем да је све игра.</p>\r\n<p>&emsp;&emsp;Данас нема &bdquo;Младе Босне&ldquo;, јер је наша младост и виталност запела у &bdquo;даунлоду&ldquo;, недостају нам &bdquo;печеви&ldquo; да закрпе прогоретине живота, а &bdquo;конвертовање&ldquo; предуго траје. И да нам подају оружје, превише је тирана, на кога првог пуцати? Балкан је пиратски фајл у мрежи Европе и света. И није да смо уморни &ndash; ми смо сморени. Досада нас је изјела до кости, јер живимо у процепу између виртуелног и правог света, између онога што су нас учили да јесте и онога што видимо да јесте, разапети смо између &bdquo;Мајке Русије&ldquo; и &bdquo;издајице Европе&ldquo;, а у колутању са једне на другу страну остали смо у кружници, прогутали смо свој реп. Досадно нам је и стога што смо већ све видели на телевизији, скинули смо са Интернета, знамо да је то туђа пропаганда, мислимо да смо најгори од свих, а ипак се снажно бусамо у груди и помињемо славну историју коју смо набубали, никада научили и схватили.<br />&emsp;&emsp;Враћање самом себи, стога, није самољубиви чин, болесни егоизам, већ насушна потреба да се у шизофреном свету сачува здрав разум и, пре свега, буде срећан. Постмодерно друштво ме је научило да више није непристојно јавно истицати своје врлине, таленте и успехе, већ да се то сматра погодношћу и квалитетом у пословању, па тако евo и ја развијам свој си-ви, покрећем уметнички кружок &bdquo;Собакаисти&ldquo; у којем ћу сваку мрву свог интересовања, жеље и талента изградити у велелепну мануфактурну радионицу.<br />&emsp;&emsp;А чинило ми се да неће бити тако, јер до јуче сам био послушан студент књижевности, полагао сам колоквијуме и скупљао ЕСП бодове као сличице фудбалера и веровао сам, о тако сам силно веровао, да ће моја диплома значити ишта у будућем свету. Више од пола свог живота провео сам у образовном систему који ме је учио да ћу са завршетком факултета освојити неку велику животну награду. Родитељи су ме саветовали, као и многе друге, да је битно уграбити диплому и имати је &bdquo;за сваки случај&ldquo;. Васпитавају нас да морамо да се издигнемо изнад свих, чиме год да се бавимо, морамо да будемо најбољи; убедили су нас да сви заслужујемо простране канцеларије и подређене, па нас се у међувремену накотило и превише, и видим &ndash; ту нема места за моју диплому и мене.<br />&emsp;&emsp;Ипак, знање никада није сувишно, чак и у савременом свету који захтева фискални рачун за сваку робу. Хуманистичко образовање вероватно више никада неће имати новчану исплативост, али ће вечно имати значај на макро плану човечанства, али и на микро плану постојања, јер, после свих тих прочитаних романа и анализираних фабула, примећујем да је стварност одавно налик на постмодерне романе. Ван књижевности страшно нас потресају плагијати докторских радова, а ми већ одавно знамо да ниједан документ није поуздан, да је све завера Библиотекара и да је читава историја какву познајемо можда само добро написана прича. Шта је један докторат наспрам историје народа? И оно што је у књижевности била гротеска и нонсенс хумор &ndash; сада је болна реалност, јер видим око себе како се државе граде на фиктивним документима, како се стварају нови језици сакаћењем свих законитости које сам научио из историје језика и како се преписују и моделирају читаве античке културе да би се обезбедила слабашна и провидна садашњица. Историографија, са којом се постмодерна спрдала, сада је врло јефтино средство за манипулацију. Балкан одавно личи на полигон за експериментисање.<br />&emsp;&emsp;А где је улога уметности? Пре само сто година она је имала снагу да надахњује људе да учине свет бољим, а сада је маргинализована или се јефтино продала у сврху забаве. Та скрајнутост је резултат и понашања самих уметника: дипломе и звања су важније од идеја и дела. Постмодерна је прогутала свој реп и закључала се у прстен преиспитивања. И док се воде расправе на непосећеним предавањима и трибинама, стварност полако обољева од фикције, постаје иреална и несхватљива. Уметност је остала корак иза стварности, а уметник изгубљен у том раскораку. Време је да се уметност врати у свакодневицу.<br />&emsp;&emsp;У књижевност сам ушао на Басарином бициклу, али не могу да останем у тој кружници, тај ланац се одавно прекинуо и сада бих само окретао педале у празно. Нема ни других линија које могу да наставим, јер видим Албахарија како је ударио у зид Текста и сад у бунилу понавља &bdquo;ко ово пише? ко ово пише?&ldquo;. Петковић ненадмашиво говори о темама које нас се не тичу и са којима не можемо да се поистоветимо, дух тих времена одавно не обитава у овом свету. Владушић је осетио ново поље стваралаштва и свакако заслужује признање за увођење савремене технологије у књижевност и извоз књижевности у савремене технологије, али његова белетристика је незанимљива и добар је пример како факултети књижевности од добрих читалаца праве просечне писце; у горем случају од лоших читалаца писце-самозванце.<br />&emsp;&emsp;Собакаисти осећају дух времена. Собакаисти препознају вредности целокупне досадашње традиције, они су школовани, професионални полазници постмодерног друштва. Ако је још Џојс показао како је &bdquo;Одисеја&ldquo; књижевна традиција човечанства, шта нас Собакаисте спречава, шта мене спречава да црпим материјал из целокупне уметности човечанства? Па сигурно је да нисам први у томе! Банкси (Banksy) је створио читаву културу уличне уметности пажљиво одабирајући и комбинујући са својим идејама безграничан материјал сликарске традиције човечанства. Успео је да постави основе будућим визуелним уметницима у том пољу рада, без обзира на то да ли се његова уметност може сматрати &bdquo;високом&ldquo; или не. Његов допринос је што је &bdquo;високу уметност&ldquo; извео у стваран свет, спасио је галерија и загушљивог испаравања интелектуализма. У музици то већ одавно ради &bdquo;Тивери корпорејшн&ldquo; (Thievery Corporation). Користећи утицаје различитих музичких стилова, комбинујући их и спајајући их у жељени контекст, они стварају музику на темељу светске музичке традиције. Ако нешто можемо да научимо од постмодерне то је да је цео свет наша домовина и извор инспирације.<br />&emsp;&emsp;Књижевност, а и целокупна уметност, одувек експлоатише неколико најзначајнијих тема &ndash; живот и смрт, љубав, питање Бога&hellip; Шта се променило након свих ових година? Живот има свог парњака у виртуелном свету &ndash; па како писати о стварности, када је наша стварност реализована према споља, ми прилагођавамо наше животе за излоге кроз које нас други виде, а не кроз унутрашње грађење. Како писати о љубави после сајбер секса и бесплатне порнографије на Интернету? И где је веза са Богом, где је &bdquo;линк&ldquo;? Собакаисти ће се храбро суочити са овим питањима.<br />&emsp;&emsp;Верујем да књижевност има огроман потенцијал, иако свуда око нас запомажу душебрижници како нико не чита. Ипак, статистика показује супротно &ndash; ако Видојковић, Басало, водитељке и естрадне личности имају тираже од неколико стотина хиљада књига, у више издања, морамо довести у питање тврдњу да књига нема своју публику. Јасно је да постоји огроман број читалаца до којих данашња књижевност не допире јер се затворила у свој малени свет, у свој круг уског пречника повученог између професора и студената књижевности. Уметност мора да понуди нешто овом свету, иначе је њена сврха доведена у питање. И можда је права уметност она која осети тренутак у времену и понуди оно што фали свету. Ако је у средњем веку фалила слобода &ndash; ренесанса ју је пружила. Ако у данашњем свету фали виталност, радост, разум &ndash; уметност мора да је пружи.<br />&emsp;&emsp;Не желим да будем миксер за млевење, не желим да разобличим хиљаду туђих цитата да бих створио своје дело. Собакаисти то неће радити. Не страхујем да ли ће то бити цењена уметност или тек ћорак испаљен у ваздух, још један ситан уметнички рукавац под именом собакаизам (пасматеризам). Не тражим и не очекујем признања, награде и похвале. Ако је време прави показатељ вредности неког уметничког дела &ndash; онда бринем још мање, јер постојање на овој планети је ограничено, и крајњим оптимистима је јасно да нећемо изгурати да обележимо две стотине година од Гавриловог пуцња.<br />&emsp;&emsp;Желим да се играм. На столу крај мене лежи књига &bdquo;Хетероними&ldquo; у којој су сакупљени текстови Фернанда Песое. Та књига је мали споменик који ме опомиње да сам ја неважан, само су моји хетероними битни, они стварају. Та књига ме подсећа и да су они само ликови, а да сам ја онај који је жив. Та књига је моја амајлија од свих лудости који ми прете ван Текста. Ја сам копиле постмодерне и било би превише очекивати да све с&acirc;м урадим. Као свако непризнато дете, навикао сам да живим скрајнут, обесправљен и запуштен, тако да нећу подилазити већини, нити шенити ради туђих похвала. Оно што ме је постмодерно друштво научило &ndash; ја сам то! Дубоко у себи верујем да сам најважнији аутор у овом веку, али чешће страхујем да сам погрешио и да ипак ништа од овога нема смисла. Али деца се играју јер не познају страх.<br />&emsp;&emsp;Желим да се играм, а ипак пишем због себе, градим једну личну приповест, его-манијачку поетику у којој се све моје прожима и међусобно дозива. Али ја то радим са крајњом хуманистичком надом, јер уколико ико на свету пронађе у томе макар и зрно уживања, користи или било чега што човек налази у уметности &ndash; то онда значи да је Уметност победила, да нема разлика међу нама, јер је једну толико личну причу неко осетио и себе пронашао у њој. Уколико се то не деси, ако останем само махнита луда на пустом пољу која маше и дозива, ако нико не осети ни мало емпатије за оно што радим, ако нико не доживи моје приче &ndash; то значи да једни друге не разумемо у основи, као бића, јер уколико неко ствара потпуно искрено, водећи се само својим нагоном и духом, а нико у томе не може да пронађе нешто од опште вредности, саосети се са тим, то значи да су наше индивидуалности превелике да бисмо опстали, јер ако не разумемо оно што једну особу чини посебном &ndash; не разумемо ни човечанство. У том случају је и целокупна уметност погрешно тумачена, јер никада нећемо схватити појединачност човека као бића и ствараоца, већ ћемо стално пројектовати своје идеје кроз његов живот и дело. У том случају није ни важно, ја сам бар радио оно у шта сам веровао. Играо сам се живота.</p>', 'kopile-postmoderne-ili-sta-sam-naucio-na-fakultetu', '2017-09-02 17:48:09', NULL, NULL, 0),
(27, 5, 'Članak za probu paginacije na admin panelu', '<p>Aenean maximus nec leo vitae ornare. Donec libero est, maximus ut metus nec, ornare bibendum ligula. Quisque turpis magna, tincidunt sit amet sem id, molestie rhoncus leo. Suspendisse molestie mauris id neque vehicula, vitae tempus quam vestibulum. Aliquam id elit aliquam, tincidunt ex ut, rutrum nulla. Donec suscipit ornare justo, eget blandit est convallis sed. Suspendisse quis consectetur elit. Praesent fringilla, est vitae sagittis tincidunt, tortor erat consectetur orci, eu feugiat purus diam nec arcu. Curabitur ac diam ac tortor placerat consequat. Pellentesque finibus ante velit, nec sagittis purus pharetra id.</p>\r\n<p>Phasellus fermentum libero id nunc hendrerit blandit. Nullam justo neque, euismod vel neque a, egestas euismod risus. Phasellus eu tellus nisi. Duis pulvinar semper nunc, in fringilla lorem. Fusce in varius mauris. Nam tincidunt pellentesque ante, vitae bibendum velit hendrerit non. In tincidunt magna non neque sollicitudin blandit. Quisque suscipit feugiat lorem, nec tincidunt massa.</p>\r\n<p>Suspendisse congue vel enim at accumsan. In hac habitasse platea dictumst. Curabitur vitae pellentesque eros, quis consequat metus. Vestibulum purus odio, varius sit amet finibus non, ultrices nec ipsum. Sed tristique nisi a tincidunt convallis. Nulla malesuada sem egestas ornare semper. Sed quis pellentesque orci. Aliquam et mattis tellus. Integer quis fringilla erat. Praesent id accumsan nibh.</p>\r\n<p>Praesent condimentum nisl hendrerit nibh gravida, a porta nisi vulputate. Praesent et dui at sem blandit blandit. Aliquam in tellus felis. Sed maximus mollis elit sit amet interdum. Nullam fermentum iaculis sem lobortis aliquet. Duis a augue dapibus, iaculis massa vel, fringilla dolor. Suspendisse ac risus quis dolor tempor maximus. Nulla ut ipsum vestibulum turpis blandit aliquet sed sit amet nisi.</p>', 'clanak-za-probu-paginacije-na-admin-panelu', '2017-09-10 15:09:28', NULL, NULL, 1),
(28, 2, 'Политика није за песника', '<p>Данојлић је песник који дели судбину оних наших интелектуалаца које је снажно обележило емигрантско искуство, и када је реч о његовом прозном и поетском стваралаштву, и када је реч о његовим политичким наступима.</p>\r\n<p>Његове најновије песме из збирке &bdquo;Животи, животи&rdquo; (Албатрос плус, 2016), за коју је недавно добио награду &bdquo;Драинац&rdquo; у Прокупљу и &bdquo;Књижевни вијенац Козаре&rdquo; у Приједору, наставак су његовог песничког рвања са самим собом и са светом: и када говори о свету, Данојлић говори о себи. Реч је о својеврсном погледу уназад, како гласи једна од песама у збирци (Питам се, у чуду помало/Од дечака који је кренуо на пут/Шта је у мени остало).</p>\r\n<p>У осамдесетој години живота писац са звездицом академика своју друштвену позицију доживљава као маргиналну.</p>', 'politika-nije-za-pesnika', '2017-09-02 15:48:18', NULL, NULL, 1),
(29, 8, 'Као гњурац дубоко под водом', '<p>У више својих јавних наступа, предавања или интервјуа, словеначки филозоф Славој Жижек износио је последњих година формулу која гласи &bdquo;Нема етничког чишћења без поезије&rdquo; (No ethnic cleansing without poetry). Повезујући етничко чишћење са (босанским) Србима, тврдио је да уопште није случајно то што је Радован Караџић био песник. Наравно да то не треба схватити дословно, у ком би случају сваки инспиратор, &bdquo;пројектант&rdquo; или налогодавац етничког чишћења нужно морао бити песник. Шири контекст ових Жижекових тврдњи чини његова расправа о везама између (поетског) језика и насиља у књизи Living in the End Times (2010). У усменим наступима он напомиње да је потребна &bdquo;снажна митопоетска структура&rdquo;, нешто попут поезије, националистичког мита или религије, као нека врста паравана, заклона или опијума који ће злочинима које чинимо прибавити извесно дубље значење и уздићи их чак до херојског чина. Сваком тоталитарном патриотизму потребни су поезија, мит, религија, каже Жижек.</p>\r\n<p>Скоро да би се могло говорити о некој врсти епидемије јавних суза, о својеврсној инвазији, поплави плакања пред свеприсутним &bdquo;оком&rdquo; медија. Па ипак, није свеједно ко плаче, са ког места и којим поводом.<br />Следећи, премда у другачијем регистру, овакву врсту увезивања могли бисмо, на пример, (не)опрезно рећи да нема холокауста без сликарства, позивајући се при том на добро познату љубав Адолфа Хитлера према сликарству и његове, како се обавезно наглашава, неталентоване и неуспеле покушаје у савладавању сликарског умећа. Ако Караџићево политичко наслеђе не прави нужно од њега и лошег песника (на шта и Жижек посредно упућује), онда и порицање сваког сликарског талента Хитлеру може бити под знаком питања. Но мимо тога, посебно важно, високо место које сликарство и скулптура имају у дизајнирању, пропагирању и провођењу нацифашистичког тоталитарног и расистичког пројекта показује да се и тој врсти уметности може наменити улога паравана или опијума. Завођење сликама често је много ефикасније од завођења речима.</p>\r\n<p>У новије време ову улогу паравана/заклона за злодела и злочине који се чине у савременом свету, улогу завођења и анестезирања који нас наводе да такве ствари примамо као нужне кораке на путу постизања тзв. виших, хуманих, еманципаторских, прогресивних вредности и циљева, све више и упадљивије почиње да обавља нешто сасвим друго и необично, што нема никакве везе ни са поезијом, ни са митовима, ни са уметношћу уопште. То нешто може се именовати помоћу следеће формуле: нема убијања дроновима без плакања (No drone-killing without crying). Да бисмо ову формулу конкретизовали, подсетићемо на то да је донедавни амерички председник Барак Обама, који је као главнокомандујући оружаних снага ауторизовао и одобравао масовну употребу дронова у тзв. борби против тероризма широм света (толико често и успешно да би се drone-killing скоро могло сматрати његовим брендом), у јавним наступима одређеним поводима током свог мандата заплакао &bdquo;најмање пет пута&rdquo;, како је то утврдио новинар Лондонског Гардијана Лео Бенедиктус. Исти новинар тврди да су сва тројица последњих америчких председника у својим мандатима јавно плакали.</p>', 'kao-gnurac-duboko-pod-vodom', '2017-09-13 10:24:37', NULL, NULL, 0),
(30, 8, 'Мале мистерије Дејвида Линча', '<p>Чини се да Линчов улазак у филм заправо почиње у моменту када као тинејџер открива сликарство или, прецизније, могућност да визуелно изрази, анимира и стави у неку врсту покрета све што га је већ тада окупирало кроз сећања, призоре, имагинацију и жељу да проникне у наличја и тајанствене силе свакодневног живота</p>\r\n<p>&bdquo;Радио сам на једној мањој углавном црној слици, али је имала неке зелене биљке и листове који су излазили из те црне боје. Седео сам и, вероватно уз цигарету, посматрао слику из које се зачуо ветар и биљке су почеле да се крећу. Помислио сам: &rsquo;Ох, покретна слика, али са звуком&rsquo;. Та ми се идеја запатила у глави. Покретна слика.&ldquo; У документарном филму David Lynch: The Art Life који се појавио прошле године, Линч се још једном осврнуо на догађај који се десио током студија на уметничкој академији у Филаделфији и који често и он и његови биографи наводе као иницијацијски тренутак у одлуци да се бави седмом уметношћу. Ипак чини се да Линчов улазак у филм заправо почиње у моменту када као тинејџер открива сликарство или, прецизније, могућност да визуелно изрази, анимира и стави у неку врсту покрета све што га је већ тада окупирало кроз сећања, призоре, имагинацију и жељу да проникне у наличја и тајанствене силе свакодневног живота малих америчких градова, што ће уосталом одредити његову јединствену уметничку визију и најавити феномен &bdquo;линчовски универзум&ldquo;.</p>', 'male-misterije-dejvida-linca', '2017-09-17 17:26:39', NULL, NULL, 1),
(31, 1, 'Article for english people', '<p>Lorem ipsum dolor sit amet, soleat causae sea te, pri percipitur delicatissimi eu. Cibo iusto menandri mel ei, diceret blandit mandamus vel eu. Magna oporteat reprimique in vel. Sonet tempor tincidunt duo te, nam ei oporteat dignissim instructior, id mei docendi explicari voluptaria.</p>\r\n<p>Est erat doctus contentiones ad, has an falli viderer inciderint. Sed docendi tincidunt ad. Postulant sententiae ius at, id vel putent iudicabit. An elitr vivendum cotidieque cum. Errem bonorum fuisset vis no. Iusto mediocrem eam at.</p>', 'article-for-english-people', '2018-01-21 14:41:07', NULL, 'rs', 0),
(32, 1, 'Tis is the first article on English', '<p>Lorem ipsum dolor sit amet, nam id percipitur definitiones. Lorem viris philosophia an qui, an dissentiet consequuntur est. Et velit vituperata duo, no solet habemus quo. In has esse molestiae constituto, et nam ubique persecuti disputationi.</p>\r\n<p>Tota impedit cum ut. Graeco possim cu qui, elit porro noluisse no eum. At errem senserit molestiae sit, paulo consetetur quo ex. Saepe patrioque eos eu, velit novum nihil in est. Ad posidonium vituperatoribus his.</p>\r\n<p>Quo noster utamur numquam no, putant antiopam definiebas te his. Te duis causae maiorum mea. Latine legendos platonem et sed. Est maiestatis voluptaria ut. No his impedit civibus incorrupte, his ex mollis iracundia euripidis, ponderum neglegentur vim ea. Et vix indoctum suavitate.</p>\r\n<p>Cum ea quodsi incorrupte scriptorem, nostrum sapientem in quo, probo posidonium intellegam at sea. Per justo minimum mentitum ei. Aliquip euismod vel id, duo cu aperiam persequeris adversarium, malorum fabellas sadipscing quo ei. Duo ne tempor labore eloquentiam. Nominavi nominati te sed, ea quo quot ridens salutatus.</p>', 'tis-is-the-first-article-on-english', '2018-01-21 16:38:43', 65, 'en', 1);

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
(1, 3),
(2, 3),
(14, 4),
(16, 1),
(16, 3),
(19, 3),
(20, 3),
(21, 4),
(22, 3),
(22, 4),
(23, 3),
(24, 3),
(25, 4),
(26, 3),
(27, 4),
(28, 3),
(29, 3),
(30, 3),
(31, 3),
(31, 5),
(32, 5),
(32, 6);

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
(2, 1),
(14, 1),
(14, 11),
(16, 1),
(16, 27),
(19, 1),
(20, 1),
(21, 1),
(21, 11),
(21, 12),
(22, 11),
(22, 13),
(23, 11),
(23, 13),
(24, 11),
(24, 13),
(24, 15),
(25, 11),
(26, 16),
(28, 17),
(31, 11),
(31, 20),
(31, 21);

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `ID` int(11) NOT NULL,
  `FIRST_NAME` varchar(30) DEFAULT NULL,
  `LAST_NAME` varchar(30) DEFAULT NULL,
  `DATE_OF_BIRTH` date DEFAULT NULL,
  `BIRTHPLACE` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  `SHORT_BIO` varchar(255) DEFAULT NULL,
  `PROFESSION` varchar(50) DEFAULT NULL,
  `AVATAR_PATH` varchar(255) DEFAULT NULL,
  `slug` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`ID`, `FIRST_NAME`, `LAST_NAME`, `DATE_OF_BIRTH`, `BIRTHPLACE`, `EMAIL`, `WEBSITE`, `SHORT_BIO`, `PROFESSION`, `AVATAR_PATH`, `slug`) VALUES
(1, 'Andrea', 'Kane', '1988-10-10', 'Milano, Italia', 'andrea.kane@sobakaisti.org', 'andreakane.sobakaisti.org', '', 'Pesnik / blogger', 'avatar_path_value', 'andrea-kane'),
(2, 'Stefan', 'Pas', '1987-12-01', 'Gornji Milnovac', 'stefn.pas@sobakaisti.com', '', '', 'Pisac', 'avatar_path_value', 'stefan-pas'),
(3, 'Astor', 'Lajka', '1988-10-12', 'Beograd', 'astor.lajkav@sobakaisti.org', '', '', 'muzicar', 'avatar_path_value', 'astor-lajka'),
(4, 'Igor', 'Repin', '1980-03-25', 'Vranje', 'igor.repin@sobakaisti.org', '', '', 'slikar', 'avatar_path_value', 'igor-repin'),
(5, 'Ivan', 'Sobakov', '1990-12-12', 'Gornji MIlanovac', 'ivan.sobakov@sobakaisti.org', '', '', 'pisac', 'avatar_path_value', 'ivan-sobakov'),
(7, 'Čkalja', 'Jokić', '1988-05-05', 'Beograd', 'cklaja@gmail.com', '', '', 'dizajner', 'avatar_path_value', 'ckalja-jokic'),
(8, 'Nemanja', 'Jelesijević', '1986-12-09', 'Gornji Milanovac', 'jelles@gmil.com', '', '', 'programer', 'avatar_path_value', 'nemanja-jelesijevic'),
(9, 'Јован', 'П. Брајовић', '1975-02-02', 'Zajecar', 'jovan.p.brajovic@sobakaisti.org', '', '', 'Knjizevni kriticar', 'avatar_path_value', 'jovan-p-brajovic');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `slug` varchar(60) DEFAULT NULL,
  `parent_category_id` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `slug`, `parent_category_id`) VALUES
(1, 'Radovi', 'arts', 0),
(3, 'Književnost', 'literature', 1),
(4, 'Muzika', 'music', 1),
(5, 'Slikarstvo', 'paintings', 1),
(6, 'Fotografija', 'photography', 1),
(7, 'Video', 'video', 1);

-- --------------------------------------------------------

--
-- Table structure for table `i18n_article`
--

CREATE TABLE `i18n_article` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` mediumtext,
  `lang` varchar(3) NOT NULL,
  `article_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Translated Articles';

-- --------------------------------------------------------

--
-- Stand-in structure for view `intro_bgd_post`
--
CREATE TABLE `intro_bgd_post` (
`id` int(11)
,`slug` varchar(255)
,`title` varchar(255)
,`content` longtext
,`lang` varchar(3)
);

-- --------------------------------------------------------

--
-- Table structure for table `media`
--

CREATE TABLE `media` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `post_date` timestamp NULL DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `lang` varchar(2) DEFAULT 'RS',
  `active` tinyint(4) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `content_type` varchar(255) DEFAULT '0',
  `size` int(11) DEFAULT NULL,
  `descriprion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `media`
--

INSERT INTO `media` (`id`, `title`, `slug`, `post_date`, `author_id`, `lang`, `active`, `file_name`, `path`, `content_type`, `size`, `descriprion`) VALUES
(1, 'Stewie griffin simple', 'stewie-griffin--family-guy---04-by-frasier-and-niles', '2017-11-26 14:31:44', NULL, 'rs', 1, 'stewie-griffin--family-guy---04-by-frasier-and-niles.jpg', NULL, 'image/jpeg', 75681, NULL),
(2, 'FamilyGuy Peter remote 72 56a00a585f9b58eba4ae9b36', 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36', '2017-11-26 14:43:51', NULL, 'rs', 1, 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36.jpg', NULL, 'image/jpeg', 15718, NULL),
(3, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-11-26 14:50:27', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(4, '2361498 peter griffin as batman family guy 5981460 591 1146', '2361498-peter-griffin-as-batman-family-guy-5981460-591-1146', '2017-11-26 15:24:36', NULL, 'rs', 1, '2361498-peter-griffin-as-batman-family-guy-5981460-591-1146.jpg', NULL, 'image/jpeg', 82970, NULL),
(5, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-11-26 15:32:47', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(6, 'Htn0xctsu4qdwxmfsyjq', 'htn0xctsu4qdwxmfsyjq', '2017-11-26 15:35:27', NULL, 'rs', 1, 'htn0xctsu4qdwxmfsyjq.jpg', NULL, 'image/jpeg', 279493, NULL),
(7, 'FamilyGuy Peter remote 72 56a00a585f9b58eba4ae9b36', 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36', '2017-11-26 15:36:07', NULL, 'rs', 1, 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36.jpg', NULL, 'image/jpeg', 15718, NULL),
(8, 'FamilyGuy Peter remote', 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36', '2017-11-26 15:37:41', 1, 'rs', 1, 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36.jpg', NULL, 'image/jpeg', 15718, 'Peter griffin sadaljinskim ureavljačem u rukama'),
(9, 'FamilyGuy Peter remote 72 56a00a585f9b58eba4ae9b36', 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36', '2017-11-26 16:55:27', NULL, 'rs', 1, 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36.jpg', NULL, 'image/jpeg', 15718, NULL),
(10, 'Peter as che', 'peter-as-che', '2017-11-26 16:55:46', NULL, 'rs', 1, 'peter-as-che.jpg', NULL, 'image/jpeg', 73805, NULL),
(11, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-11-26 16:57:06', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(12, 'StewiesGeneric 2010 R3buttF showing ass', 'stewiesgeneric-2010-r3buttf-showing-ass', '2017-11-26 17:02:05', NULL, 'rs', 1, 'stewiesgeneric-2010-r3buttf-showing-ass.jpg', NULL, 'image/jpeg', 22250, NULL),
(13, 'Worldinmyeyes7', 'worldinmyeyes7', '2017-11-26 17:03:29', NULL, 'rs', 1, 'worldinmyeyes7.jpg', NULL, 'image/jpeg', 57467, NULL),
(14, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-11-26 17:36:23', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(15, 'Htn0xctsu4qdwxmfsyjq', 'htn0xctsu4qdwxmfsyjq', '2017-11-26 17:40:52', NULL, 'rs', 1, 'htn0xctsu4qdwxmfsyjq.jpg', NULL, 'image/jpeg', 279493, NULL),
(16, 'FamilyGuy Peter remote 72 56a00a585f9b58eba4ae9b36', 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36', '2017-11-26 17:41:27', NULL, 'rs', 1, 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36.jpg', NULL, 'image/jpeg', 15718, NULL),
(17, '2361498 peter griffin as batman family guy 5981460 591 1146', '2361498-peter-griffin-as-batman-family-guy-5981460-591-1146', '2017-11-26 17:41:55', NULL, 'rs', 1, '2361498-peter-griffin-as-batman-family-guy-5981460-591-1146.jpg', NULL, 'image/jpeg', 82970, NULL),
(18, 'Stewie Griffin', 'stewie-griffin', '2017-11-26 17:43:44', NULL, 'rs', 1, 'stewie-griffin.jpg', NULL, 'image/jpeg', 108108, NULL),
(19, '49eec25769d754d291c314c360a289ae', '49eec25769d754d291c314c360a289ae', '2017-11-26 17:44:08', NULL, 'rs', 1, '49eec25769d754d291c314c360a289ae.jpg', NULL, 'image/jpeg', 434073, NULL),
(21, 'FamilyGuy Peter ', 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36', '2017-11-26 17:49:28', NULL, 'rs', 1, 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36.jpg', NULL, 'image/jpeg', 15718, NULL),
(22, 'Htn0xctsu4qdwxmfsyjq', 'htn0xctsu4qdwxmfsyjq', '2017-11-26 17:49:56', NULL, 'rs', 1, 'htn0xctsu4qdwxmfsyjq.jpg', NULL, 'image/jpeg', 279493, NULL),
(23, 'Worldinmyeyes7', 'worldinmyeyes7', '2017-11-26 19:11:30', NULL, 'rs', 1, 'worldinmyeyes7.jpg', NULL, 'image/jpeg', 57467, NULL),
(24, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-11-26 19:12:04', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(25, '2361498 peter griffin as batman family guy 5981460 591 1146', '2361498-peter-griffin-as-batman-family-guy-5981460-591-1146', '2017-11-26 19:12:38', NULL, 'rs', 1, '2361498-peter-griffin-as-batman-family-guy-5981460-591-1146.jpg', NULL, 'image/jpeg', 82970, NULL),
(26, 'Htn0xctsu4qdwxmfsyjq', 'htn0xctsu4qdwxmfsyjq', '2017-11-26 19:15:30', NULL, 'rs', 1, 'htn0xctsu4qdwxmfsyjq.jpg', NULL, 'image/jpeg', 279493, NULL),
(27, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-11-26 19:15:47', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(28, '2361498 peter griffin as batman family guy 5981460 591 1146', '2361498-peter-griffin-as-batman-family-guy-5981460-591-1146', '2017-11-26 19:18:11', NULL, 'rs', 1, '2361498-peter-griffin-as-batman-family-guy-5981460-591-1146.jpg', NULL, 'image/jpeg', 82970, NULL),
(29, 'FamilyGuy Peter remote 72 56a00a585f9b58eba4ae9b36', 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36', '2017-11-26 19:18:37', NULL, 'rs', 1, 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36.jpg', NULL, 'image/jpeg', 15718, NULL),
(30, '49eec25769d754d291c314c360a289ae', '49eec25769d754d291c314c360a289ae', '2017-11-26 19:19:19', NULL, 'rs', 1, '49eec25769d754d291c314c360a289ae.jpg', NULL, 'image/jpeg', 434073, NULL),
(31, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-11-26 19:19:36', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(33, 'Htn0xctsu4qdwxmfsyjq', 'htn0xctsu4qdwxmfsyjq', '2017-11-26 19:23:36', NULL, 'rs', 1, 'htn0xctsu4qdwxmfsyjq.jpg', NULL, 'image/jpeg', 279493, NULL),
(34, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-11-26 19:23:52', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(35, 'FamilyGuy Peter remote 72 56a00a585f9b58eba4ae9b36', 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36', '2017-11-26 19:24:39', NULL, 'rs', 1, 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36.jpg', NULL, 'image/jpeg', 15718, NULL),
(36, 'Pdf icon', 'pdf-icon', '2017-11-26 19:25:08', NULL, 'rs', 1, 'pdf-icon.png', NULL, 'image/png', 7092, NULL),
(37, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-11-26 19:25:29', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(38, 'Peter griffin  family guy   19 by frasier and niles d8yfsiu', 'peter-griffin--family-guy---19-by-frasier-and-niles-d8yfsiu', '2017-12-02 15:22:50', NULL, 'rs', 1, 'peter-griffin--family-guy---19-by-frasier-and-niles-d8yfsiu.jpg', NULL, 'image/jpeg', 78198, NULL),
(39, 'Stewie Griffin', 'stewie-griffin', '2017-12-02 17:09:33', NULL, 'rs', 1, 'stewie-griffin.jpg', NULL, 'image/jpeg', 108108, NULL),
(41, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-12-02 17:41:09', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(42, 'Htn0xctsu4qdwxmfsyjq', 'htn0xctsu4qdwxmfsyjq', '2017-12-02 17:46:29', NULL, 'rs', 1, 'htn0xctsu4qdwxmfsyjq.jpg', NULL, 'image/jpeg', 279493, NULL),
(43, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-12-02 17:48:48', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(44, 'Htn0xctsu4qdwxmfsyjq', 'htn0xctsu4qdwxmfsyjq', '2017-12-02 17:49:47', NULL, 'rs', 1, 'htn0xctsu4qdwxmfsyjq.jpg', NULL, 'image/jpeg', 279493, NULL),
(46, 'Stewie griffin  family guy   04 by frasier and niles', 'stewie-griffin--family-guy---04-by-frasier-and-niles', '2017-12-02 17:58:29', NULL, 'rs', 1, 'stewie-griffin--family-guy---04-by-frasier-and-niles.jpg', NULL, 'image/jpeg', 75681, NULL),
(47, 'FamilyGuy Peter remote 72 56a00a585f9b58eba4ae9b36', 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36', '2017-12-02 17:59:26', NULL, 'rs', 1, 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36.jpg', NULL, 'image/jpeg', 15718, NULL),
(48, 'Peter as che', 'peter-as-che', '2017-12-02 18:00:13', NULL, 'rs', 1, 'peter-as-che.jpg', NULL, 'image/jpeg', 73805, NULL),
(49, 'Peter as che', 'peter-as-che', '2017-12-02 18:31:08', NULL, 'rs', 1, 'peter-as-che.jpg', NULL, 'image/jpeg', 73805, NULL),
(50, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-12-02 18:32:36', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(51, 'Peter as che', 'peter-as-che', '2017-12-02 21:06:57', NULL, 'rs', 1, 'peter-as-che.jpg', NULL, 'image/jpeg', 73805, NULL),
(52, 'Peter as che', 'peter-as-che', '2017-12-02 21:14:09', NULL, 'rs', 1, 'peter-as-che.jpg', NULL, 'image/jpeg', 73805, NULL),
(53, 'FamilyGuy Peter remote 72 56a00a585f9b58eba4ae9b36', 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36', '2017-12-02 21:18:53', NULL, 'rs', 1, 'familyguy-peter-remote-72-56a00a585f9b58eba4ae9b36.jpg', NULL, 'image/jpeg', 15718, NULL),
(54, 'Peter as che', 'peter-as-che', '2017-12-03 14:12:58', NULL, 'rs', 1, 'peter-as-che.jpg', NULL, 'image/jpeg', 73805, NULL),
(55, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389', '2017-12-03 17:12:45', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389.jpg', NULL, 'image/jpeg', 37255, NULL),
(56, 'Peter griffin  family guy   19 by frasier and niles d8yfsiu', 'peter-griffin--family-guy---19-by-frasier-and-niles-d8yfsiu', '2017-12-03 17:14:32', NULL, 'rs', 1, 'peter-griffin--family-guy---19-by-frasier-and-niles-d8yfsiu.jpg', NULL, 'image/jpeg', 78198, NULL),
(57, '5621Anthony de Mello Budjenje', '5621anthony-de-mello-budjenje', '2017-12-03 17:21:54', NULL, 'rs', 1, '5621anthony-de-mello-budjenje.pdf', NULL, 'application/pdf', 686626, NULL),
(58, 'Osnovi telekomunikacija', 'osnovi-telekomunikacija', '2017-12-03 17:33:39', NULL, 'rs', 1, 'osnovi-telekomunikacija.docx', NULL, 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 310338, NULL),
(59, 'Backtrack2015HDRipXViD ETRG', 'backtrack2015hdripxvid-etrg', '2017-12-03 17:41:16', NULL, 'rs', 1, 'backtrack2015hdripxvid-etrg.srt', NULL, 'application/octet-stream', 27956, NULL),
(60, '5621Anthony de Mello Budjenje', '5621anthony-de-mello-budjenje', '2017-12-03 17:42:02', NULL, 'rs', 1, '5621anthony-de-mello-budjenje.pdf', NULL, 'application/pdf', 686626, NULL),
(61, 'WWWFOXMTO', 'wwwfoxmto', '2017-12-03 18:09:09', NULL, 'rs', 1, 'wwwfoxmto.jpg', NULL, 'image/jpeg', 123571, NULL),
(62, 'Predlog 64852', 'predlog-64852', '2017-12-03 18:10:38', NULL, 'rs', 1, 'predlog-64852.png', NULL, 'image/png', 45154, NULL),
(63, 'Worldinmyeyes7', 'worldinmyeyes7-2', '2017-12-03 18:14:34', NULL, 'rs', 1, 'worldinmyeyes7-2.jpg', NULL, 'image/jpeg', 57467, NULL),
(64, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389-13', '2017-12-03 18:15:39', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389-13.jpg', NULL, 'image/jpeg', 37255, NULL),
(65, 'Stewie Griffin', 'stewie-griffin-4', '2017-12-03 19:08:15', NULL, 'rs', 1, 'stewie-griffin-4.jpg', NULL, 'image/jpeg', 108108, NULL),
(66, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389-14', '2017-12-03 19:08:26', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389-14.jpg', NULL, 'image/jpeg', 37255, NULL),
(67, '2361498 peter griffin as batman family guy 5981460 591 1146', '2361498-peter-griffin-as-batman-family-guy-5981460-591-1146-4', '2018-01-02 18:41:07', NULL, 'rs', 1, '2361498-peter-griffin-as-batman-family-guy-5981460-591-1146-4.jpg', NULL, 'image/jpeg', 82970, NULL),
(68, 'Ass race peter griffin 29342485 480 389', 'ass-race-peter-griffin-29342485-480-389-15', '2018-01-02 18:46:38', NULL, 'rs', 1, 'ass-race-peter-griffin-29342485-480-389-15.jpg', NULL, 'image/jpeg', 37255, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `publication`
--

CREATE TABLE `publication` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `slug` varchar(255) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `post_date` timestamp NULL DEFAULT NULL,
  `active` tinyint(4) NOT NULL,
  `downloaded` int(5) NOT NULL DEFAULT '0',
  `author_id` int(11) NOT NULL,
  `lang` varchar(2) DEFAULT 'RS',
  `media_id` int(11) DEFAULT NULL,
  `featured_img_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `publication`
--

INSERT INTO `publication` (`id`, `title`, `content`, `slug`, `path`, `post_date`, `active`, `downloaded`, `author_id`, `lang`, `media_id`, `featured_img_id`) VALUES
(1, 'Probno izdanje', 'Ovo je neko probno izdanje ', 'probno-izdanje', 'probno-izdaje-andrea-kane', '2017-07-08 22:00:00', 1, 0, 1, 'RS', NULL, NULL),
(2, 'Proba novog članka za publications', '', 'proba-novog-clanka-za-publications', 'P7.pdf', '2017-09-23 15:54:24', 1, 0, 3, NULL, NULL, NULL),
(3, 'Proba Älanka bez post date', '<p>bez post date</p>', 'proba-clanka-bez-post-date', NULL, '2017-12-16 22:27:32', 0, 0, 1, 'rs', NULL, NULL),
(4, 'Proba sa formatiranim postDate', '<p>formatiran post date</p>', 'proba-sa-formatiranim-postdate', NULL, '2017-12-16 22:56:47', 0, 0, 1, 'rs', NULL, NULL),
(5, 'Ovo je poslednji Älanak sa probom datuma', '<p>poslednji Älanak</p>', 'ovo-je-poslednji-clanak-sa-probom-datuma', NULL, '2017-12-16 23:04:33', 1, 0, 2, 'rs', NULL, NULL),
(6, 'Publikejšn subotom', '<p>flfgfggfhhh</p>', 'publikejsn-subotom', NULL, '2017-12-23 15:31:18', 1, 0, 1, 'rs', NULL, 61),
(7, 'Još jedan pulicejšn sa feč tagovima', '', 'jos-jedan-pulicejsn-sa-fec-tagovima', NULL, '2017-12-23 16:18:44', 1, 0, 2, 'rs', NULL, NULL),
(8, 'publikešn sa dva taga', '', 'publikesn-sa-dva-taga', NULL, '2017-12-23 16:21:12', 0, 0, 1, 'rs', NULL, NULL),
(9, 'Proba publikejšna sa tri taga', '', 'proba-publikejsna-sa-tri-taga', NULL, '2017-12-23 17:44:46', 1, 0, 1, 'rs', NULL, NULL),
(10, 'Još jedna proba izdanja', '', 'jos-jedna-proba-izdanja', NULL, '2017-12-23 17:47:50', 1, 0, 1, 'rs', NULL, NULL),
(11, 'pulikejšn sa full fatch tag listom', '', 'pulikejsn-sa-full-fatch-tag-listom', NULL, '2017-12-23 17:52:26', 0, 0, 3, 'rs', NULL, NULL),
(12, 'konačna proba publkejšna', '', 'konacna-proba-publkejsna', NULL, '2017-12-23 17:59:48', 0, 0, 1, 'rs', NULL, NULL),
(13, 'Probaj još tagova', '', 'probaj-jos-tagova', NULL, '2017-12-23 18:08:47', 0, 0, 1, 'rs', NULL, NULL),
(14, 'kaneova publikacija', '', '-13', NULL, '2017-12-23 18:24:13', 0, 0, 1, 'rs', NULL, NULL),
(15, 'sobakaisti književnost muzikolog', '<p>Proba članaka sa 5 Tagova</p>', 'sobakaisti-knjizevnost-muzikolog', NULL, '2017-12-23 18:30:37', 1, 0, 2, 'rs', NULL, NULL),
(16, 'kanetov publikejšn', '', 'kanetov-publikejsn', NULL, '2017-12-23 18:45:40', 0, 0, 1, 'rs', NULL, NULL),
(17, 'Proba dodavnja tagova preko tastera enter', '', 'proba-dodavnja-tagova-preko-tastera-enter', NULL, '2017-12-23 21:48:00', 0, 0, 1, 'rs', NULL, NULL),
(18, 'proba novih tagova', '', 'proba-novih-tagova', NULL, '2017-11-01 21:52:00', 1, 0, 4, 'rs', NULL, NULL),
(19, 'proba message boxa', '', 'proba-message-boxa', NULL, '2017-12-30 21:36:59', 0, 0, 3, 'rs', NULL, NULL),
(20, 'proba sa džava', '', 'proba-sa-dzava', NULL, '2017-12-30 21:53:41', 0, 0, 5, 'rs', NULL, NULL),
(21, 'proba tiksadkasd ksadasj', '', 'proba-tiksadkasd-ksadasj', NULL, '2017-12-30 22:00:20', 0, 0, 2, 'rs', NULL, NULL),
(22, 'kdaskhdkas das dashdasdash', '', '-21', NULL, '2017-12-31 19:48:35', 0, 0, 2, 'rs', NULL, NULL),
(23, 'jashda dhajdjadjajhd', '', 'jashda-dhajdjadjajhd', NULL, '2017-12-31 19:52:37', 0, 0, 2, 'rs', NULL, NULL),
(24, 'skajdsajd  djaskdsakdask', '', 'skajdsajd--djaskdsakdask', NULL, '2017-12-31 19:59:58', 0, 0, 1, 'rs', NULL, NULL),
(25, 'ksajdkjkaks kasjkdasjjdkasjk', '', 'ksajdkjkaks-kasjkdasjjdkasjk', NULL, '2017-12-31 20:03:24', 0, 0, 1, 'rs', NULL, NULL),
(26, 'djashdjashdj', '', 'djashdjashdj', NULL, '2017-12-31 20:10:27', 1, 0, 1, 'rs', NULL, NULL),
(27, 'asdsas', '', 'asdsas', NULL, '2018-01-01 13:42:08', 0, 0, 2, 'rs', NULL, NULL),
(28, 'nsjdhdjhapdsahdp', '', 'nsjdhdjhapdsahdp', NULL, '2018-01-01 13:44:37', 0, 0, 5, 'rs', NULL, NULL),
(29, 'dahjdjahdjashd hasjdh', '', 'dahjdjahdjashd-hasjdh', NULL, '2018-01-01 14:25:31', 1, 0, 3, 'rs', NULL, NULL),
(30, 'dasjdddha jasdjsajd', '', 'dasjdddha-jasdjsajd', NULL, '2018-01-01 16:41:38', 0, 0, 1, 'rs', NULL, NULL),
(31, 'dsrrtyrtyrry', '', 'dsrrtyrtyrry', NULL, '2018-01-01 16:45:03', 0, 0, 4, 'rs', NULL, NULL),
(32, 'oidoi ads ahsdhasdasdh ', '', 'oidoi-ads-ahsdhasdasdh', NULL, '2018-01-01 16:46:11', 0, 0, 1, 'rs', NULL, NULL),
(33, 'djashdasd asd hadja', '', 'djashdasd-asd-hadja', NULL, '2018-01-01 16:47:35', 0, 0, 3, 'rs', NULL, NULL),
(34, 'dkjddjsakjdkasjk', '', 'dkjddjsakjdkasjk', NULL, '2018-01-01 16:49:17', 0, 0, 4, 'rs', NULL, NULL),
(35, 'dhahsadj asdjasjd', '', 'dhahsadj-asdjasjd', NULL, '2018-01-01 17:01:42', 0, 0, 1, 'rs', NULL, NULL),
(36, 'proba uploada članka sa rezultatom ', '', 'proba-uploada-clanka-sa-rezultatom', NULL, '2018-01-01 17:41:21', 0, 0, 4, 'rs', NULL, NULL),
(37, 'Članak koje ćemo publikovati', '', 'clanak-koje-cemo-publikovati', NULL, '2018-01-01 18:13:44', 1, 0, 5, 'rs', NULL, NULL),
(38, 'Objavi članak sa abstraktnomm klasom', '', 'objavi-clanak-sa-abstraktnomm-klasom', NULL, '2018-01-02 16:31:59', 0, 0, 2, 'rs', NULL, NULL),
(39, 'uploadujem predlog publikejšna', '', 'uploadujem-predlog-publikejsna', NULL, '2018-01-02 18:46:28', 1, 0, 4, 'rs', 62, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `publication_tag`
--

CREATE TABLE `publication_tag` (
  `publication_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `publication_tag`
--

INSERT INTO `publication_tag` (`publication_id`, `tag_id`) VALUES
(1, 1),
(2, 1),
(6, 1),
(7, 11),
(8, 11),
(9, 11),
(9, 19),
(9, 20),
(9, 21),
(9, 22),
(10, 1),
(10, 11),
(10, 21),
(10, 20),
(10, 1),
(11, 11),
(11, 19),
(11, 20),
(11, 21),
(11, 22),
(12, 1),
(12, 11),
(12, 15),
(12, 21),
(12, 22),
(13, 1),
(13, 11),
(13, 15),
(13, 20),
(13, 22),
(14, 1),
(14, 11),
(14, 15),
(14, 20),
(14, 22),
(15, 1),
(15, 11),
(15, 15),
(15, 19),
(15, 20),
(16, 1),
(16, 11),
(16, 15),
(16, 19),
(16, 21),
(16, 22),
(17, 24),
(17, 25),
(18, 11),
(18, 21),
(18, 23),
(18, 28),
(18, 29),
(37, 30);

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
(11, 'muzika', 'muzika'),
(12, 'gitara', 'gitara'),
(13, 'radio202', 'radio202'),
(14, 'televizija', 'televizija'),
(15, 'Književnost', 'knjizevnost'),
(16, 'postmoderna', 'postmoderna'),
(17, 'Politika', 'politika'),
(18, 'собакаисти', 'sobakaisti'),
(19, 'muzikolog', 'mozikolog'),
(20, 'muza', 'muza'),
(21, 'muzej', 'muzej'),
(22, 'muzički urednik', 'mozicki-urednik'),
(23, 'pivo', 'pivo'),
(24, 'obrva', 'obrva'),
(25, 'kafa', 'kafa'),
(26, 'tatatura', 'tatatura'),
(27, 'sobaka', 'sobaka'),
(28, 'limenka', 'limenka'),
(29, 'čaša', 'casa'),
(30, 'vulkan', 'vulkan');

-- --------------------------------------------------------

--
-- Structure for view `intro_bgd_post`
--
DROP TABLE IF EXISTS `intro_bgd_post`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `intro_bgd_post`  AS  select `article`.`id` AS `id`,`article`.`slug` AS `slug`,`article`.`title` AS `title`,`article`.`content` AS `content`,`article`.`lang` AS `lang` from `article` where (`article`.`slug` = 'manifesto') ;

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
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `i18n_article`
--
ALTER TABLE `i18n_article`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `media`
--
ALTER TABLE `media`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `publication`
--
ALTER TABLE `publication`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `media`
--
ALTER TABLE `media`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
--
-- AUTO_INCREMENT for table `publication`
--
ALTER TABLE `publication`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT for table `tag`
--
ALTER TABLE `tag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
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
