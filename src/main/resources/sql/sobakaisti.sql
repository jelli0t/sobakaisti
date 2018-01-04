-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 04, 2018 at 11:27 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.8

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
  `featured_img_id` int(11) DEFAULT NULL,
  `active` int(2) DEFAULT NULL COMMENT 'is published'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`id`, `author_id`, `title`, `content`, `slug`, `post_date`, `lang`, `featured_img_id`, `active`) VALUES
(1, 1, 'Mafifest', '<p class="western" lang="" style="text-align: justify" align="CENTER">  Ми смо Собакаисти. Нисмо се удружили из политичких разлога, није нас никаква пропаганда спојила, не држе нас националне ни верске стеге, ништа тако јефтино као крв нас не спаја, новцу се не клањамо. Нисмо се окупили око једне заставе, нити нас је иста звезда водила до овог места. Наше су везе егзистенцијалне – ми живимо ради уметности. Ми смо сви дављеници у мору баналне свакодневице, а овај кружок је наше острво, наша мала ада, наша лука у коју пристајемо да се одморимо, јер живот је проклето дуг, а море је ретко мирно. Сваки од нас је свој њух пратио да би дошао довде. Препознали смо се међусобно по неком тајном знаку који нам нико није показао, чули смо музику и пратили смо звук, свако је у себи нашао обележје, чудни белег наше судбине, јер ми сви верујемо да смо изабраници, да смо пророци у новом свету где ће уметност бити важнија од телевизије, важнија од забаве, где ће уметност бити потреба, физичка и духовна неопходност.</p>\r\n<p class="western" lang="" style="text-align: justify">  Ми смо Собакаисти. Ми смо пси што бесно гризу за уметност, ми смо чувари богате ризнице светске културне традиције, ми смо водичи онима што не знају за овај пут. Нисмо постмодернисти, нисмо лапурлатисти, али нисмо ни инокосни - знамо ко је пре нас био овде, али нико се до кости није дао као што је сваки од нас спреман. Има у нама оне силе са почетка двадесетог века, када је у ратовима, у крвавим окршајима, и књижевност учествовала, када су слике бомбардовале, а филмови решетали масе. Има у нама нешто од те ангажованости, али ми нећемо нове обрачуне, срамну нељудскост света, ми хоћемо да се човечанство и у миру сети хуманизма, да се пита о смислу живота, а како другачије него - уметношћу. Не мислимо да је масна кобасица важнија од Шекспира, нити да Шекспир засити празан стомак, али лајемо на оне што срљају у тривијалност постојања, лајемо на дебеле мешине што нису гладне уметности.</p>\r\n<p class="western" lang="" style="text-align: justify">  Ми смо Собакаисти и стварање нам је светиња. Верујемо у Креацију, у оно што надвисује пропадљивост и деструкцију, оно што досеже даље од сваке периодике, оно што може да се огласи у тишини векова. Верујемо да ће све што створи и један од нас променити свет; не бринемо се када ће се то догодити, да ли сутра или за хиљаду година, јер за нас је Алтамира музеј човечанства, исто као и Музеј савремене уметности у Београду, (недоступан колико и сама пећина). Све што смо данас посејали, може да избије из земље било када у вечности постојања, као што смо ми нашли корене у целом свету и жиле су нам дубоко у Јужној Америци, у култури Инка и Маја, у Африци и њеној болној историји, у џезу, у Лувру, у Русији и Истоку, и дубоко, дубоко, дубоко на Балкану. И како смо појединачно налазили пут кроз густу шуму глупости, тако ће и нас неко наћи, јер онај који се даје тражењу, тај и налази.</p>\r\n<p class="western" lang="" style="text-align: justify">  Ми смо Собакаисти и не плашимо се грешке, импровизације, искрености, покушаја, муке и зноја. Не плашимо се да ћемо остати незапажени, а не жудимо за славом. Наши су циљеви да променимо стварност, а за то смо одабрали тежак пут, да од овога што имамо створимо бољи свет, а не да уништимо све постојеће и из пепела подижемо нашу реалност. Ми знамо, неки од нас су и видели будућност – уметност ће спасити човечанство и планета Земља биће домовина свих нас. Ове Речи су нови почетак.</p>\r\n<p class="western" style="text-align: justify"><span lang="">  Радићемо неуморно. И прошириваћемо се. Дозиваћемо једни друге и спајати се. Нећемо бити странци ниједној уметности и у свакој ћемо дати нову стварност. Крећемо од књижевности, јер је и у првом кругу на почетку била Реч. </span></p>\r\n<p class="western" lang="" style="text-align: justify">Ми, Собакаисти, заклињемо се да ћемо верно служити Стварању, заклињемо се на вечну верност Уметности.</p>', 'manifesto', '2017-03-16 10:00:00', 'rs', NULL, 1),
(2, 1, 'Manifesto', '<p>  We are Sobakaists. We didn’t join forces for political reasons and no propaganda brought us together. No chains of nationalism or religion hold us down nor do substances as insignificant as blood tie us together. We don’t bow down to money. We do not stand under one flag nor has the same star guided us to this very place. Our relations are purely existentialist in nature – we live for art. We are continually drowning in an ocean of everyday banality. This group represents our refuge, our very own islet, a harbour we land in to rest, for life is terribly long and the ocean is seldom steady. Each one of us reached this inlet relying solely on the sense of smell. We knew one another by a secret sign no one revealed to us previously. We heard music and traced its sound. Each one of us discovered a mark within, an odd emblem of fate, for we rest assured we are the chosen ones, prophets of a new world wherein art bears far more importance than television, more significance than fun, wherein it constitutes a dire need, a physical and spiritual necessity.</p>\r\n\r\n<p>  We are Sobakaists. We are hounds that furiously bite for art, we are the keepers of the riches of the world cultural heritage, and we guide the unaware down these paths. We are no postmodernists, we do not propose L’art pour l’art, nor are we solitary – we recognize our predecessors’ efforts. However, none of them have devoted themselves to art as much as all of us are willing to. We find in ourselves the traces of the early 20th century force, when literature partook in wars and bloody conflicts, when paintings bombarded and movies executed masses. We possess a degree of that commitment but we don’t crave new conflicts, the terrible inhumanity of the world. We merely want human kind to nurture humanism even in times of peace and question the meaning of life, which one may only accomplish through art. We do not consider a greasy sausage more important than Shakespeare nor do we believe that Shakespeare may sate an empty stomach, but we howl at those nose-diving into the triviality of existence, we snarl at fat guts not craving art.</p>\r\n\r\n<p>  We are Sobakaists and the creative process is our sanctity. We believe in Creation, in phenomena that endure corruption and destruction, that reach further than any periodicals and are not drowned in the silence of the centuries. We maintain either one of us can change the world with his or her creative invention. Timing is of no concern to us, as it may happen tomorrow or in thousands of years. To us, Altamira is a museum of mankind, the same as the Museum of Contemporary Art in Belgrade (inaccessible as the cave itself). Everything we’ve sown so far may grow at any moment throughout the eternity of existence, as we discovered our roots throughout the world. Our roots are embedded deeply in South America, the Incan and Mayan culture, in Africa and its abhorrent history, in jazz, Louvre, Russia and the East and deep in the very heart of the Balkans. More adherents are to find us in the same way we located one another in the dense forest of stupidity, for whoever seeks shall eventually discover.</p>\r\n\r\n<p>  We are Sobakaists and we do not fear mistakes, improvisations, honesty, attempts, grief and sweat. We do not fear being left outside the spotlight and we do not crave fame. Our goal is to change reality and we have opted for a difficult path in our mission. We aim to create a better world out of the existing one, instead of simply ruining everything in our present experience and recreating a new reality from the ashes of the previous one. Some of us have seen the future, while others are firmly aware that art will save mankind and that planet Earth will be our homeland. These Words represent a new beginning.</p>\r\n\r\n  We shall work vigorously. And expand. We shall beckon each other and bond. We shall be no strangers to any kind of art and provide a new reality to each of them. Literature shall be our starting point, since in the beginning was the Logos.\r\n\r\n  We, the Sobakaists, swear to eternally serve Creation and proclaim eternal fidelity to Art.', 'manifesto', '2017-03-15 07:00:00', 'en', NULL, 1),
(3, 23, 'Članak broj #1', '<p>NO Since id is defined as primitive int, it will be default initialized with 0 and it will never be null. There is no need to check primitive types, if they are null. They will never be null. If you want, you can compare against the default value 0 as if(person.getId()==0){}.</p>', 'clanak-broj-1-1', '2017-03-08 11:00:22', NULL, NULL, 0),
(9, 1, 'proba kategorija', '<p>fdsfsdf sd f</p>', 'proba-kategorija', '2017-04-07 22:28:58', 'rs', NULL, 0),
(13, 1, 'Kopile postmoderne: ili šta sam naučio na fakultetu', '<p>sdfsfsdfds sdfsdfsdfsd</p>', 'kopile-postmoderne-ili-sta-sam-naucio-na-fakultetu', '2017-04-12 19:39:15', 'rs', 0, 1),
(14, 16, 'Članak sa posla, proba', '<p>sadasdasdasd asd asdasd</p>', 'clanak-sa-posla-proba', '2017-04-13 12:01:43', 'rs', NULL, 1),
(15, 16, 'Proba članka sa selektovanim kategorijama, i tagovima', '', '', '2017-04-19 21:04:25', 'rs', NULL, 1),
(16, 16, 'Astor Lajka - Marina', '', 'astor-lajka-marina', '2017-04-19 21:14:41', 'rs', NULL, 1),
(17, 1, 'Andrea Kane - Pleh Orkestar', '', 'andrea-kane-pleh-orkestar', '2017-04-21 07:52:37', 'rs', NULL, 1),
(18, 17, 'Proba članka Jovna p. Brajovića', '<p>Ovo je proba novg ćlanka</p>', 'proba-clanka-jovna-p-brajovica', '2017-04-25 09:26:01', 'rs', 0, 1),
(19, 1, 'Provera', '<p>Ako ovo ne radi, klinac, najebao si.&nbsp;</p>', 'provera', '2017-04-26 10:39:58', 'rs', NULL, 0),
(20, 1, 'Nova proba članka', '', 'nova-proba-clanka', '2017-05-09 21:15:42', 'rs', NULL, 1),
(62, 19, 'Prva Stvar', '<p>Sanjam luku u kojoj spavaju čamci<br />duboko duboko<br />Žene na doku podigle su ruke<br />visoko visoko<br />Negde za nas nove zastave se kroje<br />to su novi logori i bombe<br />i čuje se glas onih &scaron;to se boje<br />da, to je zvuk sirena za brodove da plove<br />Sanjam</p>\n<p>Sanjam prugu koja i posle mesec dana nema kraja<br />bife vagon i viski uz cigaru<br />i da brojim otkucaje pe&scaron;čanog sata<br />da imam svoj strah i mislim da sam jači<br />da nosim tetovažu po kojoj me znaju<br />da govorim glasno dok podižem ruku<br />da sam čovek u odelu ja to sanjam</p>\n<p>Sanjam zemlju<br />u kojoj večno ki&scaron;a pada vi&scaron;e nego &scaron;to nam sunce sja<br />Na&scaron;a polja su potopljena krvlju<br />i gde god da stane&scaron; tu su kosti predaka<br />da nepismeni i dalje istoriju pi&scaron;u<br />a mi se oporavljamo od pro&scaron;ljih pobeda<br />zato ne slu&scaron;aj &scaron;ta kaže čovek u odelu<br />daj nam malo mira dosta je bilo bitaka</p>', 'prva-stvar', '2017-06-22 06:34:04', 'rs', NULL, 1),
(67, 1, 'Brisel blista', '<p>B: Pa da&hellip; Mislim, to može da se gleda i kao gre&scaron;ka, ali jebi ga&hellip; Ne može&scaron; da veruje&scaron; koliko me je ovaj spot iscrpio. Dao sam se toliko u njega, od planiranja, do cimanja za neke objekte gde je trebalo da snimamo, pa sve do scenografije&hellip; I na kraju nije ispalo onako kako sam zami&scaron;ljao&hellip; Ona scena kada ljudi idu na nju &ndash; tu je trebalo da bude makar pedeset ljudi da bi to izgledalo kako sam zamislio. Ovako je mnogo drugačije&hellip; ne znam kako je drugima, ali meni se ta scena čini neubedljivom i to me ubija. Ali bilo mi je najbitnije da bend bude zadovoljan. To sam naučio od pro&scaron;log puta, moram da napravim kompromis jer je nekome to mnogo važnije nego meni&hellip; Imala je jedna scena koja je odgovarala onim stihovima &bdquo;mehanička smrt&ldquo;&hellip; Snimio sam predivan kadar pod ringi&scaron;pilom kao nekom mehaničkom, svetlećom kro&scaron;njom! Keve mi, ne bi verovao kako dobro izgleda taj kadar. Ali oni su odlučili da ga izbacimo, i eto&hellip; Mislim, nije to uticalo na kvalitet samog spota, ali meni je u glavi i dalje ta scena i drugačije vidim ceo spot.</p>\n<p>AK: Mora da prođe malo vremena pa onda da ga pogleda&scaron; opet, objektivnije&hellip; A i da čuje&scaron; komentare ljudi&hellip;</p>\n<p>B: Ma ljudi će ovde da ti kažu sve, samo ne ono &scaron;to misle. Čuo sam u prolazu dvoje kako pričaju, dečko kaže devojci kako ima neki kadar gde se ni&scaron;ta ne de&scaron;ava ne znam koliko dugo&hellip; &scaron;ta treba da se de&scaron;ava? Za&scaron;to ljudi gledaju spotove ili bilo &scaron;ta drugo sa nekim svojim očekivanjima kako to treba i mora da izgleda? To me je tako usralo, jer sigurno oni nisu jedini&hellip; Ja nisam pravio spot da opi&scaron;em tekst pesme, nego jedno sa drugim&hellip; ono&hellip; imaju zajedničku ideju. Spot daje neko novo značenje pesmi, kao &scaron;to bez pesme spot ne bi mogao da funkcioni&scaron;e. Možda ja serem, ali stvarno mislim da spotovi treba da funkcioni&scaron;u po zakonitostima filma i muzike. U njihovom spoju! Na primer ritam pesme, dominantni zvuk, prelaz u pesmi, pevanje, sve to mora da bude usklađeno sa dužinom kadra, a onda i sa onim &scaron;ta je u kadru i za&scaron;to je ba&scaron; to&hellip; Vidi ove scene&hellip; [03:41-04:07] Je l&rsquo; provljajue&scaron; kako se kadrovi smenjuju na dobo&scaron;? Pa onda je ovde duže zbog solo gitare, pa ga opet vrati u isti ritam, a onda su sve kraći kadrovi da bi puklo onim prelazom i onda u refren&hellip; mislim, meni je to bitno, ja to primećujem jer verujem da tako treba da funkcioni&scaron;e spot, kao video napravljen po muzici, ali da ne bude banalan kao ono na &bdquo;Tjubu&ldquo; kad prave slajd&scaron;ou, pa ovaj u pesmi kaže cvet, a pojavi se slika cveta, ili reč ljubav, a ono neko odvratno srculence i neki smajli i ne znam &scaron;ta&hellip;</p>\n<p>AK: Slažem se i razumem &scaron;ta mi priča&scaron;. Vidim da si razmi&scaron;ljao o tome&hellip;</p>\n<p>B: Brate, ja pričam da mi je ovo hobi da bih za&scaron;titio sebe od prevelikih očekivanja, a zapravo ne bih voleo ni&scaron;ta drugo da radim osim ovoga. Samo kad bih mogao da snimam spotove za muziku koja mi se sviđa, koja me uradi na neki način, to je jedino &scaron;to mi treba u životu. Radim u onoj jebenoj radionici, samo gledam kad ću sebi &scaron;aku da odsečem na cirkular, jer samo mislim o kadrovima i kako bi neka scena i&scaron;la uz neku pesmu&hellip; Ovaj spot sam snimao sa tako dobrom kamerom da sam mogao da vidim koja je razlika kad ima&scaron; opremu i kad zna&scaron; da je koristi&scaron;&hellip; bukvalno sam se igrao i provaljivao fore i ispalo je ovako&hellip; Moram da se iscimam da nabavim svoju kameru i stalak, o tome ma&scaron;tam neprestano&hellip; Zna&scaron; kakve bih spotove snimao!</p>\n<p>&emsp;&emsp;I kao da je umoran od tih želja, glava mu je pala na jastuk i kroz pet minuta već je hrkao. Pogledao sam spot jo&scaron; jednom, zavr&scaron;io cigaru i odvezao se kući. Noć je bila vedra, a ja sam neprestano priviđao ki&scaron;obrane kako se dižu i spu&scaron;taju duž ceste.</p>', 'brisel-blista', '2017-06-22 06:39:50', 'rs', NULL, 1),
(68, 1, 'Pitanja povodom spota za pesmu "Ne pitaj se"', '<p>Iskoristio sam Bluzdogovu posetu kao povod za razgovor o novom spotu beogradskog benda Dingospo Dalija koji je nedavno objavljen. Odbio je da mu skuvam kafu, već je iz ranca izvadio dva staklenca. Nije hteo ni da pu&scaron;i sa mnom, ali meni su tanki kolutovi dima igrali pod svetlom sa ekrana lap-topa. Razgovarali smo o Beogradu, o spotu, o životu&hellip;</p>\n<p>&emsp;&emsp;Andrea Kane: Poslednji put kad smo ovako pričali, rekao si mi da očekuje&scaron; da se ne&scaron;to pokrene. Evo, sada si režirao i snimio drugi spot za &bdquo;Dingospo Dali&ldquo;, pa me zanima da li je to ono na &scaron;ta si mislio?<br />Bluzdog: Pa i jeste i nije. Nisam imao neku jasnu viziju kako bi to trebalo da izgleda, ipak sam ja i dalje nov u ovoj priči, ali kada su me ovi iz benda pozvali i rekli mi da imaju dobru kameru za snimanje i da hoće da rade novi spot, pristao sam bez razmi&scaron;ljanja. Ovako ne&scaron;to sam priželjkivao&hellip; Bilo je stvarno jako zabavno smi&scaron;ljati sve scene i pripremati se za snimanje, ali je sam proces snimanja, a pogotovo montaža, bio jako naporan. I u fizičkom i u psihičkom smislu.<br />AK: Kako to misli&scaron;?<br />B: Pa sve smo snimali u Beogradu, a ja tamo nisam bio godinama. I ne znam vi&scaron;e toliko dobro lokacije. Ono &scaron;to sam ja imao u glavi, moja sećanja na neke &scaron;tekove i prizore iz Beograda, to je sad sve drugačije. Oti&scaron;ao sam u jednu ulicu gde je trebalo da snimimo &scaron;etnju, sećao sam se tog stepeni&scaron;ta sa velikim, ba&scaron; onako debelim gelenderima, a sad je tamo neki solarijum. I sve &scaron;lja&scaron;ti, potpuno je drugi fazon. A tako sam se ispalio za jo&scaron; jedno desetak lokacija, pa smo većinu scena snimali po nasumičnim lokacijama koje smo nalazili usput. To mi je bio prvi problem, jer u mojoj glavi je sve to bilo mnogo bolje. Malo mi je bilo čudno, jer sam ja imao viziju kako spot mora da izgleda, a onda dođe&scaron; na scenu i vidi&scaron; da nema pola stvari koje bi učinile spot i priču u njemu onim &scaron;to jeste.</p>', 'pitanja-povodom-spota-za-pesmu-ne-pitaj-se', '2017-06-22 06:43:37', 'rs', NULL, 1),
(69, 23, 'Sa Bluzdogom o spotu za pesmu "Ona"', '<p>Poslužio me je prezaslađenom kafom i nekim matorim keksom uz izvinjenje da nije nikakav domaćin. Ali ipak me je primorao da sednem na krevet, a on je prekrstio noge na brodskom podu svoje sobe. Na zidu suprotnom od prozora, razvukao je platno i pustio spot za pesmu &bdquo;Ona&ldquo;, beogradskog sastava Dingospo Dali. Na svetlu projektora igrala je sitna pra&scaron;ina u vazduhu, a po praznoj sobi se presijavala plava boja sa platna.</p>\r\n<p>&emsp;&emsp;Počeli smo priču pitanjem kako je počeo sa snimanjem spotova. Otpio je malo piva iz zelene fla&scaron;e i zagledao se u prazninu pred njegovim nogama. Kada sam već pomislio da me nije čuo, progovorio je.</p>\r\n<p>&emsp;&emsp;Bluzdog: Prvi spot koji sam snimao bio je za Depilaciju mozga&hellip; Oni su se raspali kasnije&hellip; nikada nismo uradili taj spot, ali imali smo dosta materijala&hellip; Ne&scaron;to od tih snimaka sam iskoristio i za ovaj spot. Ovo je</p>\r\n<p><iframe src="//www.youtube.com/embed/cC9jjmtxbFs" width="560" height="314" allowfullscreen="allowfullscreen"></iframe>Vlade [pokazuje fla&scaron;om na platno], gitarista Depilacije. Tako je počelo&hellip;</p>\r\n<p>&emsp;&emsp;Govori polako, sa dugim pauzama. Verovatno je umoran, pokupio sam ga čim je zavr&scaron;io drugu smenu u radionici.</p>\r\n<p>&emsp;&emsp;Andrea Kane: Znači za ovaj spot si koristio i neki stari materijal?<br />Bluzdog: Samo sam to i koristio. Ovde nema nijednog kadra snimljenog za ovaj spot, za ovu pemsu&hellip; To su sve snimci koje sam već imao na svom računaru, brdo materijala koje imam odavno&hellip; Ne znam da li sam ti pričao kako je uop&scaron;te do&scaron;lo do ovoga&hellip;<br />AK: Ne, ispričaj.<br />B: Pa razgovarao sam jednom sa Sandrom [pevačica Dingospo Dalija] i ona mi se požalila kako danas mora&scaron; da ima&scaron; spot na &bdquo;Tjubu&ldquo; kako bi iko čuo za tebe, a oni nemaju para da snime spot. Ja sam rek&rsquo;o, samo mi dajte pesmu, napraviću vam ja spot. Poslali su mi ovu stvar i &bdquo;Pelin&ldquo;. Ova mi se svidela mnogo vi&scaron;e i preslu&scaron;avao sam je danima&hellip; i tako sam krenuo da kopam po mojoj arhivi snimaka. Imam preko sto gigabajta raznih snimaka, od kada sam klinac pa do danas&hellip; Vodila me atmosfera pesme, jednostavno sam pratio to &scaron;to osećam dok slu&scaron;am&hellip; i tako sam izvukao sve one snimke koji idu uz muziku i krenuo sam da montiram&hellip;<br />AK: Znači svi ovi snimci su zapravo snimljeni sa nekim drugim razlogom?<br />Bluzdog: Da, da, svi&hellip; Ovi kadrovi sa Vladom, to je sve trebalo da bude u spotu za Depilaciju&hellip; Ne znam, ovi kadrovi iz ruke, po ulici, to je sve snimano u Beogradu kad sam studirao&hellip; mislim, kao sam studirao, zapravo sam i&scaron;ao okolo i snimao gluposti, ali eto, imalo je koristi i od toga&hellip; Mnogo mi se sviđa ova scena sa stvarima na vetru [01:28], &scaron;teta &scaron;to se ne vidi bolje. Jebi ga, tad sam snimao nekom mojom kamericom&hellip; Ove scene iz galerije, to nemam pojma odakle mi! Možda i nisu moje, ali na&scaron;ao sam ih u nekom folderu i svidele su mi se&hellip; Imam toliko snimaka da mi se čini da sam živeo hiljadu života&hellip;<br />AK: Da li je to razlog za&scaron;to snima&scaron;?<br />B: Kako to misli&scaron;?<br />AK: U smislu da li ima&scaron; sve te snimke kao dokaz o svom postojanju, da li sve ovo radi&scaron; da bi sačuvao neke trenutke u životu?<br />B: Ne znam&hellip; nisam to tako gledao&hellip; Moj ćale je radio na lokalnoj televiziji i uvek smo imali kameru u kući. I ja sam voleo da se igram sa njom. Mislim, prvo sam se igrao sa kasetama, kamere su bile prevelike, ono VHS fazon&hellip; Posle kad je televizija prsla, ćale je dobio otpremninu u nekoj opremi, i bila je tu jedna manja kamera na one male kasetice&hellip; Tad sam krenuo da se igram. Svuda sam i&scaron;ao sa tom kamerom. Neprestano sam snimao, bukvalno sve. Kako jedem, kako idem u &scaron;kolu, kako se kerovi jure po ulici, kako noću kom&scaron;ija gleda te-ve i vide mu se noge na stolu&hellip; kažem ti &ndash; sve! E, ali ovo je fascinantno! Ovaj deo spota [02:20-02:42] sam snimio u bioskopu kad sam volontirao na festivalu kratkog filma. Bio je to neki studentski film koji mi se jako svideo kad sam ga gledao prvi put, pa sam u bioskopu uzeo kamericu da se igram, pravio sam film od filma, zumirao sam i mrdao kameru preko njega. I kad sam pregledao materijal za spot, znao sam da ću ubaciti taj deo, i to ba&scaron; ovaj deo sa biciklistom. Jednostavno, kao da sam ga snimao za taj spot. I neće&scaron; verovati &ndash; ovaj lik na biciklu, to je bubnjar benda!<br />AK: Bubnjar Dingospo Dalija?<br />B: Da, brate, da ne poveruje&scaron;! A ja nisam imao pojma, snimio sam to pre ne znam koliko i eto, iskopao sam ba&scaron; taj snimak, ba&scaron; tu deonicu sam stavio, ba&scaron; za njihov spot&hellip; a nikada pre toga nisam upoznao lika, niti sam znao za bend. Ne može&scaron; da veruje&scaron;! Zamisli tek njih kad su videli spot! Nisu verovali&hellip;</p>', 'sa-bluzdogom-o-spotu-za-pesmu-ona-1-1', '2017-08-22 21:38:43', NULL, NULL, 1),
(71, 21, 'Where does it come from', '<div>\n<h2 id="mcetoc_1bmfjs2sj0">Where does it come from?</h2>\n<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.</p>\n<p>The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>\n</div>', '', '2017-08-01 19:21:08', 'rs', NULL, 1),
(72, 23, 'Ovo je proba sa novim autorom', '<p>asdasdasdasdasdas</p>', '', '2017-08-02 21:15:41', 'rs', 0, 1),
(73, 21, 'Ovo je najnoviji Članak', '<p>t is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).</p>', '', '2017-08-05 13:38:52', 'rs', NULL, 0),
(74, 23, 'Deseti članak u kategoriji Književnost', '<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.</p>\r\n<p>The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>', 'deseti-clanak-u-kategoriji-knjizevnost', '2017-08-30 18:49:00', NULL, NULL, 1),
(75, 18, 'Ovo je jedanaesti članak u Literatues', '<p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).</p>\n<p><br />Where does it come from?<br />Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.</p>\n<p>The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>\n<p>Where can I get some?<br />There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.</p>\n<p><br />5<br /> paragraphs<br /> words<br /> bytes<br /> lists<br /> Start with ''Lorem<br />ipsum dolor sit amet...''<br />Generate Lorem Ipsum</p>', '', '2017-08-05 19:35:53', 'rs', NULL, 1),
(76, 23, 'Ovo je dvanaesti članak na  knjizevnosti', '<p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ''Content here, content here'', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ''lorem ipsum'' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).<br />Where does it come from?<br />Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.</p>\r\n<p>The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>\r\n<p>Where can I get some?<br />There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.</p>\r\n<p><br />5<br /> paragraphs<br /> words<br /> bytes<br /> lists<br /> Start with ''Lorem<br />ipsum dolor sit amet...''<br />Generate Lorem Ipsum</p>', 'ovo-je-dvanaesti-clanak-na-knjizevnosti-1', '2017-08-30 18:47:31', NULL, NULL, 1),
(78, 23, 'Ovo je neki neformatiran članak', '<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.</p>\r\n<p>&nbsp;</p>', 'ovo-je-neki-neformatiran-clanak', '2017-08-30 18:46:03', NULL, NULL, 0),
(79, 16, 'Ovo je proba članka sa novim  post mehanizmom', '', 'ovo-je-proba-clanka-sa-novim-post-mehanizmom', '2017-08-09 21:09:15', NULL, 0, 1),
(80, 20, 'Sledeći članak sa novim postom', '', 'sledeci-clanak-sa-novim-postom', '2017-08-09 21:17:46', NULL, 0, 1),
(81, 16, 'Ovo je proba ispravljenog jq posta', '<p>Note: If you specify a Blob as the data to append to the FormData object, the filename that will be reported to the server in the "Content-Disposition" header used to vary from browser to browser.</p>', 'ovo-je-proba-ispravljenog-jq-posta', '2017-08-10 17:52:54', NULL, 0, 1),
(82, 1, 'Ovo je Lorem Ipsum tekst ', '<p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."</p>\r\n<h3>&nbsp;</h3>', 'ovo-je-lorem-ipsum-tekst', '2017-08-10 17:55:31', NULL, 0, 1),
(83, 16, 'Lorem Ipsum dolores Suse', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ullamcorper ex at leo blandit laoreet. Aenean porta, elit id lobortis malesuada, lectus dolor ornare diam, quis ultricies ex mauris in lacus. Donec leo sem, maximus in fermentum sed, posuere vitae mi. Sed laoreet tellus nibh, eget cursus dui fermentum sit amet. Fusce porttitor mi sed ipsum aliquam suscipit. Morbi volutpat porta condimentum. Phasellus pulvinar porta augue, et dapibus nunc ullamcorper eget. Maecenas nunc justo, auctor vel accumsan ac, tincidunt a magna. Sed eu pellentesque metus. Quisque at auctor lorem. Duis ut nulla lacinia, semper enim nec, luctus mauris.</p>\r\n<p>Nam ac lacus id sapien dapibus ultricies ac dapibus neque. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam mattis orci quis sapien venenatis semper at in elit. Nam porttitor odio sit amet lectus convallis blandit. Nunc pulvinar quam sed ipsum tristique, quis sagittis quam faucibus. Mauris mattis nulla et turpis venenatis sagittis. Cras luctus hendrerit quam a varius. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vivamus et ipsum nec mauris luctus consectetur a tempus velit.</p>\r\n<p>Pellentesque rhoncus lorem vel augue gravida molestie. Fusce imperdiet congue sapien id tincidunt. Fusce tincidunt odio arcu. Sed porta, nisi sit amet euismod ornare, ipsum quam sollicitudin metus, vitae ornare augue sem eget enim. Maecenas pretium ultricies libero, vitae faucibus orci vestibulum sagittis. Nam eleifend ligula a metus pulvinar, id tempor purus luctus. Mauris eu ante tempor, ultrices eros vitae, vulputate nulla. Nunc porta eros vehicula feugiat aliquam. Curabitur sed ullamcorper sem, sit amet venenatis purus.</p>\r\n<p>Aenean placerat mollis maximus. Vivamus mattis dolor at magna congue, tristique elementum turpis viverra. Mauris non aliquam mi, feugiat vehicula nisl. Pellentesque at metus eu turpis sodales volutpat. Quisque imperdiet ligula quis diam consequat gravida. In molestie ac eros sit amet consequat. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Maecenas justo velit, molestie in congue dignissim, tempus commodo sapien. Cras diam neque, hendrerit eget vestibulum quis, volutpat eget massa. Quisque non vehicula risus, ut tempor felis. Nulla ex dolor, tristique nec suscipit sed, porttitor in elit. Maecenas finibus, nisl eget fringilla pellentesque, magna sem malesuada tellus, condimentum luctus massa mi a metus. Fusce tincidunt risus a risus malesuada, quis vulputate ligula aliquet. Quisque lobortis urna id magna interdum tincidunt. Maecenas malesuada hendrerit nulla.</p>\r\n<p>Cras vitae tempus urna, et elementum ante. Pellentesque id porttitor magna. Etiam libero sem, sagittis cursus egestas et, bibendum vitae mi. Sed maximus elit dolor. Etiam hendrerit sapien eget arcu interdum pulvinar. Integer sed ligula a sem luctus facilisis eu eu quam. Etiam massa tortor, dapibus at luctus tincidunt, luctus eu dui. Pellentesque justo erat, pharetra eget convallis auctor, rutrum in nibh. Fusce orci risus, condimentum sit amet metus sed, maximus viverra metus. Quisque dolor orci, elementum ut bibendum eget, tempus eu ipsum. Nunc quis congue tortor.</p>', 'lorem-ipsum-dolores-suse', '2017-08-12 15:36:50', NULL, 0, 1),
(84, 1, 'Proba postovanja članka bez featured image', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ullamcorper ex at leo blandit laoreet. Aenean porta, elit id lobortis malesuada, lectus dolor ornare diam, quis ultricies ex mauris in lacus. Donec leo sem, maximus in fermentum sed, posuere vitae mi. Sed laoreet tellus nibh, eget cursus dui fermentum sit amet. Fusce porttitor mi sed ipsum aliquam suscipit. Morbi volutpat porta condimentum. Phasellus pulvinar porta augue, et dapibus nunc ullamcorper eget. Maecenas nunc justo, auctor vel accumsan ac, tincidunt a magna. Sed eu pellentesque metus. Quisque at auctor lorem. Duis ut nulla lacinia, semper enim nec, luctus mauris.</p>\r\n<p>Nam ac lacus id sapien dapibus ultricies ac dapibus neque. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam mattis orci quis sapien venenatis semper at in elit. Nam porttitor odio sit amet lectus convallis blandit. Nunc pulvinar quam sed ipsum tristique, quis sagittis quam faucibus. Mauris mattis nulla et turpis venenatis sagittis. Cras luctus hendrerit quam a varius. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vivamus et ipsum nec mauris luctus consectetur a tempus velit.</p>\r\n<p>Pellentesque rhoncus lorem vel augue gravida molestie. Fusce imperdiet congue sapien id tincidunt. Fusce tincidunt odio arcu. Sed porta, nisi sit amet euismod ornare, ipsum quam sollicitudin metus, vitae ornare augue sem eget enim. Maecenas pretium ultricies libero, vitae faucibus orci vestibulum sagittis. Nam eleifend ligula a metus pulvinar, id tempor purus luctus. Mauris eu ante tempor, ultrices eros vitae, vulputate nulla. Nunc porta eros vehicula feugiat aliquam. Curabitur sed ullamcorper sem, sit amet venenatis purus.</p>\r\n<p>Aenean placerat mollis maximus. Vivamus mattis dolor at magna congue, tristique elementum turpis viverra. Mauris non aliquam mi, feugiat vehicula nisl. Pellentesque at metus eu turpis sodales volutpat. Quisque imperdiet ligula quis diam consequat gravida. In molestie ac eros sit amet consequat. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Maecenas justo velit, molestie in congue dignissim, tempus commodo sapien. Cras diam neque, hendrerit eget vestibulum quis, volutpat eget massa. Quisque non vehicula risus, ut tempor felis. Nulla ex dolor, tristique nec suscipit sed, porttitor in elit. Maecenas finibus, nisl eget fringilla pellentesque, magna sem malesuada tellus, condimentum luctus massa mi a metus. Fusce tincidunt risus a risus malesuada, quis vulputate ligula aliquet. Quisque lobortis urna id magna interdum tincidunt. Maecenas malesuada hendrerit nulla.</p>\r\n<p>Cras vitae tempus urna, et elementum ante. Pellentesque id porttitor magna. Etiam libero sem, sagittis cursus egestas et, bibendum vitae mi. Sed maximus elit dolor. Etiam hendrerit sapien eget arcu interdum pulvinar. Integer sed ligula a sem luctus facilisis eu eu quam. Etiam massa tortor, dapibus at luctus tincidunt, luctus eu dui. Pellentesque justo erat, pharetra eget convallis auctor, rutrum in nibh. Fusce orci risus, condimentum sit amet metus sed, maximus viverra metus. Quisque dolor orci, elementum ut bibendum eget, tempus eu ipsum. Nunc quis congue tortor.</p>', 'proba-postovanja-clanka-bez-featured-image', '2017-08-12 15:38:49', NULL, NULL, 1),
(85, 23, 'Proba uploada članka sa featured im g preko 2mb', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a magna sit amet leo eleifend sodales a nec nisl. Duis vel est gravida, ornare arcu a, ullamcorper urna. In condimentum mollis vehicula. Nam urna dolor, varius et mollis non, commodo nec dolor. Donec at sapien id ipsum pretium lacinia sit amet eget risus. Sed non mattis mi. Morbi semper massa non luctus ornare.</p>\r\n<p>Donec quis nisi tortor. Vestibulum imperdiet quam in dolor cursus, vel eleifend augue aliquet. Nullam euismod, dui at consequat volutpat, risus magna consequat enim, a interdum neque turpis scelerisque odio. Nam elit neque, dignissim eget aliquet quis, blandit sit amet orci. Mauris vitae augue vestibulum, varius risus non, luctus felis. Donec maximus lacus nisl, ut pellentesque orci scelerisque porta. Vestibulum eget euismod ligula. Vestibulum a porttitor elit, in venenatis sapien. Nunc pharetra libero nulla, sit amet condimentum orci convallis non. Integer in iaculis enim.</p>\r\n<p>Nulla vestibulum finibus urna a porta. Etiam tincidunt congue tellus at finibus. Curabitur hendrerit lorem in venenatis semper. Sed nec scelerisque augue. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean hendrerit, dui sed interdum ornare, enim lectus ultrices nunc, eu feugiat erat risus vel arcu. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Ut eu mollis dui. Vestibulum vel eros interdum, efficitur mi vel, mollis augue. Proin suscipit eros in aliquam gravida. Morbi tincidunt porta dolor sed auctor.</p>\r\n<p>Vestibulum eget congue lorem. Integer vestibulum enim nec luctus fermentum. Aenean sed nisi non sem bibendum sagittis. Sed vulputate dolor nisl, non porttitor nisi laoreet vitae. Morbi orci lectus, tincidunt a tempus eget, luctus non purus. Aliquam venenatis elementum nisi ac scelerisque. Cras accumsan lacus nibh. Nullam dapibus convallis est, at varius odio mattis a. Duis iaculis ornare erat, id hendrerit sem gravida nec.</p>\r\n<p>Donec vel orci sit amet quam volutpat accumsan vitae sit amet velit. Phasellus consectetur risus vel ex porta convallis. Aenean laoreet elementum pulvinar. Nunc ultrices massa eu magna blandit sodales. In hac habitasse platea dictumst. Nunc tempus cursus eros. Donec luctus ipsum sapien, et ultrices nunc ullamcorper sed. Vivamus tincidunt suscipit lacus sit amet scelerisque. Sed fermentum, nisl id euismod eleifend, purus mauris ultrices diam, ut dictum nisi ligula quis sapien.</p>\r\n<p>Vestibulum at ex vel turpis fringilla lacinia. Curabitur nec quam ultricies arcu fermentum iaculis. Integer felis velit, pulvinar ac dignissim sed, tempus at massa. Cras elementum ullamcorper augue id ullamcorper. Nullam euismod aliquet lorem id interdum. Phasellus vitae dictum neque. Vivamus iaculis ante quis turpis fermentum mollis. Curabitur rhoncus neque arcu. Suspendisse nec nunc libero. Aliquam maximus diam quis scelerisque ultricies. Mauris eget scelerisque quam. Nam vel varius lectus.</p>\r\n<p>Sed efficitur massa ut enim tristique, sit amet venenatis magna convallis. Phasellus lacus augue, facilisis in lacinia vitae, venenatis vel magna. Vivamus neque ex, aliquet non urna vitae, molestie aliquam tortor. Donec eu arcu porta, sagittis lorem ut, consequat odio. Ut mi sem, mattis in congue ac, varius non eros. Mauris fermentum ex nunc, sed ullamcorper nisl luctus ut. Curabitur scelerisque lobortis dolor. Sed quam arcu, hendrerit eget tincidunt ullamcorper, scelerisque nec arcu.</p>\r\n<p>In pretium risus sed venenatis porttitor. Pellentesque maximus mollis nulla sit amet mollis. Aliquam dapibus blandit purus vel porta. Aliquam sagittis et ipsum nec sollicitudin. Pellentesque lacinia urna lectus. Aenean posuere, sem non sollicitudin posuere, arcu tellus fringilla magna, eget luctus ante libero gravida sapien. Vivamus tellus nulla, convallis vitae nibh at, viverra convallis ante. Suspendisse potenti. Vivamus quis mattis ante, sed condimentum elit. Curabitur maximus massa facilisis.</p>', 'proba-uploada-clanka-sa-featured-im-g-preko-2mb', '2017-08-12 15:46:23', NULL, NULL, 1),
(86, 20, 'Proba članka sa featured img i reset iste', '<p>Nulla vestibulum finibus urna a porta. Etiam tincidunt congue tellus at finibus. Curabitur hendrerit lorem in venenatis semper. Sed nec scelerisque augue. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean hendrerit, dui sed interdum ornare, enim lectus ultrices nunc, eu feugiat erat risus vel arcu. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Ut eu mollis dui. Vestibulum vel eros interdum, efficitur mi vel, mollis augue. Proin suscipit eros in aliquam gravida. Morbi tincidunt porta dolor sed auctor.</p>\r\n<p>Vestibulum eget congue lorem. Integer vestibulum enim nec luctus fermentum. Aenean sed nisi non sem bibendum sagittis. Sed vulputate dolor nisl, non porttitor nisi laoreet vitae. Morbi orci lectus, tincidunt a tempus eget, luctus non purus. Aliquam venenatis elementum nisi ac scelerisque. Cras accumsan lacus nibh. Nullam dapibus convallis est, at varius odio mattis a. Duis iaculis ornare erat, id hendrerit sem gravida nec.</p>\r\n<p>Donec vel orci sit amet quam volutpat accumsan vitae sit amet velit. Phasellus consectetur risus vel ex porta convallis. Aenean laoreet elementum pulvinar. Nunc ultrices massa eu magna blandit sodales. In hac habitasse platea dictumst. Nunc tempus cursus eros. Donec luctus ipsum sapien, et ultrices nunc ullamcorper sed. Vivamus tincidunt suscipit lacus sit amet scelerisque. Sed fermentum, nisl id euismod eleifend, purus mauris ultrices diam, ut dictum nisi ligula quis sapien.</p>\r\n<p>Vestibulum at ex vel turpis fringilla lacinia. Curabitur nec quam ultricies arcu fermentum iaculis. Integer felis velit, pulvinar ac dignissim sed, tempus at massa. Cras elementum ullamcorper augue id ullamcorper. Nullam euismod aliquet lorem id interdum. Phasellus vitae dictum neque. Vivamus iaculis ante quis turpis fermentum mollis. Curabitur rhoncus neque arcu. Suspendisse nec nunc libero. Aliquam maximus diam quis scelerisque ultricies. Mauris eget scelerisque quam. Nam vel varius lectus.</p>', 'proba-clanka-sa-featured-img-i-reset-iste', '2017-08-12 16:31:01', NULL, 0, 1),
(87, 17, 'Ovo je proba članka sa prosledjenim statusom 1', '<p>Donec quis nisi tortor. Vestibulum imperdiet quam in dolor cursus, vel eleifend augue aliquet. Nullam euismod, dui at consequat volutpat, risus magna consequat enim, a interdum neque turpis scelerisque odio. Nam elit neque, dignissim eget aliquet quis, blandit sit amet orci. Mauris vitae augue vestibulum, varius risus non, luctus felis. Donec maximus lacus nisl, ut pellentesque orci scelerisque porta. Vestibulum eget euismod ligula. Vestibulum a porttitor elit, in venenatis sapien. Nunc pharetra libero nulla, sit amet condimentum orci convallis non. Integer in iaculis enim.</p>', 'ovo-je-proba-clanka-sa-prosledjenim-statusom-1', '2017-08-12 16:44:43', NULL, NULL, 1),
(89, 20, 'Druga proba nacrta članka', '<p>Donec quis nisi tortor. Vestibulum imperdiet quam in dolor cursus, vel eleifend augue aliquet. Nullam euismod, dui at consequat volutpat, risus magna consequat enim, a interdum neque turpis scelerisque odio. Nam elit neque, dignissim eget aliquet quis, blandit sit amet orci. Mauris vitae augue vestibulum, varius risus non, luctus felis. Donec maximus lacus nisl, ut pellentesque orci scelerisque porta. Vestibulum eget euismod ligula. Vestibulum a porttitor elit, in venenatis sapien. Nunc pharetra libero nulla, sit amet condimentum orci convallis non. Integer in iaculis enim.</p>', 'druga-proba-nacrta-clanka', '2017-08-12 16:59:17', NULL, NULL, 1),
(90, 23, 'Teća proba draftovanja članka', '<p>Donec quis nisi tortor. Vestibulum imperdiet quam in dolor cursus, vel eleifend augue aliquet. Nullam euismod, dui at consequat volutpat, risus magna consequat enim, a interdum neque turpis scelerisque odio. Nam elit neque, dignissim eget aliquet quis, blandit sit amet orci. Mauris vitae augue vestibulum, varius risus non, luctus felis. Donec maximus lacus nisl, ut pellentesque orci scelerisque porta. Vestibulum eget euismod ligula. Vestibulum a porttitor elit, in venenatis sapien. Nunc pharetra libero nulla, sit amet condimentum orci convallis non. Integer in iaculis enim.</p>', 'teca-proba-draftovanja-clanka-1', '2017-08-21 20:17:06', NULL, 0, 1),
(91, 21, 'Proba članka sa statusom 1', '<p>Donec quis nisi tortor. Vestibulum imperdiet quam in dolor cursus, vel eleifend augue aliquet. Nullam euismod, dui at consequat volutpat, risus magna consequat enim, a interdum neque turpis scelerisque odio. Nam elit neque, dignissim eget aliquet quis, blandit sit amet orci. Mauris vitae augue vestibulum, varius risus non, luctus felis. Donec maximus lacus nisl, ut pellentesque orci scelerisque porta. Vestibulum eget euismod ligula. Vestibulum a porttitor elit, in venenatis sapien. Nunc pharetra libero nulla, sit amet condimentum orci convallis non. Integer in iaculis enim.</p>', 'proba-clanka-sa-statusom-1', '2017-08-12 17:02:31', NULL, 0, 1);
INSERT INTO `article` (`id`, `author_id`, `title`, `content`, `slug`, `post_date`, `lang`, `featured_img_id`, `active`) VALUES
(92, 20, 'Proba nacrta članka br.4 sa featured img', '<p>Donec quis nisi tortor. Vestibulum imperdiet quam in dolor cursus, vel eleifend augue aliquet. Nullam euismod, dui at consequat volutpat, risus magna consequat enim, a interdum neque turpis scelerisque odio. Nam elit neque, dignissim eget aliquet quis, blandit sit amet orci. Mauris vitae augue vestibulum, varius risus non, luctus felis. Donec maximus lacus nisl, ut pellentesque orci scelerisque porta. Vestibulum eget euismod ligula. Vestibulum a porttitor elit, in venenatis sapien. Nunc pharetra libero nulla, sit amet condimentum orci convallis non. Integer in iaculis enim.</p>', 'proba-nacrta-clanka-br4-sa-featured-img', '2017-08-12 17:12:55', NULL, 0, 1),
(93, 22, 'Proba čuvanja nacrta sa porukom o uspešnosti', '<p>active</p>', 'proba-cuvanja-nacrta-sa-porukom-o-uspesnosti', '2017-08-12 17:18:41', NULL, NULL, 1),
(94, 22, 'Proba čuvanja nacrta sa porukom o uspešnosti', '<p>active</p>', 'proba-cuvanja-nacrta-sa-porukom-o-uspesnosti-1', '2017-08-12 17:18:47', NULL, NULL, 0),
(95, 20, 'Za i protiv marihuane', '<p style="margin: 0px 0px 15px; padding: 0px; text-align: justify; color: #000000; font-family: ''Open Sans'', Arial, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; text-decoration-style: initial; text-decoration-color: initial;">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec fermentum a augue id rutrum. Quisque pretium ante nisl, at interdum sapien tempus non. Donec accumsan urna arcu, sit amet varius massa tincidunt at. Suspendisse quis lacinia metus. In pretium finibus massa, quis gravida tellus condimentum et. Mauris suscipit tincidunt leo ut condimentum. Nullam lorem nibh, molestie in lacinia eu, tristique et neque. Praesent sem metus, feugiat sed rhoncus ac, faucibus quis enim. Proin vitae velit id metus euismod commodo. Aliquam dignissim tortor at enim tempus, vitae placerat ante dapibus. Aliquam eget ligula elit. Nunc luctus nibh ipsum, ac auctor dui blandit eget. Aliquam lacinia, mi nec pulvinar euismod, dolor risus posuere libero, eu ultrices lorem lorem non lacus. In vulputate, nisl in bibendum cursus, turpis ante gravida nunc, ac semper mi magna a risus. Fusce elementum ut nisi sed viverra.</p>\r\n<p style="margin: 0px 0px 15px; padding: 0px; text-align: justify; color: #000000; font-family: ''Open Sans'', Arial, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; text-decoration-style: initial; text-decoration-color: initial;">Ut efficitur odio sem, eget rutrum justo pellentesque eu. Aliquam id sem gravida, eleifend eros malesuada, tempor urna. Nullam varius est quis ornare fermentum. In pharetra augue sed sapien sagittis aliquet. Maecenas sollicitudin libero vitae neque accumsan, nec tincidunt nibh maximus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce at enim in dui interdum malesuada eu quis diam. Curabitur a nunc dictum, tempus quam nec, elementum leo. Cras maximus, quam in mollis malesuada, magna libero ullamcorper urna, sed cursus lacus arcu sit amet justo. Duis vitae mauris sed tortor gravida consequat eu in ex.</p>\r\n<p style="margin: 0px 0px 15px; padding: 0px; text-align: justify; color: #000000; font-family: ''Open Sans'', Arial, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; text-decoration-style: initial; text-decoration-color: initial;">Suspendisse pharetra bibendum ipsum, ac aliquet felis maximus a. Etiam in purus ut ex convallis semper. Mauris pretium arcu quis dui tincidunt, sed consequat eros dapibus. In in erat vel neque tempor consequat in at erat. Maecenas nec ornare mi. Sed vel porta nunc. Maecenas sodales, justo eu fermentum sollicitudin, mi purus scelerisque orci, a finibus est dolor in tortor. Donec mattis arcu id lectus placerat vehicula. Praesent a diam risus.</p>\r\n<p style="margin: 0px 0px 15px; padding: 0px; text-align: justify; color: #000000; font-family: ''Open Sans'', Arial, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; text-decoration-style: initial; text-decoration-color: initial;">Sed ut malesuada mauris, vel vulputate metus. Praesent sagittis sit amet ex ut pellentesque. Nam nec molestie odio, eu consectetur urna. Fusce quis ex a leo sagittis elementum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vestibulum faucibus tortor ac elit tempor sollicitudin eget sit amet augue. Nullam dignissim diam lacus, a bibendum turpis dapibus efficitur. Nam tellus ligula, ultrices at feugiat sit amet, blandit eu libero.</p>\r\n<p>&nbsp;</p>', 'za-i-protiv-marihuane-1', '2017-08-23 21:28:56', NULL, NULL, 1),
(96, 20, 'Za i protiv marihuane', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec fermentum a augue id rutrum. Quisque pretium ante nisl, at interdum sapien tempus non. Donec accumsan urna arcu, sit amet varius massa tincidunt at. Suspendisse quis lacinia metus. In pretium finibus massa, quis gravida tellus condimentum et. Mauris suscipit tincidunt leo ut condimentum. Nullam lorem nibh, molestie in lacinia eu, tristique et neque. Praesent sem metus, feugiat sed rhoncus ac, faucibus quis enim. Proin vitae velit id metus euismod commodo. Aliquam dignissim tortor at enim tempus, vitae placerat ante dapibus. Aliquam eget ligula elit. Nunc luctus nibh ipsum, ac auctor dui blandit eget. Aliquam lacinia, mi nec pulvinar euismod, dolor risus posuere libero, eu ultrices lorem lorem non lacus. In vulputate, nisl in bibendum cursus, turpis ante gravida nunc, ac semper mi magna a risus. Fusce elementum ut nisi sed viverra.</p>\r\n<p>Ut efficitur odio sem, eget rutrum justo pellentesque eu. Aliquam id sem gravida, eleifend eros malesuada, tempor urna. Nullam varius est quis ornare fermentum. In pharetra augue sed sapien sagittis aliquet. Maecenas sollicitudin libero vitae neque accumsan, nec tincidunt nibh maximus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce at enim in dui interdum malesuada eu quis diam. Curabitur a nunc dictum, tempus quam nec, elementum leo. Cras maximus, quam in mollis malesuada, magna libero ullamcorper urna, sed cursus lacus arcu sit amet justo. Duis vitae mauris sed tortor gravida consequat eu in ex.</p>\r\n<p>Suspendisse pharetra bibendum ipsum, ac aliquet felis maximus a. Etiam in purus ut ex convallis semper. Mauris pretium arcu quis dui tincidunt, sed consequat eros dapibus. In in erat vel neque tempor consequat in at erat. Maecenas nec ornare mi. Sed vel porta nunc. Maecenas sodales, justo eu fermentum sollicitudin, mi purus scelerisque orci, a finibus est dolor in tortor. Donec mattis arcu id lectus placerat vehicula. Praesent a diam risus.</p>\r\n<p>Sed ut malesuada mauris, vel vulputate metus. Praesent sagittis sit amet ex ut pellentesque. Nam nec molestie odio, eu consectetur urna. Fusce quis ex a leo sagittis elementum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vestibulum faucibus tortor ac elit tempor sollicitudin eget sit amet augue. Nullam dignissim diam lacus, a bibendum turpis dapibus efficitur. Nam tellus ligula, ultrices at feugiat sit amet, blandit eu libero.</p>\r\n<p>&nbsp;</p>', 'za-i-protiv-marihuane-2', '2017-08-23 21:29:24', NULL, NULL, 1),
(97, 20, 'Kako sam sistematki uništen od idiota', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec fermentum a augue id rutrum. Quisque pretium ante nisl, at interdum sapien tempus non. Donec accumsan urna arcu, sit amet varius massa tincidunt at. Suspendisse quis lacinia metus. In pretium finibus massa, quis gravida tellus condimentum et. Mauris suscipit tincidunt leo ut condimentum. Nullam lorem nibh, molestie in lacinia eu, tristique et neque. Praesent sem metus, feugiat sed rhoncus ac, faucibus quis enim. Proin vitae velit id metus euismod commodo. Aliquam dignissim tortor at enim tempus, vitae placerat ante dapibus. Aliquam eget ligula elit. Nunc luctus nibh ipsum, ac auctor dui blandit eget. Aliquam lacinia, mi nec pulvinar euismod, dolor risus posuere libero, eu ultrices lorem lorem non lacus. In vulputate, nisl in bibendum cursus, turpis ante gravida nunc, ac semper mi magna a risus. Fusce elementum ut nisi sed viverra.</p>\r\n<p>Ut efficitur odio sem, eget rutrum justo pellentesque eu. Aliquam id sem gravida, eleifend eros malesuada, tempor urna. Nullam varius est quis ornare fermentum. In pharetra augue sed sapien sagittis aliquet. Maecenas sollicitudin libero vitae neque accumsan, nec tincidunt nibh maximus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce at enim in dui interdum malesuada eu quis diam. Curabitur a nunc dictum, tempus quam nec, elementum leo. Cras maximus, quam in mollis malesuada, magna libero ullamcorper urna, sed cursus lacus arcu sit amet justo. Duis vitae mauris sed tortor gravida consequat eu in ex.</p>\r\n<p>Suspendisse pharetra bibendum ipsum, ac aliquet felis maximus a. Etiam in purus ut ex convallis semper. Mauris pretium arcu quis dui tincidunt, sed consequat eros dapibus. In in erat vel neque tempor consequat in at erat. Maecenas nec ornare mi. Sed vel porta nunc. Maecenas sodales, justo eu fermentum sollicitudin, mi purus scelerisque orci, a finibus est dolor in tortor. Donec mattis arcu id lectus placerat vehicula. Praesent a diam risus.</p>\r\n<p>Sed ut malesuada mauris, vel vulputate metus. Praesent sagittis sit amet ex ut pellentesque. Nam nec molestie odio, eu consectetur urna. Fusce quis ex a leo sagittis elementum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vestibulum faucibus tortor ac elit tempor sollicitudin eget sit amet augue. Nullam dignissim diam lacus, a bibendum turpis dapibus efficitur. Nam tellus ligula, ultrices at feugiat sit amet, blandit eu libero.</p>\r\n<p>&nbsp;</p>', 'kako-sam-sistematki-unisten-od-idiota', '2017-08-23 21:30:11', NULL, NULL, 1),
(98, 23, 'Proba postovanja sa zadatim datumom u prošlosti', '<p>Donec a orci hendrerit, interdum nisi quis, venenatis lectus. Ut a eleifend eros. Donec in vehicula ipsum. Vivamus ut arcu nec purus egestas lacinia cursus convallis lectus. Nam efficitur dui at ultrices tincidunt. Cras pretium enim vel lacus semper, ac fermentum odio vestibulum. Mauris tincidunt rutrum diam quis sollicitudin. Aenean condimentum arcu non interdum lacinia. Donec ut leo odio. Fusce malesuada aliquam suscipit. Praesent dignissim finibus lacus, et rhoncus ipsum venenatis vel. Donec posuere lorem vitae aliquam tincidunt.</p>', 'proba-postovanja-sa-zadatim-datumom-u-proslosti-1', '2017-09-08 20:47:34', NULL, NULL, 1),
(99, 16, 'Чланак објавлјен 23 августа', '<p>Suspendisse faucibus dolor vitae maximus congue. Donec mauris ante, lobortis non sodales ut, accumsan a diam. Proin id risus nisl. Aliquam fringilla, neque a consequat elementum, lacus libero congue erat, a egestas tellus dui ac justo. Maecenas ac justo elementum, vehicula arcu quis, imperdiet nisi. Aenean viverra bibendum ultricies. Aliquam semper interdum justo, sed semper turpis ullamcorper non. Ut eget blandit risus, vel accumsan tortor. Praesent lobortis sit amet enim vitae pharetra.</p>\r\n<p>Donec a orci hendrerit, interdum nisi quis, venenatis lectus. Ut a eleifend eros. Donec in vehicula ipsum. Vivamus ut arcu nec purus egestas lacinia cursus convallis lectus. Nam efficitur dui at ultrices tincidunt. Cras pretium enim vel lacus semper, ac fermentum odio vestibulum. Mauris tincidunt rutrum diam quis sollicitudin. Aenean condimentum arcu non interdum lacinia. Donec ut leo odio. Fusce malesuada aliquam suscipit. Praesent dignissim finibus lacus, et rhoncus ipsum venenatis vel. Donec posuere lorem vitae aliquam tincidunt.</p>\r\n<p>Etiam vitae ex sed tellus sagittis condimentum. Suspendisse id facilisis sem. Vestibulum placerat diam congue turpis gravida ornare. Pellentesque fermentum consequat neque, non semper magna pharetra sit amet. Nulla dignissim erat tristique dui fermentum, et laoreet eros ultrices. Donec vitae posuere libero. Nulla facilisi. Donec tempus elit a urna aliquam consectetur. Vestibulum quis ornare purus. Duis ac ligula ultricies sapien finibus consectetur. Donec metus urna, mollis quis dolor quis, porttitor finibus justo. Etiam venenatis ornare lacus euismod dictum. Nulla elementum et nisi nec eleifend.</p>\r\n<p>&nbsp;</p>', 'clanak-objavljen-23-avgusta', '2017-08-23 21:18:02', NULL, NULL, 1),
(100, 16, 'Ovaj članak je retroaktivno objavljen 10. avgusta', '<p><br />could I then repopulate the models select after running this method? &ndash; TotalNewbie Apr 10 ''14 at 12:52<br /> <br />I need to empty the contents on change and repopulate the dropdown &ndash; TotalNewbie Apr 10 ''14 at 12:52<br /> <br />no after I cleat the select box I aim to make an ajax call and then repopulate the dropdown &ndash; TotalNewbie Apr 10 ''14 at 12:55<br /> <br />thanks for your answer &ndash; TotalNewbie Apr 10 ''14 at 12:58<br /> <br />ok thankyou - I am having an issue even triggering the function onchange &ndash; Total</p>', 'ovaj-clanak-je-retroaktivno-objavljen-10-avgusta', '2017-08-10 15:39:17', NULL, NULL, 1);

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
(2, 1),
(3, 2),
(9, 1),
(9, 2),
(13, 1),
(13, 2),
(13, 4),
(14, 3),
(14, 4),
(15, 1),
(15, 3),
(15, 4),
(16, 3),
(16, 4),
(17, 3),
(17, 4),
(18, 1),
(18, 3),
(19, 2),
(20, 2),
(20, 4),
(62, 1),
(62, 3),
(62, 4),
(67, 3),
(67, 4),
(68, 4),
(69, 3),
(71, 2),
(71, 4),
(72, 4),
(73, 6),
(74, 3),
(75, 2),
(76, 3),
(78, 3),
(79, 5),
(80, 3),
(81, 3),
(82, 4),
(83, 4),
(84, 4),
(85, 4),
(86, 3),
(87, 5),
(89, 5),
(91, 5),
(92, 5),
(93, 5),
(94, 5),
(95, 3),
(96, 3),
(97, 3),
(98, 3),
(99, 3),
(100, 5);

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
(15, 1),
(15, 4),
(15, 5),
(15, 11),
(16, 2),
(16, 3),
(16, 6),
(16, 12),
(16, 15),
(17, 4),
(17, 6),
(17, 7),
(17, 11),
(18, 1),
(18, 2),
(18, 12),
(20, 6),
(20, 11),
(62, 2),
(69, 41),
(74, 47),
(78, 46),
(79, 1),
(79, 38),
(79, 40),
(80, 1),
(81, 1),
(82, 11),
(83, 1),
(83, 6),
(83, 11),
(84, 11),
(85, 11),
(86, 40),
(90, 6),
(90, 7),
(95, 42),
(95, 43),
(96, 42),
(96, 43),
(97, 42),
(97, 43),
(97, 44),
(98, 12),
(98, 48);

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
(1, 'Astor', 'Lajka', '1988-07-18', 'Milano', 'adnrea.kane@sobakaisti.org', NULL, NULL, NULL, NULL, 'andrea-kane'),
(16, 'Nemanja', 'Jelesijevic', NULL, 'Gornji Milanovac', 'jelles86@yahoo.co.yu', '', '', 'programer', 'avatar_path_value', 'nem-jel'),
(17, 'Jovan', 'P. Brajovic', '1986-05-11', 'Beograd', 'brajovic.jovan@gmail.com', '', '', 'kriticar', 'avatar_path_value', 'jovan-p-brajovic'),
(18, 'Igor', 'Repin', '1988-10-04', 'Valjevo', 'igor.repin@sobakaisti.org', '', '', 'slikar', 'avatar_path_value', 'igor-repin'),
(19, 'Astor', 'Lajka', '1985-05-04', 'Beograd', 'astor.lajka@sobakaisti.org', '', '', 'muzicar', 'avatar_path_value', 'astor-lajka'),
(20, 'Ivan', 'Sobakov', '1990-09-27', 'GM', 'ivan.sobakov@sobakaisti.org', '', '', 'pisac', 'avatar_path_value', 'ivan-sobakov'),
(21, 'Stefan', 'Pas', '1998-10-12', 'GM', 'stefan.pas@yahoo.co.uk', '', '', 'Slikar', 'avatar_path_value', 'stefan-pas'),
(22, 'Igor', 'Repin', '1990-12-25', 'Sicilija', 'igor.repin@gmail.com', '', '', 'Slikar', 'avatar_path_value', 'igor-repin'),
(23, 'Stefan', 'Stefanovic', '1990-10-28', 'Gm', 'stefan.stef@gmail.com', '', '', 'Pisac', 'avatar_path_value', 'stefan-stefanovic');

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
(2, 'Fotografija', 'photography', 1),
(3, 'Književnost', 'literature', 1),
(4, 'Muzika', 'music', 1),
(5, 'Slikarije', 'paintings', 1),
(6, 'Video', 'video', 1);

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
  `content_type` varchar(30) DEFAULT '0',
  `size` int(11) DEFAULT NULL,
  `descriprion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `media`
--

INSERT INTO `media` (`id`, `title`, `slug`, `post_date`, `author_id`, `lang`, `active`, `file_name`, `path`, `content_type`, `size`, `descriprion`) VALUES
(145, 'Stewie and brien', 'stewie-and-brien', '2017-11-29 20:14:26', NULL, 'rs', 1, 'stewie-and-brien.jpg', NULL, 'image/jpeg', 25047, NULL),
(146, 'Stewir blaster gun', 'stewir-blaster-gun', '2017-11-30 20:26:24', NULL, 'rs', 1, 'stewir-blaster-gun.jpg', NULL, 'image/jpeg', 97059, NULL),
(147, 'Stewie helmet', 'stewie-helmet', '2017-12-01 20:43:14', NULL, 'rs', 1, 'stewie-helmet.jpeg', NULL, 'image/jpeg', 26160, NULL),
(148, 'Za jednu osobu', 'za-jednu-osobu', '2017-12-04 21:32:28', NULL, 'rs', 1, 'za-jednu-osobu.pdf', NULL, 'application/pdf', 60514, NULL),
(149, 'Stewie griffin smoke', 'stewie-griffin-smoke', '2017-12-04 22:16:24', NULL, 'rs', 1, 'stewie-griffin-smoke.gif', NULL, 'image/gif', 50938, NULL),
(150, 'Sobakaisti WEB 97069', 'sobakaisti-web-97069', '2017-12-05 20:36:09', NULL, 'rs', 1, 'sobakaisti-web-97069.pdf', NULL, 'application/pdf', 772574, NULL),
(151, 'Wallhaven 248436', 'wallhaven-248436', '2017-12-07 22:12:05', NULL, 'rs', 1, 'wallhaven-248436.png', NULL, 'image/png', 93953, NULL),
(152, '9b5b79daf346c4be1e7183feb73e43d4f761c6c9', '9b5b79daf346c4be1e7183feb73e43d4f761c6c9', '2017-12-07 22:14:48', NULL, 'rs', 1, '9b5b79daf346c4be1e7183feb73e43d4f761c6c9.png', NULL, 'image/png', 90788, NULL),
(153, 'Space 1509400085719 6496', 'space-1509400085719-6496', '2017-12-12 20:08:12', NULL, 'rs', 1, 'space-1509400085719-6496.jpg', NULL, 'image/jpeg', 388881, NULL);

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
  `downloaded` int(11) NOT NULL DEFAULT '0',
  `lang` varchar(5) NOT NULL DEFAULT 'rs',
  `author_id` int(11) NOT NULL,
  `media_id` int(11) DEFAULT NULL,
  `featured_img_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `publication`
--

INSERT INTO `publication` (`id`, `title`, `content`, `slug`, `path`, `post_date`, `active`, `downloaded`, `lang`, `author_id`, `media_id`, `featured_img_id`) VALUES
(6, 'Pleh orkestar', 'sdkjk', 'pleh-orkestar', NULL, '2017-07-11 10:00:00', 1, 999, 'rs', 1, NULL, NULL),
(8, 'Druga odiseja: 2010', 'skdlskdlk', 'druga-odiseja', 'neki_path', '2017-07-03 11:15:00', 1, 0, 'rs', 17, NULL, NULL),
(9, 'Proba publikacije sa Tagovima', '<p>fsfsdfsdfsd</p>', 'proba-publikacije-sa-tagovima', 'Fotografija.svg', NULL, 0, 0, 'rs', 1, NULL, NULL),
(10, 'Astor Lajka - Pleh Orkestar', '<p>fsdfsdf sdfsfsdfsd</p>', 'astor-lajka-pleh-orkestar', 'Igra znak.svg', '2017-07-22 20:24:44', 1, 0, 'rs', 1, NULL, NULL),
(13, 'Paranoja u las vegasu', '<p>Objava nekog članka</p>', 'paranoja-u-las-vegasu', 'theme-48918.xml', '2017-07-22 22:02:48', 1, 0, 'rs', 16, NULL, NULL),
(14, 'Sobakaisti logotip', '', 'sobakaisti-logotip', 'Sobakaisti znak _ logotype.svg', '2017-07-22 22:11:01', 1, 0, 'rs', 19, NULL, NULL),
(16, 'Ovo je probni publication sa tagovima', '<p>Ovo je neki text</p>', 'ovo-je-probni-publication-sa-tagovima', 'Film v2-91841.svg', '2017-07-23 13:48:54', 1, 0, 'rs', 18, NULL, NULL),
(17, 'Proba Izdanja na Sekciji Izdanaja', '<p>Your Linode has a unique IP address that identifies it to other devices and users on the Internet. For the time being, you&rsquo;ll use the IP address to connect to your server. After you perform some of these initial configuration steps outlined in the Linode Quick Start Guides, you can use DNS records to point a domain name at your server and give it a more recognizable and memorable identifier.</p>', 'proba-izdanja-na-sekciji-izdanaja', 'manjaro_linux_wallpaper_3840_x_2160_by_nick_oh-d9j0c34.png', '2017-07-23 16:10:14', 1, 0, 'rs', 20, NULL, NULL),
(18, 'Još jedan primer Izdanja radi pregleda', '<p>In this course, Envato Tuts+ instructor Jos&eacute; Mota will teach you how to build a custom bookmark manager in Ruby on Rails. You''ll create a data model, an interface for editing bookmarks, a link shortener, a scraper to get basic information about each linked resource, and even some front-end JavaScript to improve interactivity. You''ll also get to create some unusual custom controllers and custom actions.</p>', 'jos-jedan-primer-izdanja-radi-pregleda', 'image-0-02-04-8395ae47cfce764e9a8f39124bee44a58c69d50422a2c98ded931481cc94bf90-V.jpg', '2017-07-23 16:22:55', 1, 0, 'rs', 18, NULL, NULL),
(19, 'Proba publikacije sa featured img', '<p>The FileReader object lets web applications asynchronously read the contents of files ( or raw data buffers ) stored on the user&rsquo;s computer, using File or Blob objects to specify the file or data to read, which means that your program will not stall while a file is being read. This is particularly useful when dealing with large files.</p>\r\n<p>File objects may be obtained from a FileList object returned as a result of a user selecting files using the &lt;input&gt; element, from a drag and drop operation&rsquo;s DataTransfer object, or from the mozGetAsFile() API on an HTMLCanvasElement.</p>', 'proba-publikacije-sa-featured-img', 'Favico.png', '2017-07-24 21:16:01', 1, 0, 'rs', 16, NULL, NULL),
(21, 'Ovo je poslednje izdanje', '<p>Ovo je jedan primer izdanja</p>', 'ovo-je-poslednje-izdanje-1', 'if_trashcan_298881.svg', '2017-08-06 20:20:47', 1, 0, 'rs', 20, NULL, NULL),
(22, 'Zašto ne radi upload contenta', '', 'zasto-ne-radi-upload-contenta', 'pleh_orkestar_cover.jpg', '2017-08-09 21:20:11', 1, 0, 'rs', 19, NULL, NULL),
(23, 'Zašto ne radi upload contenta 2', '', 'zasto-ne-radi-upload-contenta-2', 'pleh_orkestar_cover.jpg', '2017-08-09 21:24:07', 1, 0, 'rs', 19, NULL, NULL),
(28, 'How to install MS ttf fonts', '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sagittis aliquam auctor. Duis et lacinia neque. Nunc lacinia fringilla luctus. Pellentesque nulla ipsum, pellentesque nec eleifend vitae, porta at dui. Interdum et malesuada fames ac ante ipsum primis in faucibus. Phasellus eleifend mauris felis, nec tempus ex sollicitudin sit amet. Vestibulum non interdum dui. Nunc tempus, sem nec pellentesque malesuada, arcu mi ornare ex, sit amet placerat mi metus sit amet quam. Nunc quis massa in augue maximus eleifend. Donec sapien leo, efficitur sed luctus in, sollicitudin sodales arcu. Donec pretium et libero non posuere. Donec euismod cursus nunc ut laoreet. Sed feugiat ut lectus aliquet mattis.</p>\r\n<p>Pellentesque ullamcorper felis quis suscipit lacinia. Mauris egestas convallis est et cursus. Donec ac scelerisque ante. In ipsum tortor, blandit vel dapibus sed, efficitur non mauris. Integer faucibus interdum mi nec molestie. Maecenas ullamcorper ultrices nulla. Nulla a augue vitae tortor aliquet blandit. Morbi dignissim bibendum risus eu tincidunt. Pellentesque eget mi massa. Phasellus consequat, ex id pretium consequat, odio leo fermentum nisi, at gravida sem ante sit amet arcu. Fusce eleifend mauris et diam suscipit, ut tincidunt est pellentesque. Vivamus tempus auctor odio, eget convallis leo egestas non. Nunc vitae egestas quam. Donec porta hendrerit ex vitae semper. Donec eu lorem ultricies, semper nulla vitae, hendrerit enim. Nullam efficitur, nisi et cursus luctus, ante libero feugiat eros, ac dictum metus quam vitae leo.</p>', 'how-to-install-ms-ttf-fonts', NULL, '2017-12-04 21:35:25', 1, 0, 'rs', 20, 148, 146),
(29, 'Miodrag Zec gost Pressinga', '<p>Pellentesque ullamcorper felis quis suscipit lacinia. Mauris egestas convallis est et cursus. Donec ac scelerisque ante. In ipsum tortor, blandit vel dapibus sed, efficitur non mauris. Integer faucibus interdum mi nec molestie. Maecenas ullamcorper ultrices nulla. Nulla a augue vitae tortor aliquet blandit. Morbi dignissim bibendum risus eu tincidunt. Pellentesque eget mi massa. Phasellus consequat, ex id pretium consequat, odio leo fermentum nisi, at gravida sem ante sit amet arcu. Fusce eleifend mauris et diam suscipit, ut tincidunt est pellentesque. Vivamus tempus auctor odio, eget convallis leo egestas non. Nunc vitae egestas quam. Donec porta hendrerit ex vitae semper. Donec eu lorem ultricies, semper nulla vitae, hendrerit enim. Nullam efficitur, nisi et cursus luctus, ante libero feugiat eros, ac dictum metus quam vitae leo.</p>', 'miodrag-zec-gost-pressinga', NULL, '2017-12-04 21:44:27', 1, 0, 'rs', 21, 148, 145),
(30, 'Nemanja Pise publication', '<p>Neki sadrzaj</p>', 'nemanja-pise-publication', NULL, '2017-12-04 22:16:47', 1, 0, 'rs', 16, 147, 149),
(31, 'Ovo je naslov koji treba da se vrati nakon cuvanja', '', 'ovo-je-naslov-koji-treba-da-se-vrati-nakon-cuvanja', NULL, '2017-12-04 22:22:18', 1, 0, 'rs', 1, 149, NULL),
(32, 'Clanak koji mora da vrati i autora', '', 'clanak-koji-mora-da-vrati-i-autora', NULL, '2017-12-04 22:34:38', 1, 0, 'rs', 21, 149, NULL),
(33, 'Proba previewa sa featured image', '<p>Pellentesque ullamcorper felis quis suscipit lacinia. Mauris egestas convallis est et cursus. Donec ac scelerisque ante. In ipsum tortor, blandit vel dapibus sed, efficitur non mauris. Integer faucibus interdum mi nec molestie. Maecenas ullamcorper ultrices nulla. Nulla a augue vitae tortor aliquet blandit. Morbi dignissim bibendum risus eu tincidunt. Pellentesque eget mi massa. Phasellus consequat, ex id pretium consequat, odio leo fermentum nisi, at gravida sem ante sit amet arcu. Fusce eleifend mauris et diam suscipit, ut tincidunt est pellentesque. Vivamus tempus auctor odio, eget convallis leo egestas non. Nunc vitae egestas quam. Donec porta hendrerit ex vitae semper. Donec eu lorem ultricies, semper nulla vitae, hendrerit enim. Nullam efficitur, nisi et cursus luctus, ante libero feugiat eros, ac dictum metus quam vitae leo.</p>', 'proba-previewa-sa-featured-image', NULL, '2017-12-05 20:37:04', 1, 0, 'rs', 19, 150, 147),
(34, 'Ovo je Publikation Älanak sa tagovima', '<p>Ovo je body publikacije</p>', 'ovo-je-publikation-clanak-sa-tagovima', NULL, '2017-12-07 21:01:42', 1, 0, 'rs', 19, 148, NULL),
(35, 'Sasvim novi nacin objavljivanja publikacije', '', 'sasvim-novi-nacin-objavljivanja-publikacije', NULL, '2017-12-07 21:05:10', 1, 0, 'rs', 1, 150, 145),
(36, 'upload post sa tagovima', '', 'upload-post-sa-tagovima', NULL, '2017-12-07 21:10:18', 1, 0, 'rs', 1, NULL, 147),
(37, 'Sasvim novi nacin objavljivanja publikacije', '', 'sasvim-novi-nacin-objavljivanja-publikacije-1', NULL, '2017-12-07 22:06:39', 1, 0, 'rs', 1, 148, 149),
(38, 'Stewie Griffin publication', '<p>Neki Älanak</p>', 'stewie-griffin-publication', NULL, '2017-12-07 22:12:56', 1, 0, 'rs', 1, 151, 149),
(39, 'Dodat div iza koskica', '', 'dodat-div-iza-koskica', NULL, '2017-12-07 22:15:40', 1, 0, 'rs', 1, 150, 152),
(40, 'proba Älanka po ko zna koji put', '', 'proba-clanka-po-ko-zna-koji-put', NULL, '2017-12-07 22:21:14', 1, 0, 'rs', 1, 150, NULL),
(41, 'Publication sa oznakama', '', 'publication-sa-oznakama', NULL, '2017-12-07 22:25:19', 1, 0, 'rs', 16, 150, NULL),
(42, 'KonaÄno reÅ¡enje za uploadovane koskice', '<p>ne&scaron;to!</p>', 'konacno-resenje-za-uploadovane-koskice', NULL, '2017-12-07 22:28:14', 1, 0, 'rs', 18, 150, 145),
(43, 'Proba posta sa rotirajucim koskicama', '', 'proba-posta-sa-rotirajucim-koskicama', NULL, '2017-12-07 22:32:18', 1, 0, 'rs', 1, 150, 146),
(44, 'Proba posta sa rotirajucim koskicama', '', 'proba-posta-sa-rotirajucim-koskicama-1', NULL, '2017-12-07 22:33:08', 1, 0, 'rs', 1, 150, 146),
(45, 'Proba posta sa rotirajucim koskicama v2', '', 'proba-posta-sa-rotirajucim-koskicama-v2', NULL, '2017-12-07 22:33:47', 1, 0, 'rs', 1, 150, 146),
(46, 'Publication sa bez ID-a', '', 'publication-sa-bez-id-a-1', NULL, '2017-12-11 21:33:00', 1, 0, 'rs', 1, 148, NULL),
(47, 'Poslednji Älanak sa probom updatea', '<p>sadrzaj</p>', 'poslednji-clanak-sa-probom-updatea', NULL, '2017-12-11 21:53:50', 1, 0, 'rs', 17, 151, NULL),
(48, 'JoÅ¡ jedan publication sa proverom uploada', '<p>Snimano kod Marka (Underground), kod Andreje (Red Water), kod PeÄe (na Krstu), kod Steve (studio Vitas), kod Pere (Destilerija), kod Bandara (Piknik), kod PetroviÄa (Bigz), kod Boice (Ultrazvuk} , kod Cveleta u &scaron;koli MMI, kod Nemanje u Vi&scaron;oj elektrotehniÄkoj &scaron;koli, kod Nikole na VoÅ¾dovcu i u KrÄedinu, na Äamcima na Tisi i Dunavu</p>', 'jos-jedan-publication-sa-proverom-uploada', NULL, '2017-12-11 21:59:57', 1, 0, 'rs', 19, 148, 146),
(49, 'Ovo je poslednji Älanak za VeÄeras', '<p>Snimano kod Marka (Underground), kod Andreje (Red Water), kod PeÄe (na Krstu), kod Steve (studio Vitas), kod Pere (Destilerija), kod Bandara (Piknik), kod PetroviÄa (Bigz), kod Boice (Ultrazvuk} , kod Cveleta u &scaron;koli MMI, kod Nemanje u Vi&scaron;oj elektrotehniÄkoj &scaron;koli, kod Nikole na VoÅ¾dovcu i u KrÄedinu, na Äamcima na Tisi i Dunavu</p>', 'ovo-je-poslednji-clanak-za-veceras', NULL, '2017-12-11 22:07:11', 1, 0, 'rs', 1, 150, NULL),
(50, 'Samo joÅ¡ ova proba', '<p>JA ÄITAV Å½IVOT HODAM UZBRDO<br />I SNAGE MI JO&Scaron; MALO OSTALO<br />ZA BESPOTREBAN KORAK ILI DVA<br />VIDEO SAM SVE OVO I ODOZDO</p>', 'samo-jos-ova-proba', NULL, '2017-12-11 22:27:44', 1, 0, 'rs', 18, 150, 146),
(51, 'Proba publication Älanak sa private opcijom', '<p>Ovo je neki publication sa private opcijom</p>', 'proba-publication-clanak-sa-private-opcijom', NULL, '2017-12-12 20:09:01', 1, 0, 'rs', 16, 148, 153),
(52, 'proba privatnog Älanka drugi put', '<p>&scaron;ta sam ja u tvom Å¾ivotu</p>', 'proba-privatnog-clanka-drugi-put', NULL, '2017-12-12 20:22:22', 0, 0, 'rs', 16, 149, 149),
(53, 'Proba bez fajla', '<p>fydg dfgdfg dfgdf</p>', 'proba-bez-fajla', NULL, '2017-12-12 20:41:55', 1, 0, 'rs', 21, NULL, NULL),
(54, 'Proba sa Listom tagova', '<p>fsdfsdfds fsdf sdfsdf</p>', 'proba-sa-listom-tagova', NULL, '2017-12-12 21:28:46', 0, 0, 'rs', 1, NULL, NULL),
(55, 'Proba sa ID posle zagrada', '<p>proba sa id</p>', 'proba-sa-id-posle-zagrada', NULL, '2017-12-12 21:40:48', 0, 0, 'rs', 1, NULL, NULL),
(56, 'Proba Tagova sa kaunterom klikova ÄÄ', '<p>fdsfsdf sdfÄ&scaron;Ä</p>', 'proba-tagova-sa-kaunterom-klikova-cc', NULL, '2017-12-12 22:05:22', 0, 0, 'rs', 1, 150, NULL),
(57, 'Proba submita koskica', '<p>dasdas asd asdasdas</p>', 'proba-submita-koskica', NULL, '2017-12-12 23:07:08', 0, 0, 'rs', 1, NULL, NULL),
(58, 'Proba sa post datumom', '<p>Ovo je neki post</p>', 'proba-sa-post-datumom', NULL, '2017-11-19 20:51:00', 1, 0, 'rs', 16, NULL, 147),
(59, 'Älankak yza Oktoba 17', '<p>ovo je neki Älanaka</p>', 'clankak-yza-oktoba-17', NULL, '2017-10-18 20:09:00', 1, 0, 'rs', 16, NULL, 149),
(60, 'Proba latiniÄnih slova na Älanku', '<p>Proba latiniÄnih slova na sadrÅ¾aju</p>', 'proba-latinicnih-slova-na-clanku', NULL, '2017-02-10 20:08:00', 0, 0, 'rs', 1, NULL, NULL),
(61, 'Älanak sa Ävakicama', '<p>sadrÅ¾aj sa Ävakicama</p>', 'clanak-sa-cvakicama', NULL, '2017-12-09 11:42:00', 0, 0, 'rs', 20, NULL, NULL),
(62, 'Ovo je Media Älanak', '<p>ovo je neki Älanak</p>', 'ovo-je-media-clanak', NULL, '2017-12-16 20:56:00', 1, 0, 'rs', 20, 148, NULL),
(63, 'PublikejÅ¡n sa oba media fajla', '<p>sadrÅ¾aj</p>', 'publikejsn-sa-oba-media-fajla', NULL, '2017-12-02 11:03:00', 0, 0, 'rs', 16, 148, 147),
(64, 'Proba članka sa kvačicama sa filterom na security', '<p>Ovo je sadžaj</p>', 'proba-clanka-sa-kvacicama-sa-filterom-na-security', NULL, '2017-12-19 21:23:01', 0, 0, 'rs', 16, NULL, NULL),
(65, 'Konačna proba članka tipa Publication za Večeras', '<p>Konačna proba članka tipa Publication za Večeras</p>', 'konacna-proba-clanka-tipa-publication-za-veceras', NULL, '2017-12-09 22:43:00', 1, 0, 'rs', 16, 148, 145),
(66, 'Dvaesprvi dec, članak za probu koskica', '<p>sadržaj posta ili članka</p>', 'dvaesprvi-dec-clanak-za-probu-koskica', NULL, '2017-12-21 22:26:43', 0, 0, 'rs', 16, NULL, 149),
(67, 'Proba tagova za danas', '', 'proba-tagova-za-danas', NULL, '2017-12-22 20:20:38', 0, 0, 'rs', 16, NULL, 145),
(68, 'Proba sa tagovima ko zna koja', '', 'proba-sa-tagovima-ko-zna-koja', NULL, '2017-12-22 21:30:59', 1, 0, 'rs', 16, NULL, 147),
(69, 'Proba publikejšna sa ispravnom validacijom', '<p>Ov je sadržaj</p>', 'proba-publikejsna-sa-ispravnom-validacijom', NULL, '2017-12-26 22:00:09', 1, 0, 'rs', 16, NULL, NULL),
(70, 'Proba nekog naslova', '<p>ksdjkhsdh&nbsp; dsjhdjhsjkhdjksjkh</p>', 'proba-nekog-naslova', NULL, '2017-12-27 20:37:40', 1, 0, 'rs', 1, NULL, NULL),
(71, 'Proba publicationsa sa porukama o uspešnosti', '', 'proba-publicationsa-sa-porukama-o-uspesnosti', NULL, '2017-12-27 20:41:25', 0, 0, 'rs', 1, NULL, NULL),
(72, 'propba responsea', '', '-57', NULL, '2017-12-28 21:45:43', 0, 0, 'rs', 1, NULL, NULL),
(73, 'Sasvim novi nacin objavljivanja publikacije', '', 'sasvim-novi-nacin-objavljivanja-publikacije-2', NULL, '2017-12-28 21:51:54', 0, 0, 'rs', 1, NULL, NULL),
(74, 'Sasvim novi nacin objavljivanja publikacije', '', 'sasvim-novi-nacin-objavljivanja-publikacije-3', NULL, '2017-12-28 21:54:05', 0, 0, 'rs', 1, NULL, NULL),
(75, 'Sasvim novi nacin objavljivanja publikacije', '', 'sasvim-novi-nacin-objavljivanja-publikacije-4', NULL, '2017-12-28 21:56:48', 0, 0, 'rs', 1, NULL, NULL),
(76, 'Proba responsea', '', 'proba-responsea', NULL, '2017-12-28 21:59:32', 0, 0, 'rs', 16, NULL, NULL),
(77, 'Sasvim novi nacin objavljivanja publikacije', '', 'sasvim-novi-nacin-objavljivanja-publikacije-5', NULL, '2017-12-28 22:02:20', 0, 0, 'rs', 1, NULL, NULL),
(78, 'Proba commit messages', '', 'proba-commit-messages', NULL, '2017-12-28 22:07:23', 0, 0, 'rs', 1, NULL, NULL),
(79, 'Publication sa submitom', '', 'publication-sa-submitom', NULL, '2017-12-28 22:13:01', 0, 0, 'rs', 1, NULL, NULL),
(80, 'Sasvim novi nacin objavljivanja publikacije', '', 'sasvim-novi-nacin-objavljivanja-publikacije-6', NULL, '2017-12-28 22:16:50', 0, 0, 'rs', 20, NULL, NULL),
(81, 'Sasvim novi nacin objavljivanja publikacije', '', 'sasvim-novi-nacin-objavljivanja-publikacije-7', NULL, '2017-12-28 22:18:40', 0, 0, 'rs', 16, NULL, NULL),
(82, 'Proba commit messages', '', 'proba-commit-messages-1', NULL, '2017-12-28 22:19:34', 0, 0, 'rs', 1, NULL, NULL),
(83, 'Sasvim novi nacin objavljivanja publikacije', '', 'sasvim-novi-nacin-objavljivanja-publikacije-8', NULL, '2017-12-28 22:26:45', 0, 0, 'rs', 16, NULL, NULL),
(84, 'Proba publikešna sa uklonjemi šč filetom', '<p>Ovo je proa članak sa kvači&scaron;ćama</p>', 'proba-publikesna-sa-uklonjemi-sc-filetom', NULL, '2017-12-29 21:25:45', 0, 0, 'rs', 1, NULL, NULL),
(85, 'Proba sa ispravljenim lang', '', 'proba-sa-ispravljenim-lang', NULL, '2017-12-29 21:38:22', 1, 0, 'rs', 1, NULL, NULL),
(86, 'Menkksnd ksadasn', '', 'menkksnd-ksadasn', NULL, '2017-12-29 21:40:18', 0, 0, 'rs', 1, NULL, NULL),
(87, 'Sasvim novi nacin objavljivanja publikacije', '', 'sasvim-novi-nacin-objavljivanja-publikacije-9', NULL, '2017-12-29 21:42:41', 0, 0, 'rs', 1, NULL, NULL),
(88, 'Menkksnd ksadasn', '', 'menkksnd-ksadasn-1', NULL, '2017-12-29 21:46:38', 0, 0, 'rs', 16, NULL, NULL),
(89, 'neki naslov', '', 'neki-naslov', NULL, '2017-12-29 22:02:30', 0, 0, 'rs', 17, NULL, NULL),
(90, 'Sasvim novi nacin objavljivanja publikacije', '', 'sasvim-novi-nacin-objavljivanja-publikacije-10', NULL, '2017-12-29 22:07:46', 0, 0, 'rs', 1, NULL, NULL),
(91, 'Proba commit messages', '', 'proba-commit-messages-2', NULL, '2017-12-29 22:10:18', 0, 0, 'rs', 1, NULL, NULL),
(92, 'Menkksnd ksadasn', '', 'menkksnd-ksadasn-2', NULL, '2017-12-29 22:15:02', 0, 0, 'rs', 1, NULL, NULL),
(93, 'Proba commit messages', '', 'proba-commit-messages-3', NULL, '2017-12-29 22:16:35', 0, 0, 'rs', 19, NULL, NULL),
(94, 'Menkksnd ksadasn', '', 'menkksnd-ksadasn-3', NULL, '2017-12-29 22:18:20', 0, 0, 'rs', 17, NULL, NULL);

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
(9, 11),
(9, 17),
(9, 18),
(9, 20),
(9, 21),
(13, 11),
(13, 18),
(13, 20),
(14, 1),
(16, 1),
(17, 1),
(17, 6),
(19, 6),
(22, 1),
(23, 1),
(35, 1),
(35, 2),
(35, 4),
(35, 11),
(36, 1),
(36, 23),
(36, 38),
(37, 1),
(37, 34),
(37, 38),
(38, 1),
(38, 23),
(38, 38),
(39, 1),
(39, 2),
(39, 11),
(39, 20),
(39, 23),
(39, 47),
(40, 1),
(40, 23),
(40, 34),
(40, 38),
(41, 1),
(41, 23),
(41, 34),
(41, 38),
(42, 1),
(42, 2),
(42, 12),
(42, 23),
(42, 38),
(42, 50),
(43, 1),
(43, 23),
(43, 34),
(43, 38),
(45, 1),
(45, 2),
(45, 23),
(45, 34),
(45, 38),
(47, 2),
(47, 50),
(48, 1),
(48, 23),
(48, 34),
(48, 38),
(49, 1),
(50, 1),
(50, 23),
(50, 34),
(51, 1),
(51, 23),
(51, 34),
(51, 38),
(52, 1),
(52, 23),
(52, 34),
(52, 38),
(52, 44),
(54, 1),
(54, 23),
(54, 34),
(54, 38),
(60, 1),
(60, 23),
(60, 34),
(60, 38),
(61, 2),
(61, 11),
(61, 20),
(62, 11),
(62, 17),
(62, 18),
(62, 20),
(63, 17),
(63, 11),
(63, 21),
(65, 11),
(65, 17),
(65, 18),
(65, 20),
(66, 1),
(66, 23),
(66, 34),
(66, 38),
(67, 1),
(67, 23),
(67, 34),
(67, 38),
(68, 1),
(68, 23),
(68, 34),
(68, 38),
(69, 52);

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
(12, 'postmoderna', 'postmoderna'),
(13, 'esej', 'esej'),
(14, 'jazz', 'jazz'),
(15, 'Astor Lajka', 'astor-lajka'),
(16, 'bluzdog', 'bluzdog'),
(17, 'muzej', 'muzej'),
(18, 'muza', 'muza'),
(19, 'mjuzikl', 'mjuzikl'),
(20, 'muzičar', 'muzicar'),
(21, 'Muzičarke', 'muzicarke'),
(22, 'Downloads', 'downloads'),
(23, 'Sobakaiza', 'sobakaiza'),
(24, 'neka', 'neka'),
(25, 'nekad', 'nekad'),
(26, 'return', 'return'),
(27, 'ret', 'ret'),
(28, 'clanak', 'clanak'),
(29, 'tastatura', 'tastatura'),
(30, 'tadasnji', 'tadasnji'),
(31, 'opeth', 'opeth'),
(32, 'zvezda', 'zvezda'),
(33, 'zvezdara', 'zvezdara'),
(34, 'sobaka', 'sobaka'),
(35, 'Zorana', 'zorana'),
(36, 'jelles nemanjic', 'jelles-nemanjic'),
(37, 'Enter', 'enter'),
(38, 'Sobakaizam', 'sobakaizam'),
(39, 'Ljubav', 'ljubav'),
(40, 'Slikarije', 'slikarije'),
(41, 'Dingospo Dali', 'dingospo-dali'),
(42, 'marihuana', 'marihuana'),
(43, 'Kanabis', 'kanabis'),
(44, 'sistem', 'sistem'),
(45, 'ulica', 'ulica'),
(46, 'neformalnost', 'neformalnost'),
(47, 'književnost', 'knjizevnost'),
(48, 'Post', 'post'),
(49, 'Kalendar', 'kalendar'),
(50, 'proza', 'proza'),
(51, 'Oznaka', 'oznaka'),
(52, 'zvučnici', 'zvucnici');

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
-- Indexes for table `intro_article`
--
ALTER TABLE `intro_article`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `lang_code` (`lang`);

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
-- Indexes for table `publication_tag`
--
ALTER TABLE `publication_tag`
  ADD KEY `publication_id` (`publication_id`),
  ADD KEY `tag_id` (`tag_id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;
--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `intro_article`
--
ALTER TABLE `intro_article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `media`
--
ALTER TABLE `media`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=154;
--
-- AUTO_INCREMENT for table `publication`
--
ALTER TABLE `publication`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;
--
-- AUTO_INCREMENT for table `tag`
--
ALTER TABLE `tag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
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

--
-- Constraints for table `publication_tag`
--
ALTER TABLE `publication_tag`
  ADD CONSTRAINT `publication_fk` FOREIGN KEY (`publication_id`) REFERENCES `publication` (`id`),
  ADD CONSTRAINT `tag_fk` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
