module Talker
{
    sequence<string> StringSeq;
    
    interface ChatClient
    {
        void notifyCallback();
        void reciveMessage(string message);
    };

    interface ChatController
    {
        void sendMessage(string msg, string destination);

        void broadcastMessage(string message);

        void subscribe(ChatClient* client);

        void register(string hostname);
        
        StringSeq getClients();
    };

}