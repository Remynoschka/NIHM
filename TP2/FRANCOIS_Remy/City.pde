class City { 
  int postalcode; 
  String name; 
  float x; 
  float y; 
  float population; 
  float density;
  float altitude; 
  float radius; 
  boolean selected;
  boolean mouseOver;

  City(int cp, String name, float x, float y, float pop, float density, float altitude) {
    this.postalcode = cp;
    this.name = name;
    this.x = x;
    this.y = y;
    this.population = pop;
    this.density = density;
    this.altitude = altitude;
  }

  // put a drawing function in here and call from main drawing loop 
  void drawCity() {
    color black = color(0, 0, 0);
    color couleur;
    // densite -> couleur entre bleu et rouge
    // populatio -> taille du cercle

    float ratioMaxPop = (population/maxPopulation);
    couleur = color(255*ratioMaxPop, 0, 255-255*ratioMaxPop);
    color couleurTexte = color(255*(1/ratioMaxPop), 0, 255*ratioMaxPop);
    radius = ratioMaxPop * 100;
    if (mouseOver) {
      strokeWeight(3);
    } else {
      strokeWeight(1);
    }
    fill(couleur);
    ellipse((int)mapX(x), (int) mapY(y), radius, radius); 
    strokeWeight(1);
    if (selected) {
      fill(200);
      rect((int) mapX(x)+radius/2, (int) mapY(y), textWidth(name)+10, 20);
      fill(50);
      text(name, (int) mapX(x)+radius/2 + 5, (int) mapY(y)+15);
    }

    set((int) mapX(x), (int) mapY(y), black);
  }

  float mapX(float x) { 
    return map(x, minX, maxX, 0, 800);
  }

  float mapY(float y) { 
    return map(y, minY, maxY, 800, 0);
  }


  boolean contains(int px, int py) {
    return dist((int) mapX(x), (int) mapY(y), px, py) <= radius/2;
  }
} 

