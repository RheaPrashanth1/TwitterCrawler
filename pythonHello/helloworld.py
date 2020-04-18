from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream
import json



access_token = 
access_token_secret = 
consumer_key = 
consumer_secret = 

num_tweets = 0
tweet_Limit = 1000

class Listener(tweepy.StreamListener):

  def on_data(self, data):
        if num_tweets < tweet_Limit
            num_tweets+= 1
            return True
        
        else:
            stream.disconnect()





if __name__ == '__main__':  
# Handle Twitter authetification and the connection to Twitter Streaming API
    L = Listener()
    auth = OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    stream = Stream(auth, L)
  stream.filter(locations=[,languages=["en"])  
   #find coordinates to use... San Diego? La? LMK


      
