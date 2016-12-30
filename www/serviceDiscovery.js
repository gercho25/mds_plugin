var serviceDiscovery = {
    getNetworkServices:function(service, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "serviceDiscovery", "getNetworkServices", [service]);
    },
	getConnectedSSID:function(service, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "serviceDiscovery", "getConnectedSSID", [service]);
    }
};

module.exports = serviceDiscovery;