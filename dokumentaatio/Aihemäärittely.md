#Aihe: Miinaharava
Toteutetaan pieni standalone peli, miinaharava. Miinaharava koostuu ruudukosta, joista pelin alussa
pelaajalla ei ole tietoa, sisältävätkö ne miinoja vai ei. Ruutua painamalla testataan, onko kyseisessä
ruudussa miinaa - jos on, peli päättyy ja peli näyttää miinojen paikat. Jos ruudussa ei ole miinaa,
sitä ei enää voi painaa uudestaan, mutta se ilmaisee numerolla viereisten ruutujen miinojen määrät.
Miinojen paikkoja ei kerrota pelaajalle.


Käyttäjät: pelaaja

Kaikkien käyttäjien toiminnot:
  * Pelimoodin valinta 
    * Kolme esiasetettua vaikeustasoa, 10x10, 15x15 ja 20x20 ruudukot, joissa 10%, 15% ja 20% miinoja.
	* Mahdollisuus vapaavalintaiseen moodiin, jossa pelaaja voi valita vapaasti leveyden, korkeuden sekä miinojen määrän.
  * Pelin ohjeiden lukeminen
  * Huipputulosten tarkasteleminen
  * Pelin aloitus
  * Ruudun valinta
    * Toistuu kunnes peli on voitettu tai osutaan miinaan
  * Ruudun liputtaminen. Ruudun voi merkitä lipulla, jos pelaaja uskoo siinä olevan miinan. Tällöin ruutua ei voi valita uudelleen, mutta lipun voi myös poistaa tehden ruudusta taas normaalin ruudun.
    * Kun kaikki ruudukon miinat on merkitty lipuilla, peli päättyy pelaajan voittoon.
  * Jos pelaaja jää jumiin tai ei ole varma, missä ruudussa miina on, hän voi pyytää peliltä vihjettä. Tällöin peli näyttää yhden merkitsemättömän miinan paikan ruudukossa, mutta lisää viisi sekuntia pelikelloon.
  
  

