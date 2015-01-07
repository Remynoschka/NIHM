
library("gplots")

v = c(12, .4, 5, 2, 50, 8, 3, 1, 4, .25) # data

#q1
percentile = quantile(v,0.90);

#q2
boxplot(v);
barplot(v);

data = read.table("./data.txt", header=TRUE, sep=",")
participant2SurfPad=subset(data,Participant==2 & Technique=="SurfPad")
TempsMoyen = mean(participant2SurfPad[,"Time"])

techniques=unique(data$Technique)



#q3
tempsMoyenTechnique = function(data, technique){
  vector = subset(data, Technique==technique)
  TempsMoyen = mean(vector[,"Time"])
  return(TempsMoyen)
}

#q4
TempsMoyensChaqueTech = sapply(techniques, tempsMoyenTechnique, data=data);

#q5
barplot(TempsMoyensChaqueTech, names=techniques);

#q6
tempsMoyenTechniqueSansErreur = function(data, technique){
  vector = subset(data, Technique == technique & Err == 0);
  TempsMoyen = mean(vector[,"Time"]);
  return(TempsMoyen);
}

TempsMoyensChaqueTechSE = sapply(techniques, tempsMoyenTechniqueSansErreur, data=data);

barplot(TempsMoyensChaqueTech, names=techniques);

#q7
calculIntervalleConf = function(sd, n){
  ci = 1.96 * (sd / sqrt(n))
  return(ci);
}

calculICTechnique = function (data, technique){
    temps = subset(data, Technique == technique)$Time;
    return(calculIntervalleConf(sd(temps), length(temps)));    
}

#q8

intervallesConf = sapply(techniques, calculICTechnique, data=data);
barplot2(TempsMoyensChaqueTechSE, names.arg=techniques, 
        plot.ci=TRUE, 
        ci.l=TempsMoyensChaqueTechSE-intervallesConf, 
        ci.u=TempsMoyensChaqueTechSE+intervallesConf);
