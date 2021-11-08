# Covid-data-to-google-sheet

Steps:
   Copy the application.properties file from src/main/resources to project root directory issue maven install command cp src/main/resourcs/application.properties.

   Notes : Make sure application.properties file is available on the same folder as the jar


This project public covid19 data to google from Covid19 API
this project has two parts,
      1.GET data from Covid19 public API
      2.POST the data to google sheet using googlesheet API
      
      
#GET data from Covid19 public API
 
Here used this API,
    
    https://api.covid19api.com/world/total 
    
to get covid data in a JSON format like below,
 
 ![tempsnip](https://user-images.githubusercontent.com/77588716/139216455-11f9c9d4-08a8-40be-b938-32baa008fbd4.png)


#POST the data to google sheet using googlesheet API

I used googlesheet API POST Method url,

https://sheets.googleapis.com/v4/spreadsheets/{spreadsheetId}/values/{range}:append  

the spreadsheetId you will get from the google sheet url,

![Screenshot 2021-11-08 104228](https://user-images.githubusercontent.com/77588716/140687741-11cc97a8-3a20-4b07-8189-eb4f8a34b0e6.png)



to append the data to my googlesheet .

the JSON format to upload the googlesheet,

example,

      {
         "majorDimension": "ROWS",
         "range": "A1",
         "values": [
              [ "data to store"]
          ]
       }
       
 
here,

beartoken is used for the authentication it attached to the header.

after that what are data want to store in the googlesheet convert into string and post using above url.

