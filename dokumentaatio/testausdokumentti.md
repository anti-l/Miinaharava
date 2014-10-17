# Testausdokumentaatio

## Testaus

Projektia on testattu mielestäni melkoisen kattavasti, koska alussa konfiguroin PITestauksen kattamaan kaikki projektin paketit (mitä ei siis olisi tarvinnut tehdä), jokainen raportti näytti inhottavasti nollaa prosenttia ja punaista palkkia sekä graafisen käyttöliittymän että pääpaketin suhteen. Tahdoin saada vähän korkeampia prosentteja raportista, jotta pysyttäisiin mukana rivikattavuus ja mutaatiotestien tavoitteissa, joten tein testejä myös käyttöliittymälle.

Viimeisin raportti kertoo miinaharava.domain -paketin rivikattavuuden olevan 100% ja mutaatiokttavuuden 95%. Sovelluslogiikassa prosentit ovat 94% ja 67%, joista keskiarvona saadaan rivikattavuudelle 97% ja mutaatioille 81%. Huomasin, että varsinkin sovelluslogiikan puolella testien saaminen 100% asti oli erittäin vaikeaa, kun poikkeuksia käsittelevät lohkot aina jäivät (syystäkin!) testien ulkopuolelle, ja koska testaus poisti joskus metodikutsuja, jotka ovat välttämättömiä koko ohjeman toiminnalle mutta eivät sille metodille tai testille, jossa asia käydään läpi, raportteihin tuli mielestäni turhan paljon punaista väriä sekä kattavuudet kärsivät.

Tästäkin huolimatta olen testannut näitä metodeja käsin, kommentoimalla välillä osan koodista pois tai muuttamalla sitä siten, että joudun myös ajamaan koodia, johon ei testeissä päästä käsiksi. Näin ollen testaus on onneksi ollut laajempaa, kuin mihin automatisoidut raportit väittävät testien pääsevän.

## Poikkeukset ja syötteet 

Muuten olen tyytyväinen projektiini. Olen käsitellyt kaikki poikkeukset sekä kaikki syötteet, joilla ohjelman toiminnot saadaan joko rikottua tai kaadettua. Ohjelman tekstikenttiin voi syöttää myös muita merkkejä kuin kokonaislukuja, mutta jos niihin yritetään laittaa yhtä pienempiä numeroita tai virheellisiä merkkejä, arvot palautetaan oletusarvoiksi ja peli aloitaan niillä. Nappeja ei ole mahdollista painella väärin. Vihje-nappikin toimii oikein - jos pelilaudalla on 10 miinaa ja kymmenen lippua, uutta vihjettä ei enää anneta - aikaa ei myöskään sakoteta.

Itse ohjelmaa on testattu projektin aikana lukemattomia kertoa, useita tunteja yhteensä. Raja-arvoja ja ongelmakohtia on metsästetty ja niitä on ratkottu, eikä uusia bugeja ole enää löytynyt.

## Bugit

Kahta bugia en ole täysin eristänyt ja korjannut, koska niiden ilmeneminen on hyvin satunnaista ja vaikeaa toistaa. Ensimmäinen on se, että aina, kun pelilaudan kaikki miinat on oikein liputettu, peli ei lopukaan. Ei, vaikka kaikki liput olisivat oikeissa paikoissa, niitä olisi oikea määrä ja kaikki muut ruudut olisi katsottu. Toisessa bugissa, joka esiintyy tyhjiä ruutuja massoittain avatessa, peli ei laskekaan kaikkien ruutujen viereisten miinojen määrää oikein. Joskus miinan sisältävän ruudun vasen yläkulma näyttää tyhjää, vaikka siinä pitäisi olla numero. Molemmat näistä virheistä ovat hyvin harvinaisia, mutta syytä niiden esiintymiseen en ole kyennyt selvittämään oikein.


