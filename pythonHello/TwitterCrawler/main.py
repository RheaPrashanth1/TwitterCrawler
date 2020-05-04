#Import the necessary methods from tweepy library
import tweepy
import json
import sys
import os
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream
import urllib
from urllib.request import urlopen

# Enter Twitter API Keys
access_token = "3028914487-zriqd4Qo4DUjj1IJZbAPC6McsaALwYiSMICvfms"
access_token_secret = "u929aKlFsSRjT2eTAOhBWIj2hd4FYVJjeU5nTqkXwH2GV"
consumer_key = "r9Zv2p5DWekyQnC1aXHD8k5ks"
consumer_secret = "QITRb64e44ChymHmYFKRWSAjS43TbJmCA8fwa2G6AtEnnvNm7t"
filecount = 0
tweetLimit =  0
#outF = open("tweet.txt", "w")
#outF = open("tweet.txt", "w")

# https://www.w3schools.com/python/python_file_handling.asp
filetweets = open("tweets.json", "a")

# Listener class inherits from StreamListener
class Listener(StreamListener):
      
    def __init__(self, api=None):
       # super(listener, self).__init__()
        self.num_tweets = 0

    def on_connect(self):
        print("Connection is established")

    # def on_data(self, data):   
      
    #     decoded = json.loads(data) #turn JSON into python dict for each JSON object
    #     id = decoded["created_at"]
    #     outF = open("tweet.txt", "w")

    #     print(id)
    #  #   print(data)   
        
    #     return True

    def on_status(self, status):

        dict = {'user': status.user.screen_name, 'tweet': status.text, 'location': status.user.location}#, 'url': link}

        # https://stackabuse.com/reading-and-writing-json-to-a-file-in-python/
        if (self.num_tweets < 100):

            filetweets.write(json.dumps(dict))
            filetweets.write("\n")

            self.num_tweets += 1
            return True
        else:
            return False
        

    def on_error(self, tweetstatus):
        if tweetstatus == 420:
            print("Error occured, loser")
        if tweetstatus == 401:
            print("Authentication error")
            return False

    


# if __name__ == '__main__':  
#Connect to twitter stream w/ authentication information
    
    # https://medium.com/@jaimezornoza/downloading-data-from-twitter-using-the-streaming-api-3ac6766ba96c
L = Listener()
auth = OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)
stream = Stream(auth, L)
stream.filter(locations=[-118.306274,33.896637,-117.925186,34.072569],languages=["en"], track = ["corona", "virus", "quarantine"]) 
#LA, Huntington Park, -> La Habra