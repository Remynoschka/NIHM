
//globally 
//declare the min and max variables that you need in parseInfo 
float minX, maxX; 
float minY, maxY; 
int totalCount; // total number of places 
float minPopulation, maxPopulation; 
float minSurface, maxSurface; 
float minAltitude, maxAltitude;
int minPopulationToDisplay = 10000;
City selectedCity;
//declare the variables corresponding to the column ids for x and y 
int X = 1; 
int Y = 2;
// and the tables in which the city coordinates will be stored 
City[] cities;

void setup() { 
  size(800, 800);
  readData();
} 

void draw() { 
  background(255);
  String s = "Afficher les populations supérieures à : " + minPopulationToDisplay;
  fill(0);
  text(s, 10, 10, 500, 80); 
  for (int i = 0; i < totalCount-2; i++) {    
    if (cities[i].population > minPopulationToDisplay) { 
      cities[i].drawCity();
    }
  }
}

void readData() { 
  String[] lines = loadStrings("./villes.tsv");
  parseInfo(lines[0]); // read the header line
  cities = new City[totalCount];
  for (int i = 2; i < totalCount; ++i) { 
    String[] columns = split(lines[i], TAB); 
    cities[i-2] = new City(int(columns[0]), columns[4], float(columns[1]), float(columns[2]), float(columns[5]), float(columns[6]), float(columns[7]));
  }
}


void parseInfo(String line) { 
  String infoString = line.substring(2); // remove the # 
  String[] infoPieces = split(infoString, ','); 
  totalCount = int(infoPieces[0]); 
  minX = float(infoPieces[1]); 
  maxX = float(infoPieces[2]); 
  minY = float(infoPieces[3]); 
  maxY = float(infoPieces[4]); 
  minPopulation = float(infoPieces[5]); 
  maxPopulation = float(infoPieces[6]); 
  minSurface = float(infoPieces[7]); 
  maxSurface = float(infoPieces[8]); 
  minAltitude = float(infoPieces[9]); 
  maxAltitude = float(infoPieces[10]);
}

void keyPressed() {
  if (key == CODED) {
    if (keyCode == UP) {
      if (minPopulationToDisplay == 0){
        minPopulationToDisplay = 1;
      } else {
        minPopulationToDisplay*=10;
      }
    } else if (keyCode == DOWN) {
      if ( minPopulationToDisplay > 100000) {
        minPopulationToDisplay/=10;
      } else
        minPopulationToDisplay/=10;
    }
  }
  redraw();
}

void mouseMoved() {
  //println("X: " + mouseX + " Y: " + mouseY );
    if (this.selectedCity!=null) {
      selectedCity.selected = false;
      selectedCity.mouseOver = false;
    }
    selectedCity = pick(mouseX, mouseY);
    if (selectedCity!=null) {
      selectedCity.selected = true;
      selectedCity.mouseOver = true;
    }
  redraw();
}

City pick(int px, int py) {
  for (int i = 2; i < totalCount-2; i++) {
    if (cities[i].population > minPopulationToDisplay) {
      if (cities[i].contains(px, py)){
        return cities[i];
      }
    }
  }  
  return null;
}

