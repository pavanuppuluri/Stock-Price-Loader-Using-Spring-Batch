**Stock-Price-Loader-Using-Spring-Batch**

This repo is consists of part of a Stock Market Pricing Solution.
We have an application which provides stock prices to the subscribers.
Our application will get the price data from vendors like Bloomberg, Reuters, IDC etc.

All these price files need to be loaded into our application database and after applying some hierarchy rules our application sends the best price to the subscribers.
Now this repo solves the loading part.

Vendors will be sending price feeds as .csv files. Challenge here is they have their own csv header formats.
For example, in our case 

Bloomberg, IDC have header format as - 
**"StockSymbol", "Vendor", "Price", "Currency"**

Reuters header format is - 
**"Vendor", "Price", "Currency", "StockTicker"**

Below is the sample data

**Bloomberg price data**
**StockSymbol,Vendor,Price,Currency**

P1A,Bloomberg,11,USD

P1B,Reuters,42,INR

P1C,Bloomberg,300,EURO

**IDC price data**
**StockSymbol,Vendor,Price,Currency**

P1A,IDC,44,USD

P1B,IDC,77,INR

P1C,IDC,63,EURO


**Reuters price data**

**Vendor|Price|Currency|StockTicker**

Reuters|889|USD|P1A

Reuters|990|INR|P1B

Reuters|89|EURO|P1C


Now using Spring Batch we are loading these price feeds into our database after converting them 
into common format during processing and enriching them with audit information.


**Steps to run this application**
1. Clone this repo - https://github.com/pavanuppuluri/Stock-Price-Loader-Using-Spring-Batch.git
2. Create database table (Query given in https://github.com/pavanuppuluri/Stock-Price-Loader-Using-Spring-Batch/blob/master/misc/db_queries.txt)
3. Run the application

It should load the data into database table STOCK_PRICE.

Happy Coding!!!


