#include <jni.h>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_tsci_beers_di_NetworkModule_baseUrl(JNIEnv *env, jobject thiz) {
    _jstring *baseURl = env->NewStringUTF("https://api.punkapi.com/v2/");
    return baseURl;
}