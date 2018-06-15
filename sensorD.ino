#include <Ultrasonic.h>

#define echoPin 13 //Pino 13 recebe o pulso do echo
#define trigPin 12 //Pino 12 envia o pulso para gerar o echo
Ultrasonic ultrasonic(12, 13);

#define echoPin2 4 //Pino 4 recebe o pulso do echo
#define trigPin2 3 //Pino 3 envia o pulso para gerar o echo
Ultrasonic ultrasonic2(3, 4);

String vaga4 = "0";
String vaga3 = "0";
String vaga2 = "0";
String vaga1 = "0";
String est =  "0000";

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600); //inicia a porta serial
  pinMode(echoPin, INPUT); // define o pino 13 como entrada (recebe)
  pinMode(trigPin, OUTPUT); // define o pino 12 como saída (envia)

}

void loop() {
  //seta o pino 12 com um pulso baixo "LOW" ou desligado ou ainda 0
  digitalWrite(trigPin, LOW);
  digitalWrite(trigPin2, LOW);
  
  // delay de 2 microssegundos
  delayMicroseconds(2);
  //seta o pino 12 com pulso alto "HIGH" ou ligado ou ainda 1, criando o pulso ultrassônico
  digitalWrite(trigPin, HIGH);
  digitalWrite(trigPin2, HIGH);
  //delay de 10 microssegundos
  delayMicroseconds(10);

  digitalWrite(trigPin, LOW);
  digitalWrite(trigPin2, LOW);
  // função Ranging, faz a conversão do tempo de
  //resposta do echo em centimetros, e armazena
  //na variavel distancia
  int distancia = (ultrasonic.Ranging(CM));
  int distancia2 = (ultrasonic2.Ranging(CM));
  
  //vaga04
  if(distancia <=5){

    vaga4= "1";
    
  }else{
    
    vaga4 = "0";
  }

    //vaga02
  if(distancia2 <=5){

    vaga2= "1";
    
  }else{
    
    vaga2 = "0";
  }
  
  est = vaga1 + vaga2 + vaga3+ vaga4;
  
  Serial.print(est);//enviando a string estado do estacionamento pela serial
  Serial.println();
  delay(15000); //espera 15 segundo para fazer a leitura novamente
}


