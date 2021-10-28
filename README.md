# Covid19-data-transfer-from-API-to-google-sheet
This project public covid19 data to google from Covid19 API
this project has two parts,
      1.get data from Covid19 public API
      2. POST the data to google sheet using google sheet API

I used to get the data from this API https://api.covid19api.com/world/total 
   this API response to a JSON file like below one
   ![tempsnip](https://user-images.githubusercontent.com/77588716/139216455-11f9c9d4-08a8-40be-b938-32baa008fbd4.png)


#then use JsonNode to convert json file to String 
then use google sheet api to append the data to your google sheet
