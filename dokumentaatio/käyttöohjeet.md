# Miinaharava

Tervetuloa pelaamaan miinaharavaa!

## Pelin aloitus

Kun peli käynnistyy, ensimmäinen ikkuna minkä pelaaja saa eteensä on pelimoodin valinta. Ensimmäisellä ruudulla pelaaja voi valita yhden kolmesta vaikeustasosta:

| *Moodi* | Leveys x Korkeus | Miinoja | Miinoja% |
|--------|---------|----|-----|
| Helppo | 10 x 10 | 10 | 10% |
| Keskivaikea | 15 x 15 | 35 | 15% |
| Vaikea | 20 x 20 | 80 | 20% |
| Oma valinta | ## x ## | ## | ## |

Pelaaja voi myös itse määritellä pelilaudan leveyden, korkeuden ja miinojen määrän valitsemalla *Oma valinta*.

Peli alkaa, kun ikkunan oikeasta alanurkasta painetaan *"Aloita"* -nappia.

Pelin ohjeet saa nähtäväkseen, kun alareunasta painaa *"Ohjeet"* -nappia, ja aiempien pelikertojen parhaat tulokset näkee samalla tavalla *"Huipputulokset"* -napista.


## Pelin pelaaminen

Pelin mekaniikka on samanlainen jokaisessa pelimoodissa, miinojen vähäinen määrä tekee pelistä hyvin helpon.

Aloitetaan peli oletus- eli *helpolla* vaikeustasolla. Aloita-nappia painamalla 10x10 peliruudukon sisältämä ikkuna ilmestyy ruudulle. Koko peliä pelataan tästä ikkunasta, käyttäen pelkästään hiirtä ja sen kahta näppäintä.

Peliä pelataan valitsemalla ruudukosta yksi ruutu, jota hiiren vasemmalla näppäimellä painamalla peli tarkastaa, onko kyseisessä ruudussa miinaa. Jos pelaaja osuu miinaan, se räjähtää, ja peli on ohi. Jos pelaaja osuu miinan viereiseen ruutuun, ruutu paljastaa numerolla viereisten ruutujen miinojen määrän. Kun useampi ruutu on paljastettu, näistä numeroista voidaan päätellä, missä ruudussa miina sijaitsee.

Jos pelaaja osuu tyhjään ruutuun, jonka vieressäkään ei ole miinaa, ruudukko paljastaa kaikki viereiset samanlaiset tyhjät ruudut sekä niiden viereiset ruudut, joissa on joku numerotieto - eli ruudut, joiden vieressä on miinoja.

Peliä pelatessa miinojen paikat merkitään hiiren oikealla näppäimellä painamalla. Tällöin ruutuun ilmestyy lippu, joka myös estää ruudun valitsemisen myöhemmin. Lipun voi myös poistaa samalla tavalla oikeaa hiiren näppäintä painamalla.

Peli voitetaan siten, että kaikkien miinojen paikat liputetaan. Kun lippuja on pelilaudalla sama määrä kuin miinoja, peli tarkastaa, onko liput oikeassa paikassa. Jos on, peli loppuu ja pelaajalle kerrotaan pelin ratkaisemiseen kulunut aika. Jos lipuista yksikin on väärässä paikassa tai niitä on liikaa, peli odottaa, että pelin loppumiseen vaaditut kriteerit täyttyy.

Jos pelissä jää jumiin, peli-ikkunan vihje-nappi auttaa. Se näyttää peliruudukossa yhden liputtamattoman miinan paikan sekä liputtaa sen, muuttaen samalla ruudun taustan oranssiksi. Tästä toiminnosta rokotetaan pelaajaa viiden sekunnin aikasakolla.

## Taktiikoita

Pelin ensimmäinen ruutu voidaan valita umpimähkään, koska alussa ei kuitenkaan tiedetä missä miinat sijaitsevat. Jotkut aloittavat aina keskeltä, jotkut valitsevat nurkkaruudut ensimmäisenä. Tähän tuskin on yhtään oikeaa tapaa.

Pelaajan tulee muistaa, että numero ruudussa tarkoittaa sitä, että sen viereisissä ruuduissa on juuri sen verran miinoja, kuin numero on. Jos esimerkiksi numero yksi on varmasti miinan vieressä, pelaaja voi rauhassa testata kaikki loput seitsemän ruutua, koska niissä ei silloin ole miinaa - ellei alun olettamus ole väärässä!

## Pelin loputtua

Jos peli päättyy siihen, että kaikki miinat löytyvät ja ne liputetaan, pelikello pysähtyy ja kokonaisaika paljastetaan. Jos aika on niin hyvä, että se mahtuu kymmeneen parhaaseen aikaan, pelaajalta kysytään nimeä kymmenen parhaan listalle täytettäväksi.

Jos peli päättyy siihen, että pelaaja osuu miinaan, pelilaudalla näytetään muidenkin miinojen paikat. Kohtalokas miina, johon osuttiin, näytetään punaisella pohjalla, loput merkitsemättömät miinat näytetään sellaisenaan. Virheellisiin paikkoihin sijoiteut liput näytetään tummanharmaalla pohjalla.

