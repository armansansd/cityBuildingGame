PImage afficheRandomImage(PImage refImage ,String refDossier) {
  list_directory liste;
  liste = new list_directory(refDossier, "jpg|png|gif");
  
  String hasard = liste.fichiers[floor(random(liste.nb_items))];

  refImage = loadImage(hasard);
  
  return refImage;
}
