class City { 
  int postalcode; 
  String name; 
  float x; 
  float y; 
  float population; 
  float density;
 float altitude;  
  
  City(int cp, String name, float x, float y, float pop, float density, float altitude){
    this.postalcode = cp;
    this.name = name;
    this.x = x;
    this.y = y;
    this.population = pop;
    this.density = density;
    this.altitude = altitude;
  }

  // put a drawing function in here and call from main drawing loop 
  void drawCity(){
    color black = color(0,0,0);
    color couleur;
    // densite -> couleur entre bleu et rouge
    // populatio -> taille du cercke
    
    float ratioMaxPop = (population/maxPopulation);
    if (ratioMaxPop > 0.0005){ 
      couleur = color(255*ratioMaxPop,0,255-255*ratioMaxPop);
      color couleurTexte = color(255*(1/ratioMaxPop),0,255*ratioMaxPop);
      
      fill(couleur);
      ellipse((int)mapX(x),(int) mapY(y),ratioMaxPop * 100,ratioMaxPop *100); 
//      if (ratioMaxPop > 0.05){
//         fill(couleurTexte);
//         text(name,(int)mapX(x),(int)mapY(y)); 
//      }
    }
    set((int) mapX(x), (int) mapY(y), black);
  }
  
  float mapX(float x) { 
    return map(x, minX, maxX, 0, 800);
  }
  
  float mapY(float y) { 
    return map(y, minY, maxY, 800, 0);
  }
} 


