module Demo
{

    sequence<string> StringSeq;

    interface CallbackReceiver
    {
        void printMsg(string s);
    } 

    interface ChatManager
    {
        void subscribe(CallbackReceiver* callback, string hostname);

        void sendMessage(string msg, string hostname);
    }
}