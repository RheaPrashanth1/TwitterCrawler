#Import the necessary methods from tweepy library
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream
import json

tweetCount = 0
# Enter Twitter API Keys
access_token = "3028914487-zriqd4Qo4DUjj1IJZbAPC6McsaALwYiSMICvfms"
access_token_secret = "u929aKlFsSRjT2eTAOhBWIj2hd4FYVJjeU5nTqkXwH2GV"
consumer_key = "r9Zv2p5DWekyQnC1aXHD8k5ks"
consumer_secret = "QITRb64e44ChymHmYFKRWSAjS43TbJmCA8fwa2G6AtEnnvNm7t"
OF = open("tweet.txt", "a")
filenum = 0
#outF = open("tweet.txt", "w")
#outF = open("tweet.txt", "w")

# Listener class inherits from StreamListener
class Listener(StreamListener):
    
    def on_data(self,data):
        global tweetCount 
        global OF
        global filenum 

        Dict = json.loads(data)  #JSON object to python DICT
        Dict.update({"before" : 23}) #append to DICT
        finalTweet = json.dumps(Dict) #convert DICT to JSON
        tweetCount = tweetCount + 1
        OF.write(finalTweet + "\n") #put JSON objects one at a time on newline
        print(finalTweet)

        if  OF.tell() >= 10*1024*1024:  #If file count > 10 MB open new text file
            tweetCount = 0
            filenum = filenum + 1
            newfile = "tweet" + str(filenum) + ".txt"
            OF = open(newfile, "a")

        if  filenum == 200:  #If 2GB of total data, stop streaming
            print("StreamListener no longer streaming tweets")
            return False

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
