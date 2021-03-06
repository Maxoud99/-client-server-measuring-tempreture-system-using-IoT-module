/*
    This sketch sends a string to a TCP server, and prints a one-line response.
    You must run a TCP server in your local network.
    For example, on Linux you can use this command: nc -v -l 3000
*/

#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

#ifndef STASSID
#define STASSID "HUAWEI nova 3i"
#define STAPSK  "saragamal"
#endif

const char* ssid     = STASSID;
const char* password = STAPSK;

const char* host = "192.168.43.118";
const uint16_t port = 1234;

ESP8266WiFiMulti WiFiMulti;
  // Use WiFiClient class to create TCP connections
  WiFiClient client;

int outputpin = A0;

void setup() {
  Serial.begin(115200);

  // We start by connecting to a WiFi network
  WiFi.mode(WIFI_STA);
  WiFiMulti.addAP(ssid, password);

  Serial.println();
  Serial.println();
  Serial.print("Wait for WiFi... ");

  while (WiFiMulti.run() != WL_CONNECTED) {
    Serial.print(".");
    delay(15000);
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  delay(15000);



  if (!client.connect(host, port)) {
    Serial.println("connection failed");
    Serial.println("wait 1 sec...");
    delay(15000);
    return;
  }

}

void loop() {
  Serial.print("connecting to ");
  Serial.print(host);
  Serial.print(':');
  Serial.println(port);

  int analogValue = analogRead(outputpin);
float millivolts = ((analogValue)/1024.0)*3300.0;
float celsius = millivolts/100;
Serial.println("I am Sensor 2");
Serial.println(celsius);
//client.println();
String s;
  s+=( "I am Sensor 2 ") ;
  s+= String(celsius,2);
client.println( s );
float fahrenheit = ((celsius * 9)/5 + 32);
//Serial.println(fahrenheit);
delay(15000);
  
  // This will send the request to the server
  
   
  //read back one line from server
  Serial.println("receiving from remote server");
  String line = client.readStringUntil('\r');
  Serial.println(line);

  Serial.println("closing connection");
 // client.stop();

  Serial.println("wait 1 sec...");
  delay(15000);
}

