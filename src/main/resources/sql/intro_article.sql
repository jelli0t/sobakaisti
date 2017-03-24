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

--
-- Indexes for dumped tables
--

--
-- Indexes for table `intro_article`
--
ALTER TABLE `intro_article`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `lang_code` (`lang`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `intro_article`
--
ALTER TABLE `intro_article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
