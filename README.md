# Project U-Bot &middot; 

The Home Computing System (Intelligent Auto-bot) project developed in Spring Boot

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0eaf67a1663248adb44313a5822a1791)](https://www.codacy.com/app/hbhavsar2110/project-u-bot?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=harshbits/project-u-bot&amp;utm_campaign=Badge_Grade)
Version Details:
----------------

#### Current Version: `0.0.1-SNAPSHOT`

Current Support
---------------
* Chat Bot
* Weather Forecasting
* Home Automation
* Spotify Music 

Future Scope & Enhancements
---------------------------
* Use of [grpc-spring-boot-services](https://github.com/harshbits/grpc-spring-boot-services) for Client-Server communication
* Use of [unnat-krushi-pranali](https://github.com/harshbits/unnat-krushi-pranali) for Smart Gardening
* Congnitive Cuisuine/Recipe Support


Prerequisites
-------------
In order to start work with project, make sure you have configured IDE/System with prerequisites steps.

### Java Version
* Java SE Development Kit 8 (JDK 8) 

### Lombok
* Install Lombok for your IDE (i.e. STS or Eclipse)
* Version to install: `1.16.16`
* Installation Guide: [Please refer this link](http://codeomitted.com/setup-lombok-with-stseclipse-based-ide/)


Software Dependencies
----------------------

#### Cloud Services (With Premium Subscription) 

* <img src="https://cloud.google.com/_static/aab308d0ac/images/cloud/icons/favicons/favicon.ico" height="18"> [Google Cloud](https://cloud.google.com/speech/) - Speech to Text API 
  * **Documentation**
  * **Usage**
* [![AWS-Polly](https://a0.awsstatic.com/main/images/site/favicon.ico) AWS-Polly](https://aws.amazon.com/polly/) - Text to Speech (Sound like a Human voice)
  * **Documentation**
  * **Usage**
* <img src="https://www.twilio.com/docs/static/img/favicons/favicon_57.98200b6899e6.png" height="18"> [Twilio-sms](https://www.twilio.com/docs/api?filter-product=sms) - To control system using text messages and get status/update about the system
  * **Documentation**
  * **Usage**
* <img src="https://api.ai/assets/ico/favicon-220ed053738020816b0c8b48de7212a8.png" height="18"> [api.ai](https://docs.api.ai/) - A natural language understanding platform
  * **Documentation**
  * **Usage**
 
 


#### Cloud Services (No Premium Subscription required) 

* [![Rabbit MQ](https://www.rabbitmq.com/favicon.ico) RabbitMQ](https://www.rabbitmq.com/) - As AMQP protocol provider for messanging betweeen server and client application
* [![Redis](https://redis.io/images/favicon.png) Redis](http://redis.io/) - To store and retrieve real time data


Hardware Dependencies
----------------------
#### Raspberry Pi [![raspberry-pi-3-model-b](https://www.raspberrypi.org/app/themes/mind-control/images/favicon.png)]()
* [Raspberry Pi 3 model B](https://www.raspberrypi.org/products/raspberry-pi-3-model-b/) - As CPU for home computing system
  * It is Highly recommonded model for best performance.
  * For development/testing, you can also use [Raspberry Pi Zero W](https://www.raspberrypi.org/products/pi-zero-w/), but not for working model.

#### Microphone
* [PlayStation Eye by Sony](https://www.amazon.com/gp/product/B000VTQ3LU/ref=oh_aui_detailpage_o06_s00?ie=UTF8&psc=1)
  * It works best with Raspberry Pi 3 with noise cancellation

#### Speaker (Bluetooth + AUX)
* [JBL Flip 3](https://www.jbl.com/bluetooth-speakers/JBL+FLIP+III.html)
  * Note: It turned out to be cheap and best with AUX cable support
  
