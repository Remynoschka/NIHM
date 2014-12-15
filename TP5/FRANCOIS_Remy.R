v = c(12, .4, 5, 2, 50, 8, 3, 1, 4, .25) # data


percentile = quantile(v,0.90);
boxplot(v);
barplot(v);

data = read.table("./data.txt", header=TRUE, sep=",")
participant2SurfPad=subset(data,Participant==2 & Technique=="SurfPad")
TempsMoyen = mean(participant2SurfPad[,"Time"])

techniques=unique(data$Technique)




tempsMoyenParTechnique = function(vTechnique){
  TempsMoyen = mean(vTechnique[,"Time"])
  return(TempsMoyen)
}