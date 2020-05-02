#Import the necessary methods from tweepy library
import tweepy
import json
import sys
import os
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream

# Enter Twitter API Keys
access_token = "3028914487-zriqd4Qo4DUjj1IJZbAPC6McsaALwYiSMICvfms"
access_token_secret = "u929aKlFsSRjT2eTAOhBWIj2hd4FYVJjeU5nTqkXwH2GV"
consumer_key = "r9Zv2p5DWekyQnC1aXHD8k5ks"
consumer_secret = "QITRb64e44ChymHmYFKRWSAjS43TbJmCA8fwa2G6AtEnnvNm7t"
filecount = 0
tweetLimit =  0
#outF = open("tweet.txt", "w")
#outF = open("tweet.txt", "w")

# Listener class inherits from StreamListener
class Listener(StreamListener):
      
    def __init__(self, api=None):
       # super(listener, self).__init__()
        self.num_tweets = 0

    def on_connect(self):
        print("Connection is established")

    def on_data(self, data):   
      
        decoded = json.loads(data) #turn JSON into python dict for each JSON object
        id = decoded["created_at"]
        outF = open("tweet.txt", "w")


        print(id)
     #   print(data)
      
       
        
        return True
        

    def on_error(self, tweetstatus):
        if tweetstatus == 420:
            print("Error occured, loser")
        if tweetstatus == 401:
            print("Authentication error")
            return False

    


if __name__ == '__main__':  
#Connect to twitter stream w/ authentication information
    
    L = Listener()
    auth = OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    stream = Stream(auth, L)
    stream.filter(locations=[-118.306274,33.896637,-117.925186,34.072569],languages=["en"]) 
    #LA, Huntington Park, -> La Habra