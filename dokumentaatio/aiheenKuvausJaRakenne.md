## Aiheen kuvaus (1.9.2014)

Valitsemani harjoitustyön aihe on yksinkertainen miinaharava-peli.

Itse peli koostuu Ruutu-luokasta, joka sisältäät tiedot sisältämästään miinasta, kenties myös naapureista (ei välttämättä fiksua). Ruudut sijaitsevat Ruudukossa, joka on pelin pelialue - kaksiulotteinen taulukko.

Käyttöliittymä tullee tehtyä Javax Swingillä, missä pelialue koostuu useista JButtoneista ruudukon muodossa, jokainen kuvaa yhtä Ruutua pelissä.

Ajan puitteissa luodaan mahdollisuudet useamman kokoisen pelialueen pelaamiseen (aloitus 10x10, sitten 20x20, 30x40 tms). Myös ajan riittäessä luodaan pelille juokseva kello ja sen kautta myös parhaiden tulosten lista.

---

## Rakennekuvaus, päivitys edelliseen (13.10.2014).

Nyt kun projekti on lähes valmis, ohjelman rakennetta voidaan tarkentaa.

### Projektin paketit
Projekti koostuu neljästä paketista: miinaharava, miinaharava.domain, miinaharava.sovelluslogiikka sekä miinaharava.gui.

1. miinaharava-paketti
Miinaharava-paketissa on pelkästään projektin käynnistävä Main-luokka. Sillä ei ole muuta toiminnallisuutta kuin käynnistää sovelluslogiikka, liittää se aloitusruutuun ja sitä kautta pelin graafiseen käyttöliittymään.

2. miinaharava.domain -paketti
Domain-paketissa on projektin sisällön ydinpalaset: Ruutu, Ruudukko, Tulos ja Vaikeustaso -luokat.

Ruutu on miinaharava-projektin pelilaudan yksi palanen; luokka, joka pelilaudan luotua tietää omat koordinaattinsa laudalla, tietää viereisten ruudukon ruutujen miinojen määrät, sekä tietysti omat tietonsa - onko ruudussa miinaa, onko sitä merkitty lipulla tai onko se tarkastettu jo. Näiden kenttien lisäksi luokalla on useita gettereitä ja settereitä. Ruutu ei tunne muita luokkia.

Ruudukko on kaksiulotteinen taulukko Ruutu-olioita. Se on kuvaa pelilautaa, sisältää tiedot leveydestään ja korkeudestaan, miinojen määrästä (mutta ei paikoista, ne se saa Ruuduiltaan) sekä arpoo itse miinat ruutuihinsa pelilautaa luodessa. Luokalla on metodeina gettereitä ja settereitä, metodit luoda pelilauta ja pitää sitä yllä sekä laskea, onko pelin kaikki miinat löydetty - voidaanko peli lopettaa voittoon. Ruudukko tuntee läjän Ruutuja ja keskustelee ainoastaan Sovelluslogiikan kanssa.

Tulos on luokka, joka sisältää yhden pelin pelatun tuloksen - se tallettaa pelaajan nimen ja peliin kuluneen ajan. Vain voitetuista peleistä pidetään kirjaa, mutta Tulos ei ota kantaa pelin vaikeustasoon - se on HuippuTulokset-luokan hommia. Tulos toteuttaa Comparable-rajapinnan, jotta HuippuTulokset voi helposti järjestää tuloslistan oikein. Tulos ei tunne muita luokkia, ja keskustelee vain ja ainoastaan HuippuTulokset-luokan kanssa.

Viimeisenä luokkana domain-paketissa on enum Vaikeustaso. Näitä on neljä: HELPPO, KESKIVAIKEA, VAIKEA ja CUSTOM. Vain kolmesta ensimmäisestä pidetään pelissä kirjaa.

3. miinaharava.sovelluslogiikka -paketti
Paketissa on kaksi hyvin tärkeää luokkaa, koko pelin ydin Sovelluslogiikka sekä tulosten kirjaamiseen, tarkastamiseen, sekä levylle kirjoittamiseen että sieltä lukemiseen tarkoitettu HuippuTulokset.

Sovelluslogiikka keskustelee käytännössä lähes kaikkien pelin luokkien kanssa. Sen kautta koko peli pyörii, se käskee pelilautoja muodostumaan ja ohjaa niiden toimia. Graafinen käyttöliittymä (4. paketti) ohjaa Sovelluslogiikan toimia.

HuippuTulokset lukee ja kirjoittaa tekstimuotoista topscore-listaa. Jos se on korruptoitunut, sitä ei pystytä lukea tai se puuttuu esimerkiksi pelin ensimmäistä kertaa käynnistämisen takia, se luodaan oletusarvoilla. Luokka pitää kirjaa kymmenestä parhaasta tuloksesta kolmella vaikeustasolla, vastaa kysymyksiin tuloksen hyvyydestä listalle pääsemisen kohdalla sekä sijoittaa uudet huipputulokset listoilleen.

4. miinaharava.gui -paketti
Tämä paketti sisältää kaikki graafisen käyttöliittymän komponentit - neljä ikkunaa, kustomoidun ruutunapin, pelikellon ja kaikki kuuntelijat, jotka odottavan käyttäjän toimia. 

Paketissa on neljä ikkunaa - alkuikkuna, josta valitaan vaikeustaso ja käynnistetään peli, jossa voidaan tarkastella ohjeita ja tuloslistaa; ohjeet-ikkuna, joka kertoo lyhyesti pelin ohjeet; tulosikkuna, joka listaa HuippuTulokset-luokan kirjaamat tulokset näytölle sekä tärkeimpänä PeliIkkuna, joka sisältää peliruudukon, miinalaskurin, pelikellon sekä vihjenapin vaikeita tilanteita varten.

Paketissa on kolme kuuntelijaa, jotka kaikki odottavat käyttäjän napin painalluksia. AlkuKuuntelija ja VihjeKuuntelija toteuttavat ActionListener-rajapinnan, NapinKuuntelija MouseListener-rajapinnan. Ensimmäinen kuuntelee AlkuIkkunaa, kaksi jälkimmäistä PeliIkkunaa. Ohje- ja TulosIkkunoilla ei ole kuuntelijoita eikä aktiivista toiminnallisuutta.

