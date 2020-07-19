// ISecurityCenter.aidl
package com.example.hasee.androidipcdemo;

// Declare any non-default types here with import statements
//提供解密的功能
interface ISecurityCenter {
    String encrypt(String content);
    String decrypt(String password);
}
