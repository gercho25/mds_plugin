var serviceDiscovery = {
    getNetworkServices:function(service, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "serviceDiscovery", "getNetworkServices", [service]);
    },
	getConnectedSSID:function(successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "serviceDiscovery", "getConnectedSSID", []);
    }
};

module.exports = serviceDiscovery;