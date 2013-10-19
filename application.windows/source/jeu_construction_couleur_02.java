import processing.core.*; 
import processing.xml.*; 

import processing.video.*; 
import java.util.*; 
import java.io.File; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class jeu_construction_couleur_02 extends PApplet {






// objet capture
Capture video;
// image processing
PImage imgJaune;
PImage imgRouge;
PImage imgBleue;
PImage imgVerte;
PImage imgBlanc;
PImage imgViolet;
PImage imgOrange;

//key x
int x = 0;

// on initialise
public void setup() {
  size(1024, 768);
  println(Capture.list()); 
  // on cree la capture
  video = new Capture( this, width, height, 30 );

  // on charge l'image facade en m\u00e9moire

  imgRouge = afficheRandomImage( imgRouge, "rouge" );
  imgBleue = afficheRandomImage( imgBleue, "bleu"  );
  imgVerte = afficheRandomImage( imgVerte, "vert"  );
  imgBlanc = afficheRandomImage( imgBlanc, "blanc"  );
  imgOrange = afficheRandomImage( imgOrange, "orange"  );
  imgJaune = afficheRandomImage( imgJaune, "jaune" );
  imgViolet = afficheRandomImage( imgViolet, "violet"  );

  // pas de contour
  noStroke();
  // on lisse l'image
  smooth();
}

// on cr\u00e9\u00e9 les couleurs cherch\u00e9es
int searchColor1 = color( 255, 255, 0 );
int searchColor2 = color( 255, 0, 0 );
int searchColor3 = color( 0, 0, 255 );
int searchColor4 = color( 0, 255, 0 );
int searchColor5 = color( 255, 255, 255 );
int searchColor6 = color( 255, 0, 255 );
int searchColor7 = color( 220, 0, 0 );   // ce n'est pas du orange. mais on s'en fout.
//t
int t = 0;

// on initialise les points
Point e1 = new Point( 0, 0 );
Point e2 = new Point( 640, 0);
Point e3 = new Point( 640, 480);
Point e4 = new Point( 0, 480);
Point e5 = new Point( 0, 240);
Point e6 = new Point( 320, 0);
Point e7 = new Point( 320, 480);


boolean s1, s2, s3, s4, s5, s6, s7;


public void draw() {
  //timer reset
  t ++;
  println (t); 
  if (t>=750) {
    imgRouge = afficheRandomImage( imgRouge, "rouge" );
    imgBleue = afficheRandomImage( imgBleue, "bleu"  );
    imgVerte = afficheRandomImage( imgVerte, "vert"  );
    imgBlanc = afficheRandomImage( imgBlanc, "blanc"  );
    imgOrange = afficheRandomImage( imgOrange, "orange"  );
    imgJaune= afficheRandomImage( imgJaune, "jaune" );
    imgViolet = afficheRandomImage( imgViolet, "violet"  );
    t=0;
  }


  if ( video.available()) {
    video.read();

    // mode miroir
    pushMatrix();
    imageMode(CORNER);
    scale(-1, 1);
    image( video, -width, 0, width, height );
    popMatrix();
    // fin miroir


    int idx = 0;

    //on cree des tableaux de points
    ArrayList p1 = new ArrayList();
    ArrayList p2 = new ArrayList();
    ArrayList p3 = new ArrayList();
    ArrayList p4 = new ArrayList();
    ArrayList p5 = new ArrayList();
    ArrayList p6 = new ArrayList();
    ArrayList p7 = new ArrayList();

    for ( int y = 0; y < video.height ; y++ ) {
      for ( int x = video.width; x >0; x-- ) {
        if ( match( searchColor1, video.pixels[idx] )) {
          p1.add( new Point( x, y ));
          //fill( 255, 255, 0, 128 );
          //ellipse( x, y, 10, 10 );
        } 
        else if (match( searchColor2, video.pixels[idx] )) {
          p2.add( new Point( x, y ));
          //fill( 255, 0, 0, 128 );
          //ellipse( x, y, 10, 10 );
        }
        else if (match( searchColor3, video.pixels[idx] )) {
          p3.add( new Point( x, y ));
          //fill( 255, 0, 0, 128 );
          //ellipse( x, y, 10, 10 );
        }
        else if (match( searchColor4, video.pixels[idx] )) {
          p4.add( new Point( x, y ));
          //fill( 255, 0, 0, 128 );
          //ellipse( x, y, 10, 10 );
        }
        else if (match( searchColor5, video.pixels[idx] )) {
          p5.add( new Point( x, y ));
          //fill( 255, 0, 0, 128 );
          //ellipse( x, y, 10, 10 );
        }
        else if (match( searchColor6, video.pixels[idx] )) {
          p6.add( new Point( x, y ));
          //fill( 255, 0, 0, 128 );
          //ellipse( x, y, 10, 10 );
        }
        else if (match( searchColor7, video.pixels[idx] )) {
          p7.add( new Point( x, y ));
          //fill( 255, 0, 0, 128 );
          //ellipse( x, y, 10, 10 );
        }
        idx ++;
      }
    }

    noStroke();
    if (p1.size() > 0) e1 = avg( p1 );
    if (p2.size() > 0) e2 = avg( p2 );
    if (p3.size() > 0) e3 = avg( p3 );
    if (p4.size() > 0) e4 = avg( p4 );
    if (p5.size() > 0) e5 = avg( p5 );
    if (p6.size() > 0) e6 = avg( p6 );
    if (p7.size() > 0) e7 = avg( p7 );


    if (s1) {
      imageMode(CENTER);
      pushMatrix();
      translate(0, -20);
      translate( e1.x, e1.y );
      image( imgJaune, 0, 0 );
      popMatrix();
    }
    if ( s2 ) {  
      imageMode(CENTER);
      pushMatrix();
      translate(0, -10);
      translate( e2.x, e2.y );
      image( imgRouge, 0, 0 );
      popMatrix();
    }
    if ( s3 ) { 
      imageMode(CENTER);
      pushMatrix(); 
      translate(0, -10);
      translate( e3.x, e3.y );
      image( imgBleue, 0, 0 );
      popMatrix();
    }
    if ( s4 ) {
      imageMode(CENTER);
      pushMatrix(); 
      translate(0, -10);  
      translate( e4.x, e4.y );
      image( imgVerte, 0, 0 );
      popMatrix();
    }
    if ( s5 ) {  
      imageMode(CENTER);
      pushMatrix(); 
      translate( e5.x, e5.y );
      image( imgBlanc, 0, 0 );
      popMatrix();
    }
    if ( s6 ) { 
      imageMode(CENTER);
      pushMatrix();
      translate(0, -20);  
      translate( e6.x, e6.y );
      image( imgViolet, 0, 0 );
      popMatrix();
    }
    if ( s7 ) {  

      imageMode(CENTER);
      pushMatrix(); 
      translate(0, -10);
      translate( e7.x, e7.y );
      image( imgOrange, 0, 0 );
      popMatrix();
    }
  }
}

// fonction
public boolean match( int c1, int c2 ) {
  int limit = 10;
  int sr = c1 >> 16 & 0xFF;
  int sg = c1 >> 8 & 0xFF;
  int sb = c1 & 0xFF;

  int cr = c2 >> 16 & 0xFF;
  int cg = c2 >> 8 & 0xFF;
  int cb = c2 & 0xFF;

  return cr > sr - limit && cr < sr + limit &&
    cg > sg - limit && cg < sg + limit && 
    cb > sb - limit && cb < sb + limit;
}

// clic
public void keyPressed() {
  if (key == '1') {
    x = 1;
  }
  if (key == '2') {
    x = 2;
  }
  if (key == '3') {
    x = 3;
  }
  if (key == '4') {
    x = 4;
  }
  if (key == '5') {
    x = 5;
  }
  if (key == '6') {
    x = 6;
  }
  if (key == '7') {
    x = 7;
  }
  if (key =='r') {

    imgRouge = afficheRandomImage( imgRouge, "rouge" );
    imgBleue = afficheRandomImage( imgBleue, "bleu"  );
    imgVerte = afficheRandomImage( imgVerte, "vert"  );
    imgBlanc = afficheRandomImage( imgBlanc, "blanc"  );
    imgOrange = afficheRandomImage( imgOrange, "orange"  );
    imgJaune= afficheRandomImage( imgJaune, "jaune" );
    imgViolet = afficheRandomImage( imgViolet, "violet"  );
  }
}

public void mousePressed() {
  if (mouseButton == LEFT && x==1) {
    searchColor1 = get( mouseX, mouseY );
    image(imgJaune, 0, 0);

    s1 = true;
  }
  if (mouseButton == LEFT && x== 2) {
    searchColor2 = get( mouseX, mouseY );
    image(imgRouge, 0, 0);
    s2 = true;
  }

  if (mouseButton == LEFT && x==3) {
    searchColor3 = get( mouseX, mouseY );
    image(imgBleue, 0, 0);
    s3 = true;
  }
  if (mouseButton == LEFT && x==4) {
    searchColor4 = get( mouseX, mouseY );
    image(imgVerte, 0, 0);
    s4 = true;
  }
  if (mouseButton == LEFT && x==5) {
    searchColor5 = get( mouseX, mouseY );
    image(imgBlanc, 0, 0);
    s5 = true;
  }
  if (mouseButton == LEFT && x==6) {
    searchColor6 = get( mouseX, mouseY );
    image(imgViolet, 0, 0);
    s6 = true;
  }
  if (mouseButton == LEFT && x==7) {
    searchColor7 = get( mouseX, mouseY );
    image(imgOrange, 0, 0);
    s7 = true;
  }

  if (mouseButton == RIGHT && x==1) {
    s1 = false;
  }
  if (mouseButton == RIGHT && x== 2) {
    s2 = false;
  }

  if (mouseButton == RIGHT && x==3) {
    s3 = false;
  }
  if (mouseButton == RIGHT && x==4) {
    s4 = false;
  }
  if (mouseButton == RIGHT && x==5) {
    s5 = false;
  }
  if (mouseButton == RIGHT && x==6) {
    s6 = false;
  }
  if (mouseButton == RIGHT && x==7) {
    s7 = false;
  }
}

// n'importe quelle touche on initialise les couleurs
//void keyPressed() {
/*s1 = false;
 s2 = false;
 s3 = false;
 s4 = false;*/
//}


public Point avg( ArrayList l ) {
  if (l.size() == 0) {
    return new Point( 0, 0 );
  }
  int x = 0;
  int y = 0;
  for ( Iterator i = l.iterator(); i.hasNext(); ) {
    Point p = (Point)i.next();
    x += p.x;
    y += p.y;
  }
  return new Point( x  / l.size(), y / l.size());
}

public class Point {
  int x;
  int y;

  Point( int x, int y ) {
    this.x = x;
    this.y = y;
  }
}

public PImage afficheRandomImage(PImage refImage ,String refDossier) {
  list_directory liste;
  liste = new list_directory(refDossier, "jpg|png|gif");
  
  String hasard = liste.fichiers[floor(random(liste.nb_items))];

  refImage = loadImage(hasard);
  
  return refImage;
}
// list_directory(nom du dossier, extension a filtrer separ\u00e9s par des |);
// renvoie un array de string
class list_directory {
  String extension;
  String[] fichiers;
  String dossier;
  int nb_items;

  //constructeur
  list_directory(String dossier, String extension) {
    this.dossier=dossier;
    this.extension=extension;
    fichiers = listFileNames(sketchPath + "/" + dossier, extension);
    nb_items=fichiers.length;
    println(fichiers.length + "elements");
  }

  // listage le contenu d'un dossier en ne prenant que les extensions fournies -------------------------
  public String[] listFileNames(String dir, String extension) {
    File file = new File(dir);
    if (file.isDirectory()) {
      String names[] = file.list();

      // procedure d'elimination des fichiers non concern\u00e9s
      String[] names_ok= {
      };
      for (int i=0; i<names.length;i++) {
        String[] m1 = match(names[i], extension);
        if (m1 != null) {
          names_ok=append(names_ok, dossier+"/"+names[i]);
        }
      }
      return names_ok;
    }
    else {
      // If it's not a directory
      println("le nom fourni n'est pas celui d'un dossier");
      return null;
    }
  }
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--hide-stop", "jeu_construction_couleur_02" });
  }
}
