#Import the necessary methods from tweepy library
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream
import json

# Enter Twitter API Keys
access_token = "3028914487-zriqd4Qo4DUjj1IJZbAPC6McsaALwYiSMICvfms"
access_token_secret = "u929aKlFsSRjT2eTAOhBWIj2hd4FYVJjeU5nTqkXwH2GV"
consumer_key = "r9Zv2p5DWekyQnC1aXHD8k5ks"
consumer_secret = "QITRb64e44ChymHmYFKRWSAjS43TbJmCA8fwa2G6AtEnnvNm7t"
filecount = 0
tweetLimit =  0
file = open('tweet.txt','w')
# Listener class inherits from StreamListener
class Listener(StreamListener):
      
    def __init__(self, api=None):
       # super(listener, self).__init__()
        self.num_tweets = 0

    def on_data(self, data):   
      
        if (filecnt >= 500):
           print("done with data")
       # f.close
        #if (file.tell() >= 200):
        #  filecnt = filecnt + 1
        print(data)
       #return True
        

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
    #LA, Huntingtom Park, -> La Habra