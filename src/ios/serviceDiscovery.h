/*
 * Service discovery plugin interface
 */


#import <Foundation/Foundation.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>
#import <Cordova/CDVPlugin.h>
#import <Cordova/CDVInvokedUrlCommand.h>

//Wifi
#import <Cordova/CDV.h>

@interface serviceDiscovery: CDVPlugin
- (void)getNetworkServices: (CDVInvokedUrlCommand*)command;
- (void)getConnectedSSID:(CDVInvokedUrlCommand*)command;
@end

